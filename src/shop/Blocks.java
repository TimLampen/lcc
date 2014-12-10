package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Blocks implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Dirt*
			 */
			if(block.getItem().getType()==Material.DIRT){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Stone*
			 */
			else if(block.getItem().getType()==Material.STONE){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Gravel*
			 */
			else if(block.getItem().getType()==Material.GRAVEL){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Sand*
			 */
			else if(block.getItem().getType()==Material.SAND){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Bricks*
			 */
			else if(block.getItem().getType()==Material.BRICK){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Mossy Cobble*
			 */
			else if(block.getItem().getType()==Material.MOSSY_COBBLESTONE){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Nether Brick*
			 */
			else if(block.getItem().getType()==Material.NETHER_BRICK){
				buyItem(player, block, block.getItem().getType(), 1, 16);
			}
			/*
			 * For Chest*
			 */
			else if(block.getItem().getType()==Material.CHEST){
				buyItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Bookshelf*
			 */
			else if(block.getItem().getType()==Material.BOOKSHELF){
				buyItem(player, block, block.getItem().getType(), 2, 1);
			}
			/*
			 * For Juke Box*
			 */
			else if(block.getItem().getType()==Material.JUKEBOX){
				buyItem(player, block, block.getItem().getType(), 10, 1);
			}
			/*
			 * For Glowstone*
			 */
			else if(block.getItem().getType()==Material.GLOWSTONE){
				buyItem(player, block, block.getItem().getType(), 3, 8);
			}
			/*
			 * For Anvil*
			 */
			else if(block.getItem().getType()==Material.ANVIL){
				buyItem(player, block, block.getItem().getType(), 7, 1);
			}
			/*
			 * For Bed*
			 */
			else if(block.getItem().getType()==Material.BED){
				buyItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Obisidian*
			 */
			else if(block.getItem().getType()==Material.OBSIDIAN){
				buyItem(player, block, block.getItem().getType(), 10, 1);
			}
			/*
			 * For Ender Chest*
			 */
			else if(block.getItem().getType()==Material.ENDER_CHEST){
				buyItem(player, block, block.getItem().getType(), 75, 1);
			}
			/*
			 * For Beacon*
			 */
			else if(block.getItem().getType()==Material.BEACON){
				buyItem(player, block, block.getItem().getType(), 250, 1);
			}
		}
	}
	public void buyItem(Player player, ItemFrame block, Material m ,int price, int amount){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
			loop : for(ItemStack item: player.getInventory().getContents()){
				if(player.getInventory().contains(Material.EMERALD)){
				if(item!=null && item.getType()==Material.EMERALD){
						if(item.getAmount()>=price){
							player.getInventory().removeItem(new ItemStack(Material.EMERALD, price));
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
