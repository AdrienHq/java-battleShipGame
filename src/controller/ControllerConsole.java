package controller;

import model.Game;
import view.AffichageConsole;

public class ControllerConsole {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        boolean gameOver = false;
        AffichageConsole affichage = new AffichageConsole();
        affichage.afficherGame();
        game.addObserver(affichage);

        
        
    }
    
    
        
        
    
    
}
