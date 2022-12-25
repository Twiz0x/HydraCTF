package me.twizox.ctf.kit.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import me.twizox.ctf.kit.Kit;

import java.io.IOException;

public class KitSerializer extends StdSerializer<Kit> {

    public KitSerializer() {
        this(null);
    }

    public KitSerializer(final Class<Kit> t) {
        super(t);
    }

    @Override
    public void serialize(Kit kit, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        gen.writeArrayFieldStart(kit.getName());

        //gen.writeNumberField("items", );

        gen.writeEndArray();

        gen.writeEndObject();

    }
}
