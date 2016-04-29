package covers1624.jarrm.api.recipe;

import com.google.common.collect.ImmutableList;
import covers1624.jarrm.init.OreDict;
import covers1624.lib.util.ItemUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by covers1624 on 12/19/2015.
 * DO NOT REPACK!
 * NOT FINISHED!
 * TODO OreDict AlloyFurnaceRecipe.
 * TODO Make this implement an interface.
 * <p/>
 * Repack                    Internal
 * IAlloyFurnaceRecipe > AlloyFurnaceRecipe
 */
public class AlloyFurnaceRecipe {

    private ItemStack result;

    // We don't care what order stuff is in.
    private ArrayList<ItemStack> inputs;

    public AlloyFurnaceRecipe(ItemStack result, ItemStack... inputs) {
        this.result = result;
        this.inputs = new ArrayList<ItemStack>();
        Collections.addAll(this.inputs, inputs);
        /*if (result instanceof String) {
            ItemStack ore = OreDict.getOre((String) result);
			if (ore != null) {
				ore.stackSize = 1;
				this.result = ore;
			} else {
				LogHelper.fatal("Unable to create AlloyFurnaceRecipe, was fed a OreDictionary entry that doesn't exist. " + result);
				throw new IllegalArgumentException((String) result);
			}
		} else if (result instanceof OreStack) {
			ItemStack stack = OreDict.getOre(((OreStack) result).ore);
			if (stack != null) {
				stack.stackSize = ((OreStack) result).stackSize;
				this.result = stack;
			} else {
				LogHelper.fatal("Unable to create AlloyFurnaceRecipe, was fed a OreDictionary entry that doesn't exist. " + result);
				throw new IllegalArgumentException(((OreStack) result).ore);
			}
		} else if (result instanceof Item) {
			this.result = new ItemStack((Item) result);
		} else if (result instanceof Block) {
			this.result = new ItemStack((Block) result);
		} else if (result instanceof ItemStack) {
			this.result = ((ItemStack) result).copy();
		} else if (result == null) {
			LogHelper.info("Unable to create AllyFurnaceRecipe. Was given null Input.");
			throw new IllegalArgumentException();
		}
		this.inputs = new ArrayList<ItemStack>();
		for (Object object : inputs) {
			if (object instanceof String) {
				ItemStack ore = OreDict.getOre((String) object);
				if (ore != null) {
					ore.stackSize = 1;
					this.inputs.add(ore);
				} else {
					LogHelper.fatal("Unable to create AlloyFurnaceRecipe, was fed a OreDictionary entry that doesn't exist. " + result);
					throw new IllegalArgumentException((String) object);
				}
			} else if (object instanceof OreStack) {
				ItemStack stack = OreDict.getOre(((OreStack) object).ore);
				if (stack != null) {
					stack.stackSize = ((OreStack) object).stackSize;
					this.inputs.add(stack);
				} else {
					LogHelper.fatal("Unable to create AlloyFurnaceRecipe, was fed a OreDictionary entry that doesn't exist. " + result);
					throw new IllegalArgumentException(((OreStack) object).ore);
				}
			} else if (object instanceof Item) {
				this.inputs.add(new ItemStack((Item) object));
			} else if (object instanceof Block) {
				this.inputs.add(new ItemStack((Block) object));
			} else if (object instanceof ItemStack) {
				this.inputs.add(((ItemStack) object).copy());
			} else if (object == null) {
				LogHelper.error("Unable to create AlloyFurnaceRecipe. Was given a Null Object in Recipe Inputs.");
				throw new IllegalArgumentException();
			}
		}*/
    }

    /**
     * Returns true if the array passed equals the recipe.
     */
    public boolean isRecipe(ItemStack[] craftMatrix) {
        for (ItemStack input : inputs) {
            int needed = input.stackSize;
            for (int i = 0; i < 9; i++) {
                ItemStack matrixItem = craftMatrix[i];
                if (matrixItem != null && (ItemUtils.areStacksSameTypeCrafting(input, matrixItem) || OreDict.areOreStacksSame(input, matrixItem))) {
                    needed -= matrixItem.stackSize;
                    if (needed <= 0) {
                        break;
                    }
                }
            }
            if (needed > 0) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getRecipeOutput() {
        return this.result;
    }

    public List<ItemStack> getRecipeInputs() {

        return ImmutableList.copyOf(inputs);
    }

    public static class OreStack {
        public String ore;
        public int stackSize;

        public OreStack(String ore) {
            this(ore, 1);
        }

        public OreStack(String ore, int stackSize) {
            this.ore = ore;
            this.stackSize = stackSize;
        }
    }
}
