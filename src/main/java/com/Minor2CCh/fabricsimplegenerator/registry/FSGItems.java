package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FSGItems {
    public static final Item IRON_GENERATOR = register("iron_generator", new BlockItem(FSGBlocks.IRON_GENERATOR, new Item.Settings()));
    public static final Item COPPER_GENERATOR = register("copper_generator", new BlockItem(FSGBlocks.COPPER_GENERATOR, new Item.Settings()));
    public static final Item GOLD_GENERATOR = register("gold_generator", new BlockItem(FSGBlocks.GOLD_GENERATOR, new Item.Settings()));
    public static final Item DIAMOND_GENERATOR = register("diamond_generator", new BlockItem(FSGBlocks.DIAMOND_GENERATOR, new Item.Settings()));
    public static final Item EMERALD_GENERATOR = register("emerald_generator", new BlockItem(FSGBlocks.EMERALD_GENERATOR, new Item.Settings()));
    public static final Item NETHERITE_GENERATOR = register("netherite_generator", new BlockItem(FSGBlocks.NETHERITE_GENERATOR, new Item.Settings()));
    public static final Item SCULK_GENERATOR = register("sculk_generator", new BlockItem(FSGBlocks.SCULK_GENERATOR, new Item.Settings()));
    public static final Item BIOMASS_GENERATOR = register("biomass_generator", new BlockItem(FSGBlocks.BIOMASS_GENERATOR, new Item.Settings()));
    public static final Item IRON_GENERATOR_8X = register("iron_generator_8x", new BlockItem(FSGBlocks.IRON_GENERATOR_8X, new Item.Settings()));
    public static final Item COPPER_GENERATOR_8X = register("copper_generator_8x", new BlockItem(FSGBlocks.COPPER_GENERATOR_8X, new Item.Settings()));
    public static final Item GOLD_GENERATOR_8X = register("gold_generator_8x", new BlockItem(FSGBlocks.GOLD_GENERATOR_8X, new Item.Settings()));
    public static final Item EMERALD_GENERATOR_8X = register("emerald_generator_8x", new BlockItem(FSGBlocks.EMERALD_GENERATOR_8X, new Item.Settings()));
    public static final Item DIAMOND_GENERATOR_8X = register("diamond_generator_8x", new BlockItem(FSGBlocks.DIAMOND_GENERATOR_8X, new Item.Settings()));
    public static final Item NETHERITE_GENERATOR_8X = register("netherite_generator_8x", new BlockItem(FSGBlocks.NETHERITE_GENERATOR_8X, new Item.Settings()));
    public static final Item SCULK_GENERATOR_8X = register("sculk_generator_8x", new BlockItem(FSGBlocks.SCULK_GENERATOR_8X, new Item.Settings()));
    public static final Item BIOMASS_GENERATOR_8X = register("biomass_generator_8x", new BlockItem(FSGBlocks.BIOMASS_GENERATOR_8X, new Item.Settings()));
    public static final Item NETHERITE_GENERATOR_64X = register("netherite_generator_64x", new BlockItem(FSGBlocks.NETHERITE_GENERATOR_64X, new Item.Settings()));
    public static final Item SCULK_GENERATOR_64X = register("sculk_generator_64x", new BlockItem(FSGBlocks.SCULK_GENERATOR_64X, new Item.Settings()));
    public static final Item BIOMASS_GENERATOR_64X = register("biomass_generator_64x", new BlockItem(FSGBlocks.BIOMASS_GENERATOR_64X, new Item.Settings()));
    public static Item register(String id, Item item) {
        Identifier itemID = Identifier.of(FabricSimpleGenerator.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
    public static void init(){


    }
}
