/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/MouseClickListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe écoute les clics de souris sur la fenêtre principale du jeu.
 * Elle transmet les coordonnées du clic à la méthode appropriée de {@link GameView}
 * lorsque l'utilisateur clique avec le bouton gauche.
 */

package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Écouteur de clics de souris pour le jeu.
 * Détecte les clics gauche et transmet l'information à l'instance de {@link GameView}.
 */
public class MouseClickListener extends MouseAdapter {

    /**
     * Référence à l'instance de {@link GameView} associée à cet écouteur.
     */
    private final GameView gameView;

    /**
     * Constructeur de l'écouteur de clics de souris.
     *
     * @param gameView L'instance de {@link GameView} où les clics seront gérés.
     */
    public MouseClickListener(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Méthode appelée lorsqu'un clic de souris est détecté.
     * Si le clic est effectué avec le bouton gauche, il transmet les coordonnées à {@link GameView}.
     *
     * @param e L'événement de clic de souris contenant les informations du clic.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Clic gauche
            gameView.handleMouseClick(e.getPoint());
        }
    }
}
