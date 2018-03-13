package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.Game;

import javafx.stage.Stage;

public class AffichageGraphique extends GridPane implements Observer {

    private final int COTE;
    private final ControllerGraphique ctrlG;

    public AffichageGraphique(Stage stage, int cote, ControllerGraphique ctrl) {
        ctrlG = ctrl;
        COTE = cote;
        setSizeConstraints();
        stage.setScene(new Scene(this, 600, 400));
        stage.setTitle("Plateau de jeu");
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        getChildren().clear();
        for (int c = 0; c < COTE; ++c) {
            for (int l = 0; l < COTE; ++l) {
                if (game.getCote() == c && game.getCote() == l) {
                    add(new BoatView(c, l), c, l);
                } else {
                    add(new EmptyBoxView(c, l), c, l);
                }
            }
        }
    }

// Pour que chaque ligne et chaque colonne soit dimensionnée
    private void setSizeConstraints() {
        for (int i = 0; i < COTE; ++i) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100 / COTE);
            getColumnConstraints().add(cc);
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100 / COTE);
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
