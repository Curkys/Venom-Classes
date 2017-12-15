package me.fairing.kits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairing.kits.ClassesPlugin;
import me.fairing.kits.utils.ItemStackBuilder;

public class KitsCommand implements CommandExecutor, Listener {

	private final ClassesPlugin plugin;

	public KitsCommand(ClassesPlugin plugin) {
		this.plugin = plugin;

	}

	public static Inventory kits = Bukkit.createInventory(null, 45,
			ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "Select a kit.");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if(!(sender instanceof Player)) { 
			return true;
		}
		Player player = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("kits")) {
			//TODO ItemStacks in here that are in the gui
			ItemStack gray = new ItemStackBuilder(Material.STAINED_GLASS_PANE).setName(" ").build();
			for (int i = 0; i < kits.getSize(); i ++) {
				if (kits.getItem(i) == null) kits.setItem(i, gray);
				player.updateInventory();
				player.openInventory(kits);
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void inventoryEvent(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		if(event.getClickedInventory() == null || (event.getCurrentItem().getType() == null)) {
			return;
		}
		if (event.getClickedInventory().equals(kits) && event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
			event.setCancelled(true);
	}
	}
}
