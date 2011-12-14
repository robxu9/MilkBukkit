package rusketh.Milk.Event.Listeners;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;

import rusketh.Milk.MilkBukkit;

public class MilkBlockListener extends BlockListener {

	public MilkBukkit milkBukkit;
	
	public MilkBlockListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}
	
    public void onBlockDamage(BlockDamageEvent event) {
    	milkBukkit.CallEvent("BLOCK_DAMAGE", event);
    }

    public void onBlockCanBuild(BlockCanBuildEvent event) {
    	milkBukkit.CallEvent("BLOCK_CANBUILD", event);
    }

    public void onBlockFromTo(BlockFromToEvent event) {
    	milkBukkit.CallEvent("BLOCK_FROMTO", event);
    }

    public void onBlockIgnite(BlockIgniteEvent event) {
    	milkBukkit.CallEvent("BLOCK_IGNITE", event);
    }

    public void onBlockPhysics(BlockPhysicsEvent event) {
    	milkBukkit.CallEvent("BLOCK_PHYSICS", event);
    }

    public void onBlockPlace(BlockPlaceEvent event) {
    	milkBukkit.CallEvent("BLOCK_PLACE", event);
    }

    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
    	milkBukkit.CallEvent("REDSTONE_CHANGE ", event);
    }

    public void onLeavesDecay(LeavesDecayEvent event) {
    	milkBukkit.CallEvent("LEAVES_DECAY ", event);
    }

    public void onSignChange(SignChangeEvent event) {
    	milkBukkit.CallEvent("SIGN_CHANGE ", event);
    }

    public void onBlockBurn(BlockBurnEvent event) {
    	milkBukkit.CallEvent("BLOCK_BURN ", event);
    }

    public void onBlockBreak(BlockBreakEvent event) {
    	milkBukkit.CallEvent("BLOCK_BREAK ", event);
    }

    public void onBlockForm(BlockFormEvent event) {
    	milkBukkit.CallEvent("BLOCK_FORM ", event);
    }

    public void onBlockSpread(BlockSpreadEvent event) {
    	milkBukkit.CallEvent("BLOCK_SPREAD ", event);
    }

    public void onBlockFade(BlockFadeEvent event) {
    	milkBukkit.CallEvent("BLOCK_FADE ", event);
    }

    public void onBlockDispense(BlockDispenseEvent event) {
    	milkBukkit.CallEvent("BLOCK_DISPENSE ", event);
    }

    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
    	milkBukkit.CallEvent("BLOCK_PISTON_RETRACT ", event);
    }

    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
    	milkBukkit.CallEvent("BLOCK_PISTON_EXTEND ", event);
    }
}
