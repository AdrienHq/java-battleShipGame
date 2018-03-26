package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;
import model.Position;
import view.AffichageGraphique;
import view.AffichageSetup;

public class ControllerGraphique extends Application {

    private Stage stage;
    private Game game;
    Boolean gameOver = false;

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
        game = Game.setGame(army1, army2, cote);
        game.addObserver(affG);
        game.setChangedAndNotify(); // Provoque un 1er affichage
        jouer(affG, army1, army2);
    }

    private void jouer(AffichageGraphique affG, String army1, String army2) {
        Boolean Joueur = true;

//        do {
        if (Joueur) { //si c'est au tour du joueur 1
            String pos = "";
            int portee = 0;
            do {
                affG.afficherPosition("debug testttt");
                System.out.println("on sort la0");
                affG.ChoixBateauADeplacer(army1);
                System.out.println("on sort la1");
                Position pX = affG.getPositionClicked();
                System.out.println(affG.getX());
//                    System.out.println("on sort la2");
//                    pos = game.getStringPosByPos(pX);
                System.out.println("on sort la3");
                affG.afficherPosition(pos);
                System.out.println("on sort la4");
                //game.choixBateauDeplacement(army1,pos,portee) ;
                bateauBouge = true;
            } while (bateauBouge == false);

//                do{
//                    affG.choixBateauTireur(army1);
//                    
//                    game.tire(army1,pos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while( bateauTir == false );  
        }

    }

//                System.out.println("");//affichage.portee(portee); //print la portee
//                entreeCorrecte = false ;
//                pos = "";
//                
//                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
//                    affichage.choixBateauDeplacement(army1);
//                    //clavier.nextLine();
//                    pos = clavier.nextLine();
//                    entreeCorrecte = game.choixBateauDeplacement(army1,pos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while(!entreeCorrecte);
//                entreeCorrecte = false ;
//                //pos = "";
//                String newPos = "";
//                
//                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
//                    affichage.choixCaseDeplacement();
//                    newPos = clavier.nextLine();
//                    entreeCorrecte = game.deplacebateau(army1,pos,newPos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while(!entreeCorrecte);
//                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
//                //appliquer le déplacement(maj board et reprint auto
//                
//                Joueur=false ; // On donne la main à l'autre joueur   
//            
//            }else{      //si c'est au tour du joueur 2
//                Boolean entreeCorrecte = false ;
//                String pos = "" ;
//                int portee = 0;
//                do{
//                    affichage.choixBateauTireur(army2)  ;
//                    if (clavier.hasNext()) {
//                        //clavier.nextLine();
//                        pos = clavier.nextLine();
//                    } else;
//                    System.out.println("entrée ok = "+pos);
//                    entreeCorrecte = game.tire(army2,pos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while(!entreeCorrecte);  // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
//                System.out.println("");//affichage.portee(portee); //print la portee
//                entreeCorrecte = false ;
//                pos = "";
//                
//                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
//                    affichage.choixBateauDeplacement(army2);
//                    //clavier.nextLine();
//                    pos = clavier.nextLine();
//                    entreeCorrecte = game.choixBateauDeplacement(army2,pos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while(!entreeCorrecte);
//                entreeCorrecte = false ;
//                //pos = "";
//                String newPos = "";
//                
//                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
//                    affichage.choixCaseDeplacement();
//                    newPos = clavier.nextLine();
//                    entreeCorrecte = game.deplacebateau(army2,pos,newPos,portee) ; //verifie la position ,tire et renvoie la portée
//                }
//                while(!entreeCorrecte);
//                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
//                //appliquer le déplacement(maj board et reprint auto
//                
//                Joueur=true ; // On donne la main à l'autre joueur   
//            
//                //meme chose que joueur1 à copier 
//            }
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
    // Quand l'utilisateur clique sur une case vide
    public void clickCaseVide(int x, int y) {
        if (bateauBouge) {
//            Navire.setPosition(x,y); // Déplace le bateau SET POSITION DU BATEAU
//game.deplacebateau(army1,pos,newPos,portee)
            bateauBouge = true;
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
