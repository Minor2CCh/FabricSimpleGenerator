package com.Minor2CCh.fabricsimplegenerator.screen.slot;

import com.Minor2CCh.fabricsimplegenerator.screen.AbstractGeneratorScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BatterySlot extends Slot {
    private final AbstractGeneratorScreenHandler handler;
    public BatterySlot(AbstractGeneratorScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }
    @Override
    public boolean canInsert(ItemStack stack) {
        return handler.isEnergySlot(stack);
    }
}
