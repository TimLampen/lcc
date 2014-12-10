package me.timlampen.lcc;

import java.util.Map.Entry;
import java.util.Random;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.rit.sucy.EnchantmentAPI;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.JumpEntityEffect;
import de.slikey.effectlib.effect.ShieldEntityEffect;
import de.slikey.effectlib.effect.TraceEntityEffect;
import de.slikey.effectlib.effect.TurnPlayerEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class Main extends JavaPlugin implements Listener{
	private static Plugin plugin;
    public static Permission perms = null;
    public static Chat chat = null;
    public static EffectManager effectManager;
	public static ListMultimap<String, String> donequests = ArrayListMultimap.create();
    
	@Override
	public void onDisable(){
		CommandHandler ch = new CommandHandler();
    	for(Entry<String, String> entry : donequests.entries()) {
    	    getConfig().set("Done-Quests." + entry.getKey(), entry.getValue());
    	}
        this.getConfig().set("Chosen-Players", ch.chosen);
        saveConfig();
        effectManager.dispose();
		plugin = null;
	}
    @Override
	public void onEnable(){
        EffectLib lib = EffectLib.instance();
        effectManager = new EffectManager(lib);
		plugin = this;
		registerEvents();
		getCommand("lcc").setExecutor(new CommandHandler());
        setupChat();
        setupPermissions();
		}
	public static Plugin getPlugin(){
		return plugin;
	}
	    private boolean setupChat() {
	        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
	        chat = rsp.getProvider();
	        return chat != null;
	    }
	    private boolean setupPermissions()
	    {
	        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	        if (permissionProvider != null) {
	           perms = permissionProvider.getProvider();
	        }
	        return perms != null;
	    }
	    public void registerEvents(){
			getServer().getPluginManager().registerEvents(new CommandHandler(), this);
			getServer().getPluginManager().registerEvents(new Monsters(), this);
			getServer().getPluginManager().registerEvents(new quests.FightForMe(), this);
			getServer().getPluginManager().registerEvents(new quests.DIAMONDZ(), this);
			getServer().getPluginManager().registerEvents(new quests.ChickenHunter(), this);
			getServer().getPluginManager().registerEvents(new quests.DownDirty(), this);
			getServer().getPluginManager().registerEvents(new shop.DyeWoolClay(), this);
			getServer().getPluginManager().registerEvents(new shop.Blocks(), this);
			getServer().getPluginManager().registerEvents(new shop.Misc(), this);
			getServer().getPluginManager().registerEvents(new shop.Armor(), this);
			getServer().getPluginManager().registerEvents(new shop.Valuables(), this);
			getServer().getPluginManager().registerEvents(new shop.Natural(), this);
			getServer().getPluginManager().registerEvents(new shop.Mobs(), this);
			getServer().getPluginManager().registerEvents(new shop.Redstone(), this);
			getServer().getPluginManager().registerEvents(new shop.Food(), this);
			getServer().getPluginManager().registerEvents(new shop.Tools(), this);
			getServer().getPluginManager().registerEvents(new abilities.Aqua(), this);
			getServer().getPluginManager().registerEvents(new abilities.Terra(), this);
			getServer().getPluginManager().registerEvents(new abilities.Blaze(), this);
			getServer().getPluginManager().registerEvents(new updates.Horses(), this);
			getServer().getPluginManager().registerEvents(this, this);
	    }
	    Random random = new Random();
	    
		@EventHandler
		public void applyEffects(EntityDamageByEntityEvent event){
			int jump = random.nextInt(100)+1;
			if(event.getEntity() instanceof LivingEntity){
				if(event.getDamager() instanceof Player){
					Player player = (Player)event.getDamager();
				LivingEntity entity = (LivingEntity) event.getEntity();
				if(entity.getEquipment()!=null && entity.getEquipment().getBoots()!=null && entity.getEquipment().getBoots().getItemMeta()!=null && entity.getEquipment().getBoots().getItemMeta().getEnchants()!=null){
					if(armorEnchant("Fire Shield", entity)){
						final ShieldEntityEffect sE = new ShieldEntityEffect(effectManager, event.getEntity());
						sE.particles = 1;
						sE.radius = 3;
						sE.start();
						Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
							@Override
							public void run() {
								sE.cancel();
						
							}}, 50);
					}
					else if(armorEnchant("Fling", entity)){
						if(jump<=75){
						final JumpEntityEffect jE = new JumpEntityEffect(effectManager, player);
						final TraceEntityEffect tE = new TraceEntityEffect(effectManager, player);
						jE.power = 1.2f;
						//1.5 - 20 blocks - 4hearts
						// 1.2 - 10 blocks - 2hearts
						jE.start();
						tE.particle = ParticleEffect.EXPLODE;
						tE.start();
						Bukkit.getScheduler().runTaskLater(this, new Runnable(){
							@Override
							public void run() {
								jE.cancel();
								tE.cancel();
							
							}}, 20);
					}
					}
					else if(armorEnchant("Confusion", entity)){
						if(jump<=5){
							final TurnPlayerEffect ttE = new TurnPlayerEffect(effectManager, player);
							int turn = random.nextInt(80)+1;
							ttE.iterations = turn;
							ttE.start();
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1));
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
						}
					}
				}
			}
			}
		}
		public enum Items {
			Grout,
		}

		public ItemStack getCustomItem(Items item) {
			ItemStack is = null;
			ItemMeta im;
			switch (item) {
			case Grout:
				is = new ItemStack(Material.GRAVEL, 1);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.RESET + "Grout");
				is.setItemMeta(im);
				break;
			}
			return is;
		}
		public boolean armorEnchant(String enchant, LivingEntity entity){
			if(EnchantmentAPI.itemHasEnchantment(entity.getEquipment().getBoots(), enchant)
					&& EnchantmentAPI.itemHasEnchantment(entity.getEquipment().getLeggings(), enchant)
					&& EnchantmentAPI.itemHasEnchantment(entity.getEquipment().getChestplate(), enchant)
					&& EnchantmentAPI.itemHasEnchantment(entity.getEquipment().getHelmet(), enchant)){
				return true;
			}
			else{
			return false;
			}

		/*
		 * Teleport:
  Earth:
    World: world
    X: '530'
    Y: '89'
    Z: '-1217'
  Fire:
    World: world
    X: '-525'
    Y: '210'
    Z: '-429'
  Water:
    World: world
    X: '862'
    Y: '19'
    Z: '355'
*
		 */
		}
}
