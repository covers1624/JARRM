package covers1624.jarrm.module.sickle;

import covers1624.jarrm.api.item.sickle.ICustomSickleRegistry;
import covers1624.jarrm.module.AbstractModule;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by covers1624 on 3/31/2016.
 */
public class SickleModule extends AbstractModule {

    public static void callBack(ICustomSickleRegistry registry) {
        registry.registerSickleCropHandler(new WheatCropHandler());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        FMLInterModComms.sendMessage("jarrm", "registerSickleHandler", "covers1624.jarrm.module.sickle.SickleModule.callBack");
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public String getName() {
        return "JARRM Sickle crop handling.";
    }

    @Override
    public String[] getRequiredMods() {
        return new String[] { "jarrm" };
    }

    @Override
    public String[] getIncompatibleMods() {
        return new String[] { "" };
    }

    @Override
    public String getTargetMod() {
        return "jarrm";
    }
}
