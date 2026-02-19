import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.Serializable;

/**
 * Implémentation de base du visiteur de score pour le jeu Jest.
 * Applique les règles de scoring standards du jeu :
 * - Pique et Trèfle : ajoutent leur valeur au score
 * - Carreau : soustrait leur valeur du score
 * - Coeur : comportement dépendant de la présence du Joker
 * - As : vaut 5 points s'il est seul de sa couleur, sinon 1 point
 * - Joker : vaut +4 points s'il n'y a pas de Coeur, sinon 0 et les Coeurs deviennent négatifs
 */
public class ScoreVisitorBase implements ScoreVisitor, Serializable {
    
    /**
     * Calcule le score d'un Jest selon les règles de base.
     * 
     * @param jest le Jest contenant les cartes du joueur
     * @return le score calculé
     */
    @Override
    public int calculerScore(Jest jest) {
        int score = 0;
        List<Carte> cartes = jest.getCartes();

        boolean aJoker = false;
        int nbCoeurs = 0;

        Map<String, List<Carte>> cartesParCouleur = new HashMap<>();
        for (Carte c : cartes) {
            cartesParCouleur
                .computeIfAbsent(c.getCouleur(), k -> new ArrayList<>())
                .add(c);
        }

        for (Carte c : cartes) {
            if (c instanceof Joker) {
                aJoker = true;
                continue;
            }

            String couleur = c.getCouleur();
            String nom = c.getNom();
            int valeur = c.getValeur();

            if ("As".equals(nom)) {
                List<Carte> dansCetteCouleur = cartesParCouleur.getOrDefault(couleur, List.of());
                if (dansCetteCouleur.size() == 1) {
                    valeur = 5;
                } else {
                    valeur = 1;
                }
            }

            switch (couleur) {
                case "Pique":
                case "Trèfle":
                    score += valeur;
                    break;
                case "Carreau":
                    score -= valeur;
                    break;
                case "Coeur":
                    nbCoeurs++;
                    break;
                default:
                    break;
            }
        }

        if (aJoker) {
            if (nbCoeurs == 0) {
                score += 4;
            } else {
                for (Carte c : cartes) {
                    if ("Coeur".equals(c.getCouleur())) {
                        score -= c.getValeur();
                    }
                }
            }
        }

        return score;
    }

    /**
     * Vérifie si ce visiteur est compatible avec un ensemble de types d'extensions.
     * Le visiteur de base est compatible uniquement avec le jeu sans extensions spéciales.
     * 
     * @param types l'ensemble des types d'extensions actives
     * @return true si compatible (tous les types sont "base"), false sinon
     */
    public boolean estCompatibleAvec(Set<String> types) {
        return types.stream().allMatch(t -> t.equals("base"));
    }
}