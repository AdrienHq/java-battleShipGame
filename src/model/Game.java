package model;

import java.util.ArrayList;
import java.util.Observable;
import static model.MerBoard.COLONNE;
import static model.MerBoard.LIGNE;

public class Game extends Observable {

    private static final char[][] merAffichage = {
        {'x', 'x', 'A', 'x', 'B', 'x', 'C', 'x', 'D', 'x', 'E'},
        {'1', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
        {'2', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
        {'3', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
        {'4', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
        {'5', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},};

//    public final static MerBoard merBoard = MerBoard.getInstance();

    private static Position positionPrec;
    private static Case caseActuel;
    private static Game instance;
    private static int nbBateauVivant = 6;
    private static final ArrayList<Navire> listeNavire = new ArrayList<>();
    private static final ArrayList<Flottant> listeFlottant = new ArrayList<>();

    private Game() {
        initialiserNavire();
        initialiserFlottant();
//        placerNavire();
//        placerFlottant();
    }

    public static Game getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean jouer(Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean joueurAPerdu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initialiserNavire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initialiserFlottant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public void placerNavire() {
//        placerNavires();
//    }
//    
//    public void placerFlottant() {
//        placerFlottants();
//    }

//    public void placerNavires() {
//        for (Navire n : listeNavire) {
//            if (n.getType() == TypeNavire.GRAND) {
//                merAffichage[n.getXPrec()][n.getYPrec()] = merBoard.getCase(n.getXPrec(), n.getYPrec());
//                merAffichage[n.getX()][n.getY()] = 'B';
//            } else {
//                merAffichage[n.getXPrec()][n.getYPrec()] = merBoard.getCase(n.getXPrec(), n.getYPrec());
//                merAffichage[n.getX()][n.getY()] = 'S';
//            }
//        }
//    }
//
//    private void placerFlottants() {
//        for (Flottant f : listeFlottant) {
//            if (f.getType() == TypeFlottant.NORMALE) {
//                merAffichage[f.getXPrec()][f.getYPrec()] = merBoard.getCase(f.getXPrec(), f.getYPrec());
//                merAffichage[f.getX()][f.getY()] = 'm';
//            } else {
//                merAffichage[f.getXPrec()][f.getYPrec()] = merBoard.getCase(f.getXPrec(), f.getYPrec());
//                merAffichage[f.getX()][f.getY()] = 'M';
//            }
//        }       
//    }
//    
//    private void initialiserNavire () {
//        for (int x = 0; x < LIGNE; x++) {
//            for (int y = 0; y < COLONNE; y++) {
//                if (merCase[x][y].estVide())
//        
//
   }
