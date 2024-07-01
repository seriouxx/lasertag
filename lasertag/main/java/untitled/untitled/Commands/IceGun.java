package untitled.untitled.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import untitled.untitled.Items.IceGunItem;

import java.util.UUID;

public class IceGun implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run!");
            return true;
        }
        ItemStack ice = new IceGunItem();
        Player player = (Player) sender;
        player.getInventory().addItem(ice);
        return true;

    }
}
