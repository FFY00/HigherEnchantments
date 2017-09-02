package io.github.ffy00.structure;

import com.comphenix.protocol.ProtocolManager;
import org.bukkit.configuration.file.FileConfiguration;

public interface ProtocolPlugin {

    public ProtocolManager getProtocolManager();

    public FileConfiguration getPluginConfig();
    public FileConfiguration getPluginConfig(String name);

}
