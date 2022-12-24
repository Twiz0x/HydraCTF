package me.twizox.ctf.game.runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class EndGameRunnable extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(""));
        Bukkit.shutdown();
    }

}
