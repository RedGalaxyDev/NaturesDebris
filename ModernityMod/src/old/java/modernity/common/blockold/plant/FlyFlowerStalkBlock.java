/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package modernity.common.blockold.plant;

import modernity.common.blockold.MDBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class FlyFlowerStalkBlock extends PlantBlock implements IWaterPlant {
    private static final VoxelShape SHAPE = makePlantShape(4, 16);

    public FlyFlowerStalkBlock(Properties properties) {
        super(properties);
    }

    public boolean canBlockSustain(IWorldReader world, BlockPos pos, BlockState state) {
        return state.isIn(MDBlockTags.SOIL) || state.getBlock() == this;
    }

    @Override
    public boolean canRemain(IWorldReader world, BlockPos pos, BlockState state, Direction dir, BlockPos adj, BlockState adjState) {
        return dir != Direction.DOWN || canBlockSustain(world, adj, adjState);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }
}
