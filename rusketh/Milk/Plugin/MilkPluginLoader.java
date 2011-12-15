package rusketh.Milk.Plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import rusketh.Milk.MilkBukkit;

public class MilkPluginLoader {
	
	private MilkBukkit milkBukkit;
	private MilkPluginManager pluginManager;
	
	private File pluginFolder;
	private File configFolder;
	
	private ClassLoader classLoader;
	
	public MilkPluginLoader(MilkBukkit Milk, MilkPluginManager manager) {
		milkBukkit = Milk;
		pluginManager = manager;
		
		pluginFolder = new File("Milk");
		configFolder = new File("Milk/Config");
		
		classLoader = MilkPlugin.class.getClassLoader();
	}
	
	private void Message(String message) {
		milkBukkit.Message(message);
	}
	
	public void LoadPlugins() {
		
		if ( !pluginFolder.exists() ) {
			pluginFolder.mkdir();
		}
		
		if ( !configFolder.exists() ) {
			configFolder.mkdir();
		}
		
		for (File pluginFile : pluginFolder.listFiles() ) {
			
			if ( pluginFile.isDirectory() ) {
				continue;
			}
			
			String fileName = pluginFile.getName();
			
			if ( fileName.endsWith(".jar") ) {
				
				fileName = fileName.substring(0, fileName.length() - 4);
				File configFolder = new File("Milk/Config/" + fileName);
				
				if ( !configFolder.exists() ) {
					configFolder.mkdir();
				}
				
				LoadFile(pluginFile, configFolder);
			}
		}
	}
	
	private void LoadFile(File pluginFile, File configFolder) {
		try {
			JarFile jarFile = new JarFile(pluginFile);
			
			Enumeration<JarEntry> entries = jarFile.entries();
			
			while ( entries.hasMoreElements() ) {
				
				JarEntry entry = entries.nextElement();
				String entryName = entry.getName();
				
				if ( entryName.startsWith("Milk_") && entryName.endsWith(".class") ) {
					
					File configFile = FindConfig(entry, jarFile, pluginFile, configFolder);
					if ( configFile != null ) {
						LoadClass(entry, jarFile, pluginFile, configFile);
					}
				}
			}
			
		} catch (IOException e) {
			Message("Milk: Error '" + e + "'");
			Message("When: loading plugin file '" + pluginFile.getName() + "'.");
		}
	}
	
	public void LoadClass(JarEntry entry, JarFile jarFile, File pluginFile, File configFile) {
		try { //Based on Bukkits plugin loader!
			URL[] urls = new URL[1];
			urls[0] = pluginFile.toURI().toURL();
			URLClassLoader urlLoader = new URLClassLoader(urls, classLoader);
			Message("Milk: Debug '" + urls[0] + "'");
			Class<?> jarClass = Class.forName(entry.getName(), true, urlLoader);
			Class<? extends MilkPlugin> pluginClass = jarClass.asSubclass(MilkPlugin.class);
			
			Constructor<? extends MilkPlugin> constructor = pluginClass.getConstructor();
			MilkPlugin plugin = constructor.newInstance();
			
			pluginManager.RegisterPlugin(plugin);
			plugin.CreateConig(configFile);
			
		} catch (MalformedURLException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (ClassNotFoundException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (NoSuchMethodException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (SecurityException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (InstantiationException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (IllegalAccessException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (IllegalArgumentException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		} catch (InvocationTargetException e) {
			Message("Milk: Error '" + e + "'.");
			Message("When: trying to load '" + entry.getName() + "' in '" + pluginFile.getName() + "'");
		}
	}
	
	private File FindConfig(JarEntry entry, JarFile jarFile, File pluginFile, File configFolder) {
		String configName = entry.getName();
		configName = configName.substring(5, configName.length() - 6) + ".yml";
		
		File configFile = new File(configFolder.getPath() + "/" + configName);
		
		try {
			if ( !configFile.exists() ) {
				configFile.createNewFile();
			}
			
			ZipEntry configEntry = jarFile.getEntry(configName);
			if ( configEntry == null ) {
				return configFile;
			}
			
			InputStream inStream = jarFile.getInputStream(configEntry);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(configFile));
			
			while ( inStream.available() != 0 ) {
				outputStream.write(inStream.read());
			}
			
			outputStream.close();
			
			return configFile;
			
		} catch (IOException e) {
			Message("Milk: Error '" + e + "'");
			Message("When: Loading/Creating '" + configFile.getPath() + "'");
		}
		
		return null;
	}
}
