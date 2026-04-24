package com.minor2cch.fabricsimplegenerator.screen.slot;

import com.minor2cch.fabricsimplegenerator.screen.AbstractGeneratorMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BatterySlot extends Slot {
    private final AbstractGeneratorMenu handler;
    public BatterySlot(AbstractGeneratorMenu handler, Container container, int index, int x, int y) {
        super(container, index, x, y);
        this.handler = handler;
    }
    @Override
    public boolean mayPlace(ItemStack stack) {
        return handler.isEnergySlot(stack);
    }
}
