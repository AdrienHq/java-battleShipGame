package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {

    private Random rand = new Random();
    private int cote ;
    private MerBoard board ;
    private static Game instance = null;
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;
    private Random random = new Random();
    private static boolean gameOver = false;
    List<Navire> bateauEnMer = new ArrayList<>();

    private Game(String joueur1, String joueur2,int cote) {

        this.joueur1 = new Army(joueur1);
        this.joueur2 = new Army(joueur2);
        this.cote = cote ;    
        this.board = MerBoard.getInstance(cote);
        
        initialiserBateaux(getJoueur1());
        initialiserBateaux(getJoueur2());
        placementFlottants();

    }

    public static Game setGame(String joueur1, String joueur2,int cote) {
        if (instance == null) {
            instance = new Game(joueur1, joueur2,cote);
        }
        return instance;
    }

    public static Game getInstance() {
        return instance;
    }

    public MerBoard getBoard() {
        return this.board;
    }

    private void initialiserBateaux(Army army) { //Donne Armee jouee 1 donc 3 bateaux
        //rajouter bateau à notre merboard et une list qui contient l'ensemble des bateaux
        Position pos = null;

        for (Navire n : army.getListeNavire()) {
            pos = new Position(random.nextInt(this.getCote()), random.nextInt(this.getCote())); //pos prends x = random contenu dans un carré de cote * cote
            do {
                pos = new Position(random.nextInt(this.getCote()), random.nextInt(this.getCote())); //pos prends x = random contenu dans un carré de cote * cote
            } while (!board.positionVide(pos));

            board.placerNavire(pos, n);//place le navire (pos )          
            //enregistre la position comme prise ;          
        }
    }

    private void placementFlottants() {
        Position pos = null;
        for (int x = 0; x < cote; x++) {
            for (int y = 0; x < cote; x++) {
                pos = new Position(x, y);
                if (board.positionVide(pos) && random.nextInt(9) == 5) { //si positionestprise(
                    if (random.nextInt(1) == 0) {
                        board.placerFlottant(pos, new MineAtomique());
                    } else {
                        board.placerFlottant(pos, new MineAtomique());
                    }
                }
            }
        }
    }

    private boolean navireEncore(Navire n, Position pos) { //Verifie si la pos est valide KINDA
        if (n == null || pos == null) {
            return true;
        }
        return false;
    }
    
    public void setCote(int cote){
        this.cote = cote;
    }

    public int getCote() {
        return cote;
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
        } else {
            return false; // aussinon on sort de la boucle
        }
        if (armeeTir.estAmi(nav)) {
            this.cercleDeDegat(armeeTir, armeeDegat, nav, nav.getPorteeTir());
            return true;
        }
        return false;
    }

    public boolean jouer(Position pos) {
        if (pos != null) {
            //faire le déplacement

            //Il faut une réinitilisation du plateau de jeu
            if (joueur1.getListeNavire().isEmpty() || joueur2.getListeNavire().isEmpty()) {
                gameOver = true;
            }
            setChanged();
            notifyObservers();
        }
        return gameOver;
    }

//    public boolean tirJoueur1(Position pos) {
//        if (this.joueurTir(pos, board.getJoueur1(), board.getJoueur2())) {
//            this.setChangedAndNotify(board);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean tirJoueur2(Position pos) {
//        if (this.joueurTir(pos, board.getJoueur2(), board.getJoueur1())) {
//            this.setChangedAndNotify(board);
//            return true;
//        }
//        return false;
//    }
    private void cercleDeDegat(Army army, Army armeeDegat, Navire amis, int portee) {
        Position pos = amis.getPosition();
        for (int i = -portee; i <= portee; i++) {
            for (int j = -portee; j <= portee; j++) {
                Position p = new Position(pos.getX() + i, pos.getY() + j);
                board.estCirulaire(p);
                Case c = board.getPosCase(pos);
                if (c.estNavire() && !army.estAmi(c.getNavire())) {
                    Navire ennemy = c.getNavire();
                    amis.tirDegat(ennemy);
                    if (ennemy.getPointVie() == 0) {
                        armeeDegat.deleteNavire(ennemy);
                        c.supprimerNavire();
                    }
                }
            }
        }
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

    private void setChangedAndNotify(MerBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
