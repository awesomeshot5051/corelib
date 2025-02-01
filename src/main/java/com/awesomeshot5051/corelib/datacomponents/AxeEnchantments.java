package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.resources.*;
import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class AxeEnchantments {
    public static Map<ResourceKey<Enchantment>, Boolean> initializeAxeEnchantments() {
        Map<ResourceKey<Enchantment>, Boolean> enchantmentsMap = new HashMap<>();
        enchantmentsMap.put(Enchantments.MENDING, false);
        enchantmentsMap.put(Enchantments.EFFICIENCY, false);
        enchantmentsMap.put(Enchantments.UNBREAKING, false);
        enchantmentsMap.put(Enchantments.SILK_TOUCH, false);
        return enchantmentsMap;
    }

    private static void setAxeEnchantment(Map<ResourceKey<Enchantment>, Boolean> axeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        if (axeEnchantments.containsKey(enchantment)) {
            axeEnchantments.put(enchantment, value);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static boolean getAxeEnchantmentStatus(Map<ResourceKey<Enchantment>, Boolean> axeEnchantments, ResourceKey<Enchantment> enchantment) {
        if (axeEnchantments.containsKey(enchantment)) {
            return axeEnchantments.get(enchantment);
        } else {
            throw new IllegalArgumentException("Invalid enchantment: " + enchantment);
        }
    }

    public static void toggleAxeEnchantment(Map<ResourceKey<Enchantment>, Boolean> axeEnchantments, ResourceKey<Enchantment> enchantment, boolean value) {
        setAxeEnchantment(axeEnchantments, enchantment, value);
    }

    public static void toggleAxeEnchantments(Map<ResourceKey<Enchantment>, Boolean> axeEnchantments, Map<ResourceKey<Enchantment>, Boolean> enchantmentsToToggle) {
        for (Map.Entry<ResourceKey<Enchantment>, Boolean> entry : enchantmentsToToggle.entrySet()) {
            setAxeEnchantment(axeEnchantments, entry.getKey(), entry.getValue());
        }
    }
}