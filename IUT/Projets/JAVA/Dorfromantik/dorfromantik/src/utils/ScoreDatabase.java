/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/utils/ScoreDatabase.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe utilitaire pour gérer les interactions avec la base de données des scores.
 * Cette classe fournit des méthodes pour sauvegarder des scores et récupérer
 * des listes de scores pour un seed donné.
 */

package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'interagir avec la base de données des scores.
 * Fournit des méthodes pour enregistrer des scores et récupérer les scores associés à un seed.
 */
public class ScoreDatabase {
    private final Connection connection;

    /**
     * Constructeur de la classe ScoreDatabase.
     * Établit une connexion à la base de données.
     *
     * @throws SQLException En cas d'erreur de connexion à la base de données.
     */
    public ScoreDatabase() throws SQLException {
        String url = "jdbc:mariadb://dwarves.iut-fbleau.fr/yahiaouid";
        String user = "yahiaouid";
        String password = "yahiaouid";

        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connexion à ScoreDatabase réussie !");
    }

    /**
     * Sauvegarde un score pour un seed donné dans la base de données.
     *
     * @param score  Le score à enregistrer.
     * @param seedId L'identifiant du seed associé au score.
     */
    public void saveScoreForSeed(int score, long seedId) {
        String sql = "INSERT INTO scores (seed_id, score) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, seedId);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            System.out.println("Score sauvegardé pour seed_id=" + seedId + " : " + score);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du score : " + e.getMessage());
        }
    }

    /**
     * Récupère les scores enregistrés pour un seed donné, triés par ordre décroissant.
     *
     * @param seedId L'identifiant du seed pour lequel récupérer les scores.
     * @return Une liste de scores pour le seed donné.
     */
    public List<Integer> getScoresForSeed(long seedId) {
        String sql = "SELECT score FROM scores WHERE seed_id = ? ORDER BY score DESC";
        List<Integer> scores = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, seedId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    scores.add(rs.getInt("score"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des scores pour seed_id=" + seedId + " : " + e.getMessage());
        }

        return scores;
    }

    /**
     * Récupère les scores environnants d'un score donné pour un seed.
     * Cette méthode retourne les 5 scores inférieurs ou égaux et les 5 scores supérieurs au score donné.
     *
     * @param seedId       L'identifiant du seed pour lequel récupérer les scores.
     * @param currentScore Le score de référence.
     * @return Une liste de scores environnants, triés par ordre décroissant.
     */
    public List<Integer> getSurroundingScores(long seedId, int currentScore) {
        String sql = """
            SELECT score FROM (
                (SELECT score FROM scores WHERE seed_id = ? AND score <= ? ORDER BY score DESC LIMIT 5)
                UNION
                (SELECT score FROM scores WHERE seed_id = ? AND score > ? ORDER BY score ASC LIMIT 5)
            ) AS combined_scores
            ORDER BY score DESC
        """;
        List<Integer> scores = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, seedId);
            stmt.setInt(2, currentScore);
            stmt.setLong(3, seedId);
            stmt.setInt(4, currentScore);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    scores.add(rs.getInt("score"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des scores environnants : " + e.getMessage());
        }

        return scores;
    }
}
