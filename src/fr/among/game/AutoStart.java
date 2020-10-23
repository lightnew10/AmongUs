package fr.among.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.among.Main;

public class AutoStart extends BukkitRunnable implements Listener {

	private int timer = 10;
	private Main main;

	public AutoStart(Main main) {
		this.main = main;
	}

	@Override
	public void run() {

		for (Player p : main.getPlayers()) {
			p.setLevel(timer);
		}

		if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "Lancement de la partie dans : " + ChatColor.YELLOW + timer
					+ ChatColor.GOLD + " s");
		}

		if (timer == 0) {
			Player p = (Player) main.getPlayers();
			Bukkit.broadcastMessage(
					ChatColor.RED + "Lancement de la partie !" + ChatColor.RED + "de Among" + ChatColor.WHITE + "Us");
			Location loc = new Location(Bukkit.getWorld("amongus"), -1.358, 22.43316, 0.863, -1.1f, 5.6f);
			p.teleport(loc);
			main.setState(GameStats.START);
			cancel();
		}

		timer--;

	}
}
