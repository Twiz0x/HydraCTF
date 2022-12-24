package me.twizox.ctf.listener.player;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.data.GamePlayer;
import me.twizox.ctf.game.Game;
import me.twizox.ctf.game.GameState;
import me.twizox.ctf.team.Flag;
import me.twizox.ctf.team.Team;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Game game = HydraCTF.getGame();
        if (game.getGameState() != GameState.IN_GAME) {
            event.setCancelled(true);
            return;
        }

        Player player = event.getPlayer();
        GamePlayer gamePlayer = game.getDataManager().getPlayer(event.getPlayer());
        if (gamePlayer == null) {
            player.sendMessage("§cErreur: Impossible de trouver les données du joueur.");
            event.setCancelled(true);
            return;
        }

        Block block = event.getBlock();
        boolean isFlag = block.getState() instanceof Banner;

        if (isFlag) {
            Team team = game.getTeamManager().fromMaterial(block.getType());
            if (team == null || team.equals(gamePlayer.getTeam())) {
                event.setCancelled(true);
                return;
            }
            Flag flag = team.getFlag();
        }


    }

}
