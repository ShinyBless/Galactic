package me.shinybless.arena.Eventos;

import me.shinybless.arena.FastBoard.FastBoard;
import me.shinybless.arena.Main;
import me.shinybless.arena.OtherStuff.Utils;
import me.shinybless.arena.Players.Items;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Eventos implements Listener {
    private Main plugin;

    public String prefix = (ChatColor.GRAY + "(" + ChatColor.GREEN + "+" + ChatColor.GRAY + ") ");
    public String prefix2 = (ChatColor.GRAY + "(" + ChatColor.RED + "-" + ChatColor.GRAY + ") ");

    public Eventos(Main plugin){
        this.plugin=plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(prefix + ChatColor.YELLOW + player.getName());
        player.getInventory().clear();
        Items.Kit(event.getPlayer());
        Location spawn = new Location(player.getWorld(), -1.5, 100, -3.4);
        player.teleport(spawn);

        FastBoard board = new FastBoard(player);
        board.updateTitle(Utils.chat(plugin.getConfig().getString("scoreboard.scoreboardTitle")));
        Main.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onLeave (PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(prefix2 + ChatColor.YELLOW + player.getName());
        FastBoard board = Main.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    @EventHandler
    public void onDeath (PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        event.getDrops().clear();
        event.setDroppedExp(0);
        ItemStack Gap = new ItemStack(Material.GOLDEN_APPLE);
        event.getDrops().add(Gap);
        player.setStatistic(Statistic.PLAYER_KILLS, 0);

        event.setDeathMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " ha sido asesinado por " + ChatColor.RED + player.getKiller().getDisplayName());

        if (killer != null) {
            Main.getEconomy().depositPlayer(killer, 5);
            killer.sendMessage(ChatColor.GRAY + "Has asesinado a " + ChatColor.RED + player.getName() + ChatColor.GOLD + " 5g");
            killer.sendMessage(ChatColor.GRAY + "Tienes " + ChatColor.GOLD + Main.getEconomy().getBalance(player));
        }
        Location spawn = new Location(player.getWorld(), -1.5, 100, -3.4);
        player.teleport(spawn);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        Location spawn = new Location(player.getWorld(), -1.5, 100, -3.4);
        event.setRespawnLocation(spawn);
        Items.Kit(player);
    }

    @EventHandler
    public void Miners(BlockBreakEvent event){
        if (Main.Miners) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            Block block = event.getBlock();
            ItemStack emerald = new ItemStack(Material.EMERALD);
            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemStack gold = new ItemStack(Material.GOLD_INGOT);
            ItemStack iron = new ItemStack(Material.IRON_INGOT);
            ItemStack coal = new ItemStack(Material.COAL);

            if (block.getType() == (Material.EMERALD_ORE)) {
                event.setCancelled(true);
                block.setType(Material.LIME_CONCRETE);
                block.getDrops().clear();
                player.getInventory().addItem(emerald);
            }
            if (block.getType() == (Material.DIAMOND_ORE)) {
                event.setCancelled(true);
                block.setType(Material.LIGHT_BLUE_CONCRETE);
                block.getDrops().clear();
                player.getInventory().addItem(diamond);
            }
            if (block.getType() == (Material.GOLD_ORE)) {
                event.setCancelled(true);
                block.setType(Material.YELLOW_CONCRETE);
                block.getDrops().clear();
                player.getInventory().addItem(gold);
            }
            if (block.getType() == (Material.IRON_ORE)) {
                event.setCancelled(true);
                block.setType(Material.WHITE_CONCRETE);
                block.getDrops().clear();
                player.getInventory().addItem(iron);
            }
            if (block.getType() == (Material.COAL_ORE)) {
                event.setCancelled(true);
                block.setType(Material.BLACK_CONCRETE);
                block.getDrops().clear();
                player.getInventory().addItem(coal);
            }
        }else if (event.getPlayer().getGameMode() == GameMode.SURVIVAL){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void headeffect (PlayerInteractEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == (Utils.chat("&bGolden Head"))){
            event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 2));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1));
        }
    }
}
