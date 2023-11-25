package net.zimo.crasher.entity.crasher;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CrasherModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "ore_beast"), "main");
	private final ModelPart crasher;

	public CrasherModel(ModelPart root) {
		this.crasher = root.getChild("crasher");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ore_beast = partdefinition.addOrReplaceChild("crasher", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -16.0F));

		PartDefinition head = ore_beast.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.5F, -12.5F, 12.0F, 11.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.3792F, -1.5131F, 0.5236F, 0.0F, 0.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(0, 36).addBox(-5.0F, -8.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -5.5F, -2.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r1 = horns.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 5.0F, -41.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 20.0F, 32.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition left_side_horn_r1 = horns.addOrReplaceChild("left_side_horn_r1", CubeListBuilder.create().texOffs(0, 36).addBox(-26.0F, 4.0F, 31.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 24.0F, 34.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_front_horn_r1 = horns.addOrReplaceChild("left_front_horn_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-30.7321F, -41.0F, 13.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-30.7321F, -41.0F, 23.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, 26.0F, 26.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition body = ore_beast.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 33.0F, 16.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 36).addBox(-8.0F, -30.0F, -16.0F, 16.0F, 18.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(50, 54).addBox(-6.0F, -28.0F, -1.0F, 12.0F, 14.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(24, 72).addBox(-8.0F, -19.0F, 7.0F, 5.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(24, 72).addBox(3.0F, -19.0F, 7.0F, 5.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-10.0F, -23.0F, -12.0F, 5.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 72).addBox(5.0F, -23.0F, -12.0F, 5.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		crasher.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return crasher;
	}
}