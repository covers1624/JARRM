package covers1624.jarrm.item.tool;

import covers1624.jarrm.JARRM;
import net.minecraft.item.ItemHoe;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemGemHoe extends ItemHoe {
    public ItemGemHoe(ToolMaterial material, String unlocName) {
        super(material);
        setUnlocalizedName(unlocName);
        setCreativeTab(JARRM.toolsCreativeTab);
    }
}
