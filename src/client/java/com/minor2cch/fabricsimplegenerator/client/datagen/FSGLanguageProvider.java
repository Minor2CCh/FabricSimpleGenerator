package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.block.entity.*;
import com.minor2cch.fabricsimplegenerator.client.FabricSimpleGeneratorClient;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlocks;
import com.minor2cch.fabricsimplegenerator.registry.FSGItemTags;
import com.minor2cch.fabricsimplegenerator.registry.FSGStats;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class FSGLanguageProvider extends FabricLanguageProvider {
    protected FSGLanguageProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, languageCode, provider);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
    }
    public static void addAll(FabricDataGenerator.Pack pack){
        pack.addProvider((dataOutput, registryLookup) -> new FSGLanguageProvider(dataOutput, "en_us", registryLookup){
            @Override
            public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
                translationBuilder.add(FSGBlocks.IRON_GENERATOR, "Iron Generator");
                translationBuilder.add(FSGBlocks.COPPER_GENERATOR, "Copper Generator");
                translationBuilder.add(FSGBlocks.GOLD_GENERATOR, "Gold Generator");
                translationBuilder.add(FSGBlocks.DIAMOND_GENERATOR, "Diamond Generator");
                translationBuilder.add(FSGBlocks.EMERALD_GENERATOR, "Emerald Generator");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR, "Netherite Generator");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR, "Sculk Generator");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR, "Biomass Generator");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR, "Enchantment Generator");
                translationBuilder.add(FSGBlocks.IRON_GENERATOR_8X, "8X Iron Generator");
                translationBuilder.add(FSGBlocks.COPPER_GENERATOR_8X, "8X Copper Generator");
                translationBuilder.add(FSGBlocks.GOLD_GENERATOR_8X, "8X Gold Generator");
                translationBuilder.add(FSGBlocks.EMERALD_GENERATOR_8X, "8X Emerald Generator");
                translationBuilder.add(FSGBlocks.DIAMOND_GENERATOR_8X, "8X Diamond Generator");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR_8X, "8X Netherite Generator");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR_8X, "8X Sculk Generator");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR_8X, "8X Biomass Generator");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR_8X, "8X Enchantment Generator");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR_64X, "64X Netherite Generator");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR_64X, "64X Sculk Generator");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR_64X, "64X Biomass Generator");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR_64X, "64X Enchantment Generator");
                translationBuilder.add(String.format("itemGroup.%s.%s", FabricSimpleGenerator.MOD_ID, "fsg_item_group"), "Fabric Simple Generator");
                translationBuilder.add(FabricSimpleGeneratorClient.TOOLTIP_HIDE, "§7<Press Shift for details>");
                translationBuilder.add(FabricSimpleGeneratorClient.MAX_CAPACITY_FORMAT, "Max Energy Capacity: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.MAX_EXTRACT_FORMAT, "Max Energy Extract: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, "Generate Energy: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_FORMAT, "Ingredient: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_FUEL_FORMAT, "Fuel Items");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_SCULK_FORMAT, "Sculk Items");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_BIO_FORMAT, "Compostable Items");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_ENCHANTMENT_FORMAT, "Enchanting Items");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_UNDEFINED_FORMAT, "Unknown");
                translationBuilder.add(FabricSimpleGeneratorClient.AMOUNT_FORMAT, "Amount: %s / %s");
                translationBuilder.add(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT_ENCHANTMENT_GENERATOR, "Varies by enchantment");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_BIT, "10 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_LOW, "100 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_MIDDLE, "500 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_HIGH, "1500 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_SPECIAL, "5000 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_EXTRA, "12000 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_ULTIMATE, "24000 tick Sculk Ingredients");
                translationBuilder.add(FSGItemTags.AZALEAS, "Azaleas");
                translationBuilder.add(FuelGeneratorBlockEntity.CONTAINER_NAME, "Fuel Generator");
                translationBuilder.add(SculkGeneratorBlockEntity.CONTAINER_NAME, "Sculk Generator");
                translationBuilder.add(BiomassGeneratorBlockEntity.CONTAINER_NAME, "Biomass Generator");
                translationBuilder.add(EnchantmentGeneratorBlockEntity.CONTAINER_NAME, "Enchantment Generator");
                translationBuilder.add(AbstractGeneratorBlockEntity.CONTAINER_NAME, "Unknown");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_FUEL_GENERATOR), "Interactions with Fuel Generator");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_SCULK_GENERATOR), "Interactions with Sculk Generator");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_BIOMASS_GENERATOR), "Interactions with Biomass Generator");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_ENCHANTMENT_GENERATOR), "Interactions with Enchantment Generator");

            }
        });
        pack.addProvider((dataOutput, registryLookup) -> new FSGLanguageProvider(dataOutput, "ja_jp", registryLookup){
            @Override
            public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
                //translationBuilder.add(ModItems.MY_ITEM, "My Custom Item");
                translationBuilder.add(FSGBlocks.IRON_GENERATOR, "鉄の発電機");
                translationBuilder.add(FSGBlocks.COPPER_GENERATOR, "銅の発電機");
                translationBuilder.add(FSGBlocks.GOLD_GENERATOR, "金の発電機");
                translationBuilder.add(FSGBlocks.DIAMOND_GENERATOR, "ダイヤモンドの発電機");
                translationBuilder.add(FSGBlocks.EMERALD_GENERATOR, "エメラルドの発電機");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR, "ネザライトの発電機");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR, "スカルクの発電機");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR, "バイオマス発電機");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR, "エンチャント発電機");
                translationBuilder.add(FSGBlocks.IRON_GENERATOR_8X, "8倍鉄の発電機");
                translationBuilder.add(FSGBlocks.COPPER_GENERATOR_8X, "8倍銅の発電機");
                translationBuilder.add(FSGBlocks.GOLD_GENERATOR_8X, "8倍金の発電機");
                translationBuilder.add(FSGBlocks.EMERALD_GENERATOR_8X, "8倍エメラルドの発電機");
                translationBuilder.add(FSGBlocks.DIAMOND_GENERATOR_8X, "8倍ダイヤモンドの発電機");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR_8X, "8倍ネザライトの発電機");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR_8X, "8倍スカルクの発電機");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR_8X, "8倍バイオマス発電機");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR_8X, "8倍エンチャント発電機");
                translationBuilder.add(FSGBlocks.NETHERITE_GENERATOR_64X, "64倍ネザライトの発電機");
                translationBuilder.add(FSGBlocks.SCULK_GENERATOR_64X, "64倍スカルクの発電機");
                translationBuilder.add(FSGBlocks.BIOMASS_GENERATOR_64X, "64倍バイオマス発電機");
                translationBuilder.add(FSGBlocks.ENCHANTMENT_GENERATOR_64X, "64倍エンチャント発電機");
                translationBuilder.add(String.format("itemGroup.%s.%s", FabricSimpleGenerator.MOD_ID, "fsg_item_group"), "Fabric Simple Generator");
                translationBuilder.add(FabricSimpleGeneratorClient.TOOLTIP_HIDE, "§7<Shiftを押して詳細を表示>");
                translationBuilder.add(FabricSimpleGeneratorClient.MAX_CAPACITY_FORMAT, "最大蓄電量: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.MAX_EXTRACT_FORMAT, "最大電力転送量: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT, "電力生成量: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_FORMAT, "原材料: %s");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_FUEL_FORMAT, "燃料アイテム");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_SCULK_FORMAT, "スカルク系のアイテム");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_BIO_FORMAT, "コンポスターに入れることができるアイテム");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_ENCHANTMENT_FORMAT, "エンチャントされたアイテム");
                translationBuilder.add(FabricSimpleGeneratorClient.INGREDIENT_UNDEFINED_FORMAT, "不明");
                translationBuilder.add(FabricSimpleGeneratorClient.AMOUNT_FORMAT, "蓄電量: %s / %s");
                translationBuilder.add(FabricSimpleGeneratorClient.TICK_GENERATE_FORMAT_ENCHANTMENT_GENERATOR, "エンチャントにより変動");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_BIT, "10tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_LOW, "100tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_MIDDLE, "500tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_HIGH, "1500tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_SPECIAL, "5000tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_EXTRA, "12000tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.SCULK_INGREDIENT_ULTIMATE, "24000tick分スカルク発電可能");
                translationBuilder.add(FSGItemTags.AZALEAS, "ツツジ");
                translationBuilder.add(FuelGeneratorBlockEntity.CONTAINER_NAME, "燃料発電機");
                translationBuilder.add(SculkGeneratorBlockEntity.CONTAINER_NAME, "スカルク発電機");
                translationBuilder.add(BiomassGeneratorBlockEntity.CONTAINER_NAME, "バイオマス発電機");
                translationBuilder.add(EnchantmentGeneratorBlockEntity.CONTAINER_NAME, "エンチャント発電機");
                translationBuilder.add(AbstractGeneratorBlockEntity.CONTAINER_NAME, "不明");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_FUEL_GENERATOR), "燃料発電機を使用した回数");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_SCULK_GENERATOR), "スカルク発電機を使用した回数");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_BIOMASS_GENERATOR), "バイオマス発電機を使用した回数");
                translationBuilder.add(getStatTranslationKey(FSGStats.INTERACT_WITH_ENCHANTMENT_GENERATOR), "エンチャント発電機を使用した回数");

            }
        });}

    private static String getStatTranslationKey(ResourceLocation id) {
        return "stat." + id.toString().replace(':', '.');
    }
}
