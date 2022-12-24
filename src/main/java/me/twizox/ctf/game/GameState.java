package me.twizox.ctf.game;

public enum GameState {
    WAITING("En attente"),
    STARTING("Démarrage"),
    IN_GAME("En jeu"),
    ENDING("Fin du jeu");

    GameState(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

}
