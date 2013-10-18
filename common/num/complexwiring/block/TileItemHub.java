package num.complexwiring.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.core.Wrapper;
import num.complexwiring.core.pathfinding.PathfinderItemWire;
import num.complexwiring.util.EnumColours;
import num.complexwiring.util.InventoryHelper;
import num.complexwiring.util.MCVector3;
import num.complexwiring.util.WireHelper;

public class TileItemHub extends TileEntity implements IItemConnectable {
    // a prototype of an itemHub
    // this one just pulls the first possible itemstack out
    public ArrayList<Wrapper> contents = new ArrayList<Wrapper>();
    public ArrayList<Wrapper> pending = new ArrayList<Wrapper>();
    public boolean forceUpdate;
    private short powered = 0;
    public ForgeDirection facing = ForgeDirection.DOWN;
    private float constant = 0.05F;
    private Random random = new Random();

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        Packet132TileEntityData packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 3,
                nbttagcompound);
        packet.isChunkDataPacket = true;
        return packet;
    }

    @Override
    public void onDataPacket(INetworkManager manager, Packet132TileEntityData packet) {
        readFromNBT(packet.data);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void updateEntity() {
        if (forceUpdate)
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            powered++;
        } else {
            powered = 0;
        }
        if (powered >= 20) {
            extract();
            powered = 0;
        }
        ArrayList<Wrapper> forwarding = new ArrayList<Wrapper>();
        for (Wrapper wrapper : pending) {
            contents.add(wrapper);
        }
        pending.clear();
        for (Wrapper wrapper : contents) {
            wrapper.setDirection(new PathfinderItemWire(worldObj, wrapper).findPath(MCVector3.get(this),
                    WireHelper.getAllConnections(this)));
            wrapper.addProgress(constant);
            if (wrapper.getProgress() >= 1) {
                MCVector3 vec3 = MCVector3.get(this);
                vec3.getNeighbour(wrapper.getDirection());
                TileEntity neighbour = vec3.toTile();
                if (neighbour instanceof IInventory && wrapper.hasContents()
                        && InventoryHelper.canInsertToInventory(vec3, wrapper.getContents())) {
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

    public void extract() {
        TileEntity tile = MCVector3.get(this).getNeighbour(facing).toTile();
        if (tile instanceof IInventory) {
            if (InventoryHelper.canExtractFirstFromInventory((IInventory) tile)) {
                ItemStack is = InventoryHelper.extractFirstFromInventory((IInventory) tile);
                TileEntity oppositeTile = MCVector3.get(this).getNeighbour(facing.getOpposite()).toTile();
                if (oppositeTile instanceof IItemConnectable) {
                    ((IItemConnectable) oppositeTile).acceptWrapper(new Wrapper(is, EnumColours.UNKNOWN, facing
                            .getOpposite()));
                } else if (oppositeTile instanceof IInventory
                        && InventoryHelper.canInsertToInventory((IInventory) oppositeTile, is)) {
                    InventoryHelper.insertToInventory((IInventory) oppositeTile, is);
                } else {

                    double offsetX = 0.50D + random.nextDouble();
                    double offsetY = 0.50D + random.nextDouble();
                    double offsetZ = 0.50D + random.nextDouble();
                    MCVector3 vec3 = MCVector3.get(this).getNeighbour(facing.getOpposite());
                    EntityItem item = new EntityItem(worldObj, vec3.x + offsetX, vec3.y + offsetY, vec3.z + offsetZ, is);

                    item.setVelocity(random.nextFloat() * 0.05F, random.nextFloat() * 0.10F + 0.10F,
                            random.nextFloat() * 0.05F);
                    worldObj.spawnEntityInWorld(item);
                }
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList tagList = new NBTTagList();
        for (Wrapper wrapper : contents) {
            NBTTagCompound wrapperNBT = new NBTTagCompound();
            wrapperNBT.setCompoundTag("WRAPPER", wrapper.getNBT());
            tagList.appendTag(wrapperNBT);
        }
        nbt.setTag("CONTENTS", tagList);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList tagList = (NBTTagList) nbt.getTag("CONTENTS");
        contents = new ArrayList<Wrapper>();
        for (int i = 0; i < tagList.tagCount(); i++) {
            Wrapper newWrapper = new Wrapper(new ItemStack(Block.cobblestone, 1), EnumColours.UNKNOWN,
                    ForgeDirection.UNKNOWN);
            newWrapper.loadNBT(nbt.getCompoundTag("WRAPPER"));
            contents.add(newWrapper);
        }
        forceUpdate = true;
    }

    @Override
    public boolean getConnection(ForgeDirection side) {
        return WireHelper.getConnection(side, this);
    }

    @Override
    public boolean isSideConnectable(ForgeDirection side) {
        return side == facing || side == facing.getOpposite();
    }

    @Override
    public boolean canAccept(Wrapper wrapper) {
        return true;
    }

    @Override
    public void acceptWrapper(Wrapper wrapper) {
        wrapper.resetProgress();
        pending.add(wrapper);
    }

}
