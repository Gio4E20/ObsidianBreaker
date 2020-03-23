package me.gioplugins.obsidianbreakerenchant.events;

import me.gioplugins.obsidianbreakerenchant.ObsidianBreakerEnchant;
import me.gioplugins.obsidianbreakerenchant.enchant.ObsidianBreaker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryClick implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCursor() != null && e.getCursor().getType().equals(Material.ENCHANTED_BOOK)) {
            if (e.getCursor().containsEnchantment(Enchantment.getById(ObsidianBreakerEnchant.obsidianBreaker.getId()))) {
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE) || e.getCurrentItem().getType().equals(Material.GOLD_PICKAXE)
                            || e.getCurrentItem().getType().equals(Material.IRON_PICKAXE) || e.getCurrentItem().getType().equals(Material.STONE_PICKAXE) ||
                            e.getCurrentItem().getType().equals(Material.WOOD_PICKAXE)) {
                        if (!(e.getCurrentItem().containsEnchantment(Enchantment.getById(ObsidianBreakerEnchant.obsidianBreaker.getId())))) {
                            e.setCancelled(true);

                            int slot = e.getSlot();
                            ItemStack pickaxe = e.getCurrentItem();
                            ItemMeta meta = pickaxe.getItemMeta();

                            if (meta.hasLore()) {
                                List<String> lore = meta.getLore();
                                lore.add(ChatColor.GRAY + "Obsidian Breaker I");
                                meta.setLore(lore);
                            } else {
                                ArrayList<String> lore = new ArrayList<>();
                                lore.add(ChatColor.GRAY + "Obsidian Breaker I");
                                meta.setLore(lore);
                            }

                            pickaxe.setItemMeta(meta);
                            pickaxe.addUnsafeEnchantment(Enchantment.getById(ObsidianBreakerEnchant.obsidianBreaker.getId()), 1);
                            e.getCursor().setType(Material.AIR);
                            e.getInventory().setItem(slot, pickaxe);

                        } else {
                            e.getWhoClicked().sendMessage(ChatColor.RED + "That pickaxe already has Obsidian Breaker enchant");
                        }
                    }
                }
            }
        }
    }
}