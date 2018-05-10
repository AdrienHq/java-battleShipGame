package controller;

import java.util.Scanner;
import model.Army;
import model.Game;
import view.AffichageConsole;

public class ControllerConsole {

    public static void main(String[] args) {
        AffichageConsole affichage = new AffichageConsole();
        boolean gameOver = false;
        String army1 = null, army2 = null, armyCourante = null;
        int cote = 5;
        //===========================================================================SETUP==============================================================================
        Scanner clavier = new Scanner(System.in);
        affichage.askName1();
        if (clavier.hasNext()) {
            army1 = clavier.nextLine();

        } else;     
        affichage.askName2();
        if (clavier.hasNext()) {
            army2 = clavier.nextLine();

        } else ;
        do {
            affichage.askTailleCote();
            while (!clavier.hasNextInt()) {
                String input = clavier.next();
                System.out.printf("\"%s\" n'est pas une taille de côté valide !\n", input);
                affichage.askTailleCote();
            }
            cote = clavier.nextInt();
        } while (cote < 3);
        Game game = Game.setGame(army1, army2, cote);
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage);//Ajoute l'observer 
        //===========================================================================JOUER==============================================================================
        Boolean Joueur = true;
        clavier.nextLine();
        do {
            if (Joueur) { //si c'est au tour du joueur 1
                armyCourante = army1;
            } else {
                armyCourante = army2;
            }
            Boolean entreeCorrecte = false;
            String pos = "";
            int portee = 4;
            do {
                affichage.choixBateauTireur(armyCourante);
                if (clavier.hasNext()) {
                    pos = clavier.nextLine();
                } else;
                portee = game.tire(armyCourante, pos); //verifie la position ,tire et renvoie la portée
                if (portee >= 0 && portee < 4) {
                    System.out.println("Portée = " + portee);
                }
            } while (portee == 4);  // tant que choix invalide demander la saisie du bateau ( String nom de la case ) .
            System.out.println("");//affichage.portee(portee); //print la portee
            entreeCorrecte = false;
            pos = "";

            do {         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                affichage.choixBateauDeplacement(armyCourante);
                pos = clavier.nextLine();
                entreeCorrecte = game.choixBateauDeplacement(armyCourante, pos); //verifie la position ,tire et renvoie la portée
            } while (!entreeCorrecte);
            entreeCorrecte = false;
            String newPos = "";
            do {         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                affichage.choixCaseDeplacement();
                newPos = clavier.nextLine();
                entreeCorrecte = game.deplacebateau(armyCourante, pos, newPos); //verifie la position ,tire et renvoie la portée
            } while (!entreeCorrecte);
            //tant que choix invalide demander   la saisie bateau( String nom de la case ) 
            //appliquer le déplacement(maj board et reprint auto
            if (Joueur) {
                Joueur = false;
            } else {
                Joueur = true; // On donne la main à l'autre joueur   
            }
        } while (!gameOver);
        System.out.println("Partie finie"); //à deplacer 
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
