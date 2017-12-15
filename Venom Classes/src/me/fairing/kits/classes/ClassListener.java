package me.fairing.kits.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.EquipmentSetEvent;
import org.bukkit.potion.PotionEffect;

import me.fairing.kits.ClassesPlugin;

public class ClassListener implements Listener {
	
	public static final Map<UUID, ClassType> EQUIPPED_CLASSES;

	@Nonnull
	private final ClassesPlugin plugin;

	public ClassListener(ClassesPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onEquip(EquipmentSetEvent event) {
		Player player = (Player) event.getHumanEntity();
		if (player.getTicksLived() < 30) return;
		ClassType current = EQUIPPED_CLASSES.get(player.getUniqueId());
		if (current != null) {
			if (!current.getArmor().wearingFull(player)) {
				EQUIPPED_CLASSES.remove(player.getUniqueId());
				player.sendMessage(ChatColor.YELLOW + "Unequipped class " + current.getName() + ".");
				for (PotionEffect effect : current.getEffects()) player.removePotionEffect(effect.getType());
			}
			return;
		}
		for (ClassType type : ClassType.values()) {
			if (type.getArmor().wearingFull(player)) {
				for (PotionEffect effect : type.getEffects()) player.addPotionEffect(effect, true);
				player.sendMessage(ChatColor.YELLOW + "Equipped class " + type.getName() + ".");
				EQUIPPED_CLASSES.put(player.getUniqueId(), type);
				return;
			}
		}
	}
	
	static {
		EQUIPPED_CLASSES = new HashMap<>();
	}
	
}
