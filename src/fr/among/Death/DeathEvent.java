package fr.among.Death;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Flying;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.among.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;

public class DeathEvent implements Listener{
	
	private Main main;
	
	public DeathEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo;
        PacketPlayOutNamedEntitySpawn packetPlayOutNamedEntitySpawn;
        CraftPlayer craftPlayer = (CraftPlayer) event.getEntity();
        GameProfile gameProfile;
        Field field;
        EntityPlayer entityPlayer;
        Player p = event.getEntity();
        
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(event.getEntity().getPlayer().getCustomName());
        meta.setDisplayName(ChatColor.DARK_RED + "Report");
        event.getEntity().getWorld().dropItem(event.getEntity().getPlayer().getLocation(), skull);
        
        
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "!"  + ChatColor.GRAY + "] "+ ChatColor.RED + "Tu es mort !");
        p.playEffect(p.getLocation(), Effect.FLAME, 2);
        p.hidePlayer(p);
        p.setAllowFlight(true);

        event.getEntity().setHealth(event.getEntity().getMaxHealth());
        gameProfile = new GameProfile(UUID.randomUUID(), "_Death");
//        gameProfile = new GameProfile(UUID.randomUUID(), event.getEntity().getDisplayName() + "_Death");
        for (Map.Entry<String, Property> propertiesEntry : craftPlayer.getProfile().getProperties().entries())
            gameProfile.getProperties().put(propertiesEntry.getKey(), propertiesEntry.getValue());
        entityPlayer = new EntityPlayer(((CraftServer) main.getServer()).getServer(),
        ((CraftWorld) event.getEntity().getWorld()).getHandle(), gameProfile, new PlayerInteractManager(((CraftWorld) event.getEntity().getWorld()).getHandle()));
        entityPlayer.setLocation(event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(),
                event.getEntity().getLocation().getZ(), event.getEntity().getLocation().getYaw(), event.getEntity().getLocation().getPitch());
        packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        packetPlayOutNamedEntitySpawn = new PacketPlayOutNamedEntitySpawn(entityPlayer);
        for (Player player : main.getServer().getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfo);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutNamedEntitySpawn);
        }
    }

}
