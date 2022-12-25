package me.twizox.ctf.command;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.team.Team;
import org.bukkit.plugin.Plugin;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.exception.CommandErrorException;
import revxrsal.commands.orphan.OrphanCommand;
import revxrsal.commands.orphan.Orphans;

import java.util.Arrays;
import java.util.Locale;

public class CommandManager {

    private final BukkitCommandHandler handler;
    private boolean registered = false;

    public CommandManager(Plugin plugin) {
        handler = BukkitCommandHandler.create(plugin);
        handler.setMessagePrefix("§b§lHydra§f§lCTF §8» §7");
        handler.failOnTooManyArguments();

        handler.registerValueResolver(Team.class, context -> {
            String value = context.pop();
            Team team = HydraCTF.getGame().getTeamManager().getTeam(value);
            if (team == null)
                throw new CommandErrorException("L'équipe '" + value + "' n'existe pas !");
            return team;
        });

        handler.setHelpWriter((command, actor) -> {
            if (!command.hasPermission(actor)) return null;
            return String.format("%s %s - &f%s", command.getPath().toRealString(), command.getUsage(), command.getDescription());
        });
        handler.setLocale(Locale.FRENCH);

    }

    public void load() {
        if (registered)
            throw new IllegalStateException("Commands are already registered!");
        handler.register(new GameCommand());
        registerCommands("location", new LocationCommand());
        handler.getAutoCompleter().registerParameterSuggestions(Team.class,
                SuggestionProvider.of(HydraCTF.getGame().getTeamManager().getTeamIds()));
        registered = true;
    }

    public void unload() {
        handler.unregisterAllCommands();
        registered = false;
    }

    private void registerCommands(String subpath, OrphanCommand... commands) {
        Orphans path = Orphans.path("ctf");
        if (subpath != null && !subpath.isEmpty()) {
            path = Orphans.path("ctf " + subpath);
        }
        handler.register(Arrays.stream(commands).map(path::handler).toArray());
    }

    public boolean areCommandsRegistered() {
        return registered;
    }


}
