package covers1624.jarrm.init;

import covers1624.jarrm.api.recipe.DictionaryStack;
import covers1624.jarrm.util.LogHelper;
import covers1624.lib.util.ItemUtils;
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

    //TODO Move to CCL.

    /**
     * Checks if the stacks have an OreDictionary tag in common.
     *
     * @param input   Compare 1.
     * @param compare Compare 2.
     * @return True if match.
     * Author: Brandon3055
     */
    public static boolean areOreStacksSame(ItemStack input, ItemStack compare) {
        //Get the ore id's for each stack
        int[] inputIds = OreDictionary.getOreIDs(input);
        int[] compareIds = OreDictionary.getOreIDs(compare);

        //If ether stack has no ore id the stacks are not both the same ore
        if (inputIds.length == 0 || compareIds.length == 0) {
            return false;
        }
        //If both ores contain the same id the ores are the same
        for (int ore1Id : inputIds) {
            for (int ore2Id : compareIds) {
                if (ore1Id == ore2Id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if compare has the OreDictionary tag of dictionaryStack.getMaterial()
     *
     * @param dictionaryStack DictionaryStack material to check.
     * @param compare         Stack to check.
     * @return True if match.
     */
    public static boolean areOreStacksSame(DictionaryStack dictionaryStack, ItemStack compare) {
        int inputId = OreDictionary.getOreID(dictionaryStack.getMaterial());
        int[] compareIds = OreDictionary.getOreIDs(compare);

        //If ether stack has no ore id the stacks are not both the same ore
        if (inputId == 0 || compareIds.length == 0) {
            return false;
        }

        // If the ItemStack has the ore tag from the dictionary.
        for (int ore2Id : compareIds) {
            if (inputId == ore2Id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if compare has the OreDictionary tag material.
     *
     * @param material OreDictionary tag to check for.
     * @param compare  Stack to check.
     * @return True if match.
     */
    public static boolean areOreStacksSame(String material, ItemStack compare) {
        int inputId = OreDictionary.getOreID(material);
        int[] compareIds = OreDictionary.getOreIDs(compare);

        //If ether stack has no ore id the stacks are not both the same ore
        if (inputId == 0 || compareIds.length == 0) {
            return false;
        }

        // If the ItemStack has the ore tag from the dictionary.
        for (int ore2Id : compareIds) {
            if (inputId == ore2Id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to compare against the OreDictionary.
     * Input can be instance of String, DictionaryStack or ItemStack.
     *
     * @param input   Compare 1.
     * @param compare Compare 2.
     * @return True if match.
     */
    public static boolean areObjStackSame(Object input, ItemStack compare) {
        if (input instanceof ItemStack) {
            boolean result = areOreStacksSame((ItemStack) input, compare);
            if (!result) {
                result = ItemUtils.areStacksSameTypeCrafting((ItemStack) input, compare);
            }
            return result;
        } else if (input instanceof DictionaryStack) {
            return areOreStacksSame((DictionaryStack) input, compare);
        } else if (input instanceof String) {
            return areOreStacksSame((String) input, compare);
        }
        LogHelper.error("Unknown object! %s", input.getClass().getName());
        return false;
    }

    /**
     * Used to compare Stacks.
     * If the input is an ItemStack it will check the OreDictionary for a match,
     * If the OreDictionary check returns false it will then directly compare the stacks for crafting returning the result.
     * Otherwise it is assumed that it is a String or DictionaryStack and passes it to areObjStacksSame.
     *
     * @param input   Compare 1.
     * @param compare Compare 2
     * @return True if match.
     */
    public static boolean areStacksSame(Object input, ItemStack compare) {
        if (input instanceof ItemStack) {
            ItemStack inputStack = (ItemStack) input;
            boolean result = areOreStacksSame(inputStack, compare);
            if (!result) {
                result = ItemUtils.areStacksSameTypeCrafting(inputStack, compare);
            }
            return result;
        } else {
            return areObjStackSame(input, compare);
        }
    }

}
