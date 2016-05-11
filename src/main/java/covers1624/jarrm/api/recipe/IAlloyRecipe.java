package covers1624.jarrm.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by covers1624 on 5/11/2016.
 */
public interface IAlloyRecipe {

    /**
     * Checks if the current matrix is a valid AlloyFurnace Recipe. Really only used for checking if the tile should stay active.
     *
     * @param craftMatrix The matrix to check, This should be the direct array from the tile, with a size of 9.
     * @return True if the recipe is valid.
     */
    boolean isRecipe(ItemStack[] craftMatrix);

    /**
     * Gets the result for the recipe.
     *
     * @return The stack to give the player. Should be ok for NBT tags to be applied.
     */
    ItemStack getRecipeResult();

    /**
     * Used to get all the recipe components used in its input.
     * Will either be an instance of ItemStack or DictionaryStack.
     *
     * @return List of inputs.
     */
    List<Object> getRecipeInputs();
}
