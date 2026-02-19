/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/HomeButtonAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère l'action du bouton permettant de retourner à l'écran d'accueil
 * (SplashScreen). Elle ferme la fenêtre actuelle et réaffiche l'écran de démarrage.
 */

package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action associée au bouton de retour à l'écran d'accueil.
 * Implémente {@link ActionListener} pour être liée à un bouton interactif.
 */
public class HomeButtonAction implements ActionListener {

    /**
     * La fenêtre actuelle (JFrame) qui sera fermée lorsque l'action est déclenchée.
     */
    private final JFrame frame;

    /**
     * Constructeur de l'action de retour à l'écran d'accueil.
     *
     * @param frame La fenêtre actuelle à fermer.
     */
    public HomeButtonAction(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Ferme la fenêtre actuelle et affiche l'écran de démarrage ({@link SplashScreen}).
     *
     * @param e L'événement d'action associé.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        SplashScreen.showSplashScreen(null, null);
    }
}
