package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Natural implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Blue Orchid*
			 */
			if(block.getItem().equals(new ItemStack(Material.RED_ROSE, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Oxeye Daisy*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RED_ROSE, 1, (short)8))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Allium*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RED_ROSE, 1, (short)2))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}/*
			 * For SunFlower*
			 */
			else if(block.getItem().equals(new ItemStack(175, 1))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}/*
			 * For Azure Bluet*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RED_ROSE, 1, (short)3))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}/*
			 * For Lilac*
			 */
			else if(block.getItem().equals(new ItemStack(175, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}/*
			 * For Rose Bush*
			 */
			else if(block.getItem().equals(new ItemStack(175, 1, (short)4))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}/*
			 * For Peony*
			 */
			else if(block.getItem().equals(new ItemStack(175, 1, (short)5))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Oak Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Spruce Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Birch Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG, 1, (short)2))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Jungle Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG, 1, (short)3))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Acadia Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG_2, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Dark Oak Log*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LOG_2, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 3, 16);
			}
			/*
			 * For Oak Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Spruce Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Birch Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)2))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Jungle Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)3))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Acadia Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)4))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Dark Oak Sapling*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SAPLING, 1, (short)5))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Oak Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Spruce Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Birch Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES, 1, (short)2))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Jungle Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES, 1, (short)3))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Acadia Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES_2, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Dark Oak Leaves*
			 */
			else if(block.getItem().equals(new ItemStack(Material.LEAVES_2, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Grass*
			 */
			else if(block.getItem().equals(new ItemStack(31, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Fern*
			 */
			else if(block.getItem().equals(new ItemStack(31, 1, (short)1))){
				buyIDItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Lilypad*
			 */
			else if(block.getItem().equals(new ItemStack(Material.WATER_LILY, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Double Tall Grass*
			 */
			else if(block.getItem().equals(new ItemStack(Material.DOUBLE_PLANT, 1, (short)2))){
				buyIDItem(player, block, block.getItem(), 1, 16);
			}
			/*
			 * For Large Fern*
			 */
			else if(block.getItem().equals(new ItemStack(Material.DOUBLE_PLANT, 1, (short)3))){
				buyIDItem(player, block, block.getItem(), 1, 16);
			}
			/*
			 * For Vines*
			 */
			else if(block.getItem().equals(new ItemStack(Material.VINE, 1, (short)0))){
				buyIDItem(player, block, block.getItem(), 1, 8);
			}
		}
	}
	public void buyIDItem(Player player, ItemFrame block, ItemStack s ,int price, int amount) {
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
