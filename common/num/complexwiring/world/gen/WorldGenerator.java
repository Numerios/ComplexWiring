package num.complexwiring.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import num.complexwiring.core.ConfigHandler;
import num.complexwiring.world.decor.EnumDecor;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator {
    List<OreGenerator> primaryOres = new ArrayList<OreGenerator>();
    List<DecorGenerator> decorBlocks = new ArrayList<DecorGenerator>();
    private Random rand;

    public WorldGenerator() {
        GameRegistry.registerWorldGenerator(this, 0);
        rand = new Random();

        String oreName, decorName;
        for (EnumOrePrimary ore : EnumOrePrimary.VALID) {
            oreName = ore.name.substring(3);

            //For now here, so it would be saved into config file after INIT completes
            //TODO: Move somewhere else?
            ConfigHandler.conf.addCustomCategoryComment("WorldGeneration.PrimaryOres." + oreName, "Default: " + ore.clusterSize + ", true, " + ore.maxY + ", " + ore.minY + ", " + ore.clusterNum + ", false");
            boolean generate = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "generate", true).getBoolean(true);
            int minY = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "minY", ore.minY).getInt(ore.minY);
            int mayY = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "maxY", ore.maxY).getInt(ore.maxY);
            int clusterSize = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "clusterSize", ore.clusterSize).getInt(ore.clusterSize);
            int clusterNum = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "numClusters", ore.clusterNum).getInt(ore.clusterNum);
            boolean retrogen = ConfigHandler.conf.get("WorldGeneration.PrimaryOres." + oreName, "retrogen", false).getBoolean(false); //TODO: Retrogen

            if (generate) primaryOres.add(new OreGenerator(ore, mayY, minY, clusterNum, clusterSize));
        }

        for (EnumDecor decor : EnumSet.of(EnumDecor.LIMESTONE_ROUGH, EnumDecor.DOLOMITE_ROUGH, EnumDecor.ARENITE_ROUGH)) {
            decorName = decor.name.substring(5);
            decorName = decorName.substring(0, decorName.length() - 5);

            ConfigHandler.conf.addCustomCategoryComment("WorldGeneration.Decor." + decorName, "Default: 8, true, 80, 40, 32, false");
            boolean generate = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "generate", true).getBoolean(true);
            int minY = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "minY", 40).getInt(40);
            int maxY = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "maxY", 80).getInt(80);
            int clusterSize = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "clusterSize", 8).getInt(8);
            int clusterNum = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "numClusters", 32).getInt(32);
            boolean retrogen = ConfigHandler.conf.get("WorldGeneration.Decor." + decorName, "retrogen", false).getBoolean(false);

            if (generate) decorBlocks.add(new DecorGenerator(decor, maxY, minY, clusterNum, clusterSize));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        chunkX *= 16;
        chunkZ *= 16;
        if (isSurface(world)) {     //TODO: Add all other ores!
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
}
