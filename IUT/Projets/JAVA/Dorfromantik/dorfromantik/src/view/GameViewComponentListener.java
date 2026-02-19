/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/GameViewComponentListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe est un écouteur personnalisé pour gérer les événements de redimensionnement
 * de la fenêtre principale du jeu ({@link GameView}). Elle permet de repositionner dynamiquement
 * les composants de l'interface en fonction des nouvelles dimensions de la fenêtre.
 */

package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Écouteur pour les événements de redimensionnement de {@link GameView}.
 * Utilisé pour ajuster la position des composants lorsque la fenêtre est redimensionnée.
 */
public class GameViewComponentListener extends ComponentAdapter {

    /**
     * Référence à l'instance de {@link GameView} associée à cet écouteur.
     */
    private final GameView gameView;

    /**
     * Constructeur de l'écouteur. Associe cet écouteur à une instance de {@link GameView}.
     *
     * @param gameView L'instance de GameView à surveiller pour les événements de redimensionnement.
     */
    public GameViewComponentListener(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Méthode appelée lorsqu'un redimensionnement de la fenêtre est détecté.
     * Permet de repositionner dynamiquement les composants de l'interface.
     *
     * @param e L'événement de redimensionnement associé.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        gameView.positionComponents();
    }
}
