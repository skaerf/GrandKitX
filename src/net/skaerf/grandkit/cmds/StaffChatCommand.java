package net.skaerf.grandkit.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.skaerf.grandkit.Maps;

public class StaffChatCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Console cannot use staffchat!");
		}
		Player player = (Player) sender;
		if (player.hasPermission("grandkit.staffchat.use")) {
			if (args.length == 0) {
				if (Maps.chatChannel.get(player).equals("staff")) {
					player.sendMessage(ChatColor.GREEN+"Staff chat toggled off!");
					Maps.chatChannel.put(player, "default");
				}
				else {
					player.sendMessage(ChatColor.GREEN+"Staff chat toggled on!");
					Maps.chatChannel.put(player, "staff");
				}
			}
			else {
				String msg = "";
				for (int i = 0; i > args.length; i++) {
					msg = msg+i+" ";
				}
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (online.hasPermission("grandkit.staffchat.see")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "&b&l[&3SC&b&l]&r &8>> &r%vault_prefix% %player_name% &8&l>> &b"+msg)));
					}
				}
			}
		}
		else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, but you don't have permission!"));
		}
		return true;
	}

}
