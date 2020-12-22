package me.shinybless.arena.Players;

import me.shinybless.arena.FastBoard.FastBoard;
import me.shinybless.arena.Main;
import me.shinybless.arena.OtherStuff.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Scoreboard {

    public static void update(FastBoard board){
        String Players = String.valueOf(Bukkit.getOnlinePlayers().size());
        String line1 = Main.config.getConfig().getString("scoreboard.line1");
        String line2 = Main.config.getConfig().getString("scoreboard.line2");
        String line3 = Main.config.getConfig().getString("scoreboard.line3");
        String line4 = Main.config.getConfig().getString("scoreboard.line4");
        String line5 = Main.config.getConfig().getString("scoreboard.line5");
        Player player = board.getPlayer();

        //OnlinePlayers
        assert line1 != null;
        if (line1.contains("%players%")){
            line1 = line1.replace("%players%", Players);
        }
        assert line2 != null;
        if (line2.contains("%players%")){
            line2 = line2.replace("%players%", Players);
        }
        assert line3 != null;
        if (line3.contains("%players%")){
            line3 = line3.replace("%players%", Players);
        }
        assert line4 != null;
        if (line4.contains("%players%")){
            line4 = line4.replace("%players%", Players);
        }
        assert line5 != null;
        if (line5.contains("%players%")){
            line5 = line5.replace("%players%", Players);
        }
        if (line1.contains("%oro%")){
            line1 = line1.replace("%oro%", String.valueOf(Main.getEconomy().getBalance(player)));
        }
        if (line2.contains("%oro%")){
            line2 = line2.replace("%oro%", String.valueOf(Main.getEconomy().getBalance(player)));
        }
        if (line3.contains("%oro%")){
            line3 = line3.replace("%oro%", String.valueOf(Main.getEconomy().getBalance(player)));
        }
        if (line4.contains("%oro%")){
            line4 = line4.replace("%oro%", String.valueOf(Main.getEconomy().getBalance(player)));
        }
        if (line5.contains("%oro%")){
            line5 = line5.replace("%oro%", String.valueOf(Main.getEconomy().getBalance(player)));
        }

        /*Oro
        if (line1.contains("%oro%")){
            line1 = line1.replace("%oro%", Main.getEconomy().format(Main.getEconomy().getBalance(board.getPlayer())));
        }
        if (line2.contains("%oro%")){
            line2 = line2.replace("%oro%", Main.getEconomy().format(Main.getEconomy().getBalance(board.getPlayer())));
        }
        if (line3.contains("%oro%")){
            line3 = line3.replace("%oro%", Main.getEconomy().format(Main.getEconomy().getBalance(board.getPlayer())));
        }
        if (line4.contains("%oro%")){
            line4 = line4.replace("%oro%", Main.getEconomy().format(Main.getEconomy().getBalance(board.getPlayer())));
        }
        if (line5.contains("%oro%")){
            line5 = line5.replace("%oro%", Main.getEconomy().format(Main.getEconomy().getBalance(board.getPlayer())));
        }*/

        //TotalKills
        if (line1.contains("%totalKills%")){
            line1 = line1.replace("%totalKills%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".kills"))));
        }
        if (line2.contains("%totalKills%")){
            line2 = line2.replace("%totalKills%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".kills"))));
        }
        if (line3.contains("%totalKills%")){
            line3 = line3.replace("%totalKills%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".kills"))));
        }
        if (line4.contains("%totalKills%")){
            line4 = line4.replace("%totalKills%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".kills"))));
        }
        if (line5.contains("%totalKills%")){
            line5 = line5.replace("%totalKills%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".kills"))));
        }

        //kills
        if (line1.contains("%kills%")){
            line1 = line1.replace("%kills%", String.valueOf(board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)));
        }
        if (line2.contains("%kills%")){
            line2 = line2.replace("%kills%", String.valueOf(board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)));
        }
        if (line3.contains("%kills%")){
            line3 = line3.replace("%kills%", String.valueOf(board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)));
        }
        if (line4.contains("%kills%")){
            line4 = line4.replace("%kills%", String.valueOf(board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)));
        }
        if (line5.contains("%kills%")){
            line5 = line5.replace("%kills%", String.valueOf(board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)));
        }

        //deaths
        if (line1.contains("%deaths%")){
            line1 = line1.replace("%deaths%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".deaths"))));
        }
        if (line2.contains("%deaths%")){
            line2 = line2.replace("%deaths%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".deaths"))));
        }
        if (line3.contains("%deaths%")){
            line3 = line3.replace("%deaths%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".deaths"))));
        }
        if (line4.contains("%deaths%")){
            line4 = line4.replace("%deaths%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".deaths"))));
        }
        if (line5.contains("%deaths%")){
            line5 = line5.replace("%deaths%", Objects.requireNonNull(Objects.requireNonNull(Main.config.getConfig().getString("stats.players." + board.getPlayer().getName() + ".deaths"))));
        }
        board.updateLines(
                (Utils.chat(line1)),
                (Utils.chat(line2)),
                (Utils.chat(line3)),
                (Utils.chat(line4)),
                (Utils.chat(line5)));
    }
}
