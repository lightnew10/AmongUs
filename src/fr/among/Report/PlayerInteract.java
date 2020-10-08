package fr.among.Report;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class PlayerInteract implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
//		if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getAction() != Action.RIGHT_CLICK_AIR)
//			return;
		Player p = e.getPlayer();
		Action action = e.getAction();
		ItemStack it = e.getItem();

		if (it == null)
			return;

		if (it.getType() == Material.SKULL_ITEM) {
			if (action == Action.RIGHT_CLICK_BLOCK || e.getAction() != Action.RIGHT_CLICK_AIR
					|| e.getAction() != Action.LEFT_CLICK_BLOCK || e.getAction() != Action.LEFT_CLICK_AIR) {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "!" + ChatColor.GRAY + "] " + ChatColor.RED
						+ "Tu viens de report un joueur MORT !");

				p.sendTitle(ChatColor.RED + "NOUVEAU REPORT !", "");

				p.teleport(p);
				p.getInventory().clear();
			}
		}
	}
}