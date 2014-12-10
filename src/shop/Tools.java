package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Tools implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Diamond Axe*
			 */
			if(block.getItem().getType()==Material.DIAMOND_AXE){
				buyItem(player, block, block.getItem(), 15, 1);
			}
			/*
			 * For Diamond Sword*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_SWORD){
				buyItem(player, block, block.getItem(), 10, 1);
			}
			/*
			 * For Diamond Pickaxe*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_PICKAXE){
				buyItem(player, block, block.getItem(), 15, 1);
			}
			/*
			 * For Diamond Shovel*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_SPADE){
				buyItem(player, block, block.getItem(), 5, 1);
			}
			/*
			 * For Diamond Hoe*
			 */
			else if(block.getItem().getType()==Material.DIAMOND_HOE){
				buyItem(player, block, block.getItem(), 10, 1);
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
