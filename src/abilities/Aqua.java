package abilities;

import java.util.ArrayList;
import java.util.HashMap;

import me.timlampen.lcc.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Aqua implements Listener{
	ArrayList<String> tombcooldown = new ArrayList<>();
	ArrayList<String> braincooldown = new ArrayList<>();
	ArrayList<Location> tombblocks = new ArrayList<>();
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(event.getEntity() instanceof Player && event.getCause()==DamageCause.DROWNING){
			Player player = (Player)event.getEntity();
			if(Main.perms!=null && Main.perms.playerInGroup(player, "waterclan")){
			player.sendMessage(prefix + ChatColor.YELLOW + "Your poseidon passive has been activated! Strength 1 (60s)");
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60*20, 0));
			event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onFrozenTomb(PlayerInteractEntityEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		final Player player = event.getPlayer();
		if(event.getRightClicked().getType()==EntityType.PLAYER && Main.perms.playerInGroup(player, "waterclan")){
				if(player.getItemInHand().getType()==Material.IRON_SWORD || player.getItemInHand().getType()==Material.DIAMOND_SWORD || player.getItemInHand().getType()==Material.STONE_SWORD || player.getItemInHand().getType()==Material.WOOD_SWORD){
					if(!tombcooldown.contains(player.getName())){
						tombcooldown.add(player.getName());
						player.sendMessage(prefix + ChatColor.YELLOW + "Your frozen tomb active has been activated! (60s Cooldown)");
						Player target = (Player) event.getRightClicked();
						addLocations(target);
						for(Location l: tombblocks){
							if(player.getWorld().getBlockAt(l).getType()==Material.AIR){
								player.getWorld().getBlockAt(l).setType(Material.PACKED_ICE);
							}
						}
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){
							@Override
							public void run() {
								for(Location l: tombblocks){
									if(player.getWorld().getBlockAt(l).getType()==Material.PACKED_ICE){
										player.getWorld().getBlockAt(l).setType(Material.AIR);
									}
								}
								player.sendMessage(prefix + ChatColor.YELLOW + "Your frozen tomb ability is now off cooldown!");
								tombblocks.clear();
								tombcooldown.remove(player.getName());
							}}, 60*20);
					}
					else{
						player.sendMessage(prefix + ChatColor.RED + "That ability is still on cooldown!");
					}
				}
			}
		}
	public void addLocations(Player target){
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY()+2, target.getLocation().getZ()));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY()+1, target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY()+1, target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY()+1, target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY()+1, target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY()+1, target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY()+1, target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY()+1, target.getLocation().getZ()));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY()+1, target.getLocation().getZ()));//only in 4 top corners
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY(), target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY(), target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY(), target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY(), target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY(), target.getLocation().getZ()-1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY(), target.getLocation().getZ()+1));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()-1, target.getLocation().getBlockY(), target.getLocation().getZ()));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX()+1, target.getLocation().getBlockY(), target.getLocation().getZ()));
		tombblocks.add(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getBlockY()-1, target.getLocation().getZ()));
	}
	@EventHandler
	public void onBrainFreeze(EntityDamageByEntityEvent event){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(event.getDamager() instanceof Snowball){
			Snowball snowball = (Snowball)event.getDamager();
			if(snowball.getShooter()instanceof Player && event.getEntity() instanceof Player){
				final Player player = (Player)snowball.getShooter();
				Player target = (Player)event.getEntity();
				if(!braincooldown.contains(player.getName())){
					if(Main.perms!=null &&Main.perms.playerInGroup(player, "waterclan")){
						braincooldown.add(player.getName());
						target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*45, 1));
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

							@Override
							public void run() {
								braincooldown.remove(player.getName());
								player.sendMessage(prefix + ChatColor.YELLOW + "Your frozen brain freeze is now off cooldown!");
								
							}}, 15*20);
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.RED + "That ability is still on cooldown!");
				}
			}
		}
	}
}
