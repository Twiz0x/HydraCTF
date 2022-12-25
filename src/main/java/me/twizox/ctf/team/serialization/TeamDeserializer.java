package me.twizox.ctf.team.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.twizox.ctf.team.Flag;
import me.twizox.ctf.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.IOException;

public class TeamDeserializer extends StdDeserializer<Team> {


    protected TeamDeserializer(Class<?> vc) {
        super(vc);
    }

    protected TeamDeserializer() {
        this(null);
    }

    @Override
    public Team deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String id = node.get("id").asText();
        String name = node.get("name").asText();
        ChatColor color = ChatColor.valueOf(node.get("color").asText().toUpperCase());

        JsonNode spawnNode = node.get("spawn");
        Location spawn = Location.deserialize(new ObjectMapper().readerForMapOf(Object.class).readValue(spawnNode));

        JsonNode flagNode = node.get("flag");
        Location flagLocation = Location.deserialize(new ObjectMapper().readerForMapOf(Object.class).readValue(flagNode.get("location")));
        Material material = Material.valueOf(flagNode.get("material").asText().toUpperCase());

        Team team = new Team(id, name, color);
        team.setFlag(new Flag(flagLocation, team, material));
        team.setSpawn(spawn);

        return team;
    }
}
