package com.minor2cch.fabricsimplegenerator.block;

import com.minor2cch.fabricsimplegenerator.block.entity.BiomassGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.registry.FSGStats;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BiomassGeneratorBlock extends AbstractGeneratorBlock{
    public static final MapCodec<BiomassGeneratorBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(GeneratorTier.CODEC.optionalFieldOf("gTier").forGetter(block -> Optional.of(GeneratorTier.BIOMASS)), propertiesCodec())
                    .apply(instance, (gTier, properties) -> new BiomassGeneratorBlock(gTier.orElse(GeneratorTier.BIOMASS), 1, properties)));
    public BiomassGeneratorBlock(GeneratorTier gTier, int gMul, Properties properties) {
        super(gTier, gMul, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BiomassGeneratorBlockEntity(gTier, gMul, pos, state);
    }
    @Override
    protected void openContainer(Level level, BlockPos pos, Player player){
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BiomassGeneratorBlockEntity) {
            MenuProvider menuProvider = level.getBlockState(pos).getMenuProvider(level, pos);
            player.awardStat(FSGStats.INTERACT_WITH_BIOMASS_GENERATOR);

            if (menuProvider != null) {
                player.openMenu(menuProvider);
            }
        }

    }

    @Override
    protected @NotNull MapCodec<BiomassGeneratorBlock> codec() {
        return CODEC;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, FSGBlockEntityTypes.BIOMASS_GENERATOR_BLOCK_ENTITY, BiomassGeneratorBlockEntity::tick);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                level.playLocalSound(d, e, f, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? direction.getStepX() * g : h;
            double j = (2 + random.nextDouble() * 12.0) / 16.0;
            double k = axis == Direction.Axis.Z ? direction.getStepZ() * g : h;
            level.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
    }
}
