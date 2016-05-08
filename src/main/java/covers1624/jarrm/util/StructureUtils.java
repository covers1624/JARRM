package covers1624.jarrm.util;

import covers1624.jarrm.worldgen.structure.StructureBlueprint;
import covers1624.jarrm.worldgen.structure.StructureEntry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by covers1624 on 4/6/2016.
 */
public class StructureUtils {

    public static ResourceLocation blockMapResource = new ResourceLocation("jarrm:worldgen/blockMap.dat");
    private static boolean isBlockMapParsed = false;
    private static HashMap<Integer, ResourceLocation> idToBlockMap = new HashMap<Integer, ResourceLocation>();
    private static HashMap<ResourceLocation, Integer> blockToIdMap = new HashMap<ResourceLocation, Integer>();

    /**
     * Converts the file to nbt then nbt to structure.
     *///TODO Cache loaded blueprints.
    public static StructureBlueprint fromFile(ResourceLocation location) {
        InputStream fileStream = StructureUtils.class.getResourceAsStream(String.format("/assets/%s/%s", location.getResourceDomain(), location.getResourcePath()));
        return fromNBT(readCompressedToTag(fileStream));
    }

    /**
     * Converts nbt to a structure.
     */
    public static StructureBlueprint fromNBT(NBTTagCompound tagCompound) {
        ArrayList<StructureEntry> entries = new ArrayList<StructureEntry>();
        NBTTagList blockMap = tagCompound.getTagList("BlockMap", 10);

        parseBlockMap();// May as well call this, it can't hurt.

        for (int i = 0; i < blockMap.tagCount(); i++) {
            NBTTagCompound blockTag = blockMap.getCompoundTagAt(i);
            BlockPos pos = new BlockPos(blockTag.getInteger("X"), blockTag.getInteger("Y"), blockTag.getInteger("Z"));
            ResourceLocation blockLocation = idToBlockMap.get(blockTag.getInteger("Id"));
            int meta = blockTag.getInteger("Meta");
            Block block = Block.blockRegistry.getObject(blockLocation);
            if (block == null) {
                throw new RuntimeException(String.format("Unable to continue parsing StructureBlueprint, %s is not known to GameRegistry", blockLocation));
            }
            IBlockState state = block.getStateFromMeta(meta);
            entries.add(new StructureEntry(pos, state));
        }
        return new StructureBlueprint(entries);
    }

    public static void writeCompressedFromBlueprint(OutputStream stream, StructureBlueprint blueprint) {
        writeCompressedFromTag(stream, toNBT(blueprint));
    }

    /**
     * Encodes the blueprint to nbt using the current block id map.
     *
     * @param blueprint Blueprint to parse.
     * @return NBTTagCompound of the parsed blueprint.
     */
    public static NBTTagCompound toNBT(StructureBlueprint blueprint) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagList blockMap = new NBTTagList();
        for (StructureEntry entry : blueprint.getEntries()) {
            NBTTagCompound blockTag = new NBTTagCompound();
            BlockPos pos = entry.getPos();
            IBlockState state = entry.getState();
            int meta = state.getBlock().getMetaFromState(state);
            int id = blockToIdMap.get(Block.blockRegistry.getNameForObject(state.getBlock()));

            blockTag.setInteger("X", pos.getX());
            blockTag.setInteger("Y", pos.getY());
            blockTag.setInteger("Z", pos.getZ());
            blockTag.setInteger("Id", id);
            blockTag.setInteger("Meta", meta);
            blockMap.appendTag(blockTag);
        }
        nbtTagCompound.setTag("BlockMap", blockMap);
        return nbtTagCompound;
    }

    public static void writeCompressedFromTag(OutputStream stream, NBTTagCompound tagCompound) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new GZIPOutputStream(stream));
            NBTTagList blockMap = tagCompound.getTagList("BlockMap", 10);
            for (int i = 0; i < blockMap.tagCount(); i++) {
                NBTTagCompound blockTag = blockMap.getCompoundTagAt(i);
                byte idMeta = (byte) ((byte) (blockTag.getInteger("Id") & 0xF) << 4 | (byte) blockTag.getInteger("Meta"));
                outputStream.writeByte(idMeta);
                outputStream.writeByte((byte) tagCompound.getInteger("X"));
                outputStream.writeByte((byte) tagCompound.getInteger("Y"));
                outputStream.writeByte((byte) tagCompound.getInteger("Z"));
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NBTTagCompound readCompressedToTag(InputStream stream) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList blocks = new NBTTagList();
        try {
            DataInputStream inputStream = new DataInputStream(new GZIPInputStream(stream));
            while (true) {
                try {
                    NBTTagCompound blockTag = new NBTTagCompound();
                    byte idMeta = inputStream.readByte();
                    byte x = inputStream.readByte();
                    byte y = inputStream.readByte();
                    byte z = inputStream.readByte();

                    int id = (idMeta & 0xF0) >> 4;
                    int meta = (idMeta & 0xF);

                    blockTag.setInteger("Id", id);
                    blockTag.setInteger("Meta", meta);
                    blockTag.setInteger("X", x);
                    blockTag.setInteger("Y", y);
                    blockTag.setInteger("Z", z);
                    blocks.appendTag(blockTag);

                } catch (Exception e) {
                    break;
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tagCompound.setTag("BlockMap", blocks);
        return tagCompound;
    }

    public static void parseBlockMap() {
        try {
            if (!isBlockMapParsed) {
                isBlockMapParsed = true;
                InputStream fileStream = StructureUtils.class.getResourceAsStream(String.format("/assets/%s/%s", blockMapResource.getResourceDomain(), blockMapResource.getResourcePath()));
                LogHelper.info(fileStream != null);
                DataInputStream inputStream = new DataInputStream(new GZIPInputStream(fileStream));

                while (true) {
                    try {
                        String name = inputStream.readUTF();
                        int id = inputStream.readByte();
                        idToBlockMap.put(id, new ResourceLocation(name));
                        blockToIdMap.put(new ResourceLocation(name), id);
                    } catch (Exception e) {
                        break;
                    }
                }
                inputStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing blockMap.dat!", e);
        }
    }

}
