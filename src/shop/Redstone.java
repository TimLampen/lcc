package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Redstone implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Redstone*
			 */
			if(block.getItem().getType()==Material.REDSTONE){
				sellItem(player, block, block.getItem().getType(), 3, 32);
			}
			/*
			 * For Redstone Torch*
			 */
			else if(block.getItem().getType()==Material.REDSTONE_TORCH_ON){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Redstone Lamp*
			 */
			else if(block.getItem().getType()==Material.REDSTONE_LAMP_OFF){
				buyItem(player, block, block.getItem(), 2, 1);
			}
			/*
			 * For Redstone Repeater*
			 */
			else if(block.getItem().getType()==Material.DIODE){
				buyItem(player, block, block.getItem(), 2, 1);
			}
			/*
			 * For Trip Wire Hook*
			 */
			else if(block.getItem().getType()==Material.TRIPWIRE_HOOK){
				buyItem(player, block, block.getItem(), 2, 1);
			}
			/*
			 * For Piston*
			 */
			else if(block.getItem().getType()==Material.PISTON_BASE){
				buyItem(player, block, block.getItem(), 2, 1);
			}
			/*
			 * For Sticky Piston*
			 */
			else if(block.getItem().getType()==Material.PISTON_STICKY_BASE){
				buyItem(player, block, block.getItem(), 5, 1);
			}
			/*
			 * For Dispenser*
			 */
			else if(block.getItem().getType()==Material.DISPENSER){
				buyItem(player, block, block.getItem(), 3, 1);
			}
			/*
			 * For Powered Rail*
			 */
			else if(block.getItem().getType()==Material.POWERED_RAIL){
				buyItem(player, block, block.getItem(), 2, 1);
			}
			/*
			 * For Rail*
			 */
			else if(block.getItem().getType()==Material.RAILS){
				buyItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Minecart*
			 */
			else if(block.getItem().getType()==Material.MINECART){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Boat*
			 */
			else if(block.getItem().getType()==Material.BOAT){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Saddle*
			 */
			else if(block.getItem().getType()==Material.SADDLE){
				buyItem(player, block, block.getItem(), 25, 1);
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
	public void sellItem(Player player, ItemFrame block, Material m ,int price, int amount){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
			loop : for(ItemStack item: player.getInventory().getContents()){
				if(player.getInventory().contains(m)){
				if(item!=null && item.getType()==m){
						if(item.getAmount()>=price){
							player.getInventory().addItem(new ItemStack(Material.EMERALD, price));
							player.getInventory().removeItem(new ItemStack(m, amount));
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Purchase successful");
							break loop;
						}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough items!");
						break loop;
						}
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.RED + "Shop Error: You do not have enough items!");
					break loop;
					}
		}
	}
}
