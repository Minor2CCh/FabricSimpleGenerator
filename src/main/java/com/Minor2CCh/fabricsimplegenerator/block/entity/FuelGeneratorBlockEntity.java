package com.Minor2CCh.fabricsimplegenerator.block.entity;

import com.Minor2CCh.fabricsimplegenerator.block.FuelGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.Minor2CCh.fabricsimplegenerator.screen.FuelGeneratorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class FuelGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public FuelGeneratorBlockEntity(@Nullable GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY, pos, state);
        if(gTier != null){
            setGeneratorSetting(gTier, gMul);
        }
    }

    public FuelGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY, pos, state);
        GeneratorTier tempTier = FuelGeneratorBlock.getTier(state.getBlock());
        int tempMul = FuelGeneratorBlock.getMul(state.getBlock());
        if(tempTier != null){
            setGeneratorSetting(tempTier, tempMul);
        }
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FuelGeneratorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new FuelGeneratorScreenHandler(syncId, inv, this, propertyDelegate);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.fsg.generator.fuel");
    }
    // 毎tick実行されるメソッド

    public static boolean fuelUsable(ItemStack stack){
        return fuelUsable(stack.getItem());
    }
    public static boolean fuelUsable(Item item){
        Map<Item, Integer> fuelTimeMap = FurnaceBlockEntity.createFuelTimeMap();
        return fuelTimeMap.containsKey(item);
    }
    public static int fuelTime(ItemStack stack){
        return fuelTime(stack.getItem());
    }
    public static int fuelTime(Item item){
        Map<Item, Integer> fuelTimeMap = FurnaceBlockEntity.createFuelTimeMap();
        return fuelTimeMap.getOrDefault(item, 0);
    }
    @Override
    public boolean ingredientUsable(ItemStack itemStack){
        return fuelUsable(itemStack);
    }
    @Override
    public int getIngredientTime(ItemStack itemStack){
        return fuelTime(itemStack);
    }

}
