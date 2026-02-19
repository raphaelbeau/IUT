/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/model/ScoreModel.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Modèle pour la gestion des scores. Cette classe contient la logique
 * pour gérer, ajouter, et réinitialiser le score total du joueur.
 */

package model;

/**
 * Classe représentant le modèle de gestion des scores du jeu.
 * Elle permet de suivre et de modifier le score total du joueur.
 */
public class ScoreModel {
    private int totalScore;

    /**
     * Constructeur de la classe ScoreModel.
     * Initialise le score total à 0.
     */
    public ScoreModel() {
        this.totalScore = 0;
    }

    /**
     * Récupère le score total actuel.
     *
     * @return Le score total actuel.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Ajoute un score au score total actuel.
     *
     * @param score Le score à ajouter.
     */
    public void addScore(int score) {
        totalScore += score;
        System.out.println("Score ajouté : " + score + ", Total : " + totalScore);
    }

    /**
     * Réinitialise le score total à 0.
     */
    public void resetScore() {
        totalScore = 0;
    }
}
