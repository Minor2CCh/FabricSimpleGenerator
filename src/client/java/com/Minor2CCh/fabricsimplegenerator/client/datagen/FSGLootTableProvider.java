package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FSGLootTableProvider extends FabricBlockLootTableProvider {
    protected FSGLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(FSGBlocks.IRON_GENERATOR);
        addDrop(FSGBlocks.COPPER_GENERATOR);
        addDrop(FSGBlocks.GOLD_GENERATOR);
        addDrop(FSGBlocks.DIAMOND_GENERATOR);
        addDrop(FSGBlocks.EMERALD_GENERATOR);
        addDrop(FSGBlocks.NETHERITE_GENERATOR);
        addDrop(FSGBlocks.SCULK_GENERATOR);
        addDrop(FSGBlocks.BIOMASS_GENERATOR);
        addDrop(FSGBlocks.IRON_GENERATOR_8X);
        addDrop(FSGBlocks.COPPER_GENERATOR_8X);
        addDrop(FSGBlocks.GOLD_GENERATOR_8X);
        addDrop(FSGBlocks.DIAMOND_GENERATOR_8X);
        addDrop(FSGBlocks.EMERALD_GENERATOR_8X);
        addDrop(FSGBlocks.NETHERITE_GENERATOR_8X);
        addDrop(FSGBlocks.SCULK_GENERATOR_8X);
        addDrop(FSGBlocks.BIOMASS_GENERATOR_8X);
        addDrop(FSGBlocks.NETHERITE_GENERATOR_64X);
        addDrop(FSGBlocks.SCULK_GENERATOR_64X);
        addDrop(FSGBlocks.BIOMASS_GENERATOR_64X);
        this.lootTables.forEach((id, lootTable) -> {
            lootTable.randomSequenceId(id.getValue());
        });

    }
}
