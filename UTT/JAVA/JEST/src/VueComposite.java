import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Vue composite qui permet d'afficher le jeu sur plusieurs vues simultanément.
 * Implémente le pattern Composite pour combiner plusieurs vues (par exemple console et graphique).
 * Toutes les opérations d'affichage sont propagées à chacune des vues enregistrées.
 */
public class VueComposite implements Vue {
    
    /** Liste des vues composant cette vue composite */
    private List<Vue> vues;
    
    /**
     * Constructeur de la vue composite.
     * Initialise une liste vide de vues.
     */
    public VueComposite() {
        this.vues = new ArrayList<>();
    }
    
    /**
     * Ajoute une vue à la composition.
     * 
     * @param vue la vue à ajouter
     */
    public void ajouterVue(Vue vue) {
        vues.add(vue);
    }
    
    /**
     * Affiche le jeu sur toutes les vues de la composition.
     * 
     * @param jeu l'instance du jeu à afficher
     */
    @Override
    public void afficherJeu(Jeu jeu) {
        for (Vue vue : vues) {
            vue.afficherJeu(jeu);
        }
    }
    
    /**
     * Demande une action au joueur sur toutes les vues.
     * 
     * @param joueur le joueur dont on attend une action
     */
    @Override
    public void demanderAction(Joueur joueur) {
        for (Vue vue : vues) {
            vue.demanderAction(joueur);
        }
    }
    
    /**
     * Demande au joueur de choisir une carte sur toutes les vues.
     * 
     * @param joueur le joueur qui doit choisir
     * @param choixPossibles map associant un numéro à chaque carte disponible
     * @param proprietaires map associant un numéro au joueur propriétaire de la carte
     * @param estVisible map indiquant si chaque carte est visible ou cachée
     */
    @Override
    public void demanderChoixCarte(Joueur joueur, Map<Integer, Carte> choixPossibles, 
                                   Map<Integer, Joueur> proprietaires, Map<Integer, Boolean> estVisible) {
        for (Vue vue : vues) {
            vue.demanderChoixCarte(joueur, choixPossibles, proprietaires, estVisible);
        }
    }
    
    /**
     * Demande au joueur de proposer une carte sur toutes les vues.
     * 
     * @param joueur le joueur qui doit proposer une carte
     * @param main la main actuelle du joueur
     * @param typeChoix le type de choix ("visible" ou "cachee")
     */
    @Override
    public void demanderPropositionCarte(Joueur joueur, List<Carte> main, String typeChoix) {
        for (Vue vue : vues) {
            vue.demanderPropositionCarte(joueur, main, typeChoix);
        }
    }
    
    /**
     * Demande au joueur s'il souhaite sauvegarder la partie sur toutes les vues.
     * 
     * @param joueur le joueur à qui demander
     */
    @Override
    public void demanderSauvegarde(Joueur joueur) {
        for (Vue vue : vues) {
            vue.demanderSauvegarde(joueur);
        }
    }
    
    /**
     * Affiche l'écran de fin de partie sur toutes les vues.
     * 
     * @param scores map associant chaque joueur à son score
     * @param classement liste des joueurs dans l'ordre du classement
     */
    @Override
    public void afficherFinPartie(Map<Joueur, Integer> scores, List<Joueur> classement) {
        for (Vue vue : vues) {
            vue.afficherFinPartie(scores, classement);
        }
    }
}