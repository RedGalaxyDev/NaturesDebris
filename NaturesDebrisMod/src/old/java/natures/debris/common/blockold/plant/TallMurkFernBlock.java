/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.blockold.plant;

import natures.debris.common.blockold.MDBlockTags;
import natures.debris.common.blockold.plant.growing.MurkFernGrowLogic;
import natures.debris.generic.block.IColoredBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.ILightReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TallMurkFernBlock extends DoublePlantBlock implements IColoredBlock {
    public static final VoxelShape LOWER_SHAPE = makePlantShape(14, 16);
    public static final VoxelShape UPPER_SHAPE = makePlantShape(14, 10);

    public TallMurkFernBlock(Properties properties) {
        super(properties);
        setGrowLogic(new MurkFernGrowLogic());
    }

    @Override
    public boolean canBlockSustain(IWorldReader world, BlockPos pos, BlockState state) {
        return state.isIn(MDBlockTags.DIRTLIKE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int colorMultiplier(BlockState state, @Nullable ILightReader reader, @Nullable BlockPos pos, int tintIndex) {
        return 0;
//        return ModernityClient.get().getFernColors().getColor( reader, pos );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return 0;
//        return ModernityClient.get().getFernColors().getItemColor();
    }

    @Override
    public VoxelShape getShape(BlockState state) {
        return state.get(TYPE) == ROOT ? LOWER_SHAPE : UPPER_SHAPE;
    }
}