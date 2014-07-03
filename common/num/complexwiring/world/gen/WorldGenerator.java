package num.complexwiring.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import num.complexwiring.core.ConfigHandler;
import num.complexwiring.world.decor.light.EnumLightDecor;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator {
    List<OreGenerator> primaryOres;
    List<DecorGenerator> decorBlocks;
    private Random rand;
    public static WorldGenerator INSTANCE = new WorldGenerator();

    public WorldGenerator() {
        rand = new Random();

        loadConfig();
    }

    public void loadConfig() {
        String oreName, decorName;
        primaryOres = new ArrayList<OreGenerator>();
        decorBlocks = new ArrayList<DecorGenerator>();

        for (EnumOrePrimary ore : EnumOrePrimary.VALID) {
            oreName = ore.name.substring(3);

            ConfigHandler.conf.addCustomCategoryComment("worldgeneration.primaryores." + oreName, "Default: " + ore.clusterSize + ", true, " + ore.maxY + ", " + ore.minY + ", " + ore.clusterNum + ", false");
            boolean generate = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "generate", true).getBoolean(true);
            int minY = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "minY", ore.minY).getInt(ore.minY);
            int mayY = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "maxY", ore.maxY).getInt(ore.maxY);
            int clusterSize = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "clusterSize", ore.clusterSize).getInt(ore.clusterSize);
            int clusterNum = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "numClusters", ore.clusterNum).getInt(ore.clusterNum);
            boolean retrogen = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "retrogen", false).getBoolean(false); //TODO: Retrogen

            if (generate) primaryOres.add(new OreGenerator(ore, mayY, minY, clusterNum, clusterSize));
        }

        for (EnumLightDecor decor : EnumSet.of(EnumLightDecor.LIMESTONE_ROUGH, EnumLightDecor.DOLOMITE_ROUGH, EnumLightDecor.ARENITE_ROUGH)) {
            decorName = decor.name.substring(5);
            decorName = decorName.substring(0, decorName.length() - 5);

            ConfigHandler.conf.addCustomCategoryComment("worldgeneration.decor." + decorName, "Default: 40, true, 80, 40, 5, false");
            boolean generate = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "generate", true).getBoolean(true);
            int minY = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "minY", 40).getInt(40);
            int maxY = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "maxY", 80).getInt(80);
            int clusterSize = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "clusterSize", 40).getInt(40);
            int clusterNum = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "numClusters", 5).getInt(5);
            boolean retrogen = ConfigHandler.conf.get("worldgeneration.decor." + decorName, "retrogen", false).getBoolean(false);

            if (generate) decorBlocks.add(new DecorGenerator(decor, maxY, minY, clusterNum, clusterSize));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        chunkX *= 16;
        chunkZ *= 16;
        if (isSurface(world) && !isFlat(world)) {
            for (OreGenerator primaryOreGenerator : primaryOres) {
                primaryOreGenerator.generate(world, chunkX, chunkZ, rand);
            }

            for (DecorGenerator decor : decorBlocks) {
                switch (decor.decor) {
                    case LIMESTONE_ROUGH:
                        decor.generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM);
                        break;
                    case DOLOMITE_ROUGH:
                        decor.generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FROZEN, BiomeDictionary.Type.MOUNTAIN);
                        break;
                    case ARENITE_ROUGH:
                        decor.generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.DESERT, BiomeDictionary.Type.WASTELAND);
                        break;
                }
            }
        }
    }

    public boolean isSurface(World world) {
        return world.provider.dimensionId == 0 || world.provider.dimensionId > 1;
    }

    public boolean isFlat(World world) {
        return world.provider.terrainType == WorldType.FLAT;
    }
}
