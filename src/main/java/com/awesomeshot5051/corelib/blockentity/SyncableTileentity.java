package com.awesomeshot5051.corelib.blockentity;

import com.awesomeshot5051.corelib.datacomponents.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.nbt.*;
import net.minecraft.network.protocol.*;
import net.minecraft.network.protocol.game.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.chunk.*;

import java.util.*;

public class SyncableTileentity extends BlockEntity {
    public SyncableTileentity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static Optional<ItemStack> loadSwordType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("SwordType")) {
            Tag swordTypeTag = compound.get("SwordType");
            if (swordTypeTag != null && isValidSword(swordTypeTag.toString())) {
                return ItemStack.parse(provider, swordTypeTag);
            }
        }
        return Optional.empty();
    }

    private static boolean isValidSword(String itemId) {
        return itemId.contains("minecraft:wooden_sword") ||
                itemId.contains("minecraft:stone_sword") ||
                itemId.contains("minecraft:iron_sword") ||
                itemId.contains("minecraft:diamond_sword") ||
                itemId.contains("minecraft:golden_sword") ||
                itemId.contains("minecraft:netherite_sword");
    }

    private static boolean isValidPickaxe(String itemId) {
        return itemId.contains("minecraft:wooden_pickaxe") ||
                itemId.contains("minecraft:stone_pickaxe") ||
                itemId.contains("minecraft:iron_pickaxe") ||
                itemId.contains("minecraft:diamond_pickaxe") ||
                itemId.contains("minecraft:golden_pickaxe") ||
                itemId.contains("minecraft:netherite_pickaxe");
    }

    public static Optional<ItemStack> loadHoeType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("HoeType")) {
            Tag hoeTypeTag = compound.get("HoeType");
            if (hoeTypeTag != null && isValidHoe(hoeTypeTag.toString())) {
                return ItemStack.parse(provider, hoeTypeTag);
            }
        }
        return Optional.empty();
    }

    public static Map<ResourceKey<Enchantment>, Boolean> loadHoeEnchantments(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag enchantmentsList = compound.getList("HoeEnchantments", CompoundTag.TAG_COMPOUND);
        Map<ResourceKey<Enchantment>, Boolean> enchantments = farm.getHoeEnchantments();
        for (int i = 0; i < enchantmentsList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentsList.getCompound(i);
            String enchantmentId = enchantmentTag.getString("id");
            boolean enabled = enchantmentTag.getBoolean("enabled");
            ResourceLocation enchantmentLocation = ResourceLocation.parse(enchantmentId);
            ResourceKey<Enchantment> enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentLocation);
            HoeEnchantments.toggleHoeEnchantment(enchantments, enchantmentKey, true);
        }
        return enchantments;
    }

    public static Map<ResourceKey<Enchantment>, Boolean> loadAxeEnchantments(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag enchantmentsList = compound.getList("AxeEnchantments", CompoundTag.TAG_COMPOUND);
        Map<ResourceKey<Enchantment>, Boolean> enchantments = farm.getAxeEnchantments();
        for (int i = 0; i < enchantmentsList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentsList.getCompound(i);
            String enchantmentId = enchantmentTag.getString("id");
            boolean enabled = enchantmentTag.getBoolean("enabled");
            ResourceLocation enchantmentLocation = ResourceLocation.parse(enchantmentId);
            ResourceKey<Enchantment> enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentLocation);
            AxeEnchantments.toggleAxeEnchantment(enchantments, enchantmentKey, true);
        }
        return enchantments;
    }

    public static Optional<ItemStack> loadShears(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("Shears")) {
            Tag shears = compound.get("Shears");
            if (shears != null) {
                return ItemStack.parse(provider, shears);
            }
        }
        return Optional.empty();
    }

    public static Optional<ItemStack> loadAxeType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("AxeType")) {
            Tag axeTypeTag = compound.get("AxeType");
            if (axeTypeTag != null && isValidAxe(axeTypeTag.toString())) {
                return ItemStack.parse(provider, axeTypeTag);
            }
        }
        return Optional.empty();
    }

    public static Map<ResourceKey<Enchantment>, Boolean> loadSwordEnchantments(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag enchantmentsList = compound.getList("SwordEnchantments", CompoundTag.TAG_COMPOUND);
        Map<ResourceKey<Enchantment>, Boolean> enchantments = farm.getSwordEnchantments();
        for (int i = 0; i < enchantmentsList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentsList.getCompound(i);
            String enchantmentId = enchantmentTag.getString("id");
            boolean enabled = enchantmentTag.getBoolean("enabled");
            ResourceLocation enchantmentLocation = ResourceLocation.parse(enchantmentId);
            ResourceKey<Enchantment> enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentLocation);
            SwordEnchantments.toggleEnchantment(enchantments, enchantmentKey, true);
        }
        return enchantments;
    }

    private static boolean isValidHoe(String itemId) {
        return itemId.contains("minecraft:wooden_hoe") || itemId.contains("minecraft:stone_hoe") || itemId.contains("minecraft:iron_hoe") || itemId.contains("minecraft:diamond_hoe") || itemId.contains("minecraft:golden_hoe") || itemId.contains("minecraft:netherite_hoe");
    }

    private static boolean isValidAxe(String itemId) {
        return itemId.contains("minecraft:wooden_axe") || itemId.contains("minecraft:stone_axe") || itemId.contains("minecraft:iron_axe") || itemId.contains("minecraft:diamond_axe") || itemId.contains("minecraft:golden_axe") || itemId.contains("minecraft:netherite_axe");
    }

    public static Optional<ItemStack> loadPickType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("PickType")) {
            Tag pickTypeTag = compound.get("PickType");
            if (pickTypeTag != null && isValidPickaxe(pickTypeTag.toString()) || isValidShovel(Objects.requireNonNull(pickTypeTag).toString())) {
                return ItemStack.parse(provider, pickTypeTag);
            }
        }
        return Optional.empty();
    }

    public static Map<ResourceKey<Enchantment>, Boolean> loadPickaxeEnchantments(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag enchantmentsList = compound.getList("PickaxeEnchantments", CompoundTag.TAG_COMPOUND);
        Map<ResourceKey<Enchantment>, Boolean> enchantments = farm.getPickaxeEnchantments();
        for (int i = 0; i < enchantmentsList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentsList.getCompound(i);


            String enchantmentId = enchantmentTag.getString("id");
            boolean enabled = enchantmentTag.getBoolean("enabled");


            ResourceLocation enchantmentLocation = ResourceLocation.parse(enchantmentId);
            ResourceKey<Enchantment> enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentLocation);
            PickaxeEnchantments.togglePickaxeEnchantment(enchantments, enchantmentKey, true);
        }
        return enchantments;
    }

    public static Map<ResourceKey<Enchantment>, Boolean> loadShovelEnchantments(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag enchantmentsList = compound.getList("ShovelEnchantments", CompoundTag.TAG_COMPOUND);
        Map<ResourceKey<Enchantment>, Boolean> enchantments = farm.getShovelEnchantments();
        for (int i = 0; i < enchantmentsList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentsList.getCompound(i);


            String enchantmentId = enchantmentTag.getString("id");
            boolean enabled = enchantmentTag.getBoolean("enabled");


            ResourceLocation enchantmentLocation = ResourceLocation.parse(enchantmentId);
            ResourceKey<Enchantment> enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentLocation);
            ShovelEnchantments.toggleShovelEnchantment(enchantments, enchantmentKey, true);
        }
        return enchantments;
    }

    public static Optional<ItemStack> loadShovelType(CompoundTag compound, HolderLookup.Provider provider) {
        if (compound.contains("ShovelType")) {
            Tag shovelTypeTag = compound.get("ShovelType");
            if (shovelTypeTag != null && isValidShovel(shovelTypeTag.toString()) || isValidShovel(Objects.requireNonNull(shovelTypeTag).toString())) {
                return ItemStack.parse(provider, shovelTypeTag);
            }
        }
        return Optional.empty();
    }

    private static boolean isValidShovel(String itemId) {
        return itemId.contains("minecraft:wooden_shovel") ||
                itemId.contains("minecraft:stone_shovel") ||
                itemId.contains("minecraft:iron_shovel") ||
                itemId.contains("minecraft:diamond_shovel") ||
                itemId.contains("minecraft:golden_shovel") ||
                itemId.contains("minecraft:netherite_shovel");
    }

    public static Map<ItemStack, Boolean> loadUpgrades(CompoundTag compound, HolderLookup.Provider provider, FarmTileentity farm) {
        ListTag upgradesList = compound.getList("Upgrades", CompoundTag.TAG_COMPOUND);
        Map<ItemStack, Boolean> upgrades = farm.getUpgrades();

        for (int i = 0; i < upgradesList.size(); i++) {
            CompoundTag upgradeTag = upgradesList.getCompound(i);
            CompoundTag upgradeId = upgradeTag.getCompound("id");
            ItemStack upgrade = ItemStack.parseOptional(provider, upgradeId);
            Upgrades.toggleUpgrade(upgrades, upgrade);
        }

        return upgrades;
    }

    public void sync() {
        if (level instanceof ServerLevel serverLevel) {
            LevelChunk chunk = serverLevel.getChunkAt(getBlockPos());
            if (chunk.getLevel().getChunkSource() instanceof ServerChunkCache chunkCache) {
                chunkCache.chunkMap.getPlayers(chunk.getPos(), false).forEach(this::syncContents);
            }
        }
    }

    public void syncContents(ServerPlayer player) {
        player.connection.send(getUpdatePacket());
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag updateTag = new CompoundTag();
        saveAdditional(updateTag, provider);
        return updateTag;
    }

}
