/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/model/Terrain.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Enumération représentant les différents types de terrains disponibles
 * dans le jeu. Chaque terrain peut être associé à une tuile.
 */

package model;

/**
 * Enumération des types de terrains utilisés dans le jeu.
 * Ces terrains définissent les caractéristiques des tuiles et influencent
 * les règles de placement ou de score.
 */
public enum Terrain {
    /**
     * Terrain représentant une zone d'eau.
     */
    MER,

    /**
     * Terrain représentant une zone de champ.
     */
    CHAMP,

    /**
     * Terrain représentant une forêt.
     */
    FORET,

    /**
     * Terrain représentant une prairie.
     */
    PRE,

    /**
     * Terrain représentant une montagne.
     */
    MONTAGNE
}
