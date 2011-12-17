package rusketh.Milk.Event.Listeners;

import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import rusketh.Milk.MilkBukkit;

public class MilkPlayerListener extends PlayerListener {

	public MilkBukkit milkBukkit;
	
	public MilkPlayerListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}

	public void onPlayerJoin( PlayerJoinEvent event) {
		milkBukkit.GetPlayerManager().AddPlayer(event.getPlayer());
		milkBukkit.CallEvent("PLAYER_JOIN", event);
	}
	
	public void onPlayerQuit( PlayerQuitEvent event) {
		milkBukkit.CallEvent("PLAYER_QUIT", event);
		milkBukkit.GetPlayerManager().RemovePlayer(event.getPlayer());
	}
	
	public void onPlayerKick(PlayerKickEvent event) {
		milkBukkit.CallEvent("PLAYER_KICK", event);
	}

	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		milkBukkit.CallEvent("PLAYER_COMMAND_PREPROCESS", event);
	}
	
	public void onPlayerMove(PlayerMoveEvent event) {
		milkBukkit.CallEvent("PLAYER_MOVE", event);
	}
	
	public void onPlayerVelocity(PlayerVelocityEvent event) {
		milkBukkit.CallEvent("PLAYER_VELOCITY", event);
	}
	
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		milkBukkit.CallEvent("PLAYER_TELEPORT", event);
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		milkBukkit.CallEvent("PLAYER_RESPAWN", event);
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		milkBukkit.CallEvent("PLAYER_INTERACT", event);
	}
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		milkBukkit.CallEvent("PLAYER_INTERACT_ENTITY", event);
	}
	
	public void onPlayerLogin(PlayerLoginEvent event) {
		milkBukkit.CallEvent("PLAYER_LOGIN", event);
	}
	
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
		milkBukkit.CallEvent("PLAYER_PRELOGIN", event);
	}
	
	public void onPlayerEggThrow(PlayerEggThrowEvent event) {
		milkBukkit.CallEvent("PLAYER_EGG_THROW", event);
	}
	
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		milkBukkit.CallEvent("PLAYER_ANIMATION", event);
	}
	
	public void onInventoryOpen(PlayerInventoryEvent event) {
		milkBukkit.CallEvent("PLAYER_INVENTORY", event);
	}
	
	public void onItemHeldChange(PlayerItemHeldEvent event) {
		milkBukkit.CallEvent("PLAYER_ITEM_HELD", event);
	}
	
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		milkBukkit.CallEvent("PLAYER_DROP_ITEM", event);
	}
	
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		milkBukkit.CallEvent("PLAYER_PICKUP_ITEM", event);
	}
	
	public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		milkBukkit.CallEvent("PLAYER_TOGGLE_SNEAK", event);
	}
	
	public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
		milkBukkit.CallEvent("PLAYER_TOGGLE_SPRINT", event);
	}
	
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		milkBukkit.CallEvent("PLAYER_BUCKET_FILL", event);
	}
	
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		milkBukkit.CallEvent("PLAYER_BUCKET_EMPTY", event);
	}
	
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		milkBukkit.CallEvent("PLAYER_BED_ENTER", event);
	}
	
	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		milkBukkit.CallEvent("PLAYER_BED_LEAVE", event);
	}
	
	public void onPlayerPortal(PlayerPortalEvent event) {
		milkBukkit.CallEvent("PLAYER_PORTAL", event);
	}
	
	public void onPlayerFish(PlayerFishEvent event) {
		milkBukkit.CallEvent("PLAYER_FISH", event);
	}
	
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		milkBukkit.CallEvent("PLAYER_GAME_MODE_CHANGE", event);
	}
	
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		milkBukkit.CallEvent("PLAYER_CHANGED_WORLD", event);
	}
	
/*=====================================================================
	Player Chat Event!
=====================================================================*/
	public void onPlayerChat(PlayerChatEvent event) {
		if ( event.getMessage().startsWith("!") ) {
			
			String usedCommand = event.getMessage().toLowerCase().split(" ")[0].substring(1);
			String[] args = event.getMessage().substring(usedCommand.length() + 1).split(" ");
					
			if ( milkBukkit.RunCommand( (CommandSender)event.getPlayer(), usedCommand, args) ) {
				event.setCancelled(true);
				return;
			}
		}
		
		milkBukkit.CallEvent("PLAYER_CHAT", event);
	}


 
}