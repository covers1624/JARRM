package covers1624.jarrm.asm;

import codechicken.lib.asm.ASMBlock;
import codechicken.lib.asm.ASMReader;
import codechicken.lib.asm.ModularASMTransformer;
import covers1624.lib.util.LogHelper;
import net.minecraft.launchwrapper.IClassTransformer;

import java.util.Map;

/**
 * Created by covers1624 on 4/1/2016.
 */
public class JARRMClassTransformer implements IClassTransformer {

    private ModularASMTransformer transformer = new ModularASMTransformer();

    public JARRMClassTransformer() {
        Map<String, ASMBlock> blocks = ASMReader.loadResource("/assets/jarrm/asm/hooks.asm");
        //Not needed anymore
        //transformer.add(new MethodInjector(new ObfMapping("net/minecraft/client/renderer/RenderGlobal", "func_72712_a", "()V"), blocks.get("n_LeavesGraphics"), blocks.get("i_LeavesGraphics"), false));
    }

    @Override
    public byte[] transform(String name, String tName, byte[] bytes) {
        try {
            return transformer.transform(name, bytes);
        } catch (Exception e) {
            LogHelper.bigFatal("There was a fatal error thrown by the ModularASMTransformer! Things may not work well..");
            e.printStackTrace();
        }
        return bytes;
    }
}
