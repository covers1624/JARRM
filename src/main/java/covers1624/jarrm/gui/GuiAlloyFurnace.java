package covers1624.jarrm.gui;

import covers1624.jarrm.container.ContainerAlloyFurnace;
import covers1624.jarrm.reference.Reference;
import covers1624.jarrm.tile.TileAlloyFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAlloyFurnace extends GuiContainer {
    TileAlloyFurnace alloyFurnace;

    public GuiAlloyFurnace(InventoryPlayer inventoryPlayer, TileAlloyFurnace alloyFurnace) {
        super(new ContainerAlloyFurnace(inventoryPlayer, alloyFurnace));
        this.alloyFurnace = alloyFurnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString("Alloy Furnace", 60, 6, 4210752);
        fontRendererObj.drawString("Inventory", 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation(Reference.GUI_MACHINES_FOLDER + "alloyFurnace.png"));
        int widthScaled = (width - xSize) / 2;
        int heightScaled = (height - ySize) / 2;
        drawTexturedModalRect(widthScaled, heightScaled, 0, 0, xSize, ySize);

        if (alloyFurnace.burnTime > 0) {
            int burnScaled = alloyFurnace.getBurnScaled(12);
            drawTexturedModalRect(widthScaled + 17, heightScaled + 25 + 12 - burnScaled, 176, 12 - burnScaled, 14, burnScaled + 2);
        }

        int cookScaled = alloyFurnace.getCookScaled(24);
        drawTexturedModalRect(widthScaled + 107, heightScaled + 34, 176, 14, cookScaled + 1, 16);
    }
}
