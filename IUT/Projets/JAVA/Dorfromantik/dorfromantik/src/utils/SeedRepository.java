/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/utils/SeedRepository.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les interactions avec la base de données pour les seeds (graines).
 * Elle permet de récupérer tous les seeds, d'en sauvegarder de nouveaux et d'obtenir
 * des informations spécifiques liées aux seeds.
 */

package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour gérer les seeds dans la base de données.
 * Elle permet d'ajouter des seeds, de les récupérer et de gérer les IDs des seeds.
 */
public class SeedRepository {
    private final Connection connection;

    /**
     * Constructeur de la classe SeedRepository.
     *
     * @param database Instance de la classe {@link Database} utilisée pour établir une connexion.
     */
    public SeedRepository(Database database) {
        this.connection = database.getDatabase();
    }

    /**
     * Récupère tous les seeds stockés dans la base de données.
     *
     * @return Une liste de tous les seeds.
     * @throws SQLException En cas d'erreur lors de la récupération des données.
     */
    public List<Long> getAllSeeds() throws SQLException {
        List<Long> seeds = new ArrayList<>();
        String query = "SELECT seed_value FROM seeds";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                seeds.add(resultSet.getLong("seed_value"));
            }
        }
        return seeds;
    }

    /**
     * Sauvegarde un seed dans la base de données s'il n'existe pas déjà.
     *
     * @param seed Le seed à sauvegarder.
     * @throws SQLException En cas d'erreur lors de l'insertion ou de la vérification.
     */
    public void saveSeed(long seed) throws SQLException {
        // Vérifier si le seed existe déjà
        String checkQuery = "SELECT COUNT(*) FROM seeds WHERE seed_value = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setLong(1, seed);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    System.out.println("Le seed existe déjà dans la base de données : " + seed);
                    return; // Ne pas insérer si le seed existe
                }
            }
        }

        // Insérer le seed s'il n'existe pas
        String insertQuery = "INSERT INTO seeds (seed_value) VALUES (?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setLong(1, seed);
            insertStatement.executeUpdate();
            System.out.println("Seed inséré : " + seed);
        }
    }

    /**
     * Récupère l'ID d'un seed ou le crée s'il n'existe pas encore.
     *
     * @param seed Le seed pour lequel récupérer ou créer l'ID.
     * @return L'ID du seed (valeur du seed).
     */
    public long getOrCreateSeedId(long seed) {
        String selectSql = "SELECT seed_value FROM seeds WHERE seed_value = ?";
        String insertSql = "INSERT INTO seeds (seed_value) VALUES (?)";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setLong(1, seed);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("seed_value"); // Retourne le seed existant
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du seed : " + e.getMessage());
        }

        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setLong(1, seed);
            insertStmt.executeUpdate();
            return seed; // Retourne le seed nouvellement inséré
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du seed : " + e.getMessage());
        }

        throw new RuntimeException("Impossible de récupérer ou créer un seed_id");
    }

    /**
     * Récupère le dernier seed inséré dans la base de données.
     *
     * @return Le dernier seed ajouté.
     * @throws SQLException En cas d'erreur lors de la récupération ou si aucun seed n'est présent.
     */
    public long getLastSeed() throws SQLException {
        String query = "SELECT seed_value FROM seeds ORDER BY seed_value DESC LIMIT 1";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return resultSet.getLong("seed_value");
            } else {
                throw new SQLException("Aucun seed trouvé dans la base de données.");
            }
        }
    }
}
