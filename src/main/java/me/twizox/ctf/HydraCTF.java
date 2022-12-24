package me.twizox.ctf;

import me.twizox.ctf.command.CommandManager;
import me.twizox.ctf.game.Game;
import me.twizox.ctf.listener.ListenerRegistrer;
import org.bukkit.plugin.java.JavaPlugin;

public final class HydraCTF extends JavaPlugin {

    private static HydraCTF instance;
    private static Game game;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        game = new Game();

        commandManager = new CommandManager(this);
        commandManager.load();

        ListenerRegistrer.register(this);

        getLogger().info("HydraCTF has been enabled!");
    }

    @Override
    public void onDisable() {
        commandManager.unload();
        getLogger().info("HydraCTF has been disabled!");
    }

    public static HydraCTF getInstance() {
        return instance;
    }

    public static Game getGame() {
        return game;
    }
}
