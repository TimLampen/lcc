package quests;

import java.util.ArrayList;
import java.util.HashMap;

import me.timlampen.lcc.Main;
import me.timlampen.lcc.QuestHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DownDirty implements Listener{
	QuestHandler gh = new QuestHandler();
	public HashMap<String, Integer> q4 = new HashMap<>();
	public Inventory inv = gh.inv;
	@EventHandler 
	public void onStoneBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		if(event.getBlock().getType()==Material.STONE && q4.containsKey(player.getName())){
			q4.put(player.getName(), q4.get(player.getName())+1);
		}
	}
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
	    if (event.getRightClicked().getType()==EntityType.PIG) {
	    	if(q4!=null && q4.containsKey(player.getName()) && q4.get(player.getName())>1000){
	    		inv.setItem(3, getCustomItem(Quest.quest4progress));
	    		player.openInventory(inv);
	   }
	    	else if(Main.donequests!=null && Main.donequests.containsKey(player.getName()) && Main.donequests.get(player.getName())!=null && Main.donequests.get(player.getName()).contains("q4")){
		    		inv.setItem(3, getCustomItem(Quest.quest4done));
		    		player.openInventory(inv);
	    	}
	    	else if(q4!=null && !(q4.containsKey(player.getName()))){
		    		inv.setItem(3, getCustomItem(Quest.quest4on));
		    		player.openInventory(inv);
	    	}
	    	else if(q4!=null && q4.containsKey(player.getName()) && q4.get(player.getName())>=1000){
		    		inv.setItem(3, getCustomItem(Quest.quest4reward));
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
					if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Down & Dirty")){
						q4.put(player.getName(), 0);
						event.setCancelled(true);
						player.closeInventory();
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Down & Dirty")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You are already completing this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Down & Dirty")){
						event.setCancelled(true);
						player.closeInventory();
						player.sendMessage(prefix + ChatColor.RED + "Quest Error: You already completed this quest!");
					}
					else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Down & Dirty")){
						event.setCancelled(true);
						player.closeInventory();
						player.getInventory().addItem(new ItemStack(Material.EMERALD, 10));
						player.setLevel(player.getLevel()+20);
						player.getInventory().addItem(getCustomItem(Quest.stpick));
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have completed the " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + " quest!");
						Main.donequests.put(player.getName(), "q4");
						q4.remove(player.getName());
					}
				}
			}
		}
	}
	public enum Quest {
		quest4on, quest4reward, quest4done, quest4progress, stpick
	}

	public ItemStack getCustomItem(Quest q) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> quest4on = new ArrayList<String>();
		ArrayList<String> quest4reward = new ArrayList<String>();
		ArrayList<String> quest4done = new ArrayList<String>();
		ArrayList<String> quest4progress = new ArrayList<String>();
		switch (q) {
		case quest4on:
			is = new ItemStack(Material.WOOL, 1, (short)5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Down & Dirty");
			quest4on.add(ChatColor.LIGHT_PURPLE + "Mine 1,000 stone to complete this task");
			quest4on.add(ChatColor.LIGHT_PURPLE + " ");
			quest4on.add(ChatColor.YELLOW + "Rewards:");
			quest4on.add(ChatColor.YELLOW + "- Experience (825)");
			quest4on.add(ChatColor.YELLOW + "- Emeralds (10)");
			quest4on.add(ChatColor.YELLOW + "- Silk Touch (1)");
			im.setLore(quest4on);
			is.setItemMeta(im);
			break;
		case quest4reward:
			is = new ItemStack(Material.WOOL, 1, (short)9);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Down & Dirty");
			quest4reward.add(ChatColor.LIGHT_PURPLE + "Mine 1,000 stone to complete this task");
			quest4reward.add(ChatColor.LIGHT_PURPLE + " ");
			quest4reward.add(ChatColor.YELLOW + "Rewards:");
			quest4reward.add(ChatColor.YELLOW + "- Experience (825)");
			quest4reward.add(ChatColor.YELLOW + "- Emeralds (10)");
			quest4reward.add(ChatColor.YELLOW + "- Silk Touch (1)");
			im.setLore(quest4reward);
			is.setItemMeta(im);
			break;
		case quest4done:
			is = new ItemStack(Material.WOOL, 1, (short)7);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Down & Dirty");
			quest4done.add(ChatColor.LIGHT_PURPLE + "Mine 1,000 stone to complete this task");
			quest4done.add(ChatColor.LIGHT_PURPLE + " ");
			quest4done.add(ChatColor.YELLOW + "Rewards:");
			quest4done.add(ChatColor.YELLOW + "- Experience (825)");
			quest4done.add(ChatColor.YELLOW + "- Emeralds (10)");
			quest4done.add(ChatColor.YELLOW + "- Silk Touch (1)");
			im.setLore(quest4done);
			is.setItemMeta(im);
			break;
		case quest4progress:
			is = new ItemStack(Material.WOOL, 1, (short)14);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Down & Dirty");
			quest4progress.add(ChatColor.LIGHT_PURPLE + "Mine 1,000 stone to complete this task");
			quest4progress.add(ChatColor.LIGHT_PURPLE + " ");
			quest4progress.add(ChatColor.YELLOW + "Rewards:");
			quest4progress.add(ChatColor.YELLOW + "- Experience (825)");
			quest4progress.add(ChatColor.YELLOW + "- Emeralds (10)");
			quest4progress.add(ChatColor.YELLOW + "- Silk Touch (1)");
			im.setLore(quest4progress);
			is.setItemMeta(im);
		case stpick:
			is = new ItemStack(Material.DIAMOND_PICKAXE, 1);
			is.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
			is.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		default:
			break;
		}
		return is;
	}
}
