import java.util.List;
import java.io.Serializable;

/**
 * Interface représentant une extension du jeu.
 * Une extension permet d'ajouter de nouvelles cartes et fonctionnalités au jeu de base.
 * Elle implémente Serializable pour permettre la sauvegarde et le chargement des extensions.
 */
public interface Extension extends Serializable{
    
    /**
     * Retourne le nom de l'extension.
     * 
     * @return le nom de l'extension
     */
    String getNom();
    
    /**
     * Crée et retourne la liste des cartes à ajouter à la pioche du jeu.
     * 
     * @return la liste des cartes de l'extension
     */
    List<Carte> creerCartes();
    
    /**
     * Applique des effets spéciaux de l'extension sur le jeu.
     * Méthode optionnelle qui peut être redéfinie si l'extension nécessite des modifications particulières.
     * 
     * @param jeu l'instance du jeu sur laquelle appliquer les effets
     */
    default void appliquerSurJeu(Jeu jeu) {}
    
    /**
     * Retourne le type de scoring utilisé par cette extension.
     * Par défaut, retourne "base" pour utiliser le système de score standard.
     * 
     * @return le type de score ("base" par défaut)
     */
    default String getTypeScore() {
        return "base";
    }
}