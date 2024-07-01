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
import untitled.untitled.PlayerData.PlayerData;
import untitled.untitled.PlayerData.PlayerMap;
import untitled.untitled.Untitled;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public class TNTHandler implements Listener {
    private final Untitled plugin;
    private final PlayerHandler playerHandler;

    private static final long TNT_SHOOT_COOLDOWN = 3000;


    public TNTHandler(Untitled plugin, PlayerHandler playerHandler) {
        this.plugin = plugin;
        this.playerHandler = playerHandler;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void laserRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        PlayerData data = PlayerMap.getPlayerData(player);
        if(data == null) {
            return;
        }
        String name = "";
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            name = meta.getDisplayName();
        }
        if(!name.equals("TNT Gun")) {
            return; //refactor later to include more types of guns/wands whatever
        }

        long currentTime = System.currentTimeMillis();
        if(!playerHandler.checkTime(player, currentTime, TNT_SHOOT_COOLDOWN)) {
            player.sendMessage("Gun is on cooldown!");
            return;
        }

        if(event.getAction().toString().contains("RIGHT")) {
            long newShootTime = System.currentTimeMillis();
            data.setLastShotTime(newShootTime);
            TNTPrimed tnt = player.getWorld().spawn(player.getLocation().add(0, 1, 0), TNTPrimed.class);
            tnt.setMetadata("owner", new FixedMetadataValue(plugin, playerUUID));

            Vector direction = player.getLocation().getDirection();
            tnt.setVelocity(direction.multiply(1.5)); // Adjust the multiplier to change the speed of the

            new BukkitRunnable() {
                private Vector lastVelocity = tnt.getVelocity();

                @Override
                public void run() {
                    // Check if the TNT has stopped moving (velocity is negligible)
                    if (tnt.getVelocity().lengthSquared() < 0.01) {
                        // Explode the TNT
                        tnt.getWorld().createExplosion(tnt.getLocation(), 4.0f);
                        tnt.remove();
                        cancel(); // Stop the task
                    } else {
                        // Update the last known velocity
                        lastVelocity = tnt.getVelocity();
                    }
                }
            }.runTaskTimer(plugin, 0, 5); // Check e


        }
    }
}