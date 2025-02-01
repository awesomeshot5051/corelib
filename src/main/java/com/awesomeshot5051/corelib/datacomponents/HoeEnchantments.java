package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class HoeEnchantments {
    public static Map<ResourceKey<Enchantment>, Boolean> initializeHoeEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.MENDING, false);
        enchantmentsMap.put(Enchantments.EFFICIENCY, false);
        enchantmentsMap.put(Enchantments.UNBREAKING, false);
        enchantmentsMap.put(Enchantments.FORTUNE, false);
        enchantmentsMap.put(Enchantments.SILK_TOUCH, false);
        return enchantmentsMap;
    }

    private static void setHoeEnchantment(Map<ResourceKey<Enchantment>, Boolean> hoeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        if (hoeEnchantments.containsKey(enchantment)) {
            hoeEnchantments.put(enchantment, value);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static boolean getHoeEnchantmentStatus(Map<ResourceKey<Enchantment>, Boolean> hoeEnchantments, ResourceKey<Enchantment> enchantment) {
        if (hoeEnchantments.containsKey(enchantment)) {
            return hoeEnchantments.get(enchantment);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static void toggleHoeEnchantment(Map<ResourceKey<Enchantment>, Boolean> hoeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setHoeEnchantment(hoeEnchantments, enchantment, value);
    }

    public static void toggleHoeEnchantments(Map<ResourceKey<Enchantment>, Boolean> hoeEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setHoeEnchantment(hoeEnchantments, entry.getKey(), entry.getValue());
        }
    }
}