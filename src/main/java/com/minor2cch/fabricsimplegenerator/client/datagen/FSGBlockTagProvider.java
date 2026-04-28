package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class FSGBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public FSGBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(FSGBlocks.IRON_GENERATOR)
                .add(FSGBlocks.COPPER_GENERATOR)
                .add(FSGBlocks.GOLD_GENERATOR)
                .add(FSGBlocks.DIAMOND_GENERATOR)
                .add(FSGBlocks.EMERALD_GENERATOR)
                .add(FSGBlocks.NETHERITE_GENERATOR)
                .add(FSGBlocks.SCULK_GENERATOR)
                .add(FSGBlocks.ENCHANTMENT_GENERATOR)
                .add(FSGBlocks.IRON_GENERATOR_8X)
                .add(FSGBlocks.COPPER_GENERATOR_8X)
                .add(FSGBlocks.GOLD_GENERATOR_8X)
                .add(FSGBlocks.DIAMOND_GENERATOR_8X)
                .add(FSGBlocks.EMERALD_GENERATOR_8X)
                .add(FSGBlocks.NETHERITE_GENERATOR_8X)
                .add(FSGBlocks.SCULK_GENERATOR_8X)
                .add(FSGBlocks.ENCHANTMENT_GENERATOR_8X)
                .add(FSGBlocks.NETHERITE_GENERATOR_64X)
                .add(FSGBlocks.SCULK_GENERATOR_64X)
                .add(FSGBlocks.ENCHANTMENT_GENERATOR_64X);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(FSGBlocks.BIOMASS_GENERATOR)
                .add(FSGBlocks.BIOMASS_GENERATOR_8X)
                .add(FSGBlocks.BIOMASS_GENERATOR_64X);
        valueLookupBuilder(BlockTags.GUARDED_BY_PIGLINS)
                .add(FSGBlocks.GOLD_GENERATOR)
                .add(FSGBlocks.GOLD_GENERATOR_8X);
    }
}
