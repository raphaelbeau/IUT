# Variables
JC = javac
MARIA_DB_CLIENT_PATH = src/utils/mariadb-client.jar
MARIA_DB_CLASS = src/utils
JCFLAGS = -d bin -classpath "$(MARIA_DB_CLIENT_PATH):$(MARIA_DB_CLASS)" -sourcepath "src" -encoding UTF-8 -implicit:none
JVM = java
JVMFLAGS = -classpath ".:resources:bin:$(MARIA_DB_CLIENT_PATH):$(MARIA_DB_CLASS)"
JARNAME = game.jar
ENTRY = controller.Main
SRC = src
DOCDIR = docs

# Cible "all" pour compiler toutes les classes du projet
all: bin/controller/Main.class \
     bin/model/Board.class bin/model/ScoreModel.class bin/model/Terrain.class bin/model/TileGenerator.class bin/model/Tile.class \
     bin/utils/Database.class bin/utils/HexagonUtils.class bin/utils/ScoreDatabase.class bin/utils/SeedRepository.class bin/utils/TileRepository.class \
     bin/view/ArrowKeyMovementHandler.class bin/view/BoardMouseController.class bin/view/BoardPanel.class bin/view/BoardPanelComponentListener.class \
     bin/view/BoardSizeAdjuster.class bin/view/ButtonActionHandler.class bin/view/ControlPanelSizeAdjuster.class bin/view/GameView.class \
     bin/view/GameViewComponentListener.class bin/view/GlobalMouseWheelListener.class bin/view/HexagonClickListener.class bin/view/HomeAction.class bin/view/HomeButtonAction.class \
     bin/view/HoverEffectMouseAdapter.class bin/view/MiniMapPanel.class bin/view/MouseClickListener.class bin/view/MoveDownAction.class \
     bin/view/MoveLeftAction.class bin/view/MoveRightAction.class bin/view/MoveUpAction.class bin/view/NextTilePanel.class \
     bin/view/NextTilePanelMouseListener.class bin/view/QuitActionHandler.class bin/view/QuitAction.class bin/view/QuitButtonAction.class \
     bin/view/ResizeListener.class bin/view/ReturnToSeriesMenuAction.class bin/view/ScoreFinal.class bin/view/ScoreView.class \
     bin/view/SeriesMenu.class bin/view/SplashScreen.class bin/view/SwitchToGameAction.class bin/view/SearchAction.class

# CONTROLLER
bin/controller/Main.class: src/controller/Main.java bin/controller/GameController.class bin/controller/ScoreController.class bin/controller/SeedSelectionFrame.class bin/controller/SeedSelectionListener.class
	$(JC) $(JCFLAGS) src/controller/Main.java

bin/controller/GameController.class: src/controller/GameController.java
	$(JC) $(JCFLAGS) src/controller/GameController.java

bin/controller/ScoreController.class: src/controller/ScoreController.java
	$(JC) $(JCFLAGS) src/controller/ScoreController.java

bin/controller/SeedSelectionFrame.class: src/controller/SeedSelectionFrame.java
	$(JC) $(JCFLAGS) src/controller/SeedSelectionFrame.java

bin/controller/SeedSelectionListener.class: src/controller/SeedSelectionListener.java
	$(JC) $(JCFLAGS) src/controller/SeedSelectionListener.java

# MODEL
bin/model/Board.class: src/model/Board.java
	$(JC) $(JCFLAGS) src/model/Board.java

bin/model/ScoreModel.class: src/model/ScoreModel.java
	$(JC) $(JCFLAGS) src/model/ScoreModel.java

bin/model/Terrain.class: src/model/Terrain.java
	$(JC) $(JCFLAGS) src/model/Terrain.java

bin/model/TileGenerator.class: src/model/TileGenerator.java
	$(JC) $(JCFLAGS) src/model/TileGenerator.java

bin/model/Tile.class: src/model/Tile.java
	$(JC) $(JCFLAGS) src/model/Tile.java

# UTILS
bin/utils/Database.class: src/utils/Database.java
	$(JC) $(JCFLAGS) src/utils/Database.java

bin/utils/HexagonUtils.class: src/utils/HexagonUtils.java
	$(JC) $(JCFLAGS) src/utils/HexagonUtils.java

bin/utils/ScoreDatabase.class: src/utils/ScoreDatabase.java
	$(JC) $(JCFLAGS) src/utils/ScoreDatabase.java

bin/utils/SeedRepository.class: src/utils/SeedRepository.java
	$(JC) $(JCFLAGS) src/utils/SeedRepository.java

bin/utils/TileRepository.class: src/utils/TileRepository.java
	$(JC) $(JCFLAGS) src/utils/TileRepository.java

# VIEW
bin/view/ArrowKeyMovementHandler.class: src/view/ArrowKeyMovementHandler.java
	$(JC) $(JCFLAGS) src/view/ArrowKeyMovementHandler.java

bin/view/BoardMouseController.class: src/view/BoardMouseController.java
	$(JC) $(JCFLAGS) src/view/BoardMouseController.java

bin/view/BoardPanel.class: src/view/BoardPanel.java
	$(JC) $(JCFLAGS) src/view/BoardPanel.java

bin/view/BoardPanelComponentListener.class: src/view/BoardPanelComponentListener.java
	$(JC) $(JCFLAGS) src/view/BoardPanelComponentListener.java

bin/view/BoardSizeAdjuster.class: src/view/BoardSizeAdjuster.java
	$(JC) $(JCFLAGS) src/view/BoardSizeAdjuster.java

bin/view/ButtonActionHandler.class: src/view/ButtonActionHandler.java
	$(JC) $(JCFLAGS) src/view/ButtonActionHandler.java

bin/view/ControlPanelSizeAdjuster.class: src/view/ControlPanelSizeAdjuster.java
	$(JC) $(JCFLAGS) src/view/ControlPanelSizeAdjuster.java

bin/view/GameView.class: src/view/GameView.java
	$(JC) $(JCFLAGS) src/view/GameView.java

bin/view/GameViewComponentListener.class: src/view/GameViewComponentListener.java
	$(JC) $(JCFLAGS) src/view/GameViewComponentListener.java

# Nouvelle classe ajoutée
bin/view/GlobalMouseWheelListener.class: src/view/GlobalMouseWheelListener.java
	$(JC) $(JCFLAGS) src/view/GlobalMouseWheelListener.java

bin/view/HexagonClickListener.class: src/view/HexagonClickListener.java
	$(JC) $(JCFLAGS) src/view/HexagonClickListener.java

bin/view/HomeAction.class: src/view/HomeAction.java
	$(JC) $(JCFLAGS) src/view/HomeAction.java

bin/view/HomeButtonAction.class: src/view/HomeButtonAction.java
	$(JC) $(JCFLAGS) src/view/HomeButtonAction.java

bin/view/HoverEffectMouseAdapter.class: src/view/HoverEffectMouseAdapter.java
	$(JC) $(JCFLAGS) src/view/HoverEffectMouseAdapter.java

bin/view/MiniMapPanel.class: src/view/MiniMapPanel.java
	$(JC) $(JCFLAGS) src/view/MiniMapPanel.java

bin/view/MouseClickListener.class: src/view/MouseClickListener.java
	$(JC) $(JCFLAGS) src/view/MouseClickListener.java

bin/view/MoveDownAction.class: src/view/MoveDownAction.java
	$(JC) $(JCFLAGS) src/view/MoveDownAction.java

bin/view/MoveLeftAction.class: src/view/MoveLeftAction.java
	$(JC) $(JCFLAGS) src/view/MoveLeftAction.java

bin/view/MoveRightAction.class: src/view/MoveRightAction.java
	$(JC) $(JCFLAGS) src/view/MoveRightAction.java

bin/view/MoveUpAction.class: src/view/MoveUpAction.java
	$(JC) $(JCFLAGS) src/view/MoveUpAction.java

bin/view/NextTilePanel.class: src/view/NextTilePanel.java
	$(JC) $(JCFLAGS) src/view/NextTilePanel.java

bin/view/NextTilePanelMouseListener.class: src/view/NextTilePanelMouseListener.java
	$(JC) $(JCFLAGS) src/view/NextTilePanelMouseListener.java

bin/view/QuitActionHandler.class: src/view/QuitActionHandler.java
	$(JC) $(JCFLAGS) src/view/QuitActionHandler.java

bin/view/QuitAction.class: src/view/QuitAction.java
	$(JC) $(JCFLAGS) src/view/QuitAction.java

bin/view/QuitButtonAction.class: src/view/QuitButtonAction.java
	$(JC) $(JCFLAGS) src/view/QuitButtonAction.java

bin/view/ResizeListener.class: src/view/ResizeListener.java
	$(JC) $(JCFLAGS) src/view/ResizeListener.java

bin/view/ReturnToSeriesMenuAction.class: src/view/ReturnToSeriesMenuAction.java
	$(JC) $(JCFLAGS) src/view/ReturnToSeriesMenuAction.java

bin/view/ScoreFinal.class: src/view/ScoreFinal.java
	$(JC) $(JCFLAGS) src/view/ScoreFinal.java

bin/view/ScoreView.class: src/view/ScoreView.java
	$(JC) $(JCFLAGS) src/view/ScoreView.java

bin/view/SeriesMenu.class: src/view/SeriesMenu.java
	$(JC) $(JCFLAGS) src/view/SeriesMenu.java

bin/view/SplashScreen.class: src/view/SplashScreen.java
	$(JC) $(JCFLAGS) src/view/SplashScreen.java

bin/view/SwitchToGameAction.class: src/view/SwitchToGameAction.java
	$(JC) $(JCFLAGS) src/view/SwitchToGameAction.java

bin/view/SearchAction.class: src/view/SearchAction.java
	$(JC) $(JCFLAGS) src/view/SearchAction.java

# Règles générales
run: all
	$(JVM) $(JVMFLAGS) $(ENTRY)

clean:
	rm -rf bin
	rm -rf $(DOCDIR)

jar: all
	jar cvfm $(JARNAME) resources/manifest.txt -C bin/ . -C resources/ .

javadoc:
	javadoc -d $(DOCDIR) -sourcepath src -subpackages controller:model:utils:view -classpath $(MARIA_DB_CLIENT_PATH)
	@echo "La documentation a été générée dans $(DOCDIR)."
	@echo "Ouvrez $(DOCDIR)/index.html dans votre navigateur."
