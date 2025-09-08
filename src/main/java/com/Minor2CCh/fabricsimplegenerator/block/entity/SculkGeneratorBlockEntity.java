package com.Minor2CCh.fabricsimplegenerator.block.entity;

import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import com.Minor2CCh.fabricsimplegenerator.block.SculkGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGItemTags;
import com.Minor2CCh.fabricsimplegenerator.screen.SculkGeneratorScreenHandler;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SculkGeneratorBlockEntity extends AbstractGeneratorBlockEntity{
    private static volatile Map<Item, Integer> sculkTimes;
    public SculkGeneratorBlockEntity(@Nullable GeneratorTier gTier, int gMul, BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.SCULK_GENERATOR_BLOCK_ENTITY, pos, state);
        if(gTier != null){
            setGeneratorSetting(gTier, gMul);
        }
    }
    public SculkGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(FSGBlockEntityTypes.SCULK_GENERATOR_BLOCK_ENTITY, pos, state);
        GeneratorTier tempTier = SculkGeneratorBlock.getTier(state.getBlock());
        int tempMul = SculkGeneratorBlock.getMul(state.getBlock());
        if(tempTier != null){
            setGeneratorSetting(tempTier, tempMul);
        }
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new SculkGeneratorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SculkGeneratorScreenHandler(syncId, inv, this, propertyDelegate);
    }
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.fsg.generator.sculk");
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

    public static void tick(World world, BlockPos pos, BlockState state, SculkGeneratorBlockEntity be) {
        be.tick(world, pos, state);
    }
    private static void addSculk(Map<Item, Integer> sculkTimes, ItemConvertible item, int sculkTime) {
        Item item2 = item.asItem();
        sculkTimes.put(item2, sculkTime);
    }
    private static void addSculk(Map<Item, Integer> sculkTimes, TagKey<Item> tag, int sculkTime) {
        for (RegistryEntry<Item> registryEntry : Registries.ITEM.iterateEntries(tag)) {
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
