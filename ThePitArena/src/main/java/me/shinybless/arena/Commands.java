package me.shinybless.arena;

import me.shinybless.arena.OtherStuff.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor{
    private Main plugin;

    public Commands(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("thepit").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("thepit.reload") && cmd.getName().equalsIgnoreCase("thepit")){
            if (args.length == 0){
                sender.sendMessage(Utils.chat("&c/thepit reload"));
                return true;
            }
            else if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    sender.sendMessage(Utils.chat("&aReloading Config file"));
                    Main.config.reloadConfig();
                }
                else if (args[0].equalsIgnoreCase("Miners")){

                    Main.Miners = Boolean.TRUE;
                    Bukkit.broadcastMessage(Utils.chat("&1ThePit &7» &fEl evento &3Miners &fha &acomenzado"));
                    World world = Bukkit.getWorld("Arena");
                    Location min = new Location(world, 39, 49, -24);
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

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Main.Miners = Boolean.FALSE;
                            Bukkit.broadcastMessage(Utils.chat("&1ThePit &7» &fEl evento &3Miners &fha finalizado"));
                        }
                    }, 1200L);
                }
            }
        }
        sender.sendMessage(Utils.chat("&cNo tienes permisos para ejecutar este comando"));
        return false;
    }
}