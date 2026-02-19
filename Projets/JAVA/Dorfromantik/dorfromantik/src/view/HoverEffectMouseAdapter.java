/**
 * Projet Dorfomantik
 * Chemin du fichier : dorfromantik/src/view/HoverEffectMouseAdapter.java
 * Fait par : Marwa Hafsati, Djedjiga Yahiaoui, et Raphael Beau
 *
 * Cette classe ajoute un effet visuel de survol (hover) à un bouton.
 * Lorsque la souris entre ou sort de la zone du bouton, sa couleur de fond est modifiée.
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Adaptateur pour gérer les effets de survol de la souris sur un bouton.
 * Change la couleur de fond du bouton lorsqu'il est survolé.
 */
public class HoverEffectMouseAdapter extends MouseAdapter {

    /**
     * Le bouton auquel appliquer l'effet de survol.
     */
    private final JButton button;

    /**
     * La couleur normale du bouton (affichée par défaut).
     */
    private final Color normalColor;

    /**
     * La couleur de survol du bouton (affichée lorsque la souris est dessus).
     */
    private final Color hoverColor;

    /**
     * Constructeur de l'adaptateur d'effet de survol.
     *
     * @param button      Le bouton auquel appliquer l'effet.
     * @param normalColor La couleur normale du bouton.
     * @param hoverColor  La couleur de survol du bouton.
     */
    public HoverEffectMouseAdapter(JButton button, Color normalColor, Color hoverColor) {
        this.button = button;
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
    }

    /**
     * Méthode appelée lorsque la souris entre dans la zone du bouton.
     * Change la couleur de fond du bouton en couleur de survol.
     *
     * @param e L'événement de la souris associé.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(hoverColor);
    }

    /**
     * Méthode appelée lorsque la souris sort de la zone du bouton.
     * Rétablit la couleur normale du bouton.
     *
     * @param e L'événement de la souris associé.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(normalColor);
    }
}
