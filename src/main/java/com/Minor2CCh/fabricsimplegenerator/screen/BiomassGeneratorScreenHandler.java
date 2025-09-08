package com.Minor2CCh.fabricsimplegenerator.screen;

import com.Minor2CCh.fabricsimplegenerator.block.entity.BiomassGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.block.entity.FuelGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGScreenHandlers;
import com.Minor2CCh.fabricsimplegenerator.screen.slot.GeneratorBioSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;

public class BiomassGeneratorScreenHandler extends AbstractGeneratorScreenHandler{
    public BiomassGeneratorScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(FSGScreenHandlers.BIOMASS_GENERATOR_SCREEN_HANDLER, syncId, playerInventory);
    }
    public BiomassGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(FSGScreenHandlers.BIOMASS_GENERATOR_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
    @Override
    protected void createIngredientSlot(Inventory inventory){
        this.addSlot(new GeneratorBioSlot(this, inventory, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isBio(itemStack);
    }
    public boolean isBio(ItemStack itemStack) {
        return BiomassGeneratorBlockEntity.bioUsable(itemStack);
    }
}
