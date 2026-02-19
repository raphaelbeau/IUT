import java.io.Serializable;

/**
 * Classe représentant un joueur virtuel (IA).
 * Ce joueur utilise une stratégie automatique pour prendre ses décisions de jeu.
 * Il hérite de la classe abstraite Joueur.
 */
public class JoueurVirtuel extends Joueur implements Serializable {
    
    /** La stratégie de jeu utilisée par ce joueur virtuel */
    private Strategie strategie;
    
    /** Identifiant de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur du joueur virtuel avec nom et stratégie.
     * 
     * @param nom le nom du joueur virtuel
     * @param strategie la stratégie de jeu à utiliser
     */
    public JoueurVirtuel(String nom, Strategie strategie) {
        super(nom);
        this.strategie = strategie;
    }

    /**
     * Joue un tour pour ce joueur virtuel.
     * Délègue la décision à la stratégie configurée.
     * 
     * @param jeu l'instance du jeu en cours
     */
    @Override
    public void jouerTour(Jeu jeu) {
        strategie.jouer(this, jeu);
    }

    /**
     * Constructeur par défaut du joueur virtuel.
     * Crée un joueur nommé "IA" sans stratégie définie.
     */
    public JoueurVirtuel() {
        super("IA");
        this.strategie = null;
    }

    /**
     * Permet au joueur virtuel de proposer automatiquement des cartes.
     * Choisit deux cartes de sa main : une visible et une cachée.
     * Si le joueur n'a qu'une seule carte, celle-ci est proposée comme visible.
     * La sélection est naïve : les deux premières cartes de la main sont choisies.
     */
    public void proposerCarte() {
        offreVisible.clear();
        offreCachee.clear();

        if (main.size() == 0) {
            System.out.println(nom + " n'a plus de carte à proposer.");
            return;
        }

        if (main.size() == 1) {
            Carte seule = main.remove(0);
            offreVisible.add(seule);
            System.out.println(nom + " propose " + seule.getNom() + " (" + seule.getCouleur() + ") comme visible.");
            return;
        }

        Carte vis = main.remove(0);
        Carte cachee = main.remove(0);
        offreVisible.add(vis);
        offreCachee.add(cachee);

        System.out.println(nom + " propose " + vis.getNom() + " (" + vis.getCouleur() + ") (visible) et " +
                           cachee.getNom() + " (" + cachee.getCouleur() + ") (cachée).");
    }
}