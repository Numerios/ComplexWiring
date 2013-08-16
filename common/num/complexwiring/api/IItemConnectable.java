package num.complexwiring.api;

import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.core.Wrapper;

/**
 * IItemConnectable is an interface for all tiles that are connecting through
 * the item network in Complex Wiring
 */
public interface IItemConnectable {

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
     * Checks if tile can accept Wrappers (Items carrying ItemStacks)
     * 
     * @param wrapper
     *            The wrapper we want to check
     * @return True if tile accepts wrappers, false if not.
     */
    public boolean canAccept(Wrapper wrapper);

    /**
     * Orders the tile to accept a wrapper
     * 
     * @param wrapper
     *            The accepted wrapper
     */
    public void acceptWrapper(Wrapper wrapper);

    /**
     * Checks if the tile allows connecting on the side
     * 
     * @param side
     *            The side checked
     * @return True if the side allows connecting (is connectable), false if
     *         not.
     */
    public boolean isSideConnectable(ForgeDirection side);
}
