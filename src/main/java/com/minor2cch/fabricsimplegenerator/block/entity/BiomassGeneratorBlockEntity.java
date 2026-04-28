package com.minor2cch.fabricsimplegenerator.block.entity;

import com.minor2cch.fabricsimplegenerator.block.BiomassGeneratorBlock;
import com.minor2cch.fabricsimplegenerator.block.GeneratorTier;
import com.minor2cch.fabricsimplegenerator.config.FSGConfigLoader;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.screen.BiomassGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;


public class BiomassGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final String CONTAINER_NAME = getTranslationKey(FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY);
    public static final float BIO_TIME_MUL = FSGConfigLoader.getConfig().getBioTime();
    public BiomassGeneratorBlockEntity(@NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY, gTier, gMul, pos, state);
    }
    public BiomassGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(BiomassGeneratorBlock.getTier(state.getBlock()), BiomassGeneratorBlock.getMul(state.getBlock()), pos, state);
    }
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, @NonNull Inventory inventory) {
        return new BiomassGeneratorMenu(syncId, inventory, this, dataAccess);
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, @NonNull Inventory inv, @NonNull Player player) {
        return new BiomassGeneratorMenu(syncId, inv, this, dataAccess);
    }
    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(CONTAINER_NAME);
    }

    public static boolean bioUsable(ItemStack stack){
        return bioUsable(stack.getItem());
    }
    public static boolean bioUsable(Item item){
        return ComposterBlock.COMPOSTABLES.containsKey(item) && bioTime(item) > 0;
    }
    public static int bioTime(ItemStack stack){
        return bioTime(stack.getItem());
    }
    public static int bioTime(Item item){
        float compostValue = ComposterBlock.COMPOSTABLES.getFloat(item);
        if(compostValue <= 0){
            return 0;
        }
        return (int)(Math.pow(compostValue, 2) * BIO_TIME_MUL);
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
