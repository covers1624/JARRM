package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by covers1624 on 4/3/2016.
 */
public class BlockRubberLog extends Block {
    public BlockRubberLog() {
        super(Material.wood);
        setCreativeTab(JARRM.worldCreativeTab);
        setHardness(2.0F);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        int i = 4;
        int j = i + 1;

        if (worldIn.isAreaLoaded(pos.add(-j, -j, -j), pos.add(j, j, j))) {
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-i, -i, -i), pos.add(i, i, i))) {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                }
            }
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);
    }
}
