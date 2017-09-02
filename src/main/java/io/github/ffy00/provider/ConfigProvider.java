/*
 * Copyright 2017 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */

package io.github.ffy00.provider;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


/**
 *
 * @author FFY00 <FFY00 at ffy00.github.io>
 */
public class ConfigProvider {

    protected JavaPlugin lplugin;

    public ConfigProvider(JavaPlugin plugin){
        lplugin = plugin;
    }

    public FileConfiguration get(){
        return get("config.yml");
    }

    public FileConfiguration get(String name){
        Bukkit.getConsoleSender().sendMessage("§cHigherEnchantments §e>> §bLoading config §d§o" + name);
        File f = new File(lplugin.getDataFolder(),  name);
        if(!f.exists()){
            Bukkit.getConsoleSender().sendMessage("§cHigherEnchantments §e>> §bCreating config §d§o" + name);
            lplugin.saveResource(name, false);
        }
        return YamlConfiguration.loadConfiguration(f);
    }

    public boolean save(FileConfiguration c) { return save(c, "config.yml"); }

    public boolean save(FileConfiguration c, String name){
        File f = new File(lplugin.getDataFolder().getAbsoluteFile() + "plugins" + File.separator + lplugin.getDescription().getName() + File.separator + name);
        if(!f.exists()){
            Bukkit.getConsoleSender().sendMessage("§cHigherEnchantments §e>> §bCreating config §d§o" + name);
            lplugin.saveResource(name, false);
        }
        try{
            Bukkit.getConsoleSender().sendMessage("§cHigherEnchantments §e>> §bSaving config §d§o" + name);
            c.save(f);
            return true;
        } catch (IOException ex){
            Bukkit.getConsoleSender().sendMessage("§cHigherEnchantments §e>> §4§l[!] §bCouldn't save config §d§o" + name);
            return false;
        }
    }

}
