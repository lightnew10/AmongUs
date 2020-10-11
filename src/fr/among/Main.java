package fr.among;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.among.Death.DeathEvent;
import fr.among.Utils.Skull;
import fr.among.game.AutoStart;
import fr.among.game.GameStats;
import fr.among.game.GameTime;
import fr.among.listeners.GamePlayerListener;

public class Main extends JavaPlugin implements Listener{
	
	private GameStats stats;
	private List<Player> players = new ArrayList<>();
	

	public static Main instance;
	public fr.among.Utils.Title title = new fr.among.Utils.Title();

	public void onEnable() {
		getLogger().info("Le Plugin AmonUs c'est lancer correctement !");
		setState(GameStats.WAIT);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GamePlayerListener(this), this);
		
		this.getCommand("skull").setExecutor(new Skull());
		Bukkit.getPluginManager().registerEvents(new DeathEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new GameTime(), this);
		Bukkit.getPluginManager().registerEvents(new AutoStart(this), this);
		return;
	}
	
	public void setState(GameStats stats) {
		this.stats = stats;
	}
	
	public boolean isState(GameStats stats) {
		return this.stats == stats;
	}

	@Override
	public void onDisable() {
		getLogger().info("Le Plugin AmonUs c'est arreter correctement !");
		return;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
}
