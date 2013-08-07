package num.complexwiring.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkEvent;
import num.complexwiring.api.IItemWire;
import num.complexwiring.util.MCVector3;

public class ItemNetwork {
    public Set<IItemWire> wires = new HashSet<IItemWire>();

    public ItemNetwork(IItemWire... itemWires) {
        wires.addAll(Arrays.asList(itemWires));
        ItemNetworkRegistry.getInstance().registerNetwork(this);
    }

    public void refresh() {
        Iterator<IItemWire> iterator = wires.iterator();

        while (iterator.hasNext()) {
            IItemWire itemWire = (IItemWire) iterator.next();

            if (itemWire == null) {
                iterator.remove();
            } else if (((TileEntity) itemWire).isInvalid()) {
                iterator.remove();
            } else {
                itemWire.setNetwork(this);
            }
        }
    }

    public void merge(ItemNetwork network) {
        if (network != null && network != this) {
            ItemNetwork merged = new ItemNetwork();
            merged.wires.addAll(wires);
            merged.wires.addAll(network.wires);
            merged.refresh();
        }
    }

    public void split(IItemWire splitPoint) {
        if (splitPoint instanceof TileEntity) {
            wires.remove(splitPoint);

            TileEntity[] connected = new TileEntity[6];

            for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity tile = MCVector3.get((TileEntity) splitPoint).getNeighbour(side).toTile();

                if (tile != null) {
                    connected[Arrays.asList(ForgeDirection.values()).indexOf(side)] = tile;
                }
            }

            for (int i = 0; i < connected.length; i++) {
                TileEntity connectedA = connected[i];

                if (connectedA instanceof IItemWire) {
                    for (int j = 0; j < connected.length; j++) {
                        TileEntity connectedB = connected[j];

                        if (connectedA != connectedB && connectedB instanceof IItemWire) {

                        }
                    }
                }
            }
        }
    }

    public static class NetworkFinder {
        public MCVector3 target;

        public List<MCVector3> iterated = new ArrayList<MCVector3>();
        public List<MCVector3> ignored = new ArrayList<MCVector3>();

        public NetworkFinder(MCVector3 target, MCVector3... ignored) {
            this.target = target;

            if (ignored != null) {
                this.ignored = Arrays.asList(ignored);
            }
        }

        public void loop(MCVector3 loc) {
            if (loc.toTile() instanceof IItemWire) {
                iterated.add(loc);
            }
            if (iterated.contains(target)) {
                return;
            }
            for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                MCVector3 vec3 = loc.getNeighbour(side);

                if (!iterated.contains(vec3) && !ignored.contains(vec3)) {
                    TileEntity tile = vec3.toTile();

                    if (tile instanceof IItemWire) {
                        loop(vec3);
                    }
                }
            }
        }

        public boolean found(MCVector3 start) {
            loop(start);
            return iterated.contains(target);
        }
    }

    public static class NetworkLoader {
        @ForgeSubscribe
        public void onChunkLoad(ChunkEvent.Load event) {
            if (event.getChunk() != null) {
                for (Object obj : event.getChunk().chunkTileEntityMap.values()) {
                    if (obj instanceof TileEntity) {
                        TileEntity tile = (TileEntity) obj;

                        if (tile instanceof IItemWire) {
                            ((IItemWire) tile).refreshNetwork();
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "[Item Network] " + wires.size() + " wires";
    }

}
