/*
 * Copyright (c) 2020 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   03 - 24 - 2020
 * Author: rgsw
 */

package modernity.common.block.misc;

import modernity.common.block.MDBlockStateProperties;
import modernity.common.block.base.AxisBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

import javax.annotation.Nullable;
import java.util.Optional;

public class BlackboneBlock extends AxisBlock {
    private static final BooleanProperty NATURAL = MDBlockStateProperties.NATURAL;

    public BlackboneBlock( Properties properties ) {
        super( properties );

        setDefaultState( stateContainer.getBaseState().with( NATURAL, true ) );
    }

    @Override
    protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
        super.fillStateContainer( builder );
        builder.add( NATURAL );
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement( BlockItemUseContext context ) {
        return Optional.ofNullable( super.getStateForPlacement( context ) )
                       .orElseGet( this::getDefaultState )
                       .with( NATURAL, false );
    }
}