/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/BoardMouseController.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe gérant les interactions de la souris sur le plateau de jeu.
 * Elle permet de déplacer le plateau en cliquant et glissant avec le bouton droit
 * et de gérer les clics avec le bouton gauche pour effectuer des actions dans la vue du jeu.
 */

package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 * Contrôleur de la souris pour gérer les interactions utilisateur sur le plateau de jeu.
 * Permet le déplacement du plateau à l'aide du bouton droit et l'exécution d'actions
 * spécifiques avec le bouton gauche.
 */
public class BoardMouseController extends MouseAdapter {
    private final BoardPanel boardPanel;
    private final GameView gameView;
    private Point lastDragPoint;

    /**
     * Constructeur de la classe BoardMouseController.
     *
     * @param boardPanel Le panneau représentant le plateau de jeu.
     * @param gameView   La vue principale du jeu.
     */
    public BoardMouseController(BoardPanel boardPanel, GameView gameView) {
        this.boardPanel = boardPanel;
        this.gameView = gameView;
    }

    /**
     * Gère l'événement déclenché lorsque la souris est pressée.
     * Initialise le point de départ du glissement si le bouton droit est utilisé.
     *
     * @param e L'événement souris correspondant.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { 
            lastDragPoint = e.getPoint();
        }
    }

    /**
     * Gère l'événement déclenché lors d'un glissement de la souris.
     * Permet de déplacer le centre virtuel du plateau en fonction du mouvement.
     *
     * @param e L'événement souris correspondant.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0 && lastDragPoint != null) {
            Point currentPoint = e.getPoint();
            int dx = currentPoint.x - lastDragPoint.x;
            int dy = currentPoint.y - lastDragPoint.y;
            boardPanel.moveVirtualCenter(dx, dy);
            lastDragPoint = currentPoint;
        }
    }

    /**
     * Gère l'événement déclenché lorsque la souris est relâchée.
     * Réinitialise le point de glissement si le bouton droit est relâché.
     *
     * @param e L'événement souris correspondant.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastDragPoint = null;
        }
    }

    /**
     * Gère l'événement déclenché lorsqu'un clic est effectué avec la souris.
     * Le bouton gauche déclenche une action définie dans la vue du jeu.
     *
     * @param e L'événement souris correspondant.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { 
            gameView.handleMouseClick(e.getPoint());
        }
    }
}
