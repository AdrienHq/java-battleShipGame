package controller;

import model.Direction;
import model.Game;
import view.AffichageConsole;

public class ControllerConsole {
    public static void main(String[] args) {
        Game game = Game.getInstance(); //créée une instance de Game 
        boolean finJeu = false;
        Direction direction;
        AffichageConsole affichage = new AffichageConsole(); //Crée un affichatge du jeu
        affichage.afficherGame(); // affiche le jeu 
        game.addObserver(affichage); //Ajoute l'observer 
        
        do {
            direction = saisieToucheClavier(); //la direction vaut la saisie au clavier
            if (direction != null) { //tant que la personne joue 
                finJeu = Game.jouer(direction); 
                if (finJeu) {
                    game.notifyObservers(); //Notify l'obs de la fin du jeu 
                }
            }
        } while (!finJeu);
        if (game.joueurAPerdu()) { //Si il reste encore des bateaux
            affichage.showLose(); //affiche l'ecran de fin Perdant
        } else {
            affichage.showVictory(); //affiche l'ecran de fin Gagnant
        }  
    }
    
    
    public static Direction saisieToucheClavier() {
        char c = '1'; // un character pour conserver les choix utilisateur
        try {
            c = (char)System.in.read();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return getDirection(c);                        
    }
    
    private static Direction getDirection (char c) {
        switch(c) {                     //Activation en fonction des choix de l'utilisateur
            case 'z' : return Direction.HAUT;
            case 's' : return Direction.BAS;
            case 'q' : return Direction.GAUCHE;
            case 'd' : return Direction.DROITE;
            case 't' : return Direction.STOP;
            case 'x' : System.exit(1);
            default : return null;
        }
    }
    
    
        
        
    
    
}
