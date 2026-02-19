import java.io.Serializable;

/**
 * Variante Ordre Tournant du jeu Jest.
 * Dans cette variante, l'ordre de jeu des joueurs change à chaque tour.
 * Le premier joueur d'un tour devient le dernier au tour suivant,
 * créant une rotation qui affecte la stratégie du jeu.
 */
public class VarianteOrdreTournant implements Variante, Serializable {

    /**
     * Applique les règles de la variante Ordre Tournant au jeu.
     * Active la rotation de l'ordre de jeu à chaque tour.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerRegles(Jeu jeu) {
        jeu.setOrdreTournant(true);
    }

    /**
     * Retourne le nom de cette variante.
     * 
     * @return "Variante Ordre tournant"
     */
    @Override
    public String getNom() {
        return "Variante Ordre tournant";
    }
}