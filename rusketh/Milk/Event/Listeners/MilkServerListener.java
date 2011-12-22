package rusketh.Milk.Event.Listeners;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServerListener;

import rusketh.Milk.MilkBukkit;

public class MilkServerListener extends ServerListener {
	
	public MilkBukkit milkBukkit;
	
	public MilkServerListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}

    public void onPluginEnable(PluginEnableEvent event) {
    	milkBukkit.CallEvent("PLUGIN_ENABLE", event);
    }

    public void onPluginDisable(PluginDisableEvent event) {
    	milkBukkit.CallEvent("PLUGIN_DISABLE", event);
    }

    public void onServerCommand(ServerCommandEvent event) {
    	
    	String line = event.getCommand().toLowerCase(); //Remember the '/' is still there.
    	String cmd = line.split("[\\s]")[0];
		String[] args = line.substring( cmd.length() ).trim().split("[\\s]+");
		
		if ( milkBukkit.RunCommand(event.getSender(), cmd, args ) ) {
			return;
		}
    	
    	milkBukkit.CallEvent("SERVER_COMMAND", event);
    }
    
    public void onMapInitialize(MapInitializeEvent event) {
    	milkBukkit.CallEvent("MAP_INITIALIZE", event);
    }
    
    public void onServerListPing(ServerListPingEvent event) {
    	milkBukkit.CallEvent("SERVER_LIST_PING", event);
    }
}
