package untitled.untitled.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import untitled.untitled.Game.GameManager;
import untitled.untitled.Game.LaserTagGame;
import untitled.untitled.PlayerData.PlayerData;
import untitled.untitled.PlayerData.PlayerMap;
import untitled.untitled.Untitled;

import java.util.*;

public class PlayerHandler implements Listener {
    private final Untitled plugin;
    private boolean isGame;
    private GameManager gameManager;

    public PlayerHandler(Untitled plugin, GameManager gameManager) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        isGame = false;
        this.gameManager=gameManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(Material.GRASS, 10);
        Inventory inv = player.getInventory();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("ok");
        item.setItemMeta(meta);

        inv.addItem(item);
        inv.setItem(5, item);
    }


    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        gameManager.handleDisconnect(event.getPlayer());
    }

    public boolean checkTime(Player player, long currentTime, long cooldown) {
        PlayerData data = PlayerMap.getPlayerData(player);
        if (data != null) {
            long lastShootTime = data.getLastShotTime();
            if (currentTime - lastShootTime >= cooldown) {
                return true;
            }
        }
        return false;
    }

    public boolean getGame() {
        return isGame;
    }


    public void setGame(boolean b) {
        isGame = b;
    }
}
