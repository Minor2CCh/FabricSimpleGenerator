package com.Minor2CCh.fabricsimplegenerator.screen.slot;

import com.Minor2CCh.fabricsimplegenerator.screen.BiomassGeneratorScreenHandler;
import com.Minor2CCh.fabricsimplegenerator.screen.SculkGeneratorScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class GeneratorBioSlot extends Slot {
    private final BiomassGeneratorScreenHandler handler;

    public GeneratorBioSlot(BiomassGeneratorScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return this.handler.isBio(stack);
    }
}
