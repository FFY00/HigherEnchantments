package io.github.ffy00.higherenchantments.structure;

import org.bukkit.configuration.file.FileConfiguration;

public interface ConfigPlugin {

    public FileConfiguration getPluginConfig();
    public FileConfiguration getPluginConfig(String name);

}
