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
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DIAMONDZ implements Listener{
	QuestHandler gh = new QuestHandler();
	public HashMap<String, Boolean> q2 = new HashMap<>();
	public Inventory inv = gh.inv;
	@EventHandler
	public void onDiamondCraft(CraftItemEvent event){
		if(event.getWhoClicked() instanceof Player){
			Player player = (Player)event.getWhoClicked();
			if(q2.containsKey(player.getName())){
		if(event.getRecipe().getResult().getType()==Material.DIAMOND_BLOCK){
			q2.put(player.getName(), true);
		}
			}
		}
	}
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
	    if (event.getRightClicked().getType()==EntityType.PIG) {
	    	if(q2!=null && q2.containsKey(player.getName()) && q2.get(player.getName())==false){
	    		inv.setItem(1, getCustomItem(Quest.quest2progress));
	    		player.openInventory(inv);
	   }
	    	else if(Main.donequests!=null && Main.donequests.containsKey(player.getName()) && Main.donequests.get(player.getName())!=null && Main.donequests.get(player.getName()).contains("q2")){
		    		inv.setItem(1, getCustomItem(Quest.quest2done));
		    		player.openInventory(inv);
	    	}
	    	else if(q2!=null && !(q2.containsKey(player.getName()))){
		    		inv.setItem(1, getCustomItem(Quest.quest2on));
		    		player.openInventory(inv);
	    	}
	    	else if(q2!=null && q2.containsKey(player.getName()) && q2.get(player.getName())==true){
		    		inv.setItem(1, getCustomItem(Quest.quest2reward));
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
					if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "OMG DIAMONDZ!!")){
						q2.put(player.getName(), false);
						event.setCancelled(true);
						player.closeInventory();
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "OMG DIAMONDZ!!")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You are already completing this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "OMG DIAMONDZ!!")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You already completed this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "OMG DIAMONDZ!!")){
						event.setCancelled(true);
						player.closeInventory();
						player.getInventory().addItem(new ItemStack(Material.EMERALD, 10));
						player.setLevel(player.getLevel()+5);
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have completed the " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + " quest!");
						Main.donequests.put(player.getName(), "q2");
						q2.remove(player.getName());
					}
				}
			}
		}
	}
	public enum Quest {
		quest2on, quest2reward, quest2done, quest2progress
	}

	public ItemStack getCustomItem(Quest q) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> quest2on = new ArrayList<String>();
		ArrayList<String> quest2reward = new ArrayList<String>();
		ArrayList<String> quest2done = new ArrayList<String>();
		ArrayList<String> quest2progress = new ArrayList<String>();
		switch (q) {
		case quest2on:
			is = new ItemStack(Material.WOOL, 1, (short)5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "OMG DIAMONDZ!!");
			quest2on.add(ChatColor.LIGHT_PURPLE + "Craft a diamond block to complete this task");
			quest2on.add(ChatColor.LIGHT_PURPLE + " ");
			quest2on.add(ChatColor.YELLOW + "Rewards:");
			quest2on.add(ChatColor.YELLOW + "- Experience (255)");
			quest2on.add(ChatColor.YELLOW + "- Emeralds (10)");
			im.setLore(quest2on);
			is.setItemMeta(im);
			break;
		case quest2reward:
			is = new ItemStack(Material.WOOL, 1, (short)9);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "OMG DIAMONDZ!!");
			quest2reward.add(ChatColor.LIGHT_PURPLE + "Craft a diamond block to complete this task");
			quest2reward.add(ChatColor.LIGHT_PURPLE + " ");
			quest2reward.add(ChatColor.YELLOW + "Rewards:");
			quest2reward.add(ChatColor.YELLOW + "- Experience (255)");
			quest2reward.add(ChatColor.YELLOW + "- Emeralds (10)");
			im.setLore(quest2reward);
			is.setItemMeta(im);
			break;
		case quest2done:
			is = new ItemStack(Material.WOOL, 1, (short)7);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "OMG DIAMONDZ!!");
			quest2done.add(ChatColor.LIGHT_PURPLE + "Craft a diamond block to complete this task");
			quest2done.add(ChatColor.LIGHT_PURPLE + " ");
			quest2done.add(ChatColor.YELLOW + "Rewards:");
			quest2done.add(ChatColor.YELLOW + "- Experience (255)");
			quest2done.add(ChatColor.YELLOW + "- Emeralds (10)");
			im.setLore(quest2done);
			is.setItemMeta(im);
			break;
		case quest2progress:
			is = new ItemStack(Material.WOOL, 1, (short)14);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "OMG DIAMONDZ!!");
			quest2progress.add(ChatColor.LIGHT_PURPLE + "Craft a diamond block to complete this task");
			quest2progress.add(ChatColor.LIGHT_PURPLE + " ");
			quest2progress.add(ChatColor.YELLOW + "Rewards:");
			quest2progress.add(ChatColor.YELLOW + "- Experience (255)");
			quest2progress.add(ChatColor.YELLOW + "- Emeralds (10)");
			im.setLore(quest2progress);
			is.setItemMeta(im);
		default:
			break;
		}
		return is;
	}
}
