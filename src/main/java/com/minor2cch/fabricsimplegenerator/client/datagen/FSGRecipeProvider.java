package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.registry.FSGItemTags;
import com.minor2cch.fabricsimplegenerator.registry.FSGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

public class FSGRecipeProvider extends FabricRecipeProvider {
    public FSGRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new RecipeProvider(registries, exporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, FSGItems.IRON_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', ConventionalItemTags.IRON_INGOTS)
                        .define('$', FSGItems.COPPER_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.COPPER_GENERATOR), has(FSGItems.COPPER_GENERATOR))
                        .save(exporter);

                shaped(RecipeCategory.MISC, FSGItems.COPPER_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', ConventionalItemTags.COPPER_INGOTS)
                        .define('$', Items.FURNACE)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(Items.COPPER_INGOT), has(ConventionalItemTags.COPPER_INGOTS))
                        .save(exporter);
                shaped(RecipeCategory.MISC, FSGItems.GOLD_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', ConventionalItemTags.GOLD_INGOTS)
                        .define('$', FSGItems.IRON_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.IRON_GENERATOR), has(FSGItems.IRON_GENERATOR))
                        .save(exporter);
                shaped(RecipeCategory.MISC, FSGItems.EMERALD_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', ConventionalItemTags.EMERALD_GEMS)
                        .define('$', FSGItems.GOLD_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.GOLD_GENERATOR), has(FSGItems.GOLD_GENERATOR))
                        .save(exporter);
                shaped(RecipeCategory.MISC, FSGItems.DIAMOND_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', ConventionalItemTags.DIAMOND_GEMS)
                        .define('$', FSGItems.EMERALD_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.EMERALD_GENERATOR), has(FSGItems.EMERALD_GENERATOR))
                        .save(exporter);

                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(FSGItems.DIAMOND_GENERATOR),
                                Ingredient.of(Items.NETHERITE_INGOT),
                                RecipeCategory.MISC,
                                FSGItems.NETHERITE_GENERATOR
                        ).unlocks(getHasName(FSGItems.DIAMOND_GENERATOR), has(FSGItems.DIAMOND_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("netherite_generator")));
                shaped(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("#A#")
                        .define('#', Items.SCULK_CATALYST)
                        .define('$', FSGItems.IRON_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(Items.SCULK_CATALYST), has(Items.SCULK_CATALYST))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("sculk_generator")));
                shaped(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR)
                        .pattern("###")
                        .pattern("R$R")
                        .pattern("RAR")
                        .define('#', FSGItemTags.AZALEAS)
                        .define('R', ItemTags.LEAVES)
                        .define('$', FSGItems.COPPER_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.COPPER_GENERATOR), has(FSGItems.COPPER_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("biomass_generator")));
                shaped(RecipeCategory.MISC, FSGItems.ENCHANTMENT_GENERATOR)
                        .pattern("L#L")
                        .pattern("R$R")
                        .pattern("LAL")
                        .define('#', Items.ENCHANTING_TABLE)
                        .define('R', Items.ENCHANTED_BOOK)
                        .define('L', ConventionalItemTags.STORAGE_BLOCKS_LAPIS)
                        .define('$', FSGItems.IRON_GENERATOR)
                        .define('A', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                        .unlockedBy(getHasName(FSGItems.IRON_GENERATOR), has(FSGItems.IRON_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("enchantment_generator")));
                shaped(RecipeCategory.MISC, FSGItems.IRON_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.IRON_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.IRON_GENERATOR), has(FSGItems.IRON_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("iron_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.COPPER_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.COPPER_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.COPPER_GENERATOR), has(FSGItems.COPPER_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("copper_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.GOLD_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.GOLD_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.GOLD_GENERATOR), has(FSGItems.GOLD_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("gold_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.EMERALD_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.EMERALD_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.EMERALD_GENERATOR), has(FSGItems.EMERALD_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("emerald_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.DIAMOND_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.DIAMOND_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.DIAMOND_GENERATOR), has(FSGItems.DIAMOND_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("diamond_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.NETHERITE_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.NETHERITE_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.NETHERITE_GENERATOR), has(FSGItems.NETHERITE_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("netherite_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.SCULK_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.SCULK_GENERATOR), has(FSGItems.SCULK_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("sculk_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.BIOMASS_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.BIOMASS_GENERATOR), has(FSGItems.BIOMASS_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("biomass_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.ENCHANTMENT_GENERATOR_8X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.ENCHANTMENT_GENERATOR)
                        .define('$', Items.END_CRYSTAL)
                        .unlockedBy(getHasName(FSGItems.ENCHANTMENT_GENERATOR), has(FSGItems.ENCHANTMENT_GENERATOR))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("enchantment_generator_8x")));
                shaped(RecipeCategory.MISC, FSGItems.NETHERITE_GENERATOR_64X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.NETHERITE_GENERATOR_8X)
                        .define('$', Items.NETHER_STAR)
                        .unlockedBy(getHasName(FSGItems.NETHERITE_GENERATOR_8X), has(FSGItems.NETHERITE_GENERATOR_8X))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("netherite_generator_64x")));
                shaped(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR_64X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.SCULK_GENERATOR_8X)
                        .define('$', Items.NETHER_STAR)
                        .unlockedBy(getHasName(FSGItems.SCULK_GENERATOR_8X), has(FSGItems.SCULK_GENERATOR_8X))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("sculk_generator_64x")));
                shaped(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR_64X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.BIOMASS_GENERATOR_8X)
                        .define('$', Items.NETHER_STAR)
                        .unlockedBy(getHasName(FSGItems.BIOMASS_GENERATOR_8X), has(FSGItems.BIOMASS_GENERATOR_8X))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("biomass_generator_64x")));
                shaped(RecipeCategory.MISC, FSGItems.ENCHANTMENT_GENERATOR_64X)
                        .pattern("###")
                        .pattern("#$#")
                        .pattern("###")
                        .define('#', FSGItems.ENCHANTMENT_GENERATOR_8X)
                        .define('$', Items.NETHER_STAR)
                        .unlockedBy(getHasName(FSGItems.ENCHANTMENT_GENERATOR_8X), has(FSGItems.ENCHANTMENT_GENERATOR_8X))
                        .save(exporter, getRecipeKey(FabricSimpleGenerator.of("enchantment_generator_64x")));

            }

        };


    }

    @Override
    public String getName() {
        return "Recipes / " + getClass().getName();
    }

    private static ResourceKey<Recipe<?>> getRecipeKey(Identifier id){
        return ResourceKey.create(Registries.RECIPE, id);
    }
}
