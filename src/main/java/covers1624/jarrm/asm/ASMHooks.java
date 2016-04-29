package covers1624.jarrm.asm;

import covers1624.jarrm.block.BlockRubberLeaves;
import covers1624.lib.util.LogHelper;

/**
 * Created by covers1624 on 4/1/2016.
 */
public class ASMHooks {

    public static void setGraphicsLevelCallBack(boolean fancy) {
        BlockRubberLeaves.setGraphicsLevel(fancy);
    }

}
