package me.twizox.ctf.data;

import me.twizox.ctf.game.Settings;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.WeakHashMap;

public class DamageData {

    // map player and time of last damage
    private final WeakHashMap<Player, Long> lastDamages = new WeakHashMap<>();

    public void clear() {
        lastDamages.clear();
    }

    public void addPlayer(Player player) {
        lastDamages.put(player, System.currentTimeMillis());
    }

    public void removePlayer(Player player) {
        lastDamages.remove(player);
    }

    public boolean contains(Player player) {
        return lastDamages.containsKey(player);
    }

    public long getDamageTime(Player player) {
        return lastDamages.get(player);
    }

    public List<Player> getOrderedDamagers() {

        // get list and remove when time difference is more than 8 and sort by shortest time
        long actual = System.currentTimeMillis();
        return lastDamages.keySet().stream()
                .filter(player -> actual - lastDamages.get(player) < Settings.LAST_DAMAGE_REMINDER*1000)
                .sorted((p1, p2) -> (int) (lastDamages.get(p1) - lastDamages.get(p2)))
                .toList();
    }

}

