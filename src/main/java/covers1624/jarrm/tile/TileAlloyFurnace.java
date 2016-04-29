package covers1624.jarrm.tile;

import com.google.common.collect.Lists;
import covers1624.jarrm.api.recipe.AlloyFurnaceRecipe;
import covers1624.jarrm.registry.AlloyFurnaceRegistry;
import covers1624.jarrm.reference.GuiIds;
import covers1624.lib.util.BlockUtils;
import covers1624.lib.util.ItemUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by covers1624 on 3/27/2016.
 */
public class TileAlloyFurnace extends TileBase implements ISidedInventory {

    private ItemStack[] contents = new ItemStack[11];
    public int totalBurn = 0;
    public int burnTime = 0;
    public int cookTime = 0;

    @Override
    public void openGui(World world, BlockPos pos, EntityPlayer player) {
        openGui(GuiIds.ALLOY_FURNACE, world, pos, player);
    }

    @Override
    public void update() {
        super.update();
        boolean noFuel = false;

        if (burnTime > 0) {
            --burnTime;

            if (burnTime == 0) {
                noFuel = true;
                setActive(false);
            }
        }

        if (!worldObj.isRemote) {
            boolean canSmelt = canSmelt();

            if (burnTime == 0 && canSmelt && contents[9] != null) {
                burnTime = totalBurn = ItemUtils.getBurnTime(contents[9]);

                if (burnTime > 0) {
                    setActive(true);

                    if (contents[9].getItem().hasContainerItem(contents[9])) {
                        contents[9] = new ItemStack(contents[9].getItem().getContainerItem());
                    } else {
                        --contents[9].stackSize;
                    }

                    if (contents[9].stackSize == 0) {
                        contents[9] = null;
                    }

                    if (!noFuel) {
                        markDirty();
                        updateBlock();
                        updateLight();
                    }
                }
            }

            if (burnTime > 0 && canSmelt) {
                ++cookTime;

                if (cookTime == 200) {
                    cookTime = 0;
                    smeltItem();
                    markDirty();
                }
            } else {
                cookTime = 0;
            }

            if (noFuel) {
                updateBlock();
                updateLight();
            }
        }
    }

    boolean canSmelt() {
        AlloyFurnaceRecipe recipe = AlloyFurnaceRegistry.getRecipeClass(contents);
        if (recipe == null) {
            return false;
        } else if (contents[10] == null) {
            return true;
        } else if (!contents[10].isItemEqual(recipe.getRecipeOutput())) {
            return false;
        } else {
            int size = contents[10].stackSize + recipe.getRecipeOutput().stackSize;
            return size <= getInventoryStackLimit() && size <= recipe.getRecipeOutput().getMaxStackSize();
        }
    }

    void smeltItem() {
        if (canSmelt()) {
            AlloyFurnaceRecipe recipe = AlloyFurnaceRegistry.getRecipeClass(contents);
            ItemStack crafted = AlloyFurnaceRegistry.doAlloyCrafting(recipe, contents);
            if (crafted != null) {
                if (contents[10] == null) {
                    contents[10] = crafted.copy();
                } else {
                    contents[10].stackSize += crafted.stackSize;
                }
            }
        }
    }

    public int getCookScaled(int scale) {
        return this.cookTime * scale / 200;
    }

    public int getBurnScaled(int scale) {
        return totalBurn == 0 ? 0 : burnTime * scale / totalBurn;
    }

    @Override
    public List<ItemStack> getHarvestItems() {
        return Lists.newArrayList(contents);
    }

    @Override
    public int getSizeInventory() {
        return 11;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return contents[index];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        if (contents[slot] == null) {
            return null;
        } else {
            ItemStack itemStack;

            if (contents[slot].stackSize <= size) {
                itemStack = contents[slot];
                contents[slot] = null;
                markDirty();
                return itemStack;
            } else {
                itemStack = contents[slot].splitStack(size);

                if (contents[slot].stackSize == 0) {
                    contents[slot] = null;
                }

                markDirty();
                return itemStack;
            }
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (contents[index] == null) {
            return null;
        } else {
            ItemStack stack = contents[index];
            contents[index] = null;
            return stack;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        contents[index] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getName() {
        return "AlloyFurnace";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(getPos()) == this && BlockUtils.isEntityInRage(getPos(), player, 64);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Items", 10);
        contents = new ItemStack[getSizeInventory()];

        for (int index = 0; index < tagList.tagCount(); ++index) {
            NBTTagCompound stackTag = tagList.getCompoundTagAt(index);
            int slot = stackTag.getByte("Slot") & 255;

            if (slot >= 0 && slot < contents.length) {
                contents[slot] = ItemStack.loadItemStackFromNBT(stackTag);
            }
        }

        totalBurn = tagCompound.getShort("TotalBurn");
        burnTime = tagCompound.getShort("BurnTime");
        cookTime = tagCompound.getShort("CookTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();

        for (int index = 0; index < contents.length; ++index) {
            if (contents[index] != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) index);
                contents[index].writeToNBT(stackTag);
                tagList.appendTag(stackTag);
            }
        }

        tagCompound.setTag("Items", tagList);
        tagCompound.setShort("TotalBurn", (short) totalBurn);
        tagCompound.setShort("BurnTime", (short) burnTime);
        tagCompound.setShort("CookTime", (short) cookTime);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (isActive()) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                world.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.block_furnace_fire_crackle, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            switch (getRotation()) {
            case WEST:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case EAST:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case NORTH:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                break;
            case SOUTH:
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
                world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        switch (id) {
        case 0:
            return this.totalBurn;
        case 1:
            return this.burnTime;
        case 2:
            return this.cookTime;
        default:
            return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
        case 0:
            this.totalBurn = value;
            break;

        case 1:
            this.burnTime = value;
            break;

        case 2:
            this.cookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 3;
    }

    @Override
    public void clear() {

    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return null;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }
}
