package covers1624.jarrm.handler;

import covers1624.jarrm.api.item.sickle.ICustomSickleRegistry;
import covers1624.jarrm.registry.CustomSickleRegistry;
import covers1624.lib.util.LogHelper;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.lang.reflect.Method;

/**
 * Created by covers1624 on 3/31/2016.
 */
public class InterModsManager {

    public static void processIMCMessages(FMLInterModComms.IMCEvent imcEvent) {

        for (FMLInterModComms.IMCMessage message : imcEvent.getMessages()) {
            if (message.key.equals("registerSickleHandler")) {
                if (message.isStringMessage()) {
                    try {
                        processRegisterSickleHandlerMessage(message.getStringValue());
                    } catch (Exception e) {
                        LogHelper.error("Unable to reflect inter mods message. Data: [%s].", message.getStringValue());
                        e.printStackTrace();
                    }
                } else {
                    LogHelper.error("IMC Message received from mod %s for registerSickleHandler was not a string message!", message.getSender());
                }
            }
        }
    }

    private static void processRegisterSickleHandlerMessage(String data) throws Exception {
        int lastDot = data.lastIndexOf('.');
        if (lastDot >= data.length()) {
            LogHelper.error("Invalid message data! Received: [%s], Expected [some.class.path.someCallBackMethod]", data);
        }
        String className = data.substring(0, lastDot);
        String methodName = data.substring(lastDot + 1, data.length());

        Class<?> callBackClass = Class.forName(className);
        Method callBackMethod = callBackClass.getDeclaredMethod(methodName, ICustomSickleRegistry.class);
        callBackMethod.invoke(null, CustomSickleRegistry.INSTANCE);
    }

}
