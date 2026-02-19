package view;

import utils.SeedRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de l'affichage et de la gestion du menu des séries.
 * Elle permet de sélectionner une série, d'accéder aux favoris, de rechercher des séries, et de lancer le jeu.
 */
public class SeriesMenu {

    /**
     * Affiche le menu des séries dans une nouvelle fenêtre. Ferme éventuellement une fenêtre précédente.
     *
     * @param seedRepository Le repository permettant d'accéder aux seeds.
     * @param previousFrame  La fenêtre précédente, à fermer si elle est non nulle.
     */
    public static void showSeriesMenu(SeedRepository seedRepository, JFrame previousFrame) {
        if (previousFrame != null) {
            previousFrame.dispose();
        }

        JFrame seriesMenuFrame = new JFrame("Choisissez une Partie");
        seriesMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seriesMenuFrame.setLayout(new BorderLayout());

        // Liste des séries
        List<JButton> seriesButtons = new ArrayList<>();
        List<String> seriesNames = List.of("LA FORÊT", "LA PRAIRIE", "UNE PARTIE DANS LES BOIS", "Bientôt disponible");

        // Barre de recherche et filtre
        JPanel searchPanel = createSearchPanel(seriesButtons, seriesNames);
        seriesMenuFrame.add(searchPanel, BorderLayout.NORTH);

        // Contenu principal avec des onglets
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Saison 1", createSeason1Panel(seedRepository, seriesMenuFrame, seriesButtons, seriesNames));
        tabbedPane.addTab("Favoris", createFavoritesPanel());
        seriesMenuFrame.add(tabbedPane, BorderLayout.CENTER);

        // Panneau inférieur avec les boutons Accueil et Quitter
        JPanel bottomPanel = createBottomPanel(seriesMenuFrame);
        seriesMenuFrame.add(bottomPanel, BorderLayout.SOUTH);

        // Affichage en plein écran après l'initialisation
        SwingUtilities.invokeLater(() -> {
            setFullScreen(seriesMenuFrame);
        });
    }

    /**
     * Crée une barre de recherche pour filtrer les séries.
     *
     * @param seriesButtons Liste des boutons des séries.
     * @param seriesNames   Liste des noms des séries.
     * @return Le panneau de recherche.
     */
    private static JPanel createSearchPanel(List<JButton> seriesButtons, List<String> seriesNames) {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        searchPanel.setBackground(new Color(0xFFDE59));

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton searchButton = createHoverableButton("Rechercher", new Color(0x699388), new Color(0x6B948A));
        searchButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        searchButton.setPreferredSize(new Dimension(100, 30));

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            for (int i = 0; i < seriesNames.size(); i++) {
                String seriesName = seriesNames.get(i).toLowerCase();
                JButton button = seriesButtons.get(i);

                // Affiche ou masque le bouton selon la recherche
                button.setVisible(seriesName.contains(searchText));
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        return searchPanel;
    }

    /**
     * Crée le panneau pour la "Saison 1", incluant des boutons permettant de lancer des parties.
     *
     * @param seedRepository   Le repository pour accéder aux seeds.
     * @param seriesMenuFrame  La fenêtre du menu des séries.
     * @param seriesButtons    Liste des boutons des séries (à remplir ici).
     * @param seriesNames      Liste des noms des séries.
     * @return Le panneau correspondant à la Saison 1.
     */
    private static JPanel createSeason1Panel(SeedRepository seedRepository, JFrame seriesMenuFrame,
                                             List<JButton> seriesButtons, List<String> seriesNames) {
        JPanel season1Panel = new JPanel();
        season1Panel.setLayout(new BoxLayout(season1Panel, BoxLayout.Y_AXIS));
        season1Panel.setBackground(new Color(0xFFDE59));

        JPanel season1ButtonsPanel = createButtonPanel(2);

        long[] seriesSeeds = {1234567890L, 9876543210L, 1928374650L};

        for (int i = 0; i < seriesNames.size(); i++) {
            String seriesName = seriesNames.get(i);

            JButton button;
            if (i < seriesSeeds.length) {
                long seed = seriesSeeds[i];
                button = createStyledButton(seriesName, new Color(0x38B6FF), new Color(0x058428),
                        new SwitchToGameAction(seriesMenuFrame, seedRepository, seed, seriesMenuFrame), true);
            } else {
                // Bouton désactivé pour "Bientôt disponible"
                button = createStyledButton(seriesName, new Color(0xFFDE59), new Color(0xFFD700), null, false);
            }

            seriesButtons.add(button);
            season1ButtonsPanel.add(button);
        }

        season1Panel.add(season1ButtonsPanel);
        return season1Panel;
    }

    /**
     * Crée le panneau des favoris, affichant un message lorsque la liste est vide.
     *
     * @return Le panneau des favoris.
     */
    private static JPanel createFavoritesPanel() {
        JPanel favoritesPanel = new JPanel();
        favoritesPanel.setLayout(new BoxLayout(favoritesPanel, BoxLayout.Y_AXIS));
        favoritesPanel.setBackground(new Color(0xFFDE59));

        JLabel placeholderLabel = new JLabel("Vos séries favorites apparaîtront ici.");
        placeholderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        favoritesPanel.add(placeholderLabel);

        return favoritesPanel;
    }

    /**
     * Crée le panneau inférieur contenant les boutons "Accueil" et "Quitter".
     *
     * @param seriesMenuFrame La fenêtre du menu des séries.
     * @return Le panneau inférieur.
     */
    private static JPanel createBottomPanel(JFrame seriesMenuFrame) {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton homeButton = createHoverableButton("Accueil", new Color(0x699388), new Color(0x6B948A));
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        homeButton.setPreferredSize(new Dimension(120, 40));
        homeButton.addActionListener(e -> {
            seriesMenuFrame.dispose();
            SplashScreen.showSplashScreen(null, new java.util.concurrent.CountDownLatch(1));
        });

        JButton quitButton = createHoverableButton("Quitter", new Color(0xFF0000), new Color(0x058428));
        quitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        quitButton.setPreferredSize(new Dimension(120, 40));
        quitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(homeButton, BorderLayout.WEST);
        bottomPanel.add(quitButton, BorderLayout.EAST);

        return bottomPanel;
    }

    /**
     * Crée un panneau avec une disposition en grille pour organiser les boutons.
     *
     * @param columns Le nombre de colonnes dans la grille.
     * @return Le panneau configuré.
     */
    private static JPanel createButtonPanel(int columns) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, columns, 15, 15));
        buttonPanel.setBackground(new Color(0xFFDE59));
        return buttonPanel;
    }

    /**
     * Crée un bouton avec un style personnalisé et une action associée.
     *
     * @param text        Le texte du bouton.
     * @param normalColor La couleur normale du bouton.
     * @param hoverColor  La couleur du bouton au survol.
     * @param action      L'action associée au clic sur le bouton (peut être null si désactivé).
     * @param isEnabled   Indique si le bouton est actif (true) ou désactivé (false).
     * @return Le bouton configuré et stylé.
     */
    private static JButton createStyledButton(String text, Color normalColor, Color hoverColor, ActionListener action, boolean isEnabled) {
        JButton button = new JButton("<html><center><font style='font-family:sans-serif;font-size:14px'>" + text + "</font></center></html>");
        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setEnabled(isEnabled);

        if (isEnabled && action != null) {
            button.addActionListener(action);
            button.addMouseListener(new HoverEffectMouseAdapter(button, normalColor, hoverColor));
        }
        return button;
    }

    /**
     * Crée un bouton avec un effet de survol (hover).
     *
     * @param text        Le texte du bouton.
     * @param normalColor La couleur normale du bouton.
     * @param hoverColor  La couleur du bouton au survol.
     * @return Le bouton configuré avec effet de survol.
     */
    private static JButton createHoverableButton(String text, Color normalColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.addMouseListener(new HoverEffectMouseAdapter(button, normalColor, hoverColor));
        return button;
    }

    /**
     * Configure une fenêtre pour l'affichage en plein écran.
     *
     * @param frame La fenêtre à configurer en plein écran.
     */
    public static void setFullScreen(JFrame frame) {
        if (frame.isDisplayable()) {
            frame.dispose(); // Ferme la fenêtre si déjà affichable pour appliquer les modifications
        }
        frame.setUndecorated(true); // Mode sans bordure
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Plein écran
        frame.setVisible(true); // Affiche la fenêtre
    }
}
