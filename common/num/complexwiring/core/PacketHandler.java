package num.complexwiring.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import num.complexwiring.lib.Reference;
import num.complexwiring.machine.TileEntityMachineBasic;

import java.io.*;

public class PacketHandler implements IPacketHandler {
    public static final String CHANNEL = Reference.MOD_ID;
    public static PacketHandler instance;

    public PacketHandler() {
        instance = this;
    }

    public static Packet getPacket(TileEntity tile, Object... data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            //TODO ID!
            dos.writeInt(tile.xCoord);
            dos.writeInt(tile.yCoord);
            dos.writeInt(tile.zCoord);
            for (Object obj : data) {
                if (obj instanceof Integer) {
                    dos.writeInt((Integer) obj);
                }
            }
            NBTTagCompound nbt = new NBTTagCompound();
            tile.writeToNBT(nbt);
            writeNBT(nbt, dos);
        } catch (IOException e) {
        }
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = CHANNEL;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        packet.isChunkDataPacket = true;
        return packet;
    }

    public static NBTTagCompound readNBT(DataInputStream in) {
        try {
            short length = in.readShort();
            if (length < 0) {
                return null;
            } else {
                byte[] compressed = new byte[length];
                in.readFully(compressed, 0, length);
                return CompressedStreamTools.decompress(compressed);
            }
        } catch (IOException e) {
            FMLCommonHandler.instance().raiseException(e, "PacketHandler.readNBT", true);
            return null;
        }
    }

    public static void writeNBT(NBTTagCompound nbt, DataOutputStream out) {
        try {
            if (nbt == null) {
                out.writeShort(-1);
            } else {
                byte[] compressed = CompressedStreamTools.compress(nbt);
                out.writeShort((short) compressed.length);
                out.write(compressed);
            }
        } catch (IOException e) {
            FMLCommonHandler.instance().raiseException(e, "PacketHandler.writeNBT", true);
        }
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.data != null && packet.data.length <= 0) {
            Logger.debug("INVALID PACKET!");
            return;
        }
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(packet.data));
        int x, y, z;
        try {
            x = is.readInt();
            y = is.readInt();
            z = is.readInt();
            Logger.debug("ASSIGNING XYZ COORDS!");
        } catch (IOException e) {
            return;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Logger.debug("Couldn't close the input! " + e.getMessage());
            }
        }
        NBTTagCompound nbt = readNBT(is);

        if (((EntityPlayer) player).worldObj == null) {
            Logger.warn("PacketHandler got a NULL world while processing a packet");
            return;
        }
        TileEntity tile = ((EntityPlayer) player).worldObj.getBlockTileEntity(x, y, z);
        if (tile == null) {
            Logger.warn("PacketHandler got a NULL tile while processing a packet");
            return;
        }
        tile.readFromNBT(nbt);
        if (tile instanceof TileEntityMachineBasic) {
            try {
                ((TileEntityMachineBasic) tile).handlePacket(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
