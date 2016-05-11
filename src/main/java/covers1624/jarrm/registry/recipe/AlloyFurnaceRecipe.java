package covers1624.jarrm.registry.recipe;

import com.google.common.collect.ImmutableList;
import covers1624.jarrm.api.recipe.DictionaryStack;
import covers1624.jarrm.api.recipe.IAlloyRecipe;
import covers1624.jarrm.init.OreDict;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 5/11/2016.
 */
public class AlloyFurnaceRecipe implements IAlloyRecipe {

    private ItemStack result;

    private ArrayList<Object> inputs;

    /**
     * Parameters can be instance of:
     * String: OreDictionary.
     * Block: defaults to meta of 0.
     * Item: defaults to meta of 0.
     * ItemStack: Can have NBT stored on the ItemStack.
     * DictionaryStack: Used to specify more than 1 item but still being an OreDictionary Stack, Can have meta data.
     *
     * @param result The item to give the player.
     * @param inputs The inputs for the item.
     */
    public AlloyFurnaceRecipe(Object result, Object... inputs) {
        if (result == null) {
            throw new IllegalArgumentException("Result was null!");
        }
        if (result instanceof String) {
            DictionaryStack stack = new DictionaryStack((String) result);
            if (stack.getStackFromDictionary() == null) {
                throw new IllegalArgumentException("Failed to find " + stack.getMaterial() + " in the OreDictionary!");
            } else {
                this.result = stack.getStackFromDictionary();
            }
        } else if (result instanceof DictionaryStack) {
            DictionaryStack stack = (DictionaryStack) result;
            if (stack.getStackFromDictionary() == null) {
                throw new IllegalArgumentException("Failed to find " + stack.getMaterial() + " in the OreDictionary!");
            }
        } else if (result instanceof Block) {
            this.result = new ItemStack((Block) result);
        } else if (result instanceof Item) {
            this.result = new ItemStack((Item) result);
        } else if (result instanceof ItemStack) {
            this.result = ((ItemStack) result).copy();
        } else {
            throw new IllegalArgumentException("Unknown type instance in recipe output! " + "\"" + result.getClass().getName() + "\"");
        }
        for (Object object : inputs) {
            if (object == null) {
                //TODO Make say the element index that was null for better debugging.
                throw new IllegalArgumentException("Argument in recipe inputs was null!");
            }
            if (object instanceof String) {
                DictionaryStack stack = new DictionaryStack((String) object);
                if (stack.existsInDictionary()) {
                    this.inputs.add(stack);
                } else {
                    throw new IllegalArgumentException("Failed to find " + stack.getMaterial() + " in the OreDictionary!");
                }
            } else if (object instanceof DictionaryStack) {
                DictionaryStack stack = (DictionaryStack) object;
                if (stack.existsInDictionary()) {
                    this.inputs.add(stack);
                } else {
                    throw new IllegalArgumentException("Failed to find " + stack.getMaterial() + " in the OreDictionary!");
                }
            } else if (object instanceof Block) {
                this.inputs.add(new ItemStack((Block) object));
            } else if (object instanceof Item) {
                this.inputs.add(new ItemStack((Item) object));
            } else if (object instanceof ItemStack) {
                this.inputs.add(((ItemStack) object).copy());
            } else {
                throw new IllegalArgumentException("Unknown type instance in recipe inputs! " + "\"" + object.getClass().getName() + "\"");
            }
        }
    }

    @Override
    public boolean isRecipe(ItemStack[] craftMatrix) {
        for (Object input : inputs) {
            int needed = (input instanceof ItemStack ? ((ItemStack) input).stackSize : ((DictionaryStack) input).getStackSize());
            for (int i = 0; i < 9; i++) {
                ItemStack matrixItem = craftMatrix[i];
                if (matrixItem != null && OreDict.areStacksSame(input, matrixItem)) {
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

    @Override
    public ItemStack getRecipeResult() {
        return this.result;
    }

    @Override
    public List<Object> getRecipeInputs() {
        return ImmutableList.copyOf(this.inputs);
    }
}
