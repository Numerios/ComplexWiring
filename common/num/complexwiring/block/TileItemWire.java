package num.complexwiring.block;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.api.IItemWire;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.Wrapper;
import num.complexwiring.core.pathfinding.PathfinderItemWire;
import num.complexwiring.util.EnumColours;
import num.complexwiring.util.InventoryHelper;
import num.complexwiring.util.WireHelper;

import java.util.ArrayList;

public class TileItemWire extends TileEntity implements IItemWire {
    public ArrayList<Wrapper> contents = new ArrayList<Wrapper>();
    public ArrayList<Wrapper> pending = new ArrayList<Wrapper>();
    public EnumColours colour = EnumColours.UNKNOWN;
    private float constant = 0.05F;

    public TileItemWire() {
        super();
        contents = new ArrayList<Wrapper>();
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        manageWrappers();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.getWorldInfo()
                .getVanillaDimension(), getDescriptionPacket());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        Packet132TileEntityData packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 3, nbt);
        packet.isChunkDataPacket = true;
        return packet;
    }

    @Override
    public void onDataPacket(INetworkManager network, Packet132TileEntityData packet) {
        readFromNBT(packet.data);
    }

    @Override
    public boolean canAccept(Wrapper wrapper) {
        return wrapper.getColour() == colour || colour == EnumColours.UNKNOWN
                || wrapper.getColour() == EnumColours.UNKNOWN;
    }

    @Override
    public void acceptWrapper(Wrapper wrapper) {
        wrapper.resetProgress();
        pending.add(wrapper);
    }

    public void manageWrappers() {
        ArrayList<Wrapper> forwarding = new ArrayList<Wrapper>();
        for (Wrapper wrapper : pending) {
            contents.add(wrapper);
        }
        pending.clear();
        for (Wrapper wrapper : contents) {
            wrapper.addProgress(constant);
            if (wrapper.getProgress() >= 1) {
                wrapper.setDirection(new PathfinderItemWire(worldObj, wrapper).findPath(Vector3.get(this),
                        WireHelper.getAllConnections(this, worldObj)));
                Vector3 vec3 = Vector3.get(this).step(wrapper.getDirection());
                TileEntity neighbour = vec3.toTile(worldObj);
                if (neighbour instanceof IInventory && wrapper.hasContents()
                        && InventoryHelper.canInsertToInventory(vec3, wrapper.getContents(), worldObj)) {
                    forwarding.add(wrapper);
                    InventoryHelper.insertToInventory((IInventory) neighbour, wrapper.getContents());
                } else if (neighbour instanceof IItemConnectable && ((IItemConnectable) neighbour).canAccept(wrapper)) {
                    forwarding.add(wrapper);
                    ((IItemConnectable) neighbour).acceptWrapper(wrapper);
                }
            }
        }
        for (Wrapper wrapper : forwarding) {
            contents.remove(wrapper);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("COLOUR", colour.toInt());
        NBTTagList tagList = new NBTTagList();
        for (Wrapper wrapper : contents) {
            tagList.appendTag(Wrapper.getWrapperNBT(wrapper));
        }
        nbt.setTag("CONTENTS_LIST", tagList);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        colour = EnumColours.fromInt(nbt.getInteger("COLOUR"));
        NBTTagList tagList = (NBTTagList) nbt.getTag("CONTENTS_LIST");
        contents = new ArrayList<Wrapper>();
        for (int i = 0; i < tagList.tagCount(); i++) {
            contents.add(Wrapper.getFromNBT((NBTTagCompound) tagList.tagAt(i)));
        }
    }

    @Override
    public boolean isSideConnectable(ForgeDirection side) {
        TileEntity tile = (TileEntity) Vector3.get(this).step(side).toTile(worldObj);
        if (tile instanceof TileItemHub) {
            if (!((TileItemHub) tile).getConnection(side.getOpposite())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean getConnection(ForgeDirection side) {
        return WireHelper.getConnection(side, this, worldObj);
    }

    @Override
    public World getWorld() {
        return this.getWorldObj();
    }
}
