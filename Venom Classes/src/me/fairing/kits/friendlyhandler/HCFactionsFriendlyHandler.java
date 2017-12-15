package me.fairing.kits.friendlyhandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

public class HCFactionsFriendlyHandler implements FriendlyHandler {

	@Override
	public Set<Player> getOnlineFactionMembers(Player player) {
		FPlayer me = FPlayers.getInstance().getByPlayer(player);
		if (me.getFaction().isNormal()) {
			return new HashSet<>(me.getFaction().getOnlinePlayers());
		}
		return Collections.singleton(player);
	}

	@Override
	public boolean isInSafezone(Location location) {
		FLocation here = new FLocation(location);
		Faction faction = Board.getInstance().getFactionAt(here);
		return faction != null && faction.isSafeZone();
	}

}
