/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/controller/Main.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe principale pour lancer l'application Dorfomantik. Cette classe initialise
 * les composants nécessaires, affiche un écran de bienvenue (SplashScreen), 
 * et redirige ensuite vers le menu des séries.
 */

package controller;

import utils.Database;
import utils.SeedRepository;
import view.SeriesMenu;
import view.SplashScreen;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * Classe principale du programme qui initialise et démarre l'application Dorfomantik.
 * Elle gère l'affichage initial du SplashScreen, la connexion à la base de données,
 * et la redirection vers le menu principal des séries.
 */
public class Main {

    /**
     * Point d'entrée principal de l'application.
     *
     * @param args Les arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Synchronisation entre le SplashScreen et l'ouverture du menu principal
        CountDownLatch latch = new CountDownLatch(1);

        // Prélire les images nécessaires avant d'afficher le SplashScreen
        SwingUtilities.invokeLater(() -> SplashScreen.preloadImages("Menu.jpg", "regles.jpg", "Controles.jpg"));

        // Afficher l'écran de bienvenue (SplashScreen)
        SwingUtilities.invokeLater(() -> {
            try {
                SplashScreen.showSplashScreen(null, latch);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Une fois le SplashScreen fermé, charger les ressources et rediriger
        new Thread(() -> {
            try {
                // Attendre la fermeture du SplashScreen
                latch.await();

                // Initialisation de la base de données et des seeds
                Database database = new Database();
                SeedRepository seedRepository = new SeedRepository(database);

                // Afficher le menu des séries
                SwingUtilities.invokeLater(() -> SeriesMenu.showSeriesMenu(seedRepository, null));
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
