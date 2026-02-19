/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/NextTilePanel.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe représente un panneau graphique qui affiche la prochaine tuile à placer.
 * Elle permet également de faire pivoter la tuile pour choisir son orientation avant de la placer.
 */

package view;

import javax.swing.*;
import java.awt.*;
import model.Tile;
import model.Terrain;
import java.util.HashMap;
import java.util.Map;

/**
 * Panneau graphique affichant la prochaine tuile à placer.
 * Inclut des fonctionnalités pour afficher et faire pivoter la tuile.
 */
public class NextTilePanel extends JPanel {

    /**
     * La prochaine tuile à afficher.
     */
    private Tile nextTile;

    /**
     * Rotation actuelle de la tuile en degrés (multiples de 60°).
     */
    private int rotation;

    /**
     * Rayon des hexagones dessinés.
     */
    private final int hexRadius = 50;

    /**
     * Couleurs associées à chaque type de terrain.
     */
    private final Map<Terrain, Color> terrainColors;

    /**
     * Constructeur du panneau de la prochaine tuile.
     *
     * @param initialTile La tuile initiale à afficher.
     */
    public NextTilePanel(Tile initialTile) {
        this.nextTile = initialTile;
        this.rotation = 0;

        // Configuration du panneau
        setPreferredSize(new Dimension(200, 200));
        setFocusable(true);
        setOpaque(true);
        setBackground(Color.WHITE);

        // Initialisation des couleurs pour chaque type de terrain
        terrainColors = new HashMap<>();
        terrainColors.put(Terrain.MER, new Color(0, 0, 255));
        terrainColors.put(Terrain.CHAMP, new Color(0, 255, 0));
        terrainColors.put(Terrain.FORET, new Color(34, 139, 34));
        terrainColors.put(Terrain.PRE, new Color(255, 255, 0));
        terrainColors.put(Terrain.MONTAGNE, new Color(139, 69, 19));
    }

    /**
     * Définit la prochaine tuile à afficher et réinitialise la rotation.
     *
     * @param nextTile La nouvelle tuile à afficher.
     */
    public void setNextTile(Tile nextTile) {
        this.nextTile = nextTile;
        this.rotation = 0;
        repaint();
    }

    /**
     * Fait pivoter la tuile de 60° vers la gauche.
     */
    public void rotateLeft() {
        rotation = (rotation - 60 + 360) % 360;
        repaint();
    }

    /**
     * Fait pivoter la tuile de 60° vers la droite.
     */
    public void rotateRight() {
        rotation = (rotation + 60) % 360;
        repaint();
    }

    /**
     * Retourne l'angle de rotation actuel de la tuile.
     *
     * @return La rotation en degrés.
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Méthode appelée pour dessiner le contenu du panneau.
     * Si une tuile est définie, elle est dessinée avec l'angle de rotation actuel.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (nextTile != null) {
            drawTile(g);
        }
    }

    /**
     * Dessine la tuile actuelle au centre du panneau.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    private void drawTile(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int xCentre = getWidth() / 2;
        int yCentre = getHeight() / 2;

        g2d.rotate(Math.toRadians(rotation), xCentre, yCentre);

        // Calcul des sommets de l'hexagone
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i - 30);
            xPoints[i] = (int) (xCentre + hexRadius * Math.cos(angle));
            yPoints[i] = (int) (yCentre + hexRadius * Math.sin(angle));
        }

        // Dessin de la tuile
        if (nextTile.hasSingleTerrain()) {
            g2d.setColor(getColorForTerrain(nextTile.getTerrain1()));
            g2d.fillPolygon(xPoints, yPoints, 6);
        } else {
            int terrain1Sides = nextTile.getTerrain1Sides();
            Color color1 = getColorForTerrain(nextTile.getTerrain1());
            Color color2 = getColorForTerrain(nextTile.getTerrain2());
            for (int i = 0; i < 6; i++) {
                int next = (i + 1) % 6;
                g2d.setColor(i < terrain1Sides ? color1 : color2);
                g2d.fillPolygon(
                        new int[]{xCentre, xPoints[i], xPoints[next]},
                        new int[]{yCentre, yPoints[i], yPoints[next]},
                        3
                );
            }
        }

        // Contour de l'hexagone
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 6);

        // Réinitialisation de la rotation
        g2d.rotate(Math.toRadians(-rotation), xCentre, yCentre);
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
