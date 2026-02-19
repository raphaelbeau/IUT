/**
 * Classe Contrôleur qui gère la logique de contrôle du jeu.
 * Elle fait le lien entre le modèle (Jeu) et la vue (Vue) selon le pattern MVC.
 * Le contrôleur coordonne les interactions entre l'utilisateur, le jeu et l'affichage.
 */
public class Controleur {
    
    /** L'instance du jeu géré par ce contrôleur */
    private Jeu jeu;
    
    /** La vue utilisée pour afficher le jeu */
    private Vue vue;

    /**
     * Constructeur du contrôleur.
     * 
     * @param jeu l'instance du jeu à contrôler
     * @param vue la vue pour afficher le jeu
     */
    public Controleur(Jeu jeu, Vue vue) {
        this.jeu = jeu;
        this.vue = vue;
    }

    /**
     * Démarre le jeu en affichant l'état initial et en lançant la boucle de jeu.
     * Cette méthode gère les demandes d'actions et coordonne le déroulement du jeu.
     */
    public void demarrerJeu() {
        vue.afficherJeu(jeu);
    }
}