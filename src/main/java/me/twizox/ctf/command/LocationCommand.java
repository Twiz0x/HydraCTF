package me.twizox.ctf.command;

import me.twizox.ctf.team.Team;
import org.bukkit.Material;
import org.bukkit.block.Block;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.BukkitCommandActor;
import revxrsal.commands.exception.CommandErrorException;
import revxrsal.commands.orphan.OrphanCommand;

import java.util.Set;

public class LocationCommand implements OrphanCommand {

    @Subcommand("hub")
    @Description("Définir le hub")
    public void hub(BukkitCommandActor actor) {
        if (actor.isConsole())
            throw new CommandErrorException("Vous devez être un joueur pour exécuter cette commande !");
        actor.reply("§aLe hub a été défini !");
    }

    @Subcommand("spawn")
    @Description("Définir le spawn d'une équipe")
    public void setSpawn(BukkitCommandActor actor, Team team) {
        if (actor.isConsole())
            throw new CommandErrorException("Vous devez être un joueur pour exécuter cette commande !");
        team.setSpawn(actor.getAsPlayer().getLocation());
        actor.reply("Vous avez défini le spawn des " + team.displayName() + "s");
    }

    @Subcommand("flag")
    @Description("Définir le drapeau d'une équipe en visant le bloc")
    public void setFlag(BukkitCommandActor actor, Team team) {
        if (actor.isConsole())
            throw new CommandErrorException("Vous devez être un joueur pour exécuter cette commande !");
        Block block = actor.getAsPlayer().getTargetBlock((Set<Material>) null, 10);
        if (block.getType() == Material.AIR)
            throw new CommandErrorException("Vous devez viser un bloc");

        team.getFlag().setSpawnLocation(block.getLocation());
        actor.reply("Vous avez défini le drapeau des " + team.displayName() + "s");
    }

}
