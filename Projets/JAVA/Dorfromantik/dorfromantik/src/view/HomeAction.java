/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/HomeAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère l'action de retour au menu principal (SeriesMenu).
 * Elle permet de fermer la fenêtre actuelle et d'afficher le menu des séries.
 */

package view;

import utils.SeedRepository;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Action permettant de retourner au menu principal (SeriesMenu).
 * Implémente {@link ActionListener} pour être associée à des boutons ou d'autres composants interactifs.
 */
public class HomeAction implements ActionListener {

    /**
     * Référence à la fenêtre (JFrame) actuelle, qui sera fermée lorsque l'action est déclenchée.
     */
    private final JFrame seriesMenuFrame;

    /**
     * Repository contenant les informations sur les seeds (graines) utilisées pour les plateaux.
     * Permet d'initialiser correctement le menu des séries.
     */
    private final SeedRepository seedRepository;

    /**
     * Constructeur de l'action de retour au menu principal.
     *
     * @param seriesMenuFrame La fenêtre actuelle qui sera fermée.
     * @param seedRepository  Le repository contenant les seeds pour le menu des séries.
     */
    public HomeAction(JFrame seriesMenuFrame, SeedRepository seedRepository) {
        this.seriesMenuFrame = seriesMenuFrame;
        this.seedRepository = seedRepository;
    }

    /**
     * Méthode appelée lorsque l'action est déclenchée.
     * Ferme la fenêtre actuelle et affiche le menu principal des séries.
     *
     * @param e L'événement d'action associé.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        seriesMenuFrame.dispose();
        SeriesMenu.showSeriesMenu(seedRepository, null);
    }
}
