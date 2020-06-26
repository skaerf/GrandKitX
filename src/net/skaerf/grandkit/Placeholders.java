package net.skaerf.grandkit;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion {
	
	Main plugin;
	
	@Override
    public boolean persist(){
        return true;
    }

	@Override
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier() {
		return "BigassCore";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

	@Override
    public String onPlaceholderRequest(Player player, String placeholder) {
        if(player == null){
            return "";
        }
        
        // placeholders
        if (placeholder.equals("level")) {
        	return Maps.levelStorage.get(player).toString();
        }
        return null;
	}

}
