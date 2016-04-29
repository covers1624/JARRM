package covers1624.jarrm;

import covers1624.jarrm.handler.ConfigurationHandler;
import covers1624.jarrm.handler.InterModsManager;
import covers1624.jarrm.module.ModuleManager;
import covers1624.jarrm.network.ActiveUpdatePacket;
import covers1624.jarrm.network.RotationUpdatePacket;
import covers1624.jarrm.proxy.CommonProxy;
import covers1624.jarrm.util.StructureUtils;
import covers1624.lib.gui.SimpleCreativeTab;
import covers1624.lib.network.PacketPipeline;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static covers1624.jarrm.reference.Reference.*;

/**
 * Created by covers1624 on 2/5/2016.
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, guiFactory = GUI_FACTORY_CLASS)
public class JARRM {

    public static SimpleCreativeTab worldCreativeTab = new SimpleCreativeTab("jarrm.world", "jarrm:ores", true);
    public static SimpleCreativeTab machineCreativeTab = new SimpleCreativeTab("jarrm.machine", "jarrm:appliance", true);
    public static SimpleCreativeTab toolsCreativeTab = new SimpleCreativeTab("jarrm.tools", "jarrm:screwdriver", false);

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @Mod.Instance(MOD_NAME)
    public static JARRM instance;

    @SidedProxy(serverSide = COMMON_PROXY, clientSide = CLIENT_PROXY)
    public static CommonProxy proxy;

    public JARRM() {
        instance = this;
        ModuleManager.findModules();
        StructureUtils.parseBlockMap();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        ModuleManager.sortModules();
        packetPipeline.initalise(MOD_NAME);
        proxy.preInit(event);
        ModuleManager.preInitModules(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ModuleManager.initModules(event);
        packetPipeline.registerPacket(RotationUpdatePacket.class);
        packetPipeline.registerPacket(ActiveUpdatePacket.class);
    }

    @Mod.EventHandler
    public void interMods(FMLInterModComms.IMCEvent imcEvent) {
        InterModsManager.processIMCMessages(imcEvent);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
        proxy.postInit(event);
        ModuleManager.postInitModules(event);
    }
}
