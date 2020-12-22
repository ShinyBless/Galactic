package me.shinybless.arena.Habildades;

import me.shinybless.arena.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

public class Habilidades implements CommandExecutor {
    private Main plugin;

    public Habilidades(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("rocket").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            if(cmd.getName().equalsIgnoreCase("rocket")){
                Player player = ((Player) sender).getPlayer();
                ItemStack i = new ItemStack(Material.FIREWORK_ROCKET, 3);
                FireworkMeta fm = (FireworkMeta) i.getItemMeta();
                List<Color> c = new ArrayList<Color>();
                c.add(Color.AQUA);
                FireworkEffect e = FireworkEffect.builder().flicker(true).withColor(c).withFade(c).with(FireworkEffect.Type.BALL_LARGE).trail(true).build();
                fm.addEffect(e);
                fm.setPower(5);
                i.setItemMeta(fm);
                player.getInventory().addItem(i);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No.");
            return true;
        }
        return false;
    }
}

