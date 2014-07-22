package num.complexwiring.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.decor.light.EnumLightDecor;

import java.util.Random;

public class DecorGenerator implements ICWGenerator {
    public final EnumLightDecor decor;
    private final int maxY, minY, clusterNum, clusterSize;
    private final BiomeDictionary.Type[] biomeTypes;

    public DecorGenerator(EnumLightDecor decor, int maxY, int minY, int clusterNum, int clusterSize, BiomeDictionary.Type... biomeTypes) {
        this.decor = decor;
        this.maxY = maxY;
        this.minY = minY;
        this.clusterNum = clusterNum;
        this.clusterSize = clusterSize;
        this.biomeTypes = biomeTypes;
    }

    public void generate(World world, int chunkX, int chunkZ, Random rand, boolean isOverworld) {
        if (!isOverworld) return;

        boolean doGenerate = false;
        for (BiomeDictionary.Type type : biomeTypes) {
            if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(chunkX + 8, chunkZ + 8), type)) {
                doGenerate = true;
            }
        }
        if (doGenerate) {
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
    }

    public boolean generateMinable(World world, Random rand, int x, int y, int z) {
        WorldGenMinable minable = new WorldGenMinable(ModuleWorld.decorLight, this.decor.meta, clusterSize, Blocks.stone);
        return minable.generate(world, rand, x, y, z);
    }
}
