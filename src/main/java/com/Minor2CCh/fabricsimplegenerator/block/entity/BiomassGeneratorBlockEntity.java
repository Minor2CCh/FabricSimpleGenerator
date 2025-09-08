package com.Minor2CCh.fabricsimplegenerator.block.entity;

import com.Minor2CCh.fabricsimplegenerator.block.BiomassGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.Minor2CCh.fabricsimplegenerator.screen.BiomassGeneratorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;


public class BiomassGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final float BIO_TIME_MUL = 1000.0F;
    public BiomassGeneratorBlockEntity(@Nullable GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY, pos, state);
        if(gTier != null){
            setGeneratorSetting(gTier, gMul);
        }
    }
    public BiomassGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY, pos, state);
        GeneratorTier tempTier = BiomassGeneratorBlock.getTier(state.getBlock());
        int tempMul = BiomassGeneratorBlock.getMul(state.getBlock());
        if(tempTier != null){
            setGeneratorSetting(tempTier, tempMul);
        }
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BiomassGeneratorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new BiomassGeneratorScreenHandler(syncId, inv, this, propertyDelegate);
    }
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.fsg.generator.biomass");
    }

    public static boolean bioUsable(ItemStack stack){
        return bioUsable(stack.getItem());
    }
    public static boolean bioUsable(Item item){
        return ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.containsKey(item) && bioTime(item) > 0;
    }
    public static int bioTime(ItemStack stack){
        return bioTime(stack.getItem());
    }
    public static int bioTime(Item item){
        float compostValue = ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.getFloat(item);
        if(compostValue <= 0){
            return 0;
        }
        return (int)(compostValue * compostValue * BIO_TIME_MUL);
    }

    @Override
    public boolean ingredientUsable(ItemStack itemStack){
        return bioUsable(itemStack);
    }
    @Override
    public int getIngredientTime(ItemStack itemStack){
        return bioTime(itemStack);
    }

}
