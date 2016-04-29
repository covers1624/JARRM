package covers1624.jarrm.worldgen;

import covers1624.jarrm.handler.ConfigurationHandler;
import covers1624.jarrm.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by covers1624 on 3/26/2016.
 */
public class WorldGeneratorOre implements IWorldGenerator {

    WorldGenMinable oreRuby;
    WorldGenMinable oreGreenSapphire;
    WorldGenMinable oreSapphire;
    WorldGenMinable oreSilver;
    WorldGenMinable oreCopper;
    WorldGenMinable oreTin;
    WorldGenMinable oreNikolite;
    WorldGenMinable oreTungsten;

    public WorldGeneratorOre() {
        oreRuby = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(0), 7);
        oreGreenSapphire = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(1), 7);
        oreSapphire = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(2), 7);
        oreSilver = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(3), 8);
        oreCopper = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(4), 8);
        oreTin = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(5), 8);
        oreNikolite = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(6), 10);
        oreTungsten = new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(7), 4);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.isSurfaceWorld()) {
            generateUndergroundOres(world, chunkX * 16, chunkZ * 16, random);
        }
    }

    private void generateUndergroundOres(World world, int xChunk, int zChunk, Random random) {
        int xPos;
        int yPos;
        int zPos;
        if (ConfigurationHandler.generateRuby) {
            for (int i = 0; i <= 2; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(48);
                zPos = zChunk + random.nextInt(16);
                oreRuby.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateSapphire) {
            for (int i = 0; i <= 2; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(48);
                zPos = zChunk + random.nextInt(16);
                oreGreenSapphire.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateGreenSapphire) {
            for (int i = 0; i <= 2; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(48);
                zPos = zChunk + random.nextInt(16);
                oreSapphire.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateSilver) {
            for (int i = 0; i <= 4; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(32);
                zPos = zChunk + random.nextInt(16);
                oreSilver.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateCopper) {
            for (int i = 0; i <= 20; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(64);
                zPos = zChunk + random.nextInt(16);
                oreCopper.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateTin) {
            for (int i = 0; i <= 10; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = random.nextInt(48);
                zPos = zChunk + random.nextInt(16);
                oreTin.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateNikolite) {
            for (int i = 0; i <= 4; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = 10 + random.nextInt(16);
                zPos = zChunk + random.nextInt(16);
                oreNikolite.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
        if (ConfigurationHandler.generateTungsten) {
            for (int i = 0; i <= 1; i++) {
                xPos = xChunk + random.nextInt(16);
                yPos = 10 + random.nextInt(16);
                zPos = zChunk + random.nextInt(16);
                oreTungsten.generate(world, random, new BlockPos(xPos, yPos, zPos));
            }
        }
    }
}
