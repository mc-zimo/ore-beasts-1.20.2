package net.zimo.crasher.entity.crasher;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zimo.crasher.Crasher;
import net.zimo.crasher.entity.ModelLayerManager;

import java.util.Map;

public class CrasherRenderer extends MobRenderer<CrasherEntity, CrasherModel<CrasherEntity>> {

    private static final Map<CrasherVariant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(CrasherVariant.class), (locale) ->{
        locale.put(CrasherVariant.AMETHYST, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/amethyst_crasher.png"));
        locale.put(CrasherVariant.COAL, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/coal_crasher.png"));
        locale.put(CrasherVariant.COPPER, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/copper_crasher.png"));
        locale.put(CrasherVariant.DIAMOND, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/diamond_crasher.png"));
        locale.put(CrasherVariant.EMERALD, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/emerald_crasher.png"));
        locale.put(CrasherVariant.GOLD, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/gold_crasher.png"));
        locale.put(CrasherVariant.IRON, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/iron_crasher.png"));
        locale.put(CrasherVariant.LAPIS, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/lapis_crasher.png"));
        locale.put(CrasherVariant.NETHERITE, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/netherite_crasher.png"));
        locale.put(CrasherVariant.QUARTZ, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/quartz_crasher.png"));
        locale.put(CrasherVariant.REDSTONE, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/redstone_crasher.png"));
        locale.put(CrasherVariant.STONE, new ResourceLocation(Crasher.MODID, "textures/entity/crasher/stone_crasher.png"));
    });

    public CrasherRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CrasherModel<>(pContext.bakeLayer(ModelLayerManager.CrasherLayer)), 1.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(CrasherEntity pCrasherEntity) {
        return LOCATION_BY_VARIANT.get(pCrasherEntity.getVariant());
    }

    @Override
    public void render(CrasherEntity pCrasherEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pCrasherEntity.isBaby()){
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pCrasherEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
