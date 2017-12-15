package me.fairing.kits.classes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;

@Getter
public enum ClassType {

	ARCHER("Archer", Armor.LEATHER, new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0)),
	ROUGE("Rouge", Armor.CHAINMAIL, new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1)),
	BARD("Bard", Armor.GOLD, new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1)),
	MINER("Miner", Armor.IRON, new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0), new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
	
	
	private String name;
	private Armor armor;
	private PotionEffect[] effects;
	
	private ClassType(String name, Armor armor, PotionEffect... effects) {
		this.name = name;
		this.armor = armor;
		this.effects = effects;
	}
	
	public static enum Armor {
		
		LEATHER, GOLD, CHAINMAIL, IRON, DIAMOND;
		
		public boolean wearingFull(Player player) {
			int status = 0;
			for (ItemStack item : player.getInventory().getArmorContents()) {
				if (item != null && item.getType().name().startsWith(name())) status++;
			}
			return status == 4;
					
				}
			}
		}
		
	
