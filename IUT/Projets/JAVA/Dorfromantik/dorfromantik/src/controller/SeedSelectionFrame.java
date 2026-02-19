/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/controller/SeedSelectionFrame.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une fenêtre graphique permettant à l'utilisateur
 * de sélectionner un seed (graine) parmi une liste de seeds disponibles.
 * Une fois le seed sélectionné, il est transmis au jeu via un listener.
 */

package controller;

import utils.SeedRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Fenêtre graphique pour sélectionner un seed utilisé pour initialiser le jeu.
 * Cette fenêtre affiche une liste de seeds disponibles récupérés depuis la base
 * de données et notifie un {@link SeedSelectionListener} lorsque l'utilisateur
 * sélectionne un seed.
 */
public class SeedSelectionFrame extends JFrame {

    /**
     * Liste déroulante pour afficher les seeds disponibles.
     */
    private JComboBox<Long> seedComboBox;

    /**
     * Bouton pour confirmer la sélection et lancer le jeu.
     */
    private JButton startButton;

    /**
     * Le seed sélectionné par l'utilisateur.
     */
    private long selectedSeed;

    /**
     * Constructeur de la fenêtre de sélection de seed.
     *
     * @param seedRepository Le repository contenant les seeds disponibles.
     * @param listener       Le listener qui sera notifié lorsque l'utilisateur
     *                       sélectionne un seed.
     */
    public SeedSelectionFrame(SeedRepository seedRepository, SeedSelectionListener listener) {
        setTitle("Choisissez un seed");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Récupérer les seeds depuis la base de données
        List<Long> seeds;
        try {
            seeds = seedRepository.getAllSeeds();
        } catch (SQLException e) {
            e.printStackTrace();
            // En cas d'erreur, générer un seed par défaut
            seeds = List.of(System.currentTimeMillis());
        }

        // Initialiser la liste déroulante avec les seeds disponibles
        seedComboBox = new JComboBox<>(seeds.toArray(new Long[0]));
        add(seedComboBox, BorderLayout.CENTER);

        // Bouton pour démarrer le jeu
        startButton = new JButton("Lancer le jeu");
        startButton.addActionListener(e -> {
            // Récupérer le seed sélectionné et notifier le listener
            selectedSeed = (Long) seedComboBox.getSelectedItem();
            listener.onSeedSelected(selectedSeed);
            dispose(); // Fermer la fenêtre
        });
        add(startButton, BorderLayout.SOUTH);

        // Afficher la fenêtre
        setVisible(true);
    }
}
