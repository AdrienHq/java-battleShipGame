package model;

import java.util.List;

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

    public boolean plusDeNavire() {
        return nbNavire == 0;
    }

    private void initMer() {                          //initialise la mer avec les paramètre voulu 
        merCase = new Case[LIGNE][COLONNE];
        for (int x = 0; x < LIGNE; x++) {
            for (int y = 0; y < COLONNE; y++) {
                switch (tabMer[x][y]) {
                    case 0:
                        merCase[x][y] = new Element(new BateauGrand());
                        merCase[x][y] = new Element(new BateauPetit());
                        merCase[x][y] = new Element(new MineAtomique());
                        merCase[x][y] = new Element(new MineNormale());

                }
            }
        }
    }

}
