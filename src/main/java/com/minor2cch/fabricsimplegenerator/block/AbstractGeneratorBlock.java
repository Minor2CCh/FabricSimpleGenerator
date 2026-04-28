package com.minor2cch.fabricsimplegenerator.block;

import com.minor2cch.fabricsimplegenerator.block.entity.AbstractGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractGeneratorBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected final GeneratorTier gTier;
    protected final int gMul;
    protected AbstractGeneratorBlock(GeneratorTier gTier, int gMul, Properties properties) {
        super(properties);
        this.gTier = gTier;
        this.gMul = gMul;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }
    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected abstract void openContainer(Level level, BlockPos pos, Player player);
    public GeneratorTier getTier(){
        return this.gTier;
    }
    public int getMul(){
        return this.gMul;
    }
    public static GeneratorTier getTier(Block block){
        return block instanceof AbstractGeneratorBlock ? ((AbstractGeneratorBlock) block).getTier() : GeneratorTier.IRON;
    }
    public static int getMul(Block block){
        return block instanceof AbstractGeneratorBlock ? ((AbstractGeneratorBlock) block).getMul() : 1;
    }
    public long getCapacity(){
        return this.getTier().CAPACITY * this.getMul();
    }
    public long getExtract(){
        return this.getTier().MAX_EXTRACT * this.getMul();

    }
    public long getTickGenerate(){
        return this.getTier().TICK_GENERATE * this.getMul();

    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
    @Override
    protected @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    protected void affectNeighborsAfterRemoval(final BlockState state, final ServerLevel level, final BlockPos pos, final boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }
    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof AbstractGeneratorBlockEntity generator){
            long amount = generator.getAmount();
            long maxCapacity = generator.getCapacity();
            double percentage = amount * 1.0 / maxCapacity;
            if(amount > 0){
                return Math.clamp(Mth.floor(1 + percentage * 14), 1, 15);
            }
        }
        return 0;
    }
}
