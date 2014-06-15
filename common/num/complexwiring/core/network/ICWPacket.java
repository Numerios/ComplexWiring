package num.complexwiring.core.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public interface ICWPacket {
    public void encode(ChannelHandlerContext context, ByteBuf buffer);

    public void decode(ChannelHandlerContext context, ByteBuf buffer);

    public void handleClient(EntityPlayer player);

    public void handleServer(EntityPlayer player);
}
