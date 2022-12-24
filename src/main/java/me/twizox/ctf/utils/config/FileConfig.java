package me.twizox.ctf.utils.config;

import me.twizox.ctf.HydraCTF;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileConfig {

    private final YamlConfiguration config;
    private final File file;

    public FileConfig(String name) {
        this.file = new File(HydraCTF.getInstance().getDataFolder(), name + ".yml");
        if (!file.exists()) {
            HydraCTF.getInstance().saveResource(name + ".yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
