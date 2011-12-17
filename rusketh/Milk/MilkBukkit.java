package rusketh.Milk;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import rusketh.Milk.Command.MilkCommandManager;
import rusketh.Milk.Event.MilkEventManager;
import rusketh.Milk.Player.MilkPlayerManager;
import rusketh.Milk.Plugin.MilkPlugin;
import rusketh.Milk.Plugin.MilkPluginManager;

public class MilkBukkit extends JavaPlugin {
	
	private MilkPlayerManager playerManager;
	private MilkEventManager eventManager;
	private MilkCommandManager commandManager;
	private MilkPluginManager pluginManager;
	
	public void CreateManagers() {
		playerManager = new MilkPlayerManager(this);
		eventManager = new MilkEventManager(this);
		commandManager = new MilkCommandManager(this);
		pluginManager = new MilkPluginManager(this, eventManager, commandManager);
	}
	
	public void Message(String message) {
		System.out.print(message);
	}
	
	public void onEnable() {
		CreateManagers();
		pluginManager.EnablePlugins();
		CallEvent("MILK_INIT", null);
	}
	
	public void onDisable() {
		pluginManager.DisablePlugins();
	}
	
	public MilkPlugin GetPlugin(String name) {
		return pluginManager.GetPlugin(name);
	}
	
	public void CallEvent(String eventName, Object event) {
		eventManager.Call(eventName, event);
	}
	
	public boolean RunCommand(CommandSender Sender, String usedCommand, String[] args) {
		return commandManager.Run(Sender, usedCommand, args);
	}
	
	public void RegisterPlugin(MilkPlugin plugin) {
		pluginManager.RegisterPlugin(plugin);
	}
	
	public MilkPlayerManager GetPlayerManager() {
		return playerManager;
	}
	
	/*---------------------------
	 *  Milk Utility Functions
	 ---------------------------*/
	
	public String Format(String message) {
		message = message.replace("%red%", ChatColor.RED.toString());
		message = message.replace("%Red%", ChatColor.DARK_RED.toString());
		message = message.replace("%yellow%", ChatColor.YELLOW.toString());
		message = message.replace("%gold%", ChatColor.GOLD.toString());
		message = message.replace("%green%", ChatColor.GREEN.toString());
		message = message.replace("%Green%", ChatColor.DARK_GREEN.toString());
		message = message.replace("%aqua%", ChatColor.AQUA.toString());
		message = message.replace("%Aqua%", ChatColor.DARK_AQUA.toString());
		message = message.replace("%blue%", ChatColor.BLUE.toString());
		message = message.replace("%Blue%", ChatColor.DARK_BLUE.toString());
		message = message.replace("%purple%", ChatColor.LIGHT_PURPLE.toString());
		message = message.replace("%Purple%", ChatColor.DARK_PURPLE.toString());
		message = message.replace("%black%", ChatColor.BLACK.toString());
		message = message.replace("%grey%", ChatColor.GRAY.toString());
		message = message.replace("%Grey%", ChatColor.DARK_GRAY.toString());
		message = message.replace("%white%", ChatColor.WHITE.toString());
		
		message = message.replace("%servername%", "" + getServer().getName());
		message = message.replace("%serverip%", "" + getServer().getIp());
		message = message.replace("%serverport%", "" + getServer().getPort());
		
		return message;
	}
	
	public String Format(String message,World world) {
		message = Format(message);
		message = message.replace("%worldtime%", GameTime(world.getTime()));
		message = message.replace("%worldname%", world.getName());
		
		return message;
	}
	
	public String Format(String message,Player player) {
		message = Format(message,player.getWorld());
		message = message.replace("%playername%", player.getDisplayName());
		
		return message;
	}
	
	//Borrowed code: Sk89q Commandbook.
	public String GameTime(long time) {
	        int hours = (int) ((time / 1000 + 8) % 24);
	        int minutes = (int) (60 * (time % 1000) / 1000);
	        return String.format("%d:%02d %s",
	                (hours % 12) == 0 ? 12 : hours % 12, minutes,
	                hours < 12 ? "am" : "pm");
	    }
	
	
}
