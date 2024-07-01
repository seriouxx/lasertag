package untitled.untitled.Items;

import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IceGunItem extends ItemStack {
    public String itemName = "LaserGun";
    public IceGunItem() {
        super(Material.STICK);
        //itemName = laser;
        ItemMeta meta = super.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Ice Gun");
            List<String> lore = new ArrayList<>();
            lore.add("Right click to shoot snowball");
            meta.setLore(lore);
            this.setItemMeta(meta);
        }
    }

}
