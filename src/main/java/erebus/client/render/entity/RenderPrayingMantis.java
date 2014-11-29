package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelPrayingMantis;
import erebus.entity.EntityPrayingMantis;

@SideOnly(Side.CLIENT)
public class RenderPrayingMantis extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/prayingMantis.png");

	public RenderPrayingMantis(ModelPrayingMantis model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scalePrayingMantis((EntityPrayingMantis) entityliving, f);
	}

	protected void scalePrayingMantis(EntityPrayingMantis entityPrayingMantis, float f) {
		float f1 = 1.0F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, entityPrayingMantis.getDataWatcher().getWatchableObjectFloat(20));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}