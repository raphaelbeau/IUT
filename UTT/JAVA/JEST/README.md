Projet JEST - Jeu de Cartes
Bienvenue dans l'implémentation numérique du jeu de cartes Jest.
Le document attendu avec les justifications des diagramme est CR_JEST

Fonctionnalités
Mode Multi-Joueurs : Support pour joueurs physiques et virtuels (IA).

Intelligence Artificielle : Plusieurs stratégies disponibles (Aléatoire, Simple).

Système de Score Avancé : Calcul automatique des points incluant les bonus de couleurs, les suites et les trophées.

Extensions Incluses : Support complet des extensions Cameleon et Miroir.

Double Interface : Jouez via la console ou via une interface graphique (Swing) de manière synchronisée.

Persistance : Sauvegarde et chargement de parties en cours.

Architecture Technique
Le projet repose sur une conception logicielle rigoureuse :

Pattern MVC : Séparation stricte entre les données (Modèle), l'affichage (Vue) et la logique (Contrôleur).

Pattern Visitor : Utilisé pour le calcul des scores, permettant une extension facile des règles sans modifier les classes de base.

Pattern Strategy : Permet de changer dynamiquement l'intelligence des joueurs virtuels.

Pattern Composite : Gestion simultanée de la Vue Console et de la Vue Graphique.

Structure du Projet
src/ : Contient l'ensemble des fichiers sources .java.

bin/ : Dossier de destination des fichiers compilés .class.

doc/ : Documentation technique générée (Javadoc).

Installation et Utilisation
Ce projet utilise un Makefile pour simplifier les commandes de gestion.

1. Compilation
Pour compiler l'ensemble du projet :

make

2. Lancer le jeu
Pour démarrer une partie (lance la compilation automatiquement si nécessaire) :

make run

3. Générer la documentation
Pour générer la Javadoc complète du projet :

make doc

La documentation sera disponible dans le dossier doc/. Vous pouvez ouvrir index.html dans votre navigateur.

4. Nettoyage
Pour supprimer les fichiers compilés :

make clean

Pour un nettoyage complet (fichiers compilés + documentation) :

make clean-all

----------------------------------------------------------
Développé dans le cadre de l'UE LO02.