package net.skaerf.grandkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.skaerf.grandkit.actionbar.ActionBarAPI;

public class Events implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if (Maps.chatChannel.get(event.getPlayer()).equalsIgnoreCase("staff")) {
			event.setFormat(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(event.getPlayer(), "&b&l[&3SC&b&l]&r &8>> &r%vault_prefix% %player_name% &8&l>> &b"+event.getMessage())));
			for (Player online : Bukkit.getOnlinePlayers()) {
				if (!online.hasPermission("grandkit.staffchat.see")) {
					event.getRecipients().remove(online);
				}
			}
		}
		else {
			event.setFormat(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(event.getPlayer(), "&r&8[%grandkitx_level%&8]&r %vault_prefix% %player_name% &8&l>> &f"+event.getMessage())));
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		List<String> levelSaves = new ArrayList<String>();
		for (Entry<Player, Integer> entry : Maps.levelStorage.entrySet()) {
			System.out.println(entry);
		}
		Maps.chatChannel.put(event.getPlayer(), "default");
		if (Maps.lastJoined.get(event.getPlayer()) != null) {
			if ((System.currentTimeMillis()-Maps.lastJoined.get(event.getPlayer())) <= 86400000) {
				ActionBarAPI.sendActionBar(event.getPlayer(), ChatColor.translateAlternateColorCodes('&', "&e&lWelcome back! &aEnjoy the &l100xp&a!"));
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lThanks for joining back! &aBecause you have joined for two or more days in a row, you get a boost of 100xp! Enjoy!"));
				// TODO add xp reward
			}
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		XPManager.giveXP(event.getPlayer(), 125);
	}

}
