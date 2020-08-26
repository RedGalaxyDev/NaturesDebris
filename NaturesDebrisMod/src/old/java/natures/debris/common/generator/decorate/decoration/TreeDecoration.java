/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.generator.decorate.decoration;

import natures.debris.common.generator.tree.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class TreeDecoration implements IDecoration {
    private final Tree tree;

    public TreeDecoration(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }

    @Override
    public void generate(IWorld world, BlockPos pos, Random rand, ChunkGenerator<?> chunkGenerator) {
        if (!tree.isSustainable(world, pos.down(), world.getBlockState(pos.down()))) {
            for (int i = 0; i < 20; i++) {
                pos = pos.down();
                if (world.getBlockState(pos.down()).isSolid()) {
                    break;
                }
            }
            if (!tree.isSustainable(world, pos, world.getBlockState(pos.down()))) {
                return;
            }
        }

        long seed = rand.nextLong();
        Random local = new Random(seed);

        if (!tree.canGenerate(world, local, pos)) {
            return;
        }

        local.setSeed(seed);

        tree.generate(world, local, pos);
    }
}