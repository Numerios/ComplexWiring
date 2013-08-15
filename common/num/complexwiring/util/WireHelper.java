package num.complexwiring.util;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IConnectable;

public class WireHelper {
    public static boolean getConnection(ForgeDirection side, IConnectable connectable) {
        TileEntity tile = MCVector3.get((TileEntity) connectable).getNeighbour(side).toTile();
        if (tile instanceof IConnectable) { // CW connections
            return true;
        } else if (tile instanceof IInventory) { // default MC connections
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<ForgeDirection> getAllConnections(IConnectable connectable) {
        ArrayList<ForgeDirection> connected = new ArrayList<ForgeDirection>();
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (WireHelper.getConnection(side, connectable)) {
                connected.add(side);
            }
        }
        return connected;
    }
}
