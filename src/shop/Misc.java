package shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Misc implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBuy(PlayerInteractEntityEvent event) {
		if(event.getRightClicked() instanceof ItemFrame){
			Player player = event.getPlayer();
			ItemFrame block = (ItemFrame)event.getRightClicked();
			/*
			 * For Water Bucket*
			 */
			if(block.getItem().getType()==Material.WATER_BUCKET){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Milk Bucket*
			 */
			else if(block.getItem().getType()==Material.MILK_BUCKET){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Lava Bucket*
			 */
			else if(block.getItem().getType()==Material.LAVA_BUCKET){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Arrows*
			 */
			else if(block.getItem().getType()==Material.ARROW){
				buyItem(player, block, block.getItem(), 2, 32);
			}
			/*
			 * For Bow*
			 */
			else if(block.getItem().getType()==Material.BOW){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Shears*
			 */
			else if(block.getItem().getType()==Material.SHEARS){
				buyItem(player, block, block.getItem(), 1, 1);
			}
			/*
			 * For Lead*
			 */
			else if(block.getItem().getType()==Material.LEASH){
				buyItem(player, block, block.getItem(), 15, 1);
			}
			/*
			 * For Name Tag*
			 */
			else if(block.getItem().getType()==Material.NAME_TAG){
				buyItem(player, block, block.getItem(), 25, 1);
			}
			/*
			 * For Rotten Flesh*
			 */
			else if(block.getItem().getType()==Material.ROTTEN_FLESH){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For Spider Eye*
			 */
			else if(block.getItem().getType()==Material.SPIDER_EYE){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For String*
			 */
			else if(block.getItem().getType()==Material.STRING){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For Bone*
			 */
			else if(block.getItem().getType()==Material.BONE){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For Golden Nugget*
			 */
			else if(block.getItem().getType()==Material.GOLD_NUGGET){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Slimeball*
			 */
			else if(block.getItem().getType()==Material.SLIME_BALL){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For GunPowder*
			 */
			else if(block.getItem().getType()==Material.SULPHUR){
				sellItem(player, block, block.getItem().getType(), 2, 8);
			}
			/*
			 * For Flint*
			 */
			else if(block.getItem().getType()==Material.FLINT){
				sellItem(player, block, block.getItem().getType(), 1, 8);
			}
			/*
			 * For Bone Meal*
			 */
			else if(block.getItem().equals(new ItemStack(Material.INK_SACK, 1, (short)15))){
				sellItem(player, block, block.getItem().getType(), 2, 1);
			}
			/*
			 * For Record Disk 13*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2256, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Cat*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2257, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Blocks*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2258, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Chirp*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2259, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Far*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2260, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Mall*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2261, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Mellohi*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2262, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Stal*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2263, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Strad*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2264, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Ward*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2265, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Record Disk Wait*
			 */
			else if(block.getItem().getType().equals(new ItemStack(2267, 1, (short)0))){
				sellItem(player, block, block.getItem().getType(), 20, 1);
			}
			/*
			 * For Bottle o Enchanting*
			 */
			else if(block.getItem().getType()==Material.EXP_BOTTLE){
				sellItem(player, block, block.getItem().getType(), 5, 16);
			}
			/*
			 * For Blaze Powder*
			 */
			else if(block.getItem().getType()==Material.BLAZE_POWDER){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Fermented Spider Eye*
			 */
			else if(block.getItem().getType()==Material.FERMENTED_SPIDER_EYE){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Ghast Tear*
			 */
			else if(block.getItem().getType()==Material.GHAST_TEAR){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Magma Cream*
			 */
			else if(block.getItem().getType()==Material.MAGMA_CREAM){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Brewing Stand*
			 */
			else if(block.getItem().getType()==Material.BREWING_STAND){
				sellItem(player, block, block.getItem().getType(), 3, 1);
			}
			/*
			 * For Glistering Melon*
			 */
			else if(block.getItem().getType()==Material.SPECKLED_MELON){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Fishing Rod*
			 */
			else if(block.getItem().getType()==Material.FISHING_ROD){
				sellItem(player, block, block.getItem().getType(), 1, 1);
			}
			/*
			 * For Carrot on a Stick*
			 */
			else if(block.getItem().getType()==Material.CARROT_STICK){
				sellItem(player, block, block.getItem().getType(), 1, 1);
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
