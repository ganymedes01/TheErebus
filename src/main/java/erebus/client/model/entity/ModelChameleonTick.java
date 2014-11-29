package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityChameleonTick;

public class ModelChameleonTick extends ModelBase {
	ModelRenderer LBL1;
	ModelRenderer LBL2;
	ModelRenderer LBL3;
	ModelRenderer LBL4;
	ModelRenderer LML1;
	ModelRenderer LML2;
	ModelRenderer LML3;
	ModelRenderer LML4;
	ModelRenderer LFL1;
	ModelRenderer LFL2;
	ModelRenderer LFL3;
	ModelRenderer LFL4;
	ModelRenderer RBL1;
	ModelRenderer RBL2;
	ModelRenderer RBL3;
	ModelRenderer RBL4;
	ModelRenderer RML1;
	ModelRenderer RML2;
	ModelRenderer RML3;
	ModelRenderer RML4;
	ModelRenderer RFL1;
	ModelRenderer RFL2;
	ModelRenderer RFL3;
	ModelRenderer RFL4;
	ModelRenderer Back2;
	ModelRenderer HeadBack;
	ModelRenderer Body;
	ModelRenderer Back1;
	ModelRenderer Rmand;
	ModelRenderer Lmand;
	ModelRenderer HeadFront;

	public ModelChameleonTick() {
		textureWidth = 16;
		textureHeight = 16;

		LBL1 = new ModelRenderer(this, 0, 16);
		LBL1.addBox(1F, -1F, -1.5F, 5, 3, 3);
		LBL1.setRotationPoint(6F, 18F, 6F);
		setRotation(LBL1, 0F, -0.3490659F, -0.3490659F);
		LBL2 = new ModelRenderer(this, 0, 16);
		LBL2.addBox(5F, 0F, -1F, 2, 4, 2);
		LBL2.setRotationPoint(6F, 18F, 6F);
		setRotation(LBL2, 0F, -0.3490659F, -0.3490659F);
		LBL3 = new ModelRenderer(this, 0, 16);
		LBL3.addBox(3.5F, 5.5F, -0.5F, 2, 4, 1);
		LBL3.setRotationPoint(6F, 18F, 6F);
		setRotation(LBL3, 0F, -0.3490659F, -0.6981317F);
		LBL4 = new ModelRenderer(this, 0, 16);
		LBL4.addBox(2.5F, 9F, -0.5F, 1, 4, 1);
		LBL4.setRotationPoint(6F, 18F, 6F);
		setRotation(LBL4, 0F, -0.3490659F, -0.8726646F);
		LML1 = new ModelRenderer(this, 0, 16);
		LML1.addBox(-1F, -1F, -1.5F, 5, 3, 3);
		LML1.setRotationPoint(8F, 17F, 0F);
		setRotation(LML1, 0F, 0F, -0.3490659F);
		LML2 = new ModelRenderer(this, 0, 16);
		LML2.addBox(3F, 0F, -1F, 2, 4, 2);
		LML2.setRotationPoint(8F, 17F, 0F);
		setRotation(LML2, 0F, 0F, -0.3490659F);
		LML3 = new ModelRenderer(this, 0, 16);
		LML3.addBox(1.5F, 4.5F, -0.5F, 2, 4, 1);
		LML3.setRotationPoint(8F, 17F, 0F);
		setRotation(LML3, 0F, 0F, -0.6981317F);
		LML4 = new ModelRenderer(this, 0, 16);
		LML4.addBox(0.5F, 8F, -0.5F, 1, 4, 1);
		LML4.setRotationPoint(8F, 17F, 0F);
		setRotation(LML4, 0F, 0F, -0.8726646F);
		LFL1 = new ModelRenderer(this, 0, 16);
		LFL1.addBox(-1F, -1F, -1.5F, 5, 3, 3);
		LFL1.setRotationPoint(8F, 17F, -6F);
		setRotation(LFL1, 0F, 0.3490659F, -0.3490659F);
		LFL2 = new ModelRenderer(this, 0, 16);
		LFL2.addBox(3F, 0F, -1F, 2, 4, 2);
		LFL2.setRotationPoint(8F, 17F, -6F);
		setRotation(LFL2, 0F, 0.3490659F, -0.3490659F);
		LFL3 = new ModelRenderer(this, 0, 16);
		LFL3.addBox(1.5F, 4.5F, -0.5F, 2, 4, 1);
		LFL3.setRotationPoint(8F, 17F, -6F);
		setRotation(LFL3, 0F, 0.3490659F, -0.6981317F);
		LFL4 = new ModelRenderer(this, 0, 16);
		LFL4.addBox(0.5F, 8F, -0.5F, 1, 4, 1);
		LFL4.setRotationPoint(8F, 17F, -6F);
		setRotation(LFL4, 0F, 0.3490659F, -0.8726646F);
		RBL1 = new ModelRenderer(this, 0, 16);
		RBL1.addBox(-6F, -1F, -1.5F, 5, 3, 3);
		RBL1.setRotationPoint(-6F, 18F, 6F);
		setRotation(RBL1, 0F, 0.3490659F, 0.3490659F);
		RBL2 = new ModelRenderer(this, 0, 16);
		RBL2.addBox(-7F, 0F, -1F, 2, 4, 2);
		RBL2.setRotationPoint(-6F, 18F, 6F);
		setRotation(RBL2, 0F, 0.3490659F, 0.3490659F);
		RBL3 = new ModelRenderer(this, 0, 16);
		RBL3.addBox(-5.5F, 5.5F, -0.5F, 2, 4, 1);
		RBL3.setRotationPoint(-6F, 18F, 6F);
		setRotation(RBL3, 0F, 0.3490659F, 0.6981317F);
		RBL4 = new ModelRenderer(this, 0, 16);
		RBL4.addBox(-3.5F, 9F, -0.5F, 1, 4, 1);
		RBL4.setRotationPoint(-6F, 18F, 6F);
		setRotation(RBL4, 0F, 0.3490659F, 0.8726646F);
		RML1 = new ModelRenderer(this, 0, 16);
		RML1.addBox(-4F, -1F, -1.5F, 5, 3, 3);
		RML1.setRotationPoint(-8F, 17F, 0F);
		setRotation(RML1, 0F, 0F, 0.3490659F);
		RML2 = new ModelRenderer(this, 0, 16);
		RML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		RML2.setRotationPoint(-8F, 17F, 0F);
		setRotation(RML2, 0F, 0F, 0.3490659F);
		RML3 = new ModelRenderer(this, 0, 16);
		RML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		RML3.setRotationPoint(-8F, 17F, 0F);
		setRotation(RML3, 0F, 0F, 0.6981317F);
		RML4 = new ModelRenderer(this, 0, 16);
		RML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		RML4.setRotationPoint(-8F, 17F, 0F);
		setRotation(RML4, 0F, 0F, 0.8726646F);
		RFL1 = new ModelRenderer(this, 0, 16);
		RFL1.addBox(-4F, -1F, -1.5F, 5, 3, 3);
		RFL1.setRotationPoint(-8F, 17F, -6F);
		setRotation(RFL1, 0F, -0.3490659F, 0.3490659F);
		RFL2 = new ModelRenderer(this, 0, 16);
		RFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		RFL2.setRotationPoint(-8F, 17F, -6F);
		setRotation(RFL2, 0F, -0.3490659F, 0.3490659F);
		RFL3 = new ModelRenderer(this, 0, 16);
		RFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		RFL3.setRotationPoint(-8F, 17F, -6F);
		setRotation(RFL3, 0F, -0.3490659F, 0.6981317F);
		RFL4 = new ModelRenderer(this, 0, 16);
		RFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		RFL4.setRotationPoint(-8F, 17F, -6F);
		setRotation(RFL4, 0F, -0.3490659F, 0.8726646F);
		Back2 = new ModelRenderer(this, 0, 0);
		Back2.addBox(-4F, -3F, 13F, 8, 7, 3);
		Back2.setRotationPoint(0F, 15F, 0F);
		Back2.setTextureSize(64, 32);
		Back2.mirror = true;
		setRotation(Back2, 0F, 0F, 0F);
		HeadBack = new ModelRenderer(this, 0, 0);
		HeadBack.addBox(-5.5F, -5F, -4F, 11, 10, 4);
		HeadBack.setRotationPoint(0F, 16F, -8F);
		HeadBack.setTextureSize(64, 32);
		HeadBack.mirror = true;
		setRotation(HeadBack, 0F, 0F, 0F);

		Back1 = new ModelRenderer(this, 0, 0);
		Back1.addBox(-6F, -5F, 8F, 12, 11, 5);
		Back1.setRotationPoint(0F, 15F, 0F);
		Back1.setTextureSize(64, 32);
		Back1.mirror = true;
		setRotation(Back1, 0F, 0F, 0F);
		Rmand = new ModelRenderer(this, 0, 0);
		Rmand.addBox(-3F, 0F, -15F, 2, 2, 6);
		Rmand.setRotationPoint(0F, 16F, -8F);
		Rmand.setTextureSize(64, 32);
		Rmand.mirror = true;
		setRotation(Rmand, 0F, 0F, 0F);
		Lmand = new ModelRenderer(this, -1, 0);
		Lmand.addBox(1F, 0F, -15F, 2, 2, 6);
		Lmand.setRotationPoint(0F, 16F, -8F);
		Lmand.setTextureSize(64, 32);
		Lmand.mirror = true;
		setRotation(Lmand, 0F, 0F, 0F);
		HeadFront = new ModelRenderer(this, 0, 0);
		HeadFront.addBox(-4F, -2F, -10F, 8, 5, 6);
		HeadFront.setRotationPoint(0F, 16F, -8F);
		HeadFront.setTextureSize(64, 32);
		HeadFront.mirror = true;
		setRotation(HeadFront, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityChameleonTick chameleonTick = (EntityChameleonTick) entity;
		float x = chameleonTick.animation;
		GL11.glPushMatrix();
		GL11.glTranslated(0F, 1.0F - 0.1F * x, 0F);
		GL11.glScalef(0.1F * x, 0.1F * x, 0.1F * x);
		LBL1.render(unitPixel);
		LBL2.render(unitPixel);
		LBL3.render(unitPixel);
		LBL4.render(unitPixel);
		LML1.render(unitPixel);
		LML2.render(unitPixel);
		LML3.render(unitPixel);
		LML4.render(unitPixel);
		LFL1.render(unitPixel);
		LFL2.render(unitPixel);
		LFL3.render(unitPixel);
		LFL4.render(unitPixel);
		RBL1.render(unitPixel);
		RBL2.render(unitPixel);
		RBL3.render(unitPixel);
		RBL4.render(unitPixel);
		RML1.render(unitPixel);
		RML2.render(unitPixel);
		RML3.render(unitPixel);
		RML4.render(unitPixel);
		RFL1.render(unitPixel);
		RFL2.render(unitPixel);
		RFL3.render(unitPixel);
		RFL4.render(unitPixel);
		Back2.render(unitPixel);
		HeadBack.render(unitPixel);
		Back1.render(unitPixel);
		Rmand.render(unitPixel);
		Lmand.render(unitPixel);
		HeadFront.render(unitPixel);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		LBL1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle + 0.25F;
		LBL2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle + 0.25F;
		LBL3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle + 0.3F;
		LBL4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle + 0.334F;
		LML1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle;
		LML2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle;
		LML3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle;
		LML4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle;
		LFL1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle - 0.25F;
		LFL2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle - 0.25F;
		LFL3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle - 0.3F;
		LFL4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle - 0.334F;
		RBL1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle + 0.25F;
		RBL2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle + 0.25F;
		RBL3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle + 0.3F;
		RBL4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle + 0.334F;
		RML1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle;
		RML2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle;
		RML3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle;
		RML4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAngle;
		RFL1.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle - 0.25F;
		RFL2.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle - 0.25F;
		RFL3.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle - 0.3F;
		RFL4.rotateAngleX = MathHelper.cos(limbSwing * 2.0F) * 0.7F * limbSwingAngle - 0.334F;
	}
}
