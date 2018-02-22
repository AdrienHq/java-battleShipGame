package model;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    public static final int LIGNE = 5;
    public static final int COLONNE = 5;
    private static Case[][] merBoard = null;
    private Army army1 = null; //army (nom / arrayList / color)
    private Army army2 = null; //army (nom / arrayList / color) 
//    private ArrayList<Navire> listNavire;
//    private static Navire[] typeBateau = {BIG, SMALL}; 

    private static Game instance = null;
    private Game (String army1,String army2){
        this.army1 = new Army(army1,RED);
        this.army2 = new Army (army2,BLUE);
    }

    public static Game getInstance(String army1,String army2) {
        if (instance == null) {
            instance = new Game(army1,army2);
            instance.initMer();
        }
        return instance;
    }

    private void initMer() { //Création et initialisation du tableau de case.
        Case[][] merBoard = new Case[COLONNE][LIGNE];
        
        for (int y = 0; y < COLONNE; y++) {
            for (int x = 0; x < LIGNE; x++) {
                char Col = (char)(y+65) ;           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x+1) ; //String du nom de la case (exemple : B1)
                merBoard[x][y] = new Case(name);
                //random x et y 
                // case random  
                //new navire
            }
        }
    }
   }
