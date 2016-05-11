package covers1624.jarrm.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by covers1624 on 5/11/2016.
 */
public final class DictionaryStack {

    private final String material;
    private final int stackSize;

    public DictionaryStack(String material, int stackSize) {
        this.material = material;
        this.stackSize = stackSize;
    }

    public DictionaryStack(String material) {
        this.material = material;
        this.stackSize = 1;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public ItemStack getStackFromDictionary() {
        List<ItemStack> dictStacks = OreDictionary.getOres(material);
        if (dictStacks.isEmpty()) {
            return null;
        }
        ItemStack finalStack = dictStacks.get(0).copy();
        finalStack.stackSize = stackSize;
        return finalStack;
    }

    public boolean existsInDictionary(){
        return !OreDictionary.getOres(material).isEmpty();
    }
}
