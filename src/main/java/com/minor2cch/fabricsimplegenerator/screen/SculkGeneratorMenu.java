package com.minor2cch.fabricsimplegenerator.screen;

import com.minor2cch.fabricsimplegenerator.block.entity.SculkGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGMenus;
import com.minor2cch.fabricsimplegenerator.screen.slot.GeneratorSculkSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;

public class SculkGeneratorMenu extends AbstractGeneratorMenu {
    public SculkGeneratorMenu(int syncId, Inventory inventory) {
        super(FSGMenus.SCULK_GENERATOR_SCREEN_HANDLER, syncId, inventory);
    }
    public SculkGeneratorMenu(int syncId, Inventory inventory, Container container, ContainerData data) {
        super(FSGMenus.SCULK_GENERATOR_SCREEN_HANDLER, syncId, inventory, container, data);
    }
    @Override
    protected void createIngredientSlot(Container container){
        this.addSlot(new GeneratorSculkSlot(this, container, 0, 80, 35));
    }
    @Override
    public boolean isIngredientStack(ItemStack itemStack) {
        return isSculk(itemStack);
    }
    public boolean isSculk(ItemStack itemStack) {
        return SculkGeneratorBlockEntity.sculkUsable(itemStack);
    }
}
