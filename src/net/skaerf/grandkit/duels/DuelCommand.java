package net.skaerf.grandkit.duels;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.grandkit.Maps;

public class DuelCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Console cannot execute Duels commands!");
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aUsage: &l/duel <player/accept/deny> [player]"));
		}
		if (args[0].equalsIgnoreCase("accept")) {
			if (args.length == 1) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aPlease specify the player you want to accept! &l/duel accept <player>"));
			}
			// get from arraylist 
		}
		else if (args[0].equalsIgnoreCase("deny")) {
			if (args.length == 1) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aPlease specify the player you want to deny! &l/duel deny <player>"));
			}
			// send message to other player that they are bad and got rejected on minecraft 
		}
		else {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aThat player cannot be found! Are they online?"));
			}
			else {
				Maps.duelData.add(player.getUniqueId().toString()+":"+target.getUniqueId().toString()+":"+System.currentTimeMillis());
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aYour duel request was sent to &l"+target.getName()+"&a! They have 120 seconds to accept."));
			}
			
		}
		return true;
	}

}
