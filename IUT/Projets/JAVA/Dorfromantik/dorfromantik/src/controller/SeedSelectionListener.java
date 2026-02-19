/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/controller/SeedSelectionListener.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette interface définit le contrat pour écouter les événements liés
 * à la sélection d'un seed dans le jeu. Elle est utilisée pour implémenter
 * des comportements spécifiques en réponse à la sélection d'un seed.
 */

package controller;

/**
 * Interface fonctionnelle pour gérer la sélection d'un seed (graine).
 * Les classes qui implémentent cette interface doivent définir le
 * comportement attendu lorsqu'un seed est sélectionné par l'utilisateur.
 */
public interface SeedSelectionListener {

    /**
     * Méthode appelée lorsque l'utilisateur effectue une sélection de seed.
     * Implémentez cette méthode pour définir des actions spécifiques à
     * exécuter après la sélection.
     *
     * @param seed Le seed (graine) sélectionné par l'utilisateur.
     */
    void onSeedSelected(long seed);
}
