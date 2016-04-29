package covers1624.jarrm.network;

import covers1624.lib.network.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.math.BlockPos;

/**
 * Created by covers1624 on 3/29/2016.
 */
public abstract class AbstractUpdatePacket extends AbstractPacket {

    protected BlockPos pos;

    public AbstractUpdatePacket() {

    }

    public AbstractUpdatePacket(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(pos.getX());
        buffer.writeShort(pos.getY());
        buffer.writeInt(pos.getZ());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        int x = buffer.readInt();
        int y = buffer.readShort();
        int z = buffer.readInt();
        this.pos = new BlockPos(x, y, z);
    }
}
