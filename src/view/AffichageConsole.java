package view;

import java.util.Observable;
import java.util.Observer;
import model.Army;
import model.MerBoard;

public class AffichageConsole implements Observer {
//
//    private static final Character x = null;
//    private Army Army = null;
//
//    private static final char[][] merAffichage = {
//        {'x', 'x', 'A', 'x', 'B', 'x', 'C', 'x', 'D', 'x', 'E'},
//        {'1', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
//        {'2', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
//        {'3', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
//        {'4', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},
//        {'5', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|', 'x', '|'},};
//
//    public AffichageConsole() {
//
//    }
//
//    public static char[][] getMerAffichage() {
//        return merAffichage;
//    }
//
//    public void showVictory() {
//
//        System.out.println("Joueur :" + Army.getNomArmee()); //Sera surement dans Army getNomArmee + " a gagné !"); //AJOUTER GETNOM POUR PRENDRE LE NOM DU JOUEUR / ARMEE
//    }
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
//    @Override
//    public void update(Observable o, Object o1) {
//        showGame();
//    }
//
//    public void showGame() {
//        afficherGame();
//    }
//
//    public void afficherGame() {
//        MerBoard mer = MerBoard.getInstance();
//        System.out.println(" A B C D E");
//        for (int x = 0; x < MerBoard.LIGNE; x++) {
//            System.out.print((x + 1) + " |"); //Affiche l'entête de la ligne (1 2 3 4 5 ...)
//            for (int y = 0; y < MerBoard.COLONNE; y++) {
//                System.out.print(mer.getCase(x, y) + " |");
//            }
//        }
//    }

}
