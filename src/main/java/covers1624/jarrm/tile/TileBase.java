package covers1624.jarrm.tile;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.network.ActiveUpdatePacket;
import covers1624.jarrm.network.RotationUpdatePacket;
import covers1624.jarrm.reference.GuiIds;
import covers1624.lib.api.tile.*;
import covers1624.lib.util.RotationUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.List;
import java.util.Random;

/**
 * Created by covers1624 on 3/26/2016.
 */
public class TileBase extends TileEntity implements ITickable, IGuiTile, IHarvestTile, IActiveTile, IDisplayTickTile, IRotatableTile {

    private boolean isAdvanced = false;
    private boolean isActive = false;
    private EnumFacing facing = EnumFacing.NORTH;

    @Override
    public void update() {

    }

    @Override
    public void openGui(World world, BlockPos pos, EntityPlayer player) {

    }

    protected void openGui(GuiIds id, World world, BlockPos pos, EntityPlayer player) {
        player.openGui(JARRM.instance, id.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public List<ItemStack> getHarvestItems() {
        return null;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
        sendActiveUpdatePacket();
        updateBlock();
        updateLight();
    }

    //region Helper Functions.

    public void updateBlock() {
        IBlockState state = worldObj.getBlockState(getPos());
        worldObj.notifyBlockUpdate(getPos(), state, state, 3);
    }

    public void updateLight() {
        worldObj.notifyLightSet(getPos());
    }

    public void dirtyBlock() {
        Chunk chunk = worldObj.getChunkFromBlockCoords(getPos());
        chunk.setChunkModified();
    }

    //endregion
    @Override
    public void randomDisplayTick(World world, BlockPos position, IBlockState state, Random random) {

    }

    @Override
    public void doRotation(boolean shift) {
        if (isAdvanced) {
            if (!shift) {
                setRotation(RotationUtils.rotateAdvancedForward(this.facing));
            } else {
                setRotation(RotationUtils.rotateAdvancedBackwards(this.facing));
            }
        } else {
            if (!shift) {
                setRotation(RotationUtils.rotateClockwise(this.facing));
            } else {
                setRotation(RotationUtils.rotateCounterClockwise(this.facing));
            }
        }
    }

    @Override
    public EnumFacing getRotation() {
        return this.facing;
    }

    @Override
    public void setRotation(EnumFacing facing) {
        this.facing = facing;
        updateBlock();
        updateLight();
        sendRotationUpdatePacket();
    }

    private void sendRotationUpdatePacket() {
        if (!worldObj.isRemote) {
            RotationUpdatePacket packet = new RotationUpdatePacket(this, getPos());
            JARRM.packetPipeline.sendToAllAround(packet, new NetworkRegistry.TargetPoint(worldObj.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 128));
        }
    }

    private void sendActiveUpdatePacket(){
        if (!worldObj.isRemote){
            ActiveUpdatePacket packet = new ActiveUpdatePacket(this, getPos());
            JARRM.packetPipeline.sendToAllAround(packet, new NetworkRegistry.TargetPoint(worldObj.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 128));
        }
    }

    @Override
    public boolean getPlacing() {
        return this.isAdvanced;
    }

    @Override
    public void setPlacing(boolean placing) {
        this.isAdvanced = placing;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeNBT(compound);
    }

    public void writeNBT(NBTTagCompound tagCompound){
        tagCompound.setString("Rotation", facing.getName2());
        tagCompound.setBoolean("Active", isActive);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readNBT(compound);
    }

    public void readNBT(NBTTagCompound tagCompound){
        facing = EnumFacing.byName(tagCompound.getString("Rotation"));
        isActive = tagCompound.getBoolean("Active");
    }

    @Override
    public Packet<?> getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeNBT(tagCompound);
        return new SPacketUpdateTileEntity(getPos(), 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readNBT(pkt.getNbtCompound());
    }
}
