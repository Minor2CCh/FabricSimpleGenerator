package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public final class FSGBlocks {
    private FSGBlocks(){}
    public static final Block IRON_GENERATOR = register("iron_generator", new FuelGeneratorBlock(GeneratorTier.IRON, 1, Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block COPPER_GENERATOR = register("copper_generator", new FuelGeneratorBlock(GeneratorTier.COPPER, 1, Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block GOLD_GENERATOR = register("gold_generator", new FuelGeneratorBlock(GeneratorTier.GOLD, 1, Block.Properties.ofFullCopy(Blocks.GOLD_BLOCK)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block DIAMOND_GENERATOR = register("diamond_generator", new FuelGeneratorBlock(GeneratorTier.DIAMOND, 1, Block.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block EMERALD_GENERATOR = register("emerald_generator", new FuelGeneratorBlock(GeneratorTier.EMERALD, 1, Block.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block NETHERITE_GENERATOR = register("netherite_generator", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 1, Block.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block SCULK_GENERATOR = register("sculk_generator", new SculkGeneratorBlock(GeneratorTier.SCULK, 1, Block.Properties.ofFullCopy(Blocks.SCULK_CATALYST)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block BIOMASS_GENERATOR = register("biomass_generator", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 1, Block.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.MOSS)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block ENCHANTMENT_GENERATOR = register("enchantment_generator", new EnchantmentGeneratorBlock(GeneratorTier.ENCHANTMENT, 1, Block.Properties.of().mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).requiresCorrectToolForDrops()
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block IRON_GENERATOR_8X = register("iron_generator_8x", new FuelGeneratorBlock(GeneratorTier.IRON, 8, Block.Properties.ofFullCopy(IRON_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block COPPER_GENERATOR_8X = register("copper_generator_8x", new FuelGeneratorBlock(GeneratorTier.COPPER, 8, Block.Properties.ofFullCopy(COPPER_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block GOLD_GENERATOR_8X = register("gold_generator_8x", new FuelGeneratorBlock(GeneratorTier.GOLD, 8, Block.Properties.ofFullCopy(GOLD_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block EMERALD_GENERATOR_8X = register("emerald_generator_8x", new FuelGeneratorBlock(GeneratorTier.EMERALD, 8, Block.Properties.ofFullCopy(EMERALD_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block DIAMOND_GENERATOR_8X = register("diamond_generator_8x", new FuelGeneratorBlock(GeneratorTier.DIAMOND, 8, Block.Properties.ofFullCopy(DIAMOND_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block NETHERITE_GENERATOR_8X = register("netherite_generator_8x", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 8, Block.Properties.ofFullCopy(NETHERITE_GENERATOR)
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block SCULK_GENERATOR_8X = register("sculk_generator_8x", new SculkGeneratorBlock(GeneratorTier.SCULK, 8, Block.Properties.ofFullCopy(SCULK_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block BIOMASS_GENERATOR_8X = register("biomass_generator_8x", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 8, Block.Properties.ofFullCopy(BIOMASS_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block ENCHANTMENT_GENERATOR_8X = register("enchantment_generator_8x", new EnchantmentGeneratorBlock(GeneratorTier.ENCHANTMENT, 8, Block.Properties.ofFullCopy(ENCHANTMENT_GENERATOR)
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block NETHERITE_GENERATOR_64X = register("netherite_generator_64x", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 64, Block.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block SCULK_GENERATOR_64X = register("sculk_generator_64x", new SculkGeneratorBlock(GeneratorTier.SCULK, 64, Block.Properties.ofFullCopy(SCULK_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block BIOMASS_GENERATOR_64X = register("biomass_generator_64x", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 64, Block.Properties.ofFullCopy(BIOMASS_GENERATOR)
            .strength(3.5F, 6.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static final Block ENCHANTMENT_GENERATOR_64X = register("enchantment_generator_64x", new EnchantmentGeneratorBlock(GeneratorTier.ENCHANTMENT, 64, Block.Properties.ofFullCopy(ENCHANTMENT_GENERATOR)
            .strength(3.5F, 1200.0F)
            .lightLevel(Blocks.litBlockEmission(13))));
    public static Block register(String name, Block block) {
        ResourceLocation id = FabricSimpleGenerator.of(name);
        return Registry.register(BuiltInRegistries.BLOCK, id, block);

    }
    public static void init(){

    }
}
