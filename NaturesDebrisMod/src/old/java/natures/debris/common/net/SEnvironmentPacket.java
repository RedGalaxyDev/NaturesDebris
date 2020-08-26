/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package natures.debris.common.net;

import modernity.network.IPacket;
import modernity.network.ProcessContext;
import natures.debris.common.environment.event.EnvironmentEventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Packet that sends environment data to the client.
 */
public class SEnvironmentPacket implements IPacket {

    private CompoundNBT nbt;

    public SEnvironmentPacket(EnvironmentEventManager manager) {
        this.nbt = new CompoundNBT();
        manager.write(nbt);
    }

    public SEnvironmentPacket() {

    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void read(PacketBuffer buf) {
        nbt = buf.readCompoundTag();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void process(ProcessContext ctx) {
        ctx.ensureMainThread();

        World world = Minecraft.getInstance().world; // TODO
//        if( world.dimension instanceof IEnvEventsDimension ) {
//            EnvironmentEventManager mngr = ((IEnvEventsDimension) world.dimension).getEnvEventManager();
//            mngr.read( nbt );
//        }
    }
}