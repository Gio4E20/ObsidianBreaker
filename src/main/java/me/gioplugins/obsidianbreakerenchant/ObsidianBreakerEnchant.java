package me.gioplugins.obsidianbreakerenchant;

import me.gioplugins.obsidianbreakerenchant.comands.ObbyBook;
import me.gioplugins.obsidianbreakerenchant.enchant.ObsidianBreaker;
import me.gioplugins.obsidianbreakerenchant.events.InventoryClick;
import me.gioplugins.obsidianbreakerenchant.events.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class ObsidianBreakerEnchant extends JavaPlugin {

    public static ObsidianBreaker obsidianBreaker;
    private static ArrayList<Enchantment> enchantments = new ArrayList<>();

    @Override
    public void onEnable() {
        obsidianBreaker = new ObsidianBreaker(69);
        registerEnchantment(obsidianBreaker);
        enchantments.add(obsidianBreaker);

        getCommand("obbybook").setExecutor(new ObbyBook());

        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
    }

    @Override
    public void onDisable() {
        try {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);


            for(Enchantment enchantment : enchantments) {
                if (byId.containsKey(enchantment.getId()))
                    byId.remove(enchantment.getId());

                if (byName.containsKey(enchantment.getName()))
                    byName.remove(enchantment.getName());
            }
        } catch (Exception ignored) { }
    }

    public void registerEnchantment(Enchantment enchantment){

        try{
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(enchantment);
            } catch (IllegalArgumentException e){
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
