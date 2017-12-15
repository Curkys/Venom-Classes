package me.fairing.kits;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.fairing.kits.classes.ClassListener;
import me.fairing.kits.commands.KitsCommand;
import me.fairing.kits.friendlyhandler.EmptyFriendlyHandler;
import me.fairing.kits.friendlyhandler.FriendlyHandler;
import me.fairing.kits.friendlyhandler.HCFactionsFriendlyHandler;
import me.fairing.kits.friendlyhandler.MangoFriendlyHandler;
import me.fairing.kits.listeners.ArcherClassListeners;
import me.fairing.kits.listeners.BardClassListeners;

public class ClassesPlugin extends JavaPlugin {
	
	@Getter
	private static ClassesPlugin instance;
	
	@Getter
	private FriendlyHandler friendlyHandler;
	
	@Override
	public void onEnable() {
		ClassesPlugin.instance = this;
		this.saveConfig();
		this.reloadConfig();
		findFriendlyHandler();
		Bukkit.getPluginManager().registerEvents(new ClassListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ArcherClassListeners(this), this);
		Bukkit.getPluginManager().registerEvents(new BardClassListeners(this), this);
		this.getCommand("kits").setExecutor(new KitsCommand(this));
	}
	
	private void findFriendlyHandler() {
		try {
			Class.forName("org.zencode.mango.Mango");
			friendlyHandler = new MangoFriendlyHandler();
			Bukkit.getLogger().info("[Kits] Successfully created friendly handler for Mango.");
			return;
		} catch (ClassNotFoundException exception) {}
		try {
			Class.forName("com.massivecraft.factions.P");
			friendlyHandler = new HCFactionsFriendlyHandler();
			Bukkit.getLogger().info("[Kits] Successfully created friendly handler for HCFactions.");
			return;
		} catch (ClassNotFoundException exception) {}
		Bukkit.getLogger().info("[Kits] Could not find any friendly handlers!");
		friendlyHandler = new EmptyFriendlyHandler();
	}

}
