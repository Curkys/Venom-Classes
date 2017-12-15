package me.fairing.kits.friendlyhandler;

import java.util.Collections;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EmptyFriendlyHandler implements FriendlyHandler {

	@Override
	public Set<Player> getOnlineFactionMembers(Player player) {
		return Collections.singleton(player);
	}

	@Override
	public boolean isInSafezone(Location location) {
		return false;
	}

}
