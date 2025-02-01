package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class SwordEnchantments {
    public static Map<ResourceKey<Enchantment>, Boolean> initializeSwordEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.BANE_OF_ARTHROPODS, false);
        enchantmentsMap.put(Enchantments.FIRE_ASPECT, false);
        enchantmentsMap.put(Enchantments.KNOCKBACK, false);
        enchantmentsMap.put(Enchantments.LOOTING, false);
        enchantmentsMap.put(Enchantments.SHARPNESS, false);
        enchantmentsMap.put(Enchantments.SMITE, false);
        enchantmentsMap.put(Enchantments.SWEEPING_EDGE, false);
        enchantmentsMap.put(Enchantments.UNBREAKING, false);
        return enchantmentsMap;
    }

    private static void setEnchantment(Map<ResourceKey<Enchantment>, Boolean> swordEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        if (swordEnchantments.containsKey(enchantment)) {
            swordEnchantments.put(enchantment, value);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static boolean getEnchantmentStatus(Map<ResourceKey<Enchantment>, Boolean> swordEnchantments, ResourceKey<Enchantment> enchantment) {
        if (swordEnchantments.containsKey(enchantment)) {
            return swordEnchantments.get(enchantment);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static void toggleEnchantment(Map<ResourceKey<Enchantment>, Boolean> swordEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setEnchantment(swordEnchantments, enchantment, value);
    }

    public static void toggleEnchantments(Map<ResourceKey<Enchantment>, Boolean> swordEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setEnchantment(swordEnchantments, entry.getKey(), entry.getValue());
        }
    }
}
