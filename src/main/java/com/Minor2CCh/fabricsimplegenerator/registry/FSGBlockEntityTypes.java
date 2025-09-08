package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import com.Minor2CCh.fabricsimplegenerator.block.entity.BiomassGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.block.entity.FuelGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.block.entity.SculkGeneratorBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.EnergyStorage;


public class FSGBlockEntityTypes {
    public static BlockEntityType<FuelGeneratorBlockEntity> FUEL_GENERATOR_BLOCK_ENTITY = register("fuel_generator", BlockEntityType.Builder.create(FuelGeneratorBlockEntity::new,
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
    public static BlockEntityType<SculkGeneratorBlockEntity> SCULK_GENERATOR_BLOCK_ENTITY = register("sculk_generator", BlockEntityType.Builder.create(SculkGeneratorBlockEntity::new,
            FSGBlocks.SCULK_GENERATOR,
            FSGBlocks.SCULK_GENERATOR_8X,
            FSGBlocks.SCULK_GENERATOR_64X
    ));
    public static BlockEntityType<BiomassGeneratorBlockEntity> BIOMASS_GENERATOR_BLOCK_ENTITY = register("biomass_generator", BlockEntityType.Builder.create(BiomassGeneratorBlockEntity::new,
            FSGBlocks.BIOMASS_GENERATOR,
            FSGBlocks.BIOMASS_GENERATOR_8X,
            FSGBlocks.BIOMASS_GENERATOR_64X
    ));
    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder){
        Identifier id = Identifier.of(FabricSimpleGenerator.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build());
    }
    public static void init(){
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, FUEL_GENERATOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, SCULK_GENERATOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, BIOMASS_GENERATOR_BLOCK_ENTITY);

    }
}
