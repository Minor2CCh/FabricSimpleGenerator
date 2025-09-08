package com.Minor2CCh.fabricsimplegenerator.registry;

import com.Minor2CCh.fabricsimplegenerator.FabricSimpleGenerator;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FSGItemTags {
    public static final TagKey<Item> SCULK_INGREDIENT_BIT = modOf("sculk_ingredient_bit");  // 10
    public static final TagKey<Item> SCULK_INGREDIENT_LOW = modOf("sculk_ingredient_low"); // 100
    public static final TagKey<Item> SCULK_INGREDIENT_MIDDLE = modOf("sculk_ingredient_middle"); // 500
    public static final TagKey<Item> SCULK_INGREDIENT_HIGH = modOf("sculk_ingredient_high"); // 1500
    public static final TagKey<Item> SCULK_INGREDIENT_SPECIAL = modOf("sculk_ingredient_special"); // 1500
    public static final TagKey<Item> SCULK_INGREDIENT_EXTRA = modOf("sculk_ingredient_extra"); // 12000
    public static final TagKey<Item> SCULK_INGREDIENT_ULTIMATE = modOf("sculk_ingredient_ultimate"); // 24000
    public static final TagKey<Item> AZALEAS = modOf("azaleas");
    private static TagKey<Item> modOf(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(FabricSimpleGenerator.MOD_ID, id));
    }
}
