package me.timlampen.lcc;


import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.rit.sucy.EnchantmentAPI;

public class Monsters implements Listener{
	ArrayList<Location> giantdmg = new ArrayList<Location>();
	ItemStack[] farmor = {getCustomItem(Items.fboots), getCustomItem(Items.fchest), getCustomItem(Items.flegs), getCustomItem(Items.fhelm)};
	ItemStack[] warmor = {getCustomItem(Items.wboots), getCustomItem(Items.wchest), getCustomItem(Items.wlegs), getCustomItem(Items.whelm)};
	ItemStack[] earmor = {getCustomItem(Items.eboots), getCustomItem(Items.echest), getCustomItem(Items.elegs), getCustomItem(Items.ehelm)};
	ItemStack[] marmor = {getCustomItem(Items.mboots), getCustomItem(Items.mchest), getCustomItem(Items.mlegs), getCustomItem(Items.mhelm)};
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event){
		int random = new Random().nextInt(100)+1;
		if(event.getEntityType()==EntityType.ZOMBIE){
			if(random==1){
			Zombie entityc = (Zombie)event.getEntity();
			entityc.getEquipment().setItemInHand(getCustomItem(Items.esword));
			entityc.getEquipment().setArmorContents(earmor);
			setStats(entityc);
			entityc.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth Guardian");
		}
		}
		else if(event.getEntity().getType()==EntityType.SLIME){
			event.setCancelled(true);
		}
		
	}
	@EventHandler
	public void onPlayerFightMaster(EntityDamageByEntityEvent event){
		int random = new Random().nextInt(100)+1;
		if(event.getEntity() instanceof Giant && event.getDamager() instanceof Player){
			if(event.getCause()==DamageCause.PROJECTILE){
				event.setCancelled(true);
			}
			else{
			if(random<=14){
				for(Player player : Bukkit.getOnlinePlayers()){
					if(giantdmg.contains(player.getLocation())){
						player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, 11, 3);
						player.setHealth(player.getHealth()-5);
					}
				}
			}
		}
		}
	}
	@EventHandler
	public void onAnvilPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		if(event.getBlock()!=null&&event.getBlock().getType()!=null&&event.getBlock().getType()==Material.ANVIL){
			if(!player.isOp()){
			player.sendMessage(ChatColor.RED + "You cannot place anvils!");
			player.getInventory().remove(Material.ANVIL);
		}
		}
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		int random = new Random().nextInt(5)+1;
		Vector dir = event.getEntity().getLocation().getDirection();
		Vector vec1 = new Vector(dir.getZ() * 0.2D, 0.9D, dir.getZ() * 0.2D);
	    Vector vec2 = new Vector(dir.getX() * 0.2D, 0.9D, dir.getX() * 0.2D);
	    Vector vec3 = new Vector(dir.getX() * 0.2D, 0.9D, dir.getZ() * 0.2D);
	    Vector vec4 = new Vector(dir.getZ() * 0.2D, 0.9D, dir.getX() * 0.2D);
		Vector vec5 = new Vector(dir.getZ() * random, random, dir.getZ() * random);
	    Vector vec6 = new Vector(dir.getX() * random, random, dir.getX() * random);
	    Vector vec7 = new Vector(dir.getX() * random, random, dir.getZ() * random);
	    Vector vec8 = new Vector(dir.getZ() * random, random, dir.getX() * random);
	    	if(event.getEntity() instanceof Giant && event.getEntity().getCustomName().contains(ChatColor.stripColor("Gulum"))){
	    		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.EMERALD, 50)).setVelocity(vec1);
	    		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.EMERALD, 50)).setVelocity(vec2);
		    	event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.EMERALD, 50)).setVelocity(vec3);
				event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.EMERALD, 50)).setVelocity(vec4);
	    		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ROTTEN_FLESH, 10)).setVelocity(vec5);
	    		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ROTTEN_FLESH, 10)).setVelocity(vec6);
		    	event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ROTTEN_FLESH, 10)).setVelocity(vec7);
				event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ROTTEN_FLESH, 10)).setVelocity(vec8);
		}
	}
	@EventHandler
	public void onSpawn(PlayerInteractEvent event){
		Player player = event.getPlayer();
		String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(event.getAction()!=null && event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(event.getItem()!=null&&event.getItem().getItemMeta()!=null&&event.getItem().getType()!=null){
				if(event.getItem().getType()==Material.EGG){
					if(player.getExpToLevel()>=35){
						player.setExp(player.getExp()-1205);
						if(event.getItem().getItemMeta().getDisplayName()!=null && event.getItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Fire Monster"))){
							Bukkit.broadcastMessage(prefix + ChatColor.LIGHT_PURPLE + player.getName() + " has spawned a " + ChatColor.RED + "Fire Monster" + ChatColor.LIGHT_PURPLE + " at x: " + Math.round(player.getLocation().getX()) +  " y: " + Math.round(player.getLocation().getY()) + " z: " + Math.round(player.getLocation().getZ()) + "!");
							Blaze entitya = (Blaze) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.BLAZE);
							entitya.getEquipment().setArmorContents(farmor);
							setStats(entitya);
							entitya.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Demon");
							entitya.setPassenger(player);
							entitya.setTarget(player);
						}
						else if(event.getItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Water Monster"))){
							Bukkit.broadcastMessage(prefix + ChatColor.LIGHT_PURPLE + player.getName() + " has spawned a " + ChatColor.BLUE + "Water Monster" + ChatColor.LIGHT_PURPLE + " at x: " + Math.round(player.getLocation().getX()) +  " y: " + Math.round(player.getLocation().getY()) + " z: " + Math.round(player.getLocation().getZ()) + "!");
							IronGolem entityb = (IronGolem) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.IRON_GOLEM);
							entityb.getEquipment().setArmorContents(warmor);
							setStats(entityb);
							entityb.setCustomName(ChatColor.BLUE + "" + ChatColor.BOLD + "Water Sigil");
							entityb.setPassenger(player);
							entityb.setTarget(player);
							entityb.setHealth(20);
						}
						else if(event.getItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Earth Monster"))){
							Bukkit.broadcastMessage(prefix + ChatColor.LIGHT_PURPLE + player.getName() + " has spawned a " + ChatColor.GREEN + "Earth Monster" + ChatColor.LIGHT_PURPLE + " at x: " + Math.round(player.getLocation().getX()) +  " y: " + Math.round(player.getLocation().getY()) + " z: " + Math.round(player.getLocation().getZ()) + "!");
							Zombie entityc = (Zombie) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ZOMBIE);
							entityc.getEquipment().setItemInHand(getCustomItem(Items.esword));
							entityc.getEquipment().setArmorContents(earmor);
							setStats(entityc);
							entityc.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth Guardian");
							entityc.setPassenger(player);
							entityc.setTarget(player);
		}
						else if(event.getItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Master Monster"))){
							if(player.getExpToLevel()>=50){
								Bukkit.broadcastMessage(prefix + ChatColor.LIGHT_PURPLE + player.getName() + " has spawned a " + ChatColor.DARK_PURPLE + "Master Monster" + ChatColor.LIGHT_PURPLE + " at x: " + Math.round(player.getLocation().getX()) +  " y: " + Math.round(player.getLocation().getY()) + " z: " + Math.round(player.getLocation().getZ()) + "!");
								Giant entityd = (Giant) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.GIANT);
								entityd.getEquipment().setItemInHand(getCustomItem(Items.msword));
								entityd.getEquipment().setArmorContents(marmor);
								setMStats(entityd);
								entityd.setMaxHealth(1000.0);
								entityd.setHealth(1000.0);
								entityd.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Gulum, Master of All Guardians");
								entityd.setTarget(player);
							}
							else{
								player.sendMessage(prefix + ChatColor.RED + "You are not level 50, so you cannot spawn this creature!");
							}
						}
					}
					else{
						player.sendMessage(prefix + ChatColor.RED + "You are not level 35, so you cannot spawn this creature!");
					}
				}
			}
		}
	}
	public enum Items {
		fhelm, fchest, flegs, fboots, fsword, whelm, wchest, wlegs, wboots, wsword, ehelm, echest, elegs, eboots, esword, mhelm, mchest, mlegs, mboots, msword
	}

	public ItemStack getCustomItem(Items item) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> fboots = new ArrayList<String>();
		ArrayList<String> fchest = new ArrayList<String>();
		ArrayList<String> fhelm = new ArrayList<String>();
		ArrayList<String> flegs = new ArrayList<String>();
		ArrayList<String> fsword = new ArrayList<String>();
		ArrayList<String> eboots = new ArrayList<String>();
		ArrayList<String> echest = new ArrayList<String>();
		ArrayList<String> ehelm = new ArrayList<String>();
		ArrayList<String> elegs = new ArrayList<String>();
		ArrayList<String> esword = new ArrayList<String>();
		ArrayList<String> wboots = new ArrayList<String>();
		ArrayList<String> wchest = new ArrayList<String>();
		ArrayList<String> whelm = new ArrayList<String>();
		ArrayList<String> wlegs = new ArrayList<String>();
		ArrayList<String> wsword = new ArrayList<String>();
		ArrayList<String> mboots = new ArrayList<String>();
		ArrayList<String> mchest = new ArrayList<String>();
		ArrayList<String> mhelm = new ArrayList<String>();
		ArrayList<String> mlegs = new ArrayList<String>();
		ArrayList<String> msword = new ArrayList<String>();
		switch (item) {
		case fboots:
			is = new ItemStack(Material.IRON_BOOTS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Haunted Greaves");
			fboots.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Tainted by lost souls");
			im.setLore(fboots);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("FireShield").addToItem(is, 1);
			break;
		case fchest:
			is = new ItemStack(Material.IRON_CHESTPLATE, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Demon Wings");
			fchest.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Crippled and broken by the many wars");
			im.setLore(fchest);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("FireShield").addToItem(is, 1);
			break;
		case fhelm:
			is = new ItemStack(Material.IRON_HELMET, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Demon Crown");
			fhelm.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Worn by the king of demons");
			im.setLore(fhelm);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("FireShield").addToItem(is, 1);
			break;
		case flegs:
			is = new ItemStack(Material.IRON_LEGGINGS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Adamantine Greaves of Demons");
			flegs.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Since when to demons have legs?");
			im.setLore(flegs);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("FireShield").addToItem(is, 1);
			break;
		case fsword:
			is = new ItemStack(Material.IRON_SWORD, 1);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
			is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Demon Claw");
			fsword.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "One swipe can rip a head off");
			im.setLore(fsword);
			is.setItemMeta(im);
			break;
		case wboots:
			is = new ItemStack(Material.IRON_BOOTS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Striders of Depth");
			wboots.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Allows fast travel in the oceans");
			im.setLore(wboots);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Confusion").addToItem(is, 1);
			break;
		case wchest:
			is = new ItemStack(Material.IRON_CHESTPLATE, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Vine Robes");
			wchest.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Tathered vines hold the body in place");
			im.setLore(wchest);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Confusion").addToItem(is, 1);
			break;
		case whelm:
			is = new ItemStack(Material.IRON_HELMET, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.WATER_WORKER, 2);
			is.addUnsafeEnchantment(Enchantment.OXYGEN, 4);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Scuba Helmet");
			whelm.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Made in Atlantis");
			im.setLore(whelm);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Confusion").addToItem(is, 1);
			break;
		case wlegs:
			is = new ItemStack(Material.IRON_LEGGINGS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Trousers");
			wlegs.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "From the killed fishermen if the sea");
			im.setLore(wlegs);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Confusion").addToItem(is, 1);
			break;
		case wsword:
			is = new ItemStack(Material.IRON_SWORD, 1);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fist");
			wsword.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Such hurt, so bold");
			im.setLore(wsword);
			is.setItemMeta(im);
			break;
		case eboots:
			is = new ItemStack(Material.IRON_BOOTS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Muddy Boots");
			eboots.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Ripped and worn from years of travelling");
			im.setLore(eboots);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Fling").addToItem(is, 1);
			break;
		case echest:
			is = new ItemStack(Material.IRON_CHESTPLATE, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Leather Satchel");
			echest.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Hardened by years of wind");
			im.setLore(echest);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Fling").addToItem(is, 1);
			break;
		case ehelm:
			is = new ItemStack(Material.IRON_HELMET, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Hat");
			ehelm.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "UV ray protector");
			im.setLore(ehelm);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Fling").addToItem(is, 1);
			break;
		case elegs:
			is = new ItemStack(Material.IRON_LEGGINGS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Stained Shorts");
			elegs.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "You should really find a washroom");
			im.setLore(elegs);
			is.setItemMeta(im);
			EnchantmentAPI.getEnchantment("Fling").addToItem(is, 1);
			break;
		case esword:
			is = new ItemStack(Material.IRON_SWORD, 1);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Whip");
			esword.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Gives you feather falling 10 (Jump off a cliff)");
			im.setLore(esword);
			is.setItemMeta(im);
			break;
		case mboots:
			is = new ItemStack(Material.DIAMOND_BOOTS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Diamond Studded Boots");
			mboots.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "The cost is over 9000");
			im.setLore(mboots);
			is.setItemMeta(im);
			break;
		case mchest:
			is = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kings Chestplate");
			mchest.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Stare at the people below you");
			im.setLore(mchest);
			is.setItemMeta(im);
			break;
		case mhelm:
			is = new ItemStack(Material.DIAMOND_HELMET, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Golden Crown");
			mhelm.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "We ran out of diamonds");
			im.setLore(mhelm);
			is.setItemMeta(im);
			break;
		case mlegs:
			is = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
			is.addUnsafeEnchantment(Enchantment.THORNS, 1);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Striders");
			mlegs.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Vroom Vroom");
			im.setLore(mlegs);
			EnchantmentAPI.getEnchantment("Strider").addToItem(is, 1);
			is.setItemMeta(im);
			break;
		case msword:
			is = new ItemStack(Material.DIAMOND_SWORD, 1);
			is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
			is.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
			is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ban Hammer");
			msword.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "No humans were harmed in the making of this product");
			im.setLore(msword);
			is.setItemMeta(im);
			break;
		}
		return is;
	}
	public void setStats(LivingEntity entity){
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 0));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 0));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1));
		entity.getEquipment().setBootsDropChance(.2f);
		entity.getEquipment().setLeggingsDropChance(.15f);
		entity.getEquipment().setChestplateDropChance(.10f);
		entity.getEquipment().setHelmetDropChance(.2f);
		entity.getEquipment().setItemInHandDropChance(.15f);
	}
	public void setMStats(LivingEntity entity){
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 1));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 1));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999, 3));
		entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1));
		entity.getEquipment().setBootsDropChance(.4f);
		entity.getEquipment().setLeggingsDropChance(.3f);
		entity.getEquipment().setChestplateDropChance(.2f);
		entity.getEquipment().setHelmetDropChance(.4f);
		entity.getEquipment().setItemInHandDropChance(.3f);
	}
   

}
