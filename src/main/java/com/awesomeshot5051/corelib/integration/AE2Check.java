package com.awesomeshot5051.corelib.integration;

import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.stream.*;

public class AE2Check {
    public static boolean containsAllItems(List<ItemStack> target, TagKey<Item> tagKey, Level level, int requiredLength) {
        Optional<HolderSet.Named<Item>> optionalTagItems = level.registryAccess().lookup(tagKey.registry()).flatMap(reg -> reg.get(tagKey));

        if (optionalTagItems.isEmpty()) {
            return false; // The tag does not exist in the registry
        }
        if (target.size() < requiredLength) {
            return false;
        }
        Set<Item> tagItems = optionalTagItems.get().stream().map(Holder::value).collect(Collectors.toSet());

        // Check if all target items exist in the tag
        return target.stream().allMatch(stack -> tagItems.contains(stack.getItem()));
    }

    public static boolean containsAllItems(List<ItemStack> target, TagKey<Item> tagKey) {
        return target.stream().allMatch(stack -> stack.is(tagKey));
    }


    public static boolean containsAllItems(List<ItemStack> target, List<ItemStack> source) {
        if (target.isEmpty() || source.isEmpty()) return false;
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
