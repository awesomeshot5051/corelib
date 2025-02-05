package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.world.item.*;

import java.util.*;

public class Upgrades {
    public static Map<ItemStack, Boolean> initializeUpgrades(List<ItemStack> upgrades) {
        Map<ItemStack, Boolean> upgradesMap = new HashMap<>();

        // Loop through the list of upgrades and initialize the map with each item and its corresponding upgrade status.
        for (ItemStack upgrade : upgrades) {
            upgradesMap.put(upgrade, false);  // Assuming false means not activated
        }

        return upgradesMap;
    }

}
