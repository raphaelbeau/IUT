/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/controller/ScoreController.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Contrôleur pour la gestion des scores. Cette classe coordonne les interactions
 * entre le modèle de score, la vue des scores, et la base de données des scores.
 */

package controller;

import model.ScoreModel;
import utils.ScoreDatabase;
import view.ScoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de la gestion des scores dans le jeu.
 * Elle permet d'ajouter, d'afficher et de sauvegarder les scores, 
 * tout en communiquant avec le modèle, la vue et la base de données.
 */
public class ScoreController {
    private final ScoreModel scoreModel;
    private final ScoreView scoreView;
    private final ScoreDatabase scoreDatabase;
    private final long seedId;

    /**
     * Constructeur du contrôleur des scores.
     *
     * @param scoreModel    Le modèle de score contenant les données des scores.
     * @param scoreView     La vue pour afficher les scores à l'utilisateur.
     * @param scoreDatabase La base de données pour sauvegarder et récupérer les scores.
     * @param seedId        L'identifiant de la graine (seed) associée aux scores.
     */
    public ScoreController(ScoreModel scoreModel, ScoreView scoreView, ScoreDatabase scoreDatabase, long seedId) {
        this.scoreModel = scoreModel;
        this.scoreView = scoreView;
        this.scoreDatabase = scoreDatabase;
        this.seedId = seedId;
    }

    /**
     * Ajoute un score au modèle et met à jour la vue.
     *
     * @param score Le score à ajouter.
     */
    public void addScore(int score) {
        scoreModel.addScore(score);
        scoreView.updateScore(scoreModel.getTotalScore());
    }

    /**
     * Sauvegarde le score final dans la base de données.
     */
    public void saveFinalScore() {
        int finalScore = scoreModel.getTotalScore();
        scoreDatabase.saveScoreForSeed(finalScore, seedId);
    }

    /**
     * Récupère une liste de scores autour du score actuel pour les afficher dans la vue.
     *
     * @return Une sous-liste de scores comprenant le score actuel et ses voisins.
     */
    public List<Integer> getSurroundingScoresForDisplay() {
        int currentScore = scoreModel.getTotalScore();
        List<Integer> allScores = scoreDatabase.getScoresForSeed(seedId);

        int currentIndex = allScores.indexOf(currentScore);

        if (currentIndex == -1) {
            System.out.println("Erreur : le score actuel n'est pas trouvé !");
            return new ArrayList<>();
        }

        int start = Math.max(0, currentIndex - 5);
        int end = Math.min(allScores.size(), currentIndex + 6);

        return allScores.subList(start, end);
    }

    /**
     * Récupère le score total actuel à partir du modèle.
     *
     * @return Le score total actuel.
     */
    public int getTotalScore() {
        return scoreModel.getTotalScore();
    }

    /**
     * Récupère le meilleur score pour la série actuelle.
     *
     * @return Le meilleur score, ou 0 si aucun score n'est disponible.
     */
    public int getBestScoreForSeries() {
        List<Integer> allScores = scoreDatabase.getScoresForSeed(seedId);
        return allScores.isEmpty() ? 0 : allScores.get(0);
    }
}
