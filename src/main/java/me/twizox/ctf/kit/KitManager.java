package me.twizox.ctf.kit;

import me.twizox.ctf.utils.config.FileConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class KitManager {

    private final Map<String, Kit> kits = new HashMap<>();
    private final FileConfig config;

    public KitManager(FileConfig config) {
        this.config = config;
    }

    public boolean configHasKits() {
        return config.getConfig().contains("kits");
    }

    public void loadFromConfig() {
        if (!configHasKits()) return;
        for (String key : config.getConfig().getConfigurationSection("kits").getKeys(false)) {
            Kit kit = (Kit) config.getConfig().get("kits." + key);
            kits.put(key, kit);
        }
    }

    public void cache(Kit kit) {
        kits.put(kit.getName(), kit);
    }

    public void uncache(String name) {
        kits.remove(name);
    }

    public Kit getCachedKit(String name) {
        return kits.get(name);
    }

    public Kit getFirstKit() {
        return kits.values().stream().findFirst().orElse(null);
    }

    public Kit getRandomKit() {
        Random rand = new Random();
        return kits.values().stream().toList().get(rand.nextInt(kits.size()));
    }

    public void saveToFile(Kit kit) {
        config.getConfig().set("kits." + kit.getName().toLowerCase(), kit);
        config.save();
    }

    public Map<String, Kit> getKits() {
        return kits;
    }

}
