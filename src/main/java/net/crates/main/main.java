package net.crates.main;

import net.crates.main.crateHandler.crates;
import net.crates.main.utils.packets;
import net.crates.main.utils.util;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
	
	public void onEnable(){
		loadClasses();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(util.color("&e[&cCore&e] &aHas been enabled!"));
	}
	
	public void onDisable(){
		util.removeHoloGram(1);
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(util.color("&e[&cCore&e] &cHas been disabled!"));
	}
	
	public void loadClasses(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new util(this), this);
		pm.registerEvents(new packets(this), this);
		pm.registerEvents(new crates(this), this);
	}
}
