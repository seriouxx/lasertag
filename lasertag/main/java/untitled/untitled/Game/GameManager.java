package untitled.untitled.Game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final List<LaserTagGame> laserTagGame = new ArrayList<>();

    public GameManager() {
    }
    public void addGame(LaserTagGame game) {
        laserTagGame.add(game);
    }

    public void removeGame(LaserTagGame game) {
        laserTagGame.remove(game);
    }

    public boolean findGame(Player player) {
        for (LaserTagGame game : laserTagGame) {
            if (game.isGameJoinable()) {
                game.addPlayerToGame(player);
                return true;
            }
        }
        return false;
    }

    public boolean handleDisconnect(Player player) {
        for(LaserTagGame game : laserTagGame) {
            if(game.isPlayerInGame(player)) {
                game.removePlayerFromGame(player);
                return true;
            }
        }
        return false;
    }
}
