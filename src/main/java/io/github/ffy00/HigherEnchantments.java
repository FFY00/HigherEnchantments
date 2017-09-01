/*
 * Copyright 2017 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */

package io.github.ffy00;

import io.github.ffy00.provider.ConfigProvider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author FFY00 <FFY00 at ffy00.github.io>
 */
public class HigherEnchantments extends JavaPlugin {

    private PluginManager pm;
    private ConfigProvider cp;

    private FileConfiguration config;

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
        pm = getServer().getPluginManager();
    }

}
