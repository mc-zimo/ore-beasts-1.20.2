package net.zimo.crasher.entity.crasher;

import com.mojang.serialization.Codec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;

public enum CrasherVariant implements StringRepresentable {
    AMETHYST(0, "amethyst"),
    COAL(1, "coal"),
    COPPER(2, "copper"),
    DIAMOND(3, "diamond"),
    EMERALD(4, "emerald"),
    GOLD(5, "gold"),
    IRON(6, "iron"),
    LAPIS(7, "lapis"),
    NETHERITE(8, "netherite"),
    QUARTZ(9, "quartz"),
    REDSTONE(10, "redstone"),
    STONE(11, "stone");

    public static final Codec<CrasherVariant> CODEC = StringRepresentable.fromEnum(CrasherVariant::values);
    private static final IntFunction<CrasherVariant> BY_ID = ByIdMap.continuous(CrasherVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;

    private static final Map<Item, CrasherVariant> VARIANT_BY_ITEM = new HashMap<Item, CrasherVariant>();
     static{
         VARIANT_BY_ITEM.put(Items.AMETHYST_CLUSTER, CrasherVariant.AMETHYST);
         VARIANT_BY_ITEM.put(Items.COAL_ORE, CrasherVariant.COAL);
         VARIANT_BY_ITEM.put(Items.COPPER_ORE, CrasherVariant.COPPER);
         VARIANT_BY_ITEM.put(Items.DIAMOND_ORE, CrasherVariant.DIAMOND);
         VARIANT_BY_ITEM.put(Items.EMERALD_ORE, CrasherVariant.EMERALD);
         VARIANT_BY_ITEM.put(Items.GOLD_ORE, CrasherVariant.GOLD);
         VARIANT_BY_ITEM.put(Items.IRON_ORE, CrasherVariant.IRON);
         VARIANT_BY_ITEM.put(Items.LAPIS_ORE, CrasherVariant.LAPIS);
         VARIANT_BY_ITEM.put(Items.ANCIENT_DEBRIS, CrasherVariant.NETHERITE);
         VARIANT_BY_ITEM.put(Items.NETHER_QUARTZ_ORE, CrasherVariant.QUARTZ);
         VARIANT_BY_ITEM.put(Items.REDSTONE_ORE, CrasherVariant.REDSTONE);
         VARIANT_BY_ITEM.put(Items.STONE, CrasherVariant.STONE);
     }

            CrasherVariant(int pId, String pName) {
        this.id = pId;
        this.name = pName;
    }

    public int getId() {
        return this.id;
    }

    public static CrasherVariant byId(int pId) {
        return BY_ID.apply(pId);
    }

    public String getSerializedName() {
        return this.name;
    }

    public static CrasherVariant byItem(ItemStack pItemStack){
        return VARIANT_BY_ITEM.get(pItemStack.getItem());
    }
}