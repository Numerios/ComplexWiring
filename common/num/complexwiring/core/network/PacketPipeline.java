package num.complexwiring.core.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import num.complexwiring.core.Logger;

import java.util.*;

/**
 * @author sirgingalot, some code from: cpw
 */
@ChannelHandler.Sharable
public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket, ICWPacket> {
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends ICWPacket>> packets = new LinkedList<Class<? extends ICWPacket>>();
    private boolean postInited = false;

    public boolean registerPacket(Class<? extends ICWPacket> packetClass) {
        if (this.packets.size() > 256) {
            Logger.warn("Can't register " + packetClass.getName() + ", because there can be max 256 packets!");
            return false;
        }

        if (this.packets.contains(packetClass)) {
            Logger.warn("Can't register " + packetClass.getName() + ", because it has been registered already!");
            return false;
        }

        if (this.postInited) {
            Logger.warn("Can't register " + packetClass.getName() + ", because it has been registered too late!");
            return false;
        }

        this.packets.add(packetClass);
        return true;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ICWPacket msg, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Class<? extends ICWPacket> packetClass = msg.getClass();
        if (!this.packets.contains(msg.getClass())) {
            throw new NullPointerException("Packet " + msg.getClass().getName() + " has not been registered!");
        }

        byte discriminator = (byte) this.packets.indexOf(packetClass);
        buffer.writeByte(discriminator);
        msg.encode(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add(proxyPacket);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Class<? extends ICWPacket> packetClass = this.packets.get(discriminator);
        if (packetClass == null) {
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        }

        ICWPacket pkt = packetClass.newInstance();
        pkt.decode(ctx, payload.slice());

        EntityPlayer player;
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                player = this.getClientPlayer();
                pkt.handleClient(player);
                break;

            case SERVER:
                INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer) netHandler).playerEntity;
                pkt.handleServer(player);
                break;

            default:
        }

        out.add(pkt);
    }

    public void init() {
        this.channels = NetworkRegistry.INSTANCE.newChannel("CW", this);
    }

    public void postInit() {
        if (this.postInited) {
            return;
        }

        this.postInited = true;
        Collections.sort(this.packets, new Comparator<Class<? extends ICWPacket>>() {

            @Override
            public int compare(Class<? extends ICWPacket> clazz1, Class<? extends ICWPacket> clazz2) {
                int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
                if (com == 0) {
                    com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
                }

                return com;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public void sendToAll(ICWPacket message) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    public void sendTo(ICWPacket message, EntityPlayerMP player) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    public void sendToAllAround(ICWPacket message, NetworkRegistry.TargetPoint point) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    public void sendToDimension(ICWPacket message, int dimensionId) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    public void sendToServer(ICWPacket message) {
        this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.channels.get(Side.CLIENT).writeAndFlush(message);
    }
}
