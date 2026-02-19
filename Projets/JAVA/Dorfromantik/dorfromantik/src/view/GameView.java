/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/GameView.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente la fenêtre principale du jeu Dorfomantik.
 * Elle gère l'affichage du plateau, de la mini-map, du score, ainsi que l'ajout de tuiles et la navigation.
 * Le joueur peut interagir avec le plateau (ajouter des tuiles, faire pivoter la prochaine tuile, etc.).
 * Le jeu se termine après la pose d'un nombre défini de tuiles, puis le score final est affiché.
 */

package view;

import controller.ScoreController;
import model.Board;
import model.Tile;
import model.TileGenerator;
import utils.SeedRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;

/**
 * Fenêtre principale du jeu, contenant le plateau, la mini-map, le score, les boutons de navigation, et gérant
 * les interactions utilisateur (clic, molette de souris, touches de direction).
 */
public class GameView extends JFrame {
    // Champs et constructeur identiques à la version précédente

    private final Board board;
    private final TileGenerator tileGenerator;
    private BoardPanel boardPanel;
    private NextTilePanel nextTilePanel;
    private MiniMapPanel miniMapPanel;
    private JPanel grayPanel;
    private Tile nextTile;
    private final ScoreController scoreController;
    private final ScoreFinal scoreFinal;
    private final SeedRepository seedRepository;
    private JLabel scoreLabel;
    private JButton backToMenuButton;
    private JButton quitButton;

    public GameView(Board board, long seed, ScoreController scoreController, ScoreView scoreView, SeedRepository seedRepository) {
        this.board = board;
        this.tileGenerator = new TileGenerator(seed);
        this.scoreController = scoreController;
        this.scoreFinal = new ScoreFinal(scoreController);
        this.seedRepository = seedRepository;

        setTitle("Dorfromantik - Plateau");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(Color.WHITE);

        initializeBoard();
        initializeComponents();
        placeInitialTile();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setupKeyBindings();
        setupGlobalMouseWheelListener();

        // Centre le plateau après l'affichage
        SwingUtilities.invokeLater(this::centerOnBoard);

        // Repositionne les composants après affichage et en cas de redimensionnement
        SwingUtilities.invokeLater(this::positionComponents);

        // Ajout de l'écouteur de redimensionnement
        addComponentListener(new GameViewComponentListener(this));
    }

    private void initializeBoard() {
        Point center = new Point(0, 0);
        board.addHexagon(center);
    }

    private void initializeComponents() {
        boardPanel = new BoardPanel(board);
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(BorderFactory.createEmptyBorder());
        boardPanel.setLayout(null);

        BoardMouseController mouseController = new BoardMouseController(boardPanel, this);
        boardPanel.addMouseListener(mouseController);
        boardPanel.addMouseMotionListener(mouseController);

        miniMapPanel = new MiniMapPanel(board);
        miniMapPanel.setOpaque(true);
        miniMapPanel.setBackground(new Color(255, 255, 255, 180));

        grayPanel = new JPanel();
        grayPanel.setBackground(Color.LIGHT_GRAY);
        grayPanel.setOpaque(true);

        nextTile = tileGenerator.generateRandomTile();
        nextTilePanel = new NextTilePanel(nextTile);
        nextTilePanel.setOpaque(false);

        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.RED);

        backToMenuButton = new JButton("Retour au menu série");
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBackground(new Color(255, 255, 0, 200));
        backToMenuButton.setForeground(Color.BLACK);
        backToMenuButton.addActionListener(new HomeAction(this, seedRepository));

        quitButton = new JButton("Quitter");
        quitButton.setFocusPainted(false);
        quitButton.setBackground(new Color(255, 0, 0, 200));
        quitButton.setForeground(Color.WHITE);
        quitButton.addActionListener(new QuitAction());

        boardPanel.add(grayPanel);
        boardPanel.add(miniMapPanel);
        boardPanel.add(scoreLabel);
        boardPanel.add(nextTilePanel);
        boardPanel.add(backToMenuButton);
        boardPanel.add(quitButton);

        boardPanel.setComponentZOrder(miniMapPanel, 0);
        boardPanel.setComponentZOrder(grayPanel, 1);

        add(boardPanel, BorderLayout.CENTER);
    }

    /**
     * Configure l'écouteur global pour la molette de la souris, permettant de faire pivoter la tuile suivante.
     */
    private void setupGlobalMouseWheelListener() {
        MouseWheelListener globalWheelListener = new GlobalMouseWheelListener(nextTilePanel);
        addMouseWheelListener(globalWheelListener);
    }

    void positionComponents() {
        int panelWidth = boardPanel.getWidth();
        int panelHeight = boardPanel.getHeight();
        int margin = 20;

        nextTilePanel.setBounds(margin, margin, 120, 120);
        backToMenuButton.setBounds(panelWidth - 220, margin, 200, 40);
        quitButton.setBounds(panelWidth - 220, margin + 50, 200, 40);
        scoreLabel.setBounds(margin, panelHeight - 60, 200, 40);
        grayPanel.setBounds(panelWidth - 220, panelHeight - 220, 200, 200);
        miniMapPanel.setBounds(panelWidth - 220, panelHeight - 220, 200, 200);
    }

    private void placeInitialTile() {
        Point center = new Point(0, 0);

        if (!board.isPositionOccupied(center)) {
            board.addTile(center, nextTile);
            boardPanel.setTileRotation(center, 0);

            int score = calculateScore(center, nextTile);
            scoreController.addScore(score);

            scoreLabel.setText("Score : " + scoreController.getTotalScore());

            addNeighborHexagons(center);
            boardPanel.repaintAffectedArea(center);

            for (Point neighbor : getNeighbors(center)) {
                boardPanel.repaintAffectedArea(neighbor);
            }

            nextTile = tileGenerator.generateRandomTile();
            nextTilePanel.setNextTile(nextTile);
        }
    }

    public void handleMouseClick(Point clickPoint) {
        Point hexPosition = boardPanel.calculateHexCoordinates(clickPoint);

        if (hexPosition == null) {
            return;
        }

        if (board.hasHexagon(hexPosition) && boardPanel.isPointInsideHexagon(clickPoint, boardPanel.getHexCenter(hexPosition))) {
            if (!board.isPositionOccupied(hexPosition)) {
                board.addTile(hexPosition, nextTile);
                boardPanel.setTileRotation(hexPosition, nextTilePanel.getRotation());

                int score = calculateScore(hexPosition, nextTile);
                scoreController.addScore(score);

                scoreLabel.setText("Score : " + scoreController.getTotalScore());

                addNeighborHexagons(hexPosition);
                boardPanel.repaintAffectedArea(hexPosition);

                for (Point neighbor : getNeighbors(hexPosition)) {
                    boardPanel.repaintAffectedArea(neighbor);
                }

                miniMapPanel.repaint();

                if (tileGenerator.isGameFinished()) {
                    System.out.println("Fin du jeu - Passage à l'écran final");
                    scoreFinal.afficher(this, () -> SeriesMenu.showSeriesMenu(seedRepository, null));
                    return;
                }

                nextTile = tileGenerator.generateRandomTile();
                nextTilePanel.setNextTile(nextTile);
            }
        }
    }

    private void addNeighborHexagons(Point position) {
        for (Point neighbor : getNeighbors(position)) {
            if (!board.hasHexagon(neighbor)) {
                board.addHexagon(neighbor);
            }
        }
    }

    private Point[] getNeighbors(Point position) {
        int col = position.x;
        int row = position.y;
        boolean isOddRow = (row % 2 != 0);
        return isOddRow ? new Point[]{
                new Point(col, row - 1),
                new Point(col + 1, row - 1),
                new Point(col - 1, row),
                new Point(col + 1, row),
                new Point(col, row + 1),
                new Point(col + 1, row + 1)
        } : new Point[]{
                new Point(col - 1, row - 1),
                new Point(col, row - 1),
                new Point(col - 1, row),
                new Point(col + 1, row),
                new Point(col - 1, row + 1),
                new Point(col, row + 1)
        };
    }

    private int calculateScore(Point position, Tile newTile) {
        int score = 0;
        Point[] neighbors = getNeighbors(position);

        for (int i = 0; i < neighbors.length; i++) {
            Point neighborPosition = neighbors[i];
            Tile neighborTile = board.getTile(neighborPosition);

            if (neighborTile != null) {
                int adjacentSideThisTile = i;
                int adjacentSideNeighbor = (i + 3) % 6;

                if (newTile.getTerrainForSide(adjacentSideThisTile) == neighborTile.getTerrainForSide(adjacentSideNeighbor)) {
                    score += 4;
                } else {
                    score += 1;
                }
            }
        }
        return score;
    }

    private void setupKeyBindings() {
        InputMap inputMap = boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = boardPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        actionMap.put("moveUp", new MoveUpAction(boardPanel));
        actionMap.put("moveDown", new MoveDownAction(boardPanel));
        actionMap.put("moveLeft", new MoveLeftAction(boardPanel));
        actionMap.put("moveRight", new MoveRightAction(boardPanel));
    }

    private void centerOnBoard() {
        boardPanel.moveVirtualCenter(-boardPanel.getVirtualCenter().x + getWidth() / 2,
                -boardPanel.getVirtualCenter().y + getHeight() / 2);
    }
}
