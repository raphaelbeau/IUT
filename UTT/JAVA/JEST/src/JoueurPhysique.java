import java.util.*;
import java.io.Serializable;

/**
 * Classe représentant un joueur humain physique.
 * Ce joueur interagit avec le jeu via des entrées utilisateur (console ou interface graphique).
 * Il hérite de la classe abstraite Joueur.
 */
public class JoueurPhysique extends Joueur implements Serializable {
    
    /** Identifiant de version pour la sérialisation */
    private static final long serialVersionUID = 1L;
    
    /** Scanner hybride pour gérer les entrées console et graphiques (non sérialisé) */
    private transient ScannerHybride scannerHybride;
    
    /**
     * Constructeur du joueur physique.
     * 
     * @param nom le nom du joueur
     */
    public JoueurPhysique(String nom) { 
        super(nom); 
    }
    
    /**
     * Définit le scanner hybride pour ce joueur.
     * 
     * @param scanner le scanner hybride à utiliser
     */
    public void setScannerHybride(ScannerHybride scanner) {
        this.scannerHybride = scanner;
    }

    /**
     * Joue un tour pour ce joueur physique.
     * Permet au joueur de choisir une carte dans l'offre d'un autre joueur,
     * ou de sauvegarder la partie en cours.
     * 
     * @param jeu l'instance du jeu en cours
     */
    @Override
    public void jouerTour(Jeu jeu) {
        System.out.println("\n" + nom + ", c'est ton tour de choisir une carte dans l'offre d'un autre joueur.");
        
        if (jeu.getVue() != null) {
            jeu.getVue().demanderSauvegarde(this);
        }
        
        System.out.print("Tape 'save' pour sauvegarder la partie, ou autre chose pour continuer : ");
        
        String rep;
        if (scannerHybride != null) {
            rep = scannerHybride.next();
        } else {
            Scanner scanner = new Scanner(System.in);
            rep = scanner.next();
        }
        
        if (rep.equalsIgnoreCase("save")) {
            System.out.print("Nom de la sauvegarde : ");
            String nomSave;
            if (scannerHybride != null) {
                nomSave = scannerHybride.next();
            } else {
                Scanner scanner = new Scanner(System.in);
                nomSave = scanner.next();
            }
            try {
                SauvegardeJeu.sauvegarderJeu(jeu, nomSave);
                System.out.println("Partie sauvegardée dans saves/" + nomSave + ".ser");
            } catch (Exception e) {
                System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
            }
            return;
        }

        Map<Integer, Carte> choixPossibles = new HashMap<>();
        Map<Integer, Joueur> proprietaires = new HashMap<>();
        Map<Integer, Boolean> estVisible = new HashMap<>();
        int index = 1;

        for (Joueur autre : jeu.getJoueurs()) {
            if (autre == this) continue;
            boolean afficheUneOffre = false;
            System.out.println("Offre de " + autre.getNom() + " :");
            
            if (!autre.getOffreVisible().isEmpty()) {
                Carte c = autre.getOffreVisible().get(0);
                System.out.println("   " + index + " - " + c.getNom() + " (" + c.getCouleur() + ") [VISIBLE]");
                choixPossibles.put(index, c);
                proprietaires.put(index, autre);
                estVisible.put(index, true);
                index++;
                afficheUneOffre = true;
            }
            
            if (!autre.getOffreCachee().isEmpty()) {
                System.out.println("   " + index + " - Carte cachée [CACHEE]");
                Carte c = autre.getOffreCachee().get(0);
                choixPossibles.put(index, c);
                proprietaires.put(index, autre);
                estVisible.put(index, false);
                index++;
                afficheUneOffre = true;
            }
            if (!afficheUneOffre) {
                System.out.println("   (aucune carte disponible)");
            }
        }

        if (choixPossibles.isEmpty()) {
            System.out.println("Il n'y a aucune carte à prendre.");
            return;
        }
        
        if (jeu.getVue() != null) {
            jeu.getVue().demanderChoixCarte(this, choixPossibles, proprietaires, estVisible);
        }

        int choix = -1;
        System.out.print("Entre le numéro de la carte que tu veux prendre : ");
        while (!choixPossibles.containsKey(choix)) {
            try {
                String input;
                if (scannerHybride != null) {
                    input = scannerHybride.next();
                } else {
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.next();
                }
                choix = Integer.parseInt(input);
                if (!choixPossibles.containsKey(choix)) {
                    System.out.print("Choix invalide, essaye encore : ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Choix invalide, essaye encore : ");
            }
        }

        Carte cartePrise = choixPossibles.get(choix);
        Joueur proprietaire = proprietaires.get(choix);
        boolean priseVisible = estVisible.get(choix);

        if (priseVisible) {
            proprietaire.getOffreVisible().remove(cartePrise);
            System.out.println("Tu prends la carte visible : " + cartePrise.getNom() + " (" + cartePrise.getCouleur() + ").");
        } else {
            proprietaire.getOffreCachee().remove(cartePrise);
            System.out.println("Tu prends la carte cachée : " + cartePrise.getNom() + " (" + cartePrise.getCouleur() + ").");
        }

        jest.ajouterCarte(cartePrise);
    }

    /**
     * Permet au joueur physique de proposer des cartes aux autres joueurs.
     * Le joueur choisit deux cartes de sa main : une visible et une cachée.
     * Si le joueur n'a qu'une seule carte, celle-ci est proposée comme visible.
     * 
     * @param jeu l'instance du jeu en cours
     */
    public void proposerCarte(Jeu jeu) {
        offreVisible.clear();
        offreCachee.clear();

        if (main.size() == 0) {
            System.out.println(nom + " n'a plus de carte à proposer.");
            return;
        }

        if (main.size() == 1) {
            System.out.println(nom + ", il te reste une seule carte.");
            Carte carte = main.remove(0);
            offreVisible.add(carte);
            System.out.println("Tu proposes " + carte.getNom() + " (" + carte.getCouleur() + ") comme carte visible.");
            return;
        }

        System.out.println(nom + ", choisis DEUX cartes à proposer (une visible, une cachée) :");
        for (int i = 0; i < main.size(); i++) {
            Carte c = main.get(i);
            System.out.println((i+1) + " : " + c.getNom() + " (" + c.getCouleur() + ")");
        }

        List<Carte> mainCopie = new ArrayList<>(main);
        
        if (jeu.getVue() != null) {
            jeu.getVue().demanderPropositionCarte(this, mainCopie, "visible");
        }
        
        int choixVisible = -1;
        System.out.print("Numéro de la carte à proposer comme visible : ");
        while (choixVisible < 1 || choixVisible > main.size()) {
            try {
                String input;
                if (scannerHybride != null) {
                    input = scannerHybride.next();
                } else {
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.next();
                }
                choixVisible = Integer.parseInt(input);
                if (choixVisible < 1 || choixVisible > main.size()) {
                    System.out.print("Choix invalide, essaie encore : ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entre un nombre valide : ");
            }
        }
        Carte carteVisible = main.remove(choixVisible-1);
        offreVisible.add(carteVisible);

        System.out.println("Main restante pour la carte cachée :");
        for (int i = 0; i < main.size(); i++) {
            Carte c = main.get(i);
            System.out.println((i+1) + " : " + c.getNom() + " (" + c.getCouleur() + ")");
        }
        
        mainCopie = new ArrayList<>(main);
        if (jeu.getVue() != null) {
            jeu.getVue().demanderPropositionCarte(this, mainCopie, "cachee");
        }
        
        int choixCachee = -1;
        System.out.print("Numéro de la carte à proposer comme cachée : ");
        while (choixCachee < 1 || choixCachee > main.size()) {
            try {
                String input;
                if (scannerHybride != null) {
                    input = scannerHybride.next();
                } else {
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.next();
                }
                choixCachee = Integer.parseInt(input);
                if (choixCachee < 1 || choixCachee > main.size()) {
                    System.out.print("Choix invalide, essaie encore : ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entre un nombre valide : ");
            }
        }
        Carte carteCachee = main.remove(choixCachee-1);
        offreCachee.add(carteCachee);

        System.out.println("Tu proposes " + carteVisible.getNom() + " (" + carteVisible.getCouleur() + ") (visible) et " +
                           carteCachee.getNom() + " (" + carteCachee.getCouleur() + ") (cachée) comme offre.");
    }
}