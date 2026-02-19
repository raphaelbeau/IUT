import java.io.Serializable;

/**
 * Visiteur de score classique pour le jeu Jest.
 * Applique les règles de scoring complètes du jeu incluant :
 * - Pique et Trèfle : ajoutent leur valeur
 * - Carreau : soustrait leur valeur
 * - Coeur : comportement variable selon le Joker et le nombre de Coeurs
 * - As seul : vaut 5 points au lieu de 1
 * - Paires noires : bonus pour les paires de cartes de même valeur en Pique et Trèfle
 * - Joker : +4 points sans Coeur, négatif avec 1-3 Coeurs, positif avec 4+ Coeurs
 */
public class ScoreVisitorClassique implements ScoreVisitor, Serializable {
    
    /**
     * Calcule le score d'un Jest selon les règles classiques complètes.
     * 
     * @param jest le Jest contenant les cartes du joueur
     * @return le score calculé
     */
    @Override
    public int calculerScore(Jest jest) {
        int score = 0;
        boolean aJoker = false;
        int nbHearts = 0;
        int scoreJoker = 0;

        for (Carte c : jest.getCartes()) {
            switch(c.getCouleur()) {
                case "Pique":
                case "Trèfle":
                    score += c.getValeur();
                    break;
                case "Carreau":
                    score -= c.getValeur();
                    break;
                case "Coeur":
                    nbHearts += 1;
                    break;
            }
            if (c instanceof Joker) {
                aJoker = true;
            }
        }
        
        if (aJoker) {
            if (nbHearts == 0) score += 4;
            else if (nbHearts < 4) score -= jest.getCoeurValeurTotale();
            else score += jest.getCoeurValeurTotale();
        }

        for (Carte c : jest.getCartes()) {
            if (c.getValeur() == 1 && jest.isSeulDansCouleur(c)) {
                score += 4;
            }
        }

        score += jest.bonusPairesNoires();

        return score;
    }
}