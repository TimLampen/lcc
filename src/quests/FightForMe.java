package quests;

import java.util.ArrayList;
import java.util.HashMap;

import me.timlampen.lcc.Main;
import me.timlampen.lcc.QuestHandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FightForMe implements Listener{
	QuestHandler gh = new QuestHandler();
	public HashMap<String, Integer> q1 = new HashMap<>();//contains player, number of kills
	public Inventory inv = gh.inv;
	@EventHandler
	public void onPlayerKill(EntityDeathEvent event){
		if(event.getEntity().getKiller() instanceof Player){
			Player player = event.getEntity().getKiller();
			if(q1.containsKey(player.getName())){
			q1.put(player.getName(), q1.get(player.getName())+1);
		}
		}
	}
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
	    if (event.getRightClicked().getType()==EntityType.PIG) {
	    	if(q1!=null && q1.containsKey(player.getName()) && q1.get(player.getName())<5){
	    		inv.setItem(0, getCustomItem(Quest.quest1progress));
	    		player.openInventory(inv);
	   }
	    	else if(Main.donequests!=null && Main.donequests.containsKey(player.getName()) && Main.donequests.get(player.getName())!=null && Main.donequests.get(player.getName()).contains("q1")){
	    		inv.setItem(0, getCustomItem(Quest.quest1done));
		    	player.openInventory(inv);
	    	}
	    	else if(q1!=null && !(q1.containsKey(player.getName()))){
	    		inv.setItem(0, getCustomItem(Quest.quest1on));
		    	player.openInventory(inv);
	    	}
	    	else if(q1!=null && q1.containsKey(player.getName()) && q1.get(player.getName())>=5){
	    		inv.setItem(0, getCustomItem(Quest.quest1reward));
		    	player.openInventory(inv);
	    	}
	    }
	}
	@EventHandler
	public void onQuestClick(InventoryClickEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(event.getWhoClicked() instanceof Player){
			Player player = (Player)event.getWhoClicked();
			if(event.getInventory().getName().equals(ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] " + ChatColor.LIGHT_PURPLE + "Quests")){
				if(event.getCurrentItem()!=null && event.getCurrentItem().getItemMeta()!=null && event.getCurrentItem().getItemMeta()!=null){
					if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Fight For Me!")){
						q1.put(player.getName(), 0);
						event.setCancelled(true);
						player.closeInventory();
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Fight For Me!")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You are already completing this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Fight For Me!")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You already completed this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Fight For Me!")){
						event.setCancelled(true);
						player.closeInventory();
						player.getInventory().addItem(new ItemStack(Material.EMERALD, 25));
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have completed the " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + " quest!");
						Main.donequests.put(player.getName(), "q1");
						q1.remove(player.getName());
					}
				}
			}
		}
	}
	public enum Quest {
		quest1on, quest1reward, quest1done, quest1progress,
	}

	public ItemStack getCustomItem(Quest q) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> quest1on = new ArrayList<String>();
		ArrayList<String> quest1reward = new ArrayList<String>();
		ArrayList<String> quest1done = new ArrayList<String>();
		ArrayList<String> quest1progress = new ArrayList<String>();
		switch (q) {
		case quest1on:
			is = new ItemStack(Material.WOOL, 1, (short)5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Fight For Me!");
			quest1on.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest1on.add(ChatColor.LIGHT_PURPLE + " ");
			quest1on.add(ChatColor.YELLOW + "Rewards:");
			quest1on.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1on.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1on);
			is.setItemMeta(im);
			break;
		case quest1reward:
			is = new ItemStack(Material.WOOL, 1, (short)9);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fight For Me!");
			quest1reward.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest1reward.add(ChatColor.LIGHT_PURPLE + " ");
			quest1reward.add(ChatColor.YELLOW + "Rewards:");
			quest1reward.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1reward.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1reward);
			is.setItemMeta(im);
			break;
		case quest1done:
			is = new ItemStack(Material.WOOL, 1, (short)7);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Fight For Me!");
			quest1done.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest1done.add(ChatColor.LIGHT_PURPLE + " ");
			quest1done.add(ChatColor.YELLOW + "Rewards:");
			quest1done.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1done.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1done);
			is.setItemMeta(im);
			break;
		case quest1progress:
			is = new ItemStack(Material.WOOL, 1, (short)14);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fight For Me!");
			quest1progress.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest1progress.add(ChatColor.LIGHT_PURPLE + " ");
			quest1progress.add(ChatColor.YELLOW + "Rewards:");
			quest1progress.add(ChatColor.YELLOW + "- Iron Sword (1)");
			quest1progress.add(ChatColor.YELLOW + "- Emeralds (25)");
			im.setLore(quest1progress);
			is.setItemMeta(im);
		default:
			break;
		}
		return is;
	}
}
