/*
 * This file belongs to the missing files in Forge Userdev (gradle generates an empty directory somehow).
 * This file is under copyright of forge and does not belong to the modernity.
 *
 * Date: 6 - 11 - 2019
 */

package net.minecraftforge.userdev;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ILaunchHandlerService;
import cpw.mods.modlauncher.api.ITransformingClassLoader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;
import net.minecraftforge.fml.loading.LibraryFinder;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static net.minecraftforge.fml.loading.LogMarkers.*;

public class FMLDevServerLaunchProvider extends FMLCommonLaunchHandler implements ILaunchHandlerService {
    private static final Logger LOGGER = LogManager.getLogger();
    private Path compiledClasses;
    private Path resources;

    @Override
    public String name() {
        return "fmldevserver";
    }

    @Override
    public Path getForgePath( final String mcVersion, final String forgeVersion, final String forgeGroup ) {
        // In forge dev, we just find the path for ForgeVersion for everything
        compiledClasses = LibraryFinder.findJarPathFor( "net/minecraftforge/versions/forge/ForgeVersion.class", "forge" );
        resources = LibraryFinder.findJarPathFor( "assets/minecraft/lang/en_us.json", "mcassets" );
        return compiledClasses;
    }

    @Override
    public Path[] getMCPaths( final String mcVersion, final String mcpVersion, final String forgeVersion, final String forgeGroup ) {
        // In forge dev, we just find the path for ForgeVersion for everything
        return new Path[] { compiledClasses, resources };
    }

    @Override
    public Callable<Void> launchService( String[] arguments, ITransformingClassLoader launchClassLoader ) {
        return () -> {
            LOGGER.debug( CORE, "Launching minecraft in {} with arguments {}", launchClassLoader, arguments );
            beforeStart( launchClassLoader );
            launchClassLoader.addTargetPackageFilter( getPackagePredicate() );
            Thread.currentThread().setContextClassLoader( launchClassLoader.getInstance() );
            Class.forName( "net.minecraft.server.MinecraftServer", true, launchClassLoader.getInstance() ).getMethod( "main", String[].class ).invoke( null, (Object) arguments );
            return null;
        };
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void setup( IEnvironment environment, final Map<String, ?> arguments ) {
        // we're injecting forge into the exploded dir finder
        final Path forgemodstoml = LibraryFinder.findJarPathFor( "META-INF/mods.toml", "forgemodstoml" );
        ( (Map<String, List<Pair<Path, List<Path>>>>) arguments ).computeIfAbsent( "explodedTargets", a -> new ArrayList<>() ).
                add( Pair.of( forgemodstoml, Collections.singletonList( compiledClasses ) ) );

        processModClassesEnvironmentVariable( (Map<String, List<Pair<Path, List<Path>>>>) arguments );
    }

    @Override
    public Dist getDist() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    protected String getNaming() {
        return "srg";
    }

}
