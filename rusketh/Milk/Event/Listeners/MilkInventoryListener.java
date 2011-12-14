package rusketh.Milk.Event.Listeners;

import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryListener;

import rusketh.Milk.MilkBukkit;

public class MilkInventoryListener extends InventoryListener {
	
public MilkBukkit milkBukkit;
	
	public MilkInventoryListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}
	
	public void onFurnaceSmelt(FurnaceSmeltEvent event) {
		milkBukkit.CallEvent("FURNACE_SMELT", event);
	}

    public void onFurnaceBurn(FurnaceBurnEvent event) {
    	milkBukkit.CallEvent("FURNACE_BURN", event);
    }
}