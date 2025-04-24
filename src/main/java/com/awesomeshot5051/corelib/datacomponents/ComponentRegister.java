package com.awesomeshot5051.corelib.datacomponents;

import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.component.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class ComponentRegister {
    private static String MODID = "";
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockEntityData>> BLOCK_ENTITY_DATA_COMPONENT = DATA_COMPONENT_TYPE_REGISTER.register("block_entity", () -> DataComponentType.<BlockEntityData>builder().networkSynchronized(BlockEntityData.STREAM_CODEC).build());


    public static class PlantFarmComponents {
        public static final DeferredRegister.DataComponents PLANT_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
        public static final Supplier<DataComponentType<ItemContainerContents>> HOE_TYPE = PLANT_REGISTRAR.registerComponentType(
                "hoe_type", builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC));
        public static final Supplier<DataComponentType<ItemContainerContents>> AXE_TYPE = PLANT_REGISTRAR.registerComponentType(
                "axe_type", builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC));
        public static final Supplier<DataComponentType<ItemContainerContents>> SHEARS = PLANT_REGISTRAR.registerComponentType(
                "shears",
                builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC));
        public static Supplier<DataComponentType<ItemContainerContents>> UPGRADE = PLANT_REGISTRAR.registerComponentType(
                "upgrade",
                builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC)
        );

        public static void plantInit(IEventBus eventBus, String modid) {
            MODID = modid;
            DATA_COMPONENT_TYPE_REGISTER.register(eventBus);
            PLANT_REGISTRAR.register(eventBus);

        }
    }

    public static class ResourceFarmComponents {
        public static void resourceInit(IEventBus eventBus, String modid) {
            MODID = modid;
            DATA_COMPONENT_TYPE_REGISTER.register(eventBus);
            RESOURCE_REGISTRAR.register(eventBus);
        }

        public static final DeferredRegister.DataComponents RESOURCE_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
        public static final DeferredRegister.DataComponents AE2_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
        public static final DeferredRegister.DataComponents EAE2_REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
        public static final Supplier<DataComponentType<ItemContainerContents>> PICK_TYPE = RESOURCE_REGISTRAR.registerComponentType(
                "pick_type",
                builder -> builder

                        .persistent(ItemContainerContents.CODEC)

                        .networkSynchronized(ItemContainerContents.STREAM_CODEC)
        );
        public static Supplier<DataComponentType<ItemContainerContents>> RESOURCE_UPGRADE = RESOURCE_REGISTRAR.registerComponentType(
                "upgrade",
                builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC)
        );
        public static Supplier<DataComponentType<ItemContainerContents>> AE2ITEMS = AE2_REGISTRAR.registerComponentType(
                "ae2_items",
                builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC)
        );
        public static Supplier<DataComponentType<ItemContainerContents>> EAE2ITEMS = EAE2_REGISTRAR.registerComponentType(
                "eae2_items",
                builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC)
        );
    }
}
