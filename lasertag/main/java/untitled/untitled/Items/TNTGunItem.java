package untitled.untitled.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TNTGunItem extends ItemStack {
    public String itemName = "TNTGun";
    public TNTGunItem() {
        super(Material.STICK);
        //itemName = laser;
        ItemMeta meta = super.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("TNT Gun");
            List<String> lore = new ArrayList<>();
            lore.add("Right click to shoot tnt");
            meta.setLore(lore);
            this.setItemMeta(meta);
        }
    }

}
