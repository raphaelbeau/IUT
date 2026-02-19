/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/BoardPanelComponentListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe écoute les événements de redimensionnement du panneau contenant le plateau de jeu.
 * Elle permet d'ajuster dynamiquement la taille du plateau graphique lorsque la fenêtre est redimensionnée.
 */

package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Écouteur pour les événements de redimensionnement de la fenêtre ou du composant.
 * Lorsqu'un redimensionnement est détecté, il ajuste la taille du {@link BoardPanel}.
 */
public class BoardPanelComponentListener extends ComponentAdapter {

    /**
     * Le panneau du plateau de jeu à ajuster lorsque la taille change.
     */
    private final BoardPanel boardPanel;

    /**
     * Constructeur de l'écouteur pour le {@link BoardPanel}.
     *
     * @param boardPanel Le panneau du plateau de jeu à surveiller.
     */
    public BoardPanelComponentListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    /**
     * Méthode appelée lorsqu'un composant est redimensionné.
     * Cette implémentation ajuste dynamiquement la taille du plateau.
     *
     * @param e L'événement de redimensionnement du composant.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        boardPanel.adjustBoardSize();
    }
}
