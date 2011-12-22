package rusketh.Milk.Plugin;


//import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import rusketh.Milk.MilkBukkit;


public abstract class MilkPlugin {
	
	private MilkBukkit milkBukkit;
	
	private boolean isEnabled = false;
	private boolean initialized = false;
	
	private YamlConfiguration configuration;
	private File configurationFile;
	
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
	
	public String OnFormat(String message) {
		return message;
	}
	
	public String OnFormat(String message,World world) {
		return message;
	}
	
	public String OnFormat(String message,Player player) {
		return message;
	}
	
	protected void Message(String message) {
		milkBukkit.Message(message);
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
	
	protected final void CreateConfig(File configFile) {
		configuration = new YamlConfiguration();
		configurationFile = configFile;
		LoadConfig();
	}
	
	public final YamlConfiguration GetConfig() {
		return configuration;
	}
	
	public final void LoadConfig() {
		if ( configurationFile != null ) {
			try {
				configuration.load(configurationFile);
			} catch (FileNotFoundException e) {
				Message("Milk: Error '" + e + "'.");
				Message("When: Loading '" + configurationFile.getPath() + "' for plugin '" + this.GetName() + "'.");
			} catch (IOException e) {
				Message("Milk: Error '" + e + "'.");
				Message("When: Loading '" + configurationFile.getPath() + "' for plugin '" + this.GetName() + "'.");
			} catch (InvalidConfigurationException e) {
				Message("Milk: Error '" + e + "'.");
				Message("When: Loading '" + configurationFile.getPath() + "' for plugin '" + this.GetName() + "'.");
			}
		}
	}
	
	public final void SaveConfig() {
		if ( configuration != null ) {
			try {
				configuration.save(configurationFile);
			} catch (IOException e) {
				Message("Milk: Error '" + e + "'.");
				Message("When: Saveing '" + configurationFile.getPath() + "' for plugin '" + this.GetName() + "'.");
			}
		}
	}
	
	//TODO add a subclass event and command register
	
}
