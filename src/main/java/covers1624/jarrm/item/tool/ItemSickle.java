package covers1624.jarrm.item.tool;

import com.google.common.collect.Sets;
import covers1624.jarrm.JARRM;
import covers1624.jarrm.api.item.sickle.ISickleCropHandler;
import covers1624.jarrm.registry.CustomSickleRegistry;
import covers1624.lib.math.Vector3I;
import covers1624.lib.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

/**
 * Created by covers1624 on 3/29/2016.
 */
public class ItemSickle extends ItemTool {

    //TODO Make this a blocks to destroy style thing, then calculate box to remove on the fly.
    //TODO or Make this a "box map" where the box for a given player roataion is already defined per tool material.
    private static HashMap<ToolMaterial, Float> toolRadiusMap = new HashMap<ToolMaterial, Float>();
    private static HashMap<ToolMaterial, Float> toolCropMap = new HashMap<ToolMaterial, Float>();

    public ItemSickle(ToolMaterial material, String unlocName) {
        super(material, Sets.<Block>newHashSet());
        setUnlocalizedName(unlocName);
        setCreativeTab(JARRM.toolsCreativeTab);

        toolRadiusMap.put(ToolMaterial.WOOD, 0F);//1 block
        toolRadiusMap.put(ToolMaterial.STONE, 0.5F);//2x2
        toolRadiusMap.put(ToolMaterial.IRON, 1F);//3x3
        toolRadiusMap.put(ToolMaterial.DIAMOND, 1F);
        toolRadiusMap.put(ToolMaterial.GOLD, 1F);

        toolCropMap.put(ToolMaterial.WOOD, 0.5F);//1 block
        toolCropMap.put(ToolMaterial.STONE, 1F);//2x2
        toolCropMap.put(ToolMaterial.IRON, 2F);//3x3
        toolCropMap.put(ToolMaterial.DIAMOND, 2F);
        toolCropMap.put(ToolMaterial.GOLD, 2F);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            Vector3I max = new Vector3I();
            Vector3I min = new Vector3I();

            Vector3I minCrop = new Vector3I();
            Vector3I maxCrop = new Vector3I();
            float leafRadius;
            float cropRadius;
            if (toolRadiusMap.containsKey(toolMaterial)) {
                leafRadius = toolRadiusMap.get(toolMaterial);
            } else {
                leafRadius = 1F;
            }
            if (toolCropMap.containsKey(toolMaterial)) {
                cropRadius = toolCropMap.get(toolMaterial);
            } else {
                cropRadius = 2F;
            }

            if (leafRadius == 0F) {
                min.set(0, 0, 0);
                max.set(0, 0, 0);
            } else if (leafRadius == 0.5F) {
                //TODO Raytrace player and adjust this to face away from the player.
                min.set(-1, -1, 0);
                max.set(0, 0, 0);
            } else if (leafRadius == 1.0F) {
                min.set(-1, -1, -1);
                max.set(1, 1, 1);
            }
            if (cropRadius == 0F) {
                minCrop.set(0, 0, 0);
                maxCrop.set(0, 0, 0);
            } else if (cropRadius == .5F) {
                minCrop.set(-1, 0, 0);
                maxCrop.set(0, 0, 1);
            } else if (cropRadius == 1) {
                minCrop.set(-1, 0, 1);
                maxCrop.set(-1, 0, 1);
            } else if (cropRadius == 2) {
                minCrop.set(-2, 0, 2);
                maxCrop.set(-2, 0, 2);
            }

            if (state.getBlock() != null && state.getBlock().isLeaves(state, worldIn, pos)) {
                for (int posX = min.x; posX <= max.x; posX++) {
                    for (int posY = min.y; posY <= max.y; posY++) {
                        for (int posZ = min.z; posZ <= max.z; posZ++) {
                            BlockPos curPos = new BlockPos(pos).add(posX, posY, posZ);
                            IBlockState curPosState = worldIn.getBlockState(curPos);
                            if (curPosState.getBlock().isLeaves(curPosState, worldIn, curPos)) {
                                if (attemptBreak(worldIn, curPos, curPosState, player, stack)) {
                                    if (applyDamage(stack, player)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                //TODO Other handlers.
                ISickleCropHandler handler = CustomSickleRegistry.INSTANCE.getHandlerForState(state);
                if (handler.canHandle(state)) {
                    LogHelper.info("Hello!");
                    for (int xPos = minCrop.x; xPos < maxCrop.x; xPos++) {
                        for (int zPos = minCrop.z; zPos < maxCrop.z; zPos++) {
                            BlockPos curPos = new BlockPos(pos).add(xPos, 0, zPos);
                            IBlockState curPosState = worldIn.getBlockState(curPos);
                            if (handler.shouldBeBroken(state)) {
                                if (attemptBreak(worldIn, curPos, curPosState, player, stack)) {
                                    if (applyDamage(stack, player)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                } else {

                }
            }

        }
        return false;
    }

    /**
     * Applies 1 damage to the stack.
     *
     * @param stack  Stack to apply damage.
     * @param player Player holding item.
     * @return Returns true if the item has reached 0 durability.
     */
    private boolean applyDamage(ItemStack stack, EntityPlayer player) {
        stack.damageItem(1, player);
        return player.getHeldItemMainhand() == null;
    }

    private static boolean attemptBreak(World world, BlockPos curPos, IBlockState curPosState, EntityPlayer player, ItemStack stack) {
        if (curPosState.getBlock().canHarvestBlock(world, curPos, player)) {
            if (curPosState.getBlock().removedByPlayer(curPosState, world, curPos, player, true)) {
                if (!world.isRemote) {
                    TileEntity tileEntity = world.getTileEntity(curPos);
                    curPosState.getBlock().harvestBlock(world, player, curPos, curPosState, tileEntity, stack);
                }
                return true;
            }
        }

        return false;
    }
}
