import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Extension Miroir qui ajoute des cartes Miroir au jeu.
 * Cette extension utilise un système de scoring spécifique de type "miroir"
 * et configure le jeu pour gérer les mécaniques spéciales des cartes Miroir.
 */
public class ExtensionMiroir implements Extension, Serializable{
    
    /**
     * Retourne le nom de cette extension.
     * 
     * @return "Extension Miroir"
     */
    @Override
    public String getNom() { return "Extension Miroir"; }

    /**
     * Crée et retourne la liste des cartes Miroir à ajouter au jeu.
     * Ajoute une carte Miroir au paquet de cartes.
     * 
     * @return la liste contenant les cartes Miroir
     */
    @Override
    public List<Carte> creerCartes() {
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new CarteMiroir());
        return cartes;
    }

    /**
     * Retourne le type de scoring utilisé par cette extension.
     * 
     * @return "miroir" pour indiquer qu'un ScoreVisitor spécialisé est requis
     */
    @Override
    public String getTypeScore() {
        return "miroir";
    }

    /**
     * Applique les effets spéciaux de l'extension sur le jeu.
     * Configure le jeu pour utiliser un ScoreVisitor spécifique capable de gérer les cartes Miroir.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerSurJeu(Jeu jeu) {
        jeu.setScoreVisitor(new ScoreVisitorMiroir());
    }
}