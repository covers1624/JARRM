package covers1624.jarrm.module;

import com.google.common.collect.ImmutableList;
import covers1624.jarrm.util.LogHelper;
import covers1624.lib.classutils.ClassDiscoverer;
import covers1624.lib.classutils.IStringMatcher;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by covers1624 on 12/20/2015.
 */
public class ModuleManager {

    private static ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
    public static ImmutableList<String> loadedModules;


    public static void findModules() {
        ClassDiscoverer discoverer = new ClassDiscoverer(new IStringMatcher() {
            @Override
            public boolean matches(String test) {
                return test.contains("Module");
            }
        }, AbstractModule.class);
        discoverer.findClasses();

        for (Class clazz : discoverer.classes) {
            try {
                modules.add((AbstractModule) clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes any recipe classes that do not have the required mods loaded.
     */
    public static void sortModules() {
        LogHelper.trace("Sorting Modules...");
        ProgressManager.ProgressBar bar = ProgressManager.push("JARRM Module Sorting.", modules.size());
        bar.timeEachStep();
        //Copy so we can edit the main list.
        ArrayList<AbstractModule> copy = new ArrayList<AbstractModule>(modules);
        ArrayList<String> modulesLoaded = new ArrayList<String>();
        for (AbstractModule module : copy) {
            bar.step(module.getName());
            if (!checkModList(module)) {
                LogHelper.warn("Module {%s} will not be loaded! The mods required for it to load {%s} are not installed!", module.getName(), buildUnloadedModList(module));
                modules.remove(module);
            } else if (modulesLoaded.contains(module.getName())) {
                throw new RuntimeException(String.format("Module with name %s already exists! Report this to the official JARRM GitHub! Offending Class: %s", module.getName(), module.getClass().getName()));
            } else {
                modulesLoaded.add(module.getName());
            }
        }
        loadedModules = ImmutableList.copyOf(modulesLoaded);
        LogHelper.info("JARRM has successfully Found %s Modules {%s}", loadedModules.size(), buildModuleList(loadedModules));
        ProgressManager.pop(bar);
    }

    /**
     * Checks if the mods required for the module to load exist and adds the unloaded mods to the internal list of the Module.
     *
     * @param module Module to check.
     * @return If all mods are loaded.
     */
    private static boolean checkModList(AbstractModule module) {
        LogHelper.trace("Checking Mods for module: " + module.getName());
        boolean flag = true;
        if (module.getRequiredMods() != null) {
            for (String mod : module.getRequiredMods()) {
                if (!Loader.isModLoaded(mod)) {
                    module.unloadedMods.add(mod);
                    flag = false;
                }
            }
        }
        return flag;
    }

    private static String buildUnloadedModList(AbstractModule module) {
        StringBuilder builder = new StringBuilder();
        for (String mod : module.unloadedMods) {
            builder.append(mod);
            builder.append(", ");
        }
        String list = builder.toString();
        list = list.trim();
        if (list.endsWith(",")) {
            list = list.substring(0, list.lastIndexOf(","));
        }
        return list + ".";
    }

    private static String buildModuleList(Collection<String> modules) {
        StringBuilder builder = new StringBuilder();
        for (String module : modules) {
            builder.append(module);
            builder.append(", ");
        }
        String list = builder.toString();
        list = list.trim();
        if (list.endsWith(",")) {
            list = list.substring(0, list.lastIndexOf(","));
        }
        return list + ".";
    }



    public static void preInitModules(FMLPreInitializationEvent event) {
        for (AbstractModule module : modules) {
            module.preInit(event);
        }
    }

    public static void initModules(FMLInitializationEvent event) {
        for (AbstractModule module : modules) {
            module.init(event);
        }
    }

    public static void postInitModules(FMLPostInitializationEvent event) {
        for (AbstractModule module : modules) {
            module.postInit(event);
        }
    }
}

