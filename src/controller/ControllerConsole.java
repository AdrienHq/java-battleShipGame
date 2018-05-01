package controller;

import java.util.Scanner;
import model.Army;
import model.Game;
import view.AffichageConsole;

public class ControllerConsole {

    public static void main(String[] args) {
        AffichageConsole affichage = new AffichageConsole();
        boolean gameOver = false;
        String army1 = null, army2 = null;
        int cote = 5;
        Scanner clavier = new Scanner(System.in);
        affichage.askName1();
        if (clavier.hasNext()) {
            army1 = clavier.nextLine();

        } else; 
        affichage.askName2();
        if (clavier.hasNext()) {
            army2 = clavier.nextLine();

        } else ;
        affichage.askTailleCote();
        if (clavier.hasNext()) {
            cote = clavier.nextInt();                                     
        } else ;
        Game game = Game.setGame(army1, army2,cote);
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage);//Ajoute l'observer 
        
        Boolean Joueur = true;
        clavier.nextLine();
        do {
            if(Joueur){ //si c'est au tour du joueur 1
                 
                Boolean entreeCorrecte = false ;
                String pos = "" ;
                int portee = 0;
                do{
                    affichage.choixBateauTireur(army1)  ;
                    if (clavier.hasNext()) {
                        //clavier.nextLine();
                        pos = clavier.nextLine();
                    } else;
                    System.out.println("entrée ok = "+pos);
                    entreeCorrecte = game.tire(army1,pos,portee) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);  // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
                System.out.println("");//affichage.portee(portee); //print la portee
                entreeCorrecte = false ;
                pos = "";
                
                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                    affichage.choixBateauDeplacement(army1);
                    //clavier.nextLine();
                    pos = clavier.nextLine();
                    entreeCorrecte = game.choixBateauDeplacement(army1,pos) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);
                entreeCorrecte = false ;
                String newPos = "";               
                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                    affichage.choixCaseDeplacement();
                    newPos = clavier.nextLine();
                    entreeCorrecte = game.deplacebateau(army1,pos,newPos) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);
                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
                //appliquer le déplacement(maj board et reprint auto                
                Joueur=false ; // On donne la main à l'autre joueur   
            
            }else{      //si c'est au tour du joueur 2
                Boolean entreeCorrecte = false ;
                String pos = "" ;
                int portee = 0;
                do{
                    affichage.choixBateauTireur(army2)  ;
                    if (clavier.hasNext()) {
                        //clavier.nextLine();
                        pos = clavier.nextLine();
                    } else;
                    System.out.println("entrée ok = "+pos);
                    entreeCorrecte = game.tire(army2,pos,portee) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);  // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
                System.out.println("");//affichage.portee(portee); //print la portee
                entreeCorrecte = false ;
                pos = "";               
                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                    affichage.choixBateauDeplacement(army2);
                    //clavier.nextLine();
                    pos = clavier.nextLine();
                    entreeCorrecte = game.choixBateauDeplacement(army2,pos) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);
                entreeCorrecte = false;
                String newPos = "";
                
                do{         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                    affichage.choixCaseDeplacement();
                    newPos = clavier.nextLine();
                    entreeCorrecte = game.deplacebateau(army2,pos,newPos) ; //verifie la position ,tire et renvoie la portée
                }
                while(!entreeCorrecte);
                //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
                //appliquer le déplacement(maj board et reprint auto                
                Joueur=true ; // On donne la main à l'autre joueur              
                //meme chose que joueur1 à copier 
            }             
        } while (!gameOver); 
        System.out.println("partie finie"); //à deplacer  
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
}
