package com.Minor2CCh.fabricsimplegenerator.screen;

import com.Minor2CCh.fabricsimplegenerator.block.entity.AbstractGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.screen.slot.BatterySlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorageUtil;

public abstract class AbstractGeneratorScreenHandler extends ScreenHandler {
    protected final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected AbstractGeneratorScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        this(type, syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(AbstractGeneratorBlockEntity.PROPERTY_SIZE));
    }
    public AbstractGeneratorScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        // some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);
        this.addProperties(this.propertyDelegate);

        // This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        // This will not render the background of the slots however, this is the Screens job
        createIngredientSlot(inventory);
        this.addSlot(new BatterySlot(this, inventory, 1, 134, 35));

        int m;
        int l;
        // The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
        /*
        InventoryMap
        0:IngredientInventory
        1:BatteryInventory
        2~28:MainHandInventory
        29~37:NormalInventory
        */

    }
    protected abstract void createIngredientSlot(Inventory inventory);
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 0 || slot == 1) {
                if (!this.insertItem(itemStack2, 2, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (isIngredientStack(itemStack2)) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isEnergySlot(itemStack2)) {
                if(!this.insertItem(itemStack2, 1, 2, false)){
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.insertItem(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38) {
                if (!this.insertItem(itemStack2, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }
    public abstract boolean isIngredientStack(ItemStack itemStack);
    public boolean isEnergySlot(ItemStack itemStack) {
        return EnergyStorageUtil.isEnergyStorage(itemStack);
    }
    @Override
    public boolean canUse(PlayerEntity player) {return this.inventory.canPlayerUse(player);
    }

    public boolean isBurning() {
        return this.propertyDelegate.get(AbstractGeneratorBlockEntity.BURN_TIME_INDEX) > 0;
    }
    public long amount(){
        return getLong(AbstractGeneratorBlockEntity.AMOUNT_INDEX);
    }
    public long getMaxCapacity(){
        return getLong(AbstractGeneratorBlockEntity.CAPACITY_INDEX);
    }
    public int getBurnTime() {
        return this.propertyDelegate.get(AbstractGeneratorBlockEntity.BURN_TIME_INDEX);
    }
    public int getStartBurnTime() {
        return this.propertyDelegate.get(AbstractGeneratorBlockEntity.START_BURN_TIME_INDEX);
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

    // propertyDelegateはshort型としてしか受け取れないため分割する必要がある
    public long getShort(int index){
        return (this.propertyDelegate.get(index) & 0xFFFFL);
    }
    public long getLong(int startIndex){
        return (getShort(startIndex)|(getShort(startIndex+1) << 16)|(getShort(startIndex+2) << 32)|(getShort(startIndex+3) << 48));
    }
}
