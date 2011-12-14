package rusketh.Milk.Command;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rusketh.Milk.MilkBukkit;
import rusketh.Milk.Plugin.MilkPlugin;

public class MilkCommandManager {
	
	private MilkBukkit milkBukkit;
	
	private HashMap<String, Method> CommandMethods = new HashMap<String, Method>();
	private HashMap<String, String> CommandPlugins = new HashMap<String, String>();
	
	private Hashtable<String, String> CommandAliases = new Hashtable<String, String>();
	
	public MilkCommandManager(MilkBukkit Milk) {
		milkBukkit = Milk;
	}
	
	private void Message(String message) {
		milkBukkit.Message(message);
	}
	
	private String RegisterCommand(MilkPlugin plugin, String Name, Method method) {
		String pluginName = plugin.GetName().toLowerCase();
		Name = pluginName + Name;
		
		CommandMethods.put(Name, method);
		CommandPlugins.put(Name, pluginName);
		
		return Name;
	}
	
	public void RegisterCommands(MilkPlugin plugin) {
		for ( Method method : plugin.getClass().getMethods() )
		{
			try {
				if ( !method.isAnnotationPresent(MilkCommand.class) ) {
					continue;
				}
				
				MilkCommand command = method.getAnnotation(MilkCommand.class);
				String commandName = RegisterCommand(plugin, command.names()[0], method);
				
				for (String name : command.names() ) {
					CommandAliases.put(name, commandName);
				}
			} catch (Throwable e) {
				Message( "Milk Error: '" + e + "'.");
				Message( "When: Registering commands from plugin '" + plugin.GetName() + "'.");
				//TODO Let this report the command!
			}
		} 
	
	}
	
	public boolean Run(CommandSender sender, String usedCommand, String[] args)
	{
		boolean sucess = false;
		
		try
		{
			
			String commandName = CommandAliases.get(usedCommand);
			
			if ( commandName != null ) {
				
				Method method = CommandMethods.get(commandName);
				MilkCommand command = method.getAnnotation(MilkCommand.class);
				
				if ( args.length < command.least() ) {
					throw new CommandException("%red%Not enought parameters:\n" + command.example());
				}
				
				if ( args.length > command.most() && command.most() != -1 )
				{
					throw new CommandException("%red%Too many parameters:\n" + command.example());
				}
				
				if ( sender instanceof Player ) {
					
					if ( !command.player() ) {
						throw new CommandException("%red%This command can only be used from console.");
					}
					
					//TODO auto perms!
					
				} else if ( !command.console() ) {
					throw new CommandException("This command can not be used from console.");
				}
				
				String pluginName = CommandPlugins.get(commandName);
				MilkPlugin plugin = milkBukkit.GetPlugin(pluginName);
				
				if ( plugin.Enabled() ) {
					sucess = (Boolean) method.invoke(plugin, sender, args, usedCommand);
				}
			}
			
			if ( !sucess ) {
				//TODO Custom command event!
			}
			
		} catch (CommandException e) {
			sender.sendMessage(milkBukkit.Format(e.toString()));
		} catch (Throwable e) {
			Message("Milk Error: '" + e.toString() + "'");
			Message("When: calling command '" + usedCommand + "'.");
			sender.sendMessage(milkBukkit.Format("%red%There was an error with this command please see console.")); 
		}
		
		return sucess;
	}
}
