package net.skaerf.grandkit.duels;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.skaerf.grandkit.Maps;

public class Duels {
	
	public static void acceptDuel(Player acceptingDuel, Player sentDuel) {
		for (String itemX : Maps.duelData) {
			String[] var = itemX.split(":");
			if (var[1].equalsIgnoreCase(acceptingDuel.getUniqueId().toString())&&var[0].equalsIgnoreCase(sentDuel.getUniqueId().toString())) {
				if (!(System.currentTimeMillis() - (Integer.parseInt(var[2])) < 120000)) {
					Maps.duelData.remove(var[0]+":"+var[1]+":"+var[2]);
					
					/* DUELS STARTING STUFF HERE
					 * player.teleport them to the locations they need to be at
					 * player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
					 * yaw and pitch are very important because otherwise they will be facing the wrong way round lmao
					 */
					
					// hide the player from everyone but themselves (and people with perms to see them like staff)
					for (Player online : Bukkit.getOnlinePlayers()) {
						if (!online.getName().equalsIgnoreCase(acceptingDuel.getName()) || !online.hasPermission("grandkit.duels.see")) {
							sentDuel.hidePlayer(online);
						}
						if (!online.getName().equalsIgnoreCase(sentDuel.getName()) || !online.hasPermission("grandkit.duels.see")) {
							acceptingDuel.hidePlayer(online);
						}
					}
					// rest of the shit here
					
				}
				else {
					acceptingDuel.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aSorry, but that request was sent more than two minutes ago! "
							+ "Please ask the user to send you another request!"));
				}
			}
		}
	}
	public static void denyDuel(Player player, Player target) {
		
	}
	public static void requestDuel(Player player, Player target) {
		Maps.duelData.add(player.getUniqueId().toString()+":"+target.getUniqueId().toString()+":"+System.currentTimeMillis());
		target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &a&l"+player.getName()+"&a has requested that you duel them!\n"
				+ "Accept with &l/duel accept "+player.getName()+"\n"
				+ "&aDeny with &l/duel deny "+player.getName()+"\n"
				+ "&aYou have &l120 seconds &ato accept!"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lGrandKit Duels >> &aYour duel request was sent to &l"+target.getName()+"&a!"
				+ " They have 120 seconds to accept."));
	}

}
