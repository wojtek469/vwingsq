package pl.vojteq.wings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin {
	
	private static Main instance;

    public void onEnable() {
    	instance = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WingListener(), this);
		new WingCMD(this);
    	getLogger().warning("[VWingsQ] Loaded.");
    	
    }

    public void onDisable() {
    	getLogger().warning("[VWingsQ] Disabled.");
    }
    
	public static Main getInst() {
		return instance;
	}
}