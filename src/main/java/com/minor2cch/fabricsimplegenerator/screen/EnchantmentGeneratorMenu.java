package com.minor2cch.fabricsimplegenerator.screen;

import com.minor2cch.fabricsimplegenerator.block.entity.EnchantmentGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGMenus;
import com.minor2cch.fabricsimplegenerator.screen.slot.GeneratorEnchantedItemSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;

public class EnchantmentGeneratorMenu extends AbstractGeneratorMenu {
    public EnchantmentGeneratorMenu(int syncId, Inventory inventory) {
        super(FSGMenus.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, syncId, inventory);
    }
    public EnchantmentGeneratorMenu(int syncId, Inventory inventory, Container container, ContainerData data) {
        super(FSGMenus.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, syncId, inventory, container, data);
    }
    @Override
    protected void createIngredientSlot(Container container){
        this.addSlot(new GeneratorEnchantedItemSlot(this, container, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isEnchantingItem(itemStack);
    }
    public boolean isEnchantingItem(ItemStack itemStack) {
        return EnchantmentGeneratorBlockEntity.enchantingItemUsable(itemStack);
    }
}
