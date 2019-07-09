/*
 * Copyright (c) 2019 RedGalaxy & co.
 * Licensed under the Apache Licence v2.0.
 * Do not redistribute.
 *
 * By  : RGSW
 * Date: 7 - 9 - 2019
 */

package modernity.common.world.gen;

import net.minecraft.world.gen.IChunkGenSettings;

public interface IModernityGenSettings extends IChunkGenSettings {
    int getBiomeBlendRadius();         // 3

    double getMainNoiseSizeX();        // 684.412
    double getMainNoiseSizeY();        // 684.412
    double getMainNoiseSizeZ();        // 684.412
    int getMainNoiseSizeOctaves();     // 16
    double getMixNoiseSizeX();         // 684.412 / 80
    double getMixNoiseSizeY();         // 684.412 / 160
    double getMixNoiseSizeZ();         // 684.412 / 80
    int getMixNoiseSizeOctaves();      // 8
    double getDepthNoiseSizeX();       // 200
    double getDepthNoiseSizeZ();       // 200
    int getDepthNoiseSizeOctaves();    // 16
    double getDepthNoiseInfluence();   // 0.2

    double getBaseBiomeDepth();        // 0
    double getBaseBiomeScale();        // 0
    double getBiomeDepthMultiplier();  // 1
    double getBiomeScaleMultiplier();  // 1
    double getHeightScale();           // 12
    double getHeightStretch();         // 8.5

    double getSurfaceNoiseSizeX();     // 28.733918
    double getSurfaceNoiseSizeY();     // 1.4252741
    double getSurfaceNoiseSizeZ();     // 28.733918
    int getSurfaceNoiseSizeOctaves();  // 4

    int getWaterLevel();              // 64
}
