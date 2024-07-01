package untitled.untitled.Game;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import untitled.untitled.Items.Kits;
import untitled.untitled.PlayerData.PlayerData;
import untitled.untitled.PlayerData.PlayerMap;
import untitled.untitled.Untitled;
import untitled.untitled.handlers.ButtonKitHandler;
import untitled.untitled.handlers.PlayerHandler;

import java.util.ArrayList;
import java.util.Map;

import static org.bukkit.Bukkit.broadcastMessage;

public class LaserTagGame implements Listener {
    private final Untitled plugin;
    private boolean waitingForGameStart = false;
    private boolean gameStarted = false;
    private boolean isGameJoinable = false;
    private final PlayerHandler playerHandler;
    private final PlayerMap playerMap = new PlayerMap();
    final Location redSpawn = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    final Location blueSpawn = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    public LaserTagGame(Untitled plugin, PlayerHandler playerHandler) {
        this.plugin = plugin;
        this.playerHandler = playerHandler;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public void onGameStart() {
        waitingForGameStart = true;
        sortPlayers();
        teleportPlayers();
        //countdown
        waitingForGameStart = false;
        gameStarted = true;
        applyKits();
    }
    //TODO: make file not static
    public void addPlayerToGame(Player player) {
        PlayerMap.addPlayer(player);
    }
    public void removePlayerFromGame(Player player) {
        PlayerMap.removePlayer(player);
    }
    public void sortPlayers() {
        String currentTeam = "Red";
        for(Map.Entry<Player, PlayerData> entry : PlayerMap.getMap().entrySet()) {
            Player player = entry.getKey();
            PlayerData data = entry.getValue();
            data.setTeam(currentTeam);
            player.sendMessage("You are on the " + currentTeam + " team!");
            currentTeam = (currentTeam.equals("Red")) ? "Blue" : "Red";
        }
    }
    public void teleportPlayers() {
        for (Map.Entry<Player, PlayerData> entry : PlayerMap.getMap().entrySet()) {
            Player player = entry.getKey();
            PlayerData data = entry.getValue();
            player.sendMessage("Teleporting...");
            if(data.getTeam().equals("Red")) {
                player.teleport(redSpawn);
            } else if(data.getTeam().equals("Blue")) {
                player.teleport(blueSpawn);
            }

        }
    }

    public void applyKits() {
        for (Map.Entry<Player, PlayerData> entry : PlayerMap.getMap().entrySet()) {
            Player player = entry.getKey();
            PlayerData data = entry.getValue();
            PlayerInventory inventory = player.getInventory();
            inventory.clear();
            String kitName = data.getKit();
            Kits.equipGun(player, kitName);
            if (data.getTeam().equals("Red")) {
                Color color = Color.RED;
                Kits.equipLeatherArmor(player, color);
            } else if (data.getTeam().equals("Blue")) {
                Color color = Color.BLUE;
                Kits.equipLeatherArmor(player, color);
            }
            Kits.equipGun(player, kitName);
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if(waitingForGameStart){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void removeArmor(InventoryClickEvent event) {
        if(gameStarted) {
            event.setCancelled(true);
        }
    }

    public void startCountdown() {
        new BukkitRunnable() {
            int countdown = 20;

            @Override
            public void run() {
                if (countdown > 0) {
                    if (countdown == 20 || countdown == 10 || countdown <= 5) {
                        broadcastMessage("Game starting in " + countdown + " seconds.../joingame to join!");
                    }
                    countdown--;
                } else {
                    broadcastMessage("Game is starting!");
                    playerHandler.setGame(true);
                    isGameJoinable = false;
                    onGameStart();
                    broadcastMessage("test2");
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20); // 20 ticks = 1 second
    }

    public PlayerMap getPlayerMap() {
        return playerMap;
    }

    public boolean isGameJoinable() {
        return isGameJoinable;
    }

    public void setGameJoinable(boolean gameJoinable) {
        isGameJoinable = gameJoinable;
    }

    public boolean isPlayerInGame(Player player) {
        return PlayerMap.getMap().containsKey(player);
    }
}
