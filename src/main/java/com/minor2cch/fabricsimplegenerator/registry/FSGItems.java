package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public final class FSGItems {
    private FSGItems() {}
    public static final Item IRON_GENERATOR = register("iron_generator", (p) -> new BlockItem(FSGBlocks.IRON_GENERATOR, p), new Item.Properties());
    public static final Item COPPER_GENERATOR = register("copper_generator", (p) -> new BlockItem(FSGBlocks.COPPER_GENERATOR, p), new Item.Properties());
    public static final Item GOLD_GENERATOR = register("gold_generator", (p) -> new BlockItem(FSGBlocks.GOLD_GENERATOR, p), new Item.Properties());
    public static final Item DIAMOND_GENERATOR = register("diamond_generator", (p) -> new BlockItem(FSGBlocks.DIAMOND_GENERATOR, p), new Item.Properties());
    public static final Item EMERALD_GENERATOR = register("emerald_generator", (p) -> new BlockItem(FSGBlocks.EMERALD_GENERATOR, p), new Item.Properties());
    public static final Item NETHERITE_GENERATOR = register("netherite_generator", (p) -> new BlockItem(FSGBlocks.NETHERITE_GENERATOR, p), new Item.Properties());
    public static final Item SCULK_GENERATOR = register("sculk_generator", (p) -> new BlockItem(FSGBlocks.SCULK_GENERATOR, p), new Item.Properties());
    public static final Item BIOMASS_GENERATOR = register("biomass_generator", (p) -> new BlockItem(FSGBlocks.BIOMASS_GENERATOR, p), new Item.Properties());
    public static final Item ENCHANTMENT_GENERATOR = register("enchantment_generator", (p) -> new BlockItem(FSGBlocks.ENCHANTMENT_GENERATOR, p), new Item.Properties());
    public static final Item IRON_GENERATOR_8X = register("iron_generator_8x", (p) -> new BlockItem(FSGBlocks.IRON_GENERATOR_8X, p), new Item.Properties());
    public static final Item COPPER_GENERATOR_8X = register("copper_generator_8x", (p) -> new BlockItem(FSGBlocks.COPPER_GENERATOR_8X, p), new Item.Properties());
    public static final Item GOLD_GENERATOR_8X = register("gold_generator_8x", (p) -> new BlockItem(FSGBlocks.GOLD_GENERATOR_8X, p), new Item.Properties());
    public static final Item EMERALD_GENERATOR_8X = register("emerald_generator_8x", (p) -> new BlockItem(FSGBlocks.EMERALD_GENERATOR_8X, p), new Item.Properties());
    public static final Item DIAMOND_GENERATOR_8X = register("diamond_generator_8x", (p) -> new BlockItem(FSGBlocks.DIAMOND_GENERATOR_8X, p), new Item.Properties());
    public static final Item NETHERITE_GENERATOR_8X = register("netherite_generator_8x", (p) -> new BlockItem(FSGBlocks.NETHERITE_GENERATOR_8X, p), new Item.Properties());
    public static final Item SCULK_GENERATOR_8X = register("sculk_generator_8x", (p) -> new BlockItem(FSGBlocks.SCULK_GENERATOR_8X, p), new Item.Properties());
    public static final Item BIOMASS_GENERATOR_8X = register("biomass_generator_8x", (p) -> new BlockItem(FSGBlocks.BIOMASS_GENERATOR_8X, p), new Item.Properties());
    public static final Item ENCHANTMENT_GENERATOR_8X = register("enchantment_generator_8x", (p) -> new BlockItem(FSGBlocks.ENCHANTMENT_GENERATOR_8X, p), new Item.Properties());
    public static final Item NETHERITE_GENERATOR_64X = register("netherite_generator_64x", (p) -> new BlockItem(FSGBlocks.NETHERITE_GENERATOR_64X, p), new Item.Properties());
    public static final Item SCULK_GENERATOR_64X = register("sculk_generator_64x", (p) -> new BlockItem(FSGBlocks.SCULK_GENERATOR_64X, p), new Item.Properties());
    public static final Item BIOMASS_GENERATOR_64X = register("biomass_generator_64x", (p) -> new BlockItem(FSGBlocks.BIOMASS_GENERATOR_64X, p), new Item.Properties());
    public static final Item ENCHANTMENT_GENERATOR_64X = register("enchantment_generator_64x", (p) -> new BlockItem(FSGBlocks.ENCHANTMENT_GENERATOR_64X, p), new Item.Properties());
    public static Item register(String id, Function<Item.Properties, Item> item, Item.Properties properties) {
        Identifier itemID = FabricSimpleGenerator.of(id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, itemID);
        return Registry.register(BuiltInRegistries.ITEM, itemID, item.apply(properties.setId(itemKey)));
    }
    public static void init(){


    }
}
