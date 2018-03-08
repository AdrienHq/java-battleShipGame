package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MerBoard {

    private static int cote ;

    public static int getCote() {
        return cote; //To change body of generated methods, choose Tools | Templates.
    }

    public static void setCote(int cote) {
        MerBoard.cote = cote;
    }

    private Case[][] mer;
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;
    private Random random = new Random();
    private static MerBoard instance = null;

    private MerBoard(int cote) {
        this.cote = cote;
        this.mer = new Case[cote][cote];
        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (x + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(y + 1); //String du nom de la case (exemple : B1)
                mer[x][y] = new Case(name);
            }
        }
    }

    public Case[][] getTab() {
        return mer;
    }

    public boolean positionVide(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (mer[x][y].estVide()) {
            return true;
        }
        return false;
    }

    void placerNavire(Position pos, Navire n) {
        int x = pos.getX();
        int y = pos.getY();
        mer[x][y].setNavire(n);
        char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
        String popo = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
        n.setPopo(popo);
    }

    void placerFlottant(Position pos, Flottant f) {
        int x = pos.getX();
        int y = pos.getY();
        mer[x][y].setFlottant(f);
    }

    public static MerBoard getInstance(int cote) {
        if (instance == null) {
            instance = new MerBoard(cote);
            instance.initMer();
        }
        return instance;
    }

    private void initMer() { //Création et initialisation du tableau de case.
        mer = new Case[cote][cote];

        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                mer[x][y] = new Case(name);
            }
        }
    }

    private boolean posValide(int x, int y) {
        return (x >= 0 && x < cote) && (y >= 0 && y < cote);
    }

    public void supprimerNavire(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        mer[x][y].supprimerNavire();
    }

    public Navire getNavire(Position pos) {
        int x1 = pos.getX();
        int y1 = pos.getY();

        Navire n = mer[x1][y1].getNavire();

        return n;
    }

    Case getCaseInPos(Position p) {
        int x = p.getX();
        int y = p.getY();
        return mer[x][y] ;
    }
    
    Case getCaseInPos(String pos) {
        int x = getNumberFromAZ(pos.charAt(0)) ;
        int y = pos.charAt(1) ;
        return mer[x][y] ;
        
    }
    void tirDegat(Position p) {
        int x = p.getX();
        int y = p.getY();
        mer[x][y].tirDegat();
    }


    void getRealPosition(Position p) {
        int x = p.getX();
        int y = p.getY();
        if(x>cote){
            p.setX(x-cote);
        }
        else if(y>cote){
            p.setY(y-cote);
        }
        
    }

    
    private int getNumberFromAZ(char charAt) {
        return (int)(charAt-65) ;
    }

    

    
    
}
