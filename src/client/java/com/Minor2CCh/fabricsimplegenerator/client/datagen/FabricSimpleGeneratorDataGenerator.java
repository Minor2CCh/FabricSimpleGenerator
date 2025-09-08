package com.Minor2CCh.fabricsimplegenerator.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricSimpleGeneratorDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FSGModelProvider::new);
        FSGLanguageProvider.addAll(pack);
        pack.addProvider(FSGLootTableProvider::new);
        pack.addProvider(FSGBlockTagProvider::new);
        pack.addProvider(FSGItemTagProvider::new);
        pack.addProvider(FSGRecipeProvider::new);
    }
}
