package me.twizox.ctf.game.runnable;

import me.twizox.ctf.game.Game;
import me.twizox.ctf.game.Settings;
import me.twizox.ctf.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public class LaunchRunnable extends BukkitRunnable {

    private final Game game;
    private AtomicInteger time = new AtomicInteger(5);

    public LaunchRunnable(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getGameState() != GameState.STARTING) {
            cancel();
            return;
        }

        if (game.getDataManager().getPlayers().size() < Settings.MIN_PLAYERS && !Settings.BYPASS_MIN_PLAYERS) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(ChatColor.RED + "Not enough players to start the game!");
            });
            cancel();
            return;
        }

        if (time.get() <= 0) {
            game.start();
            cancel();
            return;
        }

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setLevel(time.get());
            player.sendTitle(ChatColor.AQUA + time.toString(), "");
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, time.get());
        });


        time.getAndDecrement();
    }

}