package covers1624.jarrm.reference;

/**
 * Created by covers1624 on 2/5/2016.
 */
public class Reference {
    // Mod ID's
    public static final String MOD_ID = "jarrm";

    // Mod Names
    public static final String MOD_NAME = "JARRM";
    public static final String MOD_DESCRIPTION = "Just Another RedPower Replacement Mod";

    // Version
    public static final String VERSION = "${VERSION}";

    // Resource Utils.
    public static final String MOD_PREFIX = MOD_ID.toLowerCase() + ":";
    public static final String TEXTURE_FOLDER = MOD_PREFIX + "/textures/";
    public static final String GUI_FOLDER = TEXTURE_FOLDER + "gui/";
    public static final String GUI_MACHINES_FOLDER = GUI_FOLDER + "machines/";

    public static final String CLIENT_PROXY = "covers1624.jarrm.proxy.ClientProxy";
    public static final String COMMON_PROXY = "covers1624.jarrm.proxy.CommonProxy";
    public static final String GUI_FACTORY_CLASS = "covers1624.jarrm.gui.ConfigGuiFactory";

    //Configuration Category's
    public static final String CATEGORY_WORLD = "world";
    public static final String CATEGORY_TWEAKS = "tweaks";
    public static final String CATEGORY_FEATURES = "features";

    // Lang.
    public static final String CATEGORY_WORLD_LANG = "jarrm.category.world";
    public static final String CATEGORY_TWEAKS_LANG = "jarrm.category.tweaks";
    public static final String CATEGORY_FEATURES_LANG = "jarrm.category.features";
}
