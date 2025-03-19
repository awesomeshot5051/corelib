package com.awesomeshot5051.corelib.integration;

import net.minecraft.world.item.*;

import java.util.*;

public class AE2Check {
    public static boolean containsAllItems(List<ItemStack> source, List<ItemStack> target) {
        if (target.size() > source.size()) return false; // Impossible to match if target has more items

        // Sort both lists so we can compare them easily
        List<ItemStack> sourceSorted = new ArrayList<>(source);
        List<ItemStack> targetSorted = new ArrayList<>(target);

        // Sort the lists by item registry and stack size, then compare item by item
        // If items are the same, compare by stack size
        sourceSorted.sort(Comparator.comparing((ItemStack stack) -> stack.getItem().toString()).thenComparingInt(ItemStack::getCount));
        // If items are the same, compare by stack size
        targetSorted.sort(Comparator.comparing((ItemStack stack) -> stack.getItem().toString()).thenComparingInt(ItemStack::getCount));

        // Now compare items including NBT
        for (int i = 0; i < sourceSorted.size(); i++) {
            if (!ItemStack.matches(sourceSorted.get(i), targetSorted.get(i))) {
                return false; // If any item doesn't match exactly (item, stack size, NBT), return false
            }
        }

        return true; // If all items match, return true
    }
}
