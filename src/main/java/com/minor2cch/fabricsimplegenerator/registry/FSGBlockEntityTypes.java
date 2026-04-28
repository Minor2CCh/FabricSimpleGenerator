package com.minor2cch.fabricsimplegenerator.registry;

import com.minor2cch.fabricsimplegenerator.FabricSimpleGenerator;
import com.minor2cch.fabricsimplegenerator.block.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;


public final class FSGBlockEntityTypes {
    private FSGBlockEntityTypes(){}
    public static final BlockEntityType<FuelGeneratorBlockEntity> FUEL_GENERATOR_BLOCK_ENTITY = register("fuel_generator", FabricBlockEntityTypeBuilder.create(FuelGeneratorBlockEntity::new,
            FSGBlocks.IRON_GENERATOR,
            FSGBlocks.COPPER_GENERATOR,
            FSGBlocks.GOLD_GENERATOR,
            FSGBlocks.DIAMOND_GENERATOR,
            FSGBlocks.EMERALD_GENERATOR,
            FSGBlocks.NETHERITE_GENERATOR,
            FSGBlocks.IRON_GENERATOR_8X,
            FSGBlocks.COPPER_GENERATOR_8X,
            FSGBlocks.GOLD_GENERATOR_8X,
            FSGBlocks.EMERALD_GENERATOR_8X,
            FSGBlocks.DIAMOND_GENERATOR_8X,
            FSGBlocks.NETHERITE_GENERATOR_8X,
            FSGBlocks.NETHERITE_GENERATOR_64X
    ));
    public static final BlockEntityType<SculkGeneratorBlockEntity> SCULK_GENERATOR_BLOCK_ENTITY = register("sculk_generator", FabricBlockEntityTypeBuilder.create(SculkGeneratorBlockEntity::new,
            FSGBlocks.SCULK_GENERATOR,
            FSGBlocks.SCULK_GENERATOR_8X,
            FSGBlocks.SCULK_GENERATOR_64X
    ));
    public static final BlockEntityType<BiomassGeneratorBlockEntity> BIOMASS_GENERATOR_BLOCK_ENTITY = register("biomass_generator", FabricBlockEntityTypeBuilder.create(BiomassGeneratorBlockEntity::new,
            FSGBlocks.BIOMASS_GENERATOR,
            FSGBlocks.BIOMASS_GENERATOR_8X,
            FSGBlocks.BIOMASS_GENERATOR_64X
    ));
    public static final BlockEntityType<EnchantmentGeneratorBlockEntity> ENCHANTMENT_GENERATOR_BLOCK_ENTITY = register("enchantment_generator", FabricBlockEntityTypeBuilder.create(EnchantmentGeneratorBlockEntity::new,
            FSGBlocks.ENCHANTMENT_GENERATOR,
            FSGBlocks.ENCHANTMENT_GENERATOR_8X,
            FSGBlocks.ENCHANTMENT_GENERATOR_64X
    ));
    public static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder){
        Identifier id = FabricSimpleGenerator.of(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, builder.build());
    }
    public static void init(){
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, _) -> myBlockEntity.energyStorage, FUEL_GENERATOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, _) -> myBlockEntity.energyStorage, SCULK_GENERATOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, _) -> myBlockEntity.energyStorage, BIOMASS_GENERATOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, _) -> myBlockEntity.energyStorage, ENCHANTMENT_GENERATOR_BLOCK_ENTITY);

    }
}
