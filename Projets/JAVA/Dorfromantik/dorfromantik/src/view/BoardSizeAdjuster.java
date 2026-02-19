/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/BoardSizeAdjuster.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe permet d'ajuster la taille du panneau du plateau de jeu.
 * Elle est conçue pour être exécutée dans un thread ou une tâche asynchrone via l'interface {@link Runnable}.
 */

package view;

/**
 * Classe responsable de l'ajustement de la taille du {@link BoardPanel}.
 * Implémente {@link Runnable} pour permettre une exécution dans un contexte multithread.
 */
public class BoardSizeAdjuster implements Runnable {

    /**
     * Le panneau du plateau de jeu à ajuster.
     */
    private final BoardPanel boardPanel;

    /**
     * Constructeur de la classe BoardSizeAdjuster.
     *
     * @param boardPanel Le panneau du plateau de jeu à surveiller et ajuster.
     */
    public BoardSizeAdjuster(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    /**
     * Méthode exécutée lorsque le thread est démarré.
     * Appelle la méthode {@code adjustBoardSize} du {@link BoardPanel} pour ajuster sa taille.
     */
    @Override
    public void run() {
        boardPanel.adjustBoardSize();
    }
}
