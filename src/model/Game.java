package model;

import java.util.Observable;

public class Game extends Observable{
    
    private static final char[][] merAffichage = {
        {x, x, 'A', x, 'B', x, 'C', x, 'D', x, 'E'},
        {'1', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|'},
        {'2', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|'},
        {'3', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|'},
        {'4', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|'},
        {'5', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|', x, '|'},};


    public static Game getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean jouer(Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean joueurAPerdu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
