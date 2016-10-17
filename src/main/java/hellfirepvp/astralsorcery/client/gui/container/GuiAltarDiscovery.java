package hellfirepvp.astralsorcery.client.gui.container;

import hellfirepvp.astralsorcery.client.util.SpecialTextureLibrary;
import hellfirepvp.astralsorcery.client.util.SpriteLibrary;
import hellfirepvp.astralsorcery.client.util.resource.AssetLibrary;
import hellfirepvp.astralsorcery.client.util.resource.AssetLoader;
import hellfirepvp.astralsorcery.client.util.resource.BindableResource;
import hellfirepvp.astralsorcery.client.util.resource.SpriteSheetResource;
import hellfirepvp.astralsorcery.common.container.ContainerAltarDiscovery;
import hellfirepvp.astralsorcery.common.crafting.altar.AbstractAltarRecipe;
import hellfirepvp.astralsorcery.common.crafting.altar.AltarRecipeRegistry;
import hellfirepvp.astralsorcery.common.tile.TileAltar;
import hellfirepvp.astralsorcery.common.util.data.Tuple;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: GuiAltar
 * Created by HellFirePvP
 * Date: 21.09.2016 / 15:20
 */
public class GuiAltarDiscovery extends GuiAltarBase {

    private static final BindableResource texAltarDiscovery = AssetLibrary.loadTexture(AssetLoader.TextureLocation.GUI, "guiAltar1");
    private static final BindableResource texBlack = AssetLibrary.loadTexture(AssetLoader.TextureLocation.MISC, "black");

    public GuiAltarDiscovery(InventoryPlayer playerInv, TileAltar tileAltar) {
        super(playerInv, tileAltar);
    }

    @Override
    public void initGui() {
        this.xSize = 232;
        this.ySize = 190;
        super.initGui();
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        AbstractAltarRecipe rec = findCraftableRecipe();
        if(rec != null) {
            ItemStack out = rec.getOutputForRender();
            zLevel = 10F;
            itemRender.zLevel = 10F;

            RenderHelper.enableGUIStandardItemLighting();

            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
            GL11.glPushMatrix();
            GL11.glTranslated(170, 35, 0);
            GL11.glScaled(1.7, 1.7, 1.7);

            itemRender.renderItemAndEffectIntoGUI(mc.thePlayer, out, 0, 0);
            itemRender.renderItemOverlayIntoGUI(fontRendererObj, out, 0, 0, null);

            GL11.glPopMatrix();
            GL11.glPopAttrib();

            RenderHelper.disableStandardItemLighting();

            zLevel = 0F;
            itemRender.zLevel = 0F;

            SpecialTextureLibrary.refreshTextureBindState();
        }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        texBlack.bind();
        drawRect(guiLeft + 22, guiTop + 77, 190, 11);

        float percFilled = containerAltarBase.tileAltar.getAmbientStarlightPercent();
        if(percFilled > 0) {
            SpriteSheetResource spriteStarlight = SpriteLibrary.spriteStarlight;
            spriteStarlight.getResource().bind();
            int t = containerAltarBase.tileAltar.getTicksExisted();
            Tuple<Double, Double> uvOffset = spriteStarlight.getUVOffset(t);
            drawRect(guiLeft + 22, guiTop + 77, (int) (190 * percFilled), 11,
                    uvOffset.key, uvOffset.value,
                    spriteStarlight.getULength() * percFilled, spriteStarlight.getVLength() * percFilled);
        }

        texAltarDiscovery.bind();
        drawRect(guiLeft, guiTop, xSize, ySize);

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

}