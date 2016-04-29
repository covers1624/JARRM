package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import covers1624.lib.api.block.property.PropertyString;
import covers1624.lib.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

import static covers1624.jarrm.reference.VariantReference.stoneNamesList;

/**
 * Created by covers1624 on 3/27/2016.
 */
public class BlockStone extends Block {
    private static final PropertyString VARIANTS = new PropertyString("type", stoneNamesList);

    public BlockStone() {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(10.0F);
        setDefaultState(getStateFromMeta(0));
        setUnlocalizedName("stone");
        setCreativeTab(JARRM.worldCreativeTab);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getBlockState().getBaseState().withProperty(VARIANTS, stoneNamesList.get(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return stoneNamesList.indexOf(String.valueOf(state.getValue(VARIANTS)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANTS);
    }

    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
        int meta = getMetaFromState(blockState);
        switch (meta) {
        case 0://Marble
        case 2://Marble Brick
            return 1.0F;
        case 1://Basalt
        case 3://Basalt Cobblestone
        case 4://Basalt Brick
        case 5://Basalt Circle
        case 6://Basalt Paver
            return 2.5F;
        default:
            LogHelper.info("Block Stone has a weird meta [%s] for hardness... ", meta);
            return 3.0F;//When in doubt return 3.0F
        }
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        int meta = getMetaFromState(world.getBlockState(pos));
        switch (meta) {
        case 0://Marble
        case 2://Marble Brick
            return 6.0F;
        case 1://Basalt
        case 3://Basalt Cobblestone
        case 4://Basalt Brick
        case 5://Basalt Circle
        case 6://Basalt Paver
            return 12.0F;
        default:
            LogHelper.info("Block Stone has a weird meta [%s] for ExplosionResistance... ", meta);
            return 6.0F;//When in doubt return 6.0F
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        int meta = getMetaFromState(state);
        if (meta == 1 || meta == 6) {
            return 3;
        }
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < stoneNamesList.size(); meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
