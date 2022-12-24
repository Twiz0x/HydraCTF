package me.twizox.ctf.listener;

import me.twizox.ctf.listener.player.BlockListeners;
import me.twizox.ctf.listener.player.PlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ListenerRegistrer {

    public static List<Listener> listeners = List.of(
            new BlockListeners(),
            new PlayerListeners()
    );

    public static void register(Plugin plugin) {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }

}
