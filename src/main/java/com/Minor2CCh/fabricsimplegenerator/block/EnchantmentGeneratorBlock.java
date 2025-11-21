package com.Minor2CCh.fabricsimplegenerator.block;

import com.Minor2CCh.fabricsimplegenerator.block.entity.EnchantmentGeneratorBlockEntity;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGBlockEntityTypes;
import com.Minor2CCh.fabricsimplegenerator.registry.FSGStats;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EnchantmentGeneratorBlock extends AbstractGeneratorBlock{
    public static final MapCodec<EnchantmentGeneratorBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(GeneratorTier.CODEC.optionalFieldOf("gTier").forGetter(block -> Optional.of(GeneratorTier.ENCHANTMENT)), createSettingsCodec())
                    .apply(instance, (gTier, settings) -> new EnchantmentGeneratorBlock(gTier.orElse(GeneratorTier.ENCHANTMENT), 1, settings)));

    public EnchantmentGeneratorBlock(GeneratorTier gTier, int gMul, Settings settings) {
        super(gTier, gMul, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantmentGeneratorBlockEntity(gTier, gMul, pos, state);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnchantmentGeneratorBlockEntity) {
            NamedScreenHandlerFactory screenHandlerFactory = world.getBlockState(pos).createScreenHandlerFactory(world, pos);
            player.incrementStat(FSGStats.INTERACT_WITH_ENCHANTMENT_GENERATOR);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                  BlockEntityType<T> type) {
        return validateTicker(type, FSGBlockEntityTypes.ENCHANTMENT_GENERATOR_BLOCK_ENTITY, EnchantmentGeneratorBlockEntity::tick);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.5F, 0.5F, false);
            }
            for (BlockPos blockPos : EnchantingTableBlock.POWER_PROVIDER_OFFSETS) {
                if (random.nextInt(16) == 0) {
                    world.addParticle(
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
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
}
