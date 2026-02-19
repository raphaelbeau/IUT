/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/SplashScreen.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente l'écran de bienvenue (splash screen) affiché au lancement de l'application.
 * Elle permet de présenter un menu interactif avec des options pour jouer, consulter les règles,
 * voir les contrôles et réinitialiser l'écran.
 */

package view;

import utils.SeedRepository;
import utils.Database;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Classe représentant l'écran de bienvenue interactif.
 */
public class SplashScreen extends JPanel {

    /**
     * Image de fond du splash screen.
     */
    private Image backgroundImage;

    /**
     * Cache pour stocker les images chargées en mémoire.
     */
    private static final Map<String, Image> imageCache = new HashMap<>();

    /**
     * Constructeur du splash screen avec un chemin d'image de fond.
     *
     * @param imagePath Le chemin de l'image de fond à afficher.
     */
    public SplashScreen(String imagePath) {
        loadImage(imagePath);
        setLayout(null); // Permet de placer des composants avec des coordonnées absolues
    }

    /**
     * Précharge les images dans le cache pour éviter des rechargements multiples.
     *
     * @param imagePaths Les chemins des images à précharger.
     */
    public static void preloadImages(String... imagePaths) {
        String projectDir = System.getProperty("user.dir");
        for (String path : imagePaths) {
            try {
                File imageFile = new File(projectDir + "/resources/" + path);
                if (imageFile.exists()) {
                    imageCache.put(path, new ImageIcon(imageFile.getAbsolutePath()).getImage());
                } else {
                    System.out.println("Image introuvable : " + path);
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du préchargement de l'image : " + e.getMessage());
            }
        }
    }

    /**
     * Charge une image à partir du cache ou affiche une erreur si elle n'existe pas.
     *
     * @param imagePath Le chemin de l'image à charger.
     */
    private void loadImage(String imagePath) {
        if (imageCache.containsKey(imagePath)) {
            backgroundImage = imageCache.get(imagePath);
        } else {
            System.out.println("Image non trouvée dans le cache : " + imagePath);
        }
    }

    /**
     * Dessine le panneau avec l'image de fond.
     *
     * @param g L'objet graphique utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Met à jour l'image de fond du splash screen.
     *
     * @param imagePath Le nouveau chemin de l'image de fond.
     */
    public void updateBackgroundImage(String imagePath) {
        loadImage(imagePath);
        repaint();
    }

    /**
     * Affiche l'écran de bienvenue avec des boutons interactifs.
     *
     * @param parentFrame La fenêtre parent à masquer ou fermer.
     * @param latch       Une synchronisation optionnelle pour attendre la fermeture du splash screen.
     */
    public static void showSplashScreen(JFrame parentFrame, CountDownLatch latch) {
        JFrame splashFrame = new JFrame("Écran de bienvenue");
        SplashScreen splashPanel = new SplashScreen("Menu.jpg");

        int windowWidth = 1280;
        int windowHeight = 720;
        splashFrame.setSize(windowWidth, windowHeight);

        splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setUndecorated(true);
        splashFrame.add(splashPanel);

        // Positionnement des boutons
        int buttonWidth = 300;
        int buttonHeight = 80;
        int xCenter = (windowWidth - buttonWidth) / 2;

        int yPlayButton = (windowHeight / 2) - (buttonHeight + 20);
        int yRulesButton = yPlayButton + buttonHeight + 10;
        int yControlsButton = yRulesButton + buttonHeight + 10;
        int yResetButton = windowHeight - 150;

        JButton playButton = createTransparentButton();
        playButton.setBounds(xCenter, yPlayButton, buttonWidth, buttonHeight);

        JButton rulesButton = createTransparentButton();
        rulesButton.setBounds(xCenter, yRulesButton, buttonWidth, buttonHeight);

        JButton controlsButton = createTransparentButton();
        controlsButton.setBounds(xCenter, yControlsButton, buttonWidth, buttonHeight);

        JButton resetButton = createTransparentButton();
        resetButton.setBounds(40, yResetButton, 150, 50);
        resetButton.setEnabled(false);

        JButton quitterButton = createTransparentButton();
        quitterButton.setBounds(windowWidth - 250, windowHeight - 250, 250, 250);

        // Ajout des actions des boutons
        rulesButton.addActionListener(new ButtonActionHandler(splashPanel, "regles.jpg", playButton, rulesButton, controlsButton, resetButton));
        controlsButton.addActionListener(new ButtonActionHandler(splashPanel, "Controles.jpg", playButton, rulesButton, controlsButton, resetButton));
        resetButton.addActionListener(new ButtonActionHandler(splashPanel, "Menu.jpg", playButton, rulesButton, controlsButton, resetButton));
        quitterButton.addActionListener(new QuitActionHandler());

        playButton.addActionListener(e -> new Thread(() -> {
            splashFrame.dispose();
            latch.countDown();
            try {
                Database database = new Database();
                SeedRepository seedRepository = new SeedRepository(database);
                SwingUtilities.invokeLater(() -> SeriesMenu.showSeriesMenu(seedRepository, splashFrame));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données : " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }).start());

        // Ajout des boutons au panneau
        splashPanel.add(playButton);
        splashPanel.add(rulesButton);
        splashPanel.add(controlsButton);
        splashPanel.add(resetButton);
        splashPanel.add(quitterButton);

        splashPanel.revalidate();
        splashPanel.repaint();

        splashFrame.setVisible(true);
    }

    /**
     * Crée un bouton transparent (invisible par défaut).
     *
     * @return Le bouton transparent.
     */
    private static JButton createTransparentButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
}
