package abilities;

import java.util.ArrayList;
import java.util.HashMap;

import me.timlampen.lcc.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Blaze implements Listener{
	ArrayList<String> leap = new ArrayList<>();
	
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player)event.getEntity();
			if(event.getCause()==DamageCause.FALL){
				if(leap.contains(player.getName())){
					event.setCancelled(true);
					leap.remove(player.getName());
				}
			}
		}
	}
	ArrayList<String> flamescooldown = new ArrayList<>();
	ArrayList<String> leapcooldown = new ArrayList<>();
	HashMap<String, ItemStack> test = new HashMap<>();
	@EventHandler
	public void onLavaWader(PlayerToggleSneakEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		final Player player = event.getPlayer();
		if(Main.perms!=null && Main.perms.playerInGroup(player, "fireclan")){
			if(!player.isSneaking() && player.getFireTicks()!=0){
				if(!leapcooldown.contains(player.getName())){
					player.sendMessage(prefix + ChatColor.YELLOW + "Your flaming leap active has been activated! (5s Cooldown)");
					player.setVelocity(player.getLocation().getDirection().multiply(4));
					leapcooldown.add(player.getName());
					leap.add(player.getName());
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

						@Override
						public void run() {
							leapcooldown.remove(player.getName());
							player.sendMessage(prefix + ChatColor.YELLOW + "Your flaming leap ability is now off cooldown!");
							
						}}, 5*20);
			}
				else{
					player.sendMessage(prefix + ChatColor.RED + "That ability is still on cooldown!");
				}
			}
			else if(!player.isSneaking()){
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						if(player.isSneaking()){
							player.sendMessage(prefix + ChatColor.YELLOW + "Your lava wader passive has been activated!");
							player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10*20, 0));
						}
						
					}}, 3*20);
			}
		}
	}
	@EventHandler
	public void onDevouringFlames(PlayerInteractEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		final Player player = event.getPlayer();
		if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_AIR){
			if(player.getItemInHand().getType()==Material.DIAMOND_SWORD || player.getItemInHand().getType()==Material.IRON_SWORD || player.getItemInHand().getType()==Material.STONE_SWORD || player.getItemInHand().getType()==Material.WOOD_SWORD){
				if(Main.perms.playerInGroup(player, "fireclan")){
					if(!flamescooldown.contains(player.getName())){
					flamescooldown.add(player.getName());
					player.getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
					player.sendMessage(prefix + ChatColor.YELLOW + "Your devouring flames active has been activated! (120s Cooldown)");
					test.put(player.getName(), player.getItemInHand());
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

						@Override
						public void run() {
							player.sendMessage(prefix + ChatColor.YELLOW + "Your sword's power has dwindled");
							for(ItemStack is: player.getInventory().getContents()){
							if(is!=null && is.equals(test.get(player.getName()))){
								is.removeEnchantment(Enchantment.FIRE_ASPECT);
								test.remove(is);
								}	
							}
							
						}}, 10*20);
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

					@Override
					public void run() {
						flamescooldown.remove(player.getName());
						player.sendMessage(prefix + ChatColor.YELLOW + "Your devouring flames ability is now off cooldown!");
					}}, 10*20);
				}
					else{
						player.sendMessage(prefix + ChatColor.RED + "That ability is still on cooldown!");
					}
			}
		}
	}
	}
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(test.containsValue(event.getItemDrop())){
			event.setCancelled(true);
			Player player = event.getPlayer();
			player.sendMessage(prefix + ChatColor.YELLOW + "You cannot drop an item that is enraged with devouring flames!");
		}
	}
	@EventHandler
	public void onClick(InventoryClickEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(event.getWhoClicked() instanceof Player){
			Player player = (Player)event.getWhoClicked();
			if(test.containsValue(event.getCursor()) || test.containsValue(event.getCurrentItem())){
				event.setCancelled(true);
				player.sendMessage(prefix + ChatColor.YELLOW + "You cannot put an item that is enraged with devouring flames into an inventory!");
		}
		}
	}

}
