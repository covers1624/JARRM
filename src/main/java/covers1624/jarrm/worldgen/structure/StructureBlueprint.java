package covers1624.jarrm.worldgen.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 4/6/2016.
 */
public class StructureBlueprint {

    private ArrayList<StructureEntry> entryList;

    public StructureBlueprint(ArrayList<StructureEntry> entries) {
        entryList = new ArrayList<StructureEntry>();
        entryList.addAll(entries);
    }

    public void itterate(World world, BlockPos pos) {
        try {
            for (StructureEntry entry : entryList) {
                if (entry.canReplace(world.getBlockState(pos.add(entry.getPos())))) {
                    entry.setOffset(pos);
                    entry.placeBlock(world);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to continue placing structure!", e);
        }
    }

    public List<StructureEntry> getEntries() {
        return ImmutableList.copyOf(entryList);
    }

}
