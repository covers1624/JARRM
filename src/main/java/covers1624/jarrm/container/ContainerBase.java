package covers1624.jarrm.container;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;

import java.util.HashMap;

/**
 * Created by covers1624 on 3/28/2016.
 */
public abstract class ContainerBase extends Container {
    private IInventory syncable;
    private HashMap<Integer, Integer> syncCache;

    public ContainerBase(IInventory syncable) {
        this.syncable = syncable;
        syncCache = new HashMap<Integer, Integer>();
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int syncables = syncable.getFieldCount();
        if (syncables > 1) {
            for (int id = 0; id < syncables; id++) {
                int cache = -1;
                int value = syncable.getField(id);
                if (syncCache.containsKey(id)) {
                    cache = syncCache.get(id);
                }
                if (cache != value) {
                    sendUpdate(id, value);
                    syncCache.put(id, value);
                }
            }
        }
    }

    @Override
    public void updateProgressBar(int id, int value) {
        syncable.setField(id, value);
    }

    private void sendUpdate(int id, int value) {
        for (ICrafting listener : this.listeners) {
            listener.sendProgressBarUpdate(this, id, value);
        }
    }

}
