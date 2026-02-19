/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/model/Tile.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe représentant une tuile dans le jeu. Une tuile peut contenir jusqu'à deux types
 * de terrains différents, répartis sur ses six côtés. Cette classe gère les terrains
 * et leurs associations avec les côtés.
 */

package model;

/**
 * Classe représentant une tuile dans le jeu. Une tuile contient deux terrains possibles
 * et définit la manière dont ils sont répartis sur ses côtés.
 */
public class Tile {

    /**
     * Le premier terrain de la tuile (obligatoire, immuable après initialisation).
     */
    private final Terrain terrain1;

    /**
     * Le second terrain de la tuile (peut être null si la tuile ne contient qu'un seul terrain).
     */
    private final Terrain terrain2;

    /**
     * Nombre de côtés associés au premier terrain.
     */
    private final int terrain1Sides;

    /**
     * Constructeur de la classe Tile.
     *
     * @param terrain1      Le premier terrain de la tuile.
     * @param terrain2      Le second terrain de la tuile (peut être null si la tuile est homogène).
     * @param terrain1Sides Le nombre de côtés associés au premier terrain.
     */
    public Tile(Terrain terrain1, Terrain terrain2, int terrain1Sides) {
        this.terrain1 = terrain1;
        this.terrain2 = terrain2;
        this.terrain1Sides = terrain1Sides;
    }

    /**
     * Retourne le premier terrain de la tuile.
     *
     * @return Le premier terrain.
     */
    public Terrain getTerrain1() {
        return terrain1;
    }

    /**
     * Retourne le second terrain de la tuile.
     *
     * @return Le second terrain, ou null si la tuile ne contient qu'un seul terrain.
     */
    public Terrain getTerrain2() {
        return terrain2;
    }

    /**
     * Retourne le nombre de côtés associés au premier terrain.
     *
     * @return Le nombre de côtés du terrain1.
     */
    public int getTerrain1Sides() {
        return terrain1Sides;
    }

    /**
     * Calcule et retourne le nombre de côtés associés au second terrain.
     * En supposant que la tuile a toujours 6 côtés.
     *
     * @return Le nombre de côtés du terrain2.
     */
    public int getTerrain2Sides() {
        return 6 - terrain1Sides;
    }

    /**
     * Vérifie si la tuile est composée d'un seul terrain.
     * Cela se produit si le second terrain est null.
     *
     * @return true si la tuile a un seul terrain, false sinon.
     */
    public boolean hasSingleTerrain() {
        return terrain2 == null;
    }

    /**
     * Retourne le terrain associé à un côté spécifique (de 0 à 5).
     *
     * @param side L'indice du côté (entre 0 et 5).
     * @return Le terrain associé au côté spécifié, ou null si aucun terrain n'est défini.
     */
    public Terrain getTerrainForSide(int side) {
        if (side < terrain1Sides) {
            return terrain1;
        } else if (terrain2 != null) {
            return terrain2;
        }
        return null;
    }
}
