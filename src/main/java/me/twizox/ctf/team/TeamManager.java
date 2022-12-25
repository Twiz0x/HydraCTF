package me.twizox.ctf.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.utils.config.JsonConfig;
import org.bukkit.Material;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TeamManager {

    private final Map<String, Team> teams = new HashMap<>();
    private final JsonConfig jsonConfig;

    public TeamManager(JsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
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

    public boolean saveTeam(Team team) {

        if (team == null) return false;
        ObjectMapper mapper = jsonConfig.getMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonConfig.getFile(), Map.of(
                    team.getId(), team
            ));
        } catch (IOException e) {
            HydraCTF.getInstance().getLogger().severe("Failed to save team " + team.getId() + "!");
            throw new RuntimeException(e);
        }

        return true;

    }

    public void loadTeams() throws IOException {
        ObjectMapper mapper = jsonConfig.getMapper();
        Map<String, Team> teams = mapper.readerForMapOf(Team.class).readValue(jsonConfig.getFile());
        this.teams.putAll(teams);
        HydraCTF.getInstance().getLogger().info("Loaded " + teams.size() + " teams.");
    }

}
