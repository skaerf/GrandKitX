package net.skaerf.grandkit;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import net.skaerf.grandkit.actionbar.ActionBarAPI;

public class XPManager {
	
	public static void giveXP(Player player, int xpAmount) {
		if (Maps.xpStorage.get(player) != null) {
            Maps.xpStorage.put(player, Maps.xpStorage.get(player)+xpAmount);
            ActionBarAPI.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', ""));
        }
        else {
            Maps.xpStorage.put(player, xpAmount);
            giveReward(player, false);
        }
        if (Maps.xpStorage.get(player) >= 1250) {
            int currentXP = Maps.xpStorage.get(player);
            int newXP = currentXP-1250;
            Maps.xpStorage.put(player, newXP);
            if (Maps.levelStorage.get(player) != null) {
                Maps.levelStorage.put(player, Maps.levelStorage.get(player)+1);
                giveReward(player, true);
            }
            else {
                Maps.levelStorage.put(player, 1);
                giveReward(player, true);
            }
        }
	}
	public static void giveReward(Player player, boolean levelUp) {
		if (levelUp) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lXP >> &aYou levelled up! You are now on &lLevel "+Maps.levelStorage.get(player)+"!"));
		}
		else {
			player.sendMessage("xp gained");
		}
	}
	public static void spawnFireworks(Location location, int amount){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
       
        fw.setFireworkMeta(fwm);
        fw.detonate();
       
        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }

}
