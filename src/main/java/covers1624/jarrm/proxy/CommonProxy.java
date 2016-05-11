package covers1624.jarrm.proxy;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.handler.EventHandler;
import covers1624.jarrm.handler.GuiHandler;
import covers1624.jarrm.init.ModBlocks;
import covers1624.jarrm.init.ModItems;
import covers1624.jarrm.init.Recipes;
import covers1624.jarrm.worldgen.JARRMWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(JARRM.instance, new GuiHandler());
        ModBlocks.init();
        ModItems.init();
        JARRM.worldCreativeTab.craftIcon();
        JARRM.machineCreativeTab.craftIcon();
        JARRM.toolsCreativeTab.craftIcon();
    }

    public void init(FMLInitializationEvent event) {
        Recipes.init();
        GameRegistry.registerWorldGenerator(new JARRMWorldGenerator(), 0);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
