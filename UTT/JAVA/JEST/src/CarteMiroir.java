import java.io.Serializable;

/**
 * Classe représentant une carte Miroir.
 * Il s'agit d'une carte spéciale qui permet probablement de refléter ou copier une autre carte.
 * Elle possède une couleur "Special" et une valeur de base de 0.
 */
public class CarteMiroir extends Carte implements Serializable{
    
    /**
     * Constructeur de la carte Miroir.
     * Initialise la carte avec le nom "Miroir", une couleur spéciale et une valeur de 0.
     */
    public CarteMiroir() {
        super("Miroir", "Special", 0);
    }
}