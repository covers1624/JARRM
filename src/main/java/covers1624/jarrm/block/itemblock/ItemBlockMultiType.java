package covers1624.jarrm.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by covers1624 on 3/27/2016.
 */
//TODO Merge Model variant code in to here. maybe.
public class ItemBlockMultiType extends ItemBlock {

    private HashMap<Integer, String> names = new HashMap<Integer, String>();

    public ItemBlockMultiType(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    public void registerSubItem(int meta, String name) {
        names.put(meta, name);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile." + names.get(stack.getItemDamage());
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (Map.Entry<Integer, String> entry : names.entrySet()) {
            subItems.add(new ItemStack(this, 1, entry.getKey()));
        }
    }
}
