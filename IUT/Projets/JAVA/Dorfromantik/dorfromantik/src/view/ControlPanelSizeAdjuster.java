/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ControlPanelSizeAdjuster.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe est responsable de l'ajustement de l'affichage entre le panneau supérieur gauche
 * (topLeftPanel) et le panneau du plateau (BoardPanel). Elle permet de redessiner le panneau
 * du plateau pour s'adapter aux changements liés au panneau supérieur gauche.
 */

package view;

import javax.swing.JPanel;

/**
 * Classe responsable de l'ajustement dynamique de la taille des panneaux
 * dans l'interface graphique du jeu.
 * Implémente {@link Runnable} pour permettre une exécution dans un thread ou une tâche planifiée.
 */
public class ControlPanelSizeAdjuster implements Runnable {

    /**
     * Le panneau supérieur gauche de l'interface graphique.
     */
    private final JPanel topLeftPanel;

    /**
     * Le panneau du plateau de jeu à redessiner en fonction des ajustements.
     */
    private final BoardPanel boardPanel;

    /**
     * Constructeur de la classe ControlPanelSizeAdjuster.
     *
     * @param topLeftPanel Le panneau supérieur gauche de l'interface.
     * @param boardPanel   Le panneau du plateau de jeu à redessiner.
     */
    public ControlPanelSizeAdjuster(JPanel topLeftPanel, BoardPanel boardPanel) {
        this.topLeftPanel = topLeftPanel;
        this.boardPanel = boardPanel;
    }

    /**
     * Méthode exécutée lors de l'exécution du thread.
     * Redessine le panneau du plateau de jeu pour s'ajuster dynamiquement
     * aux modifications apportées au panneau supérieur gauche.
     */
    @Override
    public void run() {
        // Redessine le panneau du plateau pour refléter les ajustements nécessaires
        boardPanel.repaint();
    }
}
