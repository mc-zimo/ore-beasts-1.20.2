package net.zimo.crasher;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimo.crasher.entity.EntityManager;
import org.slf4j.Logger;

import static net.minecraftforge.registries.ForgeRegistries.Keys.ITEMS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Crasher.MODID)
public class Crasher {
    public static final String MODID = "crasher";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Crasher.MODID);
    public static final RegistryObject<Item> CRASHER_SPAWN_EGG = ITEMS.register("crasher_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityManager.Crasher,
                    0x8f8f8f,0x686868,
                    new Item.Properties()));

    public Crasher() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityManager.register(modEventBus);
    }


}
