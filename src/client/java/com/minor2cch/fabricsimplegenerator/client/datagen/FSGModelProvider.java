package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TexturedModel;


public class FSGModelProvider extends FabricModelProvider {
    public FSGModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createFurnace(FSGBlocks.IRON_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.COPPER_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.GOLD_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.DIAMOND_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.EMERALD_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.NETHERITE_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.SCULK_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.BIOMASS_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.ENCHANTMENT_GENERATOR, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.IRON_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.COPPER_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.GOLD_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.EMERALD_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.DIAMOND_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.NETHERITE_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.SCULK_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.BIOMASS_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.ENCHANTMENT_GENERATOR_8X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.NETHERITE_GENERATOR_64X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.SCULK_GENERATOR_64X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.BIOMASS_GENERATOR_64X, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockModelGenerators.createFurnace(FSGBlocks.ENCHANTMENT_GENERATOR_64X, TexturedModel.ORIENTABLE_ONLY_TOP);

    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {


    }
}
