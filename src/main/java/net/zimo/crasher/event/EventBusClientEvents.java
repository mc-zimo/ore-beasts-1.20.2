package net.zimo.crasher.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zimo.crasher.Crasher;
import net.zimo.crasher.entity.EntityManager;
import net.zimo.crasher.entity.ModelLayerManager;
import net.zimo.crasher.entity.crasher.CrasherModel;
import net.zimo.crasher.entity.crasher.CrasherRenderer;

@Mod.EventBusSubscriber(modid = Crasher.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class EventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModelLayerManager.CrasherLayer, CrasherModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event){
        EntityRenderers.register(EntityManager.Crasher.get(), CrasherRenderer::new);
    }
}
