package num.complexwiring.world.gen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.event.world.ChunkDataEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldHandler {

    public static WorldHandler INSTANCE = new WorldHandler();

    public Map<Integer, ArrayList<ChunkCoordIntPair>> chunksToGenerate = new HashMap<Integer, ArrayList<ChunkCoordIntPair>>();

    @SubscribeEvent
    public void tickEnd(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER) {
            return;
        }

        World world = event.world;
        int dimId = world.provider.dimensionId;

        ArrayList<ChunkCoordIntPair> chunks = chunksToGenerate.get(dimId);

        if (chunks != null && chunks.size() > 0) {
            ChunkCoordIntPair chunkCoordinates = chunks.get(0);

            Random rand = new Random(chunkCoordinates.chunkXPos * chunkCoordinates.chunkZPos ^ world.getSeed());
            WorldGenerator.INSTANCE.generateWorld(rand, chunkCoordinates.chunkXPos, chunkCoordinates.chunkZPos, world, false);
            chunks.remove(0);

            chunksToGenerate.put(dimId, chunks);
        }
    }

    @SubscribeEvent
    public void chunkSaveEvent(ChunkDataEvent.Save event) {
        if (WorldGenerator.INSTANCE.retrogen) {
            NBTTagCompound tag = new NBTTagCompound();

            for (String name : WorldGenerator.INSTANCE.retroGeneratedNames) {
                tag.setBoolean(name, true);
            }

            event.getData().setTag("CWWorldGen", tag);
        }
    }

    @SubscribeEvent
    public void chunkLoadEvent(ChunkDataEvent.Load event) {
        int dimId = event.world.provider.dimensionId;
        boolean generate = false;

        NBTTagCompound tag = (NBTTagCompound) event.getData().getTag("CWWorldGen");
        if (tag == null) {
            generate = true;
        } else {
            for (String name : WorldGenerator.INSTANCE.retroGeneratedNames) {
                if (!tag.getBoolean(name)) {
                    generate = true;
                    break;
                }
            }
        }

        if (generate) {
            ArrayList<ChunkCoordIntPair> chunks = chunksToGenerate.get(dimId);
            if (chunks == null) {
                chunks = new ArrayList<ChunkCoordIntPair>();
            }

            chunks.add(event.getChunk().getChunkCoordIntPair());
            chunksToGenerate.put(dimId, chunks);
        }
    }

}
