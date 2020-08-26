/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.generator.blocks;

import natures.debris.common.blockold.plant.BushBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;

public class BushBlockGenerator implements IBlockGenerator {
    private final BushBlock block;

    public BushBlockGenerator(BushBlock block) {
        this.block = block;
    }


    @Override
    public boolean generateBlock(IWorld world, BlockPos pos, Random rand) {
        BlockPos down = pos.down();
        if (world.isAirBlock(pos) && block.isBlockSideSustainable(world.getBlockState(down), world, down, Direction.UP)) {
            return world.setBlockState(pos, block.getDefaultState(), 2);
        }
        BlockPos down2 = pos.down(2);
        if (world.isAirBlock(pos) && block.isBlockSideSustainable(world.getBlockState(down2), world, down2, Direction.UP) && world.getBlockState(down).getBlock() == block) {
            return world.setBlockState(pos, block.getDefaultState(), 2);
        }
        return false;
    }
}