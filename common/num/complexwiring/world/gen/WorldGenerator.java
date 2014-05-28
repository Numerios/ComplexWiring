package num.complexwiring.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import num.complexwiring.world.decor.EnumDecor;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.Random;

public class WorldGenerator implements IWorldGenerator {
    private Random rand;

    public WorldGenerator() {
        GameRegistry.registerWorldGenerator(this, 0);
        rand = new Random();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        chunkX *= 16;
        chunkZ *= 16;
        if (isSurface(world)) {
            for (EnumOrePrimary primaryOre : EnumOrePrimary.VALID) {
                new OreGenerator(primaryOre).generate(world, chunkX, chunkZ, rand);
            }
            new DecorGenerator(EnumDecor.LIMESTONE_ROUGH, 80, 36, 5, 32).generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MUSHROOM);
            new DecorGenerator(EnumDecor.DOLOMITE_ROUGH, 96, 48, 5, 32).generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FROZEN, BiomeDictionary.Type.MOUNTAIN);
            new DecorGenerator(EnumDecor.ARENITE_ROUGH, 64, 20, 5, 32).generate(world, chunkX, chunkZ, rand, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.DESERT, BiomeDictionary.Type.WASTELAND);
        }
    }

    public boolean isSurface(World world) {
        return world.provider.dimensionId == 0 || world.provider.dimensionId > 1;
    }
}
