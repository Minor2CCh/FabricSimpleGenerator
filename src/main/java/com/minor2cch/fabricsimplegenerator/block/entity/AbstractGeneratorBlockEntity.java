package com.minor2cch.fabricsimplegenerator.block.entity;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.block.AbstractGeneratorBlock;
import com.minor2cch.fabricsimplegenerator.block.GeneratorTier;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;


public abstract class AbstractGeneratorBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    protected final long CAPACITY;
    protected final long MAX_INSERT;
    protected final long MAX_EXTRACT;
    protected final long TICK_GENERATE;
    public final SimpleEnergyStorage energyStorage;
    public static final int DEC_BURN_TIME = 4;
    private int burnTime = 0;
    private int startBurnTime = 0;
    public static final int PROPERTY_SIZE = 14;
    public static final int BURN_TIME_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int CAPACITY_INDEX = 5;
    public static final int START_BURN_TIME_INDEX = 9;
    public static final int TICK_GENERATE_INDEX = 10;

    public static final String CONTAINER_NAME = "container." + FabricSimpleGenerator.of("unknown_generator").toString().replace(':', '.');

    protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    public static final int BATTERY_SLOT_INDEX = 1;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case BURN_TIME_INDEX -> AbstractGeneratorBlockEntity.this.burnTime;
                case AMOUNT_INDEX -> (int) (energyStorage.amount & 0xFFFFL);
                case (AMOUNT_INDEX+1) -> (int) (((energyStorage.amount) >> 16) & (0xFFFFL));
                case (AMOUNT_INDEX+2) -> (int) (((energyStorage.amount) >> 32) & (0xFFFFL));
                case (AMOUNT_INDEX+3) -> (int) (((energyStorage.amount) >> 48) & (0xFFFFL));
                case (CAPACITY_INDEX) -> (int) (CAPACITY & 0xFFFFL);
                case (CAPACITY_INDEX+1) -> (int) ((CAPACITY >> 16) & (0xFFFFL));
                case (CAPACITY_INDEX+2) -> (int) ((CAPACITY >> 32) & (0xFFFFL));
                case (CAPACITY_INDEX+3) -> (int) ((CAPACITY >> 48) & (0xFFFFL));
                case START_BURN_TIME_INDEX -> Math.min(32767, startBurnTime);
                case (TICK_GENERATE_INDEX) -> (int) (getTickGenerate() & 0xFFFFL);
                case (TICK_GENERATE_INDEX+1) -> (int) ((getTickGenerate() >> 16) & (0xFFFFL));
                case (TICK_GENERATE_INDEX+2) -> (int) ((getTickGenerate() >> 32) & (0xFFFFL));
                case (TICK_GENERATE_INDEX+3) -> (int) ((getTickGenerate() >> 48) & (0xFFFFL));
                default -> 0;
            };
        }


        @Override
        public void set(int index, int value) {
            switch (index) {
                case BURN_TIME_INDEX:
                    AbstractGeneratorBlockEntity.this.burnTime = value;
                    break;
                case AMOUNT_INDEX:
                    energyStorage.amount &= (0xFFFFFFFFFFFF0000L);
                    energyStorage.amount |= (value&0xFFFF);
                    break;
                case AMOUNT_INDEX+1:
                    energyStorage.amount &= (0xFFFFFFFF0000FFFFL);
                    energyStorage.amount |= (((long)value<<16)&(0xFFFFL<<16));
                    break;
                case AMOUNT_INDEX+2:
                    energyStorage.amount &= (0xFFFF0000FFFFFFFFL);
                    energyStorage.amount |= (((long)value<<32)&(0xFFFFL<<32));
                    break;
                case AMOUNT_INDEX+3:
                    energyStorage.amount &= (0xFFFFFFFFFFFFL);
                    energyStorage.amount |= (((long)value<<48)&(0xFFFFL<<48));
                    break;
                case START_BURN_TIME_INDEX:
                    startBurnTime = Math.min(32767, value);
                    break;
            }
        }

        @Override
        public int getCount() {
            return PROPERTY_SIZE;
        }
    };
    public AbstractGeneratorBlockEntity(BlockEntityType<?> type, @NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.CAPACITY = gTier.CAPACITY * gMul;
        this.MAX_INSERT = gTier.MAX_INSERT * gMul;
        this.MAX_EXTRACT = gTier.MAX_EXTRACT * gMul;
        this.TICK_GENERATE = gTier.TICK_GENERATE * gMul;
        this.energyStorage =
                new SimpleEnergyStorage(this.CAPACITY, 0, this.MAX_EXTRACT) {
                    @Override
                    protected void onFinalCommit() {
                        setChanged();
                    }
                };
    }
    @Override
    public void loadAdditional(final @NonNull ValueInput input) {
        super.loadAdditional(input);
        this.energyStorage.amount = input.getLongOr("readCapacity", 0);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        this.burnTime = input.getIntOr("BurnTime", 0);
        this.startBurnTime = input.getIntOr("StartBurnTime", 0);
        ContainerHelper.loadAllItems(input, this.inventory);


    }
    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.inventory;
    }
    @Override
    protected void setItems(@NonNull NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    // 保存処理（ワールドセーブ時など）
    @Override
    public void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putLong("readCapacity", this.energyStorage.amount);
        output.putInt("BurnTime", this.burnTime);
        output.putInt("StartBurnTime", this.startBurnTime);
        ContainerHelper.saveAllItems(output, this.inventory);
    }
    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider provider) {
        return saveWithoutMetadata(provider); // クライアントに初期データ送信
    }
    public void setAmount(long value) {
        this.energyStorage.amount = value;
        setChanged(); // 変更を保存対象にする
    }

    public long getAmount() {
        return this.energyStorage.amount;
    }
    public long getCapacity() {
        return this.energyStorage.getCapacity();
    }
    public void setBurnTime(int value) {
        this.burnTime = value;
        setChanged(); // 変更を保存対象にする
    }
    public void setStartBurnTime(int value) {
        this.startBurnTime = value;
        setChanged(); // 変更を保存対象にする
    }
    public void decBurnTime(int value) {
        this.burnTime -= value;
        if(this.burnTime < 0){
            this.burnTime = 0;
        }
        setChanged(); // 変更を保存対象にする
    }

    public int getBurnTime() {
        return this.burnTime;
    }
    @Override
    public int getContainerSize() {
        return 2;
    }
    @Override
    public boolean canPlaceItemThroughFace(int slot, @NonNull ItemStack stack, @Nullable Direction dir) {
        return slot != BATTERY_SLOT_INDEX && ingredientUsable(stack);
    }
    @Override
    public boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction dir){
        if(slot == BATTERY_SLOT_INDEX){
            return false;
        }
        return (!stack.isEmpty() && !ingredientUsable(stack));
    }
    @Override
    public int @NotNull [] getSlotsForFace(@NonNull Direction side){
        return new int[] {0};
    }

    public abstract boolean ingredientUsable(ItemStack itemStack);
    public abstract int getIngredientTime(ItemStack itemStack);

    public static void tick(Level level, BlockPos pos, BlockState state, AbstractGeneratorBlockEntity be) {
        be.tick(level, pos, state);
    }

    protected void tick(Level level, BlockPos pos, BlockState state){
        if (!level.isClientSide()) {
            if(state.getBlock() instanceof AbstractGeneratorBlock){
                if(getBurnTime() > 0){
                    long amount = getAmount();
                    if(amount != getCapacity()){
                        if(amount + getTickGenerate() <= getCapacity() || getTickGenerate() > getCapacity()){
                            long gen = getTickGenerate();
                            setAmount(Math.min(amount+gen, getCapacity()));
                            decBurnTime(Math.min(getBurnTime(), DEC_BURN_TIME));

                        }
                    }
                }
                if(getBurnTime() <= 0){
                    ItemStack item = inventory.getFirst();
                    int fuelTime = getIngredientTime(item);
                    if(ingredientUsable(item) && fuelTime > 0 && item.getCount() > 0){
                        setStartBurnTime(fuelTime);
                        setBurnTime(fuelTime);
                        level.setBlockAndUpdate(pos, state.setValue(AbstractGeneratorBlock.LIT, true));
                        ItemStack usedStack = consumeStack(level, pos, item);
                        inventory.set(0, usedStack);
                        this.setChanged();
                    }
                }
                if(state.getValue(AbstractGeneratorBlock.LIT) && getBurnTime() <= 0){
                    level.setBlockAndUpdate(pos, state.setValue(AbstractGeneratorBlock.LIT, false));
                }
                sendEnergy(level, pos, state);

                ItemStack batteryStack = inventory.get(BATTERY_SLOT_INDEX);
                if(!batteryStack.isEmpty() && EnergyStorageUtil.isEnergyStorage(batteryStack)){
                    sendStackEnergy(batteryStack);
                }
            }


        }
    }
    public long getTickGenerate(){
        return TICK_GENERATE;
    }
    protected ItemStack consumeStack(Level level, BlockPos pos, ItemStack stack){
        if(stack.getCraftingRemainder() != null){
            Item remainItem = stack.getCraftingRemainder().item().value();
            stack.shrink(1);
            if(stack.getCount() > 0){
                if(!level.isClientSide()){
                    level.addFreshEntity(new ItemEntity(level, pos.getX()+0.5, pos.getY()+1,pos.getZ()+0.5, remainItem.getDefaultInstance()));
                }
                return stack;
            }else{
                return remainItem.getDefaultInstance();
            }

        }else{
            stack.shrink(1);
            return stack;
        }

    }

    protected void sendEnergy(Level level, BlockPos pos, BlockState state){
        Direction outputSide = state.getValue(AbstractGeneratorBlock.FACING);
        EnergyStorage sendStorage = this.energyStorage;//EnergyStorage.SIDED.find(level, pos, outputSide);

        if (sendStorage == null || sendStorage.getAmount() <= 0) return;


        BlockPos neighborPos = pos.relative(outputSide);
        EnergyStorage receivedStorage = EnergyStorage.SIDED.find(level, neighborPos, outputSide.getOpposite());
        if (receivedStorage == null || !receivedStorage.supportsInsertion()) return;


        try (Transaction tx = Transaction.openOuter()) {
            long maxTransfer = Math.min(sendStorage.getAmount(), this.MAX_EXTRACT);
            long inserted = receivedStorage.insert(maxTransfer, tx);
            if (inserted > 0) {
                long extracted = sendStorage.extract(inserted, tx);
                if (extracted == inserted) {
                    tx.commit();
                    this.setChanged();
                }
            }
        }
    }
    protected void sendStackEnergy(ItemStack stack){
        if(EnergyStorageUtil.isEnergyStorage(stack)){
            AbstractGeneratorBlockEntity be = this;
            SingleStackStorage slotStorage = new SingleStackStorage() {
                @Override
                protected @NonNull ItemStack getStack() {
                    // 直接参照することで write-back が可能になる
                    return inventory.get(BATTERY_SLOT_INDEX);
                }

                @Override
                protected void setStack(@NonNull ItemStack stack) {
                    inventory.set(BATTERY_SLOT_INDEX, stack);
                }

                @Override
                protected void onFinalCommit() {
                    // トランザクション確定後に setChanged する（保存のトリガ）
                    be.setChanged();
                }
            };

            ContainerItemContext ctx = ContainerItemContext.ofSingleSlot(slotStorage);

            var itemEnergy = ctx.find(EnergyStorage.ITEM);
            if (itemEnergy == null) return;
            try (Transaction tx = Transaction.openOuter()) {
                long moved = EnergyStorageUtil.move(this.energyStorage, itemEnergy, this.MAX_EXTRACT, tx);
                if (moved > 0) {
                    tx.commit();
                }
            }
        }
    }
    public static String getTranslationKey(BlockEntityType<?> blockEntityType){
        BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntityType);
        Identifier key = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntityType);
        if(key != null){
            return "container." + key.toString().replace(':', '.');

        }else{
            return CONTAINER_NAME;
        }
    }

}
