package num.complexwiring.machine.smasher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.TileEntityBase;
import num.complexwiring.core.Logger;

public class TileSmasher extends TileEntityBase implements IFacing {
    private ForgeDirection facing;

    @Override
    public ForgeDirection getFacing() {
        return facing;
    }

    @Override
    public void setFacing(ForgeDirection dir) {
        Logger.debug("My new facing is: " + dir.toString());
        this.facing = dir;
    }

    public void writePacketNBT(NBTTagCompound nbt) {
        nbt.setShort("facing", (short) facing.ordinal());
    }

    public void readPacketNBT(NBTTagCompound nbt) {
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        writePacketNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readPacketNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writePacketNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readPacketNBT(packet.func_148857_g());
    }
}
