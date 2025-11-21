package com.Minor2CCh.fabricsimplegenerator.block.entity;

import com.Minor2CCh.fabricsimplegenerator.block.EnchantmentGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import com.Minor2CCh.fabricsimplegenerator.config.FSGConfigLoader;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.Minor2CCh.fabricsimplegenerator.screen.EnchantmentGeneratorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class EnchantmentGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final String CONTAINER_NAME = getTranslationKey(FSGBlockEntityTypes.ENCHANTMENT_GENERATOR_BLOCK_ENTITY);
    private long overrideGenerate;
    public EnchantmentGeneratorBlockEntity(@NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.ENCHANTMENT_GENERATOR_BLOCK_ENTITY, gTier, gMul, pos, state);
    }

    public EnchantmentGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(EnchantmentGeneratorBlock.getTier(state.getBlock()), EnchantmentGeneratorBlock.getMul(state.getBlock()), pos, state);
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.overrideGenerate = nbt.getLong("OverrideGenerate");
    }
    // 保存処理（ワールドセーブ時など）
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putLong("OverrideGenerate", this.overrideGenerate);
    }

    @Override
    public boolean ingredientUsable(ItemStack itemStack) {
        return enchantingItemUsable(itemStack);
    }

    @Override
    public int getIngredientTime(ItemStack itemStack) {
        if(!ingredientUsable(itemStack)){
            return 0;
        }
        return Math.toIntExact(getEnchantmentGeneratorProvider(itemStack).generateTime());//enchantingItemTime(stack.getItem());
        //return enchantingItemTime(itemStack);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable(CONTAINER_NAME);
    }
    @Override
    public long getTickGenerate(){
        return overrideGenerate;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new EnchantmentGeneratorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new EnchantmentGeneratorScreenHandler(syncId, inv, this, propertyDelegate);
    }
    public static boolean enchantingItemUsable(ItemStack stack){
        return EnchantmentHelper.hasEnchantments(stack) || stack.isOf(Items.ENCHANTED_BOOK);
    }
    @Override
    protected ItemStack consumeStack(World world, BlockPos pos, ItemStack stack){
        overrideGenerate = getEnchantmentGeneratorProvider(stack).generateTick();
        this.markDirty();
        return removeEnchantment(stack);
    }
    private ItemStack removeEnchantment(ItemStack stack) {
        ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.apply(
                stack, components -> components.remove(enchantment -> (!enchantment.isIn(EnchantmentTags.CURSE) || FSGConfigLoader.getConfig().canCurseRemoval))
        );
        if (stack.isOf(Items.ENCHANTED_BOOK) && itemEnchantmentsComponent.isEmpty()) {
            stack = stack.withItem(Items.BOOK);
        }
        int i = 0;
        for (int j = 0; j < itemEnchantmentsComponent.getSize(); j++) {
            i = AnvilScreenHandler.getNextCost(i);
        }
        stack.set(DataComponentTypes.REPAIR_COST, i);
        return stack;
    }
    private record EnchantmentGeneratorProvider(long generateTick, long generateTime){

    }
    private EnchantmentGeneratorProvider getEnchantmentGeneratorProvider(ItemStack stack){
        AtomicInteger count = new AtomicInteger(0);
        AtomicLong gen = new AtomicLong(0);
        AtomicLong total = new AtomicLong(0);
        EnchantmentHelper.apply(
                stack, components -> components.getEnchantments().iterator().forEachRemaining(
                        (enchantment) -> {
                            int lv = components.getLevel(enchantment);
                            if(lv > 0 && (!enchantment.isIn(EnchantmentTags.CURSE) || FSGConfigLoader.getConfig().canCurseRemoval)){
                                int anvilCost = enchantment.value().getAnvilCost();
                                double genMul = 1 + Math.max((anvilCost - 1), 0) * 0.5;
                                int timeMul = (int)Math.pow(2, lv-1);
                                int treasureBonus = enchantment.isIn(EnchantmentTags.TREASURE) ? 2 : 1;
                                count.addAndGet(1);
                                gen.addAndGet((long)(TICK_GENERATE * genMul * treasureBonus));
                                total.addAndGet(1000L * timeMul);
                                // System.out.println(enchantment.value());
                            }
                        }
                )
        );
        if(count.get() <= 0){
            return new EnchantmentGeneratorProvider(0, 0);
        }
        total.set(total.get() * stack.getCount());

        return new EnchantmentGeneratorProvider(gen.get() / count.get(), total.get());
    }
}
