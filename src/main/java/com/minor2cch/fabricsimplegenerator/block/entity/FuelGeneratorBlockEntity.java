package com.minor2cch.fabricsimplegenerator.block.entity;

import com.minor2cch.fabricsimplegenerator.block.FuelGeneratorBlock;
import com.minor2cch.fabricsimplegenerator.block.GeneratorTier;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.screen.FuelGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class FuelGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final String CONTAINER_NAME = getTranslationKey(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY);
    public FuelGeneratorBlockEntity(@NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY, gTier, gMul, pos, state);
    }

    public FuelGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(FuelGeneratorBlock.getTier(state.getBlock()), FuelGeneratorBlock.getMul(state.getBlock()), pos, state);
    }
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new FuelGeneratorMenu(syncId, inventory, this, dataAccess);
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new FuelGeneratorMenu(syncId, inv, this, dataAccess);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(CONTAINER_NAME);
    }
    // 毎tick実行されるメソッド

    public static boolean fuelUsable(ItemStack stack){
        return fuelUsable(stack.getItem());
    }
    public static boolean fuelUsable(Item item){
        Map<Item, Integer> fuelTimeMap = FurnaceBlockEntity.getFuel();
        return fuelTimeMap.containsKey(item);
    }
    public static int fuelTime(ItemStack stack){
        return fuelTime(stack.getItem());
    }
    public static int fuelTime(Item item){
        Map<Item, Integer> fuelTimeMap = FurnaceBlockEntity.getFuel();
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
