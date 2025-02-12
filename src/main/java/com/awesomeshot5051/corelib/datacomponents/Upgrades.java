package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Upgrades {
    public static Map<ItemStack, Boolean> initializeUpgrades(Map<ItemStack,Boolean> upgradesMap, List<ItemStack> upgrades) {
//        Map<ItemStack, Boolean> upgradesMap = new HashMap<>();

        // Loop through the list of upgrades and initialize the map with each item and its corresponding upgrade status.
        for (ItemStack upgrade : upgrades) {
            toggleUpgrade(upgradesMap,upgrade);  // Assuming false means not activated
        }

        return upgradesMap;
    }

    public static boolean getUpgradeStatus(Map<ItemStack,Boolean> upgradesMap, ItemStack upgrade) {
        for (Map.Entry<ItemStack, Boolean> entry : upgradesMap.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), upgrade)) {  // Compare only items, ignoring count and other properties
                return entry.getValue();
            }
        }
        return false;
    }

    public static void setUpgradeStatus(Map<ItemStack,Boolean> upgradesMap,ItemStack upgrade, boolean value) {
        for (Map.Entry<ItemStack, Boolean> entry : upgradesMap.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), upgrade)) {  // Compare only items, ignoring count and other properties
                entry.setValue(value);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid upgrade: " + upgrade);
    }

    public static void toggleUpgrade(Map<ItemStack,Boolean> upgradesMap,ItemStack upgrade) {
        for (Map.Entry<ItemStack, Boolean> entry : upgradesMap.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), upgrade)) {
                upgradesMap.put(entry.getKey(), !upgradesMap.get(entry.getKey()));
                return;
            }
        }
        throw new IllegalArgumentException("Invalid upgrade: " + upgrade);
    }

    public static void toggleUpgrades(Map<ItemStack,Boolean> upgradesMap,Map<ItemStack, Boolean> upgradesToToggle) {
        for (Map.Entry<ItemStack, Boolean> entry : upgradesToToggle.entrySet()) {
            if (upgradesMap.containsKey(entry.getKey())) {
                upgradesMap.put(entry.getKey(), entry.getValue());
            } else {
                throw new IllegalArgumentException("Invalid upgrade: " + entry.getKey());
            }
        }
    }
}
