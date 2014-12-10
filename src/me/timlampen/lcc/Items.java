package me.timlampen.lcc;

import java.util.ArrayList;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	public enum Quest {
		quest1on, quest1off, quest1done
	}

	public ItemStack getCustomItem(Quest q) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> quest1on = new ArrayList<String>();
		ArrayList<String> quest1off = new ArrayList<String>();
		ArrayList<String> quest1done = new ArrayList<String>();
		switch (q) {
		case quest1on:
			is = new ItemStack(Material.WOOL, 1, (short)5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Fight For Me!");
			quest1on.add(ChatColor.LIGHT_PURPLE + "Kill 25 mobs to complete this task");
			quest1on.add(ChatColor.LIGHT_PURPLE + " ");
			quest1on.add(ChatColor.YELLOW + "Rewards:");
			quest1on.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1on.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1on);
			is.setItemMeta(im);
			break;
		case quest1off:
			is = new ItemStack(Material.WOOL, 1, (short)14);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED+ "" + ChatColor.BOLD + "Fight For Me!");
			quest1off.add(ChatColor.RED + "Kill 25 mobs to complete this task");
			quest1off.add(ChatColor.RED + " ");
			quest1off.add(ChatColor.RED + "Rewards:");
			quest1off.add(ChatColor.RED + "- Iron Sword (1)");
			quest1off.add(ChatColor.RED + "- Emeralds (25)");
			im.setLore(quest1off);
			is.setItemMeta(im);
			break;
		case quest1done:
			is = new ItemStack(Material.WOOL, 1, (short)7);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Fight For Me!");
			quest1done.add(ChatColor.LIGHT_PURPLE + "Kill 25 mobs to complete this task");
			quest1done.add(ChatColor.LIGHT_PURPLE + " ");
			quest1done.add(ChatColor.YELLOW + "Rewards:");
			quest1done.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1done.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1done);
			is.setItemMeta(im);
			break;
		default:
			break;
		}
		return is;
	}
}
