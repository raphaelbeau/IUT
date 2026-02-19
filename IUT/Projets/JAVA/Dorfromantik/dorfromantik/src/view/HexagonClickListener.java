/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/HexagonClickListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les clics de souris sur le plateau de jeu ({@link GameView}).
 * Elle détecte les clics sur les hexagones et transmet les coordonnées cliquées
 * à la méthode correspondante de {@link GameView}.
 */

package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Écouteur de clics de souris pour le plateau de jeu hexagonal.
 * Permet de détecter les clics sur les hexagones et de transmettre
 * l'information à l'instance de {@link GameView}.
 */
public class HexagonClickListener extends MouseAdapter {

    /**
     * Référence à l'instance de {@link GameView} associée à cet écouteur.
     */
    private final GameView gameView;

    /**
     * Constructeur de l'écouteur de clics hexagonaux.
     *
     * @param gameView L'instance de {@link GameView} à laquelle transmettre les clics détectés.
     */
    public HexagonClickListener(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Méthode appelée lorsqu'un clic de souris est détecté.
     * Transmet les coordonnées du clic à {@link GameView#handleMouseClick(Point)}.
     *
     * @param e L'événement de clic de souris contenant les coordonnées du clic.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        gameView.handleMouseClick(e.getPoint());
    }
}
