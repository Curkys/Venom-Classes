package me.fairing.kits.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.fairing.kits.ClassesPlugin;
import me.fairing.kits.utils.ItemStackBuilder;

public class BardKit {

	public static void giveBardKit(Player player) {
		ItemStack dsword = new ItemStackBuilder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL,
				ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_SHARPNESS_LEVEL"), true).build();
		ItemStack epearl = new ItemStackBuilder(Material.ENDER_PEARL).amount(16).build();
		ItemStack cookedbeef = new ItemStackBuilder(Material.COOKED_BEEF).amount(64).build();
		ItemStack healthp = new ItemStack(Material.POTION, 1, (short) 16421);
		ItemStack sugar = new ItemStackBuilder(Material.SUGAR).amount(16).build();
		ItemStack bpowder = new ItemStackBuilder(Material.BLAZE_POWDER).amount(16).build();
		ItemStack ironi = new ItemStackBuilder(Material.IRON_INGOT).amount(16).build();
		ItemStack ghasttear = new ItemStackBuilder(Material.GHAST_TEAR).amount(16).build();
		
		ItemStack ghelmet = new ItemStackBuilder(Material.GOLD_HELMET)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack gchest = new ItemStackBuilder(Material.GOLD_CHESTPLATE)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack glegs = new ItemStackBuilder(Material.GOLD_LEGGINGS)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();
		ItemStack gboots = new ItemStackBuilder(Material.GOLD_BOOTS)
				.enchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_PROT_LEVEL"), true)
				.enchant(Enchantment.DURABILITY,
						ClassesPlugin.getInstance().getConfig().getInt("MAP_KIT_UNBREAKING_LEVEL"), true)
				.build();

		player.getInventory().setHelmet(ghelmet);
		player.getInventory().setChestplate(gchest);
		player.getInventory().setLeggings(glegs);
		player.getInventory().setBoots(gboots);
		player.getInventory().setItem(0, dsword);
		player.getInventory().setItem(1, epearl);
		player.getInventory().setItem(7, cookedbeef);
		player.getInventory().addItem(sugar);
		player.getInventory().addItem(bpowder);
		player.getInventory().addItem(ironi);
		player.getInventory().addItem(ghasttear);
		
		for (int i = 0; i < 35; i++) {
			player.getInventory().addItem(healthp);
		}
	}

}
