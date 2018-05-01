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

    Case[][] port1 = new Case[3][1];
    Case[][] port2 = new Case[3][1];

    private MerBuilder(String joueur1, String joueur2, int cote) {
        boolean switchBat = AffichageSetup.getCheckBox();

        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.army1 = new Army(joueur1);
        this.army2 = new Army(joueur2);

        this.cote = cote;

        this.board = MerBoard.getInstance(cote);

        this.port1 = initPort(true);
        this.port2 = initPort(false);
    }

    public Army getArmy(Boolean Army){
        if(Army){
            return army1 ;
        }else{
            return army2 ;
        }
    }
    public Case[][] returnPort(boolean armee) {

        if (armee) {
            return this.port1;

        } else {
            return this.port2;

        }
    }
    
    

    public Case[][] initPort(boolean armee) {
        Army armyCourante = null;
        if (armee) {
            armyCourante = army1;

        } else {
            armyCourante = army2;

        }
        Case[][] port = new Case[3][1];
        int x = 0, y = 0;
        for (Navire n : armyCourante.getListeNavire()) {
            System.out.println("");
            Position pos = new Position(x, y); //pos prends x = random contenu dans un carré de cote * cote
            n.setPosition(pos);
            port[x][y] = new Case(armyCourante.getNom(), pos);
            port[x][y].setNavire(n);
            ++x;

        }

        return port;

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

    

    public void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }

    public boolean choixBateauPressed(String armyCourante, Position positionClicked, boolean joueur) {
        Navire nav = null ;
        if (joueur) {
            armyCourante = army1.getNom();

        } else {
            armyCourante = army2.getNom();

        }
        Case c = this.getCaseInPos(positionClicked, joueur);
        if (c.estNavire()) { //Regarde si la case est un Navire
            nav = c.getNavire(); //Si oui, on conserve cette donnée 
        } else {
            return false; // aussinon on sort de la boucle
        }
        if(armyCourante == nav.getNom()){
            return true ;
        }
        else{
            return false ;
        }
        
        
        
    }

    Case getCaseInPos(Position p, Boolean joueur) {
        int x = p.getX();
        int y = p.getY();
        if (joueur) {
            return port1[x][y];
        } else {
            return port2[x][y];
        }

    }

    public boolean deplaceBateauReleaseGrid(Position oldPos, Position positionClicked,Boolean joueur) {
        Case c = this.getCaseInPos(oldPos,joueur);
        Case f = board.getCaseInPos(positionClicked);
        Navire n = c.getNavire();
        c.supprimerNavire();
        if(f.estVide()){
            f.setNavire(n);
            n.setPosition(positionClicked); //donne la postion au bateau
            n.setPopo(f.getName());
            setChangedAndNotify();
            return true ;
        }
        return false ;
        
        
        
    }

}
