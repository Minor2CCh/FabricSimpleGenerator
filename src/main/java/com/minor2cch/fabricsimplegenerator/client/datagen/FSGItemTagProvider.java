package com.minor2cch.fabricsimplegenerator.client.datagen;

import com.minor2cch.fabricsimplegenerator.registry.FSGItemTags;
import com.minor2cch.fabricsimplegenerator.registry.FSGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class FSGItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public FSGItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }
    private static final String DEEPER_DARKER_MOD_ID = "deeperdarker";
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.builder(FSGItemTags.SCULK_INGREDIENT_BIT).addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_tendrils")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_vines")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_stone")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "cobbled_sculk_stone")));
        this.builder(FSGItemTags.SCULK_INGREDIENT_LOW)
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "soul_dust")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_gleam")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_bone")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "gloomy_sculk")))
        ;
        this.builder(FSGItemTags.SCULK_INGREDIENT_MIDDLE)
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "sculk_jaw")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "gloomy_geyser")))
        ;
        this.builder(FSGItemTags.SCULK_INGREDIENT_SPECIAL);
        this.builder(FSGItemTags.SCULK_INGREDIENT_HIGH)
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath("apothic_ehchanting", "warden_tendril")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "warden_carapace")))
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "heart_of_the_deep")));
        this.builder(FSGItemTags.SCULK_INGREDIENT_EXTRA)
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "soul_crystal")));
        this.builder(FSGItemTags.SCULK_INGREDIENT_ULTIMATE)
                .addOptional(createItemKey(Identifier.fromNamespaceAndPath(DEEPER_DARKER_MOD_ID, "shattered_head")));
        valueLookupBuilder(FSGItemTags.AZALEAS)
                .add(Items.AZALEA)
                .add(Items.FLOWERING_AZALEA);
        valueLookupBuilder(ItemTags.PIGLIN_LOVED)
                .add(FSGItems.GOLD_GENERATOR)
                .add(FSGItems.GOLD_GENERATOR_8X);
    }
    private static ResourceKey<Item> createItemKey(Identifier id) {
        return ResourceKey.create(Registries.ITEM, id);
    }
}
