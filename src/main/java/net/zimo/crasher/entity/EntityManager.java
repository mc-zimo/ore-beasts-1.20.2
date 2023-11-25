package net.zimo.crasher.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimo.crasher.Crasher;
import net.zimo.crasher.entity.crasher.CrasherEntity;

public class EntityManager {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, net.zimo.crasher.Crasher.MODID);

    public static final RegistryObject<EntityType<CrasherEntity>> Crasher =
            ENTITY_TYPES.register("crasher", () -> EntityType.Builder.of(CrasherEntity::new, MobCategory.CREATURE).sized(2f, 2f).build("ore_beast"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
