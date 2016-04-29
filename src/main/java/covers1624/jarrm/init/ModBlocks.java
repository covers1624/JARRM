package covers1624.jarrm.init;

import covers1624.jarrm.block.*;
import covers1624.jarrm.block.itemblock.ItemBlockMultiType;
import covers1624.jarrm.block.itemblock.ItemBlockOre;
import covers1624.jarrm.block.itemblock.ItemBlockStone;
import covers1624.jarrm.reference.VariantReference;
import covers1624.jarrm.tile.TileAlloyFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class ModBlocks {

    public static BlockOre blockOre;
    public static BlockStone blockStone;
    public static BlockAppliance blockAppliance;
    public static BlockPlantable blockPlantable;
    public static BlockRubberLeaves rubberLeaves;
    public static BlockRubberLog rubberLog;

    public static void init() {
        blockOre = new BlockOre();
        //GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "ores");
        GameRegistry.register(blockOre.setRegistryName("ores"));
        GameRegistry.register(new ItemBlockOre(blockOre).setRegistryName("ores"));
        blockStone = new BlockStone();
        //GameRegistry.registerBlock(blockStone, ItemBlockStone.class, "stone");
        GameRegistry.register(blockStone.setRegistryName("stone"));
        GameRegistry.register(new ItemBlockStone(blockStone).setRegistryName("stone"));
        blockAppliance = new BlockAppliance();
        //GameRegistry.registerBlock(blockAppliance, ItemBlockMultiType.class, "appliance");
        GameRegistry.register(blockAppliance.setRegistryName("appliance"));
        GameRegistry.register(new ItemBlockMultiType(blockAppliance).setRegistryName("appliance"));
        blockAppliance.addSubItemAndTileAndRegister(0, "alloyFurnace", TileAlloyFurnace.class);

        blockPlantable = new BlockPlantable();
        //GameRegistry.registerBlock(blockPlantable, ItemBlockMultiType.class, "plantable");
        GameRegistry.register(blockPlantable.setRegistryName("plantable"));
        GameRegistry.register(new ItemBlockMultiType(blockPlantable).setRegistryName("plantable"));
        blockPlantable.registerVariants();//TODO this to all blocks.

        rubberLeaves = new BlockRubberLeaves();
        //GameRegistry.registerBlock(rubberLeaves, "rubberLeaves");
        GameRegistry.register(rubberLeaves.setRegistryName("rubberLeaves"));

        rubberLog = new BlockRubberLog();
        //GameRegistry.registerBlock(rubberLog, "rubberLog");
        GameRegistry.register(rubberLog.setRegistryName("rubberLog"));

    }

    @SideOnly(Side.CLIENT)
    public static void registerModelVariants() {
        for (int i = 0; i < VariantReference.oreNamesList.size(); i++) {
            String variant = VariantReference.oreNamesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation("jarrm:ores", "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.blockOre), i, location);
        }

        for (int i = 0; i < VariantReference.stoneNamesList.size(); i++) {
            String variant = VariantReference.stoneNamesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation("jarrm:stone", "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.blockStone), i, location);
        }
        for (int i = 0; i < VariantReference.applianceNamesList.size(); i++) {
            String variant = VariantReference.applianceNamesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation("jarrm:appliance", "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.blockAppliance), i, location);
        }
        StateMap rubberLeavesStateMap = new StateMap.Builder().ignore(BlockRubberLeaves.CHECK_DECAY, BlockRubberLeaves.DECAYABLE).build();
        ModelLoader.setCustomStateMapper(rubberLeaves, rubberLeavesStateMap);

        ModelResourceLocation rubberLeavesInventory = new ModelResourceLocation("jarrm:rubberLeaves", "normal");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(rubberLeaves), 0, rubberLeavesInventory);

        ModelResourceLocation rubberLogInventory = new ModelResourceLocation("jarrm:rubberLog", "normal");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(rubberLog), 0, rubberLogInventory);
    }

    @SideOnly(Side.CLIENT)
    public static void registerColourHandlers() {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
                return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        }, ModBlocks.rubberLeaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
            }
        }, ModBlocks.rubberLeaves);
    }

}
