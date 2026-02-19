/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ButtonActionHandler.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les actions des boutons de l'écran de démarrage (SplashScreen).
 * Elle permet de mettre à jour l'image de fond et d'activer ou désactiver les boutons
 * selon le contexte (menu principal ou autre écran).
 */

package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire d'actions pour les boutons de l'écran de démarrage.
 * Implémente {@link ActionListener} pour écouter et répondre aux clics des boutons.
 */
public class ButtonActionHandler implements ActionListener {

    /**
     * Le panneau de l'écran de démarrage, utilisé pour mettre à jour l'image de fond.
     */
    private final SplashScreen splashPanel;

    /**
     * Le chemin de l'image à afficher en arrière-plan.
     */
    private final String imagePath;

    /**
     * Bouton "Jouer" (activé uniquement dans le menu principal).
     */
    private final JButton playButton;

    /**
     * Bouton "Règles" (activé uniquement dans le menu principal).
     */
    private final JButton rulesButton;

    /**
     * Bouton "Contrôles" (activé uniquement dans le menu principal).
     */
    private final JButton controlsButton;

    /**
     * Bouton "Réinitialiser" (activé uniquement lorsque l'on quitte le menu principal).
     */
    private final JButton resetButton;

    /**
     * Constructeur du gestionnaire d'actions.
     *
     * @param splashPanel  Le panneau de l'écran de démarrage.
     * @param imagePath    Le chemin de l'image à afficher.
     * @param playButton   Bouton "Jouer".
     * @param rulesButton  Bouton "Règles".
     * @param controlsButton Bouton "Contrôles".
     * @param resetButton  Bouton "Réinitialiser".
     */
    public ButtonActionHandler(SplashScreen splashPanel, String imagePath, JButton playButton, JButton rulesButton, JButton controlsButton, JButton resetButton) {
        this.splashPanel = splashPanel;
        this.imagePath = imagePath;
        this.playButton = playButton;
        this.rulesButton = rulesButton;
        this.controlsButton = controlsButton;
        this.resetButton = resetButton;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée sur un bouton.
     * Met à jour l'image de fond et active/désactive les boutons selon le contexte.
     *
     * @param e L'événement d'action correspondant.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mise à jour de l'image de fond dans le panneau de l'écran de démarrage
        splashPanel.updateBackgroundImage(imagePath);

        // Vérifie si le contexte est le menu principal
        boolean isMainMenu = imagePath.equals("Menu.jpg");

        // Active ou désactive les boutons en fonction du contexte
        playButton.setEnabled(isMainMenu);
        rulesButton.setEnabled(isMainMenu);
        controlsButton.setEnabled(isMainMenu);
        resetButton.setEnabled(!isMainMenu);
    }
}
