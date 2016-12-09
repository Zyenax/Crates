package net.crates.main.crateHandler;

import java.util.HashMap;

import net.crates.main.main;
import net.crates.main.utils.packets;
import net.crates.main.utils.util;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class crates implements Listener{
	
	private main plugin;
	public crates(main hub) {
		this.plugin = hub;
	}
	
	public static HashMap<String, BukkitTask> Bullets = new HashMap<String, BukkitTask>();
	public static HashMap<String, String> crateFired = new HashMap<String, String>();
	@EventHandler
	public void crateHandler(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getHand() == EquipmentSlot.HAND){
			if (e.getClickedBlock() != null) {
				if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME){
						if(!crateFired.containsKey("inUse")){
							if(!util.holoGram.containsKey(1)){
								util.createHoloGram(e.getClickedBlock().getLocation(), "&eCLICK ONCE MORE TO MAKE THIS A VALID CRATE", 1);
							}else{
								util.renameHoloGram("&eCALCULATING", 1);
								util.teleportHoloGram(1, e.getClickedBlock().getLocation());
					crateFired.put("inUse", "true");
					final ShulkerBullet bullet = (ShulkerBullet) e.getClickedBlock().getWorld().spawn(e.getClickedBlock().getLocation().add(0.5, 1.5, 0.5), ShulkerBullet.class);
					bullet.setGlowing(true);
					e.getPlayer().playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_SHULKER_OPEN, Integer.MAX_VALUE, Integer.MAX_VALUE);
					BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
					Bullets.put("starter", scheduler.runTaskTimer(plugin, new Runnable() {
								public void run() {
									bullet.setVelocity(new Vector(0,0.04,0));
								}
					}, 0, 1L));
					Bullets.put("particles", scheduler.runTaskTimer(plugin, new Runnable() {
						public void run() {
							bullet.getWorld().playEffect(bullet.getLocation().add(0, 0.2, 0), Effect.WITCH_MAGIC, Integer.MAX_VALUE);
						}
			}, 0, 1L));
					Bullets.put("name", scheduler.runTaskTimer(plugin, new Runnable() {
						public void run() {
							if(util.getHoloGramName(1).equals(util.color("&eCALCULATING"))){
								util.renameHoloGram(util.color("&aCALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&aCALCULATING"))){
								util.renameHoloGram(util.color("&bCALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&bCALCULATING"))){
								util.renameHoloGram(util.color("&cCALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&cCALCULATING"))){
								util.renameHoloGram(util.color("&dCALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&dCALCULATING"))){
								util.renameHoloGram(util.color("&5CALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&5CALCULATING"))){
								util.renameHoloGram(util.color("&6CALCULATING"), 1);
							}else if(util.getHoloGramName(1).equals(util.color("&6CALCULATING"))){
								util.renameHoloGram(util.color("&eCALCULATING"), 1);
							}
						}
			}, 0, 2L));
					scheduler.runTaskLater(plugin, new Runnable() {
						public void run() {
							Bullets.get("starter").cancel();
							Bullets.get("particles").cancel();
							Bullets.get("name").cancel();
							util.renameHoloGram("&bReward Summoned", 1);
							FireworkEffect fEffect = FireworkEffect
									.builder()
									.trail(true)
									.flicker(true)
									.withColor(
											new Color[] { Color.AQUA,
													Color.YELLOW,
													Color.ORANGE })
									.with(FireworkEffect.Type.BURST)
									.build();
							packets.playFirework(e.getClickedBlock().getLocation().add(0.5, 0.5, 0.5), fEffect, 1);
							bullet.getWorld().playEffect(bullet.getLocation().add(0, 0.2, 0), Effect.CLOUD, Integer.MAX_VALUE);
						}
			}, 5 * 20);
					scheduler.runTaskLater(plugin, new Runnable() {
						public void run() {
							bullet.remove();
							util.renameHoloGram("&cClick to open an orblet", 1);
							crateFired.remove("inUse");
							e.getPlayer().playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ITEM_PICKUP, Integer.MAX_VALUE, Integer.MAX_VALUE);
							e.getPlayer().sendMessage(util.color("&eMay click crate again!"));
						}
			}, 10 * 20);
							}
				}else{
					e.getPlayer().playSound(e.getClickedBlock().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, Integer.MAX_VALUE, Integer.MAX_VALUE);
					e.getPlayer().sendMessage(util.color("&cCrate is already in use!"));
				}
				}
		}
		}
		}
	}

}
