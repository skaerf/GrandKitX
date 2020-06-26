package net.skaerf.grandkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.skaerf.grandkit.cmds.StaffChatCommand;

public class Main extends JavaPlugin {

	
	@Override
	public void onEnable() {
		this.getCommand("staffchat").setExecutor(new StaffChatCommand());
		this.getServer().getPluginManager().registerEvents(new Events(), this);
		loadDeps();
	}
	public void loadDeps() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null && Bukkit.getPluginManager().getPlugin("BountifulAPI") != null) {
			new Placeholders().register();
			System.out.println("both required APIs are installed, starting");
		}
		else {
			System.out.println("--------------------\nPLEASE INSTALL PLACEHOLDERAPI AND BOUNTIFULAPI BECAUSE THE GrandKitX PLUGIN WILL NOT FUNCTION PROPERLY WITHOUT THEM.\nTHANK YOU\n--------------------");
		}
	}

}
