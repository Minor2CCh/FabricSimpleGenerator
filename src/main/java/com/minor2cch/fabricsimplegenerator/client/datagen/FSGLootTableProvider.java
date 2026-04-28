package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class FSGLootTableProvider extends FabricBlockLootSubProvider {
    protected FSGLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> wrapper) {
        super(dataOutput, wrapper);
    }

    @Override
    public void generate() {
        addAndSetRandomSequence(FSGBlocks.IRON_GENERATOR, (this::createNameableBlockEntityTable));
        addAndSetRandomSequence(FSGBlocks.COPPER_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.GOLD_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.DIAMOND_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.EMERALD_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.NETHERITE_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.SCULK_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.BIOMASS_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.ENCHANTMENT_GENERATOR, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.IRON_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.COPPER_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.GOLD_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.DIAMOND_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.EMERALD_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.NETHERITE_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.SCULK_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.BIOMASS_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.ENCHANTMENT_GENERATOR_8X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.NETHERITE_GENERATOR_64X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.SCULK_GENERATOR_64X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.BIOMASS_GENERATOR_64X, this::createNameableBlockEntityTable);
        addAndSetRandomSequence(FSGBlocks.ENCHANTMENT_GENERATOR_64X, this::createNameableBlockEntityTable);
        // this.map.forEach((id, lootTable) -> lootTable.setRandomSequence(id.identifier()));

    }
    private void addAndSetRandomSequence(Block block, Function<Block, LootTable.Builder> builderFunction){
        LootTable.Builder builder = builderFunction.apply(block);
        add(block, builder);
        block.getLootTable().ifPresent(blockLootTable -> builder.setRandomSequence(blockLootTable.identifier()));
    }
}
