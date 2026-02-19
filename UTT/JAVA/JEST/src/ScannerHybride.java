import java.io.*;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scanner hybride qui écoute simultanément les entrées du clavier (console)
 * et les événements de l'interface graphique (boutons).
 * Utilise une BlockingQueue pour synchroniser les entrées des deux sources
 * et permet une interaction fluide entre la console et l'interface graphique.
 */
public class ScannerHybride {
    
    /** File d'attente bloquante pour les entrées */
    private BlockingQueue<String> queue;
    
    /** Thread d'écoute de la console */
    private Thread consoleThread;
    
    /** Indicateur d'exécution du scanner */
    private boolean running = true;
    
    /** Liste des listeners pour les entrées */
    private List<InputListener> listeners = new ArrayList<>();
    
    /**
     * Interface pour écouter les entrées du scanner hybride.
     * Permet de réagir aux entrées provenant de la console ou de l'interface graphique.
     */
    public interface InputListener {
        /**
         * Appelé lorsqu'une entrée est reçue.
         * 
         * @param input le texte de l'entrée
         * @param fromGraphic true si l'entrée provient de l'interface graphique, false si de la console
         */
        void onInput(String input, boolean fromGraphic);
    }
    
    /**
     * Constructeur du scanner hybride.
     * Initialise la file d'attente et démarre le thread d'écoute de la console.
     */
    public ScannerHybride() {
        queue = new LinkedBlockingQueue<>();
        
        consoleThread = new Thread(() -> {
            Scanner consoleScanner = new Scanner(System.in);
            while (running) {
                try {
                    if (consoleScanner.hasNextLine()) {
                        String line = consoleScanner.nextLine();
                        queue.offer(line);
                        notifierListeners(line, false);
                    }
                } catch (Exception e) {
                    if (running) {
                        e.printStackTrace();
                    }
                }
            }
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }
    
    /**
     * Ajoute un listener qui sera notifié à chaque entrée reçue.
     * 
     * @param listener le listener à ajouter
     */
    public void addInputListener(InputListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Notifie tous les listeners enregistrés d'une nouvelle entrée.
     * 
     * @param input le texte de l'entrée
     * @param fromGraphic true si l'entrée provient de l'interface graphique
     */
    private void notifierListeners(String input, boolean fromGraphic) {
        for (InputListener listener : listeners) {
            listener.onInput(input, fromGraphic);
        }
    }
    
    /**
     * Envoie du texte depuis l'interface graphique vers la file d'attente.
     * Utilisé par les boutons de l'interface pour simuler une entrée.
     * 
     * @param texte le texte à envoyer
     */
    public void envoyerDepuisGraphique(String texte) {
        queue.offer(texte);
        notifierListeners(texte, true);
    }
    
    /**
     * Lit la prochaine ligne d'entrée (console ou interface graphique).
     * Bloque jusqu'à ce qu'une entrée soit disponible.
     * 
     * @return la ligne d'entrée lue
     */
    public String nextLine() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Lit le prochain mot d'entrée (console ou interface graphique).
     * Extrait le premier mot de la prochaine ligne.
     * 
     * @return le premier mot de l'entrée
     */
    public String next() {
        String line = nextLine();
        return line.split("\\s+")[0];
    }
    
    /**
     * Lit le prochain entier depuis l'entrée.
     * 
     * @return l'entier lu, ou 0 en cas d'erreur de format
     */
    public int nextInt() {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Vérifie si le prochain token est un entier.
     * Note : retourne toujours true pour simplifier l'implémentation,
     * car on ne peut pas "peek" dans une BlockingQueue sans consommer.
     * 
     * @return true
     */
    public boolean hasNextInt() {
        return true;
    }
    
    /**
     * Arrête l'écoute de la console et interrompt le thread.
     */
    public void stop() {
        running = false;
        consoleThread.interrupt();
    }
}