package com.awesomeshot5051.corelib.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import javax.annotation.Nonnull;

public class RestrictedItemStackHandler extends ItemStackHandler {

    private com.awesomeshot5051.corelib.inventory.RestrictedItemStackHandler.ItemValidator itemValidator;

    public RestrictedItemStackHandler(NonNullList<ItemStack> stacks, com.awesomeshot5051.corelib.inventory.RestrictedItemStackHandler.ItemValidator itemValidator) {
        super(stacks);
        this.itemValidator = itemValidator;
    }

    @Override
    public boolean isItemValid(int slot, @javax.annotation.Nonnull ItemStack stack) {
        return itemValidator.isValid(slot, stack);
    }

    public interface ItemValidator {
        boolean isValid(int slot, ItemStack stack);
    }

}
