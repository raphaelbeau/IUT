import java.io.Serializable;

/**
 * Interface définissant une stratégie de jeu pour les joueurs virtuels.
 * Implémente le pattern Strategy pour permettre différents comportements d'IA.
 * Chaque implémentation définit sa propre logique de décision pour choisir les cartes.
 */
public interface Strategie extends Serializable{
    
    /**
     * Exécute la stratégie pour faire jouer un joueur virtuel.
     * Cette méthode détermine quelle carte le joueur virtuel doit choisir
     * parmi les offres disponibles des autres joueurs.
     * 
     * @param joueur le joueur virtuel qui doit jouer
     * @param jeu l'instance du jeu en cours
     */
    void jouer(JoueurVirtuel joueur, Jeu jeu);
}