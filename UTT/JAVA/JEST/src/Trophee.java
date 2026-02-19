/**
 * Classe représentant un trophée dans le jeu.
 * Un trophée est une carte spéciale avec une condition d'obtention particulière.
 * Il hérite de la classe Carte et possède une couleur fixe "Trophée" avec une valeur de 0.
 */
public class Trophee extends Carte {
    
    /** La condition pour obtenir ce trophée */
    private String condition;
    
    /**
     * Constructeur du trophée.
     * 
     * @param nom le nom du trophée
     * @param condition la condition à remplir pour obtenir ce trophée
     */
    public Trophee(String nom, String condition) {
        super(nom, "Trophée", 0);
        this.condition = condition;
    }
    
    /**
     * Retourne la condition d'obtention du trophée.
     * 
     * @return la condition du trophée
     */
    public String getCondition() { return condition; }
}