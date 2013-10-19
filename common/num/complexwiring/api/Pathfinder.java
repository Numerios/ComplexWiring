package num.complexwiring.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.vec.Vector3;

public class Pathfinder {
    public final Set<Vector3> open, closed;
    public Set<Vector3> results;
    // Usually bestCost = f and totalCost = g, but I think this is more clear
    public final HashMap<Vector3, Double> bestCost, totalCost;
    public final HashMap<Vector3, Vector3> navMap;
    public final Vector3 target;
    private World world;

    public Pathfinder(World world, Vector3 target) {
        this.world = world;
        this.target = target;

        this.open = new HashSet<>();
        this.closed = new HashSet<>();
        this.results = new HashSet<>();

        this.bestCost = new HashMap<>();
        this.totalCost = new HashMap<>();
        this.navMap = new HashMap<>();
    }

    public boolean find(Vector3 start) {
        this.open.add(start);
        this.bestCost.put(start, 0D);
        this.totalCost.put(start, this.bestCost.get(start) + start.distance(this.target));

        while (!this.open.isEmpty()) {
            Vector3 thisNode = null;
            double lowestTotalCost = 0;

            for (Vector3 node : this.open) {
                if (this.totalCost.get(node) < lowestTotalCost | thisNode == null) {
                    thisNode = node;
                    lowestTotalCost = this.totalCost.get(node);
                }
            }

            if (thisNode == null) {
                break;
            }

            if (thisNode.equals(this.target)) {
                this.results = reconstruct(this.navMap, this.target);
                return true;
            }

            this.open.remove(thisNode);
            this.closed.add(thisNode);

            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                Vector3 neighbour = this.target.clone().step(dir);

               // if (neighbour.toTile(world) instanceof IItemConnectable) {
                    double tentativeTotalCost = this.bestCost.get(thisNode) + thisNode.distance(neighbour);

                    if (!this.closed.contains(neighbour) || !this.open.contains(neighbour)
                            || tentativeTotalCost < this.bestCost.get(neighbour)) {
                        this.navMap.put(neighbour, thisNode);
                        this.bestCost.put(neighbour, tentativeTotalCost);
                        this.totalCost.put(neighbour, this.bestCost.get(neighbour) + neighbour.distance(this.target));
                        this.open.add(neighbour);
                    }
               // }
            }
        }
        return false;

    }

    private Set<Vector3> reconstruct(HashMap<Vector3, Vector3> map, Vector3 node) {
        Set<Vector3> path = new HashSet<Vector3>();
        path.add(node);

        if (map.containsKey(node)) {
            path.addAll(reconstruct(map, map.get(node)));
        }
        return path;
    }
}
