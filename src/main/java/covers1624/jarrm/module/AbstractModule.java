package covers1624.jarrm.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 12/20/2015.
 */
public abstract class AbstractModule {

    /**
     * Contains a list of all mods not loaded for this module.
     * Please don't edit this. It should only be used for logging purposes only.
     */
    public List<String> unloadedMods = new ArrayList<String>();

    /**
     * Contains a list of incompatible mods for this module that are loaded.
     */
    public List<String> incompatableMods = new ArrayList<String>();


    //TODO Make these less.. Open. Only allow certain things to happen here, ASM Checks??
	public abstract void preInit(FMLPreInitializationEvent event);

	public abstract void init(FMLInitializationEvent event);

	public abstract void postInit(FMLPostInitializationEvent event);

    /**
     * Gets the unique name for this recipe handler.
     *
     * @return The name for the Recipe Handler.
     */
    public abstract String getName();

    /**
     * Gets the mods required mods for this recipe handler to load.
     * The list MUST be mod id's.
     *
     * @return List of mods required.
     */
    public abstract String[] getRequiredMods();

    /**
     * Gets a list of mods that MUST not be loaded for this module to work.
     * The list MUST be mod id's.
     *
     * @return List of incompatible mods.
     */
    @Deprecated // This may go away at some point.
    public abstract String[] getIncompatibleMods();

    /**
     * Gets the mod that will handle this recipe module.
     *
     * @return Modid of the mod to target.
     * NOT IMPLEMENTED, this is here as a placeholder for the eventual move to covers1624 lib.
     */
    @Deprecated
    public abstract String getTargetMod();
}
