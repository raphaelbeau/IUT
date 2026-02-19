import java.util.*;
import java.io.Serializable;

/**
 * Classe représentant la pioche de cartes du jeu.
 * Gère une pile de cartes d'où les joueurs peuvent piocher.
 * Les cartes sont mélangées lors de la création de la pioche.
 */
public class Pioche implements Serializable{
    
    /** La pile de cartes de la pioche */
    private Stack<Carte> cartes;
    
    /**
     * Constructeur de la pioche.
     * Crée une pioche à partir d'une liste de cartes et les mélange.
     * 
     * @param cartes la liste des cartes à ajouter à la pioche
     */
    public Pioche(List<Carte> cartes) {
        this.cartes = new Stack<>();
        this.cartes.addAll(cartes);
        Collections.shuffle(this.cartes);
    }
    
    /**
     * Pioche une carte du sommet de la pile.
     * 
     * @return la carte piochée, ou null si la pioche est vide
     */
    public Carte piocher() {
        return cartes.isEmpty() ? null : cartes.pop();
    }
    
    /**
     * Vérifie si la pioche est vide.
     * 
     * @return true si la pioche ne contient plus de cartes, false sinon
     */
    public boolean estVide() { return cartes.isEmpty(); }

    /**
     * Crée un jeu de cartes standard pour le jeu Jest.
     * Le jeu comprend 4 couleurs (Coeur, Pique, Carreau, Trèfle),
     * chacune contenant les cartes As, 2, 3, 4, plus un Joker.
     * Les cartes sont mélangées avant d'être retournées.
     * 
     * @return la liste des cartes du jeu de base
     */
    public static ArrayList<Carte> creerJeuJest() {
        ArrayList<Carte> deck = new ArrayList<>();

        String[] couleurs = {"Coeur", "Pique", "Carreau", "Trèfle"};
        String[] noms = {"As", "2", "3", "4"};
        int[] valeurs = {1, 2, 3, 4};

        for (String couleur : couleurs) {
            for (int i = 0; i < noms.length; i++) {
                deck.add(new CarteClassique(noms[i], couleur, valeurs[i]));
            }
        }
        deck.add(new Joker());

        Collections.shuffle(deck);
        return deck;
    }

    /**
     * Retourne le nombre de cartes restantes dans la pioche.
     * 
     * @return la taille de la pioche
     */
    public int getTaille() {
        return cartes.size();
    }
}