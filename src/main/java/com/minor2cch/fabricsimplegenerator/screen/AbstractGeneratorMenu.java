package com.minor2cch.fabricsimplegenerator.screen;

import com.minor2cch.fabricsimplegenerator.block.entity.AbstractGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.screen.slot.BatterySlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import team.reborn.energy.api.EnergyStorageUtil;

public abstract class AbstractGeneratorMenu extends AbstractContainerMenu {
    protected final Container container;
    private final ContainerData data;
    protected final Level level;
    protected AbstractGeneratorMenu(@Nullable MenuType<?> type, int syncId, Inventory inventory) {
        this(type, syncId, inventory, new SimpleContainer(2), new SimpleContainerData(AbstractGeneratorBlockEntity.PROPERTY_SIZE));
    }
    public AbstractGeneratorMenu(@Nullable MenuType<?> type, int syncId, Inventory inventory, Container container, ContainerData data) {
        super(type, syncId);
        checkContainerSize(container, 2);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
        // some inventories do custom logic when a player opens it.
        container.startOpen(inventory.player);
        this.addDataSlots(this.data);

        // This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        // This will not render the background of the slots however, this is the Screens job
        createIngredientSlot(container);
        this.addSlot(new BatterySlot(this, container, 1, 134, 35));

        int m;
        int l;
        // The player container
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(inventory, m, 8 + m * 18, 142));
        }
        /*
        InventoryMap
        0:IngredientInventory
        1:BatteryInventory
        2~28:MainHandInventory
        29~37:NormalInventory
        */

    }
    protected abstract void createIngredientSlot(Container container);
    @Override
    public @NotNull ItemStack quickMoveStack(@NonNull Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 0 || slot == 1) {
                if (!this.moveItemStackTo(itemStack2, 2, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickCraft(itemStack2, itemStack);
            } else if (isIngredientStack(itemStack2)) {
                if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isEnergySlot(itemStack2)) {
                if(!this.moveItemStackTo(itemStack2, 1, 2, false)){
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.moveItemStackTo(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38) {
                if (!this.moveItemStackTo(itemStack2, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setByPlayer(ItemStack.EMPTY);
            } else {
                slot2.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
        }

        return itemStack;
    }
    public abstract boolean isIngredientStack(ItemStack itemStack);
    public boolean isEnergySlot(ItemStack itemStack) {
        return EnergyStorageUtil.isEnergyStorage(itemStack);
    }
    @Override
    public boolean stillValid(@NonNull Player player) {return this.container.stillValid(player);
    }

    public boolean isBurning() {
        return this.data.get(AbstractGeneratorBlockEntity.BURN_TIME_INDEX) > 0;
    }
    public long amount(){
        return getLong(AbstractGeneratorBlockEntity.AMOUNT_INDEX);
    }
    public long getMaxCapacity(){
        return getLong(AbstractGeneratorBlockEntity.CAPACITY_INDEX);
    }
    public int getBurnTime() {
        return this.data.get(AbstractGeneratorBlockEntity.BURN_TIME_INDEX);
    }
    public int getStartBurnTime() {
        return this.data.get(AbstractGeneratorBlockEntity.START_BURN_TIME_INDEX);
    }
    public double getFuelProgress() {
        return getStartBurnTime() == 0 ? 1.0: ((double) getBurnTime()) / getStartBurnTime();
    }
    public double getEnergyPercentage(){
        return getMaxCapacity() == 0 ? 0.0 : ((double) amount()) / getMaxCapacity();
    }
    public long getTickGenerate(){
        return getLong(AbstractGeneratorBlockEntity.TICK_GENERATE_INDEX);
    }

    // ContainerDataはshort型としてしか受け取れないため分割する必要がある
    public long getShort(int index){
        return (this.data.get(index) & 0xFFFFL);
    }
    public long getLong(int startIndex){
        return (getShort(startIndex)|(getShort(startIndex+1) << 16)|(getShort(startIndex+2) << 32)|(getShort(startIndex+3) << 48));
    }
}
