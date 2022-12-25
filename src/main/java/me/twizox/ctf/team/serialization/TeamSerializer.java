package me.twizox.ctf.team.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import me.twizox.ctf.team.Team;

import java.io.IOException;
import java.util.Map;

public class TeamSerializer extends StdSerializer<Team> {

    public TeamSerializer() {
        this(null);
    }

    public TeamSerializer(final Class<Team> t) {
        super(t);
    }

    @Override
    public void serialize(Team value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("color", value.getColor().name());
        gen.writeObjectField("spawn", value.getSpawn().serialize());

        Map<String, Object> location = value.getFlag().getSpawnLocation().serialize();
        location.remove("pitch");
        location.remove("yaw");

        gen.writeObjectField("flag", Map.of(
                "material", value.getFlag().getMaterial().name(),
                "location", location
        ));

        gen.writeEndObject();
    }

}
