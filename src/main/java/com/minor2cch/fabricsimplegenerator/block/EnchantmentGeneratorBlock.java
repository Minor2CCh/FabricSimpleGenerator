package com.minor2cch.fabricsimplegenerator.block;

import com.minor2cch.fabricsimplegenerator.block.entity.EnchantmentGeneratorBlockEntity;
import com.minor2cch.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.minor2cch.fabricsimplegenerator.registry.FSGStats;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EnchantmentGeneratorBlock extends AbstractGeneratorBlock{
    public static final MapCodec<EnchantmentGeneratorBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(GeneratorTier.CODEC.optionalFieldOf("gTier").forGetter(_ -> Optional.of(GeneratorTier.ENCHANTMENT)), propertiesCodec())
                    .apply(instance, (gTier, properties) -> new EnchantmentGeneratorBlock(gTier.orElse(GeneratorTier.ENCHANTMENT), 1, properties)));

    public EnchantmentGeneratorBlock(GeneratorTier gTier, int gMul, Properties properties) {
        super(gTier, gMul, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantmentGeneratorBlockEntity(gTier, gMul, pos, state);
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof EnchantmentGeneratorBlockEntity) {
            MenuProvider menuProvider = level.getBlockState(pos).getMenuProvider(level, pos);
            player.awardStat(FSGStats.INTERACT_WITH_ENCHANTMENT_GENERATOR);

            if (menuProvider != null) {
                player.openMenu(menuProvider);
            }
        }
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, FSGBlockEntityTypes.ENCHANTMENT_GENERATOR_BLOCK_ENTITY, EnchantmentGeneratorBlockEntity::tick);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                level.playLocalSound(d, e, f, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 0.5F, 0.5F, false);
            }
            for (BlockPos blockPos : EnchantingTableBlock.BOOKSHELF_OFFSETS) {
                if (random.nextInt(16) == 0) {
                    level.addParticle(
                            ParticleTypes.ENCHANT,
                            pos.getX() + 0.5,
                            pos.getY() + 2.0,
                            pos.getZ() + 0.5,
                            blockPos.getX() + random.nextFloat() - 0.5,
                            blockPos.getY() - random.nextFloat() - 1.0F,
                            blockPos.getZ() + random.nextFloat() - 0.5
                    );
                }
            }
        }
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
