/*
 * Copyright (c) 2019 RedGalaxy & contributors
 * Licensed under the Apache Licence v2.0.
 * Do not redistribute.
 *
 * By  : RGSW
 * Date: 8 - 30 - 2019
 */

package modernity.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

import modernity.api.block.IColoredBlock;
import modernity.common.block.base.*;
import modernity.common.fluid.MDFluids;
import modernity.common.item.MDItemGroups;
import modernity.common.world.gen.decorate.feature.MDFeatures;

import java.util.ArrayList;

public class MDBlocks {
    private static final ArrayList<Entry> ENTRIES = Lists.newArrayList();
    private static final ArrayList<Entry> ENTRIES_ITEM = Lists.newArrayList();


    // Rocks
    public static final BlockBase ROCK = blockItem( new BlockBase( "rock", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase DARKROCK = blockItem( new BlockBase( "darkrock", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase LIGHTROCK = blockItem( new BlockBase( "lightrock", Block.Properties.create( Material.ROCK, MaterialColor.SNOW ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ).lightValue( 15 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase REDROCK = blockItem( new BlockBase( "redrock", Block.Properties.create( Material.ROCK, MaterialColor.TNT ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockNoDrop MODERN_BEDROCK = blockItem( new BlockNoDrop( "modern_bedrock", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( - 1F, 3600000F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase BASALT = blockItem( new BlockBase( "basalt", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 3, 9 ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Building blocks
    public static final BlockBase ROCK_BRICKS = blockItem( new BlockBase( "rock_bricks", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase DARKROCK_BRICKS = blockItem( new BlockBase( "darkrock_bricks", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase ASPHALT_CONCRETE = blockItem( new BlockBase( "asphalt_concrete", Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockSlab ROCK_SLAB = blockItem( new BlockSlab( "rock_slab", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSlab DARKROCK_SLAB = blockItem( new BlockSlab( "darkrock_slab", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSlab ROCK_BRICKS_SLAB = blockItem( new BlockSlab( "rock_bricks_slab", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSlab DARKROCK_BRICKS_SLAB = blockItem( new BlockSlab( "darkrock_bricks_slab", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSlab ASPHALT_CONCRETE_SLAB = blockItem( new BlockSlab( "asphalt_concrete_slab", Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockStairs ROCK_STAIRS = blockItem( new BlockStairs( "rock_stairs", false, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs DARKROCK_STAIRS = blockItem( new BlockStairs( "darkrock_stairs", false, Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs ROCK_BRICKS_STAIRS = blockItem( new BlockStairs( "rock_bricks_stairs", false, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs DARKROCK_BRICKS_STAIRS = blockItem( new BlockStairs( "darkrock_bricks_stairs", false, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs ASPHALT_CONCRETE_STAIRS = blockItem( new BlockStairs( "asphalt_concrete_stairs", false, Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockStairs ROCK_STEP = blockItem( new BlockStairs( "rock_step", true, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs DARKROCK_STEP = blockItem( new BlockStairs( "darkrock_step", true, Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs ROCK_BRICKS_STEP = blockItem( new BlockStairs( "rock_bricks_step", true, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs DARKROCK_BRICKS_STEP = blockItem( new BlockStairs( "darkrock_bricks_step", true, Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs ASPHALT_CONCRETE_STEP = blockItem( new BlockStairs( "asphalt_concrete_step", true, Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockCorner ROCK_CORNER = blockItem( new BlockCorner( "rock_corner", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockCorner DARKROCK_CORNER = blockItem( new BlockCorner( "darkrock_corner", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockCorner ROCK_BRICKS_CORNER = blockItem( new BlockCorner( "rock_bricks_corner", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockCorner DARKROCK_BRICKS_CORNER = blockItem( new BlockCorner( "darkrock_bricks_corner", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockCorner ASPHALT_CONCRETE_CORNER = blockItem( new BlockCorner( "asphalt_concrete_corner", Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockWall ROCK_WALL = blockItem( new BlockWall( "rock_wall", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockWall DARKROCK_WALL = blockItem( new BlockWall( "darkrock_wall", Block.Properties.create( Material.ROCK, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockWall ROCK_BRICKS_WALL = blockItem( new BlockWall( "rock_bricks_wall", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockWall DARKROCK_BRICKS_WALL = blockItem( new BlockWall( "darkrock_bricks_wall", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockWall ASPHALT_CONCRETE_WALL = blockItem( new BlockWall( "asphalt_concrete_wall", Block.Properties.create( MDMaterial.ASPHALT, MaterialColor.BLACK ).hardnessAndResistance( 1.5F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    public static final BlockBase BLACKWOOD_PLANKS = blockItem( new BlockBase( "blackwood_planks", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase INVER_PLANKS = blockItem( new BlockBase( "inver_planks", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockSlab BLACKWOOD_SLAB = blockItem( new BlockSlab( "blackwood_slab", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSlab INVER_SLAB = blockItem( new BlockSlab( "inver_slab", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockStairs BLACKWOOD_STAIRS = blockItem( new BlockStairs( "blackwood_stairs", false, Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs INVER_STAIRS = blockItem( new BlockStairs( "inver_stairs", false, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockStairs BLACKWOOD_STEP = blockItem( new BlockStairs( "blackwood_step", true, Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockStairs INVER_STEP = blockItem( new BlockStairs( "inver_step", true, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockCorner BLACKWOOD_CORNER = blockItem( new BlockCorner( "blackwood_corner", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockCorner INVER_CORNER = blockItem( new BlockCorner( "inver_corner", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockFence BLACKWOOD_FENCE = blockItem( new BlockFence( "blackwood_fence", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockFence INVER_FENCE = blockItem( new BlockFence( "inver_fence", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockDoor BLACKWOOD_DOOR = blockItem( new BlockDoor( "blackwood_door", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDoor INVER_DOOR = blockItem( new BlockDoor( "inver_door", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockFenceGate BLACKWOOD_FENCE_GATE = blockItem( new BlockFenceGate( "blackwood_fence_gate", Block.Properties.create( Material.WOOD, MaterialColor.BLACK_TERRACOTTA ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockFenceGate INVER_FENCE_GATE = blockItem( new BlockFenceGate( "inver_fence_gate", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2, 3 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    public static final BlockGlass DARK_GLASS = blockItem( new BlockGlass( "dark_glass", Block.Properties.create( Material.GLASS, MaterialColor.GRAY ).hardnessAndResistance( 0.3F ).sound( SoundType.GLASS ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockGlassPane DARK_GLASS_PANE = blockItem( new BlockGlassPane( "dark_glass_pane", Block.Properties.create( Material.GLASS, MaterialColor.GRAY ).hardnessAndResistance( 0.3F ).sound( SoundType.GLASS ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Dusts
    public static final BlockFall ASH_BLOCK = blockItem( new BlockFall( "ash_block", 0x333333, Block.Properties.create( MDMaterial.ASH, MaterialColor.GRAY ).hardnessAndResistance( 0.4F ).sound( SoundType.SAND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockFall SALT_DUST_BLOCK = blockItem( new BlockFall( "salt_dust_block", 0xffffff, Block.Properties.create( Material.SAND, MaterialColor.SNOW ).hardnessAndResistance( 0.4F ).sound( SoundType.SAND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Soils
    public static final BlockDirt DARK_DIRT = blockItem( new BlockDirt( BlockDirt.TYPE_DIRT, Block.Properties.create( Material.GROUND, MaterialColor.DIRT ).hardnessAndResistance( 0.5F ).sound( SoundType.GROUND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDirt DARK_GRASS = blockItem( new BlockDirt.ColoredGrass( BlockDirt.TYPE_GRASS, Block.Properties.create( Material.GROUND, MaterialColor.GRASS ).hardnessAndResistance( 0.6F ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockFall DARK_SAND = blockItem( new BlockFall( "dark_sand", 0x584c39, Block.Properties.create( Material.SAND, MaterialColor.SAND ).hardnessAndResistance( 0.5F ).sound( SoundType.SAND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase DARK_CLAY = blockItem( new BlockBase( "dark_clay", Block.Properties.create( Material.CLAY, MaterialColor.GRAY_TERRACOTTA ).hardnessAndResistance( 0.5F ).sound( SoundType.GROUND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDirt COARSE_DARK_DIRT = blockItem( new BlockDirt( BlockDirt.TYPE_COARSE_DIRT, Block.Properties.create( Material.GROUND, MaterialColor.DIRT ).hardnessAndResistance( 0.6F ).sound( SoundType.GROUND ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDirt HUMUS = blockItem( new BlockDirt( BlockDirt.TYPE_HUMUS, Block.Properties.create( Material.GROUND, MaterialColor.ORANGE_TERRACOTTA ).hardnessAndResistance( 0.6F ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDirt DARK_PODZOL = blockItem( new BlockDirt( BlockDirt.TYPE_PODZOL, Block.Properties.create( Material.GROUND, MaterialColor.ORANGE_TERRACOTTA ).hardnessAndResistance( 0.6F ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Fluids
    public static final BlockFluid MODERNIZED_WATER = blockOnly( new BlockFluid( "modernized_water", MDFluids.MODERNIZED_WATER, Block.Properties.create( Material.WATER, MaterialColor.WATER ).doesNotBlockMovement().hardnessAndResistance( 100F ) ) );
    public static final BlockFluid PORTAL_FLUID = blockOnly( new BlockPortalFluid( "portal_fluid", MDFluids.PORTAL, Block.Properties.create( Material.WATER, MaterialColor.WATER ).doesNotBlockMovement().hardnessAndResistance( 100F ).lightValue( 9 ) ) );
    public static final BlockFluid HEATROCK_FLUID = blockOnly( new BlockFluid( "heatrock_fluid", MDFluids.HEATROCK_FLUID, Block.Properties.create( Material.LAVA, MaterialColor.TNT ).doesNotBlockMovement().hardnessAndResistance( 100F ).lightValue( 15 ) ) );


    // Plants
    public static final BlockTallPlant DARK_TALLGRASS = blockItem( new BlockTallPlant.ColoredGrass( "dark_tall_grass", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockTallWaterloggingPlant REEDS = blockItem( new BlockTallWaterloggingPlant.Reeds( "reeds", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockSinglePlant RED_MELION = blockItem( new BlockSinglePlant.Melion( "melion/red", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant ORANGE_MELION = blockItem( new BlockSinglePlant.Melion( "melion/orange", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant YELLOW_MELION = blockItem( new BlockSinglePlant.Melion( "melion/yellow", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant WHITE_MELION = blockItem( new BlockSinglePlant.Melion( "melion/white", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant BLUE_MELION = blockItem( new BlockSinglePlant.Melion( "melion/blue", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant INDIGO_MELION = blockItem( new BlockSinglePlant.Melion( "melion/indigo", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant MAGENTA_MELION = blockItem( new BlockSinglePlant.Melion( "melion/magenta", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockSinglePlant RED_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/red", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant YELLOW_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/yellow", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant WHITE_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/white", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant CYAN_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/cyan", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant GREEN_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/green", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant MAGENTA_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/magenta", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSinglePlant BLUE_MILLIUM = blockItem( new BlockSinglePlant.Millium( "millium/blue", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );

    public static final BlockSinglePlant MINT_PLANT = blockItem( new BlockSinglePlant.Mint( "mint_plant", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0.2F ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDamagingPlant NETTLES = blockItem( new BlockDamagingPlant.Nettles( "nettles", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0.5F ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSaltCrystal SALT_CRYSTAL = blockItem( new BlockSaltCrystal( "salt_crystal", Block.Properties.create( MDMaterial.CRYSTAL ).doesNotBlockMovement().hardnessAndResistance( 0.2F ).sound( SoundType.GLASS ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHangingPlant MURINA = blockItem( new BlockHangingPlant.ColoredMurina( "murina", Block.Properties.create( Material.VINE, MaterialColor.GRASS ).doesNotBlockMovement().hardnessAndResistance( 0 ).sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Blackwood tree
    public static final BlockLog STRIPPED_BLACKWOOD_LOG = blockItem( new BlockLog( "stripped_blackwood_log", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockLog BLACKWOOD_LOG = blockItem( new BlockStripableLog( "blackwood_log", STRIPPED_BLACKWOOD_LOG, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase STRIPPED_BLACKWOOD_BARK = blockItem( new BlockBase( "stripped_blackwood_bark", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase BLACKWOOD_BARK = blockItem( new BlockStripable( "blackwood_bark", STRIPPED_BLACKWOOD_BARK, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSapling BLACKWOOD_SAPLING = blockItem( new BlockSapling( "blackwood_sapling", () -> MDFeatures.BLACKWOOD_TREE, Block.Properties.create( Material.VINE, MaterialColor.GRASS ).hardnessAndResistance( 0 ).tickRandomly().sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHangLeaves BLACKWOOD_LEAVES = blockItem( new BlockHangLeaves.ColoredBlackwood( "blackwood_leaves", MDBlockTags.BLACKWOOD_LOG, BLACKWOOD_SAPLING, Block.Properties.create( Material.LEAVES ).hardnessAndResistance( 0.2F ).tickRandomly().sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Inver tree
    public static final BlockLog STRIPPED_INVER_LOG = blockItem( new BlockLog( "stripped_inver_log", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockLog INVER_LOG = blockItem( new BlockStripableLog( "inver_log", STRIPPED_INVER_LOG, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase STRIPPED_INVER_BARK = blockItem( new BlockBase( "stripped_inver_bark", Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase INVER_BARK = blockItem( new BlockStripable( "inver_bark", STRIPPED_INVER_BARK, Block.Properties.create( Material.WOOD, MaterialColor.WOOD ).hardnessAndResistance( 2 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockSapling INVER_SAPLING = blockItem( new BlockSapling( "inver_sapling", () -> MDFeatures.INVER_TREE, Block.Properties.create( Material.VINE, MaterialColor.GRASS ).hardnessAndResistance( 0 ).tickRandomly().sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockDecayLeaves INVER_LEAVES = blockItem( new BlockDecayLeaves.ColoredInver( "inver_leaves", MDBlockTags.INVER_LOG, INVER_SAPLING, Block.Properties.create( Material.LEAVES ).hardnessAndResistance( 0.2F ).tickRandomly().sound( SoundType.PLANT ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Ores
    public static final BlockOre SALT_ORE = blockItem( new BlockOre.Salt( "salt_ore", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 2F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockOre ALUMINIUM_ORE = blockItem( new BlockOre( "aluminium_ore", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 2F, 6F ).sound( SoundType.STONE ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Minerals
    public static final BlockTranslucent SALT_BLOCK = blockItem( new BlockTranslucent.Salt( "salt_block", Block.Properties.create( Material.GLASS, MaterialColor.SNOW ).hardnessAndResistance( 0.3F ).sound( SoundType.GLASS ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockBase ALUMINIUM_BLOCK = blockItem( new BlockBase( "aluminium_block", Block.Properties.create( Material.IRON, MaterialColor.IRON ).hardnessAndResistance( 5F, 6F ).sound( SoundType.METAL ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Utils
    public static final BlockTorch EXTINGUISHED_ANTHRACITE_TORCH = blockItem( new BlockTorch( "extinghuished_anthracite_torch", false, Block.Properties.create( Material.WOOD, MaterialColor.SNOW ).hardnessAndResistance( 0 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockExtinguishableTorch ANTHRACITE_TORCH = blockItem( new BlockExtinguishableTorch( "anthracite_torch", true, EXTINGUISHED_ANTHRACITE_TORCH, Block.Properties.create( Material.WOOD, MaterialColor.SNOW ).hardnessAndResistance( 0 ).lightValue( 15 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockTorch LIGHTROCK_TORCH = blockItem( new BlockTorch( "lightrock_torch", false, Block.Properties.create( Material.WOOD, MaterialColor.SNOW ).hardnessAndResistance( 0 ).lightValue( 15 ).sound( SoundType.WOOD ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );


    // Misc
    public static final BlockPortalCauldron PORTAL_CAULDRON = blockOnly( new BlockPortalCauldron( "portal_cauldron", Block.Properties.create( Material.IRON, MaterialColor.STONE ).hardnessAndResistance( 2F ).lightValue( 8 ) ) );
    public static final BlockNetherAltar NETHER_ALTAR = blockItem( new BlockNetherAltar( "nether_altar", Block.Properties.create( Material.ROCK, MaterialColor.STONE ).hardnessAndResistance( 2F ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_NATURE = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/nature", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_CURSE = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/curse", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_CYEN = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/cyen", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_FYREN = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/fyren", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_TIMEN = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/timen", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_PORTAL = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/portal", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );
    public static final BlockHorizontalFacing GOLD_CARVED_NETHER_BRICKS_RGSW = blockItem( new BlockHorizontalFacing( "gold_carved_nether_bricks/rgsw", Block.Properties.create( Material.ROCK, MaterialColor.NETHERRACK ).hardnessAndResistance( 2, 6 ), new Item.Properties().group( MDItemGroups.BLOCKS ) ) );





    public static void register( IForgeRegistry<Block> registry ) {
        for( Entry e : ENTRIES ) {
            registry.register( e.getThisBlock() );
        }
    }

    public static void registerItems( IForgeRegistry<Item> registry ) {
        for( Entry e : ENTRIES_ITEM ) {
            registry.register( e.createBlockItem() );
        }
    }

    @OnlyIn( Dist.CLIENT )
    public static void registerClient( BlockColors blockColors, ItemColors itemColors ) {
        for( Entry e : ENTRIES ) {
            if( e.getThisBlock() instanceof IColoredBlock ) {
                blockColors.register( ( (IColoredBlock) e.getThisBlock() )::colorMultiplier, e.getThisBlock() );
            }
        }
        for( Entry e : ENTRIES_ITEM ) {
            if( e.getThisBlock() instanceof IColoredBlock ) {
                itemColors.register( ( (IColoredBlock) e.getThisBlock() )::colorMultiplier, e.getThisBlock() );
            }
        }
    }

    private static <T extends Entry> T blockOnly( T item ) {
        ENTRIES.add( item );
        return item;
    }

    private static <T extends Entry> T blockItem( T item ) {
        ENTRIES.add( item );
        ENTRIES_ITEM.add( item );
        return item;
    }

    public interface Entry {
        Item getBlockItem();
        Item createBlockItem();
        Block getThisBlock();
    }
}
