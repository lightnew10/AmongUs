package fr.among;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.among.Death.DeathEvent;
import fr.among.Report.DefaultItem;
import fr.among.Report.PlayerInteract;
import fr.among.Report.PlayerMove;
import fr.among.Utils.Skull;

public class Main extends JavaPlugin implements Listener{

	public static Main instance;
	public fr.among.Utils.Title title = new fr.among.Utils.Title();

	public void onEnable() {
		getLogger().info("Le Plugin AmonUs c'est lancer correctement !");
		this.getCommand("skull").setExecutor(new Skull());
		Bukkit.getPluginManager().registerEvents(new DeathEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
		Bukkit.getPluginManager().registerEvents(new DefaultItem(), this);
		return;
	}

	@Override
	public void onDisable() {
		getLogger().info("Le Plugin AmonUs c'est arreter correctement !");
		return;
	}
	
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		e.setJoinMessage("Bienvenue");
	}
	
}
