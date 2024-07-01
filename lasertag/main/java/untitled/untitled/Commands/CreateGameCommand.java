package untitled.untitled.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import untitled.untitled.Game.GameManager;
import untitled.untitled.Game.LaserTagGame;
import untitled.untitled.Untitled;
import untitled.untitled.handlers.PlayerHandler;

import static org.bukkit.Bukkit.broadcastMessage;

public class CreateGameCommand implements CommandExecutor {
    private final PlayerHandler playerHandler;
    private final LaserTagGame laserTagGame;
    private final GameManager gameManager;

    public CreateGameCommand(PlayerHandler playerHandler, Untitled plugin, GameManager gameManager) {
        this.playerHandler = playerHandler;
        laserTagGame = new LaserTagGame(plugin, playerHandler);
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        gameManager.addGame(laserTagGame);
        playerHandler.setGame(true);
        laserTagGame.startCountdown();
        broadcastMessage("test1");
        return true;
    }

}
