package net.zimo.crasher.entity.crasher;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.zimo.crasher.Crasher;

public class CrasherModel<T extends Entity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Crasher.MODID, "crasher"), "main");
    private final ModelPart crasher;
    private final ModelPart head;

    public CrasherModel(ModelPart root) {
        this.crasher = root.getChild("crasher");
        this.head = crasher.getChild("Body").getChild("Torso").getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition crasher = partdefinition.addOrReplaceChild("crasher", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = crasher.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, 2.0F));

        PartDefinition Torso = Body.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 36).addBox(-8.0F, -9.0F, -9.0F, 16.0F, 18.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(50, 54).addBox(-6.0F, -7.0F, 6.0F, 12.0F, 14.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -9.0F));

        PartDefinition Head = Torso.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.0F, -20.2942F, 12.0F, 11.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.6208F, -1.5131F, 0.5236F, 0.0F, 0.0F));

        PartDefinition Back_R_Horn = Head.addOrReplaceChild("Back_R_Horn", CubeListBuilder.create().texOffs(0, 36).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.0F, -9.2942F, -0.1845F, -0.3921F, -1.2782F));

        PartDefinition Front_Horn = Head.addOrReplaceChild("Front_Horn", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -8.0F, 3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-2.0F, -8.0F, -7.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7321F, -17.2942F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition Back_L_Horn = Head.addOrReplaceChild("Back_L_Horn", CubeListBuilder.create().texOffs(0, 36).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -6.0F, -9.2942F, -2.9571F, 0.3921F, -1.8634F));

        PartDefinition Top_Horn = Head.addOrReplaceChild("Top_Horn", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -17.2942F));

        PartDefinition Back_R_Leg = crasher.addOrReplaceChild("Back_R_Leg", CubeListBuilder.create().texOffs(24, 72).addBox(-3.5F, -0.5F, -3.5F, 5.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -18.5F, 10.5F));

        PartDefinition Back_L_Leg = crasher.addOrReplaceChild("Back_L_Leg", CubeListBuilder.create().texOffs(24, 72).addBox(-1.5F, -0.5F, -3.5F, 5.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -18.5F, 10.5F));

        PartDefinition Front_R_Leg = crasher.addOrReplaceChild("Front_R_Leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, -0.5F, -3.5F, 5.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -22.5F, -8.5F));

        PartDefinition Front_L_Leg = crasher.addOrReplaceChild("Front_L_Leg", CubeListBuilder.create().texOffs(0, 72).addBox(-1.5F, -0.5F, -3.5F, 5.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -22.5F, -8.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(CrasherAnimation.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((CrasherEntity) entity).idleAnimationState, CrasherAnimation.IDLE, ageInTicks, 1f);
        this.animate(((CrasherEntity) entity).gallopAnimationState, CrasherAnimation.GALLOP, ageInTicks, 1f);
        this.animate(((CrasherEntity) entity).attackAnimationState, CrasherAnimation.ATTACK, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
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