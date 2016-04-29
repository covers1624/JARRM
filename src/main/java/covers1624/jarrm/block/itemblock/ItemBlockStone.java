package covers1624.jarrm.block.itemblock;

import covers1624.jarrm.reference.VariantReference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

import static covers1624.jarrm.reference.VariantReference.stoneNamesList;

/**
 * Created by covers1624 on 3/27/2016.
 */
public class ItemBlockStone extends ItemBlock {

    public ItemBlockStone(Block block) {
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
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < stoneNamesList.size(); meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
