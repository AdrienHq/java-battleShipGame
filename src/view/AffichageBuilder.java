package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Army;
import model.Case;
import model.Game;
import model.MerBoard;
import model.MerBuilder;
import model.Navire;
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
        afficherBateau(merBuilder.getJoueur1(), merBuilder.getJoueur2());
        MerBoard board = merBuilder.getBoard();
        Case[][] mer = board.getTab();
        Case c = null;
        Position pos = null;
        Boolean debug = true;

        for (int x = 0; x < COTE; x++) {
            for (int y = 0; y < COTE; y++) {
                pos = new Position(x, y);
                c = mer[x][y];
                if (!c.estVide()) {//si contient qqch
                    if (c.getTypeNavire() == "BIG") { //si grand navire 
                        Navire n = c.getNavire();
                        if (merBuilder.getNomJoueur1() == n.getNom()) {       //si nom bateau =

                            merBuild.add(new AffichageBuilder.NavireGrandEquipe1(x, y), x, y);
                        } else {
                            merBuild.add(new AffichageBuilder.NavireGrandEquipe2(x, y), x, y);
                        }

                    } else if (c.getTypeNavire() == "SMALL") {                  //si petit navire
                        Navire n = c.getNavire();
                        if (merBuilder.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            merBuild.add(new AffichageBuilder.NavirePetitEquipe1(x, y), x, y);
                        } else {
                            merBuild.add(new AffichageBuilder.NavirePetitEquipe2(x, y), x, y);
                        }
                    }
                } else {
                    merBuild.add(new AffichageBuilder.EmptyBoxView(x, y), x, y);
                }
            }
        }
    }

    private void afficherBateau(Army army1, Army army2) {
        Label a1 = new Label(army1.getNom());
        a1.setStyle("-fx-background-color: red; -fx-padding: 10px;");
        a1.setFont(Font.font("Verdana", 20));
        Label a2 = new Label(army2.getNom());
        a2.setStyle("-fx-background-color: blue; -fx-padding: 10px;");
        a2.setFont(Font.font("Verdana", 20));

        bateauArmee1.setHgap(10);
        bateauArmee1.setVgap(10);
        bateauArmee1.add(a1, 1, 0, 1, 1);

        bateauArmee2.setHgap(10);
        bateauArmee2.setVgap(10);
        bateauArmee2.add(a1, 1, 0, 1, 1);

        int x = 0;
        int y = 2;
        Case c = null;
        MerBuilder merBuilder = null;

        for (Navire n : army1.getListeNavire()) {
            if (!c.estVide()) {//si contient qqch
                if (c.getTypeNavire() == "BIG") { //si grand navire 
                    n = c.getNavire();
                    if (merBuilder.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                        bateauArmee1.add(new NavireGrandEquipe1(x, y), x, y);
                    } else {
                        bateauArmee2.add(new NavireGrandEquipe2(x, y), x, y);
                    }

                } else if (c.getTypeNavire() == "SMALL") {                  //si petit navire
                    n = c.getNavire();
                    if (merBuilder.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                        bateauArmee1.add(new NavirePetitEquipe1(x, y), x, y);
                    } else {
                        bateauArmee2.add(new NavirePetitEquipe2(x, y), x, y);
                    }
                }
            }
        }
    }

    public void afficherTextAction(String army, String msg) {
        action.setText(army + " " + msg);
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
        }
    }

    // La vue d'un bateau
    private class NavireGrandEquipe1 extends BoxView {

        public NavireGrandEquipe1(int x, int y) {
            getStyleClass().add("bateauGrandEquipe1");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavireGrandEquipe2 extends BoxView {

        public NavireGrandEquipe2(int x, int y) {
            getStyleClass().add("bateauGrandEquipe2");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavirePetitEquipe1 extends BoxView {

        public NavirePetitEquipe1(int x, int y) {
            getStyleClass().add("bateauPetitEquipe1");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavirePetitEquipe2 extends BoxView {

        public NavirePetitEquipe2(int x, int y) {
            getStyleClass().add("bateauPetitEquipe2");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }

    }
}
