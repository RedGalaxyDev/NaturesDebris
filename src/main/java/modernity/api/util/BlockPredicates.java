/*
 * Copyright (c) 2019 RedGalaxy & contributors
 * Licensed under the Apache Licence v2.0.
 * Do not redistribute.
 *
 * By  : RGSW
 * Date: 8 - 26 - 2019
 */

package modernity.api.util;

import modernity.common.block.MDBlocks;
import net.minecraft.block.BlockState;

import java.util.function.Predicate;

public final class BlockPredicates {
    /**
     * Matches any of the rock types (rock, darkrock, lightrock and redrock)
     */
    public static final Predicate<BlockState> ROCK_TYPES = state ->
                                                               state.getBlock() == MDBlocks.ROCK ||
                                                                   state.getBlock() == MDBlocks.DARKROCK ||
                                                                   state.getBlock() == MDBlocks.LIGHTROCK ||
                                                                   state.getBlock() == MDBlocks.REDROCK;

    /**
     * Matches everything
     */
    public static final Predicate<BlockState> TRUE = state -> true;

    /**
     * Matches nothing
     */
    public static final Predicate<BlockState> FALSE = state -> false;

    private BlockPredicates() {
    }
}