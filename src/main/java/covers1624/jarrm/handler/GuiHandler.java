package covers1624.jarrm.handler;

import covers1624.jarrm.container.ContainerAlloyFurnace;
import covers1624.jarrm.gui.GuiAlloyFurnace;
import covers1624.jarrm.reference.GuiIds;
import covers1624.jarrm.tile.TileAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by covers1624 on 3/28/2016.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        switch (GuiIds.parse(ID)) {
        case ALLOY_FURNACE:
            return new ContainerAlloyFurnace(player.inventory, (TileAlloyFurnace) tileEntity);
        case UNKNOWN:
        default:
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        switch (GuiIds.parse(ID)) {
        case ALLOY_FURNACE:
            return new GuiAlloyFurnace(player.inventory, (TileAlloyFurnace) tileEntity);
        case UNKNOWN:
        default:
            return null;
        }
    }
}
