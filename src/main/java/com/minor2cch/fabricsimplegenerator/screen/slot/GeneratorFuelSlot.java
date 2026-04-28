package com.minor2cch.fabricsimplegenerator.screen.slot;

import com.minor2cch.fabricsimplegenerator.screen.FuelGeneratorMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

public class GeneratorFuelSlot extends Slot {
    private final FuelGeneratorMenu handler;

    public GeneratorFuelSlot(FuelGeneratorMenu handler, Container container, int index, int x, int y) {
        super(container, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(@NonNull ItemStack stack) {
        return this.handler.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(@NonNull ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
