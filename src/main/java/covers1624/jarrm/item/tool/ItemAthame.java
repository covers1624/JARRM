package covers1624.jarrm.item.tool;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.init.ModItems;
import covers1624.jarrm.init.OreDict;
import covers1624.lib.util.ItemUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemAthame extends ItemSword {
    public ItemAthame() {
        super(ToolMaterial.DIAMOND);
        setMaxDamage(100);
        setUnlocalizedName("athame");
        setCreativeTab(JARRM.toolsCreativeTab);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return 1.0F;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ItemUtils.areStacksSameType(repair, ModItems.itemIngotSilver) || OreDict.areOreStacksSame(repair, ModItems.itemIngotSilver);
    }

    @Override
    public int getItemEnchantability() {
        return 30;
    }
}
