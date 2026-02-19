import java.io.Serializable;

/**
 * Classe abstraite représentant une carte à jouer.
 * Cette classe sert de base pour tous les types de cartes utilisés dans le jeu.
 * Elle implémente Serializable pour permettre la sauvegarde et le chargement des cartes.
 */
public abstract class Carte implements Serializable{
    
    /** Le nom de la carte (ex: "As", "Roi", "Dame", etc.) */
    private String nom;
    
    /** La couleur de la carte : "Pique", "Trèfle", "Coeur", "Carreau" */
    private String couleur;
    
    /** La valeur numérique de la carte */
    private int valeur;

    /**
     * Constructeur de la classe Carte.
     * 
     * @param nom le nom de la carte
     * @param couleur la couleur de la carte ("Pique", "Trèfle", "Coeur", "Carreau")
     * @param valeur la valeur numérique de la carte
     */
    public Carte(String nom, String couleur, int valeur) {
        this.nom = nom;
        this.couleur = couleur;
        this.valeur = valeur;
    }
    
    /**
     * Retourne le nom de la carte.
     * 
     * @return le nom de la carte
     */
    public String getNom() { return nom; }
    
    /**
     * Retourne la couleur de la carte.
     * 
     * @return la couleur de la carte
     */
    public String getCouleur() { return couleur; }
    
    /**
     * Retourne la valeur numérique de la carte.
     * 
     * @return la valeur de la carte
     */
    public int getValeur() { return valeur; }
}