import java.util.*;
import java.io.Serializable;

/**
 * Classe représentant un Jest (collection de cartes) d'un joueur.
 * Un Jest contient les cartes gagnées par un joueur et fournit des méthodes
 * utilitaires pour calculer les scores selon différentes variantes de règles.
 */
public class Jest implements Serializable{
    
    /** La liste des cartes contenues dans ce Jest */
    private List<Carte> cartes;
    
    /**
     * Constructeur du Jest.
     * Initialise un Jest vide.
     */
    public Jest() { cartes = new ArrayList<>(); }
    
    /**
     * Ajoute une carte au Jest.
     * 
     * @param c la carte à ajouter
     */
    public void ajouterCarte(Carte c) { cartes.add(c); }
    
    /**
     * Retourne la liste des cartes du Jest.
     * 
     * @return la liste des cartes
     */
    public List<Carte> getCartes() { return cartes; }

    /**
     * Calcule la valeur totale des cartes de couleur Coeur dans le Jest.
     * Méthode utilitaire pour certaines variantes de score.
     * 
     * @return la somme des valeurs des cartes Coeur
     */
    public int getCoeurValeurTotale() {
        int total = 0;
        for(Carte c : cartes)
            if("Coeur".equals(c.getCouleur())) total += c.getValeur();
        return total;
    }
    
    /**
     * Vérifie si une carte est la seule de sa couleur dans le Jest.
     * 
     * @param carte la carte à vérifier
     * @return true si la carte est unique dans sa couleur, false sinon
     */
    public boolean isSeulDansCouleur(Carte carte) {
        int count = 0;
        for(Carte c : cartes)
            if(c.getCouleur().equals(carte.getCouleur())) count++;
        return count == 1;
    }
    
    /**
     * Calcule le bonus pour les paires noires (Pique et Trèfle).
     * Une paire noire est constituée de deux cartes de valeur égale,
     * l'une Pique et l'autre Trèfle.
     * 
     * @return le nombre de points bonus pour les paires noires (+1 par paire)
     */
    public int bonusPairesNoires() {
        int bonus = 0;
        for(Carte c1 : cartes) {
            if("Pique".equals(c1.getCouleur()) || "Trèfle".equals(c1.getCouleur())) {
                for(Carte c2 : cartes) {
                    if(c1 != c2 && c1.getValeur() == c2.getValeur()
                       && !c1.getCouleur().equals(c2.getCouleur())
                    ) {
                        bonus += 1;
                    }
                }
            }
        }
        return bonus / 2;
    }
}