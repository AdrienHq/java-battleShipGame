package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Game;
import javafx.stage.Stage;
import model.Army;
import model.Case;
import model.MerBoard;
import model.Navire;
import model.Position;

public class AffichageGraphique extends GridPane implements Observer {

    private final int COTE;
    private final ControllerGraphique ctrlG;
    int x = 0;

    public int getX() {
        return x;
    }
//    TableView etatArmee1 = new TableView();
//    TableView etatArmee2 = new TableView();
    GridPane etatArmee1 = new GridPane();
    GridPane etatArmee2 = new GridPane();

    private static GridPane merPane = new GridPane();

    Text action = new Text("Action");
    Text debugText = new Text("Debug");

    public AffichageGraphique(Stage stage, int cote, ControllerGraphique ctrl) {
        ctrlG = ctrl;
        COTE = cote;

        // nomArmee1.setPrefHeight(30);
        VBox centre = new VBox();
        centre.getChildren().add(merPane);
        centre.getChildren().add(action);
        centre.getChildren().add(debugText);

        Insets insets = new Insets(20);

        BorderPane bp = new BorderPane();
        bp.setLeft(etatArmee1);
        bp.setMargin(etatArmee1, insets);
        bp.setCenter(centre);
        bp.setMargin(centre, insets);
        centre.setAlignment(Pos.CENTER);
        bp.setRight(etatArmee2);
        bp.setMargin(etatArmee2, insets);

        for (int i = 0; i < COTE; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            merPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(60);
            merPane.getRowConstraints().add(row);
        }
        //etatArmee1.getColumnConstraints()
        //for

        Scene scene = new Scene(bp, 600 + (COTE * 60), 350 + (COTE * 60));
        stage.setTitle("Bataille Navale");
        stage.setScene(scene);
        stage.show();
//        merPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//            double tmp = e.getY() / 60;
//            x = (int) (e.getX() / 60);
//            System.out.println(x);
//            int y = (int) tmp;
//            System.out.println(y);
//            setPositionClicked(new Position(x, y));
//
//        });

    }

    private void afficherJeu(Observable o) {
        Game game = (Game) o;
        etatArmee(game.getJoueur1(), game.getJoueur2());
        getChildren().clear();
        MerBoard board = game.getBoard();
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
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =

                            merPane.add(new NavireGrandEquipe1(x, y), x, y);
                        } else {
                            merPane.add(new NavireGrandEquipe2(x, y), x, y);
                        }

                    } else if (c.getTypeNavire() == "SMALL") {                  //si petit navire
                        Navire n = c.getNavire();
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            merPane.add(new NavirePetitEquipe1(x, y), x, y);
                        } else {
                            merPane.add(new NavirePetitEquipe2(x, y), x, y);
                        }
                    }
                } else if (c.estchoixPossible()) {
                    add(new EmptyBoxView(x, y), x, y);   //mettre image eau verte .
                } else if (c.estFlottant() && debug == true) {
                    if (c.getTypeFlottant() == "ATOMIQUE") {
                        merPane.add(new Atomique(x, y), x, y);
                    } else if (c.getTypeFlottant() == "NORMALE") {
                        merPane.add(new Normale(x, y), x, y);
                    }
                } else {
                    merPane.add(new EmptyBoxView(x, y), x, y);
                }
            }

        }
    }

    private void etatArmee(Army army1, Army army2) {
        //nomArmee1 = new Text(army.getNom());

        Label a1 = new Label(army1.getNom());
        a1.setStyle("-fx-background-color: red; -fx-padding: 10px;");
        a1.setFont(Font.font("Verdana", 20));
        Label a2 = new Label(army2.getNom());
        a2.setStyle("-fx-background-color: blue; -fx-padding: 10px;");
        a2.setFont(Font.font("Verdana", 20));

        Text pos = new Text("Position");
        Text type = new Text("Type");
        Text etat = new Text("Etat");
        pos.setFont(Font.font("Verdana", 20));
        pos.setFill(Color.RED);
        type.setFont(Font.font("Verdana", 20));
        type.setFill(Color.RED);
        etat.setFont(Font.font("Verdana", 20));
        etat.setFill(Color.RED);

        etatArmee1.setHgap(10);
        etatArmee1.setVgap(10);
        etatArmee1.add(a1, 1, 0, 1, 1);
        etatArmee1.add(pos, 0, 1, 1, 1);
        etatArmee1.add(type, 1, 1, 1, 1);
        etatArmee1.add(etat, 2, 1, 1, 1);

        Text pos2 = new Text("Position");
        Text type2 = new Text("Type");
        Text etat2 = new Text("Etat");
        pos2.setFont(Font.font("Verdana", 20));
        pos2.setFill(Color.BLUE);
        type2.setFont(Font.font("Verdana", 20));
        type2.setFill(Color.BLUE);
        etat2.setFont(Font.font("Verdana", 20));
        etat2.setFill(Color.BLUE);

        etatArmee2.setHgap(10);
        etatArmee2.setVgap(10);
        etatArmee2.add(a2, 1, 0, 1, 1);
        etatArmee2.add(pos2, 0, 1, 1, 1);
        etatArmee2.add(type2, 1, 1, 1, 1);
        etatArmee2.add(etat2, 2, 1, 1, 1);

        int x = 0;
        int y = 2;
        for (Navire n : army1.getListeNavire()) {

            // + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());
            Text posX = new Text(n.getPopo());
            posX.setFont(Font.font("Verdana", 20));
            posX.setFill(Color.RED);

            etatArmee1.add(posX, x, y, 1, 1);
            ++x;
            Text typeX = new Text(n.getType());
            typeX.setFont(Font.font("Verdana", 20));
            typeX.setFill(Color.RED);
            etatArmee1.add(typeX, x, y, 1, 1);
            ++x;
            Text etatX = new Text(String.valueOf(n.getPointVie()));
            etatX.setFont(Font.font("Verdana", 20));
            etatX.setFill(Color.RED);
            etatArmee1.add(etatX, x, y, 1, 1);
            x = 0;
            ++y;
        }
        x = 0;
        y = 2;
        for (Navire n2 : army2.getListeNavire()) {

            // + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());
            Text posX2 = new Text(n2.getPopo());
            posX2.setFont(Font.font("Verdana", 20));
            posX2.setFill(Color.BLUE);
            etatArmee2.add(posX2, x, y, 1, 1);
            ++x;
            Text typeX2 = new Text(n2.getType());
            typeX2.setFont(Font.font("Verdana", 20));
            typeX2.setFill(Color.BLUE);
            etatArmee2.add(typeX2, x, y, 1, 1);
            ++x;
            Text etatX2 = new Text(String.valueOf(n2.getPointVie()));
            etatX2.setFont(Font.font("Verdana", 20));
            etatX2.setFill(Color.BLUE);
            etatArmee2.add(etatX2, x, y, 1, 1);
            x = 0;
            ++y;

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        afficherJeu(o);
    }

    public void choixBateauTireur(String army) {
        action.setText(army + ",Veuillez sélectionner le bateau tireur(Tire Direct)");
    }

    public void choixBateauADeplacer(String army) {
        action.setText(army + ",Veuillez sélectionner le bateau à déplacer");
    }

    public void afficherPosition(String pos) {
        debugText.setText(pos);
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
            setOnMouseClicked(e -> ctrlG.clickCaseVide(x, y, e));
        }
    }

    // La vue d'un bateau
    private class NavireGrandEquipe1 extends BoxView {

        public NavireGrandEquipe1(int x, int y) {
            getStyleClass().add("bateauGrandEquipe1");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y, e));
        }
    }

    private class NavireGrandEquipe2 extends BoxView {

        public NavireGrandEquipe2(int x, int y) {
            getStyleClass().add("bateauGrandEquipe2");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y, e));
        }
    }

    private class NavirePetitEquipe1 extends BoxView {

        public NavirePetitEquipe1(int x, int y) {
            getStyleClass().add("bateauPetitEquipe1");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y, e));
        }
    }

    private class NavirePetitEquipe2 extends BoxView {

        public NavirePetitEquipe2(int x, int y) {
            getStyleClass().add("bateauPetitEquipe2");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y, e));
        }
    }

    private class Atomique extends BoxView {

        public Atomique(int x, int y) {
            getStyleClass().add("atomique");
        }
    }

    private class Normale extends BoxView {

        public Normale(int x, int y) {
            getStyleClass().add("normale");
        }
    }
}
