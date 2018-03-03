package view;

import controller.ControllerGraphique;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.Case;
import model.Game;
import model.MerBoard;
import javafx.stage.Stage;

public class AffichageGraphique extends GridPane implements Observer {
    
    private final int cote = 5;

    Game game = Game.getInstance();
    MerBoard board = game.getBoard();
    Case[][] mer = board.getTab();
    private final Canvas canvas = new Canvas();

    public AffichageGraphique(Stage stage, int size, ControllerGraphique aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        tabAffichage = Game.getInstance();
        launch(args);
    }

    public void afficherGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        getChildren().clear();
        for (int c = 0; c < cote; ++c) {
            for (int l = 0; l < cote; ++l) {
                if (game.getCote() == c && game.getCote() == l) {
                    add(new BoatView(c, l), c, l);
                } else {
                    add(new EmptyBoxView(c, l), c, l);
                }
            }
        }
    }
    
    
    private void setSizeConstraints() {
        for (int i = 0; i < cote; ++i) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100 / cote);
            getColumnConstraints().add(cc);
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100 / cote);
            getRowConstraints().add(rc);
        }        
    }
    
    // La vue d'une "case"
    private abstract class BoxView extends Pane {
        public BoxView() {
            getStylesheets().add("mer");
        }
    }

    // La vue d'une "case" vide
    private class EmptyBoxView extends BoxView {
        public EmptyBoxView(int x, int y) {
            getStyleClass().add("mer");
            setOnMouseClicked(e -> control.emptyBoxClicked(x, y));
        }
    }

    // La vue d'un bateau
    private class NavireEquipe1 extends BoxView {
        public BoatView(int x, int y) {
            getStyleClass().add("bateauEquipe1");
            setOnMouseClicked(e -> control.boatClicked(x, y));
        }
    }
    
    // La vue d'un bateau
    private class NavireEquipe2 extends BoxView {
        public BoatView(int x, int y) {
            getStyleClass().add("bateauEquipe2");
            setOnMouseClicked(e -> control.boatClicked(x, y));
        }
    }
}
