package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Mobs implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Pig Spawner*
			 */
			if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)90))){
				buyItem(player, block, block.getItem(), 80, 1);
			}
			/*
			 * For Sheep Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)91))){
				buyItem(player, block, block.getItem(), 60, 1);
			}
			/*
			 * For Cow Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)92))){
				buyItem(player, block, block.getItem(), 80, 1);
			}
			/*
			 * For Wolf Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)95))){
				buyItem(player, block, block.getItem(), 90, 1);
			}
			/*
			 * For Mooshroom Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)96))){
				buyItem(player, block, block.getItem(), 90, 1);
			}
			/*
			 * For Horse Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)110))){
				buyItem(player, block, block.getItem(), 110, 1);
			}
			/*
			 * For Chicken Spawner*
			 */
			else if(block.getItem().equals(new ItemStack(Material.MOB_SPAWNER, 1, (short)93))){
				buyItem(player, block, block.getItem(), 70, 1);
			}
			/*
			 * For Head*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SKULL_ITEM, 1, (short)3))){
				buyLargeItem(player, block, block.getItem().getType(), 50, 1);
			}
			/*
			 * For Zombie Head*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SKULL_ITEM, 1, (short)2))){
				buyLargeItem(player, block, block.getItem().getType(), 11, 1);
			}
			/*
			 * For Creeper Head*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SKULL_ITEM, 1, (short)4))){
				buyLargeItem(player, block, block.getItem().getType(), 27, 1);
			}
			/*
			 * For Skeleton Head*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SKULL_ITEM, 1, (short)0))){
				buyLargeItem(player, block, block.getItem().getType(), 500, 1);
			}
			/*
			 * For Wither Skeleton Head*
			 */
			else if(block.getItem().equals(new ItemStack(Material.SKULL_ITEM, 1, (short)1))){
				buyLargeItem(player, block, block.getItem().getType(), 1000, 1);
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
	public void buyLargeItem(Player player, ItemFrame block, Material m ,int price, int amount){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
			loop : for(ItemStack item: player.getInventory().getContents()){
				if(player.getInventory().contains(Material.EMERALD_BLOCK)){
				if(item!=null && item.getType()==Material.EMERALD_BLOCK){
						if(item.getAmount()>=price){
							player.getInventory().removeItem(new ItemStack(Material.EMERALD_BLOCK, price));
							player.getInventory().addItem(new ItemStack(m, amount));
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Purchase successful");
							break loop;
						}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough gems!");
						break loop;
						}
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough gems!");
					break loop;
					}
		}
	}

}
