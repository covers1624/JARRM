package covers1624.jarrm.api.item.sickle;

import net.minecraft.block.state.IBlockState;

/**
 * Created by covers1624 on 3/30/2016.
 * Used to provide custom functionality to the sickle.
 * TODO Intermods message registry for this Like waila.
 */
public interface ISickleCropHandler {

    /**
     * Should return true if the handler can "handle" the block state.
     * See the vanilla implementation for more information
     *
     * @param state The state to check.
     * @return True if can "handle".
     */
    boolean canHandle(IBlockState state);

    /**
     * Should return true if the crop is ready for breaking.
     *
     * @param state The state to check.
     * @return True if should be broken.
     */
    boolean shouldBeBroken(IBlockState state);

}
