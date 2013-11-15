package num.complexwiring.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.api.vec.Vector3;

import java.util.ArrayList;

public class WireHelper {
    public static boolean getConnection(ForgeDirection side, IItemConnectable connectable, World world) {
        TileEntity tile = Vector3.get((TileEntity) connectable).step(side).toTile(world);
        if (tile instanceof IItemConnectable && ((IItemConnectable) tile).isSideConnectable(side)) { // CW connections
            return true;
        } else if (tile instanceof IInventory) { // default MC connections
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<ForgeDirection> getAllConnections(IItemConnectable connectable, World world) {
        ArrayList<ForgeDirection> connected = new ArrayList<ForgeDirection>();
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (WireHelper.getConnection(side, connectable, world)) {
                connected.add(side);
            }
        }
        return connected;
    }
}
