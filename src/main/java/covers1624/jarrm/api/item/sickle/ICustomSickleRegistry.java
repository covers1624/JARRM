package covers1624.jarrm.api.item.sickle;

import net.minecraft.block.Block;

/**
 * Created by covers1624 on 3/30/2016.
 */
public interface ICustomSickleRegistry {

    void registerSickleCropHandler(ISickleCropHandler handler);

    void registerBlacklistBlock(Block block);

    void registerBlacklistBlock(Block block, int meta);
}
