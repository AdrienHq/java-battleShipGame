package view;

import java.awt.Image;
import javafx.scene.image.Image;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import model.Case;
import model.Game;
import model.MerBoard;
import image;

public class AffichageGraphique extends Application implements Observer {

    Game game = Game.getInstance();
    MerBoard board = game.getBoard();
    Case[][] mer = board.getTab();

    private final Image bateauEquipe1 = new Image("file:src/image/battleship_1 Cropped.png");
    private final Image bateauEquipe2 = new Image("file:src/image/battleship_2 Cropped.png");
    private final Image merImage = new Image("file:src/image/Mer_gif.gif");
    private final Canvas canvas = new Canvas();

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        tabAffichage = Game.getInstance();
        launch(args);
    }
}
