package covers1624.jarrm.item.tool;

import covers1624.jarrm.JARRM;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemGemPickaxe extends ItemPickaxe {

    public ItemGemPickaxe(ToolMaterial material, String unlocName) {
        super(material);
        setUnlocalizedName(unlocName);
        setCreativeTab(JARRM.toolsCreativeTab);
    }
}
