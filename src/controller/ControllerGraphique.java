package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;
import view.AffichageGraphique;
import view.AffichageSetup;

public class ControllerGraphique extends Application {
    private Stage stage;
    private Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage = AffichageSetup(stage, this);     
    }

    public void switchToMainWindow(String army1, String army2, int cote) {
        AffichageGraphique affG = new AffichageGraphique(stage, cote, this);
        Game game = Game.setGame(army1, army2,cote);
        game.addObserver(affG);
        game.setChangedAndNotify(); // Provoque un 1er affichage
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    

}
