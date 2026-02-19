/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/model/TileGenerator.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Classe responsable de la génération aléatoire des tuiles. Elle gère également
 * le score associé aux tuiles générées et impose une limite au nombre de tuiles
 * pouvant être générées (50 maximum).
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Générateur de tuiles aléatoires pour le jeu. Cette classe utilise un générateur
 * pseudo-aléatoire pour produire des tuiles avec des terrains variés, tout en 
 * maintenant une limite sur le nombre total de tuiles.
 */
public class TileGenerator {
    private final Random random; 
    private final int maxTiles; 
    private int tileCount = 0;
    private int score = 0; 

    /**
     * Constructeur de la classe TileGenerator.
     *
     * @param seed Une graine aléatoire pour garantir la reproductibilité des tuiles générées.
     */
    public TileGenerator(long seed) {
        this.random = new Random(seed);
        this.maxTiles = 50; 
    }

    /**
     * Génère une tuile aléatoire avec des terrains variés.
     * Une tuile peut contenir un ou deux terrains.
     *
     * @return Une instance de Tile représentant la tuile générée.
     * @throws IllegalStateException Si la limite de 50 tuiles a été atteinte.
     */
    public Tile generateRandomTile() {
        if (tileCount >= maxTiles) {
            throw new IllegalStateException("La limite de 50 tuiles a été atteinte.");
        }

        tileCount++;
        score += calculateTileScore(); 

        Terrain terrain1 = Terrain.values()[random.nextInt(Terrain.values().length)];
        if (random.nextBoolean()) {
            return new Tile(terrain1, null, 6);
        }
        Terrain terrain2;
        do {
            terrain2 = Terrain.values()[random.nextInt(Terrain.values().length)];
        } while (terrain2 == terrain1);

        int terrain1Sides = random.nextInt(3) + 1; // 1 à 3 côtés pour terrain1
        return new Tile(terrain1, terrain2, terrain1Sides);
    }

    /**
     * Génère une liste de tuiles jusqu'à atteindre la limite fixée.
     *
     * @return Une liste contenant les tuiles générées.
     */
    public List<Tile> generateTiles() {
        List<Tile> tiles = new ArrayList<>();
        try {
            while (tileCount < maxTiles) {
                tiles.add(generateRandomTile());
            }
            System.out.println("Jeu terminé avec succès après la génération de 50 tuiles.");
            System.out.println("Score final : " + score);
        } catch (IllegalStateException e) {
            System.err.println("Fin prématurée du jeu : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération de la liste de tuiles : " + e.getMessage());
            throw new RuntimeException("Erreur critique lors de la génération des tuiles", e);
        }
        return tiles;
    }

    /**
     * Vérifie si le jeu est terminé, c'est-à-dire si 50 tuiles ont été générées.
     *
     * @return true si le jeu est terminé, sinon false.
     */
    public boolean isGameFinished() {
        return tileCount >= maxTiles;
    }

    /**
     * Calcule un score pour chaque tuile posée. Le score est attribué aléatoirement
     * entre 1 et 10.
     *
     * @return Un score aléatoire pour une tuile.
     */
    private int calculateTileScore() {
        return random.nextInt(10) + 1;
    }
}
