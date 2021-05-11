package com.iridium.iridiumskyblock.configs;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumskyblock.generators.GeneratorType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * The main configuration of IridiumSkyblock (configuration.yml).
 * Is deserialized automatically on plugin startup and reload.
 */
public class Configuration {

    public String prefix = "<GRADIENT:09C6F9>&lIridiumSkyblock</GRADIENT:045DE9> &8»";
    public String worldName = "IridiumSkyblock";
    public String netherWorldName = "IridiumSkyblock_nether";
    public String islandCreateTitle = "&b&lIsland Created";
    public String islandCreateSubTitle = "&7IridiumSkyblock by Peaches_MLG";
    public String dateTimeFormat = "EEEE, MMMM dd HH:mm:ss";

    public boolean defaultIslandPublic = true;

    public int distance = 151;
    public int schematicPastingDelay = 1;
    public int islandRecalculateInterval = 10;

    public GeneratorSettings generatorSettings = new GeneratorSettings();

    public Map<Integer, Integer> islandTopSlots = ImmutableMap.<Integer, Integer>builder()
            .put(1, 4)
            .put(2, 12)
            .put(3, 14)
            .put(4, 19)
            .put(5, 20)
            .put(6, 21)
            .put(7, 22)
            .put(8, 23)
            .put(9, 24)
            .put(10, 25)
            .build();

    public Map<Integer, Integer> islandWarpSlots = ImmutableMap.<Integer, Integer>builder()
            .put(1, 9)
            .put(2, 11)
            .put(3, 13)
            .put(4, 15)
            .put(5, 17)
            .build();

    /**
     * Settings for the {@link org.bukkit.generator.ChunkGenerator} of IridiumSkyblock.
     * Allows fine-tuning of the {@link com.iridium.iridiumskyblock.generators.OceanGenerator}.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GeneratorSettings {

        public GeneratorType generatorType = GeneratorType.SKYBLOCK;
        public int waterHeight = 95;
        public int minOceanFloorLevel = 10;
        public int maxOceanFloorLevel = 25;
        public XMaterial oceanFloorBottomMaterial = XMaterial.GRAVEL;
        public XMaterial oceanFloorTopMaterial = XMaterial.SAND;

    }

}