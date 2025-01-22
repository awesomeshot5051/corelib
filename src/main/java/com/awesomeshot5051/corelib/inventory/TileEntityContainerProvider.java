package com.awesomeshot5051.corelib.inventory;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TileEntityContainerProvider implements MenuProvider {

    private com.awesomeshot5051.corelib.inventory.TileEntityContainerProvider.ContainerCreator container;
    private BlockEntity tileEntity;

    public TileEntityContainerProvider(com.awesomeshot5051.corelib.inventory.TileEntityContainerProvider.ContainerCreator container, BlockEntity tileEntity) {
        this.container = container;
        this.tileEntity = tileEntity;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(tileEntity.getBlockState().getBlock().getDescriptionId());
    }

    public static void openGui(Player player, BlockEntity tileEntity, com.awesomeshot5051.corelib.inventory.TileEntityContainerProvider.ContainerCreator containerCreator) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new com.awesomeshot5051.corelib.inventory.TileEntityContainerProvider(containerCreator, tileEntity), packetBuffer -> packetBuffer.writeBlockPos(tileEntity.getBlockPos()));
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        return container.create(i, playerInventory, playerEntity);
    }

    public interface ContainerCreator {
        AbstractContainerMenu create(int i, Inventory playerInventory, Player playerEntity);
    }
}
