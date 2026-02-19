# Dossiers
SRC_DIR := src
BIN_DIR := bin
DOC_DIR := doc

# Tous les fichiers .java
SOURCES := $(wildcard $(SRC_DIR)/*.java)

# Cible par défaut
all: compile

# Compilation en une seule commande javac
compile: $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Exécution (classe Main sans package)
run: compile
	java -cp $(BIN_DIR) Main

# Génération de la Javadoc
doc:
	mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 $(SOURCES)

# Nettoyage des .class
clean:
	rm -rf $(BIN_DIR)

# Nettoyage de la documentation
clean-doc:
	rm -rf $(DOC_DIR)

# Nettoyage complet
clean-all: clean clean-doc

.PHONY: all compile run doc clean clean-doc clean-all