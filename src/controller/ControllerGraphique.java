package controller;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Game;
import model.Position;
import view.AffichageGraphique;
import view.AffichageSetup;

public class ControllerGraphique extends Application {

    private Stage stage;
    private Game game;
    Boolean gameOver = false;
    private AffichageGraphique affG;
    private boolean joueur;
    private boolean deplacementBateau;
    private boolean tirBateau;
    private String joueur1;
    private String joueur2;
    private boolean choixPositionDeplacement;
    private String armyCourante;
    private int portee;
    private Position oldPos ;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        new AffichageSetup(stage, this);
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void switchToMainWindow(String army1, String army2, int cote ) {
        affG = new AffichageGraphique(stage, cote, this);
        game = Game.setGame(army1, army2, cote);
        game.addObserver(affG);
        game.setChangedAndNotify(); // Provoque un 1er affichage
        jouer(affG, army1, army2);
    }
   
    private void jouer(AffichageGraphique affG, String army1, String army2) {
        joueur = true;
        choixPositionDeplacement = false;
        deplacementBateau = false;
        tirBateau = true;
        joueur1 = army1;
        joueur2 = army2;
        affG.afficherTextAction(army1 ," à vous de tirer");

    }

    private Position positionClicked;

    public Position getPositionClicked() {
        return this.positionClicked;
    }

    public void clickBateau(int x, int y) {
        positionClicked = new Position(x, y);

        if (joueur) {
            armyCourante = joueur1;
        } else {
            armyCourante = joueur2;
        }
        if (tirBateau) {
            if (game.tirGraphique(armyCourante, positionClicked, portee)) {
                tirBateau = false;
                choixPositionDeplacement = true ;
                affG.afficherTextDebug(armyCourante, "portee :" + portee);
                affG.afficherTextAction(armyCourante ," ,sélectionner le bateau à déplacer ");
            } else {
                affG.afficherTextDebug(armyCourante, "la case n'est pas valide,réessayer");
            }
        } 
        else if (choixPositionDeplacement) {
            
            if(game.choixBateauDeplacementGraphique(armyCourante, positionClicked)){
                affG.afficherTextDebug(armyCourante, " ");
                choixPositionDeplacement = false ;
                deplacementBateau = true ;
                
                affG.afficherTextAction(armyCourante ," ,sélectionner la case où déplacer le bateau! ");
                oldPos = positionClicked ;
            }
           
        }
        
        if(gameOver){
           affG.afficherTextAction(armyCourante ," Vous avez gagné!!") ;
        }
    }

    public void clickCaseVide(int x, int y) {
        positionClicked = new Position(x, y);
        if (deplacementBateau) {
            if(game.deplaceBateauGraphique(armyCourante, oldPos, positionClicked)){
                    deplacementBateau = false ; 
                    
                    tirBateau = true ;
                    if(gameOver){
                      affG.afficherTextAction(armyCourante ," Vous avez perdu!!") ;
                    }else{
                        if(joueur){
                            joueur = false ;
                            armyCourante = joueur2 ;
                        }else{
                            joueur = true ;
                            armyCourante = joueur1 ;
                        }
                        affG.afficherTextAction(armyCourante ," à vous de tirer!");
                    }
                    
                } 
            ;
        }
    }
}
