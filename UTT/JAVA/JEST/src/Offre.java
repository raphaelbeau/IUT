import java.io.Serializable;

/**
 * Classe représentant une offre de carte faite par un joueur.
 * Une offre associe une carte à son propriétaire et indique si elle est visible ou cachée.
 * Cette classe est utilisée pour faciliter le traitement des choix de cartes dans les stratégies.
 */
class Offre implements Serializable {
    
    /** Le joueur propriétaire de l'offre */
    private final Joueur proprietaire;
    
    /** La carte proposée dans l'offre */
    private final Carte carte;
    
    /** Indique si la carte est visible (true) ou cachée (false) */
    private final boolean visible;

    /**
     * Constructeur d'une offre.
     * 
     * @param proprietaire le joueur qui propose la carte
     * @param carte la carte proposée
     * @param visible true si la carte est visible, false si elle est cachée
     */
    public Offre(Joueur proprietaire, Carte carte, boolean visible) {
        this.proprietaire = proprietaire;
        this.carte = carte;
        this.visible = visible;
    }

    /**
     * Retourne le propriétaire de l'offre.
     * 
     * @return le joueur propriétaire
     */
    public Joueur getProprietaire() { return proprietaire; }
    
    /**
     * Retourne la carte de l'offre.
     * 
     * @return la carte proposée
     */
    public Carte getCarte() { return carte; }
    
    /**
     * Indique si la carte de l'offre est visible.
     * 
     * @return true si la carte est visible, false si elle est cachée
     */
    public boolean isVisible() { return visible; }
}