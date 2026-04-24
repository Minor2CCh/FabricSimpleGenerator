package com.minor2cch.fabricsimplegenerator.screen.slot;

import com.minor2cch.fabricsimplegenerator.screen.BiomassGeneratorMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GeneratorBioSlot extends Slot {
    private final BiomassGeneratorMenu handler;

    public GeneratorBioSlot(BiomassGeneratorMenu handler, Container container, int index, int x, int y) {
        super(container, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.handler.isBio(stack);
    }
}
