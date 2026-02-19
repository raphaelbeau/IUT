/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/model/Board.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe représentant le plateau de jeu. Elle gère les positions des tuiles
 * et des hexagones sur le plateau ainsi que leurs limites.
 */

package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Modèle pour le plateau de jeu. Cette classe permet de gérer l'ajout de tuiles,
 * la vérification des positions occupées et les limites du plateau.
 */
public class Board {
    private final Map<Point, Tile> tiles; // Stocke les tuiles positionnées sur le plateau
    private final Map<Point, Boolean> hexagons; // Stocke les positions des hexagones
    private int minX, maxX, minY, maxY; // Limites du plateau

    /**
     * Constructeur de la classe Board.
     * Initialise le plateau avec des limites et des collections vides.
     */
    public Board() {
        this.tiles = new HashMap<>();
        this.hexagons = new HashMap<>();
        this.minX = this.maxX = this.minY = this.maxY = 0;
    }

    /**
     * Vérifie si une position donnée est occupée par une tuile.
     *
     * @param position La position à vérifier.
     * @return true si la position est occupée, sinon false.
     */
    public boolean isPositionOccupied(Point position) {
        return tiles.containsKey(position);
    }

    /**
     * Vérifie si une position donnée contient un hexagone.
     *
     * @param position La position à vérifier.
     * @return true si un hexagone est présent, sinon false.
     */
    public boolean hasHexagon(Point position) {
        return hexagons.containsKey(position);
    }

    /**
     * Ajoute une tuile à une position spécifique sur le plateau.
     * Met également à jour les limites du plateau.
     *
     * @param position La position où ajouter la tuile.
     * @param tile     La tuile à ajouter.
     */
    public void addTile(Point position, Tile tile) {
        tiles.put(position, tile);
        hexagons.put(position, true);
        updateBounds(position);
    }

    /**
     * Ajoute un hexagone à une position spécifique sur le plateau.
     * Met également à jour les limites du plateau.
     *
     * @param position La position où ajouter l'hexagone.
     */
    public void addHexagon(Point position) {
        hexagons.put(position, true);
        updateBounds(position);
    }

    /**
     * Récupère la tuile présente à une position donnée.
     *
     * @param position La position de la tuile.
     * @return La tuile à la position spécifiée, ou null si aucune tuile n'est présente.
     */
    public Tile getTile(Point position) {
        return tiles.get(position);
    }

    /**
     * Récupère toutes les tuiles présentes sur le plateau.
     *
     * @return Une map contenant les positions et les tuiles correspondantes.
     */
    public Map<Point, Tile> getTiles() {
        return tiles;
    }

    /**
     * Récupère toutes les positions d'hexagones sur le plateau.
     *
     * @return Une map contenant les positions et leur état (toujours true ici).
     */
    public Map<Point, Boolean> getHexagons() {
        return hexagons;
    }

    /**
     * Met à jour les limites du plateau en fonction de la position ajoutée.
     *
     * @param position La position à prendre en compte pour les limites.
     */
    private void updateBounds(Point position) {
        if (position.x < minX) minX = position.x;
        if (position.x > maxX) maxX = position.x;
        if (position.y < minY) minY = position.y;
        if (position.y > maxY) maxY = position.y;
    }

    /**
     * Récupère la limite minimale en X du plateau.
     *
     * @return La coordonnée X minimale.
     */
    public int getMinX() {
        return minX;
    }

    /**
     * Récupère la limite maximale en X du plateau.
     *
     * @return La coordonnée X maximale.
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * Récupère la limite minimale en Y du plateau.
     *
     * @return La coordonnée Y minimale.
     */
    public int getMinY() {
        return minY;
    }

    /**
     * Récupère la limite maximale en Y du plateau.
     *
     * @return La coordonnée Y maximale.
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * Définit la taille du plateau. Méthode prévue pour compatibilité future.
     *
     * @param width  Largeur souhaitée du plateau.
     * @param height Hauteur souhaitée du plateau.
     */
    public void setPlateauSize(int width, int height) {
        // Cette méthode est ici pour compatibilité.
    }
}
