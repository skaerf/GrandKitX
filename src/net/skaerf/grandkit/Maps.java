package net.skaerf.grandkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class Maps {
		
	// saves what channel the player is in in chat
	public static HashMap<Player, String> chatChannel = new HashMap<>();
	// saves when player last joined in milliseconds to determine daily playtime
	public static HashMap<Player, Integer> lastJoined = new HashMap<>();
	// saves xp amount to determine when to level up
	public static HashMap<Player, Integer> xpStorage = new HashMap<>();
	// saves player's level
	public static HashMap<Player, Integer> levelStorage = new HashMap<>();
	// list for duels info
	public static List<String> duelData = new ArrayList<String>();

}
