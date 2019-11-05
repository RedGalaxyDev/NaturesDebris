package modernity.common.area.core;

import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.stream.LongStream;

@OnlyIn( Dist.CLIENT )
class EmptyAreaReferenceChunk implements IAreaReferenceChunk {
    public final int x;
    public final int z;

    EmptyAreaReferenceChunk( int x, int z ) {
        this.x = x;
        this.z = z;
    }

    @Override
    public boolean hasReference( long ref ) {
        return false;
    }

    @Override
    public boolean hasReferences() {
        return false;
    }

    @Override
    public LongStream referenceStream() {
        return LongStream.empty();
    }

    @Override
    public ChunkPos getPos() {
        return new ChunkPos( x, z );
    }
}
