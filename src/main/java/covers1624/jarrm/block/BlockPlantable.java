package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.block.itemblock.ItemBlockMultiType;
import covers1624.jarrm.init.ModBlocks;
import covers1624.jarrm.util.LogHelper;
import covers1624.jarrm.worldgen.WorldGeneratorRubberTree;
import covers1624.lib.api.block.property.PropertyString;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import static covers1624.jarrm.reference.AABBReference.SAPLING_AABB;
import static covers1624.jarrm.reference.VariantReference.plantableNames;
import static covers1624.jarrm.reference.VariantReference.plantableNamesList;

/**
 * Created by covers1624 on 4/3/2016.
 */
public class BlockPlantable extends BlockBush implements IGrowable {

    public static final PropertyString VARIANTS = new PropertyString("type", plantableNamesList);

    public BlockPlantable() {
        super();
        setDefaultState(getStateFromMeta(0));
        setCreativeTab(JARRM.worldCreativeTab);
        setUnlocalizedName("plantable");
        setSoundType(SoundType.PLANT);
    }

    public BlockPlantable registerVariants() {
        for (int i = 0; i < plantableNames.length; i++) {
            String variant = plantableNames[i];
            ItemBlockMultiType itemBlock = (ItemBlockMultiType) Item.getItemFromBlock(this);
            itemBlock.registerSubItem(i, variant);
        }
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            registerModelVariants();
        }
        return this;
    }

    @SideOnly(Side.CLIENT)
    private void registerModelVariants() {
        for (int i = 0; i < plantableNamesList.size(); i++) {
            String variant = plantableNamesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation("jarrm:plantable", "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.blockPlantable), i, location);
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                attemptTreeGen(worldIn, pos, state, rand);
            }
        }
    }

    public void attemptTreeGen(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        //StructureBlueprint treeStructure = StructureUtils.fromFile(new ResourceLocation("jarrm:worldgen/tree.dat"));
        //treeStructure.generateStructure(worldIn, pos);
        WorldGeneratorRubberTree tree = new WorldGeneratorRubberTree(40);//TODO
        tree.generate(worldIn, rand, pos);
        LogHelper.info("Tree Gen!");

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return plantableNamesList.indexOf(String.valueOf(state.getValue(VARIANTS)));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getBlockState().getBaseState().withProperty(VARIANTS, plantableNamesList.get(meta));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANTS);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < plantableNamesList.size(); meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        attemptTreeGen(worldIn, pos, state, rand);
    }
}
