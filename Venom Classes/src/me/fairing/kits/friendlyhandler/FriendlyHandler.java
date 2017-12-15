package me.fairing.kits.friendlyhandler;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface FriendlyHandler {
	
	Set<Player> getOnlineFactionMembers(Player player);
	
	boolean isInSafezone(Location location);

}
