package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Army;
import model.Game;
import view.AffichageGraphique;
import view.AffichageSetup;

public class ControllerGraphique extends Application{
    private Stage stage;
    private Game game;
    Army joueur1 = null; Army joueur2 = null; 
    String army1 = null, army2 = null;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        new AffichageSetup(stage, this); // Fenêtre initiale (saisie taille)
    }
    
    public void afficheAffichageGraphiQUE(Army joueur1, Army joueur2) {
            
    }
    
     // fait apparaître la fenêtre principale de l'application
    public void switchToMainWindow(String army1, String army2, int taille) {
        AffichageGraphique affGraphique = new AffichageGraphique(stage, size, this); 
        Game game = Game.setGame(army1, army2);
        game.addObserver(affGraphique);
        affGraphique.afficherGame(); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
