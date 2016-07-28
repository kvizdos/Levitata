package com.Kento.Levitata;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public final class LevitataMethods {
	
 HashMap(Player, String) playerArenaInfo = new HashMap(Player, String);
	
	public static void getPlayerArena(Player p) {
		
	}
			
	public static void addPlayer(Player p) {
		Main.playersInGame.add(p);
	}
	
	public static void removePlayer(Player p) {
		Main.playersInGame.remove(p.getName());
	}
	
	public static void getPlayers() {
		Main.playersInGame.toString();
	}
	
	public static void startGame() {
		Main.gameStarted = true;
		for(Player p : Main.playersInGame) {
			p.sendMessage(ChatColor.RED + "Starting Game!");
		}
		
	}
	
	public static void endGame() {
		for(Player p : Main.playersInGame) {
			p.sendMessage(ChatColor.RED + "Thanks For Playing!");
		}
		Main.gameStarted = false;
		Main.playersInGame.clear();	
	}
	
	public static void clearPlayers() {
		Main.playersInGame.clear();
	}
}
