package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.*;

public class AffichageConsole implements Observer {

    public AffichageConsole() {

    }

    public void afficherGame() {
        Game game = Game.getInstance();
        MerBoard board = game.getBoard();
        Case[][] mer = board.getTab();
        Case c = null;
        Position pos = null;
        Boolean debug = true;
        int cote = game.getCote();

        printCol(cote);
        for (int x = 0; x < cote; x++) {
            if(cote>= 10 && x < 9){        // Si tableau >= 10colonne alors on décalle les 9premiere colonne pour alligner le tout
                System.out.print(" ");
            }
            System.out.print((x + 1) + " |"); //Affiche l'entête de la ligne (1 2 3 4 5 ...)
            for (int y = 0; y < cote; y++) {

                pos = new Position(x, y);
                c = mer[x][y];

                if (!c.estVide()) {//si contient qqch
                    if (c.getTypeNavire() == "BIG") { //si grand navire 
                        Navire n = c.getNavire();
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            System.out.print(Couleur.RED + " B " + Couleur.RESET + "|");
                        } else {
                            System.out.print(Couleur.BLUE + " B " + Couleur.RESET + "|");
                        }
                    } else if (c.getTypeNavire() == "SMALL") {                  //si petit navire
                        Navire n = c.getNavire();
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            System.out.print(Couleur.RED + " S " + Couleur.RESET + "|");
                        } else {
                            System.out.print(Couleur.BLUE + " S " + Couleur.RESET + "|");
                        }
                    } else if (debug == true) {
                        if (c.getTypeFlottant() == "ATOMIQUE") {
                            System.out.print(Couleur.BLACK + " A " + Couleur.RESET + "|");
                        } else {
                            System.out.print(Couleur.BLACK + " N " + Couleur.RESET + "|");
                        }
                    } else {
                        System.out.print(Couleur.BLACK + "   " + Couleur.RESET + "|");
                    }
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println(" ");
        }
        afficheLegende();
        afficheEtatArmee(game.getJoueur1(), game.getJoueur2());

    }

    private void printCol(int cote) {
        System.out.print("     A");
        for (int x = 1; x < cote; x++) { //game.getCote()
            char col = (char) (x + 65);
            System.out.print("   " + col);
        }
        System.out.println();
    }

    private void afficheLegende() {
        System.out.println(" B: Grand Bateau / S: Petit Bateau");
        System.out.println(" A: Mine Atomique / N: Mine Normale");
    }

    private void afficheEtatArmee(Army army1, Army army2) {
        System.out.println("");
        System.out.println("Etat des armees");
        System.out.println("Position   Type   Integrité(%) Armée ");
        for (Navire n : army1.getListeNavire()) {
            System.out.println(" " + n.getPopo() + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());

        }
        for (Navire n : army2.getListeNavire()) {
            System.out.println(" " + n.getPopo() + "        " + n.getType() + "      " + n.getPointVie() + "       " + army2.getNom());
        }
    }

//    private void detecterNavires(MerBoard mer, int x, int y) {
//
//    }
    enum Couleur {
        RESET("\u001B[0m"),
        RED("\u001B[31m"),
        BLUE("\u001B[34m"),
        BLACK("\u001B[30m");

        private final String code;

        private Couleur(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
//    
//    public void showEtatArmee()
//            print "position ..."
//
//    public static char[][] getMerAffichage() {
//        return merAffichage;
//    }

    public void askName1() {
        System.out.println("Entrer nom Joueur1 suivit de Enter ");
    }

    public void askName2() {
        System.out.println("Entrer nom Joueur2 suivit de Enter ");
    }
    
    public void askTailleCote(){
        System.out.println("De quelle taille voulez vous votre plateau de jeu (taille du côté) ? ");
    }

    public void askPermissionDeplacement() {
        System.out.println("Voulez vous déplacer un navire ? 1 pour se déplacer / 2 pour ne pas se déplacer.");
    }

    public void askDeplacement() {
        System.out.println("Vers quel postition voulez vous déplacer le navire (Ex : B5) ? ");
    }

    public void showVictory() {
        Army joueur1 = null;
        Army joueur2 = null;
        if (joueur1.listeVide()) {
            System.out.println(joueur1.nom + "vous avez gagnez !!! BRAVO !");
        } else if (joueur2.listeVide()) {
            System.out.println(joueur2.nom + "vous avez gagnez !!! BRAVO !");
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        showGame();
    }

    public void showGame() {
        afficherGame();
    }

}

//
//    public void showLose() {
//        System.out.println("Joueur :" + Army.getNomArmee());  //Sera surement dans Army getNomArmee+ " a perdu !");
//    }
//
//    public void afficherError() {
//        System.out.println("");
//        System.out.print("Erreur : Touche non assigné !");
//        System.out.println("");
//    }
//
