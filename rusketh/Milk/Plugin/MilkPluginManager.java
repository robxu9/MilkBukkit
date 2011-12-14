package rusketh.Milk.Plugin;

import java.util.Hashtable;

import rusketh.Milk.MilkBukkit;
import rusketh.Milk.Command.MilkCommandManager;
import rusketh.Milk.Event.MilkEventManager;

public class MilkPluginManager {
	
	private MilkBukkit milkBukkit;
	
	private MilkEventManager eventManager;
	private MilkCommandManager commandManager;
	private MilkPluginLoader pluginLoader;
	
	private Hashtable<String, MilkPlugin> plugins;
	
	public MilkPluginManager(MilkBukkit Milk, MilkEventManager events, MilkCommandManager commands) {
		milkBukkit = Milk;
		
		eventManager = events;
		commandManager = commands;
		
		pluginLoader = new MilkPluginLoader(milkBukkit, this);
		plugins = new Hashtable<String, MilkPlugin>();
		
		pluginLoader.LoadPlugins();
	}
	
	private void Message(String message) {
		milkBukkit.Message(message);
	}
	
	public void RegisterPlugin(MilkPlugin plugin) {
		
		plugin.Init(milkBukkit);
		
		String name = plugin.GetName();
		String version = plugin.GetVer();
		String author = plugin.GetAuthor();
		
		if ( name == null || name.equals("") ) {
			Message("Milk Error: Attempted to register unnamed plugin.");
			Message("When: Registering plugins.");
			return;
		}
		
		if ( name.split(".").length != 0 ) {
			Message("Milk Error: Invalid plugin name '" + name + "' name must not contain '.' (period).");
			Message("When: Registering plugins.");
		}
		
		if ( author == null || author == "" ) {
			author = "Unkown Author";
		}
		
		if ( version == null || version == "" ) {
			version = "Unkown Version";
		}
		
		MilkPlugin conflictor = GetPlugin(name);
		
		if ( conflictor != null ) {
			Message("Milk Error: Conflicting plugin found.");
			Message("When: Registering plugin '" + name + "' : '" + version + "' by '" + author + "'");
			return;
		}
		
		plugins.put(name.toLowerCase(), plugin);
		
		eventManager.RegisterEvents(plugin);
		commandManager.RegisterCommands(plugin);
		
		Message("Milk: Registered plugin '" + name + "' : '" + version + "' by '" + author + "'");
	}
	
	public MilkPlugin GetPlugin(String name) {
		return plugins.get(name.toLowerCase());
	}
	
	public void EnablePlugins() {
		for ( MilkPlugin plugin : plugins.values() ) {
			plugin.SetEnabled(true);
		}
	}
	
	public void DisablePlugins() {
		for ( MilkPlugin plugin : plugins.values() ) {
			plugin.SetEnabled(false);
		}
	}
	
	//TODO new plugin loader (Jars and Zips)
	
	//TODO config loader (separate class?)
	
	/* The following is complete shit
	It is from the prototype but needs completely redoing
	i am keeping it here in case i need it later
	
	public File GetPluginFolder() {
		File pluginFolder = new File("Milk");
		
		if ( !pluginFolder.exists() ) {
			pluginFolder.mkdir();
		}
		
		return pluginFolder;
	}
	
	public File GetConfigFolder() {
		File configFolder = new File("Milk/Config");
		
		if ( !configFolder.exists() ) {
			configFolder.mkdir();
		}
		
		return configFolder;
	}
	
	public void FindPlugins() {
		File pluginFolder = GetPluginFolder();
		//File configFolder = GetConfigFolder();
		ClassLoader pluginLoader = (MilkPlugin.class).getClassLoader();
		
		Method method;
		try {
			method = (java.net.URLClassLoader.class).getDeclaredMethod("addURL", new Class[] {java.net.URL.class});
			method.setAccessible(true);
	        
			for ( File pluginFile : pluginFolder.listFiles() ) {
				if ( !pluginFile.isFile() ) {
					continue;
				}
				
				if ( pluginFile.getName().endsWith(".jar") || pluginFile.getName().endsWith(".zip") ) {
					String pluginFileName = pluginFile.getName().split("\\.")[0];
					Msg("Loading MilkPlugin: '" + pluginFileName + "'");
					
					if ( pluginLoader instanceof URLClassLoader ) {
						method.invoke(pluginLoader, new Object[] {pluginFile.toURI().toURL()});
					}
					
					try {
						FileInputStream pluginStream = new FileInputStream(pluginFile);
						ZipInputStream zipStream = new ZipInputStream(pluginStream);
						
						while ( true ) {
							ZipEntry entry = zipStream.getNextEntry();
							
							if ( entry == null ) {
								break;
							}
							
							String className = entry.getName();
							if ( className.startsWith("milk_") && className.endsWith(".class") ) {
								className = className.split("\\.")[0];
								Class<?> pluginClass = pluginLoader.loadClass(className);
								if ( !MilkPlugin.class.isAssignableFrom(pluginClass) ) {
									Msg("Milk Error: '" + entry.getName() + "' does not extend 'MilkPlugin'.");
									Msg("When: Loading '" + pluginFile.getPath() + "'");
								} else {
									MilkPlugin plugin = (MilkPlugin)pluginClass.newInstance();
									RegisterPlugin(plugin);
								}
							}
						}
						
						pluginStream.close();
						
					} catch (FileNotFoundException e) {
						Msg("Milk Error: File '" + pluginFile.getPath() + "' not found");
						Msg("When: Trying to load the plugin file.");
					} catch (IOException e) {
						Msg("Milk Error: '" + e + "'");
						Msg("When: Loading '" + pluginFile.getPath() + "'");
					} catch (ClassNotFoundException e) {
						Msg("Milk Error: '" + e + "'");
						Msg("When: Loading '" + pluginFile.getPath() + "'");
					} catch (InstantiationException e) {
						Msg("Milk Error: '" + e + "'");
						Msg("When: Loading '" + pluginFile.getPath() + "'");
					} catch (IllegalAccessException e) {
						Msg("Milk Error: '" + e + "'");
						Msg("When: Loading '" + pluginFile.getPath() + "'");
					}
		            	
					
				}
			}
		} catch (NoSuchMethodException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		} catch (SecurityException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		} catch (IllegalAccessException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		} catch (IllegalArgumentException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		} catch (InvocationTargetException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		} catch (MalformedURLException e) {
			Msg("Milk Error: '" + e + "'");
			Msg("When: Loading plugins.");
		}
	}
	*/
}
