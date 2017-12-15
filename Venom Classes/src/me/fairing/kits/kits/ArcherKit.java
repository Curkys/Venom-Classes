package me.fairing.kits.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.fairing.kits.ClassesPlugin;
import me.fairing.kits.utils.ItemStackBuilder;

public class ArcherKit {

	public static void giveArcherKit(Player player) {
		ItemStack dsword = new ItemStackBuilder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL,
				ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_SHARPNESS_LEVEL"), true).build();
		ItemStack epearl = new ItemStackBuilder(Material.ENDER_PEARL).amount(16).build();
		ItemStack bow = new ItemStackBuilder(Material.BOW)
				.enchant(Enchantment.ARROW_DAMAGE,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_POWER_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.enchant(Enchantment.ARROW_INFINITE, 1, true).build();
		ItemStack cookedbeef = new ItemStackBuilder(Material.COOKED_BEEF).amount(64).build();
		ItemStack healthp = new ItemStack(Material.POTION, 1, (short) 16421);
		ItemStack sugar = new ItemStackBuilder(Material.SUGAR).amount(16).build();
		ItemStack lhelmet = new ItemStackBuilder(Material.LEATHER_HELMET)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack lchest = new ItemStackBuilder(Material.LEATHER_CHESTPLATE)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack llegs = new ItemStackBuilder(Material.LEATHER_LEGGINGS)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack lboots = new ItemStackBuilder(Material.LEATHER_BOOTS)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();

		player.getInventory().setHelmet(lhelmet);
		player.getInventory().setChestplate(lchest);
		player.getInventory().setLeggings(llegs);
		player.getInventory().setBoots(lboots);
		player.getInventory().setItem(2, bow);
		player.getInventory().setItem(8, sugar);
		player.getInventory().setItem(0, dsword);
		player.getInventory().setItem(1, epearl);
		player.getInventory().setItem(7, cookedbeef);
		for (int i = 0; i < 35; i++) {
			player.getInventory().addItem(healthp);
		}
	}

}
