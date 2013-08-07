package num.complexwiring.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IConnectable;
import num.complexwiring.api.IItemWire;

public class WireHelper {
    public static boolean getConnection(ForgeDirection side, IConnectable connectable) {
        TileEntity tile = MCVector3.get((TileEntity) connectable).getNeighbour(side).toTile();
        if (tile instanceof IItemWire) {
            return true;
        } else if (tile instanceof IInventory) {
            return true;
        } else {
            return false;
        }
    }
}
