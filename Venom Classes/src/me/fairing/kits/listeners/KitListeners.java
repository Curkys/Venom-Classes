package me.fairing.kits.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.fairing.kits.ClassesPlugin;
import me.fairing.kits.commands.KitsCommand;

public class KitListeners implements Listener {

	private final ClassesPlugin plugin;

	public KitListeners(ClassesPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onKitClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(player.getInventory().equals(KitsCommand.kits)) {
			event.setCancelled(true);
		}
		}
	}

