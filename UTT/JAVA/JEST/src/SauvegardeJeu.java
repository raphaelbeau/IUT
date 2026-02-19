import java.io.*;

/**
 * Classe utilitaire pour gérer la sauvegarde et le chargement des parties de Jest.
 * Utilise la sérialisation Java pour persister l'état complet du jeu.
 * Les sauvegardes sont stockées dans le répertoire "saves" avec l'extension .ser.
 */
public class SauvegardeJeu {

    /**
     * Sauvegarde une partie de jeu dans un fichier.
     * Crée automatiquement le répertoire "saves" s'il n'existe pas.
     * Le fichier est enregistré avec l'extension .ser.
     * 
     * @param jeu l'instance du jeu à sauvegarder
     * @param nomFichier le nom du fichier de sauvegarde (sans extension)
     * @throws IOException si une erreur survient lors de l'écriture du fichier
     */
    public static void sauvegarderJeu(Jeu jeu, String nomFichier) throws IOException {
        File dir = new File("saves");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, nomFichier + ".ser");

        try (ObjectOutputStream oos =
                 new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(jeu);
        }
    }

    /**
     * Charge une partie de jeu depuis un fichier de sauvegarde.
     * Le fichier doit se trouver dans le répertoire "saves" avec l'extension .ser.
     * 
     * @param nomFichier le nom du fichier de sauvegarde (sans extension)
     * @return l'instance du jeu chargée
     * @throws IOException si une erreur survient lors de la lecture du fichier
     * @throws ClassNotFoundException si une classe nécessaire à la désérialisation n'est pas trouvée
     */
    public static Jeu chargerJeu(String nomFichier) throws IOException, ClassNotFoundException {
        File file = new File("saves", nomFichier + ".ser");
        try (ObjectInputStream ois =
                 new ObjectInputStream(new FileInputStream(file))) {
            return (Jeu) ois.readObject();
        }
    }
}