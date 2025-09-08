package com.Minor2CCh.fabricsimplegenerator;

import com.Minor2CCh.fabricsimplegenerator.config.FSGConfigLoader;
import com.Minor2CCh.fabricsimplegenerator.registry.*;
import net.fabricmc.api.ModInitializer;

public class FabricSimpleGenerator implements ModInitializer {
    public static final String MOD_ID = "fabric_simple_generator";

    @Override
    public void onInitialize() {
        FSGConfigLoader.load();
        FSGBlockEntityTypes.init();
        FSGBlocks.init();
        FSGItems.init();
        FSGItemGroups.init();
        FSGStats.init();
        FSGScreenHandlers.init();
    }
}
