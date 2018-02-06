package controller;

import java.util.Scanner;

public class Main {

    public static int modeDeJeu = 0;

    public static void main(String[] args) {
        char c = ' ';

        System.out.println("");
        System.out.println("Que voulez vous faire ? Tapez le chiffre affiché !");
        System.out.println("Jouer au mode console => 1");
        System.out.println("Jouer au mode graphique => 2");
        System.out.println("Quitter => 0");
        System.out.println("");

        try {
            Scanner clavier = new Scanner(System.in);
            if (clavier.hasNext()) {
                c = clavier.nextLine().charAt(0);
                //Caractère touver à la position 0, première position de la ligne d'écriture.
            }
        } catch (Exception e) {
            System.out.println("Error");
            //créer une classe d'exception ?           
        }

        while (c != '1' && c != '2' && c != '0') {
            System.out.println("");
            System.out.println("Choix invalide !");
            System.out.println("Choisissez une option valide ( 1 - 2 - 0");
            System.out.println("");

            try {
                Scanner clavier = new Scanner(System.in);
                if (clavier.hasNext()) {
                    c = clavier.nextLine().charAt(0);
                }
            } catch (Exception e) {
                System.out.println("Error");     
            }
        }

        switch (c) {
            case '0':
                ControllerConsole.main(args);
            case '1':
                ControllerGraphique.main(args);
            case '2':
                System.exit(0);
                //Quitte ke programme
                break;
        }
    }

}
