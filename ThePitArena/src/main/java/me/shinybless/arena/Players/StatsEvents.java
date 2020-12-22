package me.shinybless.arena.Players;

import me.shinybless.arena.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class StatsEvents implements Listener {
    private Main plugin;

    public StatsEvents(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        String player = e.getPlayer().getName();
        if (!Main.config.getConfig().contains("stats.players." + player)) {
            Main.config.getConfig().set("stats.players." + player + ".name", player);
            Main.config.getConfig().set("stats.players." + player + ".kills", 0);
            Main.config.getConfig().set("stats.players." + player + ".deaths", 0);
            Main.config.saveConfig();
        }
    }

    @EventHandler
    public void death(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p.getKiller() != null) {
            if (p.getKiller().getType() == EntityType.PLAYER) {
                String player = p.getName();
                String killer = p.getKiller().getName();
                int kills = Main.config.getConfig().getInt("stats.players." + killer + ".kills");
                int deaths = Main.config.getConfig().getInt("stats.players." + player + ".deaths");

                Main.config.getConfig().set("stats.players." + killer + ".kills", kills + 1);
                Main.config.getConfig().set("stats.players." + player + ".deaths", deaths + 1);
                Main.config.saveConfig();
            }
        }
        int deaths = Main.config.getConfig().getInt("stats.players." + p.getName()+ ".deaths");
        Main.config.getConfig().set("stats.players." + p.getName() + ".deaths", deaths + 1);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Main.antilog.contains(p)) {
            int deaths = Main.config.getConfig().getInt("stats.players." + p.getName() + ".deaths");
            Main.config.getConfig().set("stats.players." + p.getName() + ".deaths", deaths + 1);
            p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.GOLDEN_APPLE));
            if (Main.lastDamageDealer.containsKey(p)){
                Player Killer = Main.lastDamageDealer.get(p);
                int kills = Main.config.getConfig().getInt("stats.players." + Killer + ".kills");
                Main.config.getConfig().set("stats.players." + Killer + ".kills", kills + 1);
            }
        }
    }

    @EventHandler
    public void onDmg(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player damaged = (Player) e.getEntity();

        if (damaged.getLastDamageCause() != null && damaged.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (damaged.getLastDamageCause().getEntity() instanceof Player || damaged.getLastDamageCause().getEntity() instanceof Projectile) {
                Player attacker = null;
                if (damaged.getLastDamageCause().getEntity() instanceof Player) {
                    attacker = (Player) e.getEntity().getLastDamageCause().getEntity();
                } else {
                    Projectile proj = (Projectile) damaged.getLastDamageCause().getEntity();
                    attacker = (Player) proj.getShooter();
                }
                assert attacker != null;
                Main.lastDamageDealer.put(damaged, attacker);
            }
        }
    }
}
