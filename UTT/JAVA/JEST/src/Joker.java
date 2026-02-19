import java.io.Serializable;

/**
 * Classe représentant un Joker.
 * Le Joker est une carte spéciale sans couleur définie et avec une valeur de base de 0.
 * Son comportement et son impact sur le score dépendent des règles de jeu appliquées.
 */
public class Joker extends Carte implements Serializable{
    
    /**
     * Constructeur du Joker.
     * Initialise le Joker avec le nom "Joker", aucune couleur et une valeur de 0.
     */
    public Joker() {
        super("Joker", "Aucune", 0);
    }
}