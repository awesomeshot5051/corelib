package com.awesomeshot5051.corelib.blockentity;

import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public class FakeWorldTileentity extends SyncableTileentity {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected boolean fakeWorld;
    protected BlockState defaultState;

    public FakeWorldTileentity(BlockEntityType<?> type, BlockState defaultState, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.defaultState = defaultState;
    }

    public FakeWorldTileentity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void setFakeWorld(Level w) {
        level = w;
        fakeWorld = true;
    }

    @Override
    public BlockState getBlockState() {
        if (fakeWorld) {
            return defaultState;
        }
        return super.getBlockState();
    }
}
