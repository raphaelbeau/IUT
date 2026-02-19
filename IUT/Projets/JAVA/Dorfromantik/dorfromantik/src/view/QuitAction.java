/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/QuitAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une action permettant de quitter l'application.
 * Elle est utilisée pour gérer les événements déclenchés par un bouton ou une commande associée.
 */

package view;

import java.awt.event.ActionListener;

/**
 * Action pour quitter l'application.
 * Implémente {@link ActionListener} pour être associée à un bouton ou une commande.
 */
public class QuitAction implements ActionListener {

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Ferme immédiatement l'application en appelant {@link System#exit(int)}.
     *
     * @param e L'événement associé à l'action.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.exit(0);
    }
}
