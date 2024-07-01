package untitled.untitled.PlayerData;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerMap {
    private static final Map<Player, PlayerData> playerData = new HashMap<>();

    public PlayerMap() {

    }

    public static void addPlayer(Player player) {
        playerData.put(player, new PlayerData(player.getUniqueId(), "TNT", ""));
    }

    public static PlayerData getPlayerData(Player player) {
        return playerData.get(player);
    }

    public static void removePlayer(Player player) {
        playerData.remove(player);
    }

    public static void setPlayerData(Player player, PlayerData data) {
        if(playerData.containsKey(player)) {
            playerData.replace(player, data);
            return;
        }
        playerData.put(player, data);
    }

    public static Map<Player, PlayerData> getMap() {
        return playerData;
    }
}
