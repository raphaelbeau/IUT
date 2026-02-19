import java.io.Serializable;

/**
 * Interface définissant une variante de règles pour le jeu Jest.
 * Une variante permet de modifier les règles de base du jeu en appliquant
 * des configurations spécifiques (nombre de tours, ordre de jeu, scoring, etc.).
 * Chaque implémentation définit un ensemble de règles particulières.
 */
public interface Variante extends Serializable{
    
    /**
     * Applique les règles de cette variante au jeu.
     * Configure le jeu selon les spécificités de la variante
     * (nombre de tours, ordre tournant, visiteur de score, etc.).
     * 
     * @param jeu l'instance du jeu à configurer
     */
    void appliquerRegles(Jeu jeu);
    
    /**
     * Retourne le nom de la variante.
     * 
     * @return le nom descriptif de la variante
     */
    String getNom();
}