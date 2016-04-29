package covers1624.jarrm.network;

import covers1624.lib.api.tile.IRotatableTile;
import covers1624.lib.util.LogHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * Created by covers1624 on 3/28/2016.
 */
public class RotationUpdatePacket extends AbstractUpdatePacket {

    private EnumFacing facing;

    public RotationUpdatePacket(){
    }

    public RotationUpdatePacket(IRotatableTile tile, BlockPos pos) {
        super(pos);
        this.facing = tile.getRotation();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        super.encodeInto(ctx, buffer);
        buffer.writeShort(facing.ordinal());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        super.decodeInto(ctx, buffer);
        int ordinal = buffer.readShort();
        this.facing = EnumFacing.values()[ordinal];
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        TileEntity tileEntity = player.worldObj.getTileEntity(pos);
        if (tileEntity instanceof IRotatableTile) {
            ((IRotatableTile) tileEntity).setRotation(facing);
        } else {
            LogHelper.warn("Received RotationUpdatePacket for a tile that is not a IRotatableTile. {x:%s, y:%s, z:%s}", pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        //NO-OP
    }
}
