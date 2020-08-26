/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.blockold.plant;

import natures.debris.common.blockold.MDNatureBlocks;
import natures.debris.common.blockold.farmland.IFarmland;
import natures.debris.common.blockold.fluid.IMurkyWaterloggedBlock;
import natures.debris.common.blockold.fluid.IWaterloggedBlock;
import natures.debris.common.blockold.fluid.WaterlogType;
import natures.debris.common.blockold.plant.growing.IGrowLogic;
import natures.debris.common.event.MDBlockEvents;
import natures.debris.common.fluidold.MDFluids;
import natures.debris.generic.util.MDVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public abstract class PlantBlock extends Block {

    private IGrowLogic logic;

    public PlantBlock(Properties properties) {
        super(properties.notSolid()); // TODO Move this to block registry code

        BlockState def = stateContainer.getBaseState();
        if (this instanceof IMurkyWaterloggedBlock) {
            def = def.with(IMurkyWaterloggedBlock.WATERLOGGED, false);
        }
        if (this instanceof IWaterloggedBlock) {
            def = def.with(IWaterloggedBlock.WATERLOGGED, WaterlogType.NONE);
        }
        setDefaultState(def);
    }

    public static VoxelShape makePlantShape(double width, double height) {
        return MDVoxelShapes.plantShape(width, height);
    }

    public static VoxelShape makeHangPlantShape(double width, double height) {
        return MDVoxelShapes.hangPlantShape(width, height);
    }

    public IGrowLogic getGrowLogic() {
        return logic;
    }

    public PlantBlock setGrowLogic(IGrowLogic logic) {
        this.logic = logic;
        return this;
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return logic != null || super.ticksRandomly(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rng) {
        super.randomTick(state, world, pos, rng);
        if (!world.isRemote && logic != null) {
            BlockPos p = getRootPos(world, pos, state);
            if (p.equals(pos)) {
                IFarmland flLogic = getSupportingFarmland(world, pos);
                logic.grow(world, pos, state, rng, flLogic);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (logic != null) {
            BlockPos p = getRootPos(world, pos, state);
            ItemStack stack = player.getHeldItem(hand);
            if (logic.grow(world, p, state, world.rand, stack)) {
                if (!player.abilities.isCreativeMode && !world.isRemote) {
                    stack.shrink(1);
                    player.setHeldItem(hand, stack.getCount() == 0 ? ItemStack.EMPTY : stack);
                }
                MDBlockEvents.PLANT_GROW.play(world, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    protected IFarmland getSupportingFarmland(IWorld world, BlockPos pos) {
        return IFarmland.get(world, pos.down());
    }

    protected BlockPos getRootPos(World world, BlockPos pos, BlockState state) {
        return pos;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        if (this instanceof IMurkyWaterloggedBlock) {
            builder.add(IMurkyWaterloggedBlock.WATERLOGGED);
        }
        if (this instanceof IWaterloggedBlock) {
            builder.add(IWaterloggedBlock.WATERLOGGED);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos poas, ISelectionContext ctx) {
        return VoxelShapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type) {
        return true;
    }

    @Override
    public boolean canSpawnInBlock() {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState state, Direction dir, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos) {
        if (this instanceof IMurkyWaterloggedBlock || this instanceof IWaterloggedBlock || this instanceof IWaterPlant) {
            Fluid fluid = world.getFluidState(pos).getFluid();
            world.getPendingFluidTicks().scheduleTick(pos, fluid, fluid.getTickRate(world));
        }

        if (state.isValidPosition(world, pos)) {
            return state;
        }
        return Blocks.AIR.getDefaultState();
    }

    public boolean canRemain(IWorldReader world, BlockPos pos, BlockState state, Direction dir, BlockPos adj, BlockState adjState) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        if (this instanceof IWaterPlant) {
            if (world.getFluidState(pos).getFluid() != MDFluids.MURKY_WATER) {
                return false;
            }
        } else if (!(this instanceof IMurkyWaterloggedBlock) && !(this instanceof IWaterloggedBlock)) {
            if (world.getFluidState(pos).getFluid() != Fluids.EMPTY) {
                return false;
            }
        }
        for (Direction dir : Direction.values()) {
            BlockPos off = pos.offset(dir);
            BlockState offState = world.getBlockState(off);
            if (!canRemain(world, pos, state, dir, off, offState)) return false;
        }
        return true;
    }

    public void kill(World world, BlockPos pos, BlockState state) {
        world.removeBlock(pos, false);
    }

    public void growAt(World world, BlockPos pos) {
        world.setBlockState(pos, computeStateForPos(world, pos), 3);
    }

    public boolean canGenerateAt(IWorld world, BlockPos pos, BlockState state) {
        boolean air = state.isAir(world, pos);
        if (this instanceof IWaterPlant) {
            air = state.getFluidState().getFluid() == MDFluids.MURKY_WATER && state.getBlock() == MDNatureBlocks.MURKY_WATER;
        }
        if (this instanceof IMurkyWaterloggedBlock) {
            air |= state.getFluidState().getFluid() == MDFluids.MURKY_WATER && state.getBlock() == MDNatureBlocks.MURKY_WATER;
        }
        if (this instanceof IWaterloggedBlock) {
            air |= state.getFluidState().getFluid() == MDFluids.MURKY_WATER && state.getBlock() == MDNatureBlocks.MURKY_WATER;
            air |= state.getFluidState().getFluid() == Fluids.WATER && state.getBlock() == Blocks.WATER;
        }
        return air && isValidPosition(state, world, pos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return computeStateForPos(ctx.getWorld(), ctx.getPos(), getDefaultState());
    }

    public BlockState computeStateForPos(IWorldReader world, BlockPos pos, BlockState state) {
        if (this instanceof IMurkyWaterloggedBlock) {
            state = state.with(IMurkyWaterloggedBlock.WATERLOGGED, world.getFluidState(pos).getFluid() == MDFluids.MURKY_WATER);
        }
        if (this instanceof IWaterloggedBlock) {
            state = state.with(IWaterloggedBlock.WATERLOGGED, WaterlogType.getType(world.getFluidState(pos)));
        }
        return state;
    }

    public BlockState computeStateForPos(IWorldReader world, BlockPos pos) {
        return computeStateForPos(world, pos, getDefaultState());
    }

    public BlockState computeStateForGeneration(IWorldReader world, BlockPos pos, Random rand) {
        return computeStateForPos(world, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
        if (this instanceof IMurkyWaterloggedBlock) {
            return state.get(IMurkyWaterloggedBlock.WATERLOGGED)
                   ? MDFluids.MURKY_WATER.getDefaultState()
                   : Fluids.EMPTY.getDefaultState();
        }

        if (this instanceof IWaterloggedBlock) {
            return state.get(IWaterloggedBlock.WATERLOGGED).getFluidState();
        }

        if (this instanceof IWaterPlant) {
            return MDFluids.MURKY_WATER.getDefaultState();
        }

        return Fluids.EMPTY.getDefaultState();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (this instanceof IDangerousPlant) {
            IDangerousPlant dangerous = (IDangerousPlant) this;
            if (dangerous.dealsDamage(world, pos, state, entity)) {
                DamageSource src = dangerous.getDamageSource(world, pos, state, entity);
                float damage = dangerous.getDamage(world, pos, state, entity);

                entity.attackEntityFrom(src, damage);
            }
        }
    }

    public boolean isBlockSideSustainable(BlockState state, ILightReader world, BlockPos pos, Direction side) {
        Block block = state.getBlock();
        if (block instanceof IPlantSustainer) {
            return ((IPlantSustainer) block).canSustainPlant(world, pos, state, this, side);
        } else {
            return state.isSolid() && state.isSolidSide(world, pos, side);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = getShape(state);
        Vec3d offset = state.getOffset(world, pos);
        return shape.withOffset(offset.x, offset.y, offset.z);
    }

    public VoxelShape getShape(BlockState state) {
        return VoxelShapes.fullCube();
    }
}