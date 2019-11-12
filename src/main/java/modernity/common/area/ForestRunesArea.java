package modernity.common.area;

import modernity.api.util.MovingBlockPos;
import modernity.common.area.core.Area;
import modernity.common.area.core.AreaBox;
import modernity.common.area.core.IServerTickableArea;
import modernity.common.area.core.ServerWorldAreaManager;
import modernity.common.block.MDBlocks;
import modernity.common.block.base.AbstractPortalFrameBlock;
import modernity.common.block.base.PortalCornerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ForestRunesArea extends Area implements IServerTickableArea {
    private static final BlockPos[] CORNERS = {
        new BlockPos( 4, 3, 4 ),
        new BlockPos( 4, 3, 7 ),
        new BlockPos( 7, 3, 7 ),
        new BlockPos( 7, 3, 4 )
    };

    private final PortalCornerBlock.State[] states = {
        PortalCornerBlock.State.EXHAUSTED,
        PortalCornerBlock.State.EXHAUSTED,
        PortalCornerBlock.State.EXHAUSTED,
        PortalCornerBlock.State.EXHAUSTED
    };

    private boolean active;

    public ForestRunesArea( World world, AreaBox box ) {
        super( MDAreas.FOREST_RUNES, world, box );
    }

    public static ForestRunesArea create( World world, AreaBox box ) {
        ForestRunesArea area = new ForestRunesArea( world, box );
        ServerWorldAreaManager
            .get( world )
            .ifPresent( manager -> manager.addArea( area ) );
        return area;
    }

    public static ForestRunesArea get( World world, long ref ) {
        ForestRunesArea[] area = new ForestRunesArea[ 1 ];
        ServerWorldAreaManager
            .get( world )
            .ifPresent( manager -> {
                Area a = manager.getLoadedArea( ref );
                if( a instanceof ForestRunesArea ) {
                    area[ 0 ] = (ForestRunesArea) a;
                }
            } );
        return area[ 0 ];
    }

    public boolean isActive() {
        return active;
    }

    private void activate() {
        active = true;

        MovingBlockPos mpos = new MovingBlockPos();
        for( int x = 4; x <= 7; x++ ) {
            for( int z = 4; z <= 7; z++ ) {
                mpos.setPos( x, 2, z );
                BlockState state = getBlockState( mpos );
                if( state.getBlock() instanceof AbstractPortalFrameBlock ) {
                    setBlockState( mpos, state.with( AbstractPortalFrameBlock.ACTIVE, true ) );
                }
            }
        }
    }

    private void deactivate() {
        active = false;

        MovingBlockPos mpos = new MovingBlockPos();
        for( int x = 4; x <= 7; x++ ) {
            for( int z = 4; z <= 7; z++ ) {
                mpos.setPos( x, 2, z );
                BlockState state = getBlockState( mpos );
                if( state.getBlock() instanceof AbstractPortalFrameBlock ) {
                    setBlockState( mpos, state.with( AbstractPortalFrameBlock.ACTIVE, false ) );
                }
            }
        }
    }

    private void cornerUpdate( PortalCornerBlock.State state ) {
        if( state == PortalCornerBlock.State.ACTIVE ) return;

        int eyes = 0;
        for( int i = 0; i < 4; i++ ) {
            if( states[ i ] == PortalCornerBlock.State.EYE ) {
                eyes++;
            }
        }
        if( eyes == 4 ) {
            activate();
        } else {
            deactivate();
        }
    }

    private byte[] serializeStates() {
        return new byte[] {
            (byte) states[ 0 ].ordinal(),
            (byte) states[ 1 ].ordinal(),
            (byte) states[ 2 ].ordinal(),
            (byte) states[ 3 ].ordinal()
        };
    }

    private void deserializeStates( byte[] b ) {
        for( int i = 0; i < 4; i++ ) {
            if( i >= b.length ) return;
            states[ i ] = PortalCornerBlock.State.values()[ b[ i ] ];
        }
    }

    @Override
    public void write( CompoundNBT nbt, SerializeType type ) {
        if( type == SerializeType.FILESYSTEM ) {
            nbt.putByteArray( "states", serializeStates() );
        }
        nbt.putBoolean( "active", active );
    }

    @Override
    public void read( CompoundNBT nbt, SerializeType type ) {
        if( type == SerializeType.FILESYSTEM ) {
            deserializeStates( nbt.getByteArray( "states" ) );
        }
        active = nbt.getBoolean( "active" );
    }

    @Override
    public void tickServer() {
        for( int i = 0; i < 4; i++ ) {
            BlockPos pos = CORNERS[ i ];
            BlockState state = getBlockState( pos );
            if( state.getBlock() == MDBlocks.PORTAL_CORNER ) {
                PortalCornerBlock.State s = state.get( PortalCornerBlock.STATE );
                if( s != states[ i ] ) {
                    states[ i ] = s;
                    cornerUpdate( s );
                }
            } else if( states[ i ] != null ) {
                states[ i ] = null;
                cornerUpdate( null );
            }
        }
    }
}