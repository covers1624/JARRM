package covers1624.jarrm.module.sickle;

import covers1624.jarrm.api.item.sickle.ISickleCropHandler;
import covers1624.lib.util.LogHelper;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

/**
 * Created by covers1624 on 3/30/2016.
 */
public class WheatCropHandler implements ISickleCropHandler {

    @Override
    public boolean canHandle(IBlockState state) {
        return state.getBlock() == Blocks.wheat;
    }

    @Override
    public boolean shouldBeBroken(IBlockState state) {
        try {
            Integer age = state.getValue(BlockCrops.AGE);
            LogHelper.info(age);
            return age == 7;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
