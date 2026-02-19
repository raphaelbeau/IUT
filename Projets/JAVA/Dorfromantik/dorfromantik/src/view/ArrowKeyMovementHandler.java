/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ArrowKeyMovementHandler.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe gérant les mouvements avec les touches fléchées sur le clavier.
 * Elle permet de déplacer le centre virtuel du plateau dans différentes
 * directions en réponse aux touches directionnelles.
 */

package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'événements clavier pour les touches fléchées.
 * Cette classe déplace le centre virtuel du plateau en fonction de la touche
 * fléchée pressée.
 */
public class ArrowKeyMovementHandler implements KeyListener {
    private final BoardPanel boardPanel;

    /**
     * Constructeur de la classe ArrowKeyMovementHandler.
     *
     * @param boardPanel Le panneau du plateau qui sera déplacé en fonction des
     *                   touches directionnelles.
     */
    public ArrowKeyMovementHandler(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    /**
     * Méthode appelée lorsqu'une touche du clavier est pressée.
     * Déplace le centre virtuel du plateau selon la touche fléchée pressée.
     *
     * @param e L'événement KeyEvent associé à la pression d'une touche.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int moveAmount = 20; 

        switch (keyCode) {
            case KeyEvent.VK_UP:
                boardPanel.moveVirtualCenter(0, -moveAmount);
                break;
            case KeyEvent.VK_DOWN:
                boardPanel.moveVirtualCenter(0, moveAmount);
                break;
            case KeyEvent.VK_LEFT:
                boardPanel.moveVirtualCenter(-moveAmount, 0);
                break;
            case KeyEvent.VK_RIGHT:
                boardPanel.moveVirtualCenter(moveAmount, 0);
                break;
            default:
                break;
        }
    }

    /**
     * Méthode appelée lorsqu'une touche du clavier est relâchée.
     * Cette implémentation ne fait rien.
     *
     * @param e L'événement KeyEvent associé au relâchement d'une touche.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Pas d'action à la relâche de la touche
    }

    /**
     * Méthode appelée lorsqu'une touche du clavier est tapée.
     * Cette implémentation ne fait rien.
     *
     * @param e L'événement KeyEvent associé au typage d'une touche.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Pas d'action au typage de la touche
    }
}
