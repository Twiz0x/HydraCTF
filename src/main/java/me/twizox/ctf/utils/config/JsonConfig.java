package me.twizox.ctf.utils.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.twizox.ctf.HydraCTF;

import java.io.File;

public class JsonConfig {

    private final File file;
    private final ObjectMapper mapper;

    public JsonConfig(String name, ObjectMapper mapper) {
        this.file = new File(HydraCTF.getInstance().getDataFolder(), name + ".json");
        if (!file.exists()) {
            HydraCTF.getInstance().saveResource(name + ".json", false);
        }
        this.mapper = mapper;
    }

    public File getFile() {
        return file;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}
