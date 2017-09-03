/*
 * Copyright 2017 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */

package io.github.ffy00;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.ffy00.adapter.EnchantmentPacketAdapter;
import io.github.ffy00.provider.ConfigProvider;
import io.github.ffy00.structure.ConfigPlugin;
import io.github.ffy00.structure.ProtocolPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author FFY00 <FFY00 at ffy00.github.io>
 */
public class HigherEnchantments extends JavaPlugin implements ProtocolPlugin, ConfigPlugin{

    private ConfigProvider cp;
    private FileConfiguration config;

    private ProtocolManager pm;

    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§bEnabling §cHigherEnchantments §bv" + getDescription().getVersion() + " by FFY00!");

        setupConfig();
        registerListeners();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§bDisabling §cHigherEnchantments §bv" + getDescription().getVersion() + " by FFY00!");
    }

    /**
     * Setup Config
     */
    private void setupConfig(){
        cp = new ConfigProvider(this);
        config = cp.get();
    }

    /**
     * Register Listeners
     */
    private void registerListeners(){
        pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new EnchantmentPacketAdapter(this));
    }

    /**
     * Get Config
     */
    public FileConfiguration getPluginConfig() {
        return config;
    }

    public FileConfiguration getPluginConfig(String name) {
        return cp.get(name);
    }

    /**
     * Get Protocol Manager
     */
    public ProtocolManager getProtocolManager() {
        return pm;
    }
}
