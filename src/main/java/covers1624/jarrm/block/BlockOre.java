package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.init.ModItems;
import covers1624.lib.api.block.property.PropertyString;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static covers1624.jarrm.reference.VariantReference.oreNamesList;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class BlockOre extends Block {

    public static final PropertyString VARIANTS = new PropertyString("type", oreNamesList);

    public BlockOre() {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setDefaultState(this.getStateFromMeta(0));
        setCreativeTab(JARRM.worldCreativeTab);
        setUnlocalizedName("ores");
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getBlockState().getBaseState().withProperty(VARIANTS, oreNamesList.get(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return oreNamesList.indexOf(state.getValue(VARIANTS));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANTS);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < oreNamesList.size(); i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        int meta = getMetaFromState(state);
        if (meta == 6) {
            return 4 + random.nextInt(2) + random.nextInt(fortune + 1);
        } else if (meta < 3) {
            int quant = random.nextInt(fortune + 2) - 1;
            if (quant < 0) {
                quant = 0;
            }
            return quant + 1;
        } else {
            return 1;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        int meta = getMetaFromState(state);
        if (meta >= 3 && meta != 6) {
            return Item.getItemFromBlock(this);
        } else {
            return ModItems.itemResource;
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        int meta = getMetaFromState(state);
        byte from = 0;
        byte to = 0;

        //Gems
        if (meta <= 2) {
            from = 3;
            to = 7;
        } else if (meta == 6) {
            from = 1;
            to = 5;
        }
        if (to > 0) {
            dropXpOnBlockBreak(worldIn, pos, MathHelper.getRandomIntegerInRange(worldIn.rand, from, to));
        }
    }
}
