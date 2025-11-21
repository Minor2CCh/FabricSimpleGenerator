package com.Minor2CCh.fabricsimplegenerator.screen;

import com.Minor2CCh.fabricsimplegenerator.block.entity.EnchantmentGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGScreenHandlers;
import com.Minor2CCh.fabricsimplegenerator.screen.slot.GeneratorEnchantedItemSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;

public class EnchantmentGeneratorScreenHandler extends AbstractGeneratorScreenHandler{
    public EnchantmentGeneratorScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(FSGScreenHandlers.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, syncId, playerInventory);
    }
    public EnchantmentGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(FSGScreenHandlers.ENCHANTMENT_GENERATOR_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
    @Override
    protected void createIngredientSlot(Inventory inventory){
        this.addSlot(new GeneratorEnchantedItemSlot(this, inventory, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isEnchantingItem(itemStack);
    }
    public boolean isEnchantingItem(ItemStack itemStack) {
        return EnchantmentGeneratorBlockEntity.enchantingItemUsable(itemStack);
    }
}
