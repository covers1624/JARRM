package covers1624.jarrm.init;

import covers1624.jarrm.registry.AlloyFurnaceRegistry;
import covers1624.lib.util.ItemUtils;
import covers1624.lib.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class Recipes {

    public static void init() {
        registerAlloyRecipes();
    }

    //TODO OreDictionary this bs
    public static void registerAlloyRecipes() {
        LogHelper.info("Registering Alloy recipes.");
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotRed, new ItemStack(Items.redstone, 4), new ItemStack(Items.iron_ingot));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotRed, new ItemStack(Items.redstone, 4), ModItems.itemIngotCopper);
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ItemUtils.copyStack(ModItems.itemIngotBrass, 4), ModItems.itemIngotTin, ItemUtils.copyStack(ModItems.itemIngotCopper, 3));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ItemUtils.copyStack(ModItems.itemTinplate, 4), ModItems.itemIngotTin, new ItemStack(Items.iron_ingot, 2));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotBlue, ModItems.itemIngotSilver, ItemUtils.copyStack(ModItems.itemNikolite, 4));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 3), new ItemStack(Blocks.rail, 8));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 3), new ItemStack(Items.bucket));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 5), new ItemStack(Items.minecart));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 6), new ItemStack(Items.iron_door));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 3), new ItemStack(Blocks.iron_bars, 8));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 31), new ItemStack(Blocks.anvil, 1, 0));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 31), new ItemStack(Blocks.anvil, 1, 1));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 31), new ItemStack(Blocks.anvil, 1, 2));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 2), new ItemStack(Items.iron_sword));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 3), new ItemStack(Items.iron_pickaxe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 3), new ItemStack(Items.iron_axe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(Items.iron_shovel));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 2), new ItemStack(Items.iron_hoe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 5), new ItemStack(Items.iron_helmet));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 8), new ItemStack(Items.iron_chestplate));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 7), new ItemStack(Items.iron_leggings));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 4), new ItemStack(Items.iron_boots));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 2), new ItemStack(Items.golden_sword));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 3), new ItemStack(Items.golden_pickaxe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 3), new ItemStack(Items.golden_axe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 1), new ItemStack(Items.golden_shovel));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 2), new ItemStack(Items.golden_hoe));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 5), new ItemStack(Items.golden_helmet));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 8), new ItemStack(Items.golden_chestplate));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 7), new ItemStack(Items.golden_leggings));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot, 4), new ItemStack(Items.golden_boots));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget, 9));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot), ItemUtils.copyStack(ModItems.itemNuggetIron, 9));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotSilver, ItemUtils.copyStack(ModItems.itemNuggetSilver, 9));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotCopper, ItemUtils.copyStack(ModItems.itemNuggetCopper, 9));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotTin, ItemUtils.copyStack(ModItems.itemNuggetTin, 9));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemIngotCopper, ModItems.itemFineCopper);
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(new ItemStack(Items.iron_ingot, 1), ModItems.itemFineIron);
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemBouleSilicon, new ItemStack(Items.coal, 8, 0), new ItemStack(Blocks.sand, 8));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemBouleSilicon, new ItemStack(Items.coal, 8, 1), new ItemStack(Blocks.sand, 8));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemWaferBlue, ModItems.itemWaferSilicon, ItemUtils.copyStack(ModItems.itemNikolite, 4));
        AlloyFurnaceRegistry.addAlloyFurnaceRecipe(ModItems.itemWaferRed, ModItems.itemWaferSilicon, new ItemStack(Items.redstone, 4));
    }

}
