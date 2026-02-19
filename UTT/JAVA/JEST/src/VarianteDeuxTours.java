import java.io.Serializable;

/**
 * Variante limitant le nombre de tours de jeu.
 * Cette variante impose un nombre maximum de tours pour la partie,
 * ce qui peut accélérer le jeu ou créer une contrainte stratégique.
 */
public class VarianteDeuxTours implements Variante, Serializable {

    /** Le nombre maximum de tours autorisés */
    private int nbToursMax;

    /**
     * Constructeur de la variante avec nombre de tours limité.
     * 
     * @param nbToursMax le nombre maximum de tours pour la partie
     */
    public VarianteDeuxTours(int nbToursMax) {
        this.nbToursMax = nbToursMax;
    }

    /**
     * Applique la règle de limitation de tours au jeu.
     * Configure le jeu pour qu'il s'arrête après le nombre de tours spécifié.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerRegles(Jeu jeu) {
        jeu.setNombreToursMax(nbToursMax);
    }

    /**
     * Retourne le nom de cette variante incluant le nombre de tours.
     * 
     * @return le nom descriptif de la variante (ex: "Variante 5 tours max")
     */
    @Override
    public String getNom() {
        return "Variante " + nbToursMax + " tours max";
    }
}
