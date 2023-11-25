package net.zimo.crasher;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zimo.crasher.entity.EntityManager;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Crasher.MODID)
public class Crasher {
    public static final String MODID = "orebeasts";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Crasher() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityManager.register(modEventBus);
    }


}
