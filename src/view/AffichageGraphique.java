package view;

import java.awt.Image;
import javafx.scene.image.Image;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

public class AffichageGraphique extends Application implements Observer {

    private final Image bateauEquipe1 = new Image("file:src/image/battleship_1 Cropped.png");
    private final Image bateauEquipe2 = new Image("file:src/image/battleship_2 Cropped.png");
    private final Image mer = new Image("file:src/image/Mer_gif.gif");

    @Override

    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
