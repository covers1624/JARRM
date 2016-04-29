package covers1624.jarrm.network;

import covers1624.lib.api.tile.IActiveTile;
import covers1624.lib.util.LogHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ActiveUpdatePacket extends AbstractUpdatePacket {

    private boolean active;

    public ActiveUpdatePacket() {

    }

    public ActiveUpdatePacket(IActiveTile tile, BlockPos pos) {
        super(pos);
        this.active = tile.isActive();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        super.encodeInto(ctx, buffer);
        buffer.writeBoolean(active);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        super.decodeInto(ctx, buffer);
        this.active = buffer.readBoolean();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        TileEntity tileEntity = player.worldObj.getTileEntity(pos);
        if (tileEntity instanceof IActiveTile) {
            ((IActiveTile) tileEntity).setActive(active);
        } else {
            LogHelper.warn("Received ActiveUpdatePacket for a tile that is not a IActiveTile. {x:%s, y:%s, z:%s}", pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        //NO-OP
    }
}
