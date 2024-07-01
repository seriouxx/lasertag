package untitled.untitled.Items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Kits {

    public static void equipLeatherArmor(Player player, Color color) {
        ItemStack chestplate = createUnbreakableLeatherArmor(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = createUnbreakableLeatherArmor(Material.LEATHER_LEGGINGS);
        ItemStack boots = createUnbreakableLeatherArmor(Material.LEATHER_BOOTS);

        // Set the color of the leather armor
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setColor(color);
        chestplate.setItemMeta(chestplateMeta);

        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsMeta.setColor(color);
        leggings.setItemMeta(leggingsMeta);

        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(color);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }

    public static ItemStack createUnbreakableLeatherArmor(Material material) {
        ItemStack armorPiece = new ItemStack(material);
        ItemMeta meta = armorPiece.getItemMeta();
        if (meta instanceof LeatherArmorMeta) {
            LeatherArmorMeta leatherMeta = (LeatherArmorMeta) meta;
            leatherMeta.setUnbreakable(true);
            armorPiece.setItemMeta(leatherMeta);
        }
        return armorPiece;
    }

    public static void equipGun(Player player, String kitName) {
        switch (kitName) {
            case "Ice":
                ItemStack ice = new IceGunItem();
                player.getInventory().addItem(ice);
                break;
            case "TNT":
                ItemStack tnt = new TNTGunItem();
                player.getInventory().addItem(tnt);
                break;
            case "Laser":
                //ItemStack laser = new LaserGunItem();
                //player.getInventory().addItem(fire);
                break;
            default:
                break;
        }
    }
}
