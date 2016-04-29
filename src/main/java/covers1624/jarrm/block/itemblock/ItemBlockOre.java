package covers1624.jarrm.block.itemblock;

import covers1624.jarrm.reference.VariantReference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class ItemBlockOre extends ItemBlock {

	public ItemBlockOre(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}


	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + VariantReference.oreNames[stack.getMetadata()];
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int i = 0; i < VariantReference.oreNames.length; i++) {
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}
}
