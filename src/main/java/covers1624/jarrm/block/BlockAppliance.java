package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import covers1624.lib.api.block.property.PropertyString;
import covers1624.lib.api.tile.IActiveTile;
import covers1624.lib.api.tile.IRotatableTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.List;

import static covers1624.jarrm.reference.VariantReference.applianceNamesList;
import static covers1624.lib.api.block.StateReference.FACING_HOZ;

/**
 * Created by covers1624 on 3/27/2016.
 */
public class BlockAppliance extends BlockBase {

    public static final PropertyString VARIANTS = new PropertyString("type", applianceNamesList);

    //public static final PropertyEnumPlacing PLACING_TYPE = PropertyEnumPlacing.create("placingtype");
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockAppliance() {
        super(Material.rock);
        setHardness(2.0F);
        setDefaultState(getStateFromMeta(0).withProperty(FACING_HOZ, EnumFacing.NORTH).withProperty(ACTIVE, false));
        setCreativeTab(JARRM.machineCreativeTab);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getBlockState().getBaseState().withProperty(VARIANTS, applianceNamesList.get(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return applianceNamesList.indexOf(String.valueOf(state.getValue(VARIANTS)));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        boolean active = false;
        EnumFacing rotation = EnumFacing.NORTH;
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IActiveTile) {
            active = ((IActiveTile) tileEntity).isActive();
        }
        if (tileEntity instanceof IRotatableTile) {
            rotation = ((IRotatableTile) tileEntity).getRotation();
        }
        return state.withProperty(ACTIVE, active).withProperty(FACING_HOZ, rotation);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANTS, FACING_HOZ, ACTIVE);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < applianceNamesList.size(); i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
}
