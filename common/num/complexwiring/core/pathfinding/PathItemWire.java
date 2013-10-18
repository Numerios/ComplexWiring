package num.complexwiring.core.pathfinding;

import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.vec.Vector3;

public class PathItemWire implements Comparable<PathItemWire> {
    public Vector3 target;
    public ForgeDirection heading;
    public ForgeDirection direction;
    public int distance;
    public boolean isFinished;

    public PathItemWire(Vector3 target, ForgeDirection heading, ForgeDirection direction, int distance) {
        this.target = target;
        this.heading = heading;
        this.direction = direction;
        this.distance = distance;
    }

    public int compareTo(PathItemWire route) {
        return this.distance - route.distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof PathItemWire))
            return false;

        PathItemWire path = (PathItemWire) obj;

        return target.equals(path.target) && heading.equals(path.heading) && direction.equals(path.direction)
                && distance == path.distance && isFinished == path.isFinished;
    }

    @Override
    public int hashCode() {
        int nl = 31;
        int finished = 0;
        if (isFinished)
            finished = 1;

        int hashCode = target.hashCode();
        hashCode *= nl + heading.ordinal();
        hashCode *= nl + direction.ordinal();
        hashCode *= nl + distance;
        hashCode *= nl + finished;
        return hashCode;
    }

    @Override
    public String toString() {
        return "ItemWirePath @ " + target.toString() + " | heading: " + heading.toString() + " | direction: "
                + direction.toString() + " | distance: " + distance;
    }
}
