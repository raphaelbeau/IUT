/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/utils/TileRepository.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère les interactions avec la base de données pour les objets de type {@link Tile}.
 * Elle permet d'insérer de nouvelles tuiles (tiles) dans la base de données.
 */

package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Tile;

/**
 * Classe utilitaire pour gérer les tuiles (tiles) dans la base de données.
 * Fournit des méthodes pour sauvegarder des tuiles.
 */
public class TileRepository {

    private final Database database;

    /**
     * Constructeur de la classe TileRepository.
     *
     * @param database Instance de la classe {@link Database} utilisée pour établir une connexion.
     */
    public TileRepository(Database database) {
        this.database = database;
    }

    /**
     * Sauvegarde une tuile dans la base de données.
     *
     * @param tile La tuile à sauvegarder.
     *             Cette tuile doit contenir au moins un type de terrain (terrain1).
     *             Le terrain2 peut être null.
     * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
     */
    public void saveTile(Tile tile) throws SQLException {
        String query = "INSERT INTO tiles (terrain1, terrain2) VALUES (?, ?)";

        try (Connection connection = database.getDatabase();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Insérer le premier terrain (terrain1) obligatoire
            statement.setString(1, tile.getTerrain1().toString());

            // Insérer le second terrain (terrain2) si présent, sinon insérer NULL
            if (tile.getTerrain2() != null) {
                statement.setString(2, tile.getTerrain2().toString());
            } else {
                statement.setNull(2, java.sql.Types.VARCHAR);
            }

            // Exécuter la requête d'insertion
            statement.executeUpdate();
            System.out.println("Tuile sauvegardée : " + tile);
        }
    }
}
