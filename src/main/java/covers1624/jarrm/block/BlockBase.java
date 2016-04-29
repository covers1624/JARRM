package covers1624.jarrm.block;

import com.google.common.collect.ImmutableMap;
import covers1624.lib.api.block.property.PropertyEnumFacingAll;
import covers1624.lib.api.block.property.PropertyEnumFacingHoz;
import covers1624.lib.api.tile.*;
import covers1624.lib.util.ItemUtils;
import covers1624.lib.util.RotationUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by covers1624 on 3/28/2016.
 */
public class BlockBase extends BlockMultiTile {

    public BlockBase(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        if (!playerIn.isSneaking()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof IGuiTile) {
                IGuiTile tile = (IGuiTile) tileEntity;
                tile.openGui(worldIn, pos, playerIn);
                return true;
            }
        }
        return false;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        if (te instanceof IHarvestTile) {
            IHarvestTile harvestTile = (IHarvestTile) te;
            List<ItemStack> inventory = harvestTile.getHarvestItems();
            if (inventory != null) {
                for (ItemStack dropStack : inventory) {
                    if (dropStack != null) {
                        ItemUtils.dropItem(worldIn, pos, dropStack);
                    }
                }
            }
        }
        int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.fortune, stack);
        Item itemDropped = getItemDropped(state, RANDOM, fortune);
        ItemUtils.dropItem(worldIn, pos, new ItemStack(itemDropped, 1, damageDropped(state)));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IRotatableTile) {
            ImmutableMap<IProperty<?>, Comparable<?>> properties = state.getProperties();
            boolean containsFacing = false;
            //True FACING_ALL, False FACING_HOZ
            boolean facingAll = false;
            EnumFacing facing = null;
            for (IProperty<?> property : properties.keySet()) {
                if (property instanceof PropertyEnumFacingHoz) {
                    containsFacing = true;
                    facingAll = false;
                    break;
                } else if (property instanceof PropertyEnumFacingAll) {
                    containsFacing = true;
                    facingAll = true;
                    break;
                }
            }
            ((IPlacingTypeTile) tileEntity).setPlacing(facingAll);
            if (containsFacing) {
                if (facingAll) {
                    facing = RotationUtils.getPlacedRotationAdvanced(worldIn, pos, placer);
                } else {
                    facing = RotationUtils.getPlacedRotationHorizontal(worldIn, pos, placer);
                }
            }
            ((IRotatableTile) tileEntity).setRotation(facing);
        }

    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IDisplayTickTile) {
            IDisplayTickTile tickTile = (IDisplayTickTile) tileEntity;
            tickTile.randomDisplayTick(worldIn, pos, state, rand);
        }
    }
}
