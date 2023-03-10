package me.twizox.ctf.listener.player;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.data.GamePlayer;
import me.twizox.ctf.game.Game;
import me.twizox.ctf.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        Game game = HydraCTF.getGame();

        final int stateOrdinal = game.getGameState().ordinal();
        if (stateOrdinal < GameState.IN_GAME.ordinal()) {
            event.setJoinMessage("§a" + player.getName() + "§7 a rejoint la partie.");
            game.getDataManager().getOrCreatePlayer(player).injectForLobby();
        } else if (stateOrdinal == GameState.IN_GAME.ordinal()) {
            // Spectator mode
            GamePlayer.injectForSpectator(player);
        } else {
            player.kickPlayer("§cLa partie est terminée.");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Game game = HydraCTF.getGame();
        event.setQuitMessage(null);

        if (game.getGameState().ordinal() < GameState.IN_GAME.ordinal()) {
            event.setQuitMessage("§c" + player.getName() + "§7 s'est déconnecté.");
        }

        game.getDataManager().removePlayer(player);
    }

}
