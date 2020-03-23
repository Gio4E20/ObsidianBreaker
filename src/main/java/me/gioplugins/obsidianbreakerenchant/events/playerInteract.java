package me.gioplugins.obsidianbreakerenchant.events;

import me.gioplugins.obsidianbreakerenchant.ObsidianBreakerEnchant;
import me.gioplugins.obsidianbreakerenchant.enchant.ObsidianBreaker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class playerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (player.getItemInHand().getItemMeta() != null && player.getItemInHand() != null && player.getItemInHand().getItemMeta().getDisplayName() != null) {
            if (player.getItemInHand().containsEnchantment(Enchantment.getById(ObsidianBreakerEnchant.obsidianBreaker.getId()))) {
                if (e.getClickedBlock().getType().equals(Material.OBSIDIAN)) {
                    e.getClickedBlock().breakNaturally();
                }
            }
        }
    }
}
