package me.twizox.ctf.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DataManager {

    private final HashMap<UUID, GamePlayer> players = new HashMap<>();

    public GamePlayer getOrCreatePlayer(UUID uuid) {
        GamePlayer gamePlayer = players.get(uuid);
        if (gamePlayer == null) {
            gamePlayer = new GamePlayer(Bukkit.getPlayer(uuid));
            players.put(uuid, gamePlayer);
        }
        return gamePlayer;
    }

    public GamePlayer getOrCreatePlayer(Player player) {
        return getOrCreatePlayer(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    public GamePlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public GamePlayer getPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public HashMap<UUID, GamePlayer> getPlayers() {
        return players;
    }

}
