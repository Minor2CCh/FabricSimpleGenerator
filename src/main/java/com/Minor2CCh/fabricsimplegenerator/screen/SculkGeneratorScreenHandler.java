package com.Minor2CCh.fabricsimplegenerator.screen;

import com.Minor2CCh.fabricsimplegenerator.block.entity.SculkGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGScreenHandlers;
import com.Minor2CCh.fabricsimplegenerator.screen.slot.GeneratorSculkSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;

public class SculkGeneratorScreenHandler extends AbstractGeneratorScreenHandler{
    public SculkGeneratorScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(FSGScreenHandlers.SCULK_GENERATOR_SCREEN_HANDLER, syncId, playerInventory);
    }
    public SculkGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(FSGScreenHandlers.SCULK_GENERATOR_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
    @Override
    protected void createIngredientSlot(Inventory inventory){
        this.addSlot(new GeneratorSculkSlot(this, inventory, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isSculk(itemStack);
    }
    public boolean isSculk(ItemStack itemStack) {
        return SculkGeneratorBlockEntity.sculkUsable(itemStack);
    }
}
