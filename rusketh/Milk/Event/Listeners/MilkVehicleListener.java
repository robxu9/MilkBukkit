package rusketh.Milk.Event.Listeners;

import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;

import rusketh.Milk.MilkBukkit;

public class MilkVehicleListener extends VehicleListener {
    
	public MilkBukkit milkBukkit;
	
	public MilkVehicleListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}

    public void onVehicleCreate(VehicleCreateEvent event) {
    	milkBukkit.CallEvent("VEHICLE_CREATE", event);
    }

    public void onVehicleDamage(VehicleDamageEvent event) {
    	milkBukkit.CallEvent("VEHICLE_DAMAGE", event);
    }
 
    public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {
    	milkBukkit.CallEvent("VEHICLE_COLLISION_BLOCK", event);
    }

    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
    	milkBukkit.CallEvent("VEHICLE_COLLISION_ENTITY", event);
    }

    public void onVehicleEnter(VehicleEnterEvent event) {
    	milkBukkit.CallEvent("VEHICLE_ENTER", event);
    }

    public void onVehicleExit(VehicleExitEvent event) {
    	milkBukkit.CallEvent("VEHICLE_EXIT", event);
    }

    public void onVehicleMove(VehicleMoveEvent event) {
    	milkBukkit.CallEvent("VEHICLE_MOVE", event);
    }

    public void onVehicleDestroy(VehicleDestroyEvent event) {
    	milkBukkit.CallEvent("VEHICLE_DESTROY", event);
    }

    public void onVehicleUpdate(VehicleUpdateEvent event) {
    	milkBukkit.CallEvent("VEHICLE_UPDATE", event);
    }
}
