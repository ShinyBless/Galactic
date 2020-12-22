package me.shinybless.arena.OtherStuff;

import me.shinybless.arena.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatLog implements Listener {
    private Main plugin;

    public CombatLog(Main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAntiLogDmg(EntityDamageByEntityEvent event) {
        if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player))) {
            final Player player = (Player)event.getEntity();
            final Player target = (Player)event.getDamager();
            if ((!Main.antilog.contains(player)) && (!Main.antilog.contains(target))) {
                Main.antilog.add(player);
                Main.antilog.add(target);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        if ((Main.antilog.contains(player)) && (Main.antilog.contains(target))) {
                            Main.antilog.remove(player);
                            Main.antilog.remove(target);
                        }
                    }
                }, 400L);
            }
        }
    }
}
