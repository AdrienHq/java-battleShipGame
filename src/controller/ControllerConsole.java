package controller;

import java.util.Scanner;
import model.Army;
import model.Game;
import model.MerBoard;
import model.Position;
import view.AffichageConsole;

public class ControllerConsole {

    public static void main(String[] args) {
        AffichageConsole affichage = new AffichageConsole();
        boolean gameOver = false;
        Army joueur1 = null; Army joueur2 = null; 
        String army1 = null, army2 = null;
        int cote = 5;
        Scanner clavier = new Scanner(System.in);
        affichage.askName1();
        if (clavier.hasNext()) {
            army1 = clavier.nextLine();

        } else; // ??
        affichage.askName2();
        if (clavier.hasNext()) {
            army2 = clavier.nextLine();

        } else ;
        affichage.askTailleCote();
        if (clavier.hasNext()) {
            cote = clavier.nextInt();                                     //ICI CHANGEMENT A CONTINUER 
        } else ;
        Game game = Game.setGame(army1, army2,cote);
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage);//Ajoute l'observer 
        Boolean Joueur = true;
        
        do {
            if(Joueur){ //si c'est au tour du joueur 1
                affichage.choixBateauTireur(army1)  ;  
                // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
                //return la portee
                affichage.portee(portee); //print la portee
                //applique le tir (qui met à jour la board et la reprint auto
                
                affichage.choixBateauDeplacement(army1);
                //tant que choix invalide demander la saisie du bateau( String nom de la case ) 
                affichage.choixCaseDeplacement();
                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
                //appliquer le déplacement(maj board et reprint auto
                Joueur=false ; // On donne la main à l'autre joueur   
            
            }else{      //si c'est au tour du joueur 2
                affichage.choixBateauTireur(army2)  ; 
                // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
                //return la portee
                affichage.portee(portee); //print la portee
                //applique le tir (qui met à jour la board et la reprint auto
                
                affichage.choixBateauDeplacement(army2);
                //tant que choix invalide demander la saisie du bateau( String nom de la case ) 
                affichage.choixCaseDeplacement();
                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
                //appliquer le déplacement(maj board et reprint auto
                Joueur = true ; //On donne la main à l'autre joueur
            }
            //si joueur1.listeVide() 
                affichage.showVictory(army1);
            //si joueur2.listeVide()){
                affichage.showVictory(army2);
        } while (!gameOver); 
        

//        do {
//            int res = toucheClavier(); //prends le int taper par l'user 
//            do {
//                affichage.askPermissionDeplacement(); //demande si il veut un déplacement 1 = OUI et 2 = NON 
//                Position pos = null;
//                game.jouer(pos);:
//
//                if (res == 1) {
//
//                }
//            } while (res != 1 || res != 2);
//
//        } while (!gameOver); 
//        if(joueur1.listeVide()){
//            affichage.showVictory();
//        }else if(joueur2.listeVide()){
//            affichage.showVictory();
//        }      
    }

    public static int toucheClavier() {
        int clavier = 0;
        try {
            clavier = (int) System.in.read();
        } catch (Exception e) {
            System.out.println("Problème lors de la saisie");
        }
        return clavier;
    }

    public static void getDirection(Position pos) {

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
