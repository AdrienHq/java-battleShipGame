package model;

import java.util.List;
import java.util.Random;

public class MerBoard {

    private final static int[][] tabMer = { 
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public static final int LIGNE = 5;
    public static final int COLONNE = 5;
    private static Case[][] merCase = null;
    private static List<Army> listArmy = null; //Pas sûr dans avoir besoin 
    private static final int nbNavire = 0;

    private static MerBoard instance = null;

    public static MerBoard getInstance() { //return une instance de notre mer
        if (instance == null) {
            instance = new MerBoard();
            instance.initMer();
        }
        return instance;
    }

    public int getNbNavire() {
        return nbNavire;
    }

    public boolean zeroNavire() {
        return nbNavire == 0;
    }

    private void initMer() {  //initialise la mer avec les paramètre voulu 
        int BateauGrand = 0; //pour deux joueurs 2 bateaux grands 
        int bateauPetit = 0; // et 4 bateaux petits
        int id = 1 ;
        int id2 = 2;
        merCase = new Case[LIGNE][COLONNE];
        
        //place les bateaux 
        
        //RAndom 
                merCase[x][y] = new Element(new BateauGrand(id));
                        merCase[x][y] = new Element(new BateauPetit(id));
        
        
        
        for (int x = 0; x < LIGNE; x++) {
            for (int y = 0; y < COLONNE; y++) {
                
                //compteur pour bateau
                //random sur case qui donne une valeur égale à un objet 
                //Bateaux avant bombes
                //switch - case 
                //IdJoeurs A FAIRE DANS NAVIRE 
                
                
                
                
                
                
                while (BateauGrand < 1) {
                    if(case.caseVide())
                    new merCase[][]               
                }
                while(BateauPetit < 2){
                    
                }
                //then random de 10% pour les mines.

                switch (tabMer[x][y]) {
                    case 0:
                        
                        merCase[x][y] = new Element(new MineAtomique());
                        merCase[x][y] = new Element(new MineNormale());

                }
            }
        }
    }

    public Position getPositionAleatoire(Position elementPos) { //Obtient un position aléatoire sur le tableau pou le placement des elements
        int min = 1;
        int max = merCase[0].length - 1;
        int x, y;
        Random rand = new Random();
        x = rand.nextInt((max + 1) - min) + min;
        y = rand.nextInt((max + 1) - min) + min;
        Position pos;
        pos = new Position(x, y);
        return pos;
    }

}
