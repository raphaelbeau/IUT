/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/utils/Database.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe gère la connexion à une base de données MariaDB.
 * Elle permet d'établir une connexion, de récupérer la connexion active,
 * et de fermer la connexion lorsque celle-ci n'est plus nécessaire.
 */

package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour gérer la connexion à une base de données MariaDB.
 */
public class Database {
    /**
     * URL de la base de données.
     */
    private static final String URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/yahiaouid";

    /**
     * Nom d'utilisateur pour la connexion.
     */
    private static final String LOGIN = "yahiaouid";

    /**
     * Mot de passe pour la connexion.
     */
    private static final String PASSWORD = "yahiaouid";

    /**
     * Instance de la connexion active à la base de données.
     */
    private Connection database;

    /**
     * Constructeur qui initialise la connexion à la base de données.
     *
     * @throws SQLException Si le chargement du driver ou la connexion échoue.
     */
    public Database() throws SQLException {
        try {
            // Chargement explicite du driver
            System.out.println("Tentative de chargement du driver MariaDB...");
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver MariaDB chargé avec succès.");

            // Connexion à la base de données
            System.out.println("Tentative de connexion à la base de données...");
            this.database = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver MariaDB introuvable.");
            e.printStackTrace();
            throw new SQLException("Erreur de chargement du driver.", e);
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible de se connecter à la base de données.");
            e.printStackTrace();
            throw new SQLException("Erreur de connexion : " + e.getMessage(), e);
        }
    }

    /**
     * Retourne la connexion active à la base de données.
     *
     * @return L'instance {@link Connection} active.
     */
    public Connection getDatabase() {
        return this.database;
    }

    /**
     * Ferme la connexion à la base de données si elle est active.
     */
    public void close() {
        try {
            if (this.database != null && !this.database.isClosed()) {
                this.database.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Méthode principale pour tester la connexion à la base de données.
     * Cette méthode établit une connexion, puis la ferme immédiatement.
     *
     * @param args Arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        try {
            System.out.println("Test de connexion à la base de données...");
            Database db = new Database();
            System.out.println("Connexion à la base de données réussie !");
            db.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors du test de connexion : " + e.getMessage());
        }
    }
}
