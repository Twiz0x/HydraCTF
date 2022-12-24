package me.twizox.ctf.kit;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SerializableAs("Kit")
public class Kit implements ConfigurationSerializable {

    private final String name;
    private final String description;
    private final ItemStack[] items;
    private final ItemStack[] armor;

    public Kit(String name, String description, ItemStack[] items, ItemStack[] armor) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.armor = armor;
    }

    public Kit(String name, String description, PlayerInventory inventory) {
        this(name, description, inventory.getContents(), inventory.getArmorContents());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void give(PlayerInventory inventory) {
        inventory.setContents(items);
        inventory.setArmorContents(armor);
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("description", description);
        HashMap<Integer, ItemStack> itemsMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                itemsMap.put(i, items[i]);
            }
        }
        map.put("items", itemsMap);

        map.put("armor", armor);
        return map;
    }

    public static Kit deserialize(Map<String, Object> map) {
        String name = (String) map.get("name");
        String description = (String) map.get("description");
        ItemStack[] items = new ItemStack[36];
        HashMap<Integer, ItemStack> itemsMap = (HashMap<Integer, ItemStack>) map.get("items");
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
            if (itemsMap.containsKey(i)) {
                items[i] = itemsMap.get(i);
            }
        }

        ItemStack[] armor = ((List<ItemStack>) map.get("armor")).toArray(new ItemStack[0]);
        return new Kit(name, description, items, armor);
    }

}