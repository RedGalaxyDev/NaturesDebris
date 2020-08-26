/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.blockold.plant.growing;

import natures.debris.common.blockold.farmland.IFarmland;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

@FunctionalInterface
public interface IGrowLogic {
    void grow(World world, BlockPos pos, BlockState state, Random rand, @Nullable IFarmland farmland);
    default boolean grow(World world, BlockPos pos, BlockState state, Random rand, ItemStack item) {
        return false;
    }
}