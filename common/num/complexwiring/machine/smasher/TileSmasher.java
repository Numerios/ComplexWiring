package num.complexwiring.machine.smasher;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.tile.TileEntityBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.base.EnumDust;
import num.complexwiring.core.Logger;

public class TileSmasher extends TileEntityBase implements IFacing {
    private ForgeDirection facing;
    private float progress;
    private static float PROGRESS_PER_TICK = 0.005F;

    @Override
    public void update() {
        if (!world().isRemote) {
            Vector3 facingVec = pos().clone().step(facing);
            if (facingVec.blockExists(world())) {
                Block block = facingVec.getBlock(world());
                if (progress <= 1F) {
                    if (block == Blocks.iron_ore) {
                        progress += PROGRESS_PER_TICK;
                    }
                } else {
                    progress = 0F;
                    EntityItem entityItem = new EntityItem(world(), pos().getX() + 0.5, pos().getY() + 0.5, pos().getZ() + 0.5, EnumDust.IRON.getIS(2).copy());
                    entityItem.setVelocity(0, 0, 0);
                    entityItem.delayBeforeCanPickup = 0;
                    world().spawnEntityInWorld(entityItem);
                    facingVec.setBlock(world(), Blocks.air);
                }
            }
        }
    }

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
        nbt.setFloat("progress", progress);
    }

    public void readPacketNBT(NBTTagCompound nbt) {
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));
        this.progress = nbt.getFloat("progress");
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
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readPacketNBT(packet.func_148857_g());
    }

    public float getProgress() {
        return progress;
    }
}
