package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {

    private Random rand = new Random();
    private int cote;
    private MerBoard board;
    private static Game instance = null;
    private Army joueur1; //army (nom / arrayList / color) 
    private Army joueur2;
    private Random random = new Random();
    private static boolean gameOver = false;
    List<Navire> bateauEnMer = new ArrayList<>();
    public ArrayList<Position> listPositionPossible = new ArrayList<>();
    private Game(String joueur1, String joueur2, int cote) {

        this.joueur1 = new Army(joueur1);
        this.joueur2 = new Army(joueur2);
        this.cote = cote;
        this.board = MerBoard.getInstance(cote);

        initialiserBateaux(getJoueur1());
        initialiserBateaux(getJoueur2());
        placementFlottants();

    }

    public static Game setGame(String joueur1, String joueur2, int cote) {
        if (instance == null) {
            instance = new Game(joueur1, joueur2, cote);
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
        Position pos = null;

        for (Navire n : army.getListeNavire()) {
            pos = new Position(random.nextInt(this.getCote()), random.nextInt(this.getCote())); //pos prends x = random contenu dans un carré de cote * cote
            do {
                pos = new Position(random.nextInt(this.getCote()), random.nextInt(this.getCote())); //pos prends x = random contenu dans un carré de cote * cote
            } while (!board.positionVide(pos));
            n.setPosition(pos); // lui attribue sa position .
            board.placerNavire(pos, n);//place le navire (pos ) et enregistre la position comme prise ;          

        }
    }

    private void placementFlottants() {
        Position pos = null;
        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                pos = new Position(x, y);
                if (board.positionVide(pos) && Math.random()*100< 10) { //si positionestprise et 1chance sur 10
                    if (Math.random()*100<= 50) {
                        board.placerFlottant(pos, new MineAtomique());
                    } else  {
                        board.placerFlottant(pos, new MineNormale());
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

    public void setCote(int cote) {
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
    
    public boolean deplacebateau(String army1, String oldPos,String newPos, int portee) {
        
        Case c = board.getCaseInPos(oldPos);
        Case f = board.getCaseInPos(newPos);
        Position future = f.getPosition();
            if(f.choixPossible == true){
                Navire n =c.getNavire();
                c.supprimerNavire();
                f.setNavire(n);
                n.setPosition(future);
                n.setPopo(f.getName());
                for(Position p : listPositionPossible){
                    Case x = board.getCaseInPos(p);
                    x.switchChoixPossible();
                }
                setChangedAndNotify();
                return true ;
            }else{
                return false;
             }
            
    }
    
    public Boolean choixBateauDeplacement(String army, String pos, int portee) {
        Case c = board.getCaseInPos(pos);
        Navire nav = null;
        int deplacement;
        if (c.estNavire()) { //Regarde si la case est un Navire
            nav = c.getNavire(); //Si oui, on conserve cette donnée 
        } else {
            return false; // aussinon on sort de la boucle
        }
        if (army == joueur1.getNom()) {
            if (joueur1.estAmi(nav)) {
                deplacement = nav.getDeplacementMax();
                listPositionPossible = new ArrayList<>();
                this.getCasePossible(deplacement,nav); //complete la liste des déplacements possible
                //met les case concernée en choixDeplacement = true (créer cette variable)
                //dans l'affichage console ,si case est choixdeplacement print un x orange ) 

                setChangedAndNotify();
                return true;
            }else{
                return false ;
            }
        } else{
               if (joueur2.estAmi(nav)) {
                deplacement = nav.getDeplacementMax();
                listPositionPossible = new ArrayList<>();
                this.getCasePossible(deplacement,nav); //complete la liste des déplacements possible
                //met les case concernée en choixDeplacement = true (créer cette variable)
                //dans l'affichage console ,si case est choixdeplacement print un x orange ) 

                setChangedAndNotify();
                return true;
            }else{
                return false ;
            }
        }
    }

    private void getCasePossible(int deplacement,Navire n) {
        Position pos = n.getPosition();
        for (int i = -deplacement; i <= deplacement; i++) {
            for (int j = -deplacement; j <= deplacement; j++) {
                Position p = new Position(pos.getX() + i, pos.getY() + j);
                
                board.getRealPosition(p); //renvoie la position réelle de la case demandée (mer circulaire)
                
                Case c = board.getCaseInPos(p); //récupere la case 
                if(c.estVide()){
                    System.out.println("position atteignable"+p);//debug à retirer
                    this.listPositionPossible.add(p);
//                    for(Position x : listPositionPossible){
//                        System.out.println(x);
//                    }
                    board.voirChoixDeplacement(p);
                    //c.switchChoixPossible();
                }
            }
        }

        //boucle qui retourne les differents position possible selon la position et le deplacement
        //si case vide et pos valide -> à modifier:case contenant flottant n'est pas vide .
        //->cette case.setchoixDeplacement = true 

    }

    public boolean tire(String army, String pos,int portee) {
        Case c = board.getCaseInPos(pos);
        Navire nav = null;
        
        if (c.estNavire()) { //Regarde si la case est un Navire
            nav = c.getNavire(); //Si oui, on conserve cette donnée 
        
        } else {
            return false; // aussinon on sort de la boucle
        }
        if(army == joueur1.getNom()){
            if (joueur1.estAmi(nav)) {
                portee = nav.getPorteeTir();
                System.out.println("portée = " + portee);
                if(portee != 0){
                    
                    this.degatZone(joueur1,joueur2, nav, portee);
                }
                setChangedAndNotify();
                return true;
            }
        }else{
            if (joueur2.estAmi(nav)) {
                portee = nav.getPorteeTir();
                if(portee != 0){
                    this.degatZone(joueur2,joueur1, nav, portee);
                }
                setChangedAndNotify();
                return true;
            }
        }
        return false;
    }

    private void degatZone(Army joueur,Army adverse ,Navire n, int portee) {
        Position pos = n.getPosition();
        for (int i = -portee; i <= portee; i++) {
            for (int j = -portee; j <= portee; j++) {
                Position p = new Position(pos.getX() + i, pos.getY() + j);
                System.out.println(" position de tire : "+ p);//debug à retirer
                board.getRealPosition(p); //renvoie la position réelle de la case demandée (mer circulaire)
                System.out.println(" vrai position de tire : "+ p);//debug à retirer
                Case c = board.getCaseInPos(p); //récupere la case 
                if (c.estNavire() && adverse.estAmi(c.getNavire())) {//si contient un bateau et qu'il appartien a l'armée enemie
                    System.out.println("position de bateau touchable"+p);//debug à retirer
                    Navire ennemy = c.getNavire();
                    ennemy.tirDegat();
                    
                    if (ennemy.getPointVie() == 0) {
                        
                        board.supprimerNavire(p);
                    }
                }
            }
        }
        
    }
    
    public boolean jouer(Position pos) {
        if (pos != null) {
            //faire le déplacement

            //Il faut une réinitilisation du plateau de jeu
            if (joueur1.getListeNavire().isEmpty() || joueur2.getListeNavire().isEmpty()) {
                gameOver = true;
            }
            setChangedAndNotify();
        }
        return gameOver;
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

    
    public void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }

    

}
