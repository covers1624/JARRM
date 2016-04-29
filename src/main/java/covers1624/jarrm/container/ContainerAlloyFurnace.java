package covers1624.jarrm.container;

import covers1624.jarrm.slot.SlotAlloyFurnace;
import covers1624.jarrm.tile.TileAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by covers1624 on 3/28/2016.
 */
public class ContainerAlloyFurnace extends ContainerBase {
    private TileAlloyFurnace tileFurnace;

    public ContainerAlloyFurnace(InventoryPlayer inventoryPlayer, TileAlloyFurnace tileFurnace) {
        super(tileFurnace);
        this.tileFurnace = tileFurnace;
        int yPos;
        int zPos;

        for (yPos = 0; yPos < 3; ++yPos) {
            for (zPos = 0; zPos < 3; ++zPos) {
                addSlotToContainer(new Slot(tileFurnace, zPos + yPos * 3, 48 + zPos * 18, 17 + yPos * 18));
            }
        }

        addSlotToContainer(new Slot(tileFurnace, 9, 17, 42));
        addSlotToContainer(new SlotAlloyFurnace(inventoryPlayer.player, tileFurnace, 10, 141, 35));

        for (yPos = 0; yPos < 3; ++yPos) {
            for (zPos = 0; zPos < 9; ++zPos) {
                addSlotToContainer(new Slot(inventoryPlayer, zPos + yPos * 9 + 9, 8 + zPos * 18, 84 + yPos * 18));
            }
        }

        for (yPos = 0; yPos < 9; ++yPos) {
            addSlotToContainer(new Slot(inventoryPlayer, yPos, 8 + yPos * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileFurnace.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack movedStack = null;
        Slot slot = inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            movedStack = itemStack.copy();

            if (slotIndex < 11) {
                if (!mergeItemStack(itemStack, 11, 47, true)) {
                    return null;
                }
            } else if (!mergeItemStack(itemStack, 0, 9, false)) {
                return null;
            }

            if (itemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack.stackSize == movedStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemStack);
        }

        return movedStack;
    }
}