package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;


public class FSGModelProvider extends FabricModelProvider {
    public FSGModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCooker(FSGBlocks.IRON_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.COPPER_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.GOLD_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.DIAMOND_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.EMERALD_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.NETHERITE_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.SCULK_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.BIOMASS_GENERATOR, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.IRON_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.COPPER_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.GOLD_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.EMERALD_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.DIAMOND_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.NETHERITE_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.SCULK_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.BIOMASS_GENERATOR_8X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.NETHERITE_GENERATOR_64X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.SCULK_GENERATOR_64X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));
        blockStateModelGenerator.registerCooker(FSGBlocks.BIOMASS_GENERATOR_64X, TexturedModel.makeFactory(TextureMap::sideFrontTop, Models.ORIENTABLE));

    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


    }
}
