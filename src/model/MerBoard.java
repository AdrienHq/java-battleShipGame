package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MerBoard {

    private static int cote = 5;

    static int getCote() {
        return cote ; //To change body of generated methods, choose Tools | Templates.
    }
    private Case[][] mer;
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;
    private Random random = new Random();
    private static MerBoard instance = null;

    private MerBoard(int cote) {
        this.cote = cote ;
        this.mer = new Case[cote][cote];
        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                mer[x][y] = new Case(name);
            }
        }
        
    }
    
    public Case[][] getTab(){
        
        return mer ;
    }
       
    
    public boolean positionVide(Position pos ){
        int x =pos.getX();
        int y =pos.getY();
        if(mer[x][y].estVide()){
            return true ;
        }
            return false ;
    }
    
    void placerNavire(Position pos,Navire n) {
            int x =pos.getX();
            int y =pos.getY();
            mer[x][y].setNavire(n);
            
    }
    void placerFlottant(Position pos ,Flottant f){
            int x =pos.getX();
            int y =pos.getY();
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

  
    
    public Case caseVide(Direction d, Position p) { //Prend la direction et la position de base pour savoir si la case est DISPO pour un déplacement
        Case c = null; //Une case
        int x, y; //coordonnées
        x = p.getX(); //on prends le X de la pos initiale
        y = p.getY(); //on prends le Y de la pos initiale
        Position p2 = new Position(x, y); //Nouvelle position créée à partir de la pos de base
        p2.déplacer(d); //Appel de la fonction déplacer pour dire que l'on veut bouger dans tel ouy tel direction
        x = p2.getX(); //On redonne les new coordonnées a la pos
        y = p2.getY();
        if (posValide(x, y)) { //vérifie que la position voulue est valide
            c = mer[x][y]; //donne à la case ces coordonnées // bateaGrand = c 
            if (!c.estVide) { //si la case voulue n'est pas vide, return null
                return null;
            }
        }
        return c; //return null 
    }

    private boolean posValide(int x, int y) {
        return (x >= 0 && x < cote) && (y >= 0 && y < cote);
    }

    public void retirerNavire(Case c) {

    }

//    public Case getPositionCaseElement() {
//        int x = 0;
//        int y = 0;
//        int n = 0;
//
//        for (int i = 0; i < initTableau.length; i++) {
//            for (int j = 0; j < initTableau[0].length; j++) {
//                if (initTableau[i][j] == 3) {
//                    if (n == id) {
//                        x = i;
//                        y = j;
//                        n++;
//                    } else if (n < id) {
//                        n++;
//                    }
//                }
//            }
//        }
//        return new Position(x, y);
//    }

    public Navire getNavire(Position pos) {
        int x1 = pos.getX();
        int y1 = pos.getY();
        
        Navire n = mer[x1][y1].getNavire();
        
       return n ;
        
   }

    Case getPosCase(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void estCirulaire(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
//
//    private void filBoats(int id) {
//        filBoat(id, TypeNavire.PETIT);
//        filBoat(id, TypeNavire.GRAND);
//        filBoat(id, TypeNavire.PETIT);
//    }
//
//    private void filBoat(int id, TypeNavire type) {
//        Position pos = this.getPositionAleatoire();
//        int x =  deplacer(2)                                                         pos.getX();
//        int y = pos.getY();
//        if (merCase[x][y].estVide()) { //estVide
//            if (type == TypeNavire.GRAND) {
//                merCase[x][y] = new Element(new BateauGrand(id));
//            } else if (type == TypeNavire.PETIT) {
//                merCase[x][y] = new Element(new BateauPetit(id));
//            }
//            merCase[x][y].switchVide();
//        } else {
//            this.filBoat(id, type);
//        }
//    }
//
//    private void filMines() {
//        for (int x = 0; x < LIGNE; x++) {
//            for (int y = 0; y < COLONNE; y++) {
//                if (merCase[x][y].estVide()) {
//                    Random rand = new Random();
//                    if (rand.nextInt(10) - 1 == 5) {
//                        if (rand.nextInt(1) == 1) {
//                            merCase[x][y] = new Element(new MineAtomique());
//                        } else {
//                            merCase[x][y] = new Element(new MineNormale());
//                        }
//                        merCase[x][y].switchVide();
//                    }
//                }
//            }
//        }
//    }
//
//    public Position getPositionAleatoire() { //Position elementPos Obtient un position aléatoire sur le tableau pou le placement des elements
//        int min = 1;
//        int max = merCase[0].length - 1;
//        int x, y;
//        Random rand = new Random();
//        x = rand.nextInt((max + 1) - min) + min;
//        y = rand.nextInt((max + 1) - min) + min;
//        Position pos;
//        pos = new Position(x, y);
//        return pos;
//    }

//    public char getCase(int previousX, int previousY) { //Prends une case et en fonction de son contenu, affiche un caractère spécifique.
//        if (merCase[previousX][previousY] instanceof Element) {
//            if (((Element) merCase[previousX][previousY]).getNavire() instanceof BateauGrand) {
//                return 'B';
//            } else if (((Element) merCase[previousX][previousY]).getNavire() instanceof BateauPetit) {
//                return 'S';
//            } else if (((Element) merCase[previousX][previousY]).getFlottant() instanceof MineAtomique) {
//                return 'M';
//            } else if (((Element) merCase[previousX][previousY]).getFlottant() instanceof MineNormale) {
//                return 'm';
//            }
//        }
//        return ' ';
//    }

