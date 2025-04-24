package com.awesomeshot5051.corelib.datacomponents;

import com.awesomeshot5051.corelib.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;

import javax.annotation.*;
import java.lang.ref.*;
import java.util.*;
import java.util.function.*;

public class BlockEntityData {
    public static final StreamCodec<RegistryFriendlyByteBuf, BlockEntityData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public BlockEntityData decode(RegistryFriendlyByteBuf buf) {
            return new BlockEntityData(buf.readNbt());
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, BlockEntityData be) {
            buf.writeNbt(be.nbt);
        }
    };
    private WeakReference<FakeWorldTileentity> cache = new WeakReference<>(null);
    private WeakReference<FakeWorldTileentity> emptyCache = new WeakReference<>(null);
    private final CompoundTag nbt;

    private BlockEntityData(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public CompoundTag copy() {
        return nbt.copy();
    }

    public static BlockEntityData of(CompoundTag nbt) {
        return new BlockEntityData(nbt.copy());
    }

    @Nullable
    public static BlockEntityData get(ItemStack stack) {
        return stack.get(ComponentRegister.BLOCK_ENTITY_DATA_COMPONENT);
    }

    public <T extends FakeWorldTileentity> T getBlockEntity(HolderLookup.Provider provider, @Nullable Level level, Supplier<T> blockEntitySupplier) {
        if (level == null) {
            T te = (T) emptyCache.get();
            if (te == null) {
                te = blockEntitySupplier.get();
                emptyCache = new WeakReference<>(te);
            }
            return te;
        }
        T te = (T) cache.get();
        if (te == null) {
            te = blockEntitySupplier.get();
            te.setFakeWorld(level);
            te.loadCustomOnly(nbt, provider);
            cache = new WeakReference<>(te);
        }
        return te;
    }

    public static <T extends FakeWorldTileentity> T getAndStoreBlockEntity(ItemStack stack, HolderLookup.Provider provider, @Nullable Level level, Supplier<T> blockEntitySupplier) {
        BlockEntityData data = get(stack);
        if (data == null) {
            CustomData beData = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY);
            data = new BlockEntityData(beData.copyTag());
            stack.set(ComponentRegister.BLOCK_ENTITY_DATA_COMPONENT, data);
        }
        return data.getBlockEntity(provider, level, blockEntitySupplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlockEntityData be = (BlockEntityData) o;
        return Objects.equals(nbt, be.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nbt);
    }
}