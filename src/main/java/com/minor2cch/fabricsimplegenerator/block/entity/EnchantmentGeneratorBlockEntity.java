package com.minor2cch.fabricsimplegenerator.block.entity;

import com.minor2cch.fabricsimplegenerator.block.EnchantmentGeneratorBlock;
import com.minor2cch.fabricsimplegenerator.block.GeneratorTier;
import com.minor2cch.fabricsimplegenerator.config.FSGConfigLoader;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.screen.EnchantmentGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
    public void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.overrideGenerate = compoundTag.getLong("OverrideGenerate");
    }
    // 保存処理（ワールドセーブ時など）
    @Override
    public void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putLong("OverrideGenerate", this.overrideGenerate);
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
    protected @NotNull Component getDefaultName() {
        return Component.translatable(CONTAINER_NAME);
    }
    @Override
    public long getTickGenerate(){
        return overrideGenerate;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new EnchantmentGeneratorMenu(syncId, inventory, this, dataAccess);
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new EnchantmentGeneratorMenu(syncId, inv, this, dataAccess);
    }
    public static boolean enchantingItemUsable(ItemStack stack){
        return EnchantmentHelper.hasAnyEnchantments(stack) || stack.is(Items.ENCHANTED_BOOK);
    }
    @Override
    protected ItemStack consumeStack(Level level, BlockPos pos, ItemStack stack){
        overrideGenerate = getEnchantmentGeneratorProvider(stack).generateTick();
        this.setChanged();
        return removeEnchantment(stack);
    }
    private ItemStack removeEnchantment(ItemStack stack) {
        ItemEnchantments itemEnchantments = EnchantmentHelper.updateEnchantments(
                stack, components -> components.removeIf(enchantment -> (!enchantment.is(EnchantmentTags.CURSE) || FSGConfigLoader.getConfig().getCanCurseRemoval()))
        );
        if (stack.is(Items.ENCHANTED_BOOK) && itemEnchantments.isEmpty()) {
            stack = stack.transmuteCopy(Items.BOOK);
        }
        int i = 0;
        for (int j = 0; j < itemEnchantments.size(); j++) {
            i = AnvilMenu.calculateIncreasedRepairCost(i);
        }
        stack.set(DataComponents.REPAIR_COST, i);
        return stack;
    }
    private record EnchantmentGeneratorProvider(long generateTick, long generateTime){

    }
    private EnchantmentGeneratorProvider getEnchantmentGeneratorProvider(ItemStack stack){
        AtomicInteger count = new AtomicInteger(0);
        AtomicLong gen = new AtomicLong(0);
        AtomicLong total = new AtomicLong(0);
        EnchantmentHelper.updateEnchantments(
                stack, mutable -> mutable.keySet().iterator().forEachRemaining(
                        (enchantment) -> {
                            int lv = mutable.getLevel(enchantment);
                            if(lv > 0 && (!enchantment.is(EnchantmentTags.CURSE) || FSGConfigLoader.getConfig().getCanCurseRemoval())){
                                int anvilCost = enchantment.value().getAnvilCost();
                                double genMul = 1 + Math.max((anvilCost - 1), 0) * 0.5;
                                int timeMul = (int)Math.pow(2, lv-1);
                                int treasureBonus = enchantment.is(EnchantmentTags.TREASURE) ? 2 : 1;
                                count.addAndGet(1);
                                gen.addAndGet((long)(TICK_GENERATE * genMul * treasureBonus));
                                total.addAndGet(1000L * timeMul);
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
