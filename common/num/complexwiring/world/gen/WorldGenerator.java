package num.complexwiring.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
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
    public static WorldGenerator INSTANCE = new WorldGenerator();

    private List<ICWGenerator> generators, retrogenerators;
    public boolean retrogen;

    public List<String> retroGeneratedNames;

    public WorldGenerator() {
        loadConfig();
    }

    public void loadConfig() {
        String oreName, decorName;

        generators = new ArrayList<ICWGenerator>();
        retrogenerators = new ArrayList<ICWGenerator>();
        retroGeneratedNames = new ArrayList<String>();

        for (EnumOrePrimary ore : EnumOrePrimary.VALID) {
            oreName = ore.name.substring(3);

            ConfigHandler.conf.addCustomCategoryComment("worldgeneration.primaryores." + oreName, "Default: " + ore.clusterSize + ", true, " + ore.maxY + ", " + ore.minY + ", " + ore.clusterNum + ", false");
            boolean generate = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "generate", true).getBoolean(true);
            int minY = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "minY", ore.minY).getInt(ore.minY);
            int maxY = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "maxY", ore.maxY).getInt(ore.maxY);
            int clusterSize = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "clusterSize", ore.clusterSize).getInt(ore.clusterSize);
            int clusterNum = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "numClusters", ore.clusterNum).getInt(ore.clusterNum);
            boolean retrogen = ConfigHandler.conf.get("worldgeneration.primaryores." + oreName, "retrogen", false).getBoolean(false);

            if (generate){
                OreGenerator generator = new OreGenerator(ore, maxY, minY, clusterNum, clusterSize);
                generators.add(generator);
                if(retrogen){
                    retrogenerators.add(generator);
                    retroGeneratedNames.add(ore.name);
                }
            }
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

            if (generate){
                BiomeDictionary.Type[] biomes = null;
                switch (decor) {
                    case LIMESTONE_ROUGH:
                        biomes = new BiomeDictionary.Type[]{ BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM };
                        break;
                    case DOLOMITE_ROUGH:
                        biomes = new BiomeDictionary.Type[]{ BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FROZEN, BiomeDictionary.Type.MOUNTAIN };
                        break;
                    case ARENITE_ROUGH:
                        biomes = new BiomeDictionary.Type[]{ BiomeDictionary.Type.BEACH, BiomeDictionary.Type.DESERT, BiomeDictionary.Type.WASTELAND };
                        break;
                }
                DecorGenerator generator = new DecorGenerator(decor, maxY, minY, clusterNum, clusterSize, biomes);
                generators.add(generator);
                if(retrogen){
                    retrogenerators.add(generator);
                    retroGeneratedNames.add(decor.name);
                }
            }
        }

        retrogen = !retrogenerators.isEmpty();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (isSurface(world) && !isFlat(world)) {
            generateWorld(random, chunkX, chunkZ, world, false);
        }
    }

    public boolean isSurface(World world) {
        return world.provider.dimensionId == 0 || world.provider.dimensionId > 1;
    }

    public boolean isFlat(World world) {
        return world.provider.terrainType == WorldType.FLAT;
    }

    public void generateWorld(Random rand, int chunkX, int chunkZ, World world, boolean isRetro) {
        if(isRetro && !retrogen) {
            return;
        }

        List<ICWGenerator> generators = this.generators;
        if(isRetro) generators = retrogenerators;

        chunkX *= 16;
        chunkZ *= 16;

        for(ICWGenerator generator : generators) {
            generator.generate(world, chunkX, chunkZ, rand);
        }
    }
}
