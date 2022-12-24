package me.twizox.ctf.game;

import me.twizox.ctf.HydraCTF;
import me.twizox.ctf.data.DataManager;
import me.twizox.ctf.data.GamePlayer;
import me.twizox.ctf.game.runnable.EndGameRunnable;
import me.twizox.ctf.kit.KitManager;
import me.twizox.ctf.team.Team;
import me.twizox.ctf.team.TeamManager;
import me.twizox.ctf.utils.config.FileConfig;

public class Game {

    private final DataManager dataManager;
    private final TeamManager teamManager;
    private final KitManager kitManager;
    private GameState gameState = GameState.WAITING;

    public Game() {
        this.dataManager = new DataManager();
        this.teamManager = new TeamManager(new FileConfig("teams"));
        this.kitManager = new KitManager(new FileConfig("kits"));
    }

    public boolean start() {
        if (gameState.ordinal() > GameState.STARTING.ordinal()) return false;
        this.gameState = GameState.IN_GAME;
        this.dataManager.getPlayers().values().forEach(GamePlayer::injectForFight);
        return true;
    }

    public boolean stopWithoutWinner() {
        return stop(null);
    }

    public boolean stop(Team winner) {
        if (gameState != GameState.IN_GAME) return false;
        this.gameState = GameState.ENDING;
        this.dataManager.getPlayers().values().forEach(gamePlayer -> gamePlayer.injectForPostGame(winner));
        new EndGameRunnable().runTaskLater(HydraCTF.getInstance(), 20 * 5);
        return true;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public GameState getGameState() {
        return gameState;
    }
}
