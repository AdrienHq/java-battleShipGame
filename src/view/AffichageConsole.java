package view;

import java.util.Observable;
import java.util.Observer;
import model.Army;
import model.Case;
import model.Game;
import model.MerBoard;
import model.Navire;
import model.Position;

public class AffichageConsole implements Observer {
    
    public AffichageConsole() {

    }
    
     public void afficherGame() {
        
        Game game = Game.getInstance();
        MerBoard board = game.getBoard();
        Case[][] mer = board.getTab();
        Case c = null ;
        Position pos = null ;
        Boolean debug = true  ;
        
        System.out.println("    A   B   C   D   E");
        for (int x = 0; x < game.getCote(); x++) {
            System.out.print((x + 1) + " |"); //Affiche l'entête de la ligne (1 2 3 4 5 ...)
            for (int y = 0; y < game.getCote(); y++) {
                    
                    pos = new Position(x,y);
                    c = mer[x][y];
                
                    if(!c.estVide()){//si contient qqch
                        if(c.getTypeNavire() == "BIG"){ //si grand navire 
                            Navire n = c.getNavire();
                            if(game.getNomJoueur1()== n.getNom()  ){       //si nom bateau =
                                System.out.print(Couleur.RED + " B " + Couleur.RESET + "|");
                            }else{
                                System.out.print(Couleur.BLUE + " B " + Couleur.RESET + "|");
                            }
                        
                        
                        }else if(c.getTypeNavire() == "SMALL") {                  //si petit navire
                            Navire n = c.getNavire();
                            if(game.getNomJoueur1()== n.getNom()){       //si nom bateau =
                                System.out.print(Couleur.RED + " S " + Couleur.RESET + "|");
                            }else{
                                System.out.print(Couleur.BLUE + " S " + Couleur.RESET + "|");
                            }
                        }else {
                            if(debug == true){
                                    if(c.getTypeFlottant()== "ATOMIQUE"){
                                        System.out.print(Couleur.BLACK +" A " + Couleur.RESET + "|");
                                    }
                                    else{
                                     System.out.print(Couleur.BLACK +" N " + Couleur.RESET + "|");
                                    }
                            }else{
                                    System.out.print(Couleur.BLACK +"   " + Couleur.RESET + "|");
                            }                
                        }
                    }else{
                        System.out.print("   |");
                    }
                      
            }
            System.out.println(" ");
        }
     }    
     
    private void detecterNavires(MerBoard mer , int x ,int y){
        
    }
     
    enum Couleur {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    BLACK("\u001B[30m");

    private final String code;
    private Couleur(String code) { this.code = code; }
    @Override public String toString() { return code; }
}
//    
//    public void showEtatArmee()
//            print "position ..."
//
//    
//    
//    
//    public static char[][] getMerAffichage() {
//        return merAffichage;
//    }
    
    public void askName1(){
        
        System.out.println("Entrer nom Joueur1 suivit de Enter ");
        
    }
     public void askName2(){
        
        System.out.println("Entrer nom Joueur2 suivit de Enter ");
        
    }
    
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
    @Override
    public void update(Observable o, Object o1) {
        showGame();
    }

    public void showGame() {
        afficherGame();
    }

   

    
}
