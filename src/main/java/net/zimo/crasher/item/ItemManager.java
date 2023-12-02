package net.zimo.crasher.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimo.crasher.Crasher;
import net.zimo.crasher.entity.EntityManager;

public class ItemManager {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Crasher.MODID);
    public static final RegistryObject<ForgeSpawnEggItem> CRASHER_SPAWN_EGG = ITEMS.register("crasher_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityManager.Crasher,
                    0x8f8f8f,0x686868,
                    new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
