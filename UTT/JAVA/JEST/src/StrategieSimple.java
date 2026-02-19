import java.io.Serializable;
import java.util.List;

/**
 * Stratégie simple pour les joueurs virtuels.
 * Cette stratégie privilégie les offres visibles et prend la première carte disponible
 * chez les autres joueurs, sans analyse tactique approfondie.
 * L'ordre de priorité est : offre visible d'abord, puis offre cachée si aucune visible n'est disponible.
 */
public class StrategieSimple implements Strategie, Serializable {
    
    /** Identifiant de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    /**
     * Fait jouer le joueur virtuel en choisissant la première offre disponible.
     * Parcourt les autres joueurs et sélectionne en priorité une offre visible,
     * sinon une offre cachée. La carte choisie est ajoutée au Jest du joueur.
     * 
     * @param joueur le joueur virtuel qui doit jouer
     * @param jeu l'instance du jeu en cours
     */
    @Override
    public void jouer(JoueurVirtuel joueur, Jeu jeu) {
        List<Joueur> tousLesJoueurs = jeu.getJoueurs();
        Joueur joueurCible = null;
        Carte offreChoisie = null;

        for (Joueur autreJoueur : tousLesJoueurs) {
            if (autreJoueur != joueur) {
                if (!autreJoueur.getOffreVisible().isEmpty()) {
                    joueurCible = autreJoueur;
                    offreChoisie = autreJoueur.getOffreVisible().remove(0);
                    System.out.println(joueur.getNom() + " (virtuel) prend l'offre visible de " + joueurCible.getNom());
                    break;
                }
                else if (!autreJoueur.getOffreCachee().isEmpty()) {
                    joueurCible = autreJoueur;
                    offreChoisie = autreJoueur.getOffreCachee().remove(0);
                    System.out.println(joueur.getNom() + " (virtuel) prend l'offre cachée de " + joueurCible.getNom());
                    break;
                }
            }
        }

        if (offreChoisie != null) {
            joueur.getJest().ajouterCarte(offreChoisie);
            System.out.println(joueur.getNom() + " ajoute " + offreChoisie.getNom() + " (" + offreChoisie.getCouleur() + ") à son Jest.");
        } else {
            System.out.println(joueur.getNom() + " (virtuel) : aucune offre disponible chez les autres joueurs.");
        }
    }
}