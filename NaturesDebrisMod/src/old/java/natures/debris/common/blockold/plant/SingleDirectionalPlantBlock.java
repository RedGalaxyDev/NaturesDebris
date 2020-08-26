/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.blockold.plant;

import net.minecraft.util.Direction;

public abstract class SingleDirectionalPlantBlock extends DirectionalPlantBlock {
    public SingleDirectionalPlantBlock(Properties properties, Direction growDir) {
        super(properties, growDir);
    }

}