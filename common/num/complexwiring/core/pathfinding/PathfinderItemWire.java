package num.complexwiring.core.pathfinding;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.core.Wrapper;
import num.complexwiring.util.InventoryHelper;
import num.complexwiring.util.MCVector3;

public class PathfinderItemWire {
    ForgeDirection initial = ForgeDirection.UNKNOWN;
    PathItemWire result;
    World world;
    Wrapper wrapper;
    HashSet<MCVector3> itemWireNet = new HashSet<MCVector3>();
    PriorityQueue<PathItemWire> queue = new PriorityQueue<PathItemWire>();
    public static final int QUEUE_MAX = 65536;

    public PathfinderItemWire(World world, Wrapper wrapper) {
        this.world = world;
        this.wrapper = wrapper;
    }

    public void addNode(MCVector3 vec3, ForgeDirection direction, ForgeDirection heading, int distance) {
        TileEntity tile = vec3.toTile();
        if (tile == null)
            return;
        if (tile instanceof IInventory && InventoryHelper.canInsertToInventory(vec3, wrapper.getContents())) {
            PathItemWire path = new PathItemWire(vec3, direction, heading, distance);
            path.isFinished = true;
            this.queue.add(path);
            return;
        } else if (tile instanceof IItemConnectable){
            PathItemWire path = new PathItemWire(vec3, direction, heading, distance + 1);
            path.isFinished = true;
            this.queue.add(path);
            return;
        } 
        if (this.itemWireNet.contains(vec3))
            return;
        this.itemWireNet.add(vec3);
        this.queue.add(new PathItemWire(vec3, direction, heading, distance));
    }

    public ForgeDirection findPath(MCVector3 vec3, List<ForgeDirection> sides) {
        for (ForgeDirection side : sides) {
            MCVector3 pos = vec3.getNeighbour(side);
            addNode(pos, side, side, side == this.initial ? 0 : 1);
        }

        while (this.queue.size() > 0 && this.queue.size() < QUEUE_MAX) {
            PathItemWire path = (PathItemWire) this.queue.poll();
            if (path.isFinished) {
                this.result = path;
                return path.heading;
            }

            for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                MCVector3 pos = vec3.getNeighbour(side);
                addNode(pos, path.heading, side, path.distance + 1);
            }
        }
        return ForgeDirection.UNKNOWN;
    }

    public MCVector3 getResultPoint() {
        return this.result.target;
    }
}
