/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/MiniMapPanel.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente un panneau graphique affichant une minimap du plateau de jeu.
 * La minimap permet de visualiser l'ensemble du plateau avec des hexagones simplifiés et à échelle réduite.
 */

package view;

import model.Board;
import model.Tile;
import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panneau personnalisé pour afficher une minimap représentant le plateau de jeu.
 * Chaque hexagone est dessiné en fonction des terrains présents dans le modèle {@link Board}.
 */
public class MiniMapPanel extends JPanel {

    /**
     * Le modèle contenant les données du plateau (hexagones et tuiles).
     */
    private final Board board;

    /**
     * Rayon des hexagones dans la minimap.
     */
    private final int miniHexRadius = 5;

    /**
     * Couleurs associées à chaque type de terrain.
     */
    private final Map<Terrain, Color> terrainColors;

    /**
     * Constructeur de la minimap.
     *
     * @param board Le modèle représentant le plateau de jeu.
     */
    public MiniMapPanel(Board board) {
        this.board = board;

        // Initialisation des couleurs pour chaque type de terrain
        terrainColors = new HashMap<>();
        terrainColors.put(Terrain.MER, new Color(0, 0, 255));
        terrainColors.put(Terrain.CHAMP, new Color(0, 255, 0));
        terrainColors.put(Terrain.FORET, new Color(34, 139, 34));
        terrainColors.put(Terrain.PRE, new Color(255, 255, 0));
        terrainColors.put(Terrain.MONTAGNE, new Color(139, 69, 19));

        // Paramètres graphiques du panneau
        setPreferredSize(new Dimension(220, 220)); // Taille fixe par défaut
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Méthode appelée pour dessiner le contenu du panneau.
     * Dessine la minimap en fonction des données du plateau.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMiniMap(g);
    }

    /**
     * Calcule l'échelle de la minimap pour adapter le contenu à la taille du panneau.
     *
     * @return Le facteur d'échelle minimal permettant d'afficher tout le plateau.
     */
    private double calculateScale() {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Point position : board.getHexagons().keySet()) {
            minX = Math.min(minX, position.x);
            minY = Math.min(minY, position.y);
            maxX = Math.max(maxX, position.x);
            maxY = Math.max(maxY, position.y);
        }

        double width = (maxX - minX + 1) * Math.sqrt(3) * miniHexRadius;
        double height = (maxY - minY + 1) * 1.5 * miniHexRadius;

        double scaleX = getWidth() / width;
        double scaleY = getHeight() / height;

        return Math.min(scaleX, scaleY);
    }

    /**
     * Dessine la minimap du plateau en utilisant les hexagones et tuiles du modèle.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    private void drawMiniMap(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        double scale = calculateScale();

        int offsetX = getWidth() / 2;
        int offsetY = getHeight() / 2;

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Point position : board.getHexagons().keySet()) {
            minX = Math.min(minX, position.x);
            minY = Math.min(minY, position.y);
            maxX = Math.max(maxX, position.x);
            maxY = Math.max(maxY, position.y);
        }

        double logicalWidth = (maxX - minX + 1) * Math.sqrt(3) * miniHexRadius * scale;
        double logicalHeight = (maxY - minY + 1) * 1.5 * miniHexRadius * scale;

        double centerOffsetX = offsetX - logicalWidth / 2;
        double centerOffsetY = offsetY - logicalHeight / 2;

        for (Point position : board.getHexagons().keySet()) {
            int col = position.x;
            int row = position.y;

            double posX = centerOffsetX + (col - minX) * Math.sqrt(3) * miniHexRadius * scale;
            double posY = centerOffsetY + (row - minY) * 1.5 * miniHexRadius * scale;

            if (row % 2 != 0) {
                posX += Math.sqrt(3) * miniHexRadius * scale / 2;
            }

            Tile tile = board.getTile(position);
            drawMiniHexagon(g2d, (int) posX, (int) posY, tile, scale);
        }
    }

    /**
     * Dessine un hexagone simplifié sur la minimap.
     *
     * @param g       L'objet Graphics utilisé pour dessiner.
     * @param xCentre Coordonnée X du centre de l'hexagone.
     * @param yCentre Coordonnée Y du centre de l'hexagone.
     * @param tile    La tuile associée à l'hexagone (peut être null).
     * @param scale   L'échelle utilisée pour ajuster la taille de l'hexagone.
     */
    private void drawMiniHexagon(Graphics2D g, int xCentre, int yCentre, Tile tile, double scale) {
        int r = (int) (miniHexRadius * scale);
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

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
            g.setColor(new Color(200, 200, 200, 50));
            g.fillPolygon(xPoints, yPoints, 6);
        }

        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 6);
    }

    /**
     * Retourne la couleur associée à un type de terrain.
     *
     * @param terrain Le type de terrain.
     * @return La couleur correspondante.
     */
    private Color getColorForTerrain(Terrain terrain) {
        return terrainColors.getOrDefault(terrain, Color.GRAY);
    }
}
