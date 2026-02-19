/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ResizeListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les événements de redimensionnement de la fenêtre principale.
 * Elle ajuste dynamiquement la position et la taille de certains composants graphiques
 * tels que la minimap, l'affichage du score, et le bouton "Retour au menu".
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Écouteur de redimensionnement pour ajuster les positions des composants graphiques.
 */
public class ResizeListener extends ComponentAdapter {

    /**
     * Panneau de la minimap dont la position et la taille doivent être ajustées.
     */
    private final MiniMapPanel miniMapPanel;

    /**
     * Vue du score dont la position doit être ajustée.
     */
    private final ScoreView scoreView;

    /**
     * Bouton "Retour au menu" dont la position doit être ajustée.
     */
    private final JButton backToMenuButton;

    /**
     * Constructeur de l'écouteur de redimensionnement.
     *
     * @param miniMapPanel    Le panneau de la minimap à repositionner.
     * @param scoreView       La vue du score à repositionner.
     * @param backToMenuButton Le bouton "Retour au menu" à repositionner.
     */
    public ResizeListener(MiniMapPanel miniMapPanel, ScoreView scoreView, JButton backToMenuButton) {
        this.miniMapPanel = miniMapPanel;
        this.scoreView = scoreView;
        this.backToMenuButton = backToMenuButton;
    }

    /**
     * Méthode appelée lorsqu'un composant est redimensionné.
     * Ajuste dynamiquement les positions et tailles des composants associés.
     *
     * @param e L'événement de redimensionnement.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        Dimension size = e.getComponent().getSize();

        // Ajuster la position et la taille du panneau de la minimap
        miniMapPanel.setBounds(size.width - 220, size.height - 220, 200, 200);

        // Ajuster la position de la vue du score
        scoreView.setBounds(10, size.height - 60, 200, 50);

        // Ajuster la position du bouton "Retour au menu"
        backToMenuButton.setBounds(size.width - 440, size.height - 160, 200, 40);
    }
}
