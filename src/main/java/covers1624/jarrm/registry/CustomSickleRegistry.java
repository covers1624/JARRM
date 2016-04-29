package covers1624.jarrm.registry;

import covers1624.jarrm.api.item.sickle.ICustomSickleRegistry;
import covers1624.jarrm.api.item.sickle.ISickleCropHandler;
import covers1624.lib.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;

/**
 * Created by covers1624 on 3/30/2016.
 */
public class CustomSickleRegistry implements ICustomSickleRegistry {

    public static final CustomSickleRegistry INSTANCE = new CustomSickleRegistry();

    //TODO HashMap this, Key Block, Value handler.
    private ArrayList<ISickleCropHandler> handlers = new ArrayList<ISickleCropHandler>();
    private ArrayList<ItemStack> blackListedBlocks = new ArrayList<ItemStack>();

    @Override
    public void registerSickleCropHandler(ISickleCropHandler handler) {
        if (handlers.contains(handler)) {
            LogHelper.warn("Mod %s is registering a SickleCropHandler that already exists. Class Name [%s]", Loader.instance().activeModContainer().getModId(), handler.getClass().getName());
            return;
        }
        LogHelper.info("Registering sickle handler for mod %s.", Loader.instance().activeModContainer().getModId());
        handlers.add(handler);
    }

    @Override
    public void registerBlacklistBlock(Block block) {
        blackListedBlocks.add(new ItemStack(block));
    }

    @Override
    public void registerBlacklistBlock(Block block, int meta) {
        blackListedBlocks.add(new ItemStack(block, 1, meta));
    }

    public ISickleCropHandler getHandlerForState(IBlockState state) {
        for (ISickleCropHandler handler : handlers) {
            if (handler.canHandle(state)) {
                return handler;
            }
        }

        //If one doesn't exist return a blank one.
        return new ISickleCropHandler() {
            @Override
            public boolean canHandle(IBlockState state) {
                LogHelper.info("HERP");
                return false;
            }

            @Override
            public boolean shouldBeBroken(IBlockState state) {
                LogHelper.info("DERP");
                return false;
            }
        };
    }

    public boolean isBlockBlackListed(IBlockState state) {
        ItemStack stack = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
        return isBlockBlacklisted(stack);
    }

    public boolean isBlockBlackListed(Block block) {
        return isBlockBlacklisted(new ItemStack(block));
    }

    public boolean isBlockBlacklisted(ItemStack stack) {
        for (ItemStack blacklisted : blackListedBlocks) {
            if (stack.isItemEqual(blacklisted)) {
                return true;
            }
        }
        return false;
    }

}
