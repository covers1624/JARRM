package covers1624.jarrm.init;

import covers1624.jarrm.JARRM;
import covers1624.jarrm.item.tool.*;
import covers1624.lib.item.ItemMultiType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by covers1624 on 3/27/2016.
 */
public class ModItems {

    public static ItemMultiType itemResource;

    public static ItemScrewdriver itemScrewdriver;
    public static ItemAthame itemAthame;

    public static ItemStack itemRuby;
    public static ItemStack itemGreenSapphire;
    public static ItemStack itemSapphire;
    public static ItemStack itemIngotSilver;
    public static ItemStack itemIngotTin;
    public static ItemStack itemIngotCopper;
    public static ItemStack itemNikolite;

    public static ItemStack itemIngotRed;
    public static ItemStack itemIngotBlue;
    public static ItemStack itemIngotBrass;
    public static ItemStack itemBouleSilicon;
    public static ItemStack itemWaferSilicon;
    public static ItemStack itemWaferBlue;
    public static ItemStack itemWaferRed;
    public static ItemStack itemTinplate;
    public static ItemStack itemFineCopper;
    public static ItemStack itemFineIron;
    public static ItemStack itemCopperCoil;
    public static ItemStack itemMotor;
    public static ItemStack itemCanvas;
    public static ItemStack itemNuggetIron;
    public static ItemStack itemNuggetSilver;
    public static ItemStack itemNuggetTin;
    public static ItemStack itemNuggetCopper;

    public static ItemGemSword itemRubySword;
    public static ItemGemSword itemGreenSapphireSword;
    public static ItemGemSword itemSapphireSword;

    public static ItemGemPickaxe itemRubyPickaxe;
    public static ItemGemPickaxe itemGreenSapphirePickaxe;
    public static ItemGemPickaxe itemSapphirePickaxe;

    public static ItemGemSpade itemRubySpade;
    public static ItemGemSpade itemGreenSapphireSpade;
    public static ItemGemSpade itemSapphireSpade;

    public static ItemGemHoe itemRubyHoe;
    public static ItemGemHoe itemGreenSapphireHoe;
    public static ItemGemHoe itemSapphireHoe;

    public static ItemGemAxe itemRubyAxe;
    public static ItemGemAxe itemGreenSapphireAxe;
    public static ItemGemAxe itemSapphireAxe;

    public static ItemSickle itemWoodSickle;
    public static ItemSickle itemStoneSickle;
    public static ItemSickle itemIronSickle;
    public static ItemSickle itemGoldSickle;
    public static ItemSickle itemDiamondSickle;

    public static ItemSickle itemRubySickle;
    public static ItemSickle itemGreenSapphireSickle;
    public static ItemSickle itemSapphireSickle;

    public static Item.ToolMaterial toolMaterialRuby;
    public static Item.ToolMaterial toolMaterialGreenSapphire;
    public static Item.ToolMaterial toolMaterialSapphire;

    public static void init() {

        toolMaterialRuby = EnumHelper.addToolMaterial("RUBY", 2, 500, 8.0F, 3, 12);
        toolMaterialGreenSapphire = EnumHelper.addToolMaterial("GREENSAPPHIRE", 2, 500, 8.0F, 3, 12);
        toolMaterialSapphire = EnumHelper.addToolMaterial("SAPPHIRE", 2, 500, 8.0F, 3, 12);

        //TODO Tab.
        itemResource = new ItemMultiType(JARRM.worldCreativeTab, "jarrm:itemResource");
        itemRuby = itemResource.registerSubItem("ruby");
        itemGreenSapphire = itemResource.registerSubItem("greenSapphire");
        itemSapphire = itemResource.registerSubItem("sapphire");
        itemIngotSilver = itemResource.registerSubItem("ingotSilver");
        itemIngotTin = itemResource.registerSubItem("ingotTin");
        itemIngotCopper = itemResource.registerSubItem("ingotCopper");
        itemNikolite = itemResource.registerSubItem("nikolite");

        itemIngotRed = itemResource.registerSubItem("ingotRed");
        itemIngotBlue = itemResource.registerSubItem("ingotBlue");
        itemIngotBrass = itemResource.registerSubItem("ingotBrass");
        itemBouleSilicon = itemResource.registerSubItem("bouleSilicon");
        itemWaferSilicon = itemResource.registerSubItem("waferSilicon");
        itemWaferBlue = itemResource.registerSubItem("waferBlue");
        itemWaferRed = itemResource.registerSubItem("waferRed");
        itemTinplate = itemResource.registerSubItem("tinPlate");
        itemFineCopper = itemResource.registerSubItem("fineCopper");
        itemFineIron = itemResource.registerSubItem("fineIron");
        itemCopperCoil = itemResource.registerSubItem("copperCoil");
        itemMotor = itemResource.registerSubItem("btMotor");
        itemCanvas = itemResource.registerSubItem("canvas");

        itemNuggetIron = itemResource.registerSubItem("nuggetIron");
        itemNuggetSilver = itemResource.registerSubItem("nuggetSilver");
        itemNuggetTin = itemResource.registerSubItem("nuggetTin");
        itemNuggetCopper = itemResource.registerSubItem("nuggetCopper");
        registerItem(itemResource, itemResource.name);
        toolMaterialRuby.setRepairItem(itemRuby);
        toolMaterialGreenSapphire.setRepairItem(itemGreenSapphire);
        toolMaterialSapphire.setRepairItem(itemSapphire);

        itemScrewdriver = new ItemScrewdriver();
        registerItem(itemScrewdriver, "screwdriver");

        itemAthame = new ItemAthame();
        registerItem(itemAthame, "athame");

        itemRubySword = new ItemGemSword(toolMaterialRuby, "rubySword");
        itemGreenSapphireSword = new ItemGemSword(toolMaterialGreenSapphire, "greenSapphireSword");
        itemSapphireSword = new ItemGemSword(toolMaterialSapphire, "sapphireSword");
        registerItem(itemRubySword, "rubySword");
        registerItem(itemGreenSapphireSword, "greenSapphireSword");
        registerItem(itemSapphireSword, "sapphireSword");

        itemRubyPickaxe = new ItemGemPickaxe(toolMaterialRuby, "rubyPickaxe");
        itemGreenSapphirePickaxe = new ItemGemPickaxe(toolMaterialGreenSapphire, "greenSapphirePickaxe");
        itemSapphirePickaxe = new ItemGemPickaxe(toolMaterialSapphire, "sapphirePickaxe");
        registerItem(itemRubyPickaxe, "rubyPickaxe");
        registerItem(itemGreenSapphirePickaxe, "greenSapphirePickaxe");
        registerItem(itemSapphirePickaxe, "sapphirePickaxe");

        itemRubySpade = new ItemGemSpade(toolMaterialRuby, "rubySpade");
        itemGreenSapphireSpade = new ItemGemSpade(toolMaterialGreenSapphire, "greenSapphireSpade");
        itemSapphireSpade = new ItemGemSpade(toolMaterialSapphire, "sapphireSpade");
        registerItem(itemRubySpade, "rubySpade");
        registerItem(itemGreenSapphireSpade, "greenSapphireSpade");
        registerItem(itemSapphireSpade, "sapphireSpade");

        itemRubyHoe = new ItemGemHoe(toolMaterialRuby, "rubyHoe");
        itemGreenSapphireHoe = new ItemGemHoe(toolMaterialGreenSapphire, "greenSapphireHoe");
        itemSapphireHoe = new ItemGemHoe(toolMaterialSapphire, "sapphireHoe");
        registerItem(itemRubyHoe, "rubyHoe");
        registerItem(itemGreenSapphireHoe, "greenSapphireHoe");
        registerItem(itemSapphireHoe, "sapphireHoe");

        itemRubyAxe = new ItemGemAxe(toolMaterialRuby, "rubyAxe");
        itemGreenSapphireAxe = new ItemGemAxe(toolMaterialGreenSapphire, "greenSapphireAxe");
        itemSapphireAxe = new ItemGemAxe(toolMaterialSapphire, "sapphireAxe");
        registerItem(itemRubyAxe, "rubyAxe");
        registerItem(itemGreenSapphireAxe, "greenSapphireAxe");
        registerItem(itemSapphireAxe, "sapphireAxe");

        itemWoodSickle = new ItemSickle(Item.ToolMaterial.WOOD, "woodSickle");
        itemStoneSickle = new ItemSickle(Item.ToolMaterial.STONE, "stoneSickle");
        itemIronSickle = new ItemSickle(Item.ToolMaterial.IRON, "ironSickle");
        itemGoldSickle = new ItemSickle(Item.ToolMaterial.GOLD, "goldSickle");
        itemDiamondSickle = new ItemSickle(Item.ToolMaterial.DIAMOND, "diamondSickle");

        itemRubySickle = new ItemSickle(toolMaterialRuby, "rubySickle");
        itemGreenSapphireSickle = new ItemSickle(toolMaterialGreenSapphire, "greenSapphireSickle");
        itemSapphireSickle = new ItemSickle(toolMaterialSapphire, "sapphireSickle");

        registerItem(itemWoodSickle, "woodSickle");
        registerItem(itemStoneSickle, "stoneSickle");
        registerItem(itemIronSickle, "ironSickle");
        registerItem(itemGoldSickle, "goldSickle");
        registerItem(itemDiamondSickle, "diamondSickle");


        registerItem(itemRubySickle, "rubySickle");
        registerItem(itemGreenSapphireSickle, "greenSapphireSickle");
        registerItem(itemSapphireSickle, "sapphireSickle");


    }

    @SideOnly(Side.CLIENT)
    public static void registerModelVariants() {
        itemResource.registerModelVariants();
        registerToolVariantModel(itemScrewdriver);
        registerToolVariantModel(itemAthame);

        registerToolVariantModel(itemRubySword);
        registerToolVariantModel(itemGreenSapphireSword);
        registerToolVariantModel(itemSapphireSword);

        registerToolVariantModel(itemRubyPickaxe);
        registerToolVariantModel(itemGreenSapphirePickaxe);
        registerToolVariantModel(itemSapphirePickaxe);

        registerToolVariantModel(itemRubySpade);
        registerToolVariantModel(itemGreenSapphireSpade);
        registerToolVariantModel(itemSapphireSpade);

        registerToolVariantModel(itemRubyHoe);
        registerToolVariantModel(itemGreenSapphireHoe);
        registerToolVariantModel(itemSapphireHoe);

        registerToolVariantModel(itemRubyAxe);
        registerToolVariantModel(itemGreenSapphireAxe);
        registerToolVariantModel(itemSapphireAxe);

        registerToolVariantModel(itemWoodSickle);
        registerToolVariantModel(itemStoneSickle);
        registerToolVariantModel(itemIronSickle);
        registerToolVariantModel(itemGoldSickle);
        registerToolVariantModel(itemDiamondSickle);
        registerToolVariantModel(itemRubySickle);
        registerToolVariantModel(itemGreenSapphireSickle);
        registerToolVariantModel(itemSapphireSickle);
    }

    //Defaults to 0 for meta.
    private static void registerToolVariantModel(Item item) {
        registerToolVariantModel(item, 0);
    }

    private static void registerToolVariantModel(Item item, int meta) {
        String name = Item.itemRegistry.getNameForObject(item).getResourcePath().toLowerCase();
        ModelResourceLocation screwdriverLocation = new ModelResourceLocation("jarrm:tools", "type=" + name);
        ModelLoader.setCustomModelResourceLocation(item, meta, screwdriverLocation);
    }

    private static void registerItem(Item item, String registryName){
        GameRegistry.register(item.setRegistryName(registryName));
    }
}
