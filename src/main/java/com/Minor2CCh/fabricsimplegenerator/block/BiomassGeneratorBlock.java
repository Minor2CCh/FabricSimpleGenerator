package com.Minor2CCh.fabricsimplegenerator.block;

import com.Minor2CCh.fabricsimplegenerator.block.entity.BiomassGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BiomassGeneratorBlock extends AbstractGeneratorBlock{
    public static final MapCodec<BiomassGeneratorBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(GeneratorTier.CODEC.optionalFieldOf("gTier").forGetter(block -> Optional.of(GeneratorTier.BIOMASS)), createSettingsCodec())
                    .apply(instance, (gTier, settings) -> new BiomassGeneratorBlock(gTier.orElse(GeneratorTier.BIOMASS), 1, settings)));
    public BiomassGeneratorBlock(GeneratorTier gTier, int gMul, Settings settings) {
        super(settings);
        this.gTier = gTier;
        this.gMul = gMul;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BiomassGeneratorBlockEntity(gTier, gMul, pos, state);
    }
    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player){
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BiomassGeneratorBlockEntity) {
            NamedScreenHandlerFactory screenHandlerFactory = world.getBlockState(pos).createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

    }

    @Override
    protected MapCodec<BiomassGeneratorBlock> getCodec() {
        return CODEC;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                  BlockEntityType<T> type) {
        return validateTicker(type, FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY, BiomassGeneratorBlockEntity::tick);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? direction.getOffsetX() * g : h;
            double j = (2 + random.nextDouble() * 12.0) / 16.0;
            double k = axis == Direction.Axis.Z ? direction.getOffsetZ() * g : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
    }
}
