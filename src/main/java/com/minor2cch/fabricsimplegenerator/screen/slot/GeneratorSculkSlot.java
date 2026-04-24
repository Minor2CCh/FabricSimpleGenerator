package com.minor2cch.fabricsimplegenerator.screen.slot;

import com.minor2cch.fabricsimplegenerator.screen.SculkGeneratorMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GeneratorSculkSlot extends Slot {
    private final SculkGeneratorMenu handler;

    public GeneratorSculkSlot(SculkGeneratorMenu handler, Container container, int index, int x, int y) {
        super(container, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.handler.isSculk(stack);
    }
}
