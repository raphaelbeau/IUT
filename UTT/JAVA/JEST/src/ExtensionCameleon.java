import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Extension Caméléon qui ajoute des cartes Caméléon au jeu.
 * Cette extension modifie également le système de scoring pour gérer les spécificités des cartes Caméléon.
 */
public class ExtensionCameleon implements Extension, Serializable{

    /**
     * Retourne le nom de cette extension.
     * 
     * @return "Extension Caméléon"
     */
    @Override
    public String getNom() {
        return "Extension Caméléon";
    }

    /**
     * Crée et retourne la liste des cartes Caméléon à ajouter au jeu.
     * Ajoute une carte Caméléon au paquet de cartes.
     * 
     * @return la liste contenant les cartes Caméléon
     */
    @Override
    public List<Carte> creerCartes() {
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new CarteCameleon());
        return cartes;
    }

    /**
     * Applique les effets spéciaux de l'extension sur le jeu.
     * Configure le jeu pour utiliser un ScoreVisitor spécifique capable de gérer les cartes Caméléon.
     * 
     * @param jeu l'instance du jeu à configurer
     */
    @Override
    public void appliquerSurJeu(Jeu jeu) {
        jeu.setScoreVisitor(new ScoreVisitorCameleon());
    }
}