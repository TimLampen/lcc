package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Food implements Listener{
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Raw Fish*
			 */
			if(block.getItem().equals(new ItemStack(Material.RAW_FISH, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 2, 4);
			}
			/*
			 * For Raw Salmon*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RAW_FISH, 1, (short)1))){
				sellItem(player, block, block.getItem().getType(), 2, 4);
			}
			/*
			 * For PufferFish*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RAW_FISH, 1, (short)2))){
				sellItem(player, block, block.getItem().getType(), 3, 4);
			}
			/*
			 * For Raw ClownFish*
			 */
			else if(block.getItem().equals(new ItemStack(Material.RAW_FISH, 1, (short)3))){
				sellItem(player, block, block.getItem().getType(), 3, 4);
			}
			/*
			 * For Seeds*
			 */
			else if(block.getItem().getType()==Material.SEEDS){
				sellItem(player, block, block.getItem().getType(), 2, 16);
			}
			/*
			 * For Pumpkin Seeds*
			 */
			else if(block.getItem().getType()==Material.PUMPKIN_SEEDS){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Melon Seeds*
			 */
			else if(block.getItem().getType()==Material.MELON_SEEDS){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Carrot*
			 */
			else if(block.getItem().getType()==Material.CARROT){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Potato*
			 */
			else if(block.getItem().getType()==Material.POTATO){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Sugar Cane*
			 */
			else if(block.getItem().getType()==Material.SUGAR_CANE){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For Apple*
			 */
			else if(block.getItem().getType()==Material.APPLE){
				buyItem(player, block, block.getItem(), 1, 8);
			}
			/*
			 * For Bread*
			 */
			else if(block.getItem().getType()==Material.BREAD){
				buyItem(player, block, block.getItem(), 1, 7);
			}
			/*
			 * For Cooked Chicken*
			 */
			else if(block.getItem().getType()==Material.COOKED_CHICKEN){
				buyItem(player, block, block.getItem(), 1, 5);
			}
			/*
			 * For Cooked Beef*
			 */
			else if(block.getItem().getType()==Material.COOKED_BEEF){
				buyItem(player, block, block.getItem(), 1, 4);
			}
			/*
			 * For Punpkin Pie*
			 */
			else if(block.getItem().getType()==Material.PUMPKIN_PIE){
				buyItem(player, block, block.getItem(), 1, 2);
			}
			/*
			 * For Cake*
			 */
			else if(block.getItem().getType()==Material.CAKE){
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
