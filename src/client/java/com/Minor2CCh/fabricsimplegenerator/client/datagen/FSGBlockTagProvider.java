package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class FSGBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public FSGBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(FSGBlocks.IRON_GENERATOR)
                .add(FSGBlocks.COPPER_GENERATOR)
                .add(FSGBlocks.GOLD_GENERATOR)
                .add(FSGBlocks.DIAMOND_GENERATOR)
                .add(FSGBlocks.EMERALD_GENERATOR)
                .add(FSGBlocks.NETHERITE_GENERATOR)
                .add(FSGBlocks.SCULK_GENERATOR)
                .add(FSGBlocks.IRON_GENERATOR_8X)
                .add(FSGBlocks.COPPER_GENERATOR_8X)
                .add(FSGBlocks.GOLD_GENERATOR_8X)
                .add(FSGBlocks.DIAMOND_GENERATOR_8X)
                .add(FSGBlocks.EMERALD_GENERATOR_8X)
                .add(FSGBlocks.NETHERITE_GENERATOR_8X)
                .add(FSGBlocks.SCULK_GENERATOR_8X)
                .add(FSGBlocks.NETHERITE_GENERATOR_64X)
                .add(FSGBlocks.SCULK_GENERATOR_64X);
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(FSGBlocks.BIOMASS_GENERATOR)
                .add(FSGBlocks.BIOMASS_GENERATOR_8X)
                .add(FSGBlocks.BIOMASS_GENERATOR_64X);
    }
}
