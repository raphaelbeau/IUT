/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ScoreView.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente l'interface utilisateur pour afficher le score actuel et les scores environnants.
 * Elle est utilisée pour mettre à jour dynamiquement l'affichage des scores pendant la partie.
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vue graphique pour afficher et mettre à jour les informations liées au score.
 */
public class ScoreView extends JPanel {

    /**
     * Label pour afficher le score total.
     */
    private JLabel scoreLabel;

    /**
     * Constructeur de la classe `ScoreView`.
     * Initialise l'affichage avec une disposition à droite et un label pour le score.
     */
    public ScoreView() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);
    }

    /**
     * Met à jour l'affichage du score total.
     *
     * @param totalScore Le score total à afficher.
     */
    public void updateScore(int totalScore) {
        scoreLabel.setText("Score: " + totalScore);
        revalidate();
        repaint();
    }

    /**
     * Affiche une liste des scores environnants, en mettant en évidence le score actuel.
     *
     * @param scores       La liste des scores environnants.
     * @param currentScore Le score actuel à mettre en surbrillance.
     */
    public void displaySurroundingScores(List<Integer> scores, int currentScore) {
        removeAll(); // Supprimer les éléments existants
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Changer la disposition en verticale

        for (int score : scores) {
            JLabel scoreElement = new JLabel(String.valueOf(score));
            scoreElement.setFont(new Font("Arial", Font.PLAIN, 14));
            if (score == currentScore) {
                scoreElement.setForeground(Color.RED); // Mettre en évidence le score actuel
            }
            add(scoreElement); // Ajouter le score au panneau
        }

        revalidate(); // Revalider le panneau après les modifications
        repaint(); // Redessiner le panneau
    }
}
