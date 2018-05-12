package controller;

import java.util.Scanner;
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
            cote = Integer.parseInt(getInput(clavier, "Rentrez une taille de côté: "));
        } while (cote < 3);
        Game game = Game.setGame(army1, army2, cote);
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage);//Ajoute l'observer 
        //===========================================================================JOUER==============================================================================
        Boolean Joueur = true;
        do {
            if (Joueur) { //si c'est au tour du joueur 1
                armyCourante = army1;
            } else {
                armyCourante = army2;
            }
            Boolean entreeCorrecte = false;
            int portee = 4;
            String pos = "";
            affichage.afficherTexteConsole(armyCourante + ", à vous de tirer.");
            do {
                pos = getString(clavier, "Selectionnez la position du bateau tireur([A-Z][1-26]) : ");
                portee = game.tire(armyCourante, pos); //verifie la position ,tire et renvoie la portée
                if (portee >= 0 && portee < 4) {
                    System.out.println("Portée = " + portee);
                }
            } while (portee == 4);
            System.out.println("");

            entreeCorrecte = false;
            pos = "";
            do {         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                affichage.afficherTexteConsole(armyCourante + ", sélectionnez le bateau à déplacer ([A-Z][1-26]) : ");
                pos = getString(clavier, "Selectionnez la position du bateau à déplacer([A-Z][1-26]) : ");
                entreeCorrecte = game.choixBateauDeplacement(armyCourante, pos); //verifie la position ,tire et renvoie la portée
            } while (!entreeCorrecte);
            entreeCorrecte = false;
            String newPos = "";
            do {         //demande l'entrée de choix du bateau tant que l'entrée est invalide
                newPos = getString(clavier, "Choisissez la case où vous voulez vous déplacer (saisir la case actuelle pour y rester) : ");
                entreeCorrecte = game.deplacebateau(armyCourante, pos, newPos); //verifie la position ,tire et renvoie la portée
            } while (!entreeCorrecte);
            if (Joueur) {
                Joueur = false;
            } else {
                Joueur = true; // On donne la main à l'autre joueur   
            }
        } while (!gameOver);
        System.out.println("Partie finie"); //à deplacer 
    }

    public static String getInput(Scanner clavier, String textAnnonce) {
        System.out.print(textAnnonce); //Demande l'int
        String cote = "";
        while (true) { // continue de bouclé tant que ce n'est pas un int
            cote = clavier.nextLine(); // prends l'input dans l'attribut
            if (isInteger(cote)) // regarde si c'est un int
            {
                break;
            }
            System.out.print("Taille de côté non valide !  " + textAnnonce); // Non valide, redemande
        }
        return cote; // Return la valeur
    }

    public static String getString(Scanner clavier, String textAnnonce) {
        System.out.print(textAnnonce); //Demande l'int
        String pos = "";
        while (true) { // continue de bouclé tant que ce n'est pas un int
            pos = clavier.nextLine(); // prends l'input dans l'attribut
            if (pos.length() > 1) {
                if (pos.substring(0, 1).matches("[a-zA-Z]") && pos.substring(1, 2).matches("\\d")) {
                    break;
                }
                System.out.println("Seulement 1 lettre et un nombre SVP");
            }
            System.out.print("Position non valide !  " + textAnnonce); // Non valide, redemande
        }
        return pos; // Return la valeur
    }

    private static boolean isInteger(String str) { // Check si c'est un integer ou non
        try {
            Integer.parseInt(str); // Si ça passe, c'est un integer
            return true;
        } catch (NumberFormatException e) {
            return false; // N'est pas un integer
        }
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
