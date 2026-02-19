/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ReturnToSeriesMenuAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une action permettant de revenir au menu des séries.
 * Elle ferme la fenêtre actuelle et ouvre le menu des séries en utilisant les données fournies par le SeedRepository.
 */

package view;

import utils.SeedRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action pour revenir au menu des séries.
 * Implémente {@link ActionListener} pour être associée à un bouton ou une commande.
 */
public class ReturnToSeriesMenuAction implements ActionListener {

    /**
     * Référence à la fenêtre actuelle qui sera fermée.
     */
    private final JFrame currentFrame;

    /**
     * Référence au dépôt des seeds utilisé pour initialiser le menu des séries.
     */
    private final SeedRepository seedRepository;

    /**
     * Constructeur de l'action.
     *
     * @param currentFrame   La fenêtre actuelle à fermer.
     * @param seedRepository Le dépôt de seeds pour configurer le menu des séries.
     */
    public ReturnToSeriesMenuAction(JFrame currentFrame, SeedRepository seedRepository) {
        this.currentFrame = currentFrame;
        this.seedRepository = seedRepository;
    }

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Ferme la fenêtre actuelle et ouvre le menu des séries.
     *
     * @param e L'événement associé à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame.dispose();
        SwingUtilities.invokeLater(() -> SeriesMenu.showSeriesMenu(seedRepository, null));
    }
}
