package fr.among.Report;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
	
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(e.getFrom() != e.getTo()) {
			p.sendMessage("Tu as bouger");
		}
	}
}
