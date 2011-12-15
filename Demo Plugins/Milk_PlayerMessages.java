import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import rusketh.Milk.Command.MilkCommand;
import rusketh.Milk.Event.MilkEvent;
import rusketh.Milk.Plugin.MilkPlugin;

public class Milk_PlayerMessages extends MilkPlugin {

	@Override
	public String GetName() {
		return "Milk Player Messages";
	}
	
	@MilkEvent(event="PLAYER_JOIN")
	public void JoinMessage(PlayerJoinEvent event) {
		String message = event.getJoinMessage();
		message = GetConfig().getString("join",message);
		message = GetMilk().Format(message, event.getPlayer());
		event.setJoinMessage(message);
	}
	
	@MilkEvent(event="PLAYER_QUIT")
	public void LeaveMessage(PlayerQuitEvent event) {
		String message = event.getQuitMessage();
		message = GetConfig().getString("leave",message);
		message = GetMilk().Format(message, event.getPlayer());
		event.setQuitMessage(message);
	}
	
	@MilkEvent(event="PLAYER_JOIN")
	public void WelcomeMessage(PlayerJoinEvent event) {
		String message = GetConfig().getString("welcome","");
		message = GetMilk().Format(message, event.getPlayer());
		
		event.getPlayer().sendMessage(message);
	}
	
	@MilkCommand(
			names = {"motd","messageoftheday"},
			example = "motd",
			desc = "See the MOTD.",
			least = 0, most = 0,
			perms = {""}
	)
	
	public boolean ShowMOTDCommand(CommandSender sender, String usedCommand, String[] args) {
		Player player = (Player)sender;
		if ( player == null ) { Message("FUCK no player!"); return false; }
		String message = GetConfig().getString("motd","");
		message = GetMilk().Format(message, player);
		for ( String line : message.split("\n") ) {
			player.sendMessage(line);
		}
		return true;
	}
}
