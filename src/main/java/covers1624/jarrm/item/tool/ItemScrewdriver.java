package covers1624.jarrm.item.tool;

import covers1624.jarrm.JARRM;
import covers1624.lib.api.tile.IRotatableTile;
import covers1624.lib.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemScrewdriver extends Item {

    public ItemScrewdriver() {
        setMaxDamage(200);
        setMaxStackSize(1);
        setUnlocalizedName("screwdriver");
        setCreativeTab(JARRM.toolsCreativeTab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(8, attacker);
        return true;
    }

    @Override//TODO Broken.
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote) {
            return EnumActionResult.PASS;//FAIL??
        } else {
            LogHelper.info("Hello!");
            boolean isSneaking = false;
            if (player != null && player.isSneaking()) {
                isSneaking = true;
            }

            IBlockState blockState = world.getBlockState(pos);
            TileEntity tileEntity = world.getTileEntity(pos);
            Block block = blockState.getBlock();

            if (tileEntity instanceof IRotatableTile) {
                IRotatableTile tile = (IRotatableTile) tileEntity;
                tile.doRotation(isSneaking);
            } else {
                return EnumActionResult.FAIL;
            }
        }
        stack.damageItem(1, player);
        return EnumActionResult.SUCCESS;
    }
}
