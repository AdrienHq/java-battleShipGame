package model;

//import java.util.List;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import view.AffichageConsole;

public class MerBoard {

    private static final int cote = 5;
    private Case[][] merBoard;
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;
    private Random random = new Random();

    MerBoard() {

    }

    private MerBoard(String joueur1, String joueur2) {

        this.merBoard = new Case[cote][cote];

        for (int y = 0; y < cote; y++) {
            for (int x = 0; x < cote; x++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                merBoard[x][y] = new Case(name);

            }
        }
        this.joueur1 = new Army(joueur1);
        this.joueur2 = new Army(joueur2);

    }

    public void init() {
        this.initialiserBateau(getJoueur1());
        this.initialiserBateau(getJoueur2());
    }

    private void initialiserBateau(Army army) {
        List<Navire> delete = new ArrayList<>();
        List<Navire> add = new ArrayList<>();

        for (Navire n : army.getNavires()) { //A ECRIRE ENCORE (getNavire)
            boolean valide = false;
            Position pos = null;
            while (!valide) {
                pos = new Position(random.nextInt(this.getCote()), random.nextInt(this.getCote())); //getTaille a écrire
                valide = this.initialiserBateau(n, pos);
            }
            delete.add(n);
            n.setPosition(pos);
            add.add(n);
        }
        army.getNavires().rajouterTout(delete);
        army.getNavires().ajouterTout(add);
    }

    private boolean initialiserBateau(Navire n, Position pos) {
        if (n == null || pos == null) {

        } else {

        }

    }

    public static int getCote() {
        return cote;
    }

    public String getNomJoueur1() {
        return this.joueur1.nom;
    }

    public String getNomJoueur2() {
        return this.joueur2.nom;
    }

    public Army getJoueur1() {
        return joueur1;
    }

    public Army getJoueur2() {
        return joueur2;
    }

    private static MerBoard instance = null;

    public static MerBoard getInstance() {
        if (instance == null) {
            instance = new MerBoard();
            instance.initMer();
        }
        return instance;
    }

    private void initMer() { //Création et initialisation du tableau de case.
        Case[][] merBoard = new Case[cote][cote];

        for (int y = 0; y < cote; y++) {
            for (int x = 0; x < cote; x++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                merBoard[x][y] = new Case(name);
                //random x et y 
                // case random  
                //new navire
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
            c = merBoard[x][y]; //donne à la case ces coordonnées // bateaGrand = c 
            if (!c.estVide) { //si la case voulue n'est pas vide, return null
                return null;
            }
        }
        return c; //return null 
    }

    private boolean posValide(int x, int y) {
        return (x >= 0 && x < LIGNE) && (y >= 0 && y < COLONNE);
    }

    public void retirerNavire(Case c) {

    }

    public Case getPositionCaseElement() {
        int x = 0;
        int y = 0;
        int n = 0;

        for (int i = 0; i < initTableau.length; i++) {
            for (int j = 0; j < initTableau[0].length; j++) {
                if (initTableau[i][j] == 3) {
                    if (n == id) {
                        x = i;
                        y = j;
                        n++;
                    } else if (n < id) {
                        n++;
                    }
                }
            }
        }
        return new Position(x, y);
    }

    public Navire getNavire(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Case getPosCase(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
//    public static MerBoard getInstance() { //return une instance de notre mer
//        if (instance == null) {
//            instance = new MerBoard();
//            instance.initMer();
//        }
//        return instance;
//    }
//
//    private void initMer() {  //initialise la mer avec les paramètre voulu 
//        int nbreJoueur = 2;
//        merCase = new Case[LIGNE][COLONNE];
//
//        //place les bateaux
//        for (int player = 1; player < nbreJoueur; ++player) {
//            filBoats(1);
//        }
//        filMines();
//    }
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

