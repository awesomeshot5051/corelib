package com.awesomeshot5051.corelib.block;

import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.items.*;
import org.jetbrains.annotations.*;

public class OutputItemHandler extends ItemStackHandler {
    public OutputItemHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return false;
    }
}