package me.gioplugins.obsidianbreakerenchant.itemStacks;

import me.gioplugins.obsidianbreakerenchant.ObsidianBreakerEnchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class OBBook {
    public static void giveObsidianBreakerBook(Player player){
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        book.addUnsafeEnchantment(Enchantment.getById(ObsidianBreakerEnchant.obsidianBreaker.getId()), 1);
        ItemMeta meta = book.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Obsidian Breaker I");
        lore.add(ChatColor.GRAY + "Drag n' Click in a Pickaxe");
        meta.setLore(lore);
        book.setItemMeta(meta);

        player.getInventory().addItem(book);
    }
}
