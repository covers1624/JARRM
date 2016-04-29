package covers1624.jarrm.proxy;

import covers1624.jarrm.init.ModBlocks;
import covers1624.jarrm.init.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        ModBlocks.registerModelVariants();
        ModItems.registerModelVariants();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ModBlocks.registerColourHandlers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
