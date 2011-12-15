package rusketh.Milk.Event;

import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;


import rusketh.Milk.MilkBukkit;
import rusketh.Milk.Event.Listeners.MilkBlockListener;
import rusketh.Milk.Event.Listeners.MilkEntityListener;
import rusketh.Milk.Event.Listeners.MilkInventoryListener;
import rusketh.Milk.Event.Listeners.MilkPlayerListener;
import rusketh.Milk.Event.Listeners.MilkServerListener;
import rusketh.Milk.Event.Listeners.MilkVehicleListener;
import rusketh.Milk.Event.Listeners.MilkWeatherListener;
import rusketh.Milk.Event.Listeners.MilkWorldListener;
import rusketh.Milk.Plugin.MilkPlugin;

public class MilkEventManager {
	
	private MilkBukkit milkBukkit;
	
	private HashMap<String, Hashtable<Method, String>> eventMap;
	
	private MilkPlayerListener playerListener;
	private MilkEntityListener entityListener;
	private MilkBlockListener blockListener;
	private MilkInventoryListener inventoryListener;
	private MilkWorldListener worldListener;
	private MilkVehicleListener vehicleListener;
	private MilkWeatherListener weatherListener;
	private MilkServerListener serverListener;
	
	public MilkEventManager(MilkBukkit milk)
	{
		milkBukkit = milk;
		
		eventMap = new HashMap<String, Hashtable<Method, String>>();
		
		CreateListeners();
		RegisterEvents();
	}
	
	private void Message(String message) {
		milkBukkit.Message(message);
	}
	
	private void CreateListeners()
	{
		playerListener = new MilkPlayerListener(milkBukkit);
		entityListener = new MilkEntityListener(milkBukkit);
		blockListener = new MilkBlockListener(milkBukkit);
		inventoryListener = new MilkInventoryListener(milkBukkit);
		worldListener = new MilkWorldListener(milkBukkit);
		vehicleListener = new MilkVehicleListener(milkBukkit);
		weatherListener = new MilkWeatherListener(milkBukkit);
		serverListener = new MilkServerListener(milkBukkit);
	}
	
	private void RegisterEvents() {
		for( Event.Type event : EnumSet.allOf(Event.Type.class) ) {
			try {
				if ( event.getCategory() == Event.Category.PLAYER ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, playerListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.ENTITY || event == Event.Type.ITEM_SPAWN ) { //Bukkit Thinks ITEM_SPAWN is word but it is not!
					milkBukkit.getServer().getPluginManager().registerEvent(event, entityListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.BLOCK ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, blockListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.INVENTORY ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, inventoryListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.WORLD ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, worldListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.VEHICLE ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, vehicleListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.WEATHER ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, weatherListener, Priority.Normal, milkBukkit);
				} else if ( event.getCategory() == Event.Category.SERVER ) {
					milkBukkit.getServer().getPluginManager().registerEvent(event, serverListener, Priority.Normal, milkBukkit);
				}
			} catch(IllegalArgumentException e) {
				//TODO this!
			} catch (Throwable e) {
				Message("Milk: Error '" + e + "'");
				Message("Registering event '" + event + "' in catagory '" + event.getCategory() + "'");
			}
		}
	}
	
	private void RegisterEvent(MilkPlugin plugin, String eventName, Method method) {
		Hashtable<Method, String> events = eventMap.get(eventName.toLowerCase());
		
		if ( events == null ) {
			events = new Hashtable<Method, String>();
			eventMap.put(eventName.toLowerCase(), events);
		}
		
		events.put(method, plugin.GetName().toLowerCase());
	}
	
	public void RegisterEvents(MilkPlugin plugin) {
		try {
			for ( Method method : plugin.getClass().getMethods() )
			{
				if ( !method.isAnnotationPresent(MilkEvent.class) ) {
					continue;
				}
				
				MilkEvent event = method.getAnnotation(MilkEvent.class);
				RegisterEvent(plugin, event.event(), method);
			} 
		} catch (Throwable e) {
			Message( "Milk Error: '" + e + "'.");
			Message( "When: Registering Events for '" + plugin.GetName() + ".");
		}
	}
	
	public void Call(String eventName, Object event) {
		Hashtable<Method, String> events = eventMap.get(eventName.toLowerCase());
		
		if ( events == null || events.isEmpty() ) {
			return;
		}
		
		@SuppressWarnings("rawtypes")
		Enumeration keys = events.keys();
		
        while ( keys.hasMoreElements() )
		{
        	Method method = (Method)keys.nextElement();
            MilkPlugin plugin = milkBukkit.GetPlugin(events.get(method));
            
            if ( plugin.Enabled() ) {
	            try {
	            	method.invoke(plugin, event);
	            } catch (Throwable e) {
	            	Message( "Milk Error: '" + e + "'.");
	            	Message( "When: Calling event '" + eventName + "' on plugin '" + plugin.GetName() + "'");
	    		}
            }
        }
	}
}