/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/NextTilePanelMouseListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les événements de défilement de la molette de la souris sur le panneau de la prochaine tuile.
 * Elle permet de faire pivoter la tuile affichée à gauche ou à droite en fonction de la direction de défilement.
 */

package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Écouteur pour gérer les événements de la molette de la souris sur {@link NextTilePanel}.
 * Permet de faire pivoter la tuile affichée dans le panneau.
 */
public class NextTilePanelMouseListener implements MouseWheelListener {

    /**
     * Référence au panneau de la prochaine tuile.
     */
    private final NextTilePanel nextTilePanel;

    /**
     * Constructeur de l'écouteur de molette.
     *
     * @param nextTilePanel Le panneau de la prochaine tuile à contrôler.
     */
    public NextTilePanelMouseListener(NextTilePanel nextTilePanel) {
        this.nextTilePanel = nextTilePanel;
    }

    /**
     * Méthode appelée lorsque la molette de la souris est utilisée.
     * Permet de faire pivoter la tuile à gauche ou à droite en fonction de la direction de défilement.
     *
     * @param e L'événement de défilement de la molette.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            nextTilePanel.rotateRight(); // Rotation vers la droite
        } else {
            nextTilePanel.rotateLeft(); // Rotation vers la gauche
        }
    }
}
