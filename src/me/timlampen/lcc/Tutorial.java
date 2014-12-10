package me.timlampen.lcc;

import me.spoony.JSONChatLib.JSONChatClickEventType;
import me.spoony.JSONChatLib.JSONChatHoverEventType;
import me.spoony.chatlib.ChatPart;
import me.spoony.chatlib.MessageSender;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Tutorial implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore()){}
	}
	
	public void firstMsg(Player player){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH + "+============================+");
		MessageSender.sendMessage(player, new ChatPart(ChatColor.YELLOW + "Welcome to the LegitCityCraft server! You may be wondering what makes this server unique from other generic faciton servers. This server combines element binding and"
				+ "a unique faction plugin (edited by a fellow plugin developer). Currently each element (fire, water, earth) has two actives and one passive, increasing the complexity of PvP which this server is oriented towards. TimLampen"
				+ "is the owner and main developer for this server, and is always open to requests from players.").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Click to go to the next page")
				.setClickEvent(JSONChatClickEventType.RUN_COMMAND, ""));
	}
}
