package covers1624.jarrm.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by covers1624 on 3/28/2016.
 */
public class OreDict {

    public static void init() {
        OreDictionary.registerOre("gemRuby", ModItems.itemRuby);
        OreDictionary.registerOre("gemGreenSapphire", ModItems.itemGreenSapphire);
        OreDictionary.registerOre("genSapphire", ModItems.itemSapphire);

        OreDictionary.registerOre("ingotSilver", ModItems.itemIngotSilver);
        OreDictionary.registerOre("ingotTin", ModItems.itemIngotTin);
        OreDictionary.registerOre("ingotCopper", ModItems.itemIngotCopper);
        OreDictionary.registerOre("dustNikolite", ModItems.itemNikolite);
        OreDictionary.registerOre("ingotRedAlloy", ModItems.itemIngotRed);
        OreDictionary.registerOre("ingotBlueAlloy", ModItems.itemIngotBlue);
        OreDictionary.registerOre("ingotBrass", ModItems.itemIngotBrass);
        OreDictionary.registerOre("bouleSilicon", ModItems.itemBouleSilicon);
        OreDictionary.registerOre("waferSilicon", ModItems.itemWaferSilicon);
        OreDictionary.registerOre("waferBlue", ModItems.itemWaferBlue);
        OreDictionary.registerOre("waferRed", ModItems.itemWaferRed);

        OreDictionary.registerOre("nuggetIron", ModItems.itemNuggetIron);
        OreDictionary.registerOre("nuggetSilver", ModItems.itemNuggetSilver);
        OreDictionary.registerOre("nuggetTin", ModItems.itemNuggetTin);
        OreDictionary.registerOre("nuggetCopper", ModItems.itemNuggetCopper);
    }

    //Thanks Brandon3055
    public static boolean areOreStacksSame(ItemStack stack1, ItemStack stack2) {
        //Get the ore id's for each stack
        int[] ore1Ids = OreDictionary.getOreIDs(stack1);
        int[] ore2Ids = OreDictionary.getOreIDs(stack2);

        //If ether stack has no ore id the stacks are not both the same ore
        if (ore1Ids.length == 0 || ore2Ids.length == 0) {
            return false;
        }
        //If both ores contain the same id the ores are the same
        for (int ore1Id : ore1Ids) {
            for (int ore2Id : ore2Ids) {
                if (ore1Id == ore2Id) {
                    return true;
                }
            }
        }
        return false;
    }

}
