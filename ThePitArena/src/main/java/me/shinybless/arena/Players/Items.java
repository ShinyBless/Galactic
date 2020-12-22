package me.shinybless.arena.Players;

import me.shinybless.arena.Main;
import me.shinybless.arena.OtherStuff.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Items{
    public Plugin plugin = Main.getPlugin(Main.class);

    public static void Kit(Player p){
        ItemStack starter1 = new ItemStack (Material.CHAINMAIL_HELMET,1);
        ItemStack starter2 = new ItemStack (Material.IRON_CHESTPLATE,1);
        ItemStack starter3 = new ItemStack (Material.CHAINMAIL_LEGGINGS,1);
        ItemStack starter4 = new ItemStack (Material.IRON_BOOTS,1);
        ItemStack starter5 = new ItemStack (Material.SHIELD,1);
        ItemStack starter6 = new ItemStack (Material.IRON_SWORD,1);
        ItemStack starter7 = new ItemStack (Material.CROSSBOW,1);
        ItemStack starter8 = new ItemStack (Material.ARROW,16);

        ItemMeta stm1 = starter1.getItemMeta();
        ItemMeta stm2 = starter2.getItemMeta();
        ItemMeta stm3 = starter3.getItemMeta();
        ItemMeta stm4 = starter4.getItemMeta();
        ItemMeta stm5 = starter5.getItemMeta();
        ItemMeta stm6 = starter6.getItemMeta();
        ItemMeta stm7 = starter7.getItemMeta();
        stm1.setUnbreakable(true);
        stm2.setUnbreakable(true);
        stm3.setUnbreakable(true);
        stm4.setUnbreakable(true);
        stm5.setUnbreakable(true);
        stm6.setUnbreakable(true);
        stm7.setUnbreakable(true);
        starter1.setItemMeta(stm1);
        starter2.setItemMeta(stm2);
        starter3.setItemMeta(stm3);
        starter4.setItemMeta(stm4);
        starter5.setItemMeta(stm5);
        starter6.setItemMeta(stm6);
        starter7.setItemMeta(stm7);

        starter7.addEnchantment(Enchantment.PIERCING, 1);

        p.getInventory().setHelmet(starter1);
        p.getInventory().setChestplate(starter2);
        p.getInventory().setLeggings(starter3);
        p.getInventory().setBoots(starter4);
        p.getInventory().setItemInOffHand(starter5);
        p.getInventory().setItem(0, starter6);
        p.getInventory().setItem(1, starter7);
        p.getInventory().setItem(8, starter8);

        ItemStack h = new ItemStack (Material.GOLDEN_APPLE);
        ItemMeta hm = h.getItemMeta();

        hm.setDisplayName (Utils.chat("&bGolden Head"));

    }
    public static void MenuItems(){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
        ItemMeta swordmeta = sword.getItemMeta();
        swordmeta.setDisplayName("Equipamiento");
        ArrayList<String> swordlore = new ArrayList<>();
        swordlore.add(Utils.chat("&cAbre el menu de Equipamiento"));
        swordmeta.setLore(swordlore);
        swordmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sword.setItemMeta(swordmeta);

    }
}
