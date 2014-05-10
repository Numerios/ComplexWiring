package num.complexwiring.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.Random;

public class OreGenerator {
    public final EnumOrePrimary ore;

    public OreGenerator(EnumOrePrimary ore) {
        this.ore = ore;
    }

    public void generate(World world, int chunkX, int chunkZ, Random rand) {
        for (int i = 0; i < this.ore.chance; i++) {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(Math.max(this.ore.maxY - this.ore.minY, 0) + this.ore.minY);
            int z = chunkZ + rand.nextInt(16);
            generateMinable(world, rand, x, y, z);
        }
        world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }

    public boolean generateMinable(World world, Random rand, int x, int y, int z){
        WorldGenMinable minable = new WorldGenMinable(ModuleWorld.orePrimary, this.ore.meta, this.ore.clusterSize, Blocks.stone);
        return minable.generate(world, rand, x, y, z);
    }
}
