package com.Minor2CCh.fabricsimplegenerator.block;

import com.Minor2CCh.fabricsimplegenerator.block.entity.AbstractGeneratorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class AbstractGeneratorBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    protected GeneratorTier gTier;
    protected int gMul;
    protected AbstractGeneratorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LIT, false));
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    protected abstract void openScreen(World world, BlockPos pos, PlayerEntity player);
    public GeneratorTier getTier(){
        return this.gTier;
    }
    public int getMul(){
        return this.gMul;
    }
    public static GeneratorTier getTier(Block block){
        return block instanceof AbstractGeneratorBlock ? ((AbstractGeneratorBlock) block).getTier() : null;
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
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AbstractGeneratorBlockEntity generatorBlockEntity) {
                ItemScatterer.spawn(world, pos, generatorBlockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}
