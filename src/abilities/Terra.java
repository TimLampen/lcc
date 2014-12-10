package abilities;

import java.util.HashMap;

import me.timlampen.lcc.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.slikey.effectlib.effect.JumpEntityEffect;
import de.slikey.effectlib.effect.TraceEntityEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class Terra implements Listener{
	HashMap<String, Long> stonecooldown = new HashMap<>();
	HashMap<String, Long> earthcooldown = new HashMap<>();
	@EventHandler
	public void onTerraAbilities(PlayerToggleSneakEvent event){
		Player player = event.getPlayer();
		if(Main.perms.playerInGroup(player, "earthclan")){
			if(player.getWorld().getBlockAt(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getBlockY()-1, player.getLocation().getBlockZ())).getType()==Material.GRASS && player.isSneaking()){
				Runnable(player);
			}
		}
	}
	@EventHandler
	public void onStoneArm(PlayerInteractEvent event){
		final Player player = event.getPlayer();
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(Main.perms.playerInGroup(player, "earthclan")){
			if(event.getAction()==Action.RIGHT_CLICK_BLOCK || event.getAction()==Action.RIGHT_CLICK_AIR){
				if(player.getItemInHand().getType()==Material.DIAMOND_SWORD || player.getItemInHand().getType()==Material.IRON_SWORD || player.getItemInHand().getType()==Material.STONE_SWORD || player.getItemInHand().getType()==Material.WOOD_SWORD){
					if(stonecooldown.containsKey(player.getName()) && stonecooldown.get(player.getName())-System.currentTimeMillis()*1000<20){
					stonecooldown.put(player.getName(), System.currentTimeMillis());
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*8, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*8, 0));
					player.sendMessage(prefix + ChatColor.YELLOW + "Your stone arm active has been actvated! (20s Cooldown)");
				}
				else if(!stonecooldown.containsKey(player.getName())){
					stonecooldown.put(player.getName(), System.currentTimeMillis());
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*8, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*8, 0));
					player.sendMessage(prefix + ChatColor.YELLOW + "Your stone arm active has been actvated! (20s Cooldown)");
				}
				else{
					player.sendMessage(prefix + ChatColor.YELLOW  + "Your stone arm ability is still on cooldown!");
				}
				}
				else if(player.getItemInHand().getType()==Material.DIAMOND_AXE || player.getItemInHand().getType()==Material.IRON_AXE || player.getItemInHand().getType()==Material.STONE_AXE || player.getItemInHand().getType()==Material.WOOD_AXE){
					if(earthcooldown.containsKey(player.getName()) && earthcooldown.get(player.getName())-System.currentTimeMillis()*1000<360){
						earthcooldown.put(player.getName(), System.currentTimeMillis());
						earthSmash(player);
					}
					else if(!earthcooldown.containsKey(player.getName())){
						earthcooldown.put(player.getName(), System.currentTimeMillis());
						earthSmash(player);
					}
					else{
						player.sendMessage(prefix + ChatColor.YELLOW  + "Your ground bash ability is still on cooldown!");
					}
				}
			}
		}
	}
	public void Runnable(final Player player){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable(){

			@Override
			public void run() {
				if(player.isSneaking() && player.getFoodLevel()<=19){
					player.setFoodLevel(player.getFoodLevel()+1);
					player.sendMessage(prefix + ChatColor.YELLOW + "Your natural balance passive has been activated! You have gained hunger!");
					Runnable(player);
				}
				
			}}, 15*20);
	}
	public void earthSmash(final Player player){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		final JumpEntityEffect jE = new JumpEntityEffect(Main.effectManager, player);
		final TraceEntityEffect tE = new TraceEntityEffect(Main.effectManager, player);
		jE.power = 1.2f;
		jE.start();
		tE.particle = ParticleEffect.FIREWORKS_SPARK;
		tE.start();
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){
			@Override
			public void run() {
				for(Player p: Bukkit.getOnlinePlayers()){
					if(p.getWorld()==player.getWorld()){
						if((Math.round(p.getLocation().getX())==Math.round(player.getLocation().getX()) || Math.round(p.getLocation().getX())==Math.round(player.getLocation().getX()+1) || Math.round(p.getLocation().getX())==Math.round(player.getLocation().getX()-1))
								&& (Math.round(p.getLocation().getZ())==Math.round(player.getLocation().getZ()) || Math.round(p.getLocation().getZ())==Math.round(player.getLocation().getZ()+1) || Math.round(p.getLocation().getZ())==Math.round(player.getLocation().getZ()-1)) && p!=player){
							if(p.getHealth()>=14){
								p.setHealth(player.getHealth()-14);
								p.sendMessage(prefix + ChatColor.YELLOW + "You have been hit by " + player.getName() + "'s ground bash!");
							}
							else{
								p.setHealth(0);
								p.sendMessage(prefix + ChatColor.YELLOW + "You have been hit by " + player.getName() + "'s ground bash!");
							}
						}
				}
				}
				jE.cancel();
				tE.cancel();
			
			}}, 20);
	}
}
