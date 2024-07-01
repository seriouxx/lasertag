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

public class IceHandler implements Listener {
    private final Untitled plugin;
    private final PlayerHandler playerHandler;

    private static final long ICE_SHOOT_COOLDOWN = 3000;


    public IceHandler(Untitled plugin, PlayerHandler playerHandler) {
        this.plugin = plugin;
        this.playerHandler = playerHandler;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void iceRightClick(PlayerInteractEvent event) {
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

        if(!name.equals("Ice Gun")) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        if(!playerHandler.checkTime(player, currentTime, ICE_SHOOT_COOLDOWN)) {
            player.sendMessage("Gun is on cooldown!");
            return;
        }

        if(event.getAction().toString().contains("RIGHT")) {
            long newShootTime = System.currentTimeMillis();
            data.setLastShotTime(newShootTime);
            Snowball snow = player.getWorld().spawn(player.getLocation().add(0, 1, 0), Snowball.class);
            snow.setMetadata("owner", new FixedMetadataValue(plugin, playerUUID));

            Vector direction = player.getLocation().getDirection();
            snow.setVelocity(direction.multiply(1.5));

        }
    }

    @EventHandler
    public void onEntityCollide(ProjectileHitEvent event) { //handles snowball collision
        Entity entity = event.getEntity();
        if (entity instanceof Snowball) {
            Snowball snowball = (Snowball) entity;
            Player owner = null;

            // Get the owner of the snowball from metadata
            if (snowball.hasMetadata("owner")) {
                List<MetadataValue> metadataList = snowball.getMetadata("owner");
                for (MetadataValue metadata : metadataList) {
                    if (metadata.getOwningPlugin().equals(plugin)) {
                        // Assuming the owner metadata is stored as a UUID
                        UUID ownerUUID = UUID.fromString(metadata.value().toString());
                        owner = Bukkit.getPlayer(ownerUUID);
                        break;
                    }
                }
            }
            if(owner == null) {
                return;
            }
            Entity hitEntity = event.getHitEntity();
            if(hitEntity != null) {
                if (hitEntity instanceof LivingEntity) {
                    ((org.bukkit.entity.LivingEntity)hitEntity).damage(10.0);
                    PotionEffect slownessEffect = new PotionEffect(PotionEffectType.SLOW, 100, 0);
                    ((LivingEntity) hitEntity).addPotionEffect(slownessEffect);
                }
            }
        }
    }
}