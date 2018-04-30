package model;

import java.util.ArrayList;
import java.util.Observable;
import view.AffichageSetup;

public class MerBuilder extends Observable {

    private static MerBuilder instance = null;
    private static int cote;
    String joueur1;
    String joueur2;
    private Army army1; //army (nom / arrayList / color) 
    private Army army2;
    private MerBoard board;

    Case[][] portCourant = new Case[3][1];;

    private MerBuilder(String joueur1, String joueur2, int cote) {
        boolean switchBat = AffichageSetup.getCheckBox();

        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.army1 = new Army(joueur1);
        this.army2 = new Army(joueur2);

        this.cote = cote;

        this.board = MerBoard.getInstance(cote);

    }

    public Case[][] returnPort(boolean armee) {
        Army armyCourante = null;
        if (armee) {
            armyCourante = army2;

        } else {
            armyCourante = army1;

        }

        int x = 0, y = 0;
        for (Navire n : armyCourante.getListeNavire()) {
            System.out.println("");
            Position pos = new Position(x, y); //pos prends x = random contenu dans un carré de cote * cote
            n.setPosition(pos);
            portCourant[x][y] = new Case(armyCourante.getNom(), pos);
            portCourant[x][y].setNavire(n);
            ++x;
            

        }

        return portCourant;

    }


    public static MerBuilder getInstance(String joueur1, String joueur2, int cote) {
        if (instance == null) {
            instance = new MerBuilder(joueur1, joueur2, cote);

        }
        return instance;
    }

    public String getNomJoueur1() {
        return this.joueur1;
    }

    public String getNomJoueur2() {
        return this.joueur2;
    }

    public Army getJoueur1() {
        return army1;
    }

    public Army getJoueur2() {
        return army2;
    }

    public MerBoard getBoard() {
        return this.board;
    }

    public void ajouterBateau() {
        //drag and drop des bateau vers le builder et association de leurs pos 

    }

    public void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }

    public boolean choixBateauPressed(String armyCourante, Position positionClicked) {
        if (armyCourante == joueur1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deplaceBateauReleaseGrid(String armyCourante, Position positionClicked) {
        Case c = board.getCaseInPos(positionClicked);
        Position future = c.getPosition();
        if (c.choixPossible == true) {
            Navire n = c.getNavire();
            n.setPosition(future); //donne la postion au bateau
            n.setPopo(c.getName());
            setChangedAndNotify();
            return true;
        } else {
            return false;
        }
    }

} 
