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
        addDrop(FSGBlocks.IRON_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.COPPER_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.GOLD_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.DIAMOND_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.EMERALD_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.NETHERITE_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.SCULK_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.BIOMASS_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.ENCHANTMENT_GENERATOR, this::nameableContainerDrops);
        addDrop(FSGBlocks.IRON_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.COPPER_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.GOLD_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.DIAMOND_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.EMERALD_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.NETHERITE_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.SCULK_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.BIOMASS_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.ENCHANTMENT_GENERATOR_8X, this::nameableContainerDrops);
        addDrop(FSGBlocks.NETHERITE_GENERATOR_64X, this::nameableContainerDrops);
        addDrop(FSGBlocks.SCULK_GENERATOR_64X, this::nameableContainerDrops);
        addDrop(FSGBlocks.BIOMASS_GENERATOR_64X, this::nameableContainerDrops);
        addDrop(FSGBlocks.ENCHANTMENT_GENERATOR_64X, this::nameableContainerDrops);
        this.lootTables.forEach((id, lootTable) -> {
            lootTable.randomSequenceId(id.getValue());
        });

    }
}
