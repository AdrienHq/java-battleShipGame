package model;

import static java.lang.Math.random;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {

    private Random rand = new Random();
    private MerBoard board = new MerBoard();

    public MerBoard getBoard() {
        return this.board;
    }

    public Navire getNavire(Position pos) { //Récupère le navire à une position précise 
        return board.getNavire(pos);
    }

    public boolean randomTour() { //Outil pour avoir un rand true ou false
        return rand.nextBoolean();
    }

    private boolean joueurTir(Position pos, Army armeeTir, Army armeeDegat) {
        Case c = board.getPosCase(pos);
        Navire nav = null;
        if (c.estNavire()) { //Regarde si la case est un Navire
            nav = c.getNavire(); //Si oui, on conserve cette donnée 
        }else{
            return false; // aussinon on sort de la boucle
        }
        if()

    }

    public boolean tirJoueur1(Position pos) {
        if (this.joueurTir(pos, board.getJoueur1(), board.getJoueur2())) {
            this.setChangedAndNotify(board);
            return true;
        }
        return false;
    }

    public boolean tirJoueur2(Position pos) {
        if (this.joueurTir(pos, board.getJoueur2(), board.getJoueur1())) {
            this.setChangedAndNotify(board);
            return true;
        }
        return false;
    }

    private void setChangedAndNotify(MerBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
