package num.complexwiring.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.Random;

public class WorldGenerator implements IWorldGenerator {
    private Random rand;

    public WorldGenerator() {
        GameRegistry.registerWorldGenerator(this);
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
        }
    }

    public boolean isSurface(World world) {
        return world.provider.dimensionId == 0 || world.provider.dimensionId > 1;
    }
}
