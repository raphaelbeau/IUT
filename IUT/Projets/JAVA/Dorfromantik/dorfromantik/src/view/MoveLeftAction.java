/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/MoveLeftAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une action permettant de déplacer la vue du plateau vers la gauche.
 * Elle est utilisée pour gérer les raccourcis clavier associés à ce déplacement.
 */

package view;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * Action pour déplacer virtuellement le plateau vers la gauche.
 * Implémente {@link AbstractAction} pour être utilisée avec les raccourcis clavier ou d'autres déclencheurs.
 */
public class MoveLeftAction extends AbstractAction {

    /**
     * Panneau du plateau à déplacer.
     */
    private final BoardPanel boardPanel;

    /**
     * Constructeur de l'action.
     *
     * @param boardPanel Le panneau du plateau à déplacer.
     */
    public MoveLeftAction(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Déplace le centre virtuel du plateau vers la gauche.
     *
     * @param e L'événement associé à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boardPanel.moveVirtualCenter(20, 0);
    }
}
