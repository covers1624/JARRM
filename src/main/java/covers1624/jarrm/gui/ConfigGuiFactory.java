package covers1624.jarrm.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static covers1624.jarrm.handler.ConfigurationHandler.configuration;
import static covers1624.jarrm.reference.Reference.*;

/**
 * Created by covers1624 on 2/5/2016.
 */
public class ConfigGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ConfigGui extends GuiConfig {
        public ConfigGui(GuiScreen parent) {
            super(parent, getConfigCategories(), MOD_NAME, false, false, getAbridgedConfigPath(configuration.toString()));
        }

        private static List<IConfigElement> getConfigCategories() {
            List<IConfigElement> list = new ArrayList<IConfigElement>();
            //list.add(new ConfigElement(configuration.getCategory(CATEGORY_TWEAKS).setLanguageKey(CATEGORY_TWEAKS_LANG)));
            list.add(new ConfigElement(configuration.getCategory(CATEGORY_WORLD).setLanguageKey(CATEGORY_WORLD_LANG)));
            list.add(new ConfigElement(configuration.getCategory(CATEGORY_FEATURES).setLanguageKey(CATEGORY_FEATURES_LANG)));
            return list;
        }
    }

}
