package model;

import java.util.ArrayList;
import java.util.Observable;

public class MerBuilder extends Observable {

    private static MerBuilder instance = null;
    private static int cote;
    private Case[][] merBuilder;

    public static MerBuilder getInstance(int cote) {
        if (instance == null) {
            instance = new MerBuilder(cote);
            instance.initMerBuilder();
        }
        return instance;
    }

    private MerBuilder(int cote) {
        this.cote = cote;
        this.merBuilder = new Case[cote][cote];
        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (x + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(y + 1); //String du nom de la case (exemple : B1)
                Position p = new Position(x, y);
                merBuilder[x][y] = new Case(name, p);
            }
        }
    }

    public Case[][] getBuilder() {
        return merBuilder;
    }

    private void initMerBuilder() { //Création et initialisation du tableau de case.
        merBuilder = new Case[cote][cote];

        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                Position p = new Position(x, y);
                merBuilder[x][y] = new Case(name, p);
            }
        }
    }

    public void ajouterBateau() {
        //drag and drop des bateau vers le builder et association de leurs pos 

    }

    public void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;

    public boolean choixBateauPressed(String armyCourante, Position positionClicked) {
        if (armyCourante == joueur1.getNom()) {
            return true;
        }else
            return false;    
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
