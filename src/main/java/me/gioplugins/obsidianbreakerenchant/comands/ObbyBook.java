package me.gioplugins.obsidianbreakerenchant.comands;

import me.gioplugins.obsidianbreakerenchant.itemStacks.OBBook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ObbyBook implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(sender instanceof Player)
        {
            if(player.hasPermission("obsidianBreaker.give"))
            {
                OBBook.giveObsidianBreakerBook(player);
            }
        }
        return true ;
    }
}
