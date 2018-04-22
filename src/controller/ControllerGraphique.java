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
        game = Game.setGame(army1, army2, cote);
        game.addObserver(affG);
        game.setChangedAndNotify(); // Provoque un 1er affichage
        jouer(affG, army1, army2);
    }

    boolean deplacementBateau = true;
    boolean tirBateau = true;
    boolean choixPositionDeplacement = true;

    private void jouer(AffichageGraphique affG, String army1, String army2) {

        Boolean entreeCorrecte = false;
        String armyCourante = army1;
        String pos = "";
        Position pX = null;
        int portee = 0;
        do {
            //tire ::
            affG.choixBateauTireur(armyCourante);
            do {
                System.out.println("on est la 1");

//                do{pX = affG.getPositionClicked();
//                }while(pX == null);
                System.out.println("on est la 2");
                pos = game.getStringPosByPos(pX);
                System.out.println("on est la 3");
                affG.afficherPosition(pos);
                System.out.println("on est la 4");
                entreeCorrecte = game.tire(armyCourante, pos, portee);   //tirer 
            } while (!entreeCorrecte);
            entreeCorrecte = false;

            //choixbateaudéplacement ::
            affG.choixBateauADeplacer(armyCourante);
            do {
                pX = affG.getPositionClicked();
                pos = game.getStringPosByPos(pX);
                entreeCorrecte = game.choixBateauDeplacement(armyCourante, pos, portee);

            } while (!entreeCorrecte);
            entreeCorrecte = false;

            //deplacement ::
            do {
                pX = affG.getPositionClicked();
                String newPos = game.getStringPosByPos(pX);
                entreeCorrecte = game.deplacebateau(armyCourante, pos, newPos, portee);
            } while (!entreeCorrecte);
            choixPositionDeplacement = true;
            tirBateau = true;
            deplacementBateau = true;

            //switch army
            if (armyCourante == army1) {
                armyCourante = army2;
            } else {
                armyCourante = army1;
            }

        } while (!gameOver);
        //afficher victoire joueur

    }
////            if(joueur1.listeVide()){ 
////                affichage.showVictory(army1); 
////                gameOver = true ;
////            }
////            if(joueur2.listeVide()){ 
////                affichage.showVictory(army2);
////                gameOver = true ;
////            }
//            //si joueur2.listeVide()){
//        } while (!gameOver); 
//        System.out.println("partie finie"); //à deplacer
//
//    }
    private AffichageGraphique affG;

    public void clickBateau(int x, int y, MouseEvent e) {
        if (choixPositionDeplacement) {
            //fonction recupere pos
            double tmp = e.getY() / 60;
            x = (int) (e.getX() / 60);
//            System.out.println(x);
            y = (int) tmp;
//            System.out.println(y);
            affG.setPositionClicked(new Position(x, y));
            choixPositionDeplacement = false;
        }
        if (tirBateau) {
            //fonction tir; 
            tirBateau = false;
        }
    }

    public void clickCaseVide(int x, int y, MouseEvent e, AffichageGraphique affG) {
        if (deplacementBateau) {
            //fonction deplacement
            deplacementBateau = false;
        }
//        if (choixPositionDeplacement) {
//            //fonction deplacement
//        }
    }

    // Quand l'utilisateur clique sur une case vide
//    public void clickCaseVide(int x, int y) {
//        if (bateauBouge) {
////            Navire.setPosition(x,y); // Déplace le bateau SET POSITION DU BATEAU
////game.deplacebateau(army1,pos,newPos,portee)
//            bateauBouge = true;
//        }
//    }
//
//    public void clickAutreBateauPourTir(int x, int y) {
//        if (bateauTir) {
////            game.tire(army1,pos,portee);
//            bateauTir = false;
//        }
//    }
//
//    // Quand l'utilisateur clique sur un bateau
//    public void clickBateau(int x, int y) {
//        bateauBouge = true;
//        bateauTir = true;
//    }
}
