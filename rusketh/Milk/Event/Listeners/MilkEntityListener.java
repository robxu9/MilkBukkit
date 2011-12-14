package rusketh.Milk.Event.Listeners;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.event.painting.PaintingBreakEvent;

import rusketh.Milk.MilkBukkit;



public class MilkEntityListener extends EntityListener {
	
public MilkBukkit milkBukkit;
	
	public MilkEntityListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}
	
    public void onCreatureSpawn(CreatureSpawnEvent event) {
    	milkBukkit.CallEvent("CREATURE_SPAWN", event);
    }

    public void onItemSpawn(ItemSpawnEvent event) {
    	milkBukkit.CallEvent("ITEM_SPAWN", event);
    }

    public void onEntityCombust(EntityCombustEvent event) {
    	milkBukkit.CallEvent("ENTITY_COMBUST", event);
    }

    public void onEntityDamage(EntityDamageEvent event) {
    	milkBukkit.CallEvent("ENTITY_DAMAGE", event);
    }

    public void onEntityExplode(EntityExplodeEvent event) {
    	milkBukkit.CallEvent("ENTITY_EXPLODE", event);
    }

    public void onExplosionPrime(ExplosionPrimeEvent event) {
    	milkBukkit.CallEvent("EXPLOSION_PRIME", event);
    }

    public void onEntityDeath(EntityDeathEvent event) {
    	milkBukkit.CallEvent("ENTITY_DEATH", event);
    }

    public void onEntityTarget(EntityTargetEvent event) {
    	milkBukkit.CallEvent("ENTITY_TARGET", event);
    }

    public void onEntityInteract(EntityInteractEvent event) {
    	milkBukkit.CallEvent("ENTITY_INTERACT", event);
    }

    public void onEntityPortalEnter(EntityPortalEnterEvent event) {
    	milkBukkit.CallEvent("ENTITY_PORTAL_ENTER", event);
    }

    public void onPaintingPlace(PaintingPlaceEvent event) {
    	milkBukkit.CallEvent("PAINTING_PLACE", event);
    }

    public void onPaintingBreak(PaintingBreakEvent event) {
    	milkBukkit.CallEvent("PAINTING_BREAK", event);
    }

    public void onPigZap(PigZapEvent event) {
    	milkBukkit.CallEvent("PIG_ZAP", event);
    }

    public void onCreeperPower(CreeperPowerEvent event) {
    	milkBukkit.CallEvent("CREEPER_POWER", event);
    }

    public void onEntityTame(EntityTameEvent event) {
    	milkBukkit.CallEvent("ENTITY_TAME", event);
    }

    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
    	milkBukkit.CallEvent("ENTITY_REGAIN_HEALTH", event);
    }

    public void onProjectileHit(ProjectileHitEvent event) {
    	milkBukkit.CallEvent("PROJECTILE_HIT", event);
    }

    public void onEndermanPickup(EndermanPickupEvent event) {
    	milkBukkit.CallEvent("ENDERMAN_PICKUP", event);
    }

    public void onEndermanPlace(EndermanPlaceEvent event) {
    	milkBukkit.CallEvent("ENDERMAN_PLACE", event);
    }

    public void onFoodLevelChange(FoodLevelChangeEvent event) {
    	milkBukkit.CallEvent("FOOD_LEVEL_CHANGE", event);
    }

    public void onSlimeSplit(SlimeSplitEvent event) {
    	milkBukkit.CallEvent("SLIME_SPLIT", event);
    }
}