package net.zimo.crasher.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zimo.crasher.Crasher;
import net.zimo.crasher.entity.EntityManager;
import net.zimo.crasher.entity.crasher.CrasherEntity;

@Mod.EventBusSubscriber(modid = Crasher.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class EventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(EntityManager.Crasher.get(), CrasherEntity.createAttributes().build());
    }
}
