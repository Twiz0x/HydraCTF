package me.twizox.ctf.data;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.game.Game;
import me.twizox.ctf.kit.Kit;
import me.twizox.ctf.team.Flag;
import me.twizox.ctf.team.Team;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayer {

    private final Player player;
    private final Game game = HydraCTF.getGame();
    private Flag ownedFlag;
    private Kit kit;
    private Team team;
    private int deaths, kills = 0;

    public GamePlayer(Player player) {
        this.player = player;
    }

    public void injectForFight() {
        if (team == null) {
            team = game.getTeamManager().getLessPlayerTeam();
            team.addPlayer(player);
        }
        player.teleport(team.getSpawn());

        if (kit == null) kit = game.getKitManager().getFirstKit();
        player.getInventory().setContents(kit.getItems());
        player.getEquipment().setArmorContents(kit.getArmor());

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public void injectForSpectator() {
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage("§7Une partie est en cours, vous êtes en mode spectateur.");
        player.teleport((Location) HydraCTF.getInstance().getConfig().get("spectator-location", player.getLocation()));
    }

    public void injectForPostGame(Team winner) {
        player.getInventory().clear();
        player.setHealth(20);
        player.setGameMode(GameMode.SPECTATOR);
        if (winner != null) {
            if (winner.equals(team)) {
                player.sendMessage("Bravo, vous avez gagné !");
            } else {
                player.sendMessage("Vous avez perdu, dommage !");
            }
        } else {
            player.sendMessage("La partie est terminée, égalité !");
        }
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setOwnedFlag(Flag flag) {
        this.ownedFlag = flag;
    }

    public Flag getOwnedFlag() {
        return ownedFlag;
    }

    public Player getPlayer() {
        return player;
    }

    public Kit getKit() {
        return kit;
    }

    public Team getTeam() {
        return team;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }

    public void addDeath() {
        deaths++;
    }

    public void addKill() {
        kills++;
    }

}
