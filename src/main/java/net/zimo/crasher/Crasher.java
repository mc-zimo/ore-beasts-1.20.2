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
import net.zimo.crasher.item.ItemManager;
import org.slf4j.Logger;

import static net.minecraftforge.registries.ForgeRegistries.Keys.ITEMS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Crasher.MODID)
public class Crasher {
    public static final String MODID = "crasher";
    public static final Logger LOGGER = LogUtils.getLogger();
    public Crasher() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemManager.register(modEventBus);
        EntityManager.register(modEventBus);
    }


}
