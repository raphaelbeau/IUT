import java.io.Serializable;

/**
 * Variante Double Pique du jeu Jest.
 * Dans cette variante, toutes les cartes Pique valent le double de leur valeur normale,
 * tandis que les autres cartes conservent leur valeur standard.
 */
public class VarianteDoublePique implements Variante, Serializable {
    
    /**
     * Applique les règles de la variante Double Pique au jeu.
     * Configure un visiteur de score spécifique qui double la valeur des cartes Pique.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerRegles(Jeu jeu) {
        jeu.setScoreVisitor(new ScoreVisitor() {
            /**
             * Calcule le score avec les cartes Pique doublées.
             * 
             * @param jest le Jest contenant les cartes du joueur
             * @return le score calculé avec les Pique doublés
             */
            @Override
            public int calculerScore(Jest jest) {
                int score = 0;
                for (Carte c : jest.getCartes()) {
                    if(c.getCouleur().equals("Pique")) score += 2 * c.getValeur();
                    else score += c.getValeur();
                }
                return score;
            }
        });
    }

    /**
     * Retourne le nom de cette variante.
     * 
     * @return "Double Pique"
     */
    @Override
    public String getNom() {
        return "Double Pique";
    }
}