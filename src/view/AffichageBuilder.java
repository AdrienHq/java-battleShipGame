package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Case;
import model.MerBoard;
import model.MerBuilder;
import model.Position;

public class AffichageBuilder extends GridPane implements Observer {

    private final int COTE;
    private final ControllerGraphique ctrlG;
    GridPane bateauArmee1 = new GridPane();
    GridPane bateauArmee2 = new GridPane();

    private static GridPane merBuild = new GridPane();

    Text action = new Text("Action");

    public AffichageBuilder(Stage stage, int cote, ControllerGraphique ctrl) {
        ctrlG = ctrl;
        COTE = cote;
        VBox centre = new VBox();
        centre.getChildren().add(merBuild);
        centre.getChildren().add(action);
        Insets insets = new Insets(20);
        BorderPane bp = new BorderPane();
        bp.setLeft(bateauArmee1);
        bp.setMargin(bateauArmee1, insets);
        bp.setCenter(centre);
        bp.setMargin(centre, insets);
        centre.setAlignment(Pos.CENTER);
        bp.setRight(bateauArmee2);
        bp.setMargin(bateauArmee2, insets);
        setSizeConstraints();
        stage.setScene(new Scene(bp, 600 + (COTE * 60), 350 + (COTE * 60)));
        stage.setTitle("Builder");
        stage.show();

    }

    private void setSizeConstraints() {
        for (int i = 0; i < COTE; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            merBuild.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(60);
            merBuild.getRowConstraints().add(row);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        MerBuilder merBuilder = (MerBuilder) o;
//        getChildren().clear();
        //etatArmee(merBuilder.getJoueur1(), merBuilder.getJoueur2());
        MerBoard board = null;
        Case[][] mer = board.getTab();
        Case c = null;
        Position pos = null;
        for (int x = 0; x < COTE; x++) {
            for (int y = 0; y < COTE; y++) {
                merBuild.add(new AffichageBuilder.EmptyBoxView(x, y), x, y);
            }
        }
    }
    
        // La vue d'une "case"
    private abstract class BoxView extends Pane {
        public BoxView() {
            getStylesheets().add("view/Image.css");
        }
    }

    // La vue d'une "case" vide
    private class EmptyBoxView extends BoxView {

        //case vide 
        public EmptyBoxView(int x, int y) {
            getStyleClass().add("mer");
//            setOnMouseClicked(e -> ctrlG.clickCaseVide(x, y));
        }
    }

}
