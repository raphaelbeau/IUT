/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/SwitchToGameAction.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente une action permettant de basculer vers l'écran principal du jeu.
 * Elle initialise les composants nécessaires pour une nouvelle partie et gère la transition
 * depuis le menu des séries.
 */

package view;

import controller.ScoreController;
import model.Board;
import model.ScoreModel;
import utils.ScoreDatabase;
import utils.SeedRepository;

import javax.swing.*;

/**
 * Action permettant de lancer une nouvelle partie depuis le menu des séries.
 */
public class SwitchToGameAction implements java.awt.event.ActionListener {

    /**
     * La fenêtre du menu des séries.
     */
    private final JFrame seriesMenuFrame;

    /**
     * Le dépôt de seeds utilisé pour récupérer ou créer un identifiant de seed.
     */
    private final SeedRepository seedRepository;

    /**
     * Le seed utilisé pour initialiser la nouvelle partie.
     */
    private final long seed;

    /**
     * La fenêtre précédente qui sera fermée après la transition (si elle existe).
     */
    private final JFrame previousFrame;

    /**
     * Constructeur de l'action.
     *
     * @param seriesMenuFrame La fenêtre du menu des séries.
     * @param seedRepository  Le dépôt de seeds.
     * @param seed            Le seed pour la nouvelle partie.
     * @param previousFrame   La fenêtre précédente à fermer (peut être null).
     */
    public SwitchToGameAction(JFrame seriesMenuFrame, SeedRepository seedRepository, long seed, JFrame previousFrame) {
        this.seriesMenuFrame = seriesMenuFrame;
        this.seedRepository = seedRepository;
        this.seed = seed;
        this.previousFrame = previousFrame;
    }

    /**
     * Méthode appelée lorsqu'une action est déclenchée.
     * Initialise les composants du jeu et affiche la vue principale.
     *
     * @param e L'événement associé à l'action.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Récupère ou crée un identifiant pour le seed
                long seedId = seedRepository.getOrCreateSeedId(seed);

                // Initialise les composants du modèle, de la base de données et de la vue
                Board board = new Board();
                ScoreDatabase scoreDatabase = new ScoreDatabase();
                ScoreModel scoreModel = new ScoreModel();
                ScoreView scoreView = new ScoreView();
                ScoreController scoreController = new ScoreController(scoreModel, scoreView, scoreDatabase, seedId);

                // Crée la vue principale du jeu
                GameView gameView = new GameView(board, seed, scoreController, scoreView, seedRepository);

                // Passe la fenêtre du jeu en plein écran et l'affiche
                SeriesMenu.setFullScreen(gameView);
                gameView.setVisible(true);

                // Masque le menu des séries
                seriesMenuFrame.setVisible(false);

                // Ferme la fenêtre précédente si elle existe
                if (previousFrame != null) {
                    previousFrame.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
