package me.timlampen.lcc;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class QuestHandler implements Listener{
	public Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] " + ChatColor.LIGHT_PURPLE + "Quests");
	public HashMap<String, String> currentquest = new HashMap<>();
	
}
