package fr.among.Report;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.among.Main;
import net.md_5.bungee.api.ChatColor;

public class DefaultItem implements Listener{
	
	public static Main instance;
	
	public static void items(Player p) {

		ItemStack paper = new ItemStack(Material.PAPER);
		ItemMeta paper_meta = paper.getItemMeta();
		paper_meta.setDisplayName(ChatColor.BLUE + "Report");
		paper.setItemMeta(paper_meta);
		
		//p.getInventory().clear();
		p.getInventory().setItem(4, paper);

	}
	
//	public void onJoin(PlayerJoinEvent e) {
//		Player p = e.getPlayer();
//		
//		for (int i = 0; i < 200; i++)
//			p.sendMessage("");
//		
//		p.sendTitle(ChatColor.RED + "Among " + ChatColor.WHITE + "Us", ChatColor.GOLD + "");
//		
//		e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "+" + ChatColor.GRAY + "] " + p.getDisplayName());
//	}
//	
//	public void onQuit(PlayerQuitEvent e) {
//		Player p = e.getPlayer();
//		
//		e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "-" + ChatColor.GRAY + "] " + p.getDisplayName());
//	}
}
