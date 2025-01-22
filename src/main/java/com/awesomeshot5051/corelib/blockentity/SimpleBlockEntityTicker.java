package com.awesomeshot5051.corelib.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleBlockEntityTicker<T extends BlockEntity> implements BlockEntityTicker<T> {

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, T entity) {
        if (entity instanceof com.awesomeshot5051.corelib.blockentity.ITickableBlockEntity tickable) {
            tickable.tick();
        }
        if (level.isClientSide) {
            if (entity instanceof com.awesomeshot5051.corelib.blockentity.IClientTickableBlockEntity tickable) {
                tickable.tickClient();
            }
        } else {
            if (entity instanceof com.awesomeshot5051.corelib.blockentity.IServerTickableBlockEntity tickable) {
                tickable.tickServer();
            }
        }
    }

}
