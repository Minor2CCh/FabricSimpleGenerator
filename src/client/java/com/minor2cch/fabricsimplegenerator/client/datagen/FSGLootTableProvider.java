package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FSGLootTableProvider extends FabricBlockLootTableProvider {
    protected FSGLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> wrapper) {
        super(dataOutput, wrapper);
    }

    @Override
    public void generate() {
        add(FSGBlocks.IRON_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.COPPER_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.GOLD_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.DIAMOND_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.EMERALD_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.NETHERITE_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.SCULK_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.BIOMASS_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.ENCHANTMENT_GENERATOR, this::createNameableBlockEntityTable);
        add(FSGBlocks.IRON_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.COPPER_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.GOLD_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.DIAMOND_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.EMERALD_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.NETHERITE_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.SCULK_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.BIOMASS_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.ENCHANTMENT_GENERATOR_8X, this::createNameableBlockEntityTable);
        add(FSGBlocks.NETHERITE_GENERATOR_64X, this::createNameableBlockEntityTable);
        add(FSGBlocks.SCULK_GENERATOR_64X, this::createNameableBlockEntityTable);
        add(FSGBlocks.BIOMASS_GENERATOR_64X, this::createNameableBlockEntityTable);
        add(FSGBlocks.ENCHANTMENT_GENERATOR_64X, this::createNameableBlockEntityTable);
        this.map.forEach((id, lootTable) -> lootTable.setRandomSequence(id.location()));

    }
}
