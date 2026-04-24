package com.minor2cch.fabricsimplegenerator.screen;

import com.minor2cch.fabricsimplegenerator.block.entity.BiomassGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGMenus;
import com.minor2cch.fabricsimplegenerator.screen.slot.GeneratorBioSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;


public class BiomassGeneratorMenu extends AbstractGeneratorMenu {
    public BiomassGeneratorMenu(int syncId, Inventory inventory) {
        super(FSGMenus.BIOMASS_GENERATOR_SCREEN_HANDLER, syncId, inventory);
    }
    public BiomassGeneratorMenu(int syncId, Inventory Inventory, Container container, ContainerData data) {
        super(FSGMenus.BIOMASS_GENERATOR_SCREEN_HANDLER, syncId, Inventory, container, data);
    }
    @Override
    protected void createIngredientSlot(Container container){
        this.addSlot(new GeneratorBioSlot(this, container, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isBio(itemStack);
    }
    public boolean isBio(ItemStack itemStack) {
        return BiomassGeneratorBlockEntity.bioUsable(itemStack);
    }
}
