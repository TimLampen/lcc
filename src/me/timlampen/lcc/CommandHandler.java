package me.timlampen.lcc;

import java.util.ArrayList;
import java.util.List;

import me.spoony.JSONChatLib.JSONChatClickEventType;
import me.spoony.JSONChatLib.JSONChatColor;
import me.spoony.JSONChatLib.JSONChatFormat;
import me.spoony.JSONChatLib.JSONChatHoverEventType;
import me.spoony.chatlib.ChatPart;
import me.spoony.chatlib.MessageSender;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import updates.Horses;
import updates.Horses.HorseTypes;

import com.rit.sucy.EnchantmentAPI;

import ca.wacos.nametagedit.NametagAPI;

public class CommandHandler implements CommandExecutor, Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore() || player.getName().equals("TimLampen")){
			firstMsg(player);
		}
	}
	
	ItemStack[] farmor = {getCustomItem(Items.fboots), getCustomItem(Items.fchest), getCustomItem(Items.flegs), getCustomItem(Items.fhelm)};
	ItemStack[] warmor = {getCustomItem(Items.wboots), getCustomItem(Items.wchest), getCustomItem(Items.wlegs), getCustomItem(Items.whelm)};
	ItemStack[] earmor = {getCustomItem(Items.eboots), getCustomItem(Items.echest), getCustomItem(Items.elegs), getCustomItem(Items.ehelm)};
	ItemStack[] marmor = {getCustomItem(Items.mboots), getCustomItem(Items.mchest), getCustomItem(Items.mlegs), getCustomItem(Items.mhelm)};
    public List<String> chosen = Main.getPlugin().getConfig().getStringList("Chosen-Players");
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		if(label.equalsIgnoreCase("lcc")){
			if(args.length==0){
				if(sender instanceof Player){
					Player player = (Player)sender;
					player.sendMessage(ChatColor.LIGHT_PURPLE + "-------------------" + ChatColor.YELLOW + "<(" + ChatColor.DARK_PURPLE + "LegitCityCraft" + ChatColor.YELLOW + ")>" + ChatColor.LIGHT_PURPLE + "-------------------");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "- /lcc remove <player>" + ChatColor.YELLOW + " - Allows target player to choose another guild" + ChatColor.RED + " [ADMIN ONLY]");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "- /lcc choose <aqua:terra:blaze>" + ChatColor.YELLOW + " - Allows player to choose guild");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "- /lcc teleport guild" + ChatColor.YELLOW + " - Allows player to choose guild");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "- /lcc give <player> <aqua:terra:blaze> <egg:armor:tool> <amount>" + ChatColor.YELLOW + " - Allows player to give target player an item" + ChatColor.RED + "" + ChatColor.BOLD + "[OWNER ONLY]");
					player.sendMessage(ChatColor.LIGHT_PURPLE + "- /lcc reload" + ChatColor.YELLOW + " - Allows player to reload config" + ChatColor.RED + " [ADMIN ONLY]");
				}
			}
			else if(args.length==1){
				if(args[0].equalsIgnoreCase("saddle")){
					if(!(sender instanceof Player)){
						console.sendMessage(prefix + ChatColor.RED + "Command Error: You cannot execute this command as console");
					}
					else{
						Player player = (Player)sender;
						Horses h = new Horses();
						player.getInventory().addItem(h.getHorseItem(HorseTypes.DIAMOND));
					}
				}
			}
			else if(args.length>=2){
				if(args[0].equalsIgnoreCase("teleport")){
					if(sender instanceof Player){
						final Player player = (Player)sender;
						if(args[1].equalsIgnoreCase("guild")){
							final Location from = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
							player.sendMessage(prefix + ChatColor.YELLOW + "Starting teleportation protocol");
							if(Main.perms.playerInGroup(player, "earthclan")){
								final Location earth = new Location(Bukkit.getWorld("world"), 530, 89 , -1217);
								player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleportation Initialized! Please wait 5 seconds");
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

									@Override
									public void run() {
										if(from.equals(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))){
											player.sendMessage(prefix + ChatColor.YELLOW + "Success! You have been teleported to the earth clan!");
											player.teleport(earth);
										}
										else{
											player.sendMessage(prefix + ChatColor.RED + "Movement Error: You cannot move while teleporting back!");
										}
									}}, 20*5);
							}
							if(Main.perms.playerInGroup(player, "waterclan")){
								final Location water = new Location(Bukkit.getWorld("world"), 862, 19 , 355);
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

									@Override
									public void run() {
										if(from.equals(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))){
											player.sendMessage(prefix + ChatColor.YELLOW + "Success! You have been teleported to the water clan!");
											player.teleport(water);
										}
										else{
											player.sendMessage(prefix + ChatColor.RED + "Movement Error: You cannot move while teleporting back!");
										}
									}}, 20*5);
							}
							if(Main.perms.playerInGroup(player, "fireclan")){
								final Location fire = new Location(Bukkit.getWorld("world"), -525, 210, -429);
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable(){

									@Override
									public void run() {
										if(from.equals(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))){
											player.sendMessage(prefix + ChatColor.YELLOW + "Success! You have been teleported to the fire clan!");
											player.teleport(fire);
										}
										else{
											player.sendMessage(prefix + ChatColor.RED + "Movement Error: You cannot move while teleporting back!");
										}
									}}, 20*5);
							}
							else{
								player.sendMessage(prefix + ChatColor.RED + "FATAL ERROR: IDK WHAT GUILD UR IN!!!");
							}
						}
					}
				}
				else if(args[0].equalsIgnoreCase("remove")){
					if(sender instanceof Player){
						Player player = (Player)sender;
						if(player.hasPermission("lcc.commands.remove")){
							if(player.getServer().getPlayer(args[1])!=null){
								Player target = player.getServer().getPlayer(args[1]);
								if(chosen.contains(target.getName())){
									chosen.remove(target.getName());
									player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + target.getName() + " can now chose another guild!");
									target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You can chose another guild!");
									NametagAPI.setPrefix(target.getName(), ChatColor.WHITE + "[" + ChatColor.YELLOW + "New" + ChatColor.WHITE + "] " + ChatColor.YELLOW + "");
								}
								else{
									player.sendMessage(prefix + ChatColor.RED + "Chosen Error: the target player can still choose a guild!");
								}
							}
							else{
								player.sendMessage(prefix + ChatColor.RED + "Chosen Error: The target player is not online!");
							}
						}
						else{
							player.sendMessage(prefix + ChatColor.RED + "Permission Error: You do not have sufficent permission!");
						}
					}
						else{
							if(Bukkit.getServer().getPlayer(args[1])!=null){
								Player target = Bukkit.getServer().getPlayer(args[1]);
								if(chosen.contains(target.getName())){
									chosen.remove(target.getName());
									console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + target.getName() + " can now chose another guild!");
									target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You can chose another guild!");
									NametagAPI.setPrefix(target.getName(), ChatColor.WHITE + "[" + ChatColor.YELLOW + "New" + ChatColor.WHITE + "] " + ChatColor.YELLOW + "");
								}
								else{
									console.sendMessage(prefix + ChatColor.RED + "Chosen Error: the target player can still choose a guild!");
								}
							}
							else{
								console.sendMessage(prefix + ChatColor.RED + "Chosen Error: The target player is not online!");
							}	
					}
				}
				else if(args[0].equalsIgnoreCase("choose")){
					Player player = (Player)sender;
					if(!(sender instanceof Player)){
						console.sendMessage(prefix + ChatColor.RED + "Command Error: You cannot execute this command as console");
					}
					else{
					if(chosen.contains(player.getName())){
						player.sendMessage(prefix + ChatColor.RED + "Chosen Error: You have already joined a GUILD! You cannot change!");
					}
					else{
						if(args[1].equalsIgnoreCase("blaze")){
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have joined the " + ChatColor.RED + "BLAZE clan" + ChatColor.LIGHT_PURPLE + "!");
							chosen.add(player.getName());
							Main.perms.playerRemoveGroup(player, "Waterclan");
							Main.perms.playerRemoveGroup(player, "Earthclan");
							Main.perms.playerAddGroup(player, "Fireclan");
							NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.RED + "Blaze" + ChatColor.YELLOW + "] " + ChatColor.RED + "");
					}
						else if(args[1].equalsIgnoreCase("terra")){
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have joined the " + ChatColor.GREEN + "TERRA clan" + ChatColor.LIGHT_PURPLE + "!");
							chosen.add(player.getName());
							Main.perms.playerAddGroup(player, "Earthclan");
							NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.GREEN + "Terra" + ChatColor.YELLOW + "] " + ChatColor.GREEN + "");
					}
						else if(args[1].equalsIgnoreCase("aqua")){
							player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have joined the " + ChatColor.BLUE + "AQUA clan" + ChatColor.LIGHT_PURPLE + "!");
							chosen.add(player.getName());
							Main.perms.playerAddGroup(player, "Waterclan");
							NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.BLUE + "Aqua" + ChatColor.YELLOW + "] " + ChatColor.BLUE + "");
					}
						else{
							player.sendMessage(ChatColor.RED + "Chosen Error: You cannot join that clan, choose from Fire, Earth, or Water");
						}
					}
					}
				}
				else if(args[0].equalsIgnoreCase("setname")){
					if(!(sender instanceof Player)){
						console.sendMessage(prefix + ChatColor.RED + "Command Error: You cannot execute this command as console");
					}
					else{
						Player player = (Player)sender;
						if(player.isOp()){
							if(Bukkit.getServer().getPlayer(args[2])!=null){
								Player target = Bukkit.getServer().getPlayer(args[2]);
								if(args[1].equalsIgnoreCase("admin")){
									player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have set " + target.getName() + "'s prefix to " + args[1] + "!");
									NametagAPI.setPrefix(target.getName(), ChatColor.GRAY + "[" + ChatColor.AQUA + "Admin" + ChatColor.GRAY + "] " + ChatColor.WHITE);
								}
								else if(args[1].equalsIgnoreCase("mod") || args[1].equalsIgnoreCase("moderator")){
									player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have set " + target.getName() + "'s prefix to " + args[1] + "!");
									NametagAPI.setPrefix(target.getName(), ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + ChatColor.WHITE);
								}
								else if(args[1].equalsIgnoreCase("help") || args[1].equals("helper")){
									player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have set " + target.getName() + "'s prefix to " + args[1] + "!");
									NametagAPI.setPrefix(target.getName(), ChatColor.GRAY + "[" + ChatColor.AQUA + "Helper" + ChatColor.GRAY + "] " + ChatColor.WHITE);
								}
								else{
									player.sendMessage(prefix + ChatColor.RED + "Command Error: Unkown rank " + args[1] + "!");
								}
							}
							else{
								player.sendMessage(prefix + ChatColor.RED + "Command Error: " + args[2] + " is not a valid player!");
							}
						}
						else{
							player.sendMessage(prefix + ChatColor.RED + "Command Error: You do not have the required permission");
						}
					}
				}
				else if(args[0].equalsIgnoreCase("give")){
					if(sender instanceof Player){
						Player player = (Player)sender;
						if(player.hasPermission("lcc.give")){
							if(Bukkit.getServer().getPlayer(args[1])!=null){
								Player target = Bukkit.getServer().getPlayer(args[1]);
								if(args[2].equalsIgnoreCase("blaze")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.GLASS, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: " + player.getName());
										}
										else{
											player.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(farmor);
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of blaze armor by: " + player.getName());
									}
									else{
										player.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("aqua")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.STONE, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: " + player.getName());
										}
										else{
											player.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(warmor);
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of aqua armor by: " + player.getName());
									}
									else{
										player.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("terra")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.LOG, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: " + player.getName());
										}
										else{
											player.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(earmor);
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of terra armor by: " + player.getName());
									}
									else{
										player.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("master")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.LOG, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.GLASS, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.STONE, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: " + player.getName());
										}
										else{
											player.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(marmor);
											player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of terra armor by: " + player.getName());
									}
									else{
										player.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else{
									player.sendMessage(doError(Error.SYNTAX_ERROR));
								}
							}
							else{
								player.sendMessage(doError(Error.NO_VALID_PLAYER));
							}
						}
						else{
							player.sendMessage(doError(Error.NO_PERM));
						}
					}
					else if(sender instanceof ConsoleCommandSender){
							if(Bukkit.getServer().getPlayer(args[1])!=null){
								Player target = Bukkit.getServer().getPlayer(args[1]);
								if(args[2].equalsIgnoreCase("blaze")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.GLASS, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: Console");
										}
										else{
											console.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(farmor);
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of blaze armor by: Console");
									}
									else{
										console.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("aqua")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.STONE, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: Console");
										}
										else{
											console.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(warmor);
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of aqua armor by: Console");
									}
									else{
										console.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("terra")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.LOG, 8*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: Console");
										}
										else{
											console.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(earmor);
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of terra armor by: Console");
									}
									else{
										console.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else if(args[2].equalsIgnoreCase("master")){
									if(args[3].equalsIgnoreCase("egg")){
										if(args[4] !=null){
											int amount = Integer.parseInt(args[4]);
											target.getInventory().addItem(new ItemStack(Material.LOG, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.GLASS, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.STONE, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK, 2*amount));
											target.getInventory().addItem(new ItemStack(Material.EGG, amount));
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given the resources to create " + amount + " " + args[2] + " egg(s) by: Console");
										}
										else{
											console.sendMessage(doError(Error.SYNTAX_ERROR));
										}
									}
									else if(args[3].equalsIgnoreCase("armor")){
											target.getInventory().addItem(marmor);
											console.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Process Completed");
											target.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You have been given a set of terra armor by: Console");
									}
									else{
										console.sendMessage(doError(Error.SYNTAX_ERROR));
									}
								}
								else{
									console.sendMessage(doError(Error.SYNTAX_ERROR));
								}
							}
							else{
								console.sendMessage(doError(Error.NO_VALID_PLAYER));
							}
					}
				}
				else if(args[0].equalsIgnoreCase("tutorial")){
					if(sender instanceof Player){
						Player player = (Player)sender;
								if(args[1].equalsIgnoreCase("2")){
									secondMsg(player);
								}
								if(args[1].equalsIgnoreCase("3")){
									thirdMsg(player);
								}
								if(args[1].equalsIgnoreCase("4")){
									fourthMsg(player);
								}
								if(args[1].equalsIgnoreCase("5")){
									fifthMsg(player);
								}
								else{
									player.sendMessage(prefix + ChatColor.RED + "Command Error: Unkown rank " + args[1] + "!");
								}
						}
					}
				}
			}
		return false;
	}
	public enum Error{
		NO_PERM, NO_VALID_PLAYER, NO_CONSOLE_EXECUTE, NO_VALID_CLAN, NO_VALID_RANK, ALREADY_CHOSEN, CAN_CHOSE_GUILD, MOVE_ERROR, FATAL_ERROR, SYNTAX_ERROR
	}
	
	public String doError(Error e){
		final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "LCC" + ChatColor.DARK_AQUA + "] ";
		String s = null;
		switch(e){
		case ALREADY_CHOSEN:
			break;
		case CAN_CHOSE_GUILD:
			break;
		case FATAL_ERROR:
			break;
		case MOVE_ERROR:
			break;
		case NO_CONSOLE_EXECUTE:
			break;
		case NO_PERM:
			s = prefix + ChatColor.RED + "Command Error: You do not have sufficient permission!";
			break;
		case NO_VALID_CLAN:
			break;
		case NO_VALID_PLAYER:
			s = prefix + ChatColor.RED + "Command Error: You cannot select an offline player!";
			break;
		case NO_VALID_RANK:
			break;
		case SYNTAX_ERROR:
			s = prefix + ChatColor.RED + "Command Error: Incorrect syntax, /lcc for more info";
			break;
			}
		return s;
	}
	public enum Items {
		fhelm, fchest, flegs, fboots, whelm, wchest, wlegs, wboots, ehelm, echest, elegs, eboots, mhelm, mchest, mlegs, mboots,
	}
	public void firstMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart("Welcome to the LegitCityCraft server! You may be wondering what makes this server unique from other generic faciton servers. This server combines element binding and"
				+ "a unique faction plugin (edited by a fellow plugin developer). Currently each element (terra, aqua, blaze) has two actives and one passive, increasing the complexity of PvP which this server is oriented towards. TimLampen"
				+ "is the owner and main developer for this server, and is always open to requests from players. What makes this server even more PvP based is that minerals can only be found in three locations, meaning you have to be sneaky"
				+ "when trying to get them. These locations are (Maybe right these down :))", JSONChatColor.YELLOW), new ChatPart("X:-1100 Z:-1100, X:1200 Z:1200, X:1300 Z:-1400", JSONChatColor.DARK_PURPLE, new JSONChatFormat[] {JSONChatFormat.BOLD}), new ChatPart(" (Click for next page)", JSONChatColor.YELLOW)
		.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Click to go to the next page").setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc tutorial 2"));
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
	}
	public void secondMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart("There are three elemets types: Terra, Aqua, and Blaze. Each element has its own special abilities, one passive skill and two active skills.", JSONChatColor.YELLOW), new ChatPart(" Terra: [Passive] "
				+ ChatColor.DARK_GREEN + "Natural Balance: When standing on grass you will gain .5 hunger bars every 15 seconds, and you will not take fall damage on grass. [Actives] Stone Arm: When you block you will take 40% less damage and get slowness 1 for 8 seconds. [20s Cooldown]"
				+ ChatColor.DARK_GREEN + "Ground Bash: When the player right clicks with a axe in hand, they will be shot into the air and deal 7 hearts of damage to surronding players. [360s Cooldown] (Click for next page)", JSONChatColor.DARK_GREEN)
				.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc tutorial 3").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Click to go to the next page"));
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
	}
	public void thirdMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart("Aqua: [Passive] Poseidon: When you are in water, you will recieve strength 1 and not take damage from water. [Actives] Frozen Tomb: When blocking at a player with a sword in hand, "
				+ "they will be imprisoned in a tomb of ice. [60s Cooldown] Brain Freeze: When you hit a player with a snowball it will give thm a nausea effect. [10s Cooldown] (Click for next page)", JSONChatColor.DARK_BLUE)
				.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc tutorial 4").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Click to go to the next page"));
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
	}
	public void fourthMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart(ChatColor.DARK_RED + "Blaze: [Passive] Lava Wader: While sneaking the player will gain fire resistance for 10 seconds. [Actives] Devouring Flames: When the player blocks with a sword, their sword will gain"
				+ "fire aspect 3 for 15 seconds. [120s Cooldown] Flaming Leap: When the player sneaks while on fire, the player will be launched forwards [10s Cooldown] (Click for next page)", JSONChatColor.DARK_RED)
				.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc tutorial 5").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Click to go to the next page"));
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
	}
	public void fifthMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart("Now it is time for you to choose your path, your destiny. Click on the guild that you want to join below.", JSONChatColor.YELLOW));
		MessageSender.sendMessage(player, new ChatPart(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[Terra]").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "THIS CHOICE IS FINAL").setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc choose terra"));
		MessageSender.sendMessage(player, new ChatPart(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Blaze]").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "THIS CHOICE IS FINAL").setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc choose blaze"));
		MessageSender.sendMessage(player, new ChatPart(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "[Aqua]").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "THIS CHOICE IS FINAL").setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/lcc choose aqua"));
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
	}

	public ItemStack getCustomItem(Items item) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> fboots = new ArrayList<String>();
		ArrayList<String> fchest = new ArrayList<String>();
		ArrayList<String> fhelm = new ArrayList<String>();
		ArrayList<String> flegs = new ArrayList<String>();
		ArrayList<String> eboots = new ArrayList<String>();
		ArrayList<String> echest = new ArrayList<String>();
		ArrayList<String> ehelm = new ArrayList<String>();
		ArrayList<String> elegs = new ArrayList<String>();
		ArrayList<String> wboots = new ArrayList<String>();
		ArrayList<String> wchest = new ArrayList<String>();
		ArrayList<String> whelm = new ArrayList<String>();
		ArrayList<String> wlegs = new ArrayList<String>();
		ArrayList<String> mboots = new ArrayList<String>();
		ArrayList<String> mchest = new ArrayList<String>();
		ArrayList<String> mhelm = new ArrayList<String>();
		ArrayList<String> mlegs = new ArrayList<String>();
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
		}
		return is;
	}
}
