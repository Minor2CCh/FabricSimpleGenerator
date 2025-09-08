package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.block.BiomassGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.FuelGeneratorBlock;
import com.Minor2CCh.fabricsimplegenerator.block.GeneratorTier;
import com.Minor2CCh.fabricsimplegenerator.block.SculkGeneratorBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class FSGBlocks {
    public static final Block IRON_GENERATOR = register("iron_generator", new FuelGeneratorBlock(GeneratorTier.IRON, 1, Block.Settings.copy(Blocks.IRON_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block COPPER_GENERATOR = register("copper_generator", new FuelGeneratorBlock(GeneratorTier.COPPER, 1, Block.Settings.copy(Blocks.COPPER_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block GOLD_GENERATOR = register("gold_generator", new FuelGeneratorBlock(GeneratorTier.GOLD, 1, Block.Settings.copy(Blocks.GOLD_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block DIAMOND_GENERATOR = register("diamond_generator", new FuelGeneratorBlock(GeneratorTier.DIAMOND, 1, Block.Settings.copy(Blocks.DIAMOND_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block EMERALD_GENERATOR = register("emerald_generator", new FuelGeneratorBlock(GeneratorTier.EMERALD, 1, Block.Settings.copy(Blocks.EMERALD_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block NETHERITE_GENERATOR = register("netherite_generator", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 1, Block.Settings.copy(Blocks.NETHERITE_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block SCULK_GENERATOR = register("sculk_generator", new SculkGeneratorBlock(GeneratorTier.SCULK, 1, Block.Settings.copy(Blocks.SCULK_CATALYST)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block BIOMASS_GENERATOR = register("biomass_generator", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 1, AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.MOSS_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block IRON_GENERATOR_8X = register("iron_generator_8x", new FuelGeneratorBlock(GeneratorTier.IRON, 8, Block.Settings.copy(Blocks.IRON_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block COPPER_GENERATOR_8X = register("copper_generator_8x", new FuelGeneratorBlock(GeneratorTier.COPPER, 8, Block.Settings.copy(Blocks.COPPER_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block GOLD_GENERATOR_8X = register("gold_generator_8x", new FuelGeneratorBlock(GeneratorTier.GOLD, 8, Block.Settings.copy(Blocks.GOLD_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block EMERALD_GENERATOR_8X = register("emerald_generator_8x", new FuelGeneratorBlock(GeneratorTier.EMERALD, 8, Block.Settings.copy(Blocks.EMERALD_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block DIAMOND_GENERATOR_8X = register("diamond_generator_8x", new FuelGeneratorBlock(GeneratorTier.DIAMOND, 8, Block.Settings.copy(Blocks.DIAMOND_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block NETHERITE_GENERATOR_8X = register("netherite_generator_8x", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 8, Block.Settings.copy(Blocks.NETHERITE_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block SCULK_GENERATOR_8X = register("sculk_generator_8x", new SculkGeneratorBlock(GeneratorTier.SCULK, 8, Block.Settings.copy(Blocks.SCULK_CATALYST)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block BIOMASS_GENERATOR_8X = register("biomass_generator_8x", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 8, AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.MOSS_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block NETHERITE_GENERATOR_64X = register("netherite_generator_64x", new FuelGeneratorBlock(GeneratorTier.NETHERITE, 64, Block.Settings.copy(Blocks.NETHERITE_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block SCULK_GENERATOR_64X = register("sculk_generator_64x", new SculkGeneratorBlock(GeneratorTier.SCULK, 64, Block.Settings.copy(Blocks.SCULK_CATALYST)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static final Block BIOMASS_GENERATOR_64X = register("biomass_generator_64x", new BiomassGeneratorBlock(GeneratorTier.BIOMASS, 64, AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.MOSS_BLOCK)
            .strength(3.5F, 6.0F)
            .luminance(Blocks.createLightLevelFromLitBlockState(13))));
    public static Block register(String name, Block block) {
        Identifier id = Identifier.of(FabricSimpleGenerator.MOD_ID, name);
        return Registry.register(Registries.BLOCK, id, block);

    }
    public static void init(){

    }
}
