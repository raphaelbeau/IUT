import java.util.*;

/**
 * Classe principale du jeu Jest.
 * Gère le menu principal, la création de parties, le chargement de sauvegardes,
 * la configuration des joueurs, variantes et extensions.
 */
public class Main {

    /**
     * Permet de choisir une stratégie pour un joueur virtuel.
     * Affiche un menu et retourne la stratégie sélectionnée.
     * 
     * @param scanner le scanner hybride pour lire les entrées
     * @return la stratégie choisie
     */
    public static Strategie choisirStrategie(ScannerHybride scanner) {
        System.out.println("Choisissez la stratégie pour ce joueur virtuel :");
        System.out.println("1 - StrategieSimple");
        System.out.println("2 - StrategieAleatoire");

        int choix = 0;
        while (choix < 1 || choix > 2) {
            System.out.print("Votre choix : ");
            try {
                choix = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                // Ignorer
            }
            if (choix < 1 || choix > 2) {
                System.out.println("Numéro invalide, recommencez.");
            }
        }

        switch (choix) {
            case 1: return new StrategieSimple();
            case 2: return new StrategieAleatoire();
            default: return new StrategieSimple();
        }
    }

    /**
     * Point d'entrée principal du programme.
     * Gère le menu principal, la création de nouvelles parties ou le chargement de sauvegardes.
     * Configure les joueurs, les variantes, les extensions et démarre le jeu.
     * 
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        ScannerHybride scannerHybride = new ScannerHybride();
        VueGraphique vueGraphique = new VueGraphique(scannerHybride);
        
        ArrayList<Joueur> joueurs = new ArrayList<>();
        Jeu jeu;

        System.out.println("Bienvenue dans Jest !");
        System.out.println("1 - Nouvelle partie");
        System.out.println("2 - Charger une partie");

        int choixDebut = 0;
        while (choixDebut < 1 || choixDebut > 2) {
            System.out.print("Votre choix : ");
            String input = scannerHybride.next();
            try {
                choixDebut = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Ignorer
            }
        }

        if (choixDebut == 2) {
            System.out.print("Nom de la sauvegarde à charger : ");
            String nomSave = scannerHybride.next();
            try {
                jeu = SauvegardeJeu.chargerJeu(nomSave);
                System.out.println("Partie chargée depuis saves/" + nomSave + ".ser");
                jeu.reinitialiserApresChargement();
                
                VueComposite vueComposite = new VueComposite();
                vueComposite.ajouterVue(new VueConsole());
                vueComposite.ajouterVue(vueGraphique);
                jeu.setVue(vueComposite);
                jeu.setScannerHybride(scannerHybride);
                jeu.jouer();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Impossible de charger la sauvegarde '" + nomSave + "'. On démarre une nouvelle partie.");
            }
        }

        System.out.println("----------------------");
        System.out.println("Tapez '1' pour ajouter un joueur.");
        System.out.println("Tapez 'go' pour commencer la partie.");

        while (true) {
            System.out.print("Votre choix : ");
            String entree = scannerHybride.next();
            System.out.println("");

            if (entree.equalsIgnoreCase("go")) {
                if (joueurs.size() < 2) {
                    System.out.println("Il faut au moins 2 joueurs pour jouer !");
                    continue;
                } else {
                    break;
                }
            }

            if (!entree.equals("1")) {
                System.out.println("Commande inconnue. Tapez '1' pour ajouter un joueur ou 'go' pour commencer.");
                continue;
            }

            System.out.print("Ce joueur est-il virtuel (v) ou physique (p) ? ");
            String type = scannerHybride.next();
            System.out.print("Nom du joueur : ");
            String nom = scannerHybride.next();
            System.out.print("");

            if (type.equalsIgnoreCase("v")) {
                Strategie strat = choisirStrategie(scannerHybride);
                joueurs.add(new JoueurVirtuel(nom, strat));
            } else if (type.equalsIgnoreCase("p")) {
                joueurs.add(new JoueurPhysique(nom));
            } else {
                System.out.println("Type inconnu, joueur ignoré.");
                continue;
            }

            System.out.println("Joueur " + nom + " ajouté.\n");
            System.out.println("----------------------");
        }

        List<Variante> variantesDispo = new ArrayList<>();
        variantesDispo.add(new VarianteClassique());
        variantesDispo.add(new VarianteDeuxTours(5));
        variantesDispo.add(new VarianteOrdreTournant());

        System.out.println("");
        System.out.println("----------------------");
        System.out.println("CHOIX VARIANTE");
        for (int i = 0; i < variantesDispo.size(); i++) {
            System.out.println((i + 1) + " - " + variantesDispo.get(i).getNom());
        }

        int choixVariante = 0;
        while (choixVariante < 1 || choixVariante > variantesDispo.size()) {
            System.out.print("Votre choix : ");
            try {
                choixVariante = Integer.parseInt(scannerHybride.next());
            } catch (NumberFormatException e) {
                // Ignorer
            }
        }
        Variante variante = variantesDispo.get(choixVariante - 1);

        List<Extension> extensionsDispo = new ArrayList<>();
        extensionsDispo.add(new ExtensionCameleon());
        extensionsDispo.add(new ExtensionMiroir());

        List<Extension> extensionsChoisies = new ArrayList<>();
        System.out.println("");
        System.out.println("----------------------");
        System.out.println("CHOIX EXTENSION");
        System.out.println("0 - Aucune extension");
        for (int i = 0; i < extensionsDispo.size(); i++) {
            System.out.println((i + 1) + " - " + extensionsDispo.get(i).getNom());
        }

        int choixExt = -1;
        while (choixExt < 0 || choixExt > extensionsDispo.size()) {
            System.out.print("Votre choix : ");
            try {
                choixExt = Integer.parseInt(scannerHybride.next());
            } catch (NumberFormatException e) {
                // Ignorer
            }
            if (choixExt < 0 || choixExt > extensionsDispo.size()) {
                System.out.println("Numéro invalide, recommencez.");
            }
        }

        boolean extCameleonActive = false;
        boolean extMiroirActive = false;

        if (choixExt == 0) {
            // aucune extension
        } else {
            Extension extChoisie = extensionsDispo.get(choixExt - 1);
            extensionsChoisies.add(extChoisie);
            if (extChoisie instanceof ExtensionCameleon) {
                extCameleonActive = true;
            } else if (extChoisie instanceof ExtensionMiroir) {
                extMiroirActive = true;
            }
        }

        ArrayList<Carte> cartes = Pioche.creerJeuJest();
        for (Extension ext : extensionsChoisies) {
            cartes.addAll(ext.creerCartes());
        }
        Collections.shuffle(cartes);
        Pioche pioche = new Pioche(cartes);

        ArrayList<Trophee> trophees = new ArrayList<>();
        jeu = new Jeu(joueurs, pioche, trophees, variante);

        variante.appliquerRegles(jeu);

        ScoreVisitor scoreVisitor;
        if (extCameleonActive) {
            scoreVisitor = new ScoreVisitorCameleon();
        } else if (extMiroirActive) {
            scoreVisitor = new ScoreVisitorMiroir();
        } else {
            scoreVisitor = new ScoreVisitorBase();
        }
        jeu.setScoreVisitor(scoreVisitor);

        for (Extension ext : extensionsChoisies) {
            ext.appliquerSurJeu(jeu);
        }

        VueComposite vueComposite = new VueComposite();
        vueComposite.ajouterVue(new VueConsole());
        vueComposite.ajouterVue(vueGraphique);
        jeu.setVue(vueComposite);
        
        jeu.setScannerHybride(scannerHybride);

        jeu.demarrerJeu();
    }
}