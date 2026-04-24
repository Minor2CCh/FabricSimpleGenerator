package com.minor2cch.fabricsimplegenerator.screen.slot;

import com.minor2cch.fabricsimplegenerator.screen.EnchantmentGeneratorMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GeneratorEnchantedItemSlot extends Slot {
    private final EnchantmentGeneratorMenu handler;

    public GeneratorEnchantedItemSlot(EnchantmentGeneratorMenu handler, Container container, int index, int x, int y) {
        super(container, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.handler.isEnchantingItem(stack);
    }
}
