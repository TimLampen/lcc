package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DyeWoolClay implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Rose Red*
			 */
			if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)1))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Cactus Green*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)2))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Dandelion Yellow*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)11))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Light Blue Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)12))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Light Gray Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)7))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Gray Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)8))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Pink Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)9))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Magenta Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)13))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Purple Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)5))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Cyan Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)6))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Lime Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)10))){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Orange Dye*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)14))){
				buyItem(player, block, block.getItem(), 1, 1);
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
