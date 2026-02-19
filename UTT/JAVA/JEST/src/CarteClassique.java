import java.io.Serializable;

/**
 * Classe représentant une carte classique du jeu.
 * Une carte classique possède un nom, une couleur et une valeur standards.
 * Elle hérite de la classe abstraite Carte.
 */
public class CarteClassique extends Carte implements Serializable{
    
    /**
     * Constructeur de la carte classique.
     * 
     * @param nom le nom de la carte (ex: "As", "Roi", "Dame", etc.)
     * @param couleur la couleur de la carte ("Pique", "Trèfle", "Coeur", "Carreau")
     * @param valeur la valeur numérique de la carte
     */
    public CarteClassique(String nom, String couleur, int valeur) {
        super(nom, couleur, valeur);
    }
}