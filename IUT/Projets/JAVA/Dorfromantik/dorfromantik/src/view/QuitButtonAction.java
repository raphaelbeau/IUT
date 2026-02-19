/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/QuitButtonAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une action liée au bouton "Quitter".
 * Elle permet de fermer l'application lorsque le bouton est cliqué.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action associée au bouton "Quitter".
 * Implémente {@link ActionListener} pour gérer l'événement de clic sur le bouton.
 */
public class QuitButtonAction implements ActionListener {

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Quitter".
     * Cette méthode ferme immédiatement l'application en utilisant {@link System#exit(int)}.
     *
     * @param e L'événement déclenché par l'utilisateur lorsqu'il clique sur le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0); // Arrête l'application
    }
}
