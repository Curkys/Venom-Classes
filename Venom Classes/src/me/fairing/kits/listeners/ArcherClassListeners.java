package me.fairing.kits.listeners;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.fairing.kits.ClassesPlugin;
import me.fairing.kits.classes.ClassType;
import net.minecraft.util.gnu.trove.map.TObjectLongMap;
import net.minecraft.util.gnu.trove.map.hash.TObjectLongHashMap;

public class ArcherClassListeners implements Listener {

	TObjectLongMap<UUID> sugarExpiry = new TObjectLongHashMap<>();

	private final ClassesPlugin plugin;

	public ArcherClassListeners(ClassesPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteractSugar(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.getItemInHand() != null && player.getItemInHand().getType() == Material.SUGAR && event.getAction() == Action.RIGHT_CLICK_AIR) {
			int status = 0;
			for (ItemStack item : player.getInventory().getArmorContents()) {
				if (item != null && item.getType().name().startsWith("LEATHER")) status += 1;
			}
			if (status == 4) {
				long remaining;
				if ((remaining = sugarExpiry.get(player.getUniqueId()) - System.currentTimeMillis()) > 0l) {
					player.sendMessage(ChatColor.RED + "You cannot use this for another " + String.format("%.1f", remaining / 1000d) + " seconds.");
					return;
				}
				player.sendMessage(ChatColor.YELLOW + "You activated Speed 4 for 30 seconds!");
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 3), true);
				long cooldown = TimeUnit.SECONDS.toMillis(30);
				sugarExpiry.put(player.getUniqueId(), cooldown + System.currentTimeMillis());
				Bukkit.getScheduler().runTaskLater(plugin, () -> {
					if (ClassType.ARCHER.getArmor().wearingFull(player)) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), true);
				}, cooldown / 50 - 40);
			}
		}

	}
}
