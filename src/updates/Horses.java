package updates;
import java.util.ArrayList;
import java.util.HashMap;

import me.timlampen.lcc.Main;
import net.minecraft.server.v1_7_R4.AttributeInstance;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.GenericAttributes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.event.entity.EntityDismountEvent;

public class Horses implements Listener{
	final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
	public static final double NORMAL_SPEED = 0.2388842211270872;
	HashMap<String, Integer> dhorsespawn = new HashMap<>();
	HashMap<String, Integer> ghorsespawn = new HashMap<>();
	HashMap<String, Integer> ihorsespawn = new HashMap<>();
	
	@EventHandler
	public void onHorseDespawn(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Horse){
			Horse horse = (Horse) event.getEntity();
			horse.damage(horse.getMaxHealth());
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(dhorsespawn.containsKey(player.getName())){
			dhorsespawn.remove(player.getName());
		}
	}
	
	@EventHandler
	public void onDespawnHorse(EntityDismountEvent event){
		if(event.getDismounted() instanceof Player && event.getEntity() instanceof Horse){
			Horse horse = (Horse) event.getEntity();
			horse.damage(horse.getHealth());
		}
	}
	
	@EventHandler
	public void ondhorsespawn(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getItem()!=null && event.getItem().getItemMeta() !=null && event.getItem().getItemMeta().getDisplayName()!=null && event.getItem().getItemMeta().getLore()!=null){
				if(event.getItem().equals(getHorseItem(HorseTypes.DIAMOND))){
					if(!dhorsespawn.containsKey(player.getName()) && !ghorsespawn.containsKey(player.getName()) && !ihorsespawn.containsKey(player.getName())){
						DRunnable(player);
				}
					else{
						event.setCancelled(true);
						player.sendMessage(prefix + ChatColor.RED + "Spawn Error: You cannot spawn a horse while already spawning one!");
					}
				}
				else if(event.getItem().equals(getHorseItem(HorseTypes.GOLD))){
					if(!dhorsespawn.containsKey(player.getName()) && !ghorsespawn.containsKey(player.getName()) && !ihorsespawn.containsKey(player.getName())){
						DRunnable(player);
				}
					else{
						event.setCancelled(true);
						player.sendMessage(prefix + ChatColor.RED + "Spawn Error: You cannot spawn a horse while already spawning one!");
					}
				}
				else if(event.getItem().equals(getHorseItem(HorseTypes.IRON))){
					if(!dhorsespawn.containsKey(player.getName()) && !ghorsespawn.containsKey(player.getName()) && !ihorsespawn.containsKey(player.getName())){
						DRunnable(player);
				}
					else{
						event.setCancelled(true);
						player.sendMessage(prefix + ChatColor.RED + "Spawn Error: You cannot spawn a horse while already spawning one!");
					}
				}
			}
		}
	
	public enum HorseTypes{
		IRON, GOLD, DIAMOND
	}
	
	public ItemStack getHorseItem(HorseTypes item){
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> diamond = new ArrayList<>();
		ArrayList<String> gold = new ArrayList<>();
		ArrayList<String> iron = new ArrayList<>();
		switch(item){
		case DIAMOND:
			is = new ItemStack(Material.SADDLE, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Spawn Horse");
			diamond.add(ChatColor.DARK_PURPLE + "Horse Traits:");
			diamond.add(ChatColor.LIGHT_PURPLE + "- 5 Block Jump");
			diamond.add(ChatColor.LIGHT_PURPLE + "- 4b/s Speed");
			diamond.add(ChatColor.GRAY + "- Soulbound");
			im.setLore(diamond);
			is.setItemMeta(im);
			break;
		case GOLD:
			is = new ItemStack(Material.SADDLE, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Spawn Horse");
			gold.add(ChatColor.DARK_PURPLE + "Horse Traits:");
			gold.add(ChatColor.LIGHT_PURPLE + "- 4 Block Jump");
			gold.add(ChatColor.LIGHT_PURPLE + "- 3b/s Speed");
			gold.add(ChatColor.GRAY + "- Soulbound");
			im.setLore(gold);
			break;
		case IRON:
			is = new ItemStack(Material.SADDLE, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Spawn Horse");
			iron.add(ChatColor.DARK_PURPLE + "Horse Traits:");
			iron.add(ChatColor.LIGHT_PURPLE + "- 3 Block Jump");
			iron.add(ChatColor.LIGHT_PURPLE + "- 2b/s Speed");
			iron.add(ChatColor.GRAY + "- Soulbound");
			im.setLore(iron);
			break;
			}
		return is;
	}
	public void setHorseSpeed(Horse horse, int speed){
		AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d);
		attributes.setValue(speed);
	}
	public Double getHorseSpeed(Horse horse){
		AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d);
		return attributes.getValue();
	}
	public void playSound(Horse horse, EntityEffect e){
		horse.playEffect(e);
	}
	public void DRunnable(final Player player){
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

			@Override
			public void run() {
				if(dhorsespawn.containsKey(player.getName())){
					if(dhorsespawn.get(player.getName())==1){
						dhorsespawn.put(player.getName(), dhorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "4...");
						DRunnable(player);
					}
					else if(dhorsespawn.get(player.getName())==2){
						dhorsespawn.put(player.getName(), dhorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "3...");
						DRunnable(player);
					}
					else if(dhorsespawn.get(player.getName())==3){
						dhorsespawn.put(player.getName(), dhorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "2...");
						DRunnable(player);
					}
					else if(dhorsespawn.get(player.getName())==4){
						dhorsespawn.put(player.getName(), dhorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "1...");
						DRunnable(player);
					}
					else if(dhorsespawn.get(player.getName())==5){
						dhorsespawn.remove(player.getName());
						Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
						horse.setAdult();
						horse.setAgeLock(true);
						horse.setBreed(false);
						horse.setCanPickupItems(false);
						horse.setCarryingChest(false);
						horse.setCustomName(ChatColor.LIGHT_PURPLE + player.getName() + "'s Horse");
						horse.setCustomNameVisible(true);
						horse.setJumpStrength(2.0);
						horse.setOwner(player);
						horse.setPassenger(player);
						setHorseSpeed(horse, 5);
						horse.setTamed(true);
						horse.setVariant(Variant.HORSE);
					}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Fatal Error: HashMap dhorsespawn contains invalid integer! Contact TimLampen!");
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.YELLOW + "4...");
					dhorsespawn.put(player.getName(), 2);
					DRunnable(player);
				}
				
			}}, 20);
	}
	public void GRunnable(final Player player){
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

			@Override
			public void run() {
				if(ghorsespawn.containsKey(player.getName())){
					if(ghorsespawn.get(player.getName())==1){
						ghorsespawn.put(player.getName(), ghorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "4...");
						GRunnable(player);
					}
					else if(ghorsespawn.get(player.getName())==2){
						ghorsespawn.put(player.getName(), ghorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "3...");
						GRunnable(player);
					}
					else if(ghorsespawn.get(player.getName())==3){
						ghorsespawn.put(player.getName(), ghorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "2...");
						GRunnable(player);
					}
					else if(ghorsespawn.get(player.getName())==4){
						ghorsespawn.put(player.getName(), ghorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "1...");
						GRunnable(player);
					}
					else if(ghorsespawn.get(player.getName())==5){
						ghorsespawn.remove(player.getName());
						Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
						horse.setAdult();
						horse.setAgeLock(true);
						horse.setBreed(false);
						horse.setCanPickupItems(false);
						horse.setCarryingChest(false);
						horse.setCustomName(ChatColor.LIGHT_PURPLE + player.getName() + "'s Horse");
						horse.setCustomNameVisible(true);
						horse.setJumpStrength(1.5);
						horse.setOwner(player);
						horse.setPassenger(player);
						setHorseSpeed(horse, 4);
						horse.setTamed(true);
						horse.setVariant(Variant.HORSE);
					}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Fatal Error: HashMap ghorsespawn contains invalid integer! Contact TimLampen!");
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.YELLOW + "4...");
					ghorsespawn.put(player.getName(), 2);
				}
				
			}}, 20);
	}
	public void IRunnable(final Player player){
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

			@Override
			public void run() {
				if(ihorsespawn.containsKey(player.getName())){
					if(ihorsespawn.get(player.getName())==1){
						ihorsespawn.put(player.getName(), ihorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "4...");
						IRunnable(player);
					}
					else if(ihorsespawn.get(player.getName())==2){
						ihorsespawn.put(player.getName(), ihorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "3...");
						IRunnable(player);
					}
					else if(ihorsespawn.get(player.getName())==3){
						ihorsespawn.put(player.getName(), ihorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "2...");
						IRunnable(player);
					}
					else if(ihorsespawn.get(player.getName())==4){
						ihorsespawn.put(player.getName(), ihorsespawn.get(player.getName())+1);
						player.sendMessage(prefix + ChatColor.YELLOW + "1...");
						IRunnable(player);
					}
					else if(ihorsespawn.get(player.getName())==5){
						ihorsespawn.remove(player.getName());
						Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
						horse.setAdult();
						horse.setAgeLock(true);
						horse.setBreed(false);
						horse.setCanPickupItems(false);
						horse.setCarryingChest(false);
						horse.setCustomName(ChatColor.LIGHT_PURPLE + player.getName() + "'s Horse");
						horse.setCustomNameVisible(true);
						horse.setJumpStrength(2.0);
						horse.setOwner(player);
						horse.setPassenger(player);
						setHorseSpeed(horse, 5);
						horse.setTamed(true);
						horse.setVariant(Variant.HORSE);
					}
					else{
						player.sendMessage(prefix + ChatColor.RED + "Fatal Error: HashMap dhorsespawn contains invalid integer! Contact TimLampen!");
					}
				}
				else{
					player.sendMessage(prefix + ChatColor.YELLOW + "4...");
					ihorsespawn.put(player.getName(), 2);
				}
				
			}}, 20);
	}
}
