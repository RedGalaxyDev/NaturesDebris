package modernity.common.net;

import modernity.common.area.core.ClientWorldAreaManager;
import modernity.network.Packet;
import modernity.network.ProcessContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;

public class SAreaUnwatchPacket implements Packet {
    private long refID;
    private DimensionType dimen;

    public SAreaUnwatchPacket( long refID, IWorld world ) {
        this.refID = refID;
        this.dimen = world.getDimension().getType();
    }

    public SAreaUnwatchPacket() {
    }

    @Override
    public void write( PacketBuffer buf ) {
        buf.writeLong( refID );
        buf.writeInt( dimen.getId() );
    }

    @Override
    public void read( PacketBuffer buf ) {
        refID = buf.readLong();
        dimen = DimensionType.getById( buf.readInt() );
    }

    @Override
    public void process( ProcessContext ctx ) {
        ctx.ensureMainThread();
        ClientWorldAreaManager.get().ifPresent( manager -> manager.receiveAreaUnwatch( refID, dimen ) );
    }
}
