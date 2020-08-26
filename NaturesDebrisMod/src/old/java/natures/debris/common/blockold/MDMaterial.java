/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.blockold;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

/**
 * Holder class for Modernity block materials
 */
public final class MDMaterial {
    public static final Material ASH = new Builder(MaterialColor.BLACK_TERRACOTTA).build();
    public static final Material ASPHALT = new Builder(MaterialColor.BLACK).requiresTool().build();
    public static final Material CRYSTAL = new Builder(MaterialColor.SNOW).notOpaque().doesNotBlockMovement().pushDestroys().notSolid().notOpaque().build();
    public static final Material OIL = new Builder(MaterialColor.BLACK).doesNotBlockMovement().notOpaque().notSolid().pushDestroys().replaceable().liquid().build();

    private MDMaterial() {
    }

    public static class Builder {
        private final MaterialColor color;
        private PushReaction pushReaction = PushReaction.NORMAL;
        private boolean blocksMovement = true;
        private boolean canBurn;
        private boolean requiresNoTool = true;
        private boolean isLiquid;
        private boolean isReplaceable;
        private boolean isSolid = true;
        private boolean isOpaque = true;

        public Builder(MaterialColor color) {
            this.color = color;
        }

        public Builder liquid() {
            this.isLiquid = true;
            return this;
        }

        public Builder notSolid() {
            this.isSolid = false;
            return this;
        }

        public Builder doesNotBlockMovement() {
            this.blocksMovement = false;
            return this;
        }

        public Builder notOpaque() {
            this.isOpaque = false;
            return this;
        }

        public Builder requiresTool() {
            this.requiresNoTool = false;
            return this;
        }

        public Builder flammable() {
            this.canBurn = true;
            return this;
        }

        public Builder replaceable() {
            this.isReplaceable = true;
            return this;
        }

        public Builder pushDestroys() {
            this.pushReaction = PushReaction.DESTROY;
            return this;
        }

        public Builder pushBlocks() {
            this.pushReaction = PushReaction.BLOCK;
            return this;
        }

        public Material build() {
            return new Material(this.color, this.isLiquid, this.isSolid, this.blocksMovement, this.isOpaque, this.requiresNoTool, this.canBurn, this.isReplaceable, this.pushReaction);
        }
    }
}