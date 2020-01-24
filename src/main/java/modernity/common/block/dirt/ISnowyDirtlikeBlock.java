/*
 * Copyright (c) 2020 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   01 - 19 - 2020
 * Author: rgsw
 */

package modernity.common.block.dirt;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public interface ISnowyDirtlikeBlock {
    BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    static BlockState makeSnowy( IWorldReader world, BlockPos pos, BlockState state ) {
        BlockState facingState = world.getBlockState( pos.up() );
        boolean snowy = false;
        if( facingState.getBlock() == Blocks.SNOW || facingState.getBlock() == Blocks.SNOW_BLOCK ) {
            snowy = true;
        }
        return state.with( SNOWY, snowy );
    }
}