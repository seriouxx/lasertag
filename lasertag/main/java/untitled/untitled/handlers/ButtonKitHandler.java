package untitled.untitled.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import untitled.untitled.PlayerData.PlayerData;
import untitled.untitled.PlayerData.PlayerMap;
import untitled.untitled.Untitled;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ButtonKitHandler implements Listener {
    private final Untitled plugin;
    public ButtonKitHandler(Untitled plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onButtonClickAcacia(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerMap.getPlayerData(player);
        if(data == null) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material material = event.getClickedBlock().getType();
            if(material == null) {
                return;
            }
            if (material == Material.ACACIA_BUTTON) {
                data.setKit("TNT");
                player.sendMessage("Selected TNT Kit!");
                return;
            }
            if(material == Material.CRIMSON_BUTTON) {
                data.setKit("Laser");
                player.sendMessage("Selected Laser Kit!");
                return;
            }
            if(material == Material.BIRCH_BUTTON) {
                data.setKit("Ice");
                player.sendMessage("Selected Ice Kit!");
            }
        }
    }

}
