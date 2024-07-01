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

public class JoinGameCommand implements CommandExecutor {
    private final PlayerHandler playerHandler;
    private final GameManager gameManager;

    public JoinGameCommand(PlayerHandler playerHandler, GameManager gameManager) {
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
        if(!playerHandler.getGame()) {
            sender.sendMessage("No game is currently running!");
            return true;
        }

        Player player = (Player) sender;

        if(PlayerMap.getPlayerData(player) != null) {
            player.sendMessage("Already in a game!");
            return true;
        }
        // Add the player to the lastShootTimes map with an initial value
        //TODO: needs to access game manager to add player to the correct game
        if(!gameManager.findGame(player)) { //if cannot find game
            player.sendMessage("No game found!");
            return true;
        }
        sender.sendMessage("You have joined the game!");

        return true;
    }

}