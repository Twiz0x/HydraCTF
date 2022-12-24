package me.twizox.ctf.team;

import me.twizox.ctf.utils.config.FileConfig;
import org.bukkit.Material;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TeamManager {

    private final Map<String, Team> teams = new HashMap<>();
    private final FileConfig fileConfig;

    public TeamManager(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    public void addTeam(Team team) {
        teams.put(team.getId(), team);
    }

    public void removeTeam(Team team) {
        teams.remove(team.getId());
    }

    public Team getTeam(String id) {
        return teams.get(id);
    }

    public Team getLessPlayerTeam() {
        Team team = null;
        for (Team t : teams.values()) {
            if (team == null || t.getPlayers().size() < team.getPlayers().size()) {
                team = t;
            }
        }
        return team;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public Collection<Team> getTeamsAsCollection() {
        return Collections.unmodifiableCollection(teams.values());
    }

    public Collection<String> getTeamIds() {
        return Collections.unmodifiableCollection(teams.keySet());
    }

    public Team fromMaterial(Material material) {
        return teams.values().stream().filter(team -> team.getFlag().getMaterial() == material).findFirst().orElse(null);
    }

    public void save() {
        fileConfig.save();
    }

}
