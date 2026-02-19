import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * Visiteur de score pour l'extension Caméléon.
 * Applique les règles de base du jeu avec l'ajout de la carte Caméléon.
 * Le Caméléon copie la valeur de la meilleure carte noire (Pique ou Trèfle) présente dans le Jest.
 */
public class ScoreVisitorCameleon implements ScoreVisitor, Serializable {

    /**
     * Calcule le score d'un Jest avec la règle Caméléon.
     * Le Caméléon copie la valeur de la meilleure carte noire (Pique ou Trèfle).
     * Les autres règles sont identiques au scoring de base.
     * 
     * @param jest le Jest contenant les cartes du joueur
     * @return le score calculé
     */
    @Override
    public int calculerScore(Jest jest) {
        List<Carte> cartes = jest.getCartes();

        boolean aJoker = false;
        int nbCoeurs = 0;
        boolean aCameleon = false;

        Map<String, List<Carte>> cartesParCouleur = new HashMap<>();
        for (Carte c : cartes) {
            String couleur = c.getCouleur();
            if (!cartesParCouleur.containsKey(couleur)) {
                cartesParCouleur.put(couleur, new ArrayList<Carte>());
            }
            cartesParCouleur.get(couleur).add(c);
        }

        int score = 0;
        int meilleureNoire = 0;

        for (Carte c : cartes) {
            if (c instanceof Joker) {
                aJoker = true;
                continue;
            }
            if (c instanceof CarteCameleon) {
                aCameleon = true;
                continue;
            }

            String couleur = c.getCouleur();
            String nom = c.getNom();
            int valeur = c.getValeur();

            if ("As".equals(nom)) {
                List<Carte> dansCetteCouleur = cartesParCouleur.get(couleur);
                if (dansCetteCouleur != null && dansCetteCouleur.size() == 1) {
                    valeur = 5;
                } else {
                    valeur = 1;
                }
            }

            if ("Pique".equals(couleur) || "Trèfle".equals(couleur)) {
                score += valeur;
                if (valeur > meilleureNoire) {
                    meilleureNoire = valeur;
                }
            } else if ("Carreau".equals(couleur)) {
                score -= valeur;
            } else if ("Coeur".equals(couleur)) {
                nbCoeurs++;
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

        if (aCameleon && meilleureNoire > 0) {
            score += meilleureNoire;
        }

        return score;
    }
}