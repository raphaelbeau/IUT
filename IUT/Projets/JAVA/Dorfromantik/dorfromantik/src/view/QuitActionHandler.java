/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/QuitActionHandler.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente un gestionnaire d'action permettant de quitter l'application.
 * Elle est utilisée pour associer un événement, tel qu'un clic sur un bouton, à la fermeture de l'application.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire d'action pour quitter l'application.
 * Implémente {@link ActionListener} pour gérer l'événement de clic ou d'une autre interaction déclenchant l'action.
 */
public class QuitActionHandler implements ActionListener {

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Cette méthode termine immédiatement l'application en utilisant {@link System#exit(int)}.
     *
     * @param e L'événement associé à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0); // Ferme l'application
    }
}
