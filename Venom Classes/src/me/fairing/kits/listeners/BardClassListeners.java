package me.fairing.kits.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import me.fairing.kits.ClassesPlugin;
import net.minecraft.util.gnu.trove.map.TObjectLongMap;
import net.minecraft.util.gnu.trove.map.hash.TObjectLongHashMap;

public class BardClassListeners implements Listener {

	TObjectLongMap<UUID> effectExpiry = new TObjectLongHashMap<>();

	public static final Table<BardEffectType, Material, PotionEffect> BARD_EFFECTS;

	private final ClassesPlugin plugin;

	public BardClassListeners(ClassesPlugin plugin) {
		this.plugin = plugin;
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
			
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getItemInHand() != null && BARD_EFFECTS.row(BardEffectType.HELD).containsKey(player.getItemInHand().getType())) {
					int status = 0;
					for (ItemStack item : player.getInventory().getArmorContents()) {
						if (item != null && item.getType().name().startsWith("GOLD")) status += 1;
					}
					if (status == 4) {
						PotionEffect effect = BARD_EFFECTS.row(BardEffectType.HELD).get(player.getItemInHand().getType());
						plugin.getFriendlyHandler().getOnlineFactionMembers(player).forEach(other -> applyPotionEffect(other, effect));
					}
				}
			}
			
		}, 4l, 4l);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteractSugar(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand() != null && BARD_EFFECTS.row(BardEffectType.CLICKABLE).containsKey(player.getItemInHand().getType()) && event.getAction().name().startsWith("RIGHT")) {
			int status = 0;
			for (ItemStack item : player.getInventory().getArmorContents()) {
				if (item != null && item.getType().name().startsWith("GOLD")) status += 1;
			}
			if (status == 4) {
				long remaining;
				if ((remaining = effectExpiry.get(player.getUniqueId()) - System.currentTimeMillis()) > 0l) {
					player.sendMessage(ChatColor.RED + "You cannot use this for another " + String.format("%.1f", remaining / 1000d) + " seconds.");
					return;
				}
				PotionEffect effect = BARD_EFFECTS.row(BardEffectType.CLICKABLE).get(player.getItemInHand().getType());
				Map<UUID, PotionEffect> restores = new HashMap<>();
				for (Player other : plugin.getFriendlyHandler().getOnlineFactionMembers(player)) {
					if (other.hasPotionEffect(effect.getType())) {
						for (PotionEffect effect$$: other.getActivePotionEffects()) {
							if (effect$$.getType() == effect.getType()) restores.put(other.getUniqueId(), effect$$);
						}
					}
				}
				plugin.getFriendlyHandler().getOnlineFactionMembers(player).forEach(other -> applyPotionEffect(other, effect));
				long cooldown = TimeUnit.SECONDS.toMillis(30);
				effectExpiry.put(player.getUniqueId(), cooldown + System.currentTimeMillis());
				Bukkit.getScheduler().runTaskLater(plugin, () -> {
					restores.forEach((id, restoreEffect) -> {
						Player target = Bukkit.getPlayer(id);
						if (target != null) target.addPotionEffect(restoreEffect, true);
					});
				}, effect.getDuration() - 10);
			}
		}

	}
	
	public static void applyPotionEffect(Player player, PotionEffect effect) {
		for (PotionEffect $effect : player.getActivePotionEffects()) {
			if ($effect.getType() == effect.getType()) {
				if ($effect.getDuration() > effect.getDuration()) return;
				if ($effect.getAmplifier() > effect.getDuration()) return;
			}
		}
		player.addPotionEffect(effect, true);
	}
	
	public static enum BardEffectType {
		
		CLICKABLE, HELD;
		
	}

	static {
		BARD_EFFECTS = HashBasedTable.create();
		BARD_EFFECTS.put(BardEffectType.CLICKABLE, Material.SUGAR, new PotionEffect(PotionEffectType.SPEED, 200, 2));
		BARD_EFFECTS.put(BardEffectType.CLICKABLE, Material.BLAZE_POWDER, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
		BARD_EFFECTS.put(BardEffectType.HELD, Material.SUGAR, new PotionEffect(PotionEffectType.SPEED, 140, 1));
		BARD_EFFECTS.put(BardEffectType.HELD, Material.BLAZE_POWDER, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 140, 0));
	}

}
