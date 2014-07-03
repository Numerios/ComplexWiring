package num.complexwiring.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.Random;

public class OreGenerator implements ICWGenerator {
    public final EnumOrePrimary ore;

    private final int maxY;
    private final int minY;
    private final int clusterNum;
    private final int clusterSize;

    public OreGenerator(EnumOrePrimary ore, int maxY, int minY, int clusterNum, int clusterSize) {
        this.ore = ore;
        this.maxY = maxY;
        this.minY = minY;
        this.clusterNum = clusterNum;
        this.clusterSize = clusterSize;
    }

    public void generate(World world, int chunkX, int chunkZ, Random rand) {
        for (int i = 0; i < clusterNum; i++) {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(Math.max(maxY - minY, 0) + minY);
            int z = chunkZ + rand.nextInt(16);
            generateMinable(world, rand, x, y, z);
        }
        world.getChunkFromBlockCoords(chunkX, chunkZ).setChunkModified();
    }

    public boolean generateMinable(World world, Random rand, int x, int y, int z) {
        WorldGenMinable minable = new WorldGenMinable(ModuleWorld.orePrimary, ore.meta, clusterSize, Blocks.stone);
        return world.getChunkProvider().chunkExists(x >> 4, z >> 4) && minable.generate(world, rand, x, y, z);
    }
}
