package me.twizox.ctf.team;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final String id, name;
    private final List<Player> players = new ArrayList<>();
    private final Flag flag;
    private final ChatColor color;
    private Location spawn;

    public Team(String id, String name, ChatColor color, Flag flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.color = color;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public String displayName() {
        return color + name;
    }

    public Flag getFlag() {
        return flag;
    }

    public Location getSpawn() {
        return spawn;
    }

    public ChatColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Team) {
            return ((Team) obj).getId().equals(id);
        }
        return false;
    }
}
