package untitled.untitled.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import untitled.untitled.Game.GameManager;
import untitled.untitled.Game.LaserTagGame;
import untitled.untitled.PlayerData.PlayerMap;
import untitled.untitled.handlers.ButtonKitHandler;
import untitled.untitled.handlers.PlayerHandler;

import java.util.HashMap;
import java.util.Map;

public class LeaveGameCommand implements CommandExecutor {
    private final PlayerHandler playerHandler;
    private final GameManager gameManager;

    public LeaveGameCommand(PlayerHandler playerHandler, GameManager gameManager) {
        this.playerHandler = playerHandler;
        this.gameManager = gameManager;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if(gameManager.handleDisconnect(player)) {
            sender.sendMessage("You have left the game!");
        }
        else {
            sender.sendMessage("Not in a game!");
        }

        return true;
    }


}