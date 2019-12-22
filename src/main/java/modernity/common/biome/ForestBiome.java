/*
 * Copyright (c) 2019 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   12 - 22 - 2019
 * Author: rgsw
 */

package modernity.common.biome;

import modernity.common.block.MDBlocks;
import modernity.common.environment.precipitation.IPrecipitationFunction;
import modernity.common.generator.decorate.count.Chance;
import modernity.common.generator.decorate.decoration.FeatureDecoration;
import modernity.common.generator.decorate.decorator.DecorationDecorator;
import modernity.common.generator.decorate.position.FixedHeight;
import modernity.common.generator.surface.HumusSurfaceGenerator;
import modernity.common.world.gen.feature.LakeFeature;
import modernity.common.world.gen.feature.MDFeatures;

/**
 * The 'Forest' or 'modernity:forest' biome.
 */
public class ForestBiome extends ModernityBiome {
    protected ForestBiome() {
        super(
            new Builder()
                .baseHeight( 4 ).heightVariation( 6 ).heightDifference( 3 )
                .surfaceGen( new HumusSurfaceGenerator() )
                .precipitation( IPrecipitationFunction.standard() )
        );

        addDecorator( new DecorationDecorator( new FeatureDecoration<>( MDFeatures.LAKE, new LakeFeature.Config( MDBlocks.MURKY_WATER, null, null, MDBlocks.MURKY_GRASS_BLOCK ) ), new FixedHeight( 128 ), new Chance( 0.2 ) ) );

//        addFeature( GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature( MDFeatures.LAKE, new LakeFeature.Config( MDBlocks.MURKY_WATER, null, null, MDBlocks.MURKY_GRASS_BLOCK ), Placement.WATER_LAKE, new LakeChanceConfig( 5 ) ) );

//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.CLUSTER_BUSH, new ClusterBushFeature.Config( 100, 6, MDBlocks.MURK_GRASS ), Placement.COUNT_HEIGHTMAP, new FrequencyConfig( 3 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.CLUSTER_BUSH, new ClusterBushFeature.Config( 81, 7, MDBlocks.NETTLES ), Placement.CHANCE_HEIGHTMAP, new ChanceConfig( 2 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.CLUSTER_BUSH, new ClusterBushFeature.Config( 81, 7, MDBlocks.MINT_PLANT ), Placement.CHANCE_HEIGHTMAP, new ChanceConfig( 2 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.CLUSTER_BUSH, new ClusterBushFeature.Config( 81, 7, new IBlockProvider.ChooseRandom( MDBlocks.BLUE_MILLIUM, MDBlocks.CYAN_MILLIUM, MDBlocks.GREEN_MILLIUM, MDBlocks.YELLOW_MILLIUM, MDBlocks.MAGENTA_MILLIUM, MDBlocks.RED_MILLIUM, MDBlocks.WHITE_MILLIUM ) ), Placement.COUNT_HEIGHTMAP, new FrequencyConfig( 1 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.CLUSTER_BUSH, new ClusterBushFeature.Config( 81, 7, new IBlockProvider.ChooseRandom( MDBlocks.BLUE_MELION, MDBlocks.ORANGE_MELION, MDBlocks.INDIGO_MELION, MDBlocks.YELLOW_MELION, MDBlocks.MAGENTA_MELION, MDBlocks.RED_MELION, MDBlocks.WHITE_MELION ) ), Placement.COUNT_HEIGHTMAP, new FrequencyConfig( 1 ) ) );
//
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.GROUPED_BUSH, new GroupedBushFeature.Config( 3, 5, 4, MDBlocks.REEDS ), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig( 3 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.GROUPED_BUSH, new GroupedBushFeature.Config( 3, 5, 4, MDBlocks.REDWOLD ), Placement.CHANCE_HEIGHTMAP, new ChanceConfig( 6 ) ) );
//
//        addFeature( GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature( MDFeatures.DEPOSIT, new DepositFeature.Config( 4, BlockPredicates.TRUE, MDBlocks.ROCK.getDefaultState() ), MDPlacements.CHANCE_HEIGHTMAP_NO_LEAVES, new ChanceConfig( 8 ) ) );
//        addFeature( GenerationStage.Decoration.LOCAL_MODIFICATIONS, createDecoratedFeature( MDFeatures.DEPOSIT, new DepositFeature.Config( 4, BlockPredicates.TRUE, MDBlocks.DARKROCK.getDefaultState() ), MDPlacements.CHANCE_HEIGHTMAP_NO_LEAVES, new ChanceConfig( 16 ) ) );
//
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.TREE, MDTrees.INVER, Placement.COUNT_HEIGHTMAP, new FrequencyConfig( 5 ) ) );
//        addFeature( GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature( MDFeatures.TREE, MDTrees.BLACKWOOD, Placement.COUNT_HEIGHTMAP, new FrequencyConfig( 6 ) ) );
    }
}
