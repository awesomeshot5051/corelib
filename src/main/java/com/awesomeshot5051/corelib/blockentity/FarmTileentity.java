package com.awesomeshot5051.corelib.blockentity;

import com.awesomeshot5051.corelib.datacomponents.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;

public class FarmTileentity extends FakeWorldTileentity {
    protected ItemStack villager;

    public FarmTileentity(BlockEntityType<?> type, BlockState defaultState, BlockPos pos, BlockState state) {
        super(type, defaultState, pos, state);
        villager = ItemStack.EMPTY;
    }


    public ItemStack getHoeType() {
        return ItemStack.EMPTY;
    }

    public ItemStack getAxeType() {
        return ItemStack.EMPTY;
    }

    public <T extends FarmTileentity> void setHoeEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> hoeEnchantments = farm.getHoeEnchantments();
        ItemEnchantments enchantments = farm.getHoeType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                HoeEnchantments.toggleHoeEnchantment(hoeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                HoeEnchantments.toggleHoeEnchantment(hoeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                HoeEnchantments.toggleHoeEnchantment(hoeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.FORTUNE.location())) {
                HoeEnchantments.toggleHoeEnchantment(hoeEnchantments, Enchantments.FORTUNE, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                HoeEnchantments.toggleHoeEnchantment(hoeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    public <T extends FarmTileentity> void setAxeEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> axeEnchantments = farm.getAxeEnchantments();
        ItemEnchantments enchantments = farm.getAxeType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                AxeEnchantments.toggleAxeEnchantment(axeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                AxeEnchantments.toggleAxeEnchantment(axeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                AxeEnchantments.toggleAxeEnchantment(axeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.FORTUNE.location())) {
                AxeEnchantments.toggleAxeEnchantment(axeEnchantments, Enchantments.FORTUNE, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                AxeEnchantments.toggleAxeEnchantment(axeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getHoeEnchantments() {
        return null;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getAxeEnchantments() {
        return null;
    }


    public boolean hasNetherUpgrade() {
        return false;
    }

    public CustomData getCustomData() {
        return CustomData.EMPTY;
    }

    public <T extends FarmTileentity> void setSwordEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> swordEnchantments = farm.getSwordEnchantments();
        ItemEnchantments enchantments = farm.getSwordType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            if (entry.getKey().is(Enchantments.SHARPNESS.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.SHARPNESS, true);
            } else if (entry.getKey().is(Enchantments.FIRE_ASPECT.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.FIRE_ASPECT, true);
            } else if (entry.getKey().is(Enchantments.LOOTING.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.LOOTING, true);
            } else if (entry.getKey().is(Enchantments.KNOCKBACK.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.KNOCKBACK, true);
            } else if (entry.getKey().is(Enchantments.SMITE.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.SMITE, true);
            } else if (entry.getKey().is(Enchantments.SWEEPING_EDGE.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.SWEEPING_EDGE, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.BANE_OF_ARTHROPODS.location())) {
                SwordEnchantments.toggleEnchantment(swordEnchantments, Enchantments.BANE_OF_ARTHROPODS, true);
            }
        }
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getSwordEnchantments() {
        return null;
    }

    public ItemStack getSwordType() {
        return ItemStack.EMPTY;
    }


    public ItemStack getPickType() {
        return ItemStack.EMPTY;
    }

    public ItemStack getShovelType() {
        return ItemStack.EMPTY;
    }

    public <T extends FarmTileentity> void setPickaxeEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getPickaxeEnchantments();
        ItemEnchantments enchantments = farm.getPickType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            // Check and toggle enchantments based on their presence in the map
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.FORTUNE.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.FORTUNE, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                PickaxeEnchantments.togglePickaxeEnchantment(pickaxeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    public <T extends FarmTileentity> void setShovelEnchantmentStatus(T farm) {
        Map<ResourceKey<Enchantment>, Boolean> pickaxeEnchantments = farm.getShovelEnchantments();
        ItemEnchantments enchantments = farm.getShovelType().getTagEnchantments();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            // Check and toggle enchantments based on their presence in the map
            if (entry.getKey().is(Enchantments.MENDING.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.MENDING, true);
            } else if (entry.getKey().is(Enchantments.EFFICIENCY.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.EFFICIENCY, true);
            } else if (entry.getKey().is(Enchantments.UNBREAKING.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.UNBREAKING, true);
            } else if (entry.getKey().is(Enchantments.SILK_TOUCH.location())) {
                ShovelEnchantments.toggleShovelEnchantment(pickaxeEnchantments, Enchantments.SILK_TOUCH, true);
            }
        }
    }

    public boolean toggleSound() {
        return false;
    }

    public boolean getSound() {
        return false;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getPickaxeEnchantments() {
        return null;
    }

    protected Map<ResourceKey<Enchantment>, Boolean> getShovelEnchantments() {
        return null;
    }

    public long getTimer() {
        return 0;
    }
}