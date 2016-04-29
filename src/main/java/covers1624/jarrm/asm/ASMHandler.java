package covers1624.jarrm.asm;

import covers1624.jarrm.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * Created by covers1624 on 4/1/2016.
 */
@IFMLLoadingPlugin.MCVersion("1.9")
@IFMLLoadingPlugin.TransformerExclusions({ "covers1624.jarrm.asm.ASMHandler", "covers1624.jarrm.asm.JARRMClassTransformer" })
public class ASMHandler implements IFMLLoadingPlugin {

    public ASMHandler() {
        new LogHelper();
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { JARRMClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
