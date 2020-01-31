/*
 * Copyright (c) 2020 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   01 - 30 - 2020
 * Author: rgsw
 */

package modernity.common.block.plant;

import modernity.api.block.IColoredBlock;
import modernity.client.ModernityClient;
import modernity.common.block.MDBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class WatergrassBlock extends TallDirectionalPlantBlock implements IWaterPlant, IColoredBlock {
    public WatergrassBlock( Properties properties ) {
        super( properties, Direction.UP );
    }

    @Override
    @OnlyIn( Dist.CLIENT )
    public int colorMultiplier( BlockState state, @Nullable IEnviromentBlockReader reader, @Nullable BlockPos pos, int tintIndex ) {
        return ModernityClient.get().getWatergrassColors().getColor( reader, pos );
    }

    @Override
    @OnlyIn( Dist.CLIENT )
    public int colorMultiplier( ItemStack stack, int tintIndex ) {
        return ModernityClient.get().getWatergrassColors().getItemColor();
    }

    @Override
    public boolean canBlockSustain( IWorldReader world, BlockPos pos, BlockState state ) {
        return state.isIn( MDBlockTags.SOIL );
    }
}