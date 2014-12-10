package quests;

import java.util.ArrayList;
import java.util.HashMap;
import me.timlampen.lcc.Main;
import me.timlampen.lcc.QuestHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
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

public class ChickenHunter implements Listener{
	QuestHandler gh = new QuestHandler();
	public static HashMap<String, Integer> q3 = new HashMap<>();//contains player, number of kills
	Inventory inv = gh.inv;
	@EventHandler
	public void onPlayerKill(EntityDeathEvent event){
		if(event.getEntity().getKiller() instanceof Player){
			if(event.getEntity() instanceof Chicken){
			Player player = event.getEntity().getKiller();
			if(q3.containsKey(player.getName())){
			q3.put(player.getName(), q3.get(player.getName())+1);
		}
			}
		}
	}
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		player.setFoodLevel(4);
	    if (event.getRightClicked().getType()==EntityType.PIG) {
	    	if(q3!=null && q3.containsKey(player.getName()) && q3.get(player.getName())<=0){
	    		inv.setItem(2, getCustomItem(Quest.quest3progress));
	    		player.openInventory(inv);
	   }
	    	else if(Main.donequests!=null && Main.donequests.containsKey(player.getName()) && Main.donequests.get(player.getName())!=null && Main.donequests.get(player.getName()).contains("q3")){
	    		inv.setItem(2, getCustomItem(Quest.quest3done));
		    	player.openInventory(inv);
	    	}
	    	else if(q3!=null && !(q3.containsKey(player.getName()))){
	    		inv.setItem(2, getCustomItem(Quest.quest3on));
		    	player.openInventory(inv);
	    	}
	    	else if(q3!=null && q3.containsKey(player.getName()) && q3.get(player.getName())>=1){
	    		inv.setItem(2, getCustomItem(Quest.quest3reward));
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
					if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Chicken Hunter")){
						q3.put(player.getName(), 0);
						event.setCancelled(true);
						player.closeInventory();
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Chicken Hunter")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You are already completing this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Chicken Hunter")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You already completed this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Chicken Hunter")){
						event.setCancelled(true);
						player.closeInventory();
						player.getInventory().addItem(new ItemStack(Material.WOOL, 16, (short)6));
						player.getInventory().addItem(new ItemStack(Material.EMERALD, 3));
						player.getInventory().removeItem(new ItemStack(Material.FEATHER, 1));
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have completed the " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + " quest!");
						Main.donequests.put(player.getName(), "q3");
						q3.remove(player.getName());
					}
				}
			}
		}
	}
	public enum Quest {
		quest3on, quest3reward, quest3done, quest3progress,
	}

	public ItemStack getCustomItem(Quest q) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> quest3on = new ArrayList<String>();
		ArrayList<String> quest3reward = new ArrayList<String>();
		ArrayList<String> quest3done = new ArrayList<String>();
		ArrayList<String> quest3progress = new ArrayList<String>();
		switch (q) {
		case quest3on:
			is = new ItemStack(Material.WOOL, 1, (short)5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Chicken Hunter");
			quest3on.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest3on.add(ChatColor.LIGHT_PURPLE + " ");
			quest3on.add(ChatColor.YELLOW + "Rewards:");
			quest3on.add(ChatColor.YELLOW + "- Wool (16)");
			quest3on.add(ChatColor.YELLOW + "- Emeralds (3)");
			im.setLore(quest3on);
			is.setItemMeta(im);
			break;
		case quest3reward:
			is = new ItemStack(Material.WOOL, 1, (short)9);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Chicken Hunter");
			quest3reward.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest3reward.add(ChatColor.LIGHT_PURPLE + " ");
			quest3reward.add(ChatColor.YELLOW + "Rewards:");
			quest3reward.add(ChatColor.YELLOW + "- Wool (16)");
			quest3reward.add(ChatColor.YELLOW + "- Emeralds (3)");
			im.setLore(quest3reward);
			is.setItemMeta(im);
			break;
		case quest3done:
			is = new ItemStack(Material.WOOL, 1, (short)7);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Chicken Hunter");
			quest3done.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest3done.add(ChatColor.LIGHT_PURPLE + " ");
			quest3done.add(ChatColor.YELLOW + "Rewards:");
			quest3done.add(ChatColor.YELLOW + "- Wool (16)");
			quest3done.add(ChatColor.YELLOW + "- Emeralds (3)");
			im.setLore(quest3done);
			is.setItemMeta(im);
			break;
		case quest3progress:
			is = new ItemStack(Material.WOOL, 1, (short)14);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Chicken Hunter");
			quest3progress.add(ChatColor.LIGHT_PURPLE + "Kill 5 mobs to complete this task");
			quest3progress.add(ChatColor.LIGHT_PURPLE + " ");
			quest3progress.add(ChatColor.YELLOW + "Rewards:");
			quest3progress.add(ChatColor.YELLOW + "- Wool (16)");
			quest3progress.add(ChatColor.YELLOW + "- Emeralds (3)");
			im.setLore(quest3progress);
			is.setItemMeta(im);
		default:
			break;
		}
		return is;
	}
}
