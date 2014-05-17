package num.complexwiring.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface IConnectable {
    /**
     * Checks if this tile is connectable with another tile
     *
     * @param dir Direction of the connection
     * @param obj Tile checked against
     * @return True if this tile can connect
     */
    public boolean canConnect(ForgeDirection dir, Object obj);
}
