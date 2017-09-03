package io.github.ffy00.higherenchantments.adapter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import io.github.ffy00.higherenchantments.structure.ConfigPlugin;
import io.github.ffy00.higherenchantments.structure.ProtocolPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class EnchantmentPacketAdapter extends PacketAdapter {

    protected JavaPlugin plugin;
    protected ProtocolManager pm;
    protected FileConfiguration config;
    protected ArrayList<Integer> windowIds = new ArrayList<Integer>();

    public EnchantmentPacketAdapter(JavaPlugin plugin){
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.WINDOW_DATA, PacketType.Play.Server.SET_SLOT);
        plugin = plugin;
        pm = ((ProtocolPlugin) plugin).getProtocolManager();
        config = ((ConfigPlugin) plugin).getPluginConfig();
    }

    @Override
    public void onPacketSending(PacketEvent e){
        PacketContainer packet = e.getPacket();

        if(e.getPacketType() == PacketType.Play.Server.WINDOW_DATA){
            windowIds.add(packet.getIntegers().read(0));


        }

        if(e.getPacketType() == PacketType.Play.Server.SET_SLOT){
            if(!windowIds.contains(packet.getIntegers().read(0)) || packet.getIntegers().read(1) != 0)
                return;

            windowIds.remove(packet.getIntegers().getValues().get(0));

            ItemStack item = packet.getItemModifier().read(0);

            if(item.getEnchantments().isEmpty())
                return;

            for(Enchantment ench : item.getEnchantments().keySet()){
                item.removeEnchantment(ench);
                item.addUnsafeEnchantment(ench, 10);
            }

            e.getPlayer().getOpenInventory().setItem(0, item);

            PacketContainer newPacket = packet.deepClone();
            newPacket.getItemModifier().write(0, item);
            e.setPacket(newPacket);

        }
    }

}
