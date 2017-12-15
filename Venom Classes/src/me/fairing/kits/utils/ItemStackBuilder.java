package me.fairing.kits.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
 private ItemStack itemStack;
 private ItemMeta itemMeta;

 public ItemStackBuilder(Material material) {
  itemStack = new ItemStack(material);
  itemMeta = itemStack.getItemMeta();
 }

 public ItemStackBuilder amount(int amount) {
  itemStack.setAmount(amount);
  return this;
 }

 public ItemStackBuilder setName(String displayName) {
  itemMeta.setDisplayName(displayName);
  return this;
 }

 public ItemStackBuilder addLore(String... strings) {
  List<String> loreArray = new ArrayList<String>();
  for (String loreBit : strings) {
   // Might want to replace this with ChatColor.WHITE in your code.
   loreArray.add(ChatColor.WHITE + loreBit);
  }
  itemMeta.setLore(loreArray);
  return this;
 }

 public ItemStackBuilder enchant(Enchantment enchanement, int level, boolean ignoreLevelRestriction) {
  itemMeta.addEnchant(enchanement, level, ignoreLevelRestriction);
  return this;
 }

 public ItemStackBuilder durability(int durability) {
  itemStack.setDurability((short) durability);
  return this;
 }

 public ItemStack build() {
  ItemStack clonedStack = itemStack.clone();
  clonedStack.setItemMeta(itemMeta.clone());
  return clonedStack;
 }
}
