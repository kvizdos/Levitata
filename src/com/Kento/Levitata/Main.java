package com.Kento.Levitata;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Main extends JavaPlugin implements Listener{


	static List<Player> playersInGame = new ArrayList<Player>();
	public static boolean gameStarted = false;
	private BukkitTask task;
	private int count = getConfig().getInt("Maps");

	
	@Override
	public void onEnable() {
		LevitataMethods.clearPlayers();
		
		getCommand("la").setExecutor(new LAExecutor(this));
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		
			 if (task == null) {
		            task = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
		                @Override
		                public void run() {
		                        for (Player p : playersInGame) {
		                        	if(p.getLocation().getBlockY() == getConfig().getString("Maps." )) {
		                        		
		                        	}
		                    }
		                }
		            }, 20, 20);
		        }		
			 }
	

	
	@Override
	public void onDisable() {
		LevitataMethods.clearPlayers();
		task.cancel();
	}

	
}
