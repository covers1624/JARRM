package covers1624.jarrm.worldgen.structure;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by covers1624 on 4/6/2016.
 */
public class StructureEntry implements IStructureEntry {

    /**
     * The position for the block to place at.
     */
    private BlockPos pos;

    /**
     * The offset to be applied to the StructureEntry.
     */
    private BlockPos offset;
    /**
     * The BlockState to place.
     */
    private IBlockState state;

    @Deprecated
    public StructureEntry(BlockPos pos, Block block, int meta) {
        this(pos, block.getStateFromMeta(meta));
    }

    public StructureEntry(BlockPos pos, IBlockState state) {
        this.pos = pos;
        this.state = state;
    }

    @Override
    public boolean canReplace(IBlockState state) {
        return true;//TODO
    }

    @Override
    public void placeBlock(World world) {
        if (offset == null) {
            throw new IllegalStateException("Unable to place StructureEntry as offset is null!");
        }//TODO Some sort of block placement batcher, Places a lot of blocks at once and updates lighting once.
        world.setBlockState(offset.add(pos), state);
    }

    public BlockPos getPos() {
        return pos;
    }

    public IBlockState getState() {
        return state;
    }

    public void setOffset(BlockPos offset) {
        this.offset = offset;
    }

    public void clearOffset() {
        this.offset = null;
    }
}
