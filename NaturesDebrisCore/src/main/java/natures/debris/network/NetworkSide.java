/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.network;

import net.minecraftforge.fml.LogicalSide;

/**
 * Enum holding {@link #CLIENT} and {@link #SERVER}. Used in networking.
 */
public enum NetworkSide {
    CLIENT,
    SERVER;

    /**
     * Returns the equivalent {@link NetworkSide} of the specified {@link LogicalSide}.
     *
     * @param side The {@link LogicalSide}
     * @return The equivalent {@link NetworkSide}
     */
    public static NetworkSide fromLogical(LogicalSide side) {
        return side == LogicalSide.SERVER ? SERVER : CLIENT;
    }

    /**
     * Returns whether this represents the {@linkplain #CLIENT client} side.
     *
     * @return True when this is {@link #CLIENT}
     */
    public boolean isClient() {
        return this == CLIENT;
    }

    /**
     * Returns whether this represents the {@linkplain #SERVER server} side.
     *
     * @return True when this is {@link #SERVER}
     */
    public boolean isServer() {
        return this == SERVER;
    }

    /**
     * Returns the equivalent {@link LogicalSide} of this side.
     *
     * @return The equivalent {@link LogicalSide} of this side.
     */
    public LogicalSide toLogical() {
        return this == CLIENT ? LogicalSide.CLIENT : LogicalSide.SERVER;
    }

    /**
     * Returns the opposite side of this side. That is {@link #CLIENT} when this is {@link #SERVER}, and {@link #SERVER}
     * when this is {@link #CLIENT}.
     *
     * @return The opposite side of this side.
     */
    public NetworkSide opposite() {
        return this == CLIENT ? SERVER : CLIENT;
    }
}