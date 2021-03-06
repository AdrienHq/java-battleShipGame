package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;
import model.Navire;
import view.AffichageGraphique;
import view.AffichageSetup;

public class ControllerGraphique extends Application {

    private Stage stage;
    private Game game;

    // Vrai si on a cliqué sur un bateau pour le déplacer
    private boolean bateauBouge = false;
    private boolean bateauTir = false;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        new AffichageSetup(stage, this);
    }

    public static void main(String[] args) {
        launch(args);
        
    }

    public void switchToMainWindow(String army1, String army2, int cote) {
        AffichageGraphique affG = new AffichageGraphique(stage, cote, this);
        Game game = Game.setGame(army1, army2, cote);
        game.addObserver(affG);
        game.setChangedAndNotify(); // Provoque un 1er affichage
    }

    // Quand l'utilisateur clique sur une case vide
    public void clickCaseVide(int x, int y) {
        if (bateauBouge) {
//            Navire.setPosition(x,y); // Déplace le bateau SET POSITION DU BATEAU
//game.deplacebateau(army1,pos,newPos,portee)
            bateauBouge = false;
        }
    }
    
    public void clickAutreBateauPourTir(int x, int y) {
        if (bateauTir) {
//            game.tire(army1,pos,portee);
            bateauTir = false;
        }
    }

    // Quand l'utilisateur clique sur un bateau
    public void clickBateau(int x, int y) {
        bateauBouge = true;
        bateauTir = true;
    }

}
