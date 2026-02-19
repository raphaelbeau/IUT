import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Vue graphique pour le jeu Jest utilisant Swing.
 * Fournit une interface utilisateur graphique interactive avec des boutons pour toutes les actions du jeu.
 * S'intègre avec un ScannerHybride pour coordonner les entrées entre l'interface graphique et la console.
 * Affiche l'état du jeu, les cartes des joueurs, et gère les interactions via des boutons cliquables.
 */
public class VueGraphique extends JFrame implements Vue {
    
    /** Panel principal contenant les zones d'affichage */
    private JPanel panelPrincipal;
    
    /** Zone de texte pour afficher les informations générales */
    private JTextArea zoneInfo;
    
    /** Panel contenant les boutons interactifs */
    private JPanel panelBoutons;
    
    /** Scanner hybride pour gérer les entrées graphiques et console */
    private ScannerHybride scannerHybride;
    
    /** État actuel du menu de l'interface */
    private String etatMenu = "MENU_PRINCIPAL";
    
    /** Compteur de joueurs ajoutés */
    private int nombreJoueurs = 0;
    
    /**
     * Constructeur par défaut de la vue graphique.
     */
    public VueGraphique() {
        this(null);
    }
    
    /**
     * Constructeur de la vue graphique avec scanner hybride.
     * 
     * @param scannerHybride le scanner hybride pour gérer les entrées
     */
    public VueGraphique(ScannerHybride scannerHybride) {
        
        this.scannerHybride = scannerHybride;
        
        if (scannerHybride != null) {
            scannerHybride.addInputListener((input, fromGraphic) -> {
                SwingUtilities.invokeLater(() -> {
                    mettreAJourInterface(input, fromGraphic);
                });
            });
        }
        
        setTitle("Jest - Vue Graphique");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new FlowLayout());
        panelBoutons.setBackground(new Color(70, 130, 180));
        add(panelBoutons, BorderLayout.NORTH);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(34, 139, 34));
        
        zoneInfo = new JTextArea(3, 50);
        zoneInfo.setEditable(false);
        zoneInfo.setFont(new Font("Monospaced", Font.BOLD, 12));
        zoneInfo.setBackground(new Color(240, 240, 240));
        JScrollPane scrollInfo = new JScrollPane(zoneInfo);
        panelPrincipal.add(scrollInfo, BorderLayout.NORTH);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        afficherBoutonsMenu();
        
        setVisible(true);
    }
    
    /**
     * Met à jour l'interface selon l'entrée reçue du scanner hybride.
     * 
     * @param input le texte de l'entrée
     * @param fromGraphic true si l'entrée provient de l'interface graphique
     */
    private void mettreAJourInterface(String input, boolean fromGraphic) {
        if (fromGraphic) {
            return;
        }
        
        switch (etatMenu) {
            case "MENU_PRINCIPAL":
                if (input.equals("1")) {
                    etatMenu = "AJOUT_JOUEURS";
                    afficherBoutonsJoueurs();
                }
                break;
                
            case "AJOUT_JOUEURS":
                if (input.equalsIgnoreCase("go")) {
                    etatMenu = "VARIANTE";
                    afficherBoutonsVariante();
                } else if (input.equals("1")) {
                    nombreJoueurs++;
                }
                break;
                
            case "VARIANTE":
                if (input.matches("[1-3]")) {
                    etatMenu = "EXTENSION";
                    afficherBoutonsExtension();
                }
                break;
                
            case "EXTENSION":
                if (input.matches("[0-2]")) {
                    etatMenu = "JEU_EN_COURS";
                    panelBoutons.removeAll();
                    panelBoutons.revalidate();
                    panelBoutons.repaint();
                }
                break;
        }
    }
    
    /**
     * Affiche les boutons du menu principal (nouvelle partie / charger partie).
     */
    private void afficherBoutonsMenu() {
        etatMenu = "MENU_PRINCIPAL";
        panelBoutons.removeAll();
        
        JButton btnNouvelle = new JButton("🎮 Nouvelle partie");
        btnNouvelle.setFont(new Font("Arial", Font.BOLD, 14));
        btnNouvelle.addActionListener(e -> {
            if (scannerHybride != null) {
                scannerHybride.envoyerDepuisGraphique("1");
                etatMenu = "AJOUT_JOUEURS";
                afficherBoutonsJoueurs();
            }
        });
        
        JButton btnCharger = new JButton("💾 Charger partie");
        btnCharger.setFont(new Font("Arial", Font.BOLD, 14));
        btnCharger.addActionListener(e -> {
            if (scannerHybride != null) {
                scannerHybride.envoyerDepuisGraphique("2");
                
                String nomSave = JOptionPane.showInputDialog(this, "Nom de la sauvegarde à charger :");
                if (nomSave != null && !nomSave.isEmpty()) {
                    scannerHybride.envoyerDepuisGraphique(nomSave);
                }
            }
        });
        
        panelBoutons.add(btnNouvelle);
        panelBoutons.add(btnCharger);
        
        panelBoutons.revalidate();
        panelBoutons.repaint();
    }
    
    /**
     * Affiche les boutons pour ajouter des joueurs et commencer la partie.
     */
    private void afficherBoutonsJoueurs() {
        etatMenu = "AJOUT_JOUEURS";
        panelBoutons.removeAll();
        
        JButton btnAjouterJoueur = new JButton("➕ Ajouter un joueur");
        btnAjouterJoueur.setFont(new Font("Arial", Font.BOLD, 14));
        btnAjouterJoueur.addActionListener(e -> {
            if (scannerHybride != null) {
                scannerHybride.envoyerDepuisGraphique("1");
                
                String[] options = {"Physique", "Virtuel"};
                int choix = JOptionPane.showOptionDialog(this,
                    "Type de joueur ?",
                    "Nouveau joueur",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
                
                if (choix == -1) return;
                
                if (choix == 0) {
                    scannerHybride.envoyerDepuisGraphique("p");
                } else {
                    scannerHybride.envoyerDepuisGraphique("v");
                }
                
                String nom = JOptionPane.showInputDialog(this, "Nom du joueur :");
                if (nom != null && !nom.isEmpty()) {
                    scannerHybride.envoyerDepuisGraphique(nom);
                    
                    if (choix == 1) {
                        String[] strategies = {"1 - StrategieSimple", "2 - StrategieAleatoire"};
                        int stratChoix = JOptionPane.showOptionDialog(this,
                            "Choisissez la stratégie :",
                            "Stratégie du joueur virtuel",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, strategies, strategies[0]);
                        
                        if (stratChoix != -1) {
                            scannerHybride.envoyerDepuisGraphique(String.valueOf(stratChoix + 1));
                        }
                    }
                    
                    nombreJoueurs++;
                } else {
                    return;
                }
            }
        });
        
        JButton btnCommencer = new JButton("▶️ Commencer la partie");
        btnCommencer.setFont(new Font("Arial", Font.BOLD, 14));
        btnCommencer.setBackground(new Color(50, 205, 50));
        btnCommencer.addActionListener(e -> {
            if (scannerHybride != null) {
                if (nombreJoueurs < 2) {
                    JOptionPane.showMessageDialog(this,
                        "Il faut au moins 2 joueurs pour jouer !",
                        "Erreur",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                scannerHybride.envoyerDepuisGraphique("go");
                etatMenu = "VARIANTE";
                afficherBoutonsVariante();
            }
        });
        
        panelBoutons.add(btnAjouterJoueur);
        panelBoutons.add(btnCommencer);
        
        panelBoutons.revalidate();
        panelBoutons.repaint();
    }
    
    /**
     * Affiche les boutons pour choisir la variante de jeu.
     */
    private void afficherBoutonsVariante() {
        etatMenu = "VARIANTE";
        panelBoutons.removeAll();
        
        JLabel label = new JLabel("Choisir la variante : ");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        panelBoutons.add(label);
        
        String[] variantes = {"Classique", "Deux Tours (5 max)", "Ordre Tournant"};
        for (int i = 0; i < variantes.length; i++) {
            final int index = i;
            JButton btn = new JButton((i+1) + " - " + variantes[i]);
            btn.addActionListener(e -> {
                if (scannerHybride != null) {
                    scannerHybride.envoyerDepuisGraphique(String.valueOf(index + 1));
                    etatMenu = "EXTENSION";
                    afficherBoutonsExtension();
                }
            });
            panelBoutons.add(btn);
        }
        
        panelBoutons.revalidate();
        panelBoutons.repaint();
    }
    
    /**
     * Affiche les boutons pour choisir l'extension du jeu.
     */
    private void afficherBoutonsExtension() {
        etatMenu = "EXTENSION";
        panelBoutons.removeAll();
        
        JLabel label = new JLabel("Choisir l'extension : ");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        panelBoutons.add(label);
        
        String[] extensions = {"Aucune", "Cameleon", "Miroir"};
        for (int i = 0; i < extensions.length; i++) {
            final int index = i;
            JButton btn = new JButton(i + " - " + extensions[i]);
            btn.addActionListener(e -> {
                if (scannerHybride != null) {
                    scannerHybride.envoyerDepuisGraphique(String.valueOf(index));
                    etatMenu = "JEU_EN_COURS";
                    panelBoutons.removeAll();
                    panelBoutons.revalidate();
                    panelBoutons.repaint();
                }
            });
            panelBoutons.add(btn);
        }
        
        panelBoutons.revalidate();
        panelBoutons.repaint();
    }
    
    /**
     * Affiche l'état actuel du jeu avec les joueurs et leurs cartes.
     * 
     * @param jeu l'instance du jeu à afficher
     */
    @Override
    public void afficherJeu(Jeu jeu) {
        SwingUtilities.invokeLater(() -> {
            panelPrincipal.removeAll();
            panelPrincipal.add(new JScrollPane(zoneInfo), BorderLayout.NORTH);
            
            int cartesRestantes = jeu.getPioche().getTaille();
            zoneInfo.setText("=== JEU JEST ===\n");
            zoneInfo.append("Cartes restantes dans la pioche : " + cartesRestantes + "\n");
            zoneInfo.append("Nombre de joueurs : " + jeu.getJoueurs().size());
            
            JPanel panelJoueurs = new JPanel();
            panelJoueurs.setLayout(new GridLayout(0, 1, 10, 10));
            panelJoueurs.setBackground(new Color(34, 139, 34));
            panelJoueurs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            for (Joueur joueur : jeu.getJoueurs()) {
                JPanel panelJoueur = creerPanelJoueur(joueur);
                panelJoueurs.add(panelJoueur);
            }
            
            JScrollPane scrollJoueurs = new JScrollPane(panelJoueurs);
            panelPrincipal.add(scrollJoueurs, BorderLayout.CENTER);
            
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });
    }
    
    /**
     * Crée un panel d'affichage pour un joueur avec ses cartes.
     * 
     * @param joueur le joueur à afficher
     * @return le panel créé
     */
    private JPanel creerPanelJoueur(Joueur joueur) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        String typeJoueur = (joueur instanceof JoueurPhysique) ? "👤" : "🤖";
        JLabel labelNom = new JLabel(typeJoueur + " " + joueur.getNom(), SwingConstants.CENTER);
        labelNom.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelNom, BorderLayout.NORTH);
        
        JPanel panelContenu = new JPanel();
        panelContenu.setLayout(new GridLayout(1, 4, 10, 0));
        panelContenu.setBackground(Color.WHITE);
        
        JPanel panelMain = creerPanelCartes("Main (" + joueur.getMain().size() + ")", 
                                            joueur.getMain(), true);
        panelContenu.add(panelMain);
        
        JPanel panelOffreVisible = creerPanelCartes("Offre Visible", 
                                                     joueur.getOffreVisible(), true);
        panelOffreVisible.setBackground(new Color(255, 255, 200));
        panelContenu.add(panelOffreVisible);
        
        JPanel panelOffreCachee = creerPanelCartes("Offre Cachée (" + joueur.getOffreCachee().size() + ")", 
                                                    joueur.getOffreCachee(), false);
        panelOffreCachee.setBackground(new Color(200, 200, 200));
        panelContenu.add(panelOffreCachee);
        
        JPanel panelJest = creerPanelCartes("Jest (" + joueur.getJest().getCartes().size() + ")", 
                                            joueur.getJest().getCartes(), true);
        panelJest.setBackground(new Color(200, 255, 200));
        panelContenu.add(panelJest);
        
        panel.add(panelContenu, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crée un panel pour afficher une liste de cartes.
     * 
     * @param titre le titre du panel
     * @param cartes la liste des cartes à afficher
     * @param afficherCartes true pour afficher les cartes, false pour les masquer
     * @return le panel créé
     */
    private JPanel creerPanelCartes(String titre, List<Carte> cartes, boolean afficherCartes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titre));
        
        if (cartes.isEmpty()) {
            JLabel vide = new JLabel("Vide", SwingConstants.CENTER);
            vide.setForeground(Color.GRAY);
            panel.add(vide, BorderLayout.CENTER);
        } else {
            JPanel panelCartes = new JPanel();
            panelCartes.setLayout(new BoxLayout(panelCartes, BoxLayout.Y_AXIS));
            
            for (Carte carte : cartes) {
                JLabel labelCarte;
                if (afficherCartes) {
                    labelCarte = creerLabelCarte(carte);
                } else {
                    labelCarte = new JLabel("🂠 Carte cachée");
                    labelCarte.setFont(new Font("Arial", Font.PLAIN, 12));
                }
                labelCarte.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelCartes.add(labelCarte);
            }
            
            JScrollPane scroll = new JScrollPane(panelCartes);
            scroll.setPreferredSize(new Dimension(200, 100));
            panel.add(scroll, BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    /**
     * Crée un label pour afficher une carte.
     * 
     * @param carte la carte à afficher
     * @return le label créé
     */
    private JLabel creerLabelCarte(Carte carte) {
        String symbole = getSymboleCouleur(carte.getCouleur());
        Color couleur = getCouleurCarte(carte.getCouleur());
        
        JLabel label = new JLabel(carte.getNom() + " " + symbole);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(couleur);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        
        return label;
    }
    
    /**
     * Retourne le symbole Unicode correspondant à une couleur de carte.
     * 
     * @param couleur la couleur de la carte
     * @return le symbole Unicode
     */
    private String getSymboleCouleur(String couleur) {
        switch (couleur) {
            case "Coeur": return "♥";
            case "Carreau": return "♦";
            case "Pique": return "♠";
            case "Trèfle": return "♣";
            default: return "?";
        }
    }
    
    /**
     * Retourne la couleur d'affichage correspondant à une couleur de carte.
     * 
     * @param couleur la couleur de la carte
     * @return la couleur d'affichage
     */
    private Color getCouleurCarte(String couleur) {
        switch (couleur) {
            case "Coeur":
            case "Carreau":
                return Color.RED;
            case "Pique":
            case "Trèfle":
                return Color.BLACK;
            default:
                return Color.GRAY;
        }
    }
    
    /**
     * Demande une action au joueur (non implémenté dans cette version).
     * 
     * @param joueur le joueur dont on attend une action
     */
    @Override
    public void demanderAction(Joueur joueur) {
    }
    
    /**
     * Affiche des boutons pour permettre au joueur de choisir une carte parmi les offres disponibles.
     * 
     * @param joueur le joueur qui doit choisir
     * @param choixPossibles map associant un numéro à chaque carte disponible
     * @param proprietaires map associant un numéro au joueur propriétaire de la carte
     * @param estVisible map indiquant si chaque carte est visible ou cachée
     */
    @Override
    public void demanderChoixCarte(Joueur joueur, Map<Integer, Carte> choixPossibles, 
                                   Map<Integer, Joueur> proprietaires, Map<Integer, Boolean> estVisible) {
        SwingUtilities.invokeLater(() -> {
            panelBoutons.removeAll();
            
            JLabel label = new JLabel("🎯 " + joueur.getNom() + ", choisis une carte à prendre :");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.WHITE);
            panelBoutons.add(label);
            
            for (Map.Entry<Integer, Carte> entry : choixPossibles.entrySet()) {
                int numero = entry.getKey();
                Carte carte = entry.getValue();
                Joueur proprio = proprietaires.get(numero);
                boolean visible = estVisible.get(numero);
                
                String texteBouton;
                if (visible) {
                    texteBouton = numero + " - " + carte.getNom() + " " + getSymboleCouleur(carte.getCouleur()) + 
                                 " de " + proprio.getNom() + " [VISIBLE]";
                } else {
                    texteBouton = numero + " - Carte cachée de " + proprio.getNom();
                }
                
                JButton btn = new JButton(texteBouton);
                btn.setFont(new Font("Arial", Font.PLAIN, 12));
                if (visible) {
                    btn.setForeground(getCouleurCarte(carte.getCouleur()));
                }
                
                btn.addActionListener(e -> {
                    if (scannerHybride != null) {
                        scannerHybride.envoyerDepuisGraphique(String.valueOf(numero));
                        panelBoutons.removeAll();
                        panelBoutons.revalidate();
                        panelBoutons.repaint();
                    }
                });
                
                panelBoutons.add(btn);
            }
            
            panelBoutons.revalidate();
            panelBoutons.repaint();
        });
    }
    
    /**
     * Affiche des boutons pour permettre au joueur de proposer une carte (visible ou cachée).
     * 
     * @param joueur le joueur qui doit proposer une carte
     * @param main la main actuelle du joueur
     * @param typeChoix le type de choix ("visible" ou "cachee")
     */
    @Override
    public void demanderPropositionCarte(Joueur joueur, List<Carte> main, String typeChoix) {
        SwingUtilities.invokeLater(() -> {
            panelBoutons.removeAll();
            
            String message = typeChoix.equals("visible") ? 
                "🃏 " + joueur.getNom() + ", choisis la carte VISIBLE :" :
                "🂠 " + joueur.getNom() + ", choisis la carte CACHÉE :";
            
            JLabel label = new JLabel(message);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.WHITE);
            panelBoutons.add(label);
            
            for (int i = 0; i < main.size(); i++) {
                Carte carte = main.get(i);
                int numero = i + 1;
                
                String texteBouton = numero + " - " + carte.getNom() + " " + getSymboleCouleur(carte.getCouleur());
                
                JButton btn = new JButton(texteBouton);
                btn.setFont(new Font("Arial", Font.PLAIN, 12));
                btn.setForeground(getCouleurCarte(carte.getCouleur()));
                
                btn.addActionListener(e -> {
                    if (scannerHybride != null) {
                        scannerHybride.envoyerDepuisGraphique(String.valueOf(numero));
                        panelBoutons.removeAll();
                        panelBoutons.revalidate();
                        panelBoutons.repaint();
                    }
                });
                
                panelBoutons.add(btn);
            }
            
            panelBoutons.revalidate();
            panelBoutons.repaint();
        });
    }
    
    /**
     * Affiche des boutons pour permettre au joueur de sauvegarder ou continuer la partie.
     * 
     * @param joueur le joueur à qui demander
     */
    @Override
    public void demanderSauvegarde(Joueur joueur) {
        SwingUtilities.invokeLater(() -> {
            panelBoutons.removeAll();
            
            JLabel label = new JLabel("💾 " + joueur.getNom() + ", que veux-tu faire ?");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.WHITE);
            panelBoutons.add(label);
            
            JButton btnSave = new JButton("💾 Sauvegarder");
            btnSave.setFont(new Font("Arial", Font.BOLD, 14));
            btnSave.setBackground(new Color(255, 215, 0));
            btnSave.addActionListener(e -> {
                if (scannerHybride != null) {
                    String nomSave = JOptionPane.showInputDialog(this, "Nom de la sauvegarde :");
                    if (nomSave != null && !nomSave.isEmpty()) {
                        scannerHybride.envoyerDepuisGraphique("save");
                        new Thread(() -> {
                            try {
                                Thread.sleep(100);
                                scannerHybride.envoyerDepuisGraphique(nomSave);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                    }
                    panelBoutons.removeAll();
                    panelBoutons.revalidate();
                    panelBoutons.repaint();
                }
            });
            
            JButton btnContinue = new JButton("▶️ Continuer");
            btnContinue.setFont(new Font("Arial", Font.BOLD, 14));
            btnContinue.setBackground(new Color(50, 205, 50));
            btnContinue.addActionListener(e -> {
                if (scannerHybride != null) {
                    scannerHybride.envoyerDepuisGraphique("ok");
                    panelBoutons.removeAll();
                    panelBoutons.revalidate();
                    panelBoutons.repaint();
                }
            });
            
            panelBoutons.add(btnSave);
            panelBoutons.add(btnContinue);
            
            panelBoutons.revalidate();
            panelBoutons.repaint();
        });
    }

    /**
     * Affiche une fenêtre de dialogue avec les résultats finaux de la partie.
     * Présente le classement des joueurs et le détail de leurs Jests dans des onglets séparés.
     * 
     * @param scores map associant chaque joueur à son score
     * @param classement liste des joueurs dans l'ordre du classement
     */
    @Override
    public void afficherFinPartie(Map<Joueur, Integer> scores, List<Joueur> classement) {
        SwingUtilities.invokeLater(() -> {
            JDialog dialogFin = new JDialog(this, "🎊 Fin de Partie 🎊", true);
            dialogFin.setSize(900, 700);
            dialogFin.setLocationRelativeTo(this);
            
            JPanel panelPrincipal = new JPanel();
            panelPrincipal.setLayout(new BorderLayout(10, 10));
            panelPrincipal.setBackground(new Color(240, 248, 255));
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel titre = new JLabel("🎊 FIN DE LA PARTIE 🎊", SwingConstants.CENTER);
            titre.setFont(new Font("Arial", Font.BOLD, 28));
            titre.setForeground(new Color(70, 130, 180));
            panelPrincipal.add(titre, BorderLayout.NORTH);
            
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
            
            JPanel panelClassement = new JPanel();
            panelClassement.setLayout(new BoxLayout(panelClassement, BoxLayout.Y_AXIS));
            panelClassement.setBackground(Color.WHITE);
            panelClassement.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            
            JLabel titreClassement = new JLabel("🏆 CLASSEMENT 🏆");
            titreClassement.setFont(new Font("Arial", Font.BOLD, 20));
            titreClassement.setAlignmentX(Component.CENTER_ALIGNMENT);
            titreClassement.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            panelClassement.add(titreClassement);
            
            for (int i = 0; i < classement.size(); i++) {
                Joueur j = classement.get(i);
                int score = scores.get(j);
                
                JPanel panelJoueur = creerPanelClassement(j, score, i);
                panelClassement.add(panelJoueur);
                panelClassement.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            
            JScrollPane scrollClassement = new JScrollPane(panelClassement);
            tabbedPane.addTab("🏆 Classement", scrollClassement);
            
            JPanel panelDetails = new JPanel();
            panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.Y_AXIS));
            panelDetails.setBackground(Color.WHITE);
            panelDetails.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            
            JLabel titreDetails = new JLabel("📊 DÉTAIL DES JESTS");
            titreDetails.setFont(new Font("Arial", Font.BOLD, 20));
            titreDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
            titreDetails.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            panelDetails.add(titreDetails);
            
            for (Joueur j : classement) {
                JPanel panelDetailJoueur = creerPanelDetailJoueur(j, scores.get(j));
                panelDetails.add(panelDetailJoueur);
                panelDetails.add(Box.createRigidArea(new Dimension(0, 15)));
            }
            
            JScrollPane scrollDetails = new JScrollPane(panelDetails);
            tabbedPane.addTab("📊 Détails", scrollDetails);
            
            panelPrincipal.add(tabbedPane, BorderLayout.CENTER);
            
            JButton btnFermer = new JButton("Fermer");
            btnFermer.setFont(new Font("Arial", Font.BOLD, 16));
            btnFermer.setBackground(new Color(70, 130, 180));
            btnFermer.setForeground(Color.WHITE);
            btnFermer.addActionListener(e -> dialogFin.dispose());
            
            JPanel panelBouton = new JPanel();
            panelBouton.setBackground(new Color(240, 248, 255));
            panelBouton.add(btnFermer);
            panelPrincipal.add(panelBouton, BorderLayout.SOUTH);
            
            dialogFin.add(panelPrincipal);
            dialogFin.setVisible(true);
        });
    }

    /**
     * Ajoute une ligne d'affichage pour une couleur de cartes avec leur total de points.
     * 
     * @param container le conteneur où ajouter la ligne
     * @param symbole le symbole de la couleur
     * @param cartes la liste des cartes de cette couleur
     * @param couleur la couleur d'affichage
     * @param signe le signe indiquant l'effet sur le score
     */
    private void ajouterLigneCouleur(JPanel container, String symbole, List<Carte> cartes, Color couleur, String signe) {
        if (cartes.isEmpty()) {
            return;
        }
        
        int somme = 0;
        for (Carte c : cartes) {
            somme += c.getValeur();
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(symbole).append(" (").append(signe).append(") : ");
        for (int i = 0; i < cartes.size(); i++) {
            sb.append(cartes.get(i).getNom());
            if (i < cartes.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" [").append(somme).append(" pts]");
        
        JLabel label = new JLabel(sb.toString());
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(couleur);
        label.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        container.add(label);
    }
    
    /**
     * Crée un panel pour afficher un joueur dans le classement final.
     * 
     * @param joueur le joueur à afficher
     * @param score le score du joueur
     * @param position la position du joueur dans le classement (0 = premier)
     * @return le panel créé
     */
    private JPanel creerPanelClassement(Joueur joueur, int score, int position) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));
        panel.setMaximumSize(new Dimension(600, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Color couleurFond;
        String medaille;
        switch(position) {
            case 0: 
                couleurFond = new Color(255, 215, 0, 100);
                medaille = "🥇";
                break;
            case 1: 
                couleurFond = new Color(192, 192, 192, 100);
                medaille = "🥈";
                break;
            case 2: 
                couleurFond = new Color(205, 127, 50, 100);
                medaille = "🥉";
                break;
            default: 
                couleurFond = new Color(245, 245, 245);
                medaille = "  ";
                break;
        }
        panel.setBackground(couleurFond);
        
        JLabel labelPosition = new JLabel(medaille);
        labelPosition.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(labelPosition, BorderLayout.WEST);
        
        String typeJoueur = (joueur instanceof JoueurPhysique) ? "👤" : "🤖";
        JLabel labelNom = new JLabel(typeJoueur + " " + joueur.getNom());
        labelNom.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(labelNom, BorderLayout.CENTER);
        
        JLabel labelScore = new JLabel(score + " pts");
        labelScore.setFont(new Font("Arial", Font.BOLD, 24));
        labelScore.setForeground(new Color(70, 130, 180));
        panel.add(labelScore, BorderLayout.EAST);
        
        return panel;
    }

    /**
     * Crée un panel détaillé pour afficher le Jest d'un joueur avec toutes ses cartes.
     * 
     * @param joueur le joueur dont afficher le Jest
     * @param scoreTotal le score total du joueur
     * @return le panel créé
     */
    private JPanel creerPanelDetailJoueur(Joueur joueur, int scoreTotal) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        String typeJoueur = (joueur instanceof JoueurPhysique) ? "👤" : "🤖";
        JLabel labelEntete = new JLabel(typeJoueur + " " + joueur.getNom() + " - " + scoreTotal + " pts");
        labelEntete.setFont(new Font("Arial", Font.BOLD, 16));
        labelEntete.setForeground(new Color(70, 130, 180));
        panel.add(labelEntete, BorderLayout.NORTH);
        
        List<Carte> cartes = joueur.getJest().getCartes();
        
        if (cartes.isEmpty()) {
            JLabel vide = new JLabel("(Aucune carte)");
            vide.setFont(new Font("Arial", Font.ITALIC, 14));
            vide.setForeground(Color.GRAY);
            panel.add(vide, BorderLayout.CENTER);
            return panel;
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
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
        
        ajouterLigneCouleur(panelCentral, "♠ Pique", cartesParCouleur.get("Pique"), Color.BLACK, "+");
        ajouterLigneCouleur(panelCentral, "♣ Trèfle", cartesParCouleur.get("Trèfle"), Color.BLACK, "+");
        ajouterLigneCouleur(panelCentral, "♥ Coeur", cartesParCouleur.get("Coeur"), Color.RED, "?");
        ajouterLigneCouleur(panelCentral, "♦ Carreau", cartesParCouleur.get("Carreau"), Color.RED, "-");
        
        if (!speciales.isEmpty()) {
            StringBuilder sb = new StringBuilder("✨ Spécial : ");
            for (Carte c : speciales) {
                if (c instanceof Joker) {
                    sb.append("Joker ");
                } else if (c instanceof CarteCameleon) {
                    sb.append("Caméléon ");
                } else if (c instanceof CarteMiroir) {
                    sb.append("Miroir ");
                }
            }
            JLabel labelSpecial = new JLabel(sb.toString());
            labelSpecial.setFont(new Font("Arial", Font.PLAIN, 13));
            labelSpecial.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            panelCentral.add(labelSpecial);
        }
        
        JLabel labelTotal = new JLabel("💯 Total : " + cartes.size() + " cartes");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 14));
        labelTotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelCentral.add(labelTotal);
        
        panel.add(panelCentral, BorderLayout.CENTER);
        
        return panel;
    }
}