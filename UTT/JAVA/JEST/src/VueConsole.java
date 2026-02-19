/**
 * Vue console pour le jeu Jest.
 * Cette implémentation minimale de Vue s'appuie sur les affichages System.out.println
 * déjà présents dans les classes du jeu (Jeu, JoueurPhysique, etc.).
 * Elle ne nécessite pas d'affichage supplémentaire car la logique métier gère déjà
 * l'affichage console de manière intégrée.
 */
public class VueConsole implements Vue {
    
    /**
     * Affiche l'état du jeu dans la console.
     * Cette méthode ne produit pas d'affichage car le jeu s'affiche déjà
     * naturellement via les System.out.println dans les différentes classes.
     * 
     * @param jeu l'instance du jeu à afficher
     */
    @Override
    public void afficherJeu(Jeu jeu) {
        // Cette vue n'affiche rien car le jeu s'affiche déjà
        // naturellement via les System.out.println dans Jeu.java
        // On pourrait ajouter des affichages supplémentaires ici si besoin
    }
    
    /**
     * Demande une action au joueur dans la console.
     * Cette méthode ne produit pas d'action car les interactions sont déjà
     * gérées par Scanner/ScannerHybride dans JoueurPhysique.
     * 
     * @param joueur le joueur dont on attend une action
     */
    @Override
    public void demanderAction(Joueur joueur) {
        // Les actions sont déjà gérées par Scanner dans JoueurPhysique
    }
}