package com.awesomeshot5051.corelib.config;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigBase {

    protected ModConfigSpec configSpec;

    public ConfigBase(ModConfigSpec.Builder builder) {

    }

    public void onLoad(net.neoforged.fml.event.config.ModConfigEvent.Loading evt) {

    }

    public void onReload(net.neoforged.fml.event.config.ModConfigEvent.Reloading event) {

    }

    public void setConfigSpec(ModConfigSpec configSpec) {
        this.configSpec = configSpec;
    }

    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

}
