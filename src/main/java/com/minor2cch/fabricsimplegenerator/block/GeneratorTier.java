package com.minor2cch.fabricsimplegenerator.block;

import com.minor2cch.fabricsimplegenerator.config.FSGConfig;
import com.minor2cch.fabricsimplegenerator.config.FSGConfigLoader;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum GeneratorTier implements StringRepresentable {
    IRON(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.IRON), 25, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.IRON), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.IRON), "iron"),
    COPPER(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.COPPER), 13, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.COPPER), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.COPPER), "copper"),
    GOLD(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.GOLD), 50, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.GOLD), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.GOLD), "gold"),
    EMERALD(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.EMERALD), 100, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.EMERALD), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.EMERALD), "emerald"),
    DIAMOND(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.DIAMOND), 200, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.DIAMOND), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.DIAMOND), "diamond"),
    NETHERITE(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.NETHERITE), 400, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.NETHERITE), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.NETHERITE), "netherite"),
    SCULK(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.SCULK), 40, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.SCULK), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.SCULK), "sculk"),
    BIOMASS(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.BIOMASS), 25, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.BIOMASS), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.BIOMASS), "biomass"),
    ENCHANTMENT(FSGConfigLoader.getConfig().getConfigCapacity(FSGConfig.constantTier.ENCHANTMENT), 25, FSGConfigLoader.getConfig().getConfigExtract(FSGConfig.constantTier.ENCHANTMENT), FSGConfigLoader.getConfig().getConfigGenerate(FSGConfig.constantTier.ENCHANTMENT), "enchantment")
    ;
    /* 参考 Tech Reborn
        発電機 Generator:10E/t 1アイテム分の燃焼で500E(50tick) fuelTime*2.5がエネルギーの総量。fuelTime/4が燃焼時間。 1tickのアイテムの場合石炭で4000Eが最適なバランス
        熱発電機 Thermal Generator: 16E/t 1バケツ分の燃焼で60kE
        プラズマ発電 Plasma Generator: 400E/t 1バケツ分で8.192ME
        核融合 Fusion Reactor: 6コイルで16.3kE/t、7コイルで28.5kE/t 重水素+三重水素/ヘリウム3 総計6コイルで33.5ME、7コイルで58.3ME
        水車 Water Mill: 0.1E*隣接する水源数(上限4)/t
        風車 Wind Mill: 2~2.5E(雷雨時は2.5E)/t Y>64に設置する必要あり
        半流動体発電機 Semi Fluid Generator　8E/t 総計は生物燃料は6kE、石油は16kE、ナトリウムは30kE、リチウムは60kE
        ドラゴンの卵エネルギー抽出器 Dragon Egg Syphon 4E/t
        太陽電池 Solar Panel
            基本 0~3E/t
            上級 0~12E/t
            工業用 4~24E/t
            究極 8~48E/t
            クァンタム 32~512E/t
        避雷針 256E/bolt
        ガスタービン Gas Turbine 16E/t 総計は水素は15kE、エテンは45kE
        ディーゼル発電機 128E/t 総計はニトロ燃料は24kE、ニトロ石炭燃料は28kE、ディーゼルは128kE、ニトロディーゼルは400kE
    */
    public final long CAPACITY;
    public final long MAX_INSERT;
    public final long MAX_EXTRACT;
    public final long TICK_GENERATE;
    public final String id;
    public static final Codec<GeneratorTier> CODEC = StringRepresentable.fromValues(GeneratorTier::values);
    GeneratorTier(final long CAPACITY, final long MAX_INSERT, final long MAX_EXTRACT, final long TICK_GENERATE, final String id){
        this.CAPACITY = Math.max(CAPACITY, 1);
        this.MAX_INSERT = MAX_INSERT;
        this.MAX_EXTRACT = Math.max(MAX_EXTRACT, 0);
        this.TICK_GENERATE = Math.max(TICK_GENERATE, 0);
        this.id = id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}
