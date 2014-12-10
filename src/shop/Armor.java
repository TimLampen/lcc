package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Armor implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Diamond Helmet*
			 */
			if(block.getItem().getType()==Material.DIAMOND_HELMET){
				buyItem(player, block, block.getItem(), 25, 1);
			}
			/*
			 * For Diamond Chestplate*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_CHESTPLATE){
				buyItem(player, block, block.getItem(), 40, 1);
			}
			/*
			 * For Diamond Leggings*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_LEGGINGS){
				buyItem(player, block, block.getItem(), 35, 1);
			}
			/*
			 * For Diamond Boots*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_BOOTS){
				buyItem(player, block, block.getItem(), 20, 1);
			}
			/*
			 * For Diamond Horse Armor*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_BARDING){
				buyItem(player, block, block.getItem(), 30, 1);		
			}
		}
	}
	public void buyItem(Player player, ItemFrame block, ItemStack s ,int price, int amount) {
		String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
        if (player.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), price)) {
            player.getInventory().removeItem(new ItemStack(Material.EMERALD, price));
            s.setAmount(amount);
            player.getInventory().addItem(s);
            player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Purchase successful");
        }
        else {
            player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough gems!");
        }
    }
}
