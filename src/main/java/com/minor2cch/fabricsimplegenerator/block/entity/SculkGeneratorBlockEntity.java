package com.minor2cch.fabricsimplegenerator.block.entity;

import com.minor2cch.fabricsimplegenerator.block.GeneratorTier;
import com.minor2cch.fabricsimplegenerator.block.SculkGeneratorBlock;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.registry.FSGItemTags;
import com.minor2cch.fabricsimplegenerator.screen.SculkGeneratorMenu;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SculkGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    public static final String CONTAINER_NAME = getTranslationKey(FSGBlockEntityTypes.SCULK_GENERATOR_BLOCK_ENTITY);
    private static volatile Map<Item, Integer> sculkTimes;
    public SculkGeneratorBlockEntity(@NotNull GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.SCULK_GENERATOR_BLOCK_ENTITY, gTier, gMul, pos, state);
    }
    public SculkGeneratorBlockEntity(BlockPos pos, BlockState state) {
        this(SculkGeneratorBlock.getTier(state.getBlock()), SculkGeneratorBlock.getMul(state.getBlock()), pos, state);
    }
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new SculkGeneratorMenu(syncId, inventory, this, dataAccess);
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new SculkGeneratorMenu(syncId, inv, this, dataAccess);
    }
    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(CONTAINER_NAME);
    }

    public static boolean sculkUsable(ItemStack stack){
        return sculkUsable(stack.getItem());
    }
    public static boolean sculkUsable(Item item){
        Map<Item, Integer> sculkTimeMap = createSculkTimeMap();
        return sculkTimeMap.containsKey(item);
    }
    public static int sculkTime(ItemStack stack){
        return sculkTime(stack.getItem());
    }
    public static int sculkTime(Item item){
        Map<Item, Integer> sculkTimeMap = createSculkTimeMap();
        return sculkTimeMap.getOrDefault(item, 0);
    }
    public static Map<Item, Integer> createSculkTimeMap() {
        Map<Item, Integer> map = sculkTimes;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map2 = Maps.newLinkedHashMap();
            addSculk(map2, Items.SCULK_VEIN, 20);
            addSculk(map2, Items.SCULK, 100);
            addSculk(map2, Items.SCULK_SENSOR, 500);
            addSculk(map2, Items.SCULK_SHRIEKER, 1500);
            addSculk(map2, Items.SCULK_CATALYST, 1500);
            addSculk(map2, Items.ECHO_SHARD, 12000);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_BIT, 20);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_LOW, 100);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_MIDDLE, 500);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_HIGH, 1500);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_SPECIAL, 5000);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_EXTRA, 12000);
            addSculk(map2, FSGItemTags.SCULK_INGREDIENT_ULTIMATE, 24000);
            sculkTimes = map2;
            return map2;
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SculkGeneratorBlockEntity be) {
        be.tick(level, pos, state);
    }
    private static void addSculk(Map<Item, Integer> sculkTimes, ItemLike item, int sculkTime) {
        Item item2 = item.asItem();
        sculkTimes.put(item2, sculkTime);
    }
    private static void addSculk(Map<Item, Integer> sculkTimes, TagKey<Item> tag, int sculkTime) {
        for (Holder<Item> registryEntry : BuiltInRegistries.ITEM.getTagOrEmpty(tag)) {
            sculkTimes.put(registryEntry.value(), sculkTime);
        }
    }
    @Override
    public boolean ingredientUsable(ItemStack itemStack){
        return sculkUsable(itemStack);
    }
    @Override
    public int getIngredientTime(ItemStack itemStack){
        return sculkTime(itemStack);
    }

}
