package covers1624.jarrm.worldgen.structure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

/**
 * Created by covers1624 on 4/6/2016.
 */
public interface IStructureEntry {

    /**
     * Checks if the StructureEntry can replace the current block.
     *
     * @param state State to check.
     * @return True if can replace.
     */
    boolean canReplace(IBlockState state);

    /**
     * Called to place the block down in the world.
     */
    void placeBlock(World world);
}
