import java.util.Map;
import java.util.List;

/**
 * Interface définissant le contrat pour les vues du jeu Jest.
 * Implémente le pattern MVC (Modèle-Vue-Contrôleur) pour séparer l'affichage de la logique.
 * Une vue peut être une interface console, graphique, ou composite combinant plusieurs vues.
 */
public interface Vue {
    
    /**
     * Affiche l'état actuel du jeu.
     * 
     * @param jeu l'instance du jeu à afficher
     */
    void afficherJeu(Jeu jeu);
    
    /**
     * Demande une action au joueur.
     * 
     * @param joueur le joueur dont on attend une action
     */
    void demanderAction(Joueur joueur);
    
    /**
     * Demande au joueur de choisir une carte parmi les offres disponibles.
     * Méthode par défaut vide, peut être redéfinie par les implémentations.
     * 
     * @param joueur le joueur qui doit choisir
     * @param choixPossibles map associant un numéro à chaque carte disponible
     * @param proprietaires map associant un numéro au joueur propriétaire de la carte
     * @param estVisible map indiquant si chaque carte est visible ou cachée
     */
    default void demanderChoixCarte(Joueur joueur, Map<Integer, Carte> choixPossibles, 
                                   Map<Integer, Joueur> proprietaires, Map<Integer, Boolean> estVisible) {
    }
    
    /**
     * Demande au joueur de proposer une carte (visible ou cachée).
     * Méthode par défaut vide, peut être redéfinie par les implémentations.
     * 
     * @param joueur le joueur qui doit proposer une carte
     * @param main la main actuelle du joueur
     * @param typeChoix le type de choix ("visible" ou "cachee")
     */
    default void demanderPropositionCarte(Joueur joueur, List<Carte> main, String typeChoix) {
    }
    
    /**
     * Demande au joueur s'il souhaite sauvegarder la partie.
     * Méthode par défaut vide, peut être redéfinie par les implémentations.
     * 
     * @param joueur le joueur à qui demander
     */
    default void demanderSauvegarde(Joueur joueur) {
    }
    
    /**
     * Affiche l'écran de fin de partie avec les scores et le classement.
     * Méthode par défaut vide, peut être redéfinie par les implémentations.
     * 
     * @param scores map associant chaque joueur à son score
     * @param classement liste des joueurs dans l'ordre du classement
     */
    default void afficherFinPartie(Map<Joueur, Integer> scores, List<Joueur> classement) {
    }
}