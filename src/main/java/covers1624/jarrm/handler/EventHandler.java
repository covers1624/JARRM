package covers1624.jarrm.handler;

import covers1624.jarrm.item.tool.ItemAthame;
import covers1624.lib.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static covers1624.jarrm.reference.Reference.MOD_ID;

/**
 * Created by covers1624 on 2/5/2016.
 */
public class EventHandler {

	@SubscribeEvent
	public void configurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if (event.getModID().equals(MOD_ID)){
			ConfigurationHandler.loadConfiguration();
		}
	}

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        Entity hitter = event.getSource().getEntity();
        if (hitter instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) hitter;
            ItemStack heldStack = player.getHeldItemMainhand();
            //TODO A thing...
            if (heldStack != null && heldStack.getItem() instanceof ItemAthame && (event.getEntityLiving() instanceof EntityEnderman || event.getEntityLiving() instanceof EntityDragon)){
                event.setAmount(25);
            }
        }
    }

}
