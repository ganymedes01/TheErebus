package erebus.client.render.item;

import erebus.client.model.item.ModelWarHammer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWarHammer extends TileEntityItemStackRenderer {
	private final ModelWarHammer MODEL_HAMMER = new ModelWarHammer();
	public static ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/items/war_hammer.png");

    @Override
    public void renderByItem(ItemStack stack) {
    	this.renderByItem(stack, 1.0F);
    }

    @Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(-90F, 0F, 1F, 0F);
		float scale = 1.75F;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("charge"))
			scale = (float) (1.75F + stack.getTagCompound().getInteger("charge") * 0.03F);
		GlStateManager.translate(0F, 0.25F - scale, 0F);
		GlStateManager.scale(scale, scale, scale);
		MODEL_HAMMER.render();
		GlStateManager.popMatrix();
	}
}