package com.minor2cch.fabricsimplegenerator;

import com.minor2cch.fabricsimplegenerator.config.FSGConfigLoader;
import com.minor2cch.fabricsimplegenerator.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FabricSimpleGenerator implements ModInitializer {
    public static final String MOD_ID = "fabric_simple_generator";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        FSGConfigLoader.load();
        FSGBlockEntityTypes.init();
        FSGBlocks.init();
        FSGItems.init();
        FSGCreativeModeTabs.init();
        FSGStats.init();
        FSGMenus.init();
    }
    public static Identifier of(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
