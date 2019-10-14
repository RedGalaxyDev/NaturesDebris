package modernity.common.loot;

import modernity.common.loot.func.MulCornerCount;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;

/**
 * Class that handles all registring that has something to do with loot tables.
 */
public final class MDLootTables {
    private MDLootTables() {
    }

    public static void register() {
        LootFunctionManager.registerFunction( new MulCornerCount.Serializer() );
    }
}