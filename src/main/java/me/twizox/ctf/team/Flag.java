package me.twizox.ctf.team;

import me.twizox.ctf.HydraCTF;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Flag {

    private Location spawn;
    private Player owner = null;
    private final Material material;
    private final Team team;

    public Flag(Location spawn, Team team, Material material) {
        this.spawn = spawn;
        this.team = team;
        this.material = material;
    }

    public Location getSpawnLocation() {
        return spawn;
    }

    public void setSpawnLocation(Location spawn) {
        this.spawn = spawn;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Team getTeam() {
        return team;
    }

    public Material getMaterial() {
        return material;
    }

    public void drop() {
        spawn.getBlock().setType(material);
        if (owner != null) {

            owner.getInventory().remove(material);

            var playerData = HydraCTF.getGame().getDataManager().getPlayer(owner);
            if (playerData != null) playerData.setOwnedFlag(null);

            owner = null;
        }
    }
}
