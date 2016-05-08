package covers1624.jarrm.worldgen.structure;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 4/6/2016.
 */
public class StructureBlueprint {

    private ArrayList<StructureEntry> entryList;

    public int totalHeight;

    public StructureBlueprint(ArrayList<StructureEntry> entries) {
        entryList = new ArrayList<StructureEntry>(entries);
        calculateHeight();
    }

    private void calculateHeight() {
        for (StructureEntry entry : getEntries()) {
            if (entry.getPos().getY() > totalHeight) {
                totalHeight = entry.getPos().getY();
            }
        }
    }

    public List<StructureEntry> getEntries() {
        return ImmutableList.copyOf(entryList);
    }

}
