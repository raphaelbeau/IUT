import java.io.Serializable;

/**
 * Classe représentant une carte Caméléon.
 * Il s'agit d'une carte spéciale qui peut potentiellement prendre différentes couleurs ou valeurs.
 * Elle possède une couleur "Special" et une valeur de base de 0.
 */
public class CarteCameleon extends Carte implements Serializable{
    
    /**
     * Constructeur de la carte Caméléon.
     * Initialise la carte avec le nom "Caméléon", une couleur spéciale et une valeur de 0.
     */
    public CarteCameleon() {
        super("Caméléon", "Special", 0);
    }
}