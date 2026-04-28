package com.minor2cch.fabricsimplegenerator.screen;

import com.minor2cch.fabricsimplegenerator.block.entity.FuelGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGMenus;
import com.minor2cch.fabricsimplegenerator.screen.slot.GeneratorFuelSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;

public class FuelGeneratorMenu extends AbstractGeneratorMenu {
    public FuelGeneratorMenu(int syncId, Inventory inventory) {
        super(FSGMenus.FUEL_GENERATOR_SCREEN_HANDLER, syncId, inventory);
    }
    public FuelGeneratorMenu(int syncId, Inventory inventory, Container container, ContainerData data) {
        super(FSGMenus.FUEL_GENERATOR_SCREEN_HANDLER, syncId, inventory, container, data);
    }
    @Override
    protected void createIngredientSlot(Container container){
        this.addSlot(new GeneratorFuelSlot(this, container, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isFuel(itemStack);
    }
    public boolean isFuel(final ItemStack itemStack) {
        return FuelGeneratorBlockEntity.fuelUsable(level, itemStack);
    }
}
