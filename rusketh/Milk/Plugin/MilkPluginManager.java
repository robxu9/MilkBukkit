package rusketh.Milk.Plugin;

import java.util.Hashtable;

import rusketh.Milk.MilkBukkit;
import rusketh.Milk.Command.MilkCommandManager;
import rusketh.Milk.Event.MilkEventManager;

public class MilkPluginManager {
	
	private MilkBukkit milkBukkit;
	
	private MilkEventManager eventManager;
	private MilkCommandManager commandManager;
	private MilkPluginLoader pluginLoader;
	
	private Hashtable<String, MilkPlugin> plugins;
	
	public MilkPluginManager(MilkBukkit Milk, MilkEventManager events, MilkCommandManager commands) {
		milkBukkit = Milk;
		
		eventManager = events;
		commandManager = commands;
		
		pluginLoader = new MilkPluginLoader(milkBukkit, this);
		plugins = new Hashtable<String, MilkPlugin>();
		
		pluginLoader.LoadPlugins();
	}
	
	private void Message(String message) {
		milkBukkit.Message(message);
	}
	
	public void RegisterPlugin(MilkPlugin plugin) {
		
		plugin.Init(milkBukkit);
		
		String name = plugin.GetName();
		String version = plugin.GetVer();
		String author = plugin.GetAuthor();
		
		if ( name == null || name.equals("") ) {
			Message("Milk Error: Attempted to register unnamed plugin.");
			Message("When: Registering plugins.");
			return;
		}
		
		if ( name.split(".").length != 0 ) {
			Message("Milk Error: Invalid plugin name '" + name + "' name must not contain '.' (period).");
			Message("When: Registering plugins.");
		}
		
		if ( author == null || author == "" ) {
			author = "Unkown Author";
		}
		
		if ( version == null || version == "" ) {
			version = "Unkown Version";
		}
		
		MilkPlugin conflictor = GetPlugin(name);
		
		if ( conflictor != null ) {
			Message("Milk Error: Conflicting plugin found.");
			Message("When: Registering plugin '" + name + "' : '" + version + "' by '" + author + "'");
			return;
		}
		
		plugins.put(name.toLowerCase(), plugin);
		
		eventManager.RegisterEvents(plugin);
		commandManager.RegisterCommands(plugin);
		
		Message("Milk: Registered plugin '" + name + "' : '" + version + "' by '" + author + "'");
	}
	
	public MilkPlugin GetPlugin(String name) {
		return plugins.get(name.toLowerCase());
	}
	
	public void EnablePlugins() {
		for ( MilkPlugin plugin : plugins.values() ) {
			plugin.SetEnabled(true);
		}
	}
	
	public void DisablePlugins() {
		for ( MilkPlugin plugin : plugins.values() ) {
			plugin.SetEnabled(false);
		}
	}
}
