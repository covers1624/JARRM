package covers1624.jarrm.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlloyFurnace extends Slot {
    private EntityPlayer player;
    int totalCrafted;

    public SlotAlloyFurnace(EntityPlayer player, IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if (getHasStack()) {
            totalCrafted += Math.min(amount, getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
        onCrafting(stack);
        super.onPickupFromSlot(player, stack);
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        totalCrafted += amount;
        onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(player.worldObj, player, totalCrafted);
        totalCrafted = 0;
    }
}
