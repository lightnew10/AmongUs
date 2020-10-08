package fr.among.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (sender instanceof Player)
			if (p.isOp())
				if (args.length == 1) {
					ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
					skullMeta.setOwner(args[0]);
					itemStack.setItemMeta(skullMeta);
					
					p.getInventory().addItem(itemStack);
					
				} else {
					if (args.length == 0) {
						p.sendMessage(ChatColor.RED + "Utiliser /skull <name>");
					}
				}
		return true;
	}

}
