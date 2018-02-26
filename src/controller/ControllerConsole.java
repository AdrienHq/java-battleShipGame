package controller;

import java.util.Scanner;
import model.Direction;
import model.Game;
import model.MerBoard;
import view.AffichageConsole;

public class ControllerConsole {

    public static void main(String[] args) {
        AffichageConsole affichage = new AffichageConsole();
        String army1 = null, army2 = null;
        Scanner clavier = new Scanner(System.in);
        affichage.askName1();
        if (clavier.hasNext()) {
            army1 = clavier.nextLine();

        } else; // ??
        affichage.askName2();
        if (clavier.hasNext()) {
            army2 = clavier.nextLine();

        } else ;
        System.out.println(army1 + " " + army2);
        Game game = Game.setGame(army1, army2);
        boolean finJeu = false;
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage);//Ajoute l'observer 
        Boolean Joueur = true ;
        do {
           if(Joueur){
           
           }else{
           
           } 
                
            
        
        } while (!finJeu);
        
        
    }

//        Direction direction;
//        
//        
//        do {
//            direction = saisieToucheClavier(); //la direction vaut la saisie au clavier
//            if (direction != null) { //tant que la personne joue 
//                finJeu = Game.jouer(direction); 
//                if (finJeu) {
//                    game.notifyObservers(); //Notify l'obs de la fin du jeu 
//                }
//            }
//        } while (!finJeu);
    
//        if (game.joueurAPerdu()) { //Si il reste encore des bateaux
//            affichage.showLose(); //affiche l'ecran de fin Perdant
//        } else {
//            affichage.showVictory(); //affiche l'ecran de fin Gagnant
//        }  
//    }
//    
//    
//    public static Direction saisieToucheClavier() {
//        char c = '1'; // un character pour conserver les choix utilisateur
//        try {
//            c = (char)System.in.read();
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//        return getDirection(c);                        
//    }
//    
//    private static Direction getDirection (char c) {
//        switch(c) {                     //Activation en fonction des choix de l'utilisateur
//            case 'z' : return Direction.HAUT;
//            case 's' : return Direction.BAS;
//            case 'q' : return Direction.GAUCHE;
//            case 'd' : return Direction.DROITE;
//            case 't' : return Direction.STOP;
//            case 'x' : System.exit(1);
//            default : return null;
//        }
//    }
}
