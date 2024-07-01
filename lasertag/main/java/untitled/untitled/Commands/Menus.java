package untitled.untitled.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import untitled.untitled.Untitled;
import java.util.ArrayList;
import java.util.List;


public class Menus implements Listener, CommandExecutor {
    private String menuName = "Menu";

    public Menus(Untitled plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().equals(menuName)) {
            return;
        }

        Bukkit.getLogger().info(menuName);

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        Bukkit.getLogger().info(player.getDisplayName() + " " + slot);
        switch(slot) {
            case 7:
                player.sendMessage("Joining BuildBattle");
                break;
            case 4:
                player.sendMessage("Joining SkyBlock");
                break;
            case 1:
                player.sendMessage("Joining PVP");
                break;
            default:
                break;
        }
        if(event.getView().getTitle().equals(menuName)) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run!");
            return true;
        }
        Player player = (Player) sender;
        Inventory inv = Bukkit.createInventory(player,9*1, menuName);

        inv.setItem(1, getItem(new ItemStack(Material.DIAMOND_SWORD), "&9PVP", "&aClick To Join", "&aBattle Awaits You!"));
        inv.setItem(4, getItem(new ItemStack(Material.DIRT), "&9SkyBlock", "&aClick To Join", "&aBattle Awaits You!"));
        inv.setItem(7, getItem(new ItemStack(Material.OAK_PLANKS), "&9Build Battle", "&aClick To Join", "&aBattle Awaits You!"));

        player.openInventory(inv);
        return true;
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for(String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
