import java.io.Serializable;

/**
 * Interface définissant un visiteur pour le calcul des scores.
 * Implémente le pattern Visitor pour permettre différents systèmes de scoring
 * selon les variantes et extensions du jeu.
 * Chaque implémentation définit sa propre logique de calcul basée sur le Jest d'un joueur.
 */
public interface ScoreVisitor extends Serializable{
    
    /**
     * Calcule le score d'un Jest selon les règles de ce visiteur.
     * 
     * @param jest le Jest contenant les cartes du joueur
     * @return le score calculé
     */
    int calculerScore(Jest jest);
}
