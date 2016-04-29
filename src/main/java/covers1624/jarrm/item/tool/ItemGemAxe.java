package covers1624.jarrm.item.tool;

import com.google.common.collect.Sets;
import covers1624.jarrm.JARRM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemGemAxe extends ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder, Blocks.wooden_button, Blocks.wooden_pressure_plate);

    public ItemGemAxe(ToolMaterial materialIn, String unlocName) {
        super(8.0F, -3.0F, materialIn, EFFECTIVE_ON);
        setUnlocalizedName(unlocName);
        setCreativeTab(JARRM.toolsCreativeTab);
        setHarvestLevel("axe", materialIn.getHarvestLevel());
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.wood && material != Material.plants && material != Material.vine ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
}
