package net.zimo.crasher.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.zimo.crasher.Crasher;

public class ModelLayerManager {
    public static final ModelLayerLocation CrasherLayer = new ModelLayerLocation(
            new ResourceLocation(Crasher.MODID, "crasher_layer"), "main");
}
