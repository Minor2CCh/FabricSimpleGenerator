package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGItemTags;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class FSGRecipeProvider extends FabricRecipeProvider {
    public FSGRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.IRON_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.IRON_INGOT)
                .input('$', FSGItems.COPPER_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(FSGItems.COPPER_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "iron_generator"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.COPPER_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.COPPER_INGOT)
                .input('$', Items.FURNACE)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "copper_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.GOLD_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.GOLD_INGOT)
                .input('$', FSGItems.IRON_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(FSGItems.IRON_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "gold_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.EMERALD_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.EMERALD)
                .input('$', FSGItems.GOLD_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(FSGItems.GOLD_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "emerald_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.DIAMOND_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.DIAMOND)
                .input('$', FSGItems.EMERALD_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(FSGItems.EMERALD_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "diamond_generator"));
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItems(FSGItems.DIAMOND_GENERATOR),
                        Ingredient.ofItems(Items.NETHERITE_INGOT),
                        RecipeCategory.MISC,
                        FSGItems.NETHERITE_GENERATOR
                ).criterion("has_item", conditionsFromItem(FSGItems.DIAMOND_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "netherite_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR)
                .pattern("###")
                .pattern("#$#")
                .pattern("#A#")
                .input('#', Items.SCULK_CATALYST)
                .input('$', FSGItems.IRON_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(Items.SCULK_CATALYST))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "sculk_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR)
                .pattern("###")
                .pattern("R$R")
                .pattern("RAR")
                .input('#', FSGItemTags.AZALEAS)
                .input('R', ItemTags.LEAVES)
                .input('$', FSGItems.COPPER_GENERATOR)
                .input('A', Items.REDSTONE_BLOCK)
                .criterion("has_item", conditionsFromItem(FSGItems.COPPER_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "biomass_generator"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.IRON_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.IRON_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.IRON_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "iron_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.COPPER_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.COPPER_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.COPPER_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "copper_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.GOLD_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.GOLD_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.GOLD_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "gold_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.EMERALD_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.EMERALD_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.EMERALD_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "emerald_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.DIAMOND_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.DIAMOND_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.DIAMOND_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "diamond_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.NETHERITE_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.NETHERITE_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.NETHERITE_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "netherite_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.SCULK_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.SCULK_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "sculk_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR_8X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.BIOMASS_GENERATOR)
                .input('$', Items.END_CRYSTAL)
                .criterion("has_item", conditionsFromItem(FSGItems.BIOMASS_GENERATOR))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "biomass_generator_8x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.NETHERITE_GENERATOR_64X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.NETHERITE_GENERATOR_8X)
                .input('$', Items.NETHER_STAR)
                .criterion("has_item", conditionsFromItem(FSGItems.NETHERITE_GENERATOR_8X))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "netherite_generator_64x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.SCULK_GENERATOR_64X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.SCULK_GENERATOR_8X)
                .input('$', Items.NETHER_STAR)
                .criterion("has_item", conditionsFromItem(FSGItems.SCULK_GENERATOR_8X))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "sculk_generator_64x"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FSGItems.BIOMASS_GENERATOR_64X)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .input('#', FSGItems.BIOMASS_GENERATOR_8X)
                .input('$', Items.NETHER_STAR)
                .criterion("has_item", conditionsFromItem(FSGItems.BIOMASS_GENERATOR_8X))
                .offerTo(exporter, Identifier.of(FabricSimpleGenerator.MOD_ID, "biomass_generator_64x"));

    }
}
