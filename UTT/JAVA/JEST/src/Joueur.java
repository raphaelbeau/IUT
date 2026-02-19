import java.util.*;
import java.io.Serializable;

/**
 * Classe abstraite représentant un joueur dans le jeu Jest.
 * Un joueur possède un nom, une main de cartes, un Jest (collection de cartes gagnées),
 * et des offres (visibles et cachées) qu'il peut proposer aux autres joueurs.
 */
public abstract class Joueur implements Serializable {
    
    /** Le nom du joueur */
    protected String nom;
    
    /** La main du joueur contenant ses cartes actuelles */
    protected List<Carte> main;
    
    /** Le Jest du joueur contenant les cartes qu'il a gagnées */
    protected Jest jest;
    
    /** Les cartes proposées visiblement par le joueur */
    protected List<Carte> offreVisible = new ArrayList<>();
    
    /** Les cartes proposées de manière cachée par le joueur */
    protected List<Carte> offreCachee = new ArrayList<>();
    
    /** Identifiant de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur d'un joueur.
     * Initialise le joueur avec un nom et des structures vides pour la main,
     * le Jest et les offres.
     * 
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.jest = new Jest();
        this.offreVisible = new ArrayList<>();
        this.offreCachee = new ArrayList<>();
    }
    
    /**
     * Retourne le nom du joueur.
     * 
     * @return le nom du joueur
     */
    public String getNom() { return nom; }
    
    /**
     * Retourne la main du joueur.
     * 
     * @return la liste des cartes dans la main
     */
    public List<Carte> getMain() { return main; }
    
    /**
     * Retourne le Jest du joueur.
     * 
     * @return le Jest contenant les cartes gagnées
     */
    public Jest getJest() { return jest; }
    
    /**
     * Méthode abstraite pour jouer un tour.
     * Chaque type de joueur (physique, virtuel) doit implémenter sa propre logique.
     * 
     * @param jeu l'instance du jeu en cours
     */
    public abstract void jouerTour(Jeu jeu);
    
    /**
     * Retourne les cartes proposées visiblement par le joueur.
     * 
     * @return la liste des cartes de l'offre visible
     */
    public List<Carte> getOffreVisible() { return offreVisible; }
    
    /**
     * Retourne les cartes proposées de manière cachée par le joueur.
     * 
     * @return la liste des cartes de l'offre cachée
     */
    public List<Carte> getOffreCachee() { return offreCachee; }
}