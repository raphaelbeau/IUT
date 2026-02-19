/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/BoardPanel.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente un panneau graphique personnalisé pour afficher et gérer le plateau de jeu hexagonal.
 * Elle permet de dessiner les hexagones, gérer les déplacements du plateau virtuel, détecter les clics de souris
 * et fournir une interaction fluide avec le modèle du jeu (Board, Tile, Terrain).
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import model.Board;
import model.Tile;
import model.Terrain;

/**
 * Panneau personnalisé pour afficher et interagir avec le plateau de jeu hexagonal.
 * Cette classe gère le rendu, la navigation (virtualCenter), la détection de cases hexagonales cliquées,
 * ainsi que la possibilité de faire pivoter les tuiles.
 */
public class BoardPanel extends JPanel {
    
     /**
     * Le modèle du plateau contenant les informations sur les tuiles et hexagones.
     */
    private final Board board;

    /**
     * Le rayon des hexagones, utilisé pour dessiner les tuiles hexagonales.
     */
    private final int hexRadius = 40; 

    /**
     * Map associant à chaque position (Point) une rotation (en degrés) pour la tuile correspondante.
     */
    private final Map<Point, Integer> tileRotations;

    /**
     * Map associant à chaque type de terrain une couleur pour le rendu graphique.
     */
    private final Map<Terrain, Color> terrainColors;

    /**
     * Le centre virtuel du plateau, utilisé pour simuler un déplacement du plateau
     * sans modifier les coordonnées des tuiles.
     */
    private Point virtualCenter;

    /**
     * Un tampon graphique (BufferedImage) pour optimiser le rendu et éviter les scintillements.
     */
    private BufferedImage buffer;

    /**
     * Facteur d'échelle pour le tampon graphique, permettant un rendu plus propre et de meilleure qualité.
     */
    private static final int BUFFER_SCALE = 2;

    /**
     * Constructeur du panneau du plateau.
     *
     * @param board Le modèle représentant le plateau et ses éléments (tuiles, hexagones).
     */
    public BoardPanel(Board board) {
        this.board = board;
        this.tileRotations = new HashMap<>();
        this.virtualCenter = new Point(4000, 4000); // Initialisation à un grand centre virtuel
        setBackground(Color.WHITE);
        setDoubleBuffered(true); // Optimise les rendus graphiques

        // Définir les couleurs pour chaque type de terrain
        terrainColors = new HashMap<>();
        terrainColors.put(Terrain.MER, new Color(0, 0, 255));
        terrainColors.put(Terrain.CHAMP, new Color(0, 255, 0));
        terrainColors.put(Terrain.FORET, new Color(34, 139, 34));
        terrainColors.put(Terrain.PRE, new Color(255, 255, 0));
        terrainColors.put(Terrain.MONTAGNE, new Color(139, 69, 19));
    }

    /**
     * Retourne la taille préférée du panneau, utilisée par les layouts pour
     * déterminer la taille du composant.
     *
     * @return La dimension préférée (8000x8000).
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(8000, 8000); 
    }

    /**
     * Déplace le centre virtuel du plateau afin de simuler un défilement.
     *
     * @param dx Déplacement horizontal du centre virtuel.
     * @param dy Déplacement vertical du centre virtuel.
     */
    public void moveVirtualCenter(int dx, int dy) {
        virtualCenter.translate(dx, dy);
        buffer = null; 
        repaint(); 
    }

    /**
     * Retourne le centre virtuel actuel du plateau.
     *
     * @return Le point représentant le centre virtuel.
     */
    public Point getVirtualCenter() {
        return virtualCenter;
    }

    /**
     * Retourne le rayon des hexagones.
     *
     * @return La taille du rayon des hexagones.
     */
    public int getHexRadius() {
        return hexRadius;
    }

    /**
     * Ajuste la taille du plateau (par exemple après un redimensionnement)
     * et invalide le tampon graphique.
     */
    public void adjustBoardSize() {
        buffer = null;
        revalidate(); 
        repaint();    
    }

    /**
     * Méthode de rendu du composant. Utilise un tampon graphique pour améliorer les performances.
     *
     * @param g L'objet Graphics utilisé pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        int scaledWidth = getWidth() * BUFFER_SCALE;
        int scaledHeight = getHeight() * BUFFER_SCALE;

        if (buffer == null || buffer.getWidth() != scaledWidth || buffer.getHeight() != scaledHeight) {
            buffer = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffer.createGraphics();
            try {
                // Réglages de rendu pour une meilleure qualité
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2d.scale(BUFFER_SCALE, BUFFER_SCALE); 
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight()); 
                dessinerPlateau(g2d); 
            } finally {
                g2d.dispose();
            }
        }

        ((Graphics2D) g).drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Calcule le centre d'un hexagone en fonction de sa position (colonne, ligne).
     *
     * @param hexPosition Les coordonnées (col, row) de l'hexagone.
     * @return Le Point représentant le centre de l'hexagone.
     */
    public Point getHexCenter(Point hexPosition) {
        double largeurHexagone = Math.sqrt(3) * hexRadius;
        double espacementVertical = 1.5 * hexRadius;

        double centerX = virtualCenter.x + hexPosition.x * largeurHexagone;
        double centerY = virtualCenter.y + hexPosition.y * espacementVertical;

        if (hexPosition.y % 2 != 0) {
            centerX += largeurHexagone / 2;
        }

        return new Point((int) Math.round(centerX), (int) Math.round(centerY));
    }

    /**
     * Détermine si un point cliqué se trouve à l'intérieur d'un hexagone
     * en supposant aucune rotation.
     *
     * @param clickPoint Le point du clic.
     * @param hexCenter  Le centre de l'hexagone.
     * @return true si le point est à l'intérieur de l'hexagone, sinon false.
     */
    public boolean isPointInsideHexagon(Point clickPoint, Point hexCenter) {
        return isPointInsideHexagon(clickPoint, hexCenter, 0); 
    }

    /**
     * Détermine si un point cliqué se trouve à l'intérieur d'un hexagone
     * en tenant compte d'une rotation.
     *
     * @param clickPoint Le point du clic.
     * @param hexCenter  Le centre de l'hexagone.
     * @param rotation   La rotation (en degrés) appliquée à l'hexagone.
     * @return true si le point est à l'intérieur, sinon false.
     */
    public boolean isPointInsideHexagon(Point clickPoint, Point hexCenter, int rotation) {
        int r = hexRadius;
        Polygon hexagon = new Polygon();

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i - 30 + rotation); 
            int x = (int) (hexCenter.x + r * Math.cos(angle));
            int y = (int) (hexCenter.y + r * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        return hexagon.contains(clickPoint);
    }

    /**
     * Calcule les coordonnées (col, row) de l'hexagone correspondant à un point cliqué.
     * Si le point ne se trouve pas sur l'hexagone calculé initialement, on vérifie
     * les hexagones voisins pour identifier l'hexagone exact.
     *
     * @param clickPoint Le point du clic.
     * @return Les coordonnées de l'hexagone cliqué, ou null si aucun hexagone n'est trouvé.
     */
    public Point calculateHexCoordinates(Point clickPoint) {
        double largeurHexagone = Math.sqrt(3) * hexRadius;
        double espacementVertical = 1.5 * hexRadius;

        int col = (int) Math.floor((clickPoint.x - virtualCenter.x) / largeurHexagone);
        int row = (int) Math.floor((clickPoint.y - virtualCenter.y) / espacementVertical);

        if (row % 2 != 0) {
            col = (int) Math.floor((clickPoint.x - virtualCenter.x - largeurHexagone / 2) / largeurHexagone);
        }

        Point hexPosition = new Point(col, row);

        Point hexCenter = getHexCenter(hexPosition);
        int rotation = getTileRotation(hexPosition);
        if (!isPointInsideHexagon(clickPoint, hexCenter, rotation)) {
            Point[] neighbors = getNeighborHexPositions(hexPosition);
            for (Point neighbor : neighbors) {
                hexCenter = getHexCenter(neighbor);
                rotation = getTileRotation(neighbor);
                if (isPointInsideHexagon(clickPoint, hexCenter, rotation)) {
                    return neighbor;
                }
            }
            return null;
        }

        return hexPosition;
    }

    /**
     * Redessine le plateau et ses voisins autour d'un hexagone donné.
     * Invalide le tampon afin que le prochain appel de paintComponent recrée l'image.
     *
     * @param hexPosition La position de l'hexagone autour duquel redessiner.
     */
    public void repaintWithNeighbors(Point hexPosition) {
        buffer = null; 
        repaint(); 
    }

    /**
     * Redessine une zone affectée autour d'un hexagone donné, afin d'éviter
     * de redessiner tout le plateau.
     *
     * @param hexPosition La position de l'hexagone autour duquel redessiner la zone affectée.
     */
    public void repaintAffectedArea(Point hexPosition) {
        double largeurHexagone = Math.sqrt(3) * hexRadius;
        double espacementVertical = 1.5 * hexRadius;

        int x = (int) Math.round(virtualCenter.x + hexPosition.x * largeurHexagone - hexRadius * 2);
        int y = (int) Math.round(virtualCenter.y + hexPosition.y * espacementVertical - hexRadius * 2);
        int size = (int) (hexRadius * 4);

        repaint(new Rectangle(x, y, size, size));
    }

    /**
     * Définit la rotation d'une tuile à une position donnée.
     *
     * @param position        La position de la tuile à faire pivoter.
     * @param rotationDegrees Le degré de rotation à appliquer.
     */
    public void setTileRotation(Point position, int rotationDegrees) {
        tileRotations.put(position, rotationDegrees);
        buffer = null; 
        repaint();
    }

    /**
     * Récupère la rotation (en degrés) appliquée à la tuile d'une position donnée.
     *
     * @param position La position de la tuile.
     * @return La rotation en degrés, ou 0 si aucune rotation n'est définie.
     */
    public int getTileRotation(Point position) {
        return tileRotations.getOrDefault(position, 0);
    }

    /**
     * Dessine l'ensemble du plateau hexagonal (tous les hexagones et leurs tuiles).
     *
     * @param g L'objet Graphics utilisé pour dessiner le plateau.
     */
    private void dessinerPlateau(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        double largeurHexagone = Math.sqrt(3) * hexRadius;
        double espacementVertical = 1.5 * hexRadius;

        for (Point position : board.getHexagons().keySet()) {
            int col = position.x;
            int row = position.y;
            double posX = virtualCenter.x + col * largeurHexagone;
            double posY = virtualCenter.y + row * espacementVertical;

            if (row % 2 != 0) {
                posX += largeurHexagone / 2;
            }

            Tile tile = board.getTile(position);
            Integer rotation = tileRotations.getOrDefault(position, 0);
            drawHexagon(g2d, (int) Math.round(posX), (int) Math.round(posY), tile, rotation);
        }
    }

    /**
     * Dessine un hexagone unique à partir de son centre, avec une tuile éventuellement composée
     * d'un ou deux terrains, et applique une rotation si spécifiée.
     *
     * @param g        Le Graphics2D utilisé pour dessiner.
     * @param xCentre  La coordonnée X du centre de l'hexagone.
     * @param yCentre  La coordonnée Y du centre de l'hexagone.
     * @param tile     La tuile à dessiner (peut être null si aucune tuile n'est présente).
     * @param rotation La rotation en degrés à appliquer à l'hexagone.
     */
    private void drawHexagon(Graphics2D g, int xCentre, int yCentre, Tile tile, int rotation) {
        int r = hexRadius;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        AffineTransform originalTransform = g.getTransform();
        g.rotate(Math.toRadians(rotation), xCentre, yCentre);

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i - 30);
            xPoints[i] = (int) (xCentre + r * Math.cos(angle));
            yPoints[i] = (int) (yCentre + r * Math.sin(angle));
        }

        if (tile != null) {
            if (tile.hasSingleTerrain()) {
                g.setColor(getColorForTerrain(tile.getTerrain1()));
                g.fillPolygon(xPoints, yPoints, 6);
            } else {
                int terrain1Sides = tile.getTerrain1Sides();
                Color color1 = getColorForTerrain(tile.getTerrain1());
                Color color2 = getColorForTerrain(tile.getTerrain2());

                for (int i = 0; i < 6; i++) {
                    int next = (i + 1) % 6;
                    g.setColor(i < terrain1Sides ? color1 : color2);

                    Polygon triangle = new Polygon();
                    triangle.addPoint(xCentre, yCentre);
                    triangle.addPoint(xPoints[i], yPoints[i]);
                    triangle.addPoint(xPoints[next], yPoints[next]);
                    g.fillPolygon(triangle);
                }
            }
        } else {
            // Aucun tile, on dessine un hexagone semi-transparent
            g.setColor(new Color(200, 200, 200, 50));
            g.fillPolygon(xPoints, yPoints, 6);
        }

        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 6);
        g.setTransform(originalTransform);
    }

    /**
     * Retourne la couleur associée à un type de terrain.
     *
     * @param terrain Le terrain pour lequel on veut la couleur.
     * @return La couleur associée, ou gris par défaut si non défini.
     */
    private Color getColorForTerrain(Terrain terrain) {
        return terrainColors.getOrDefault(terrain, Color.GRAY);
    }

    /**
     * Calcule les positions des hexagones voisins d'un hexagone donné.
     * Les voisins diffèrent selon que la ligne (row) est paire ou impaire,
     * du fait de la disposition hexagonale en "décalé".
     *
     * @param hexPosition La position de l'hexagone dont on cherche les voisins.
     * @return Un tableau de Points représentant les positions des hexagones voisins.
     */
    private Point[] getNeighborHexPositions(Point hexPosition) {
        int col = hexPosition.x;
        int row = hexPosition.y;
        boolean isOddRow = (row % 2 != 0);
        Point[] neighbors;

        if (isOddRow) {
            neighbors = new Point[]{
                    new Point(col, row - 1),
                    new Point(col + 1, row - 1),
                    new Point(col - 1, row),
                    new Point(col + 1, row),
                    new Point(col, row + 1),
                    new Point(col + 1, row + 1)
            };
        } else {
            neighbors = new Point[]{
                    new Point(col - 1, row - 1),
                    new Point(col, row - 1),
                    new Point(col - 1, row),
                    new Point(col + 1, row),
                    new Point(col - 1, row + 1),
                    new Point(col, row + 1)
            };
        }
        return neighbors;
    }
}
