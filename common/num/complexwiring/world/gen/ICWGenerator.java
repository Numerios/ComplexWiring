package num.complexwiring.world.gen;

import net.minecraft.world.World;

import java.util.Random;

public interface ICWGenerator {
    public void generate(World world, int chunkX, int chunkZ, Random rand, boolean isOverworld);
}
