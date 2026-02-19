/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/ScoreFinal.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère l'affichage de l'écran final de la partie.
 * Elle présente les scores finaux, le pourcentage de réussite, et permet de revenir au menu principal.
 */

 package view;

 import controller.ScoreController;
 
 import javax.swing.*;
 import javax.swing.border.EmptyBorder;
 import java.awt.*;
 import java.util.List;
 
 /**
  * Classe responsable de l'affichage du score final et des options après la fin de la partie.
  */
 public class ScoreFinal {
 
     /**
      * Contrôleur des scores, utilisé pour accéder et enregistrer les données de score.
      */
     private final ScoreController scoreController;
 
     /**
      * Constructeur de la classe.
      *
      * @param scoreController Le contrôleur des scores pour accéder et manipuler les scores de la partie.
      */
     public ScoreFinal(ScoreController scoreController) {
         this.scoreController = scoreController;
     }
 
     /**
      * Affiche l'écran final de la partie.
      * Présente les scores finaux, compare le score final avec le meilleur score, et affiche les scores environnants.
      *
      * @param parentFrame          La fenêtre parente depuis laquelle cet écran est affiché.
      * @param showMainMenuCallback Callback pour retourner au menu principal.
      */
     public void afficher(JFrame parentFrame, Runnable showMainMenuCallback) {
         // Sauvegarder le score final
         scoreController.saveFinalScore();
 
         // Récupérer les scores
         int finalScore = scoreController.getTotalScore();
         int bestScore = scoreController.getBestScoreForSeries();
         int difference = bestScore - finalScore;
         double successPercentage = (finalScore / (double) bestScore) * 100;
 
         // Configuration de la boîte de dialogue
         JDialog finalScoreDialog = new JDialog(parentFrame, "Fin du jeu", true);
         finalScoreDialog.setSize(600, 700);
         finalScoreDialog.setLayout(new BorderLayout());
 
         // Panneau principal
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
         mainPanel.setBackground(new Color(56, 182, 255));
         mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
 
         // Titre
         JLabel titleLabel = new JLabel("🎉 Fin de la partie 🎉", SwingConstants.CENTER);
         titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
         titleLabel.setForeground(new Color(255, 222, 89));
         titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         mainPanel.add(titleLabel);
 
         // Score final
         JLabel finalScoreLabel = new JLabel("Votre score final : " + finalScore, SwingConstants.CENTER);
         finalScoreLabel.setFont(new Font("Verdana", Font.BOLD, 28));
         finalScoreLabel.setForeground(Color.RED);
         finalScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
         mainPanel.add(finalScoreLabel);
 
         // Comparaison avec le meilleur score
         JPanel comparisonPanel = new JPanel();
         comparisonPanel.setLayout(new BoxLayout(comparisonPanel, BoxLayout.Y_AXIS));
         comparisonPanel.setBackground(new Color(56, 182, 255));
 
         JLabel comparisonLabel = new JLabel();
         comparisonLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
         comparisonLabel.setHorizontalAlignment(SwingConstants.CENTER);
         comparisonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 
         if (finalScore >= bestScore) {
             comparisonLabel.setText("🎉 Félicitations ! Vous avez battu le meilleur score : " + bestScore);
             comparisonLabel.setForeground(Color.WHITE);
         } else {
             comparisonLabel.setText("Vous êtes à " + difference + " points du meilleur score : " + bestScore);
             comparisonLabel.setForeground(Color.WHITE);
         }
 
         // Fixer la taille préférée pour éviter le texte coupé
         comparisonLabel.setPreferredSize(new Dimension(550, 30));
         comparisonPanel.add(comparisonLabel);
         mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
         mainPanel.add(comparisonPanel);
 
         // Liste des scores environnants
         JPanel scoresPanel = new JPanel();
         scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
         scoresPanel.setBackground(new Color(56, 182, 255));
         JLabel scoresTitle = new JLabel("Scores environnants :", SwingConstants.CENTER);
         scoresTitle.setFont(new Font("Verdana", Font.BOLD, 20));
         scoresTitle.setForeground(Color.WHITE);
         scoresTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
         scoresPanel.add(scoresTitle);
 
         List<Integer> surroundingScores = scoreController.getSurroundingScoresForDisplay();
         for (int score : surroundingScores) {
             JLabel scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
             scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
             scoreLabel.setForeground(score == finalScore ? Color.RED : Color.WHITE);
             scoresPanel.add(scoreLabel);
         }
 
         JScrollPane scrollPane = new JScrollPane(scoresPanel);
         scrollPane.setPreferredSize(new Dimension(250, 80));
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
         mainPanel.add(scrollPane);
 
         // Pourcentage de réussite
         JLabel successLabel = new JLabel(String.format("🎯 Pourcentage de réussite : %.2f%%", successPercentage), SwingConstants.CENTER);
         successLabel.setFont(new Font("Verdana", Font.BOLD, 22));
         successLabel.setForeground(new Color(255, 222, 89));
         successLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
         mainPanel.add(successLabel);
 
         // Bouton de retour au menu
         JButton closeButton = new JButton("Retour au menu");
         closeButton.setFont(new Font("Verdana", Font.BOLD, 20));
         closeButton.setBackground(new Color(56, 182, 255));
         closeButton.setForeground(Color.WHITE);
         closeButton.addActionListener(e -> {
             finalScoreDialog.dispose();
             parentFrame.dispose();
             showMainMenuCallback.run();
         });
         closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
         mainPanel.add(closeButton);
 
         // Afficher la boîte de dialogue
         finalScoreDialog.add(mainPanel, BorderLayout.CENTER);
         finalScoreDialog.setLocationRelativeTo(parentFrame);
         finalScoreDialog.setVisible(true);
     }
 }
 