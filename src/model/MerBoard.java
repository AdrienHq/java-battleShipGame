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
    //random avec compteur 
    // 0.0 = 1 
    // 5.3 = 1 // compteur bateauGrand est au max 

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
        int nbreJoueur = 2;
        merCase = new Case[LIGNE][COLONNE];

        //place les bateaux
        for (int player = 1; player < nbreJoueur; ++player) {
            filBoats(1);
        }
        filMines();
    }

    private void filBoats(int id) {
        filBoat(id, TypeNavire.PETIT);
        filBoat(id, TypeNavire.GRAND);
        filBoat(id, TypeNavire.PETIT);
    }

    private void filBoat(int id, TypeNavire type) {
        Position pos = this.getPositionAleatoire();
        int x = pos.getX();
        int y = pos.getY();
        if (merCase[x][y].estVide()) { //estVide
            if (type == TypeNavire.GRAND) {
                merCase[x][y] = new Element(new BateauGrand(id));
            } else if (type == TypeNavire.PETIT) {
                merCase[x][y] = new Element(new BateauPetit(id));
            }
            merCase[x][y].switchVide();
        } else {
            this.filBoat(id, type);
        }
    }

    private void filMines() {
        for (int x = 0; x < LIGNE; x++) {
            for (int y = 0; y < COLONNE; y++) {
                if (merCase[x][y].estVide()) {
                    Random rand = new Random();
                    if (rand.nextInt(10) - 1 == 5) {
                        if (rand.nextInt(1) == 1) {
                            merCase[x][y] = new Element(new MineAtomique());
                        } else {
                            merCase[x][y] = new Element(new MineNormale());
                        }
                        merCase[x][y].switchVide();
                    }
                }
            }
        }
    }

    public Position getPositionAleatoire() { //Position elementPos Obtient un position aléatoire sur le tableau pou le placement des elements
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

    public Case deplacementPossible(Direction d, Position p) { // verifie que la nouvelle position est "viable"
        Case c = null;
        int x, y;
        x = p.getX();
        y = p.getY();
        Position p2 = new Position(x, y);
        p2.déplacer(d);
        x = p2.getX();
        y = p2.getY();
        if (posValide(x, y)) {
            c = merCase[x][y];
            if (!c.estVide()) {
                return null;
            }
        }
        return c;
    }

    private boolean posValide(int x, int y) {
        return (y >= 0 && y < COLONNE) && (x >= 0 && x < LIGNE); //vérifie que la nouvelle position est VALIDE
    }

    public char getCase(int previousX, int previousY) { //Prends une case et en fonction de son contenu, affiche un caractère spécifique.
        if (merCase[previousX][previousY] instanceof Element) {
            if (((Element) merCase[previousX][previousY]).getNavire() instanceof BateauGrand) {
                return 'B';
            } else if (((Element) merCase[previousX][previousY]).getNavire() instanceof BateauPetit) {
                return 'S';
            } else if (((Element) merCase[previousX][previousY]).getFlottant() instanceof MineAtomique) {
                return 'M';
            } else if (((Element) merCase[previousX][previousY]).getFlottant() instanceof MineNormale) {
                return 'm';
            }
        }
        return ' ';
    }

}
