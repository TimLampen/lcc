package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Valuables implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Flint*
			 */
			if(block.getItem().getType()==Material.COAL){
				sellItem(player, block, block.getItem().getType(), 5, 32);
			}
			/*
			 * For Iron Ingot*
			 */
			else if(block.getItem().getType()==Material.IRON_INGOT){
				sellItem(player, block, block.getItem().getType(), 5, 16);
			}/*
			 * For Gold Ingot*
			 */
			else if(block.getItem().getType()==Material.GOLD_INGOT){
				sellItem(player, block, block.getItem().getType(), 5, 8);
			}
			/*
			 * For Diamond*
			 */
			else if(block.getItem().getType()==Material.DIAMOND){
				sellItem(player, block, block.getItem().getType(), 5, 1);
			}
			/*
			 * For Nether Quartz*
			 */
			else if(block.getItem().getType()==Material.QUARTZ){
				sellItem(player, block, block.getItem().getType(), 5, 32);
			}
			/*
			 * For Lapis Lazuli*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)4))){
				sellItem(player, block, block.getItem().getType(), 5, 32);
			}
		}
	}
	public void sellItem(Player player, ItemFrame block, Material m ,int price, int amount){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
			loop : for(ItemStack item: player.getInventory().getContents()){
				if(player.getInventory().contains(m)){
				if(item!=null && item.getType()==m){
						if(item.getAmount()>=price){
							player.getInventory().addItem(new ItemStack(Material.EMERALD, price));
							player.getInventory().removeItem(new ItemStack(m, amount));
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Transaction successful");
							break loop;
						}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough items!");
						break loop;
						}
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough item!");
					break loop;
					}
		}
	}
}
