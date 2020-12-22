package me.shinybless.arena.Habildades;

import me.shinybless.arena.Main;
import me.shinybless.arena.OtherStuff.Utils;
import me.shinybless.arena.Players.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Menu implements Listener {
    private Main plugin;

    public Menu(Main plugin) {
        this.plugin=plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);}

    public static Inventory UpgMenu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Upgrades");

    static {
        UpgMenu.setItem(1, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(2, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(3, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(4, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(5, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(6, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(7, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(8, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(10, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(11, book);
        UpgMenu.setItem(12, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(14, sword);
        UpgMenu.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(17, expbottle);
        UpgMenu.setItem(18, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(19, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(20, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(21, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(22, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(23, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(24, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(25, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(26, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        UpgMenu.setItem(27, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

    public static Inventory EquipMenu = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Equipamiento");
    static {
        EquipMenu.setItem(11, sword2));
        EquipMenu.setItem(20, axe);
        EquipMenu.setItem(29, trident);
        EquipMenu.setItem(14, bow));
        EquipMenu.setItem(23, crossbow);
        EquipMenu.setItem(15, arrow);
        EquipMenu.setItem(24, rocket);
        EquipMenu.setItem(17, helmet);
        EquipMenu.setItem(26, chestplate);
        EquipMenu.setItem(35, leggings);
        EquipMenu.setItem(44, boots);

    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK){
            if (block.getType() == Material.DIAMOND_BLOCK){
                player.openInventory(UpgMenu);
            }
        }
    }

    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        if (inventory.getSize() == (UpgMenu.getSize())) ;
        if (clicked.getType() == sword) {
            event.setCancelled(true);
            player.openInventory(EquipMenu);

        } else {
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
        }

        if (inventory.getSize() == (EquipMenu.getSize())) {
            if (clicked.getType() == sword2) {
                clicked.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
                // boolean true para que el kit tenga espada
            }
        }
    }

    public static void MenuItems2(){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
        ItemMeta swordmeta = sword.getItemMeta();
        swordmeta.setDisplayName("Equipamiento");
        ArrayList<String> swordlore = new ArrayList<>();
        swordlore.add(Utils.chat("&cAbre el menu de Equipamiento"));
        swordmeta.setLore(swordlore);
        swordmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sword.setItemMeta(swordmeta);

        ItemStack book = new ItemStack(Material.BOOK);
        book.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
        ItemMeta bookmeta = book.getItemMeta();
        bookmeta.setDisplayName("Equipamiento");
        ArrayList<String> booklore = new ArrayList<>();
        booklore.add(Utils.chat("&cAbre el menu de Habilidades"));
        bookmeta.setLore(booklore);
        bookmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        book.setItemMeta(bookmeta);

        ItemStack expbottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        expbottle.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
        ItemMeta expbottlemeta = expbottle.getItemMeta();
        expbottlemeta.setDisplayName("Equipamiento");
        ArrayList<String> expbottlelore = new ArrayList<>();
        expbottlelore.add(Utils.chat("&cAbre el menu de Mejoras"));
        expbottlemeta.setLore(expbottlelore);
        expbottlemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        expbottle.setItemMeta(expbottlemeta);
    }
    public static ItemStack SwordEquipamiento(){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
        ItemMeta swordmeta = sword.getItemMeta();
        swordmeta.setDisplayName("Equipamiento");
        ArrayList<String> swordlore = new ArrayList<>();
        swordlore.add(Utils.chat("&cAbre el menu de Equipamiento"));
        swordmeta.setLore(swordlore);
        swordmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sword.setItemMeta(swordmeta);
        return sword;
    }
}
