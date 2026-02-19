import java.util.Random;
import java.io.Serializable;

/**
 * Stratégie aléatoire pour les joueurs virtuels.
 * Cette stratégie sélectionne une carte au hasard dans la main du joueur
 * sans considération tactique ou stratégique.
 */
public class StrategieAleatoire implements Strategie, Serializable {
    
    /** Identifiant de version pour la sérialisation */
    private static final long serialVersionUID = 1L;
    
    /**
     * Fait jouer le joueur virtuel en sélectionnant aléatoirement une carte parmi les offres disponibles.
     * Collecte toutes les offres (visibles et cachées) des autres joueurs,
     * en choisit une au hasard, et l'ajoute au Jest du joueur.
     * 
     * @param joueur le joueur virtuel qui doit jouer
     * @param jeu l'instance du jeu en cours
     */
    @Override
    public void jouer(JoueurVirtuel joueur, Jeu jeu) {
        Random rand = new Random();
        java.util.List<Offre> offresDisponibles = new java.util.ArrayList<>();
        
        // Collecter toutes les offres disponibles des autres joueurs
        for (Joueur autreJoueur : jeu.getJoueurs()) {
            if (autreJoueur != joueur) {
                // Offres visibles
                for (Carte carte : autreJoueur.getOffreVisible()) {
                    offresDisponibles.add(new Offre(autreJoueur, carte, true));
                }
                // Offres cachées
                for (Carte carte : autreJoueur.getOffreCachee()) {
                    offresDisponibles.add(new Offre(autreJoueur, carte, false));
                }
            }
        }
        
        // Si aucune offre disponible, ne rien faire
        if (offresDisponibles.isEmpty()) {
            System.out.println(joueur.getNom() + " (virtuel, aléatoire) : aucune offre disponible.");
            return;
        }
        
        // Choisir aléatoirement une offre
        int index = rand.nextInt(offresDisponibles.size());
        Offre offreChoisie = offresDisponibles.get(index);
        
        // Retirer la carte de l'offre du propriétaire
        if (offreChoisie.isVisible()) {
            offreChoisie.getProprietaire().getOffreVisible().remove(offreChoisie.getCarte());
            System.out.println(joueur.getNom() + " (virtuel, aléatoire) prend l'offre visible de " + 
                             offreChoisie.getProprietaire().getNom());
        } else {
            offreChoisie.getProprietaire().getOffreCachee().remove(offreChoisie.getCarte());
            System.out.println(joueur.getNom() + " (virtuel, aléatoire) prend l'offre cachée de " + 
                             offreChoisie.getProprietaire().getNom());
        }
        
        // Ajouter la carte au Jest du joueur
        joueur.getJest().ajouterCarte(offreChoisie.getCarte());
        System.out.println(joueur.getNom() + " ajoute " + offreChoisie.getCarte().getNom() + 
                         " (" + offreChoisie.getCarte().getCouleur() + ") à son Jest.");
    }
}