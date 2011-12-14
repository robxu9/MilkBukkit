package rusketh.Milk.Plugin;


//import java.util.HashMap;

import java.io.File;
import org.bukkit.Server;
import org.bukkit.util.config.Configuration;

import rusketh.Milk.MilkBukkit;


@SuppressWarnings("deprecation")
public abstract class MilkPlugin {
	
	private MilkBukkit milkBukkit;
	
	private boolean isEnabled = false;
	private boolean initialized = false;
	
	private Configuration configuration;
	
	//private HashMap<String, Object> classes;
	
	public abstract String GetName();
	
	public String GetAuthor() {
		return null;
	}
	
	public String GetVer() {
		return null;
	}
	
	public boolean Enabled() {
		return isEnabled;
	}
	
	public void Enable() {
	}
	
	public void Disable() {
	}
	
	protected void SetEnabled(final Boolean value) {
		if ( value && !isEnabled ) {
			isEnabled = true;
			Enable();
		} else if ( !value && isEnabled ) {
			isEnabled = false;
			Disable();
		}
	}
	
	protected MilkBukkit GetMilk() {
		return milkBukkit;
	}
	
	protected Server GetServer() {
		return milkBukkit.getServer();
	}
	
	protected final void Init(MilkBukkit Milk) {
		if ( !initialized ) {
			milkBukkit = Milk;
			initialized = true;
			//classes = new HashMap<String, Object>();
		}
	}
	
	protected final void CreateConig(File configFile) {
		configuration = new Configuration(configFile);
	}
	
	public final Configuration GetConfig() {
		return configuration;
	}
	
	public final void LoadConfig() {
		if ( configuration != null ) {
			configuration.load();
		}
	}
	
	public final void SaveConfig() {
		if ( configuration != null ) {
			configuration.save();
		}
	}
	
	//TODO add a subclass event and command register
	
}
