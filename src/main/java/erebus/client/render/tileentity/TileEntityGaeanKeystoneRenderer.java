package erebus.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.block.GaeanKeystone;

public class TileEntityGaeanKeystoneRenderer extends TileEntitySpecialRenderer {
	private static final ResourceLocation beamTexture = new ResourceLocation("textures/entity/beacon_beam.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (!GaeanKeystone.isGemActive(tile.getBlockMetadata()))
			return;

		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		Tessellator tessellator = Tessellator.instance;
		bindTexture(beamTexture);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		OpenGlHelper.glBlendFunc(770, 1, 1, 0);
		float f2 = tile.getWorldObj().getTotalWorldTime() + partialTick;
		float f3 = -f2 * 0.2F - MathHelper.floor_float(-f2 * 0.1F);
		byte b0 = 1;
		double d3 = f2 * 0.025D * (1.0D - (b0 & 1) * 2.5D);
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA(255, 255, 255, 32);
		double d5 = b0 * 0.2D;
		double d7 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d5;
		double d9 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d5;
		double d11 = 0.5D + Math.cos(d3 + Math.PI / 4D) * d5;
		double d13 = 0.5D + Math.sin(d3 + Math.PI / 4D) * d5;
		double d15 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d5;
		double d17 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d5;
		double d19 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d5;
		double d21 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d5;
		double d23 = 256.0F;
		double d25 = 0.0D;
		double d27 = 1.0D;
		double d28 = -1.0F + f3;
		double d29 = 256.0F * (0.5D / d5) + d28;
		tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d27, d29);
		tessellator.addVertexWithUV(x + d7, y, z + d9, d27, d28);
		tessellator.addVertexWithUV(x + d11, y, z + d13, d25, d28);
		tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d25, d29);
		tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d27, d29);
		tessellator.addVertexWithUV(x + d19, y, z + d21, d27, d28);
		tessellator.addVertexWithUV(x + d15, y, z + d17, d25, d28);
		tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d25, d29);
		tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d27, d29);
		tessellator.addVertexWithUV(x + d11, y, z + d13, d27, d28);
		tessellator.addVertexWithUV(x + d19, y, z + d21, d25, d28);
		tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d25, d29);
		tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d27, d29);
		tessellator.addVertexWithUV(x + d15, y, z + d17, d27, d28);
		tessellator.addVertexWithUV(x + d7, y, z + d9, d25, d28);
		tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d25, d29);
		tessellator.draw();
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDepthMask(false);
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA(255, 255, 255, 32);
		double d30 = 0.2D;
		double d4 = 0.2D;
		double d6 = 0.8D;
		double d8 = 0.2D;
		double d10 = 0.2D;
		double d12 = 0.8D;
		double d14 = 0.8D;
		double d16 = 0.8D;
		double d18 = 256.0F;
		double d20 = 0.0D;
		double d22 = 1.0D;
		double d24 = -1.0F + f3;
		double d26 = 256.0F + d24;
		tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d22, d26);
		tessellator.addVertexWithUV(x + d30, y, z + d4, d22, d24);
		tessellator.addVertexWithUV(x + d6, y, z + d8, d20, d24);
		tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d20, d26);
		tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d22, d26);
		tessellator.addVertexWithUV(x + d14, y, z + d16, d22, d24);
		tessellator.addVertexWithUV(x + d10, y, z + d12, d20, d24);
		tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d20, d26);
		tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d22, d26);
		tessellator.addVertexWithUV(x + d6, y, z + d8, d22, d24);
		tessellator.addVertexWithUV(x + d14, y, z + d16, d20, d24);
		tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d20, d26);
		tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d22, d26);
		tessellator.addVertexWithUV(x + d10, y, z + d12, d22, d24);
		tessellator.addVertexWithUV(x + d30, y, z + d4, d20, d24);
		tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d20, d26);
		tessellator.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
	}
}