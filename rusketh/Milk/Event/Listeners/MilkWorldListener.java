package rusketh.Milk.Event.Listeners;

import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import rusketh.Milk.MilkBukkit;

public class MilkWorldListener extends WorldListener {
	
	public MilkBukkit milkBukkit;
	
	public MilkWorldListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}

    public void onChunkLoad(ChunkLoadEvent event) {
    	milkBukkit.CallEvent("CHUNK_LOAD", event);
    }

    public void onChunkPopulate(ChunkPopulateEvent event) {
    	milkBukkit.CallEvent("CHUNK_POPULATED", event);
    }

    public void onChunkUnload(ChunkUnloadEvent event) {
    	milkBukkit.CallEvent("CHUNK_UNLOAD", event);
    }

    public void onSpawnChange(SpawnChangeEvent event) {
    	milkBukkit.CallEvent("SPAWN_CHANGE", event);
    }

    public void onPortalCreate(PortalCreateEvent event) {
    	milkBukkit.CallEvent("PORTAL_CREATE", event);
    }

    public void onWorldSave(WorldSaveEvent event) {
    	milkBukkit.CallEvent("WORLD_SAVE", event);
    }

    public void onWorldInit(WorldInitEvent event) {
    	milkBukkit.CallEvent("WORLD_INIT", event);
    }

    public void onWorldLoad(WorldLoadEvent event) {
    	milkBukkit.CallEvent("WORLD_LOAD", event);
    }

    public void onWorldUnload(WorldUnloadEvent event) {
    	milkBukkit.CallEvent("WORLD_UNLOAD", event);
    }
}
