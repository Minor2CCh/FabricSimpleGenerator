package com.Minor2CCh.fabricsimplegenerator.screen;

import com.Minor2CCh.fabricsimplegenerator.block.entity.FuelGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGScreenHandlers;
import com.Minor2CCh.fabricsimplegenerator.screen.slot.GeneratorFuelSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;

public class FuelGeneratorScreenHandler extends AbstractGeneratorScreenHandler{
    public FuelGeneratorScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(FSGScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER, syncId, playerInventory);
    }
    public FuelGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(FSGScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
    @Override
    protected void createIngredientSlot(Inventory inventory){
        this.addSlot(new GeneratorFuelSlot(this, inventory, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isFuel(itemStack);
    }
    public boolean isFuel(ItemStack itemStack) {
        return FuelGeneratorBlockEntity.fuelUsable(itemStack);
    }
}
