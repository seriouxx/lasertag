package untitled.untitled;

import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.plugin.java.JavaPlugin;
import untitled.untitled.Commands.*;
import untitled.untitled.Game.GameManager;
import untitled.untitled.handlers.ButtonKitHandler;
import untitled.untitled.handlers.IceHandler;
import untitled.untitled.handlers.PlayerHandler;
import untitled.untitled.handlers.TNTHandler;

public final class Untitled extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World");
        //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "difficulty peaceful");
        GameManager gameManager = new GameManager();
        getCommand("fly").setExecutor(new Fly());
        getCommand("menu").setExecutor(new Menus(this));
        getCommand("tntgun").setExecutor(new TNTGun());
        getCommand("icegun").setExecutor((new IceGun()));
        PlayerHandler playerHandler = new PlayerHandler(this, gameManager);
        TNTHandler tntHandler = new TNTHandler(this, playerHandler);
        IceHandler iceHandler = new IceHandler(this, playerHandler);
        ButtonKitHandler buttonKitHandler = new ButtonKitHandler(this);
        getCommand("joingame").setExecutor((new JoinGameCommand(playerHandler, gameManager)));
        getCommand("leavegame").setExecutor((new LeaveGameCommand(playerHandler, gameManager)));
        getCommand("startgame").setExecutor((new CreateGameCommand(playerHandler, this, gameManager)));


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting Down");
    }
}
