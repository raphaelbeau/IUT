/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/GlobalMouseWheelListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe écoute les événements de la molette de la souris à l'échelle globale de la fenêtre.
 * Elle permet de faire pivoter la prochaine tuile affichée dans la vue du jeu, vers la droite ou la gauche,
 * en fonction du sens de défilement.
 */

package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Écouteur global pour la molette de la souris, permettant de faire pivoter la prochaine tuile.
 */
public class GlobalMouseWheelListener implements MouseWheelListener {

    /**
     * Panneau affichant la prochaine tuile à placer.
     */
    private final NextTilePanel nextTilePanel;

    /**
     * Constructeur de la classe GlobalMouseWheelListener.
     *
     * @param nextTilePanel Le panneau affichant la prochaine tuile à placer.
     */
    public GlobalMouseWheelListener(NextTilePanel nextTilePanel) {
        this.nextTilePanel = nextTilePanel;
    }

    /**
     * Méthode appelée lorsqu'un événement de molette de souris est détecté.
     * En fonction du sens de rotation, la tuile est pivotée vers la droite ou vers la gauche.
     *
     * @param e L'événement de molette de souris.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            nextTilePanel.rotateRight();
        } else {
            nextTilePanel.rotateLeft();
        }
    }
}
