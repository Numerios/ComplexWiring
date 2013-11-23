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
import net.minecraft.world.World;

import java.io.*;

public class PacketHandler implements IPacketHandler {
    public static final String CHANNEL = "CW";
    public static PacketHandler instance;

    public PacketHandler() {
        instance = this;
    }

    public static Packet getPacket(TileEntity tile) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            dos.writeInt(tile.xCoord);
            dos.writeInt(tile.yCoord);
            dos.writeInt(tile.zCoord);
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

    private static TileEntity handlePacket(World world, boolean isSomething, DataInputStream is) {
        int x, y, z;
        try {
            x = is.readInt();
            y = is.readInt();
            z = is.readInt();
        } catch (IOException e) {
            FMLCommonHandler.instance().raiseException(e, "PacketHandler.handlePacket", false);
            return null;
        }
        NBTTagCompound nbt = readNBT(is);

        if (world == null) {
            Logger.warn("PacketHandler.handlePacket got a NULL world while processing a packet");
            return null;
        }
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile == null) {
            Logger.warn("PacketHandler.handlePacket got a NULL tile while processing a packet");
            return null;
        }
        tile.readFromNBT(nbt);
        return tile;
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.data != null && packet.data.length <= 0) {
            return;
        }
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(packet.data));
        try {
            PacketHandler.handlePacket(((EntityPlayer) player).worldObj, false, is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Logger.debug("Couldn't close the input! " + e.getMessage());
            }
        }
    }
}
