/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package modernity.client;

import modernity.api.IModernityClient;
import modernity.api.util.ISidedTickable;
import modernity.common.Modernity;
import net.minecraft.client.Minecraft;

public class ModernityClient extends Modernity implements IModernityClient {
    private static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void tickSided(ISidedTickable sidedTickable) {
        super.tickSided(sidedTickable);
        if(MC.isOnExecutionThread()) {
            sidedTickable.clientTick();
        }
    }
}
