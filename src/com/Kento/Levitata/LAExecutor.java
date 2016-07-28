package com.Kento.Levitata;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class LAExecutor implements CommandExecutor {
	
	private final Main PLUGIN;

	public LAExecutor(Main plugin) {
		this.PLUGIN = plugin;
	}
	
	// LevitataMethods LM;

	
	String prefix = ChatColor.GREEN + "[Levitata] " + ChatColor.AQUA + "";



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;

		
		
		if(cmd.getName().equalsIgnoreCase("la")) {
			
			if(args.length == 0) {
				p.sendMessage(prefix + "Do /la help for help!");
				return true;
			} else if(args[0].equalsIgnoreCase("help")) {
				p.sendMessage(prefix + "Levitata Help");
				p.sendMessage(ChatColor.AQUA + "/la create <name>");
				return true;
			} else if(args[0].equalsIgnoreCase("create")) {
				if(args.length == 1) {
					p.sendMessage(prefix + "Do /la create <name>");
					return true;
				} else if(!PLUGIN.getConfig().contains("Maps." + args[1])){
					p.sendMessage(prefix + "Successfully created arena: " + args[1]);
					PLUGIN.getConfig().set("Maps." + args[1] + ".Created", "yes");

					PLUGIN.saveConfig();
					return true;
				} else {
					p.sendMessage(prefix + "There is already an arena by that name!");
					return true;
				}
			}
			// SET HUB
			else if(args[0].equalsIgnoreCase("sethub")) {
					p.sendMessage(prefix + "Set the Hub!");
					
					PLUGIN.getConfig().set("Settings.Hub.X", p.getLocation().getBlockX());
					PLUGIN.getConfig().set("Settings.Hub.Y", p.getLocation().getBlockY());
					PLUGIN.getConfig().set("Settings.Hub.Z", p.getLocation().getBlockZ());

					PLUGIN.saveConfig();
					return true;
			}
			// SET SPAWN
			else if(args[0].equalsIgnoreCase("setspawn")) {
				if(args.length == 1) {
					p.sendMessage(prefix + "Please state an Arena!");
					return true;

				} else if(PLUGIN.getConfig().contains("Maps." + args[1])){
					p.sendMessage(prefix + "Set spawn for " + args[1]);
					
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Spawn.X", p.getLocation().getBlockX());
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Spawn.Y", p.getLocation().getBlockY());
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Spawn.Z", p.getLocation().getBlockZ());
					PLUGIN.saveConfig();
					return true;
				} else {
					p.sendMessage(prefix + "That is not a valid Arena!");
					return true;
				}
			}
			// SET LOBBY
			else if(args[0].equalsIgnoreCase("setlobby")) {
				if(args.length == 1) {
					p.sendMessage(prefix + "Please state an Arena!");
					return true;

				} else if(PLUGIN.getConfig().contains("Maps." + args[1])){
					p.sendMessage(prefix + "Set lobby for " + args[1]);
					
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Lobby.X", p.getLocation().getBlockX());
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Lobby.Y", p.getLocation().getBlockY());
					PLUGIN.getConfig().set("Maps." + args[1] + ".Locations.Lobby.Z", p.getLocation().getBlockZ());
					PLUGIN.saveConfig();
					return true;
				} else {
					p.sendMessage(prefix + "That is not a valid Arena!");
					return true;
				}
			}
			// SET LAYER
			else if(args[0].equalsIgnoreCase("setlayer")) {
				if(args.length == 1 || args.length == 2) {
					p.sendMessage(prefix + "Please state an Arena and layer number!");
					return true;

				} else if(PLUGIN.getConfig().contains("Maps." + args[1])){
					p.sendMessage(prefix + "Added " +args[1]+" (Layer " + args[2] + ")");
					
					PLUGIN.getConfig().set("Maps." + args[1] + ".Layers."+args[2], p.getLocation().getBlockY());
					PLUGIN.saveConfig();
					return true;
				} else {
					p.sendMessage(prefix + "That is not a valid Arena!");
					return true;
				}
			}
			// JOIN
			else if(args[0].equalsIgnoreCase("join")) {
				if(args.length == 1) {
					p.sendMessage(prefix + "Please do /at join <arena id>");
					return true;
				} else if(PLUGIN.getConfig().contains("Maps." + args[1])) {
					if(Main.playersInGame.contains(p)) {
						p.sendMessage(prefix + "You Are Already In A Game! Do /la leave to leave!");
						return true;
					} else {  
						    World w = Bukkit.getWorld("world");
						    double x = PLUGIN.getConfig().getDouble("Maps." + args[1] + ".Locations.Lobby.X");
					        double y = PLUGIN.getConfig().getDouble("Maps." + args[1] + ".Locations.Lobby.Y");
					        double z = PLUGIN.getConfig().getDouble("Maps." + args[1] + ".Locations.Lobby.Z");
					             
					        Location l = new Location(w, x, y, z);
                        p.teleport(l);
						p.sendMessage(prefix + "Joining map " + args[1]);
						LevitataMethods.addPlayer(p);
						return true;
					}
				} else {
					p.sendMessage(prefix + "Please specify a valid arena! (/la list)");
					return true;
				}
			}
			// LEAVE
			else if(args[0].equalsIgnoreCase("leave")) {
				
				LevitataMethods.removePlayer(p);
				
			    World w = Bukkit.getWorld("world");
			    double x = PLUGIN.getConfig().getDouble("Settings.Hub.X");
			    double y = PLUGIN.getConfig().getDouble("Settings.Hub.Y");
			    double z = PLUGIN.getConfig().getDouble("Settings.Hub.Z");
		             
		        Location l = new Location(w, x, y, z);
		        p.teleport(l);
		        
		        p.sendMessage(prefix + "You are being sent back to the hub!");
				
			}
			
		}
		
		return false;
	}

}
