package num.complexwiring.api;

import net.minecraftforge.common.ForgeDirection;

/**
 * IConnectable is an interface for all connectable tiles in ComplexWiring
 */
public interface IConnectable {

    /**
     * Checks if the tile is connected on checked side
     * 
     * @param side
     *            The side that is checked
     * @return True if the tile is connected to another on given side, false if
     *         not.
     */
    public boolean getConnection(ForgeDirection side);

    /**
     * Sets connection of the tile to another on side
     * 
     * @param side
     *            The side to connect to
     * @param connected
     *            True to connect, false to disconnect
     */
    public void setConnection(ForgeDirection side, boolean connected);

}
