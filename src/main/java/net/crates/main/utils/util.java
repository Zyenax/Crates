package net.crates.main.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.crates.main.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class util implements Listener{
	
	private static main plugin;

	public util(main hub) {
		util.plugin = hub;
	}

	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static ItemStack createItem(Material material, int amount ,int dataValue, String name,
			List<String> list) {
		ItemStack selector = new ItemStack(material, amount, (short) dataValue);
		ItemMeta selectorMeta = selector.getItemMeta();
		selectorMeta.setDisplayName(name);
		if (list != null)
			selectorMeta.setLore(list);
		selector.setItemMeta(selectorMeta);
		return selector;
	}

	public static ItemStack createSkull(String pname, String name,
			List<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
		meta3.setOwner(pname);
		meta3.setDisplayName(name);
		meta3.setLore(lore);
		skull.setItemMeta(meta3);
		return skull;
	}
	
	public static void updateinventory(final Player p){
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				p.updateInventory();
			}
		}, 5L);
	}
	
	public static HashMap<Integer, Entity> holoGram = new HashMap<Integer, Entity>();
	public static void createHoloGram(Location location, String holoName, Integer holoID){
		if(!holoGram.containsKey(holoID)){
		final ArmorStand stand = (ArmorStand) location.getWorld().spawn(location.add(0.5, 0, 0.5), ArmorStand.class);
		stand.setCustomName(util.color(holoName));
		stand.setCustomNameVisible(true);
		stand.setVisible(false);
		stand.setGravity(false);
		holoGram.put(holoID, stand);
		}else{
			return;
		}
	}
	
	public static void removeHoloGram(Integer holoID){
		holoGram.get(holoID).remove();
	}
	public static void teleportHoloGram(Integer holoID, Location location){
		holoGram.get(holoID).teleport(location.add(0.5, 0, 0.5));
	}
	
	public static String getHoloGramName(Integer holoID){
		return holoGram.get(holoID).getCustomName();
	}
	
	public static Location getHoloGramLoc(Integer holoID){
		return holoGram.get(holoID).getLocation();
	}
	
	public static void renameHoloGram(String holoName, Integer holoID){
		holoGram.get(holoID).setCustomName(util.color(holoName));
		holoGram.get(holoID).setCustomNameVisible(true);
	}
	
}
