package covers1624.jarrm.worldgen;

import covers1624.jarrm.init.ModBlocks;
import covers1624.jarrm.util.LogHelper;
import covers1624.jarrm.worldgen.structure.StructureBlueprint;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by covers1624 on 4/3/2016.
 */
public class WorldGeneratorRubberTree extends WorldGenerator {
    private int height = 0;//Needs to be the adverage height if a tree.
    private int trunkHeight = 20;

    public WorldGeneratorRubberTree(int height) {

    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (position.getY() + 2 + height > 256) {
            return false;

        }

        for (BlockPos pos : BlockPos.getAllInBox(position.add(-1, -1, -1), position.add(1, -1, 1))) {
            IBlockState state = worldIn.getBlockState(pos);
            if (!(state.getBlock() == Blocks.dirt || state.getBlock() == Blocks.grass)) {
                return false;
            }
        }
        //TODO Check gen area.
        //for (BlockPos pos : BlockPos.getAllInBox(position.add(-1, 0, -1), position.add(1, 1, 1))) {
        //    IBlockState state = worldIn.getBlockState(pos);
        //    Block block = state.getBlock();
        //    if (!block.isLeaves(state, worldIn, pos) && !block.isWood(worldIn, pos) && block != Blocks.tallgrass && block != Blocks.grass && block != Blocks.vine) {
        //        return false;
        //    }
        //}

        for (BlockPos pos : BlockPos.getAllInBox(position.add(-1, -1, -1), position.add(1, -1, 1))) {
            worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
        }
        for (BlockPos pos : BlockPos.getAllInBox(position.add(-1, 0, -1), position.add(1, trunkHeight, 1))) {
            worldIn.setBlockState(pos, ModBlocks.rubberLog.getDefaultState());
        }
        for (EnumFacing dir : EnumFacing.HORIZONTALS) {
            BlockPos edgePos = position.offset(dir, 2);
            switch (dir) {
            case NORTH:
                generateVines(worldIn, rand, dir, edgePos.add(-1, 0, 0), edgePos.add(1, trunkHeight, 0));
                continue;
            case SOUTH:
                generateVines(worldIn, rand, dir, edgePos.add(-1, 0, 0), edgePos.add(1, trunkHeight, 0));
                continue;
            case WEST:
                generateVines(worldIn, rand, dir, edgePos.add(0, 0, -1), edgePos.add(0, trunkHeight, 1));
                continue;
            case EAST:
                generateVines(worldIn, rand, dir, edgePos.add(0, 0, -1), edgePos.add(0, trunkHeight, 1));
                continue;
            default:
                LogHelper.info("Invalid side in EnumFacing.HORIZONTALS [%s]", dir.getName());
            }
        }
        BlockPos rayStartPos = position.add(0, trunkHeight - 3, 0);
        int posX = rayStartPos.getX();
        double posY = rayStartPos.getY();
        int posZ = rayStartPos.getZ();

        /*int rayCount = 1000;

        NoiseGeneratorSimplex ng = new NoiseGeneratorSimplex();

        for (int ray = rayCount; ray > 0; ray--){

            double pc = ray / (double)rayCount;
            double direction = Math.PI * 2 * pc;
            int rayDist = 20 + rand.nextInt(10);

            double x = posX;
            double y = posY;
            double z = posZ;

            double r = 1 + rand.nextDouble();

            for (int rayD = 0; rayD < rayDist; rayD++) {
                double d2 = direction + (ng.func_151605_a(x/1.8, z/1.8) * r);
                x += Math.cos(d2);
                z += Math.sin(d2);

                int prevY = (int) y;
                y += rand.nextGaussian();
                if ((int) y - prevY > 1) {
                    y--;
                }
                if ((int) y - prevY < -1) {
                    y++;
                }
                placeRayPart(worldIn, new BlockPos(x, y, z));
            }
        }*/
        int rayCount = 100;

        NoiseGeneratorSimplex ng = new NoiseGeneratorSimplex();

        //region Long Rays

        for (int ray = rayCount; ray > 0; ray--) {

            double pc = ray / (double) rayCount;
            double direction = Math.PI * 2 * pc;
            int rayDist = 35 + rand.nextInt(10);

            double x = posX + rand.nextGaussian() * 3;
            double y = posY + rand.nextGaussian() * 2;
            double z = posZ + rand.nextGaussian() * 3;

            double r = 1 + rand.nextDouble();

            for (int rayD = 0; rayD < rayDist; rayD++) {
                double dpc = 1D - (rayD / (double) rayDist);

                double d2 = direction + (ng.func_151605_a(x / 1.8, z / 1.8) * r);
                x += Math.cos(d2);
                z += Math.sin(d2);

                int prevY = (int) y;
                y += rand.nextGaussian() + (dpc);
                if ((int) y - prevY > 1) {
                    y--;
                }
                if ((int) y - prevY < -1) {
                    y++;
                }

                BlockPos vec = new BlockPos(x, y, z);

                placeRayPart(worldIn, vec, rand);
            }
        }
        //endregion

        //region Short Rays

        rayCount = 100;

        for (int ray = rayCount; ray > 0; ray--) {

            double pc = ray / (double) rayCount;
            double direction = Math.PI * 2 * pc;
            int rayDist = 15 + rand.nextInt(5);

            double x = posX + rand.nextGaussian() * 3;
            double y = posY + rand.nextGaussian() * 2;
            double z = posZ + rand.nextGaussian() * 3;

            double r = 1 + rand.nextDouble();

            for (int rayD = 0; rayD < rayDist; rayD++) {
                double dpc = 1D - (rayD / (double) rayDist);

                double d2 = direction + (ng.func_151605_a(x / 1.8, z / 1.8) * r);
                x += Math.cos(d2);
                z += Math.sin(d2);

                int prevY = (int) y;
                y += rand.nextGaussian() + (dpc);
                if ((int) y - prevY > 1) {
                    y--;
                }
                if ((int) y - prevY < -1) {
                    y++;
                }

                BlockPos vec = new BlockPos(x, y, z);

                placeRayPart(worldIn, vec, rand);
            }
        }

        //endregion

        return true;
    }

    private void generateVines(World world, Random rand, EnumFacing dir, BlockPos from, BlockPos to) {
        for (BlockPos pos : BlockPos.getAllInBox(from, to)) {
            if (rand.nextInt(5) == 1 && world.getBlockState(pos).getBlock() == Blocks.air) {
                world.setBlockState(pos, Blocks.vine.getDefaultState().withProperty(BlockVine.getPropertyFor(dir.getOpposite()), true));
            }
        }
    }

    private void placeRayPart(World world, BlockPos pos, Random rand) {
        //Iterable<BlockPos> l = BlockPos.getAllInBox(startPos, startPos.add(rand.nextInt(2), rand.nextInt(2), rand.nextInt(2)));

        //for (BlockPos pos : l) {
        world.setBlockState(pos, ModBlocks.rubberLog.getDefaultState());
        for (EnumFacing side : EnumFacing.VALUES) {
            BlockPos leavesPos = pos.offset(side);
            if (world.isAirBlock(leavesPos)) {
                world.setBlockState(leavesPos, ModBlocks.rubberLeaves.getWorldGenState());
            }
        }
        //}
    }
}
