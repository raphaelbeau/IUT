import java.util.*;
import java.io.Serializable;

/**
 * Classe principale représentant le jeu Jest.
 * Gère l'ensemble de la partie, incluant les joueurs, la pioche, les trophées,
 * le déroulement des tours et le calcul des scores finaux.
 */
public class Jeu implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Liste des joueurs participant à la partie */
    private List<Joueur> joueurs;
    
    /** La pioche de cartes du jeu */
    private Pioche pioche;
    
    /** Liste des trophées disponibles dans la partie */
    private List<Trophee> trophees;
    
    /** La variante de jeu appliquée */
    private Variante variante;
    
    /** Le visiteur de score utilisé pour calculer les points */
    private ScoreVisitor scoreVisitor;
    
    /** Nombre maximum de tours (-1 si illimité) */
    private int nombreToursMax = -1;
    
    /** L'ordre de jeu des joueurs */
    private List<Joueur> ordreJeu;
    
    /** Indique si l'ordre de jeu tourne à chaque tour */
    private boolean ordreTournant = false;
    
    /** La vue pour l'affichage (non sérialisée) */
    private transient Vue vue;

    /**
     * Constructeur du jeu.
     * 
     * @param joueurs la liste des joueurs
     * @param pioche la pioche de cartes
     * @param trophees la liste des trophées
     * @param variante la variante de règles à appliquer
     */
    public Jeu(List<Joueur> joueurs, Pioche pioche, List<Trophee> trophees, Variante variante) {
        this.joueurs = joueurs;
        this.pioche = pioche;
        this.trophees = trophees;
        this.variante = variante;
        this.variante.appliquerRegles(this);
        initialiserOrdreJeu();
    }

    /**
     * Réinitialise le jeu après un chargement depuis un fichier.
     * Recrée l'ordre de jeu et vérifie l'intégrité des structures de données.
     */
    public void reinitialiserApresChargement() {
        inittialiserOrdreJeu();
        
        for (Joueur j : joueurs) {
            if (j.getMain() == null) {
                j.getMain().clear();
            }
            if (j.getOffreVisible() == null) {
                j.getOffreVisible().clear();
            }
            if (j.getOffreCachee() == null) {
                j.getOffreCachee().clear();
            }
        }
    }

    /**
     * Initialise l'ordre de jeu avec la liste des joueurs.
     */
    public void inittialiserOrdreJeu() {
        this.ordreJeu = new ArrayList<>(joueurs);
    }

    /**
     * Définit le visiteur de score à utiliser pour calculer les points.
     * 
     * @param visitor le visiteur de score
     */
    public void setScoreVisitor(ScoreVisitor visitor) {
        this.scoreVisitor = visitor;
    }

    /**
     * Définit le nombre maximum de tours de la partie.
     * 
     * @param n le nombre maximum de tours (-1 pour illimité)
     */
    public void setNombreToursMax(int n) {
        this.nombreToursMax = n;
    }

    /**
     * Active ou désactive la rotation de l'ordre de jeu.
     * 
     * @param b true pour activer la rotation, false sinon
     */
    public void setOrdreTournant(boolean b) {
        this.ordreTournant = b;
    }

    /**
     * Vérifie si l'ordre de jeu est tournant.
     * 
     * @return true si l'ordre tourne, false sinon
     */
    public boolean isOrdreTournant() {
        return ordreTournant;
    }

    /**
     * Retourne la vue associée au jeu.
     * 
     * @return la vue du jeu
     */
    public Vue getVue() {
        return vue;
    }

    /**
     * Définit la vue pour l'affichage du jeu.
     * 
     * @param vue la vue à utiliser
     */
    public void setVue(Vue vue) {
        this.vue = vue;
    }

    /**
     * Configure le scanner hybride pour tous les joueurs physiques.
     * 
     * @param scanner le scanner hybride à utiliser
     */
    public void setScannerHybride(ScannerHybride scanner) {
        for (Joueur j : joueurs) {
            if (j instanceof JoueurPhysique) {
                ((JoueurPhysique) j).setScannerHybride(scanner);
            }
        }
    }

    /**
     * Initialise l'ordre de jeu avec la liste des joueurs.
     */
    public void initialiserOrdreJeu() {
        this.ordreJeu = new ArrayList<>(joueurs);
    }

    /**
     * Retourne l'ordre de jeu actuel.
     * 
     * @return la liste des joueurs dans l'ordre de jeu
     */
    public List<Joueur> getOrdreJeu() {
        return ordreJeu;
    }

    /**
     * Fait tourner l'ordre de jeu en déplaçant le premier joueur à la fin.
     */
    public void faireTournerOrdreJeu() {
        if (ordreJeu == null || ordreJeu.isEmpty()) return;
        Joueur premier = ordreJeu.remove(0);
        ordreJeu.add(premier);
    }

    /**
     * Retourne la liste des joueurs.
     * 
     * @return la liste des joueurs
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Retourne la pioche du jeu.
     * 
     * @return la pioche
     */
    public Pioche getPioche() {
        return pioche;
    }

    /**
     * Rafraîchit l'affichage de la vue si elle est définie.
     */
    private void rafraichirVue() {
        if (vue != null) {
            vue.afficherJeu(this);
        }
    }

    /**
     * Gère la boucle principale du jeu.
     * Exécute les tours successifs jusqu'à la fin de la partie.
     */
    public void jouer() {
        int tour = 1;
        rafraichirVue();

        while (true) {
            if (nombreToursMax > 0 && tour > nombreToursMax) {
                System.out.println("Nombre maximum de tours atteint (" + nombreToursMax + "), fin de la partie.");
                break;
            }
            
            System.out.println("");
            System.out.println("");
            System.out.println("---------------------------------------------");
            System.out.println("Tour #" + tour);

            for (Joueur j : ordreJeu) {
                while (j.getMain().size() < 2 && !pioche.estVide()) {
                    j.getMain().add(pioche.piocher());
                }
            }

            rafraichirVue();
            boolean actionJouee = false;

            // Phase d'offres
            for (Joueur j : ordreJeu) {
                if (!j.getMain().isEmpty()) {
                    if (j instanceof JoueurPhysique) {
                        int mainAvant = j.getMain().size();
                        ((JoueurPhysique) j).proposerCarte(this);
                        if (j.getMain().size() < mainAvant) {
                            actionJouee = true;
                        }
                    } else if (j instanceof JoueurVirtuel) {
                        int mainAvant = j.getMain().size();
                        ((JoueurVirtuel) j).proposerCarte();
                        if (j.getMain().size() < mainAvant) {
                            actionJouee = true;
                        }
                    }
                }
            }
            
            rafraichirVue();

            // Phase de choix
            for (Joueur j : ordreJeu) {
                boolean offreDispo = false;
                for (Joueur autre : joueurs) {
                    if (autre != j && (!autre.getOffreVisible().isEmpty() || !autre.getOffreCachee().isEmpty())) {
                        offreDispo = true;
                        break;
                    }
                }
                if (offreDispo) {
                    int avant = j.getJest().getCartes().size();
                    j.jouerTour(this);
                    if (j.getJest().getCartes().size() > avant) {
                        actionJouee = true;
                    }
                }
            }

            rafraichirVue();

            // Pioche
            for (Joueur j : ordreJeu) {
                int mainAvant = j.getMain().size();
                if (!pioche.estVide()) {
                    j.getMain().add(pioche.piocher());
                    if (j.getMain().size() > mainAvant) {
                        actionJouee = true;
                    }
                }
            }
            
            rafraichirVue();

            if (!actionJouee) {
                break;
            }
            if (ordreTournant) {
                faireTournerOrdreJeu();
            }
            tour++;
        }

        // Vidange finale
        for (Joueur j : ordreJeu) {
            while (!j.getOffreVisible().isEmpty()) {
                Carte c = j.getOffreVisible().remove(0);
                j.getJest().ajouterCarte(c);
                System.out.println("Fin : la carte " + c.getNom() + " (" + c.getCouleur() + ") ajoutée au Jest de " + j.getNom());
            }
            while (!j.getOffreCachee().isEmpty()) {
                Carte c = j.getOffreCachee().remove(0);
                j.getJest().ajouterCarte(c);
                System.out.println("Fin : la carte " + c.getNom() + " (" + c.getCouleur() + ") ajoutée au Jest de " + j.getNom());
            }
            while (!j.getMain().isEmpty()) {
                Carte c = j.getMain().remove(0);
                j.getJest().ajouterCarte(c);
                System.out.println("Fin : la carte " + c.getNom() + " (" + c.getCouleur() + ") ajoutée au Jest de " + j.getNom());
            }
        }

        rafraichirVue();
        System.out.println("La pioche est vide, fin du jeu.");
    }

    /**
     * Démarre une nouvelle partie.
     * Distribue les cartes initiales et lance le jeu.
     */
    public void demarrerJeu() {
        System.out.println("Début du jeu !");
        
        for (Joueur j : joueurs) {
            for (int i = 0; i < 2; i++) {
                j.getMain().add(pioche.piocher());
            }
        }

        rafraichirVue();
        jouer();
        afficherResultats();
    }

    /**
     * Affiche les résultats finaux de la partie.
     * Calcule les scores, établit le classement et affiche le détail des Jests.
     */
    public void afficherResultats() {
        System.out.println("");
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    🎊 FIN DE LA PARTIE 🎊                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println("");
        
        Map<Joueur, Integer> scores = new HashMap<>();
        for (Joueur j : joueurs) {
            int score = scoreVisitor.calculerScore(j.getJest());
            scores.put(j, score);
        }
        
        List<Joueur> classement = new ArrayList<>(joueurs);
        classement.sort((j1, j2) -> scores.get(j2).compareTo(scores.get(j1)));
        
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│                        🏆 CLASSEMENT 🏆                        │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");
        System.out.println("");
        
        for (int i = 0; i < classement.size(); i++) {
            Joueur j = classement.get(i);
            int score = scores.get(j);
            String medaille = "";
            String typeJoueur = (j instanceof JoueurPhysique) ? "👤" : "🤖";
            
            switch(i) {
                case 0: medaille = "🥇"; break;
                case 1: medaille = "🥈"; break;
                case 2: medaille = "🥉"; break;
                default: medaille = "  "; break;
            }
            
            System.out.printf("%s  %s %-20s : %3d points\n", medaille, typeJoueur, j.getNom(), score);
        }
        
        System.out.println("");
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("");
        System.out.println("📊 DÉTAIL DES JESTS :");
        System.out.println("");
        
        for (Joueur j : classement) {
            afficherDetailJoueur(j, scores.get(j));
        }
        
        if (vue != null) {
            vue.afficherFinPartie(scores, classement);
        }
    }

    /**
     * Affiche le détail du Jest d'un joueur avec ses cartes et son score.
     * 
     * @param joueur le joueur dont afficher le Jest
     * @param scoreTotal le score total du joueur
     */
    private void afficherDetailJoueur(Joueur joueur, int scoreTotal) {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        String typeJoueur = (joueur instanceof JoueurPhysique) ? "👤" : "🤖";
        System.out.printf("│ %s %-30s Score : %3d pts          │\n", typeJoueur, joueur.getNom(), scoreTotal);
        System.out.println("└────────────────────────────────────────────────────────────────┘");
        
        List<Carte> cartes = joueur.getJest().getCartes();
        
        if (cartes.isEmpty()) {
            System.out.println("  (Aucune carte)");
            System.out.println("");
            return;
        }
        
        Map<String, List<Carte>> cartesParCouleur = new HashMap<>();
        cartesParCouleur.put("Pique", new ArrayList<>());
        cartesParCouleur.put("Trèfle", new ArrayList<>());
        cartesParCouleur.put("Coeur", new ArrayList<>());
        cartesParCouleur.put("Carreau", new ArrayList<>());
        
        List<Carte> speciales = new ArrayList<>();
        
        for (Carte c : cartes) {
            if (c instanceof Joker || c instanceof CarteCameleon || c instanceof CarteMiroir) {
                speciales.add(c);
            } else {
                String couleur = c.getCouleur();
                if (cartesParCouleur.containsKey(couleur)) {
                    cartesParCouleur.get(couleur).add(c);
                }
            }
        }
        
        afficherCouleur("♠ Pique  ", cartesParCouleur.get("Pique"), "+");
        afficherCouleur("♣ Trèfle ", cartesParCouleur.get("Trèfle"), "+");
        afficherCouleur("♥ Coeur  ", cartesParCouleur.get("Coeur"), "?");
        afficherCouleur("♦ Carreau", cartesParCouleur.get("Carreau"), "-");
        
        if (!speciales.isEmpty()) {
            System.out.print("  ✨ Spécial : ");
            for (Carte c : speciales) {
                if (c instanceof Joker) {
                    System.out.print("Joker ");
                } else if (c instanceof CarteCameleon) {
                    System.out.print("Caméléon ");
                } else if (c instanceof CarteMiroir) {
                    System.out.print("Miroir ");
                }
            }
            System.out.println();
        }
        
        System.out.println("  ──────────────────────────────────────");
        System.out.printf("  💯 Total : %d cartes → %d points\n", cartes.size(), scoreTotal);
        System.out.println("");
    }

    /**
     * Affiche les cartes d'une couleur donnée avec leur total de points.
     * 
     * @param symbole le symbole de la couleur à afficher
     * @param cartes la liste des cartes de cette couleur
     * @param signe le signe indiquant l'effet sur le score (+, -, ?)
     */
    private void afficherCouleur(String symbole, List<Carte> cartes, String signe) {
        if (cartes.isEmpty()) {
            return;
        }
        
        System.out.print("  " + symbole + " (" + signe + ") : ");
        
        for (int i = 0; i < cartes.size(); i++) {
            Carte c = cartes.get(i);
            System.out.print(c.getNom());
            if (i < cartes.size() - 1) {
                System.out.print(", ");
            }
        }
        
        int somme = 0;
        for (Carte c : cartes) {
            somme += c.getValeur();
        }
        System.out.printf(" [%d pts]\n", somme);
    }

    /**
     * Vérifie si toutes les mains et offres des joueurs sont vides.
     * 
     * @return true si toutes les mains et offres sont vides, false sinon
     */
    public boolean mainEtOffresVides() {
        for (Joueur j : joueurs) {
            if (!j.getMain().isEmpty() || !j.getOffreVisible().isEmpty() || !j.getOffreCachee().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}