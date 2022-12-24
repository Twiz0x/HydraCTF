package me.twizox.ctf.command;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.game.Game;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import revxrsal.commands.help.CommandHelp;

@Command("ctf")
@Description("Commande principale du plugin")
@CommandPermission("ctf.command")
public class GameCommand {

    private final Game game = HydraCTF.getGame();

    @Subcommand("start")
    @Description("Démarrer la partie")
    @CommandPermission("ctf.admin")
    public void start(BukkitCommandActor actor) {
        if (game.start()) actor.reply("La partie a été démarrée !");
        else actor.reply("La partie est déjà démarrée !");
    }

    @Subcommand("stop")
    @Description("Arrêter la partie")
    @CommandPermission("ctf.admin")
    public void stop(BukkitCommandActor actor) {
        if (game.stopWithoutWinner()) actor.reply("La partie a été arrêtée !");
        else actor.reply("La partie n'est pas en cours !");
    }

    @Subcommand("help")
    @Description("Afficher l'aide")
    public void help(BukkitCommandActor actor, CommandHelp<String> helpEntries, @Default("1") int page) {
        helpEntries.paginate(page, 7).forEach(actor::reply);
    }

}
