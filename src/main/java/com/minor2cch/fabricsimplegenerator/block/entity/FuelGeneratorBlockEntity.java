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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class FuelGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final String CONTAINER_NAME = getTranslationKey(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY);
    public FuelGeneratorBlockEntity(@NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.FUEL_GENERATOR_BLOCK_ENTITY, gTier, gMul, pos, state);
    }

    public FuelGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(FuelGeneratorBlock.getTier(state.getBlock()), FuelGeneratorBlock.getMul(state.getBlock()), pos, state);
    }
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, @NonNull Inventory inventory) {
        return new FuelGeneratorMenu(syncId, inventory, this, dataAccess);
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, @NonNull Inventory inv, @NonNull Player player) {
        return new FuelGeneratorMenu(syncId, inv, this, dataAccess);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(CONTAINER_NAME);
    }
    // 毎tick実行されるメソッド

    public static boolean fuelUsable(Level level, ItemStack stack){
        if(level == null){
            return false;
        }
        return level.fuelValues().isFuel(stack);
    }
    public static int fuelTime(Level level, ItemStack stack){
        if(level == null){
            return 0;
        }
        return level.fuelValues().burnDuration(stack);
    }
    @Override
    public boolean ingredientUsable(ItemStack itemStack){
        return fuelUsable(this.getLevel(), itemStack);
    }
    @Override
    public int getIngredientTime(ItemStack itemStack){
        return fuelTime(this.getLevel(), itemStack);
    }

}
