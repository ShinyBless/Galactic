package me.shinybless.arena;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.shinybless.arena.Eventos.Eventos;
import me.shinybless.arena.FastBoard.FastBoard;
import me.shinybless.arena.Habildades.Habilidades;
import me.shinybless.arena.OtherStuff.CombatLog;
import me.shinybless.arena.OtherStuff.ConfigClass;
import me.shinybless.arena.OtherStuff.Utils;
import me.shinybless.arena.Players.Items;
import me.shinybless.arena.Players.Scoreboard;
import me.shinybless.arena.Players.StatsEvents;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.security.acl.Permission;
import java.util.*;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Economy eco = null;
    private static Permission perms = null;
    private static Chat chat = null;
    public static ConfigClass config;

    public static Map<UUID, FastBoard> boards = new HashMap<>();
    public static ArrayList<Player> antilog = new ArrayList<Player>();
    public static Map<Player, Player> lastDamageDealer = new HashMap<>();
    public static ArrayList<Boolean> Events = new ArrayList<Boolean>();

    public static Boolean Miners = Boolean.FALSE;
    public static Boolean GhastHunter = Boolean.FALSE;

    public void onEnable() {
        config = new ConfigClass(this);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "shi");
        new Eventos(this);
        new Habilidades(this);
        new StatsEvents(this);
        new Commands(this);
        new StatsEvents(this);
        new CombatLog(this);

        LlavesCrafts();
        setupEconomy();
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                Scoreboard.update(board);
            }
        }, 0L, 20L);

        /*getServer().getScheduler().runTaskTimer(this, () -> {
            Events.add(true);
            for (int i = 0; i < Events.size(); i++){
                Events.add(false);
            }
            Collections.shuffle(Events);
        }, 0L, 1200L);*/
        getServer().getScheduler().runTaskTimer(this, () -> {
            Miners = Boolean.TRUE;
            Bukkit.broadcastMessage(Utils.chat("&1ThePit &7» &fEl evento &3Miners &fha &acomenzado"));
            World world = Bukkit.getWorld("Arena");
            Location min = new Location(world, 34, 49, -28);
            Location max = new Location(world, 82, 23, 20);
            for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
                for (int y = min.getBlockX(); y <= max.getBlockX(); y ++) {
                    for (int z = min.getBlockX(); z <= max.getBlockX(); z ++) {
                        Block b = world.getBlockAt(x, y, z);
                        if(b.getType() == Material.GREEN_CONCRETE){
                            b.setType(Material.EMERALD_ORE);
                        }
                        if(b.getType() == Material.LIGHT_BLUE_CONCRETE) {
                            b.setType(Material.DIAMOND_ORE);
                        }
                        if(b.getType() == Material.YELLOW_CONCRETE) {
                            b.setType(Material.GOLD_ORE);
                        }
                        if(b.getType() == Material.WHITE_CONCRETE) {
                            b.setType(Material.IRON_ORE);
                        }
                        if(b.getType() == Material.BLACK_CONCRETE) {
                            b.setType(Material.COAL_ORE);
                        }
                    }
                }
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    Miners = Boolean.FALSE;
                    Bukkit.broadcastMessage(Utils.chat("&1ThePit &7» &fEl evento &3Miners &fha finalizado"));
                }
            }, 18000L);
        }, 72000L, 72000L);
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "\n\nunu\n\n");
    }

    public void registerPitEvents(){
        Events.add(Miners);
    }

    public void LlavesCrafts(){
        ItemStack l1 = new ItemStack (Material.TRIPWIRE_HOOK,1);
        ItemMeta lm1 = l1.getItemMeta();

        lm1.setDisplayName(ChatColor.GRAY + "Llave Común");
        lm1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lm1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.YELLOW + "Usa esta llave para abrir una caja común.");
        lm1.setLore(lore1);
        l1.setItemMeta(lm1);

        l1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        ShapedRecipe lc1 = new ShapedRecipe(l1);
        lc1.shape(" a ","aaa"," a ");
        lc1.setIngredient('a', Material.COAL);

        getServer().addRecipe(lc1);

        ItemStack l2 = new ItemStack (Material.TRIPWIRE_HOOK,1);
        ItemMeta lm2 = l2.getItemMeta();

        lm2.setDisplayName(ChatColor.GRAY + "Llave Rara");
        lm2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lm2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.RED + "Usa esta llave para abrir una caja rara.");
        lm2.setLore(lore2);
        l2.setItemMeta(lm2);

        l2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        ShapedRecipe lc2 = new ShapedRecipe(l2);
        lc2.shape(" b ","bcb"," b ");
        lc2.setIngredient('b', Material.IRON_INGOT);
        lc2.setIngredient('c', Material.COAL_BLOCK);

        getServer().addRecipe(lc2);

        ItemStack l3 = new ItemStack (Material.TRIPWIRE_HOOK,1);
        ItemMeta lm3 = l3.getItemMeta();

        lm3.setDisplayName(ChatColor.GRAY + "Llave Épica");
        lm3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lm3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.RED + "Usa esta llave para abrir una caja épica.");
        lm3.setLore(lore3);
        l3.setItemMeta(lm3);

        l3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        ShapedRecipe lc3 = new ShapedRecipe(l3);
        lc3.shape(" d ","ded"," d ");
        lc3.setIngredient('d', Material.GOLD_INGOT);
        lc3.setIngredient('e', Material.IRON_BLOCK);

        getServer().addRecipe(lc3);

        ItemStack l4 = new ItemStack (Material.TRIPWIRE_HOOK,1);
        ItemMeta lm4 = l4.getItemMeta();

        lm4.setDisplayName(ChatColor.GRAY + "Llave Legendaria");
        lm4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lm4.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.RED + "Usa esta llave para abrir una caja legendaria.");
        lm4.setLore(lore4);
        l4.setItemMeta(lm4);

        l4.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        ShapedRecipe lc4 = new ShapedRecipe(l4);
        lc4.shape(" f ","fgf"," f ");
        lc4.setIngredient('f', Material.DIAMOND);
        lc4.setIngredient('g', Material.GOLD_BLOCK);

        getServer().addRecipe(lc4);

        ItemStack l5 = new ItemStack (Material.TRIPWIRE_HOOK,1);
        ItemMeta lm5 = l5.getItemMeta();

        lm5.setDisplayName(ChatColor.GRAY + "Llave Mítica");
        lm5.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lm5.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore5 = new ArrayList<>();
        lore5.add(ChatColor.RED + "Usa esta llave para abrir una caja mítica.");
        lm5.setLore(lore5);
        l5.setItemMeta(lm5);

        l5.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        ShapedRecipe lc5 = new ShapedRecipe(l5);
        lc5.shape(" h ","hih"," h ");
        lc5.setIngredient('h', Material.NETHERITE_SCRAP);
        lc5.setIngredient('i', Material.DIAMOND_BLOCK);

        getServer().addRecipe(lc5);
    }



    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return eco;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }


}
