package controller;

import model.Board;
import view.GameView;

/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/controller/GameController.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Contrôleur principal du jeu, reliant la vue et le modèle. Cette classe
 * est responsable de la coordination entre l'état du plateau de jeu (modèle)
 * et l'affichage graphique (vue).
 */
public class GameController {
    private final Board board;
    private final GameView view;

    /**
     * Constructeur du contrôleur du jeu.
     *
     * @param board Le modèle du plateau de jeu, représentant la logique et l'état du jeu.
     * @param view  La vue graphique du jeu, permettant l'affichage et l'interaction avec l'utilisateur.
     */
    public GameController(Board board, GameView view) {
        this.board = board;
        this.view = view;
    }

    /**
     * Démarre le jeu en rendant la vue visible à l'utilisateur.
     */
    public void startGame() {
        view.setVisible(true);
    }
}
