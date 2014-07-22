package num.complexwiring.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.Random;

public class NetherOreGenerator implements ICWGenerator {
    public final EnumOrePrimary ore;

    private final int maxY, minY, clusterNum, clusterSize;

    public NetherOreGenerator(EnumOrePrimary ore, int maxY, int minY, int clusterNum, int clusterSize) {
        this.ore = ore;
        this.maxY = maxY;
        this.minY = minY;
        this.clusterNum = clusterNum;
        this.clusterSize = clusterSize;
    }

    public void generate(World world, int chunkX, int chunkZ, Random rand, boolean isOverworld) {
        if (isOverworld) return;

        for (int i = 0; i < clusterNum; i++) {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(Math.max(maxY - minY, 0)) + minY;
            int z = chunkZ + rand.nextInt(16);
            if (world.getChunkProvider().chunkExists(x >> 4, z >> 4)) {
                generateMinable(world, rand, x, y, z);
            }
        }
        world.getChunkFromBlockCoords(chunkX, chunkZ).setChunkModified();
    }

    public boolean generateMinable(World world, Random rand, int x, int y, int z) {
        WorldGenMinable minable = new WorldGenMinable(ModuleWorld.orePrimary, ore.meta, clusterSize, Blocks.netherrack);
        return minable.generate(world, rand, x, y, z);
    }
}
