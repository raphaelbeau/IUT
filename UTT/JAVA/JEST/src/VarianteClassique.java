import java.io.Serializable;

/**
 * Variante classique du jeu Jest.
 * Applique les règles de scoring classiques complètes incluant les bonus
 * pour les As seuls, les paires noires et la gestion complexe du Joker avec les Coeurs.
 */
public class VarianteClassique implements Variante, Serializable{
    
    /**
     * Applique les règles classiques au jeu.
     * Configure le jeu pour utiliser le ScoreVisitorClassique.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerRegles(Jeu jeu) {
        jeu.setScoreVisitor(new ScoreVisitorClassique());
    }

    /**
     * Retourne le nom de cette variante.
     * 
     * @return "Classique"
     */
    @Override
    public String getNom() {
        return "Classique";
    }
}