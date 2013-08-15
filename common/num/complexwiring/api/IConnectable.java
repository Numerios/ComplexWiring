package num.complexwiring.api;

import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.core.Wrapper;

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
     * Checks if tile can accept Wrappers (Items carrying ItemStacks)
     * @param wrapper The wrapper we want to check
     * @return True if tile accepts wrappers, false if not.
     */
    public boolean canAccept(Wrapper wrapper);
    
    /**
     * Orders the tile to accept a wrapper
     * @param wrapper The accepted wrapper
     */
    public void acceptWrapper(Wrapper wrapper);

}
