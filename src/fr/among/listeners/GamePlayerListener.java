package fr.among.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.among.Main;
import fr.among.game.AutoStart;
import fr.among.game.GameStats;

public class GamePlayerListener implements Listener {

	private Main main;

	public GamePlayerListener(Main main) {
		this.main = main;
	}

	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Location loc = new Location(Bukkit.getWorld("amongus"), -1.358, 22.43316, 0.863, -1.1f, 5.6f);
		p.teleport(loc);
		p.getInventory().clear();
		
		if (!main.isState(GameStats.WAIT)) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("Le jeux a démaré !");
			e.setJoinMessage(null);
			return;
		}
		
		if (!main.getPlayers().contains(p)) main.getPlayers().add(p);
		p.setGameMode(GameMode.ADVENTURE);
		e.setJoinMessage("§f[§c+§f] " + p.getCustomName());
		
		if(main.isState(GameStats.WAIT) && main.getPlayers().size() == 2) {
			AutoStart start = new AutoStart(main);
			start.runTaskTimer(main, 0, 20);
			main.setState(GameStats.START);
		}
	}

	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(main.getPlayers().contains(p)) {
			main.getPlayers().remove(p);
		}
		e.setQuitMessage(null);
	}

}
