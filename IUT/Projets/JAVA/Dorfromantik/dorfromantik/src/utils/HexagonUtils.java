/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/utils/HexagonUtils.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe utilitaire fournit des méthodes pour effectuer des calculs géométriques
 * liés aux hexagones. Elle permet notamment de vérifier si un point se trouve
 * à l'intérieur d'un hexagone donné.
 */

package utils;

import java.awt.*;

/**
 * Classe utilitaire pour la manipulation des hexagones.
 * Elle est principalement utilisée pour gérer des calculs géométriques
 * et déterminer des relations spatiales entre points et hexagones.
 */
public class HexagonUtils {

    /**
     * Vérifie si un point donné se trouve à l'intérieur d'un hexagone.
     * Cette méthode calcule les sommets de l'hexagone en fonction de son centre et de son rayon,
     * puis utilise un polygone pour déterminer si le point est contenu dans celui-ci.
     *
     * @param clickPoint Le point à tester (généralement un clic de souris).
     * @param hexCenter  Les coordonnées du centre de l'hexagone.
     * @param hexRadius  Le rayon de l'hexagone (distance entre le centre et l'un de ses sommets).
     * @return {@code true} si le point est à l'intérieur de l'hexagone, {@code false} sinon.
     */
    public static boolean isPointInsideHexagon(Point clickPoint, Point hexCenter, int hexRadius) {
        int r = hexRadius; // Rayon de l'hexagone
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        // Calcul des coordonnées des sommets de l'hexagone
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i - 30); // Angle en radians pour chaque sommet
            xPoints[i] = (int) (hexCenter.x + r * Math.cos(angle)); // Coordonnée X
            yPoints[i] = (int) (hexCenter.y + r * Math.sin(angle)); // Coordonnée Y
        }

        // Création d'un polygone représentant l'hexagone
        Polygon hexagon = new Polygon(xPoints, yPoints, 6);

        // Vérification si le point donné se trouve à l'intérieur de l'hexagone
        return hexagon.contains(clickPoint);
    }
}
