package covers1624.jarrm.block;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by covers1624 on 4/1/2016.
 */
public class BlockRubberLeaves extends Block implements IShearable {

    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    protected static boolean leavesFancy;
    int[] surroundings;

    public BlockRubberLeaves() {
        super(Material.leaves);
        setTickRandomly(true);
        setUnlocalizedName("rubberLeaves");
        setDefaultState(getDefaultState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, false));
        setCreativeTab(JARRM.worldCreativeTab);
        setHardness(0.2F);
        setLightOpacity(1);
        setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        leavesFancy = !Blocks.leaves.isOpaqueCube(null);//Safe to pass null, it is only vanilla leaves.
        return !leavesFancy;
    }

    @SideOnly(Side.CLIENT)
    public static void setGraphicsLevel(boolean fancy) {
        leavesFancy = fancy;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        int area = 1;//TODO
        int chunkArea = area + 1;//TODO
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (worldIn.isAreaLoaded(new BlockPos(x - chunkArea, y - chunkArea, z - chunkArea), new BlockPos(x + chunkArea, y + chunkArea, z + chunkArea))) {
            for (int xPos = -area; xPos <= area; ++xPos) {
                for (int yPos = -area; yPos <= area; ++yPos) {
                    for (int zPos = -area; zPos <= area; ++zPos) {
                        BlockPos blockpos = pos.add(xPos, yPos, zPos);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                        }
                    }
                }
            }
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (state.getValue(CHECK_DECAY) && state.getValue(DECAYABLE)) {
                int area = 4;
                int chunkArea = area + 1;
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                int j1 = 32;
                int k1 = j1 * j1;
                int l1 = j1 / 2;

                if (this.surroundings == null) {
                    this.surroundings = new int[j1 * j1 * j1];
                }

                if (worldIn.isAreaLoaded(new BlockPos(x - chunkArea, y - chunkArea, z - chunkArea), new BlockPos(x + chunkArea, y + chunkArea, z + chunkArea))) {
                    BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

                    for (int xPos = -area; xPos <= area; ++xPos) {
                        for (int yPos = -area; yPos <= area; ++yPos) {
                            for (int zPos = -area; zPos <= area; ++zPos) {
                                IBlockState iblockstate = worldIn.getBlockState(mutableBlockPos.set(x + xPos, y + yPos, z + zPos));
                                Block block = iblockstate.getBlock();

                                if (!block.canSustainLeaves(iblockstate, worldIn, mutableBlockPos.set(x + xPos, y + yPos, z + zPos))) {
                                    if (block.isLeaves(iblockstate, worldIn, mutableBlockPos.set(x + xPos, y + yPos, z + zPos))) {
                                        this.surroundings[(xPos + l1) * k1 + (yPos + l1) * j1 + zPos + l1] = -2;
                                    } else {
                                        this.surroundings[(xPos + l1) * k1 + (yPos + l1) * j1 + zPos + l1] = -1;
                                    }
                                } else {
                                    this.surroundings[(xPos + l1) * k1 + (yPos + l1) * j1 + zPos + l1] = 0;
                                }
                            }
                        }
                    }

                    for (int i3 = 1; i3 <= 4; ++i3) {
                        for (int j3 = -area; j3 <= area; ++j3) {
                            for (int k3 = -area; k3 <= area; ++k3) {
                                for (int l3 = -area; l3 <= area; ++l3) {
                                    if (this.surroundings[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1] == i3 - 1) {
                                        if (this.surroundings[(j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1] == -2) {
                                            this.surroundings[(j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1] = i3;
                                        }

                                        if (this.surroundings[(j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1] == -2) {
                                            this.surroundings[(j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1] = i3;
                                        }

                                        if (this.surroundings[(j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1] == -2) {
                                            this.surroundings[(j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1] = i3;
                                        }

                                        if (this.surroundings[(j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1] == -2) {
                                            this.surroundings[(j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1] = i3;
                                        }

                                        if (this.surroundings[(j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1)] == -2) {
                                            this.surroundings[(j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1)] = i3;
                                        }

                                        if (this.surroundings[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1] == -2) {
                                            this.surroundings[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1] = i3;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                int l2 = this.surroundings[l1 * k1 + l1 * j1 + l1];

                if (l2 >= 0) {
                    worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, false), 4);
                } else {
                    this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
                    worldIn.setBlockToAir(pos);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    //Rain Particles
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP) && rand.nextInt(15) == 1) {
            double xPos = (double) ((float) pos.getX() + rand.nextFloat());
            double yPos = (double) pos.getY() - 0.05D;
            double zPos = (double) ((float) pos.getZ() + rand.nextFloat());
            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {
        if (!state.getValue(CHECK_DECAY)) {
            world.setBlockState(pos, state.withProperty(CHECK_DECAY, true), 4);
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return !(!leavesFancy && blockAccess.getBlockState(pos.offset(side)).getBlock() == this) && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.blockPlantable);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos))));
    }
}
