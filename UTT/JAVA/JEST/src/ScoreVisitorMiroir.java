import java.util.List;
import java.util.Set;
import java.io.Serializable;

/**
 * Visiteur de score pour l'extension Miroir.
 * Applique les règles de base du jeu avec l'ajout de la carte Miroir.
 * Le Miroir double le score total si celui-ci est positif.
 */
public class ScoreVisitorMiroir implements ScoreVisitor, Serializable {
    
    /**
     * Calcule le score d'un Jest avec la règle Miroir.
     * Si la carte Miroir est présente et que le score de base est positif,
     * le score est doublé. Sinon, le score de base est retourné.
     * 
     * @param jest le Jest contenant les cartes du joueur
     * @return le score calculé (possiblement doublé)
     */
    @Override
    public int calculerScore(Jest jest) {
        List<Carte> cartes = jest.getCartes();
        boolean aMiroir = false;

        for (Carte c : cartes) {
            if (c instanceof CarteMiroir) {
                aMiroir = true;
                break;
            }
        }

        ScoreVisitor base = new ScoreVisitorBase();
        int score = base.calculerScore(jest);

        if (aMiroir && score > 0) {
            score *= 2;
        }

        return score;
    }

    /**
     * Vérifie si ce visiteur est compatible avec un ensemble de types d'extensions.
     * Le visiteur Miroir est compatible si "miroir" est présent dans les types.
     * 
     * @param types l'ensemble des types d'extensions actives
     * @return true si compatible ("miroir" est présent), false sinon
     */
    public boolean estCompatibleAvec(Set<String> types) {
        return types.contains("miroir");
    }
}