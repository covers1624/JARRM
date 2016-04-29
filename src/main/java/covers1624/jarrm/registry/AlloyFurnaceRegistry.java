package covers1624.jarrm.registry;

import com.google.common.collect.ImmutableList;
import covers1624.jarrm.api.recipe.AlloyFurnaceRecipe;
import covers1624.jarrm.init.OreDict;
import covers1624.lib.util.ItemUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 12/19/2015.
 */
public class AlloyFurnaceRegistry {

    private static ArrayList<AlloyFurnaceRecipe> recipes = new ArrayList<AlloyFurnaceRecipe>();

    public static void addAlloyFurnaceRecipe(ItemStack result, ItemStack... input) {
        AlloyFurnaceRecipe recipe = new AlloyFurnaceRecipe(result, input);
        recipes.add(recipe);
    }

    public static AlloyFurnaceRecipe getRecipeClass(ItemStack[] craftMatrix) {
        for (AlloyFurnaceRecipe recipeObject : recipes) {
            if (recipeObject.isRecipe(craftMatrix)) {
                return recipeObject;
            }
        }
        return null;
    }

    public static AlloyFurnaceRecipe getRecipeFromOutput(ItemStack stack) {
        for (AlloyFurnaceRecipe recipe : recipes) {
            if (recipe.getRecipeOutput().isItemEqual(stack)) {
                return recipe;
            }
        }
        return null;
    }

    public static List<AlloyFurnaceRecipe> getAllRecipes() {
        return ImmutableList.copyOf(recipes);
    }

    public static boolean recipeExists(ItemStack[] craftMatrix) {
        return getRecipeClass(craftMatrix) != null;
    }

    public static ArrayList<ItemStack> combineStacks(ItemStack[] craftMatrix) {
        ArrayList<ItemStack> finish = new ArrayList<ItemStack>();
        ItemStack[] stacks = craftMatrix.clone();
        for (ItemStack stack : stacks) {
            if (stack != null) {
                boolean sucsess = false;
                for (ItemStack itemStack : finish) {
                    if (itemStack.stackSize != itemStack.getMaxStackSize()) {
                        // We can add.
                        if (itemStack.getItem() == stack.getItem()) {
                            if (itemStack.getItemDamage() == stack.getItemDamage()) {
                                if (itemStack.stackSize + stack.stackSize > stack.getMaxStackSize()) {
                                    int cantAdd = itemStack.stackSize + stack.stackSize - stack.getMaxStackSize();
                                    int toAdd = stack.stackSize - cantAdd;
                                    itemStack.stackSize += toAdd;
                                    ItemStack add = stack.copy();
                                    add.stackSize = cantAdd;
                                    finish.add(add);
                                    sucsess = true;
                                    break;
                                } else {
                                    itemStack.stackSize += stack.stackSize;
                                    sucsess = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!sucsess) {
                    finish.add(stack);
                }
            }
        }
        return finish;
    }

    public static ItemStack doAlloyCrafting(AlloyFurnaceRecipe recipe, ItemStack[] craftMatrix) {
        for (ItemStack object : recipe.getRecipeInputs()) {
            int amountLeft = object.stackSize;
            for (int i = 0; i < 9; i++) {
                if (craftMatrix[i] != null && (ItemUtils.areStacksSameTypeCrafting(object, craftMatrix[i]) || OreDict.areOreStacksSame(object, craftMatrix[i]))) {
                    int toUse = Math.min(craftMatrix[i].stackSize, amountLeft);
                    craftMatrix[i].stackSize -= toUse;
                    if (craftMatrix[i].stackSize <= toUse) {
                        craftMatrix[i] = null;
                    }
                    amountLeft -= toUse;
                    if (amountLeft <= 0) {
                        break;
                    }
                }
            }
        }
        return recipe.getRecipeOutput();
    }
}