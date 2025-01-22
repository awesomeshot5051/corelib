package com.awesomeshot5051.corelib.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.file.GenericBuilder;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class DynamicConfig {

    protected com.electronwill.nightconfig.core.file.CommentedFileConfig config;
    private com.electronwill.nightconfig.core.conversion.ObjectConverter converter;

    public DynamicConfig() {
        converter = new com.electronwill.nightconfig.core.conversion.ObjectConverter();
    }

    public void init(java.nio.file.Path configFile, java.nio.file.Path defaultConfigFile) {
        boolean createDefaults = !configFile.toFile().exists() && !defaultConfigFile.toFile().exists();
        com.electronwill.nightconfig.core.file.GenericBuilder<com.electronwill.nightconfig.core.CommentedConfig, com.electronwill.nightconfig.core.file.CommentedFileConfig> builder = com.electronwill.nightconfig.core.file.CommentedFileConfig.builder(configFile).onFileNotFound(com.electronwill.nightconfig.core.file.FileNotFoundAction.CREATE_EMPTY).autosave();
        if (defaultConfigFile != null && defaultConfigFile.toFile().exists()) {
            builder.defaultData(defaultConfigFile);
        }
        config = builder.build();

        config.load();

        if (createDefaults) {
            setDefaults();
        }
        onLoad();
    }

    public void init(java.nio.file.Path configFile) {
        init(configFile, null);
    }

    protected void setDefaults() {

    }

    protected void onLoad() {

    }

    public <T> T get(String path, T defaultValue) {
        checkLoaded();
        return config.getOrElse(path, defaultValue);
    }

    public java.util.List<String> getSubValues(String path) {
        checkLoaded();
        com.electronwill.nightconfig.core.Config cfg;
        if (path == null || path.isEmpty()) {
            cfg = config;
        } else {
            cfg = config.<com.electronwill.nightconfig.core.Config>getOrElse(path, null);
        }
        if (cfg != null) {
            return cfg.entrySet().stream().map(com.electronwill.nightconfig.core.UnmodifiableConfig.Entry::getKey).collect(java.util.stream.Collectors.toList());
        }
        return java.util.Collections.emptyList();
    }

    public java.util.List<String> getSubValues() {
        checkLoaded();
        return getSubValues(null);
    }

    @javax.annotation.Nullable
    private com.electronwill.nightconfig.core.Config getSubConfig(String path) {
        checkLoaded();
        return config.<com.electronwill.nightconfig.core.Config>getOrElse(path, null);
    }

    public <T> T getObject(String path, java.util.function.Supplier<T> object, java.util.function.Supplier<T> defaultValue) {
        checkLoaded();
        com.electronwill.nightconfig.core.Config subConfig = getSubConfig(path);
        if (subConfig == null) {
            return defaultValue.get();
        }
        return converter.toObject(subConfig, object);
    }

    @javax.annotation.Nullable
    public <T> T getObject(String path, java.util.function.Supplier<T> object) {
        return getObject(path, object, () -> null);
    }

    public <T> T setObject(String path, T object) {
        checkLoaded();
        com.electronwill.nightconfig.core.Config config = converter.toConfig(object, com.electronwill.nightconfig.core.Config::inMemoryUniversal);
        set(path, config);
        return object;
    }

    public <T> T set(String path, T value) {
        checkLoaded();
        return config.set(path, value);
    }

    private void checkLoaded() {
        if (!isLoaded()) {
            throw new IllegalStateException("Config not loaded");
        }
    }

    public boolean isLoaded() {
        return config != null;
    }

    public static enum DynamicConfigType {
        SERVER, COMMON
    }

}
