package com.Minor2CCh.fabricsimplegenerator.block.entity;

import com.Minor2CCh.fabricsimplegenerator.block.AbstractGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;


public abstract class AbstractGeneratorBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
    public long CAPACITY;
    public long MAX_INSERT;
    public long MAX_EXTRACT;
    public long TICK_GENERATE;
    public SimpleEnergyStorage energyStorage;
    private long readCapacity = 0; // 保存したい情報
    public static final int DEC_BURN_TIME = 4;
    private int burnTime = 0;
    private int startBurnTime = 0;
    public static final int PROPERTY_SIZE = 11;
    public static final int BURN_TIME_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int CAPACITY_INDEX = 5;
    public static final int START_BURN_TIME_INDEX = 9;

    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
    //@Nullable
    //FurnaceBlockEntity.createFuelTimeMap();
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
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
                case 10 -> (int) TICK_GENERATE;
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
        public int size() {
            return PROPERTY_SIZE;
        }
    };
    public AbstractGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    protected void setGeneratorSetting(GeneratorTier gTier, int gMul){
        this.CAPACITY = gTier.CAPACITY * gMul;
        this.MAX_INSERT = gTier.MAX_INSERT * gMul;
        this.MAX_EXTRACT = gTier.MAX_EXTRACT * gMul;
        this.TICK_GENERATE = gTier.TICK_GENERATE * gMul;
        this.energyStorage =
                new SimpleEnergyStorage(CAPACITY, 0, MAX_EXTRACT) {
                    @Override
                    protected void onFinalCommit() {
                        markDirty();
                    }
                };
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.readCapacity = nbt.getLong("readCapacity");
        this.energyStorage.amount = this.readCapacity;
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        this.burnTime = nbt.getInt("BurnTime");
        this.startBurnTime = nbt.getInt("StartBurnTime");
        Inventories.readNbt(nbt, this.inventory, registryLookup);
    }
    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    // 保存処理（ワールドセーブ時など）
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putLong("readCapacity", this.readCapacity);
        nbt.putInt("BurnTime", this.burnTime);
        nbt.putInt("StartBurnTime", this.startBurnTime);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup); // クライアントに初期データ送信
    }
    public void setCapacity(long value) {
        this.readCapacity = value;
        markDirty(); // 変更を保存対象にする
    }

    public long getCapacity() {
        return this.readCapacity;
    }
    public void setBurnTime(int value) {
        this.burnTime = value;
        markDirty(); // 変更を保存対象にする
    }
    public void setStartBurnTime(int value) {
        this.startBurnTime = value;
        markDirty(); // 変更を保存対象にする
    }
    public void decBurnTime(int value) {
        this.burnTime -= value;
        markDirty(); // 変更を保存対象にする
    }

    public int getBurnTime() {
        return this.burnTime;
    }
    @Override
    public int size() {
        return 1;
    }
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir){
        return (stack != null && !ingredientUsable(stack));
    }

    public int[] getAvailableSlots(Direction side){
        return new int[] {0};
    }

    public abstract boolean ingredientUsable(ItemStack itemStack);
    public abstract int getIngredientTime(ItemStack itemStack);

    public static void tick(World world, BlockPos pos, BlockState state, AbstractGeneratorBlockEntity be) {
        be.tick(world, pos, state);
    }
    public static int decisionGenerateMul(long defaultGen, long capacity, long maxCapacity, int burnTime) {
        for(int i = 1;i <= DEC_BURN_TIME&&i<=burnTime;i++){
            long gen = Math.round(((double)defaultGen) * i / DEC_BURN_TIME);
            if(capacity+gen >= maxCapacity){
                return i;
            }
        }
        return DEC_BURN_TIME;
    }
    protected void tick(World world, BlockPos pos, BlockState state){
        if (!world.isClient) {
            if(state.getBlock() instanceof AbstractGeneratorBlock){
                if(getBurnTime() > 0){
                    energyStorage.amount = getCapacity();
                    if(energyStorage.getAmount() != CAPACITY){
                        int tickMul = decisionGenerateMul(TICK_GENERATE, energyStorage.amount, CAPACITY, getBurnTime());
                        long gen = Math.round(((double)TICK_GENERATE) * tickMul / DEC_BURN_TIME);
                        energyStorage.amount = Math.min(energyStorage.amount+gen, CAPACITY);
                        setCapacity(energyStorage.getAmount());
                        decBurnTime(tickMul);
                    }
                }
                if(getBurnTime() <= 0){
                    ItemStack item = inventory.getFirst();
                    int fuelTime = getIngredientTime(item);
                    if(ingredientUsable(item) && fuelTime > 0 && item.getCount() > 0){
                        setStartBurnTime(fuelTime);
                        setBurnTime(fuelTime);
                        world.setBlockState(pos, state.with(AbstractGeneratorBlock.LIT, true));
                        ItemStack usedStack = consumeStack(world, pos, item);
                        inventory.set(0, usedStack);
                        this.markDirty();
                    }
                }
                if(state.get(AbstractGeneratorBlock.LIT) && getBurnTime() <= 0){
                    world.setBlockState(pos, state.with(AbstractGeneratorBlock.LIT, false));
                }
                sendEnergy(world, pos, state);
            }


        }
    }
    protected ItemStack consumeStack(World world, BlockPos pos, ItemStack stack){
        if(!stack.getRecipeRemainder().isEmpty()){
            Item remainItem = stack.getRecipeRemainder().getItem();
            stack.decrement(1);
            if(stack.getCount() > 0){
                if(!world.isClient()){
                    world.spawnEntity(new ItemEntity(world, pos.getX()+0.5, pos.getY()+1,pos.getZ()+0.5, remainItem.getDefaultStack()));
                }
                return stack;
            }else{
                return remainItem.getDefaultStack();
            }

        }else{
            stack.decrement(1);
            return stack;
        }

    }

    protected void sendEnergy(World world, BlockPos pos, BlockState state){
        Direction outputSide = state.get(AbstractGeneratorBlock.FACING);
        EnergyStorage sendStorage = this.energyStorage;//EnergyStorage.SIDED.find(world, pos, outputSide);

        if (sendStorage == null || sendStorage.getAmount() <= 0) return;


        BlockPos neighborPos = pos.offset(outputSide);
        EnergyStorage receivedStorage = EnergyStorage.SIDED.find(world, neighborPos, outputSide.getOpposite());
        if (receivedStorage == null || !receivedStorage.supportsInsertion()) return;


        try (Transaction tx = Transaction.openOuter()) {
            long maxTransfer = Math.min(sendStorage.getAmount(), this.MAX_EXTRACT);
            long inserted = receivedStorage.insert(maxTransfer, tx);
            if (inserted > 0) {
                long extracted = sendStorage.extract(inserted, tx);
                if (extracted == inserted) {
                    tx.commit();
                }
            }
        }
        this.setCapacity(this.energyStorage.getAmount());
        this.markDirty();
    }

}
