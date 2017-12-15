package me.fairing.kits.friendlyhandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.zencode.mango.Mango;
import org.zencode.mango.factions.FactionManager;
import org.zencode.mango.factions.claims.Claim;
import org.zencode.mango.factions.claims.ClaimManager;
import org.zencode.mango.factions.types.PlayerFaction;
import org.zencode.mango.factions.types.SystemFaction;

public class MangoFriendlyHandler implements FriendlyHandler {

	private final Mango mango;
	private final FactionManager factionManager;
	private final ClaimManager claimManager;
	
	public MangoFriendlyHandler() {
		this.mango = Mango.getInstance();
		this.factionManager = mango.getFactionManager();
		this.claimManager = mango.getClaimManager();
	}
	
	@Override
	public Set<Player> getOnlineFactionMembers(Player player) {
		PlayerFaction faction = factionManager.getFaction(player);
		if (faction != null) {
			return new HashSet<>(faction.getOnlinePlayers());
		}
		return Collections.singleton(player);
	}

	@Override
	public boolean isInSafezone(Location location) {
		Claim claim = claimManager.getClaimAt(location);
		if (claim != null && claim.getOwner() instanceof SystemFaction) {
			return !((SystemFaction) claim.getOwner()).isDeathbanBoolean();
		}
		return false;
	}

}
