package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    
    TableCell tv = new TableCell();
    Text armeeGauche = new Text("armee1");
    
    Text armeeDroite = new Text("armee2");

    GridPane merPane = new GridPane();

    Text action = new Text("Action");

    public AffichageGraphique(Stage stage, int cote, ControllerGraphique ctrl) {
        ctrlG = ctrl;
        COTE = cote;
//        setSizeConstraints();
        
//        merPane.
//        armeeGauche.set
//        merPane.setPrefHeight(4000);
//        merPane.setPrefWidth(4000);
        //merPane.
        VBox centre = new VBox();
        
        //centre.setPrefWidth(4000);
        centre.getChildren().add(merPane);
        centre.getChildren().add(action);

        BorderPane bp = new BorderPane();
        //bp.setPadding(new Insets(20,0, 100,0));
        bp.setLeft(armeeGauche);
        bp.setCenter(centre);
        centre.setAlignment(Pos.CENTER);
        bp.setRight(armeeDroite);

        for (int i = 0; i < COTE; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            merPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(40);
            merPane.getRowConstraints().add(row);
        }

        Scene scene = new Scene(bp, 800, 600);
        stage.setTitle("Bataille Navalle");
        stage.setScene(scene);
        stage.show();

    }

    private void afficherJeu(Observable o) {
        armeeGauche.
        Game game = (Game) o;
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

    @Override
    public void update(Observable o, Object arg) {
        afficherJeu(o);
    }

// Pour que chaque ligne et chaque colonne soit dimensionnée
//    private void setSizeConstraints() {
//        for (int i = 0; i < COTE; ++i) {
//            ColumnConstraints cc = new ColumnConstraints();
//            cc.setPercentWidth(100 / COTE);
//            getColumnConstraints().add(cc);
//            RowConstraints rc = new RowConstraints();
//            rc.setPercentHeight(100 / COTE);
//            getRowConstraints().add(rc);
//        }
//    }

    // La vue d'une "case"
    private abstract class BoxView extends Pane {

        public BoxView() {
            getStylesheets().add("view/Image.css");
        }
    }

    // La vue d'une "case" vide
    private class EmptyBoxView extends BoxView {

        public EmptyBoxView(int x, int y) {
            getStyleClass().add("mer");
            setOnMouseClicked(e -> ctrlG.clickCaseVide(x, y));
        }
    }

    // La vue d'un bateau
    private class NavireGrandEquipe1 extends BoxView {

        public NavireGrandEquipe1(int x, int y) {
            getStyleClass().add("bateauGrandEquipe1");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y));
            setOnMouseClicked(e -> ctrlG.clickAutreBateauPourTir(x, y));
        }
    }

    private class NavireGrandEquipe2 extends BoxView {

        public NavireGrandEquipe2(int x, int y) {
            getStyleClass().add("bateauGrandEquipe2");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y));
            setOnMouseClicked(e -> ctrlG.clickAutreBateauPourTir(x, y));
        }
    }

    private class NavirePetitEquipe1 extends BoxView {

        public NavirePetitEquipe1(int x, int y) {
            getStyleClass().add("bateauPetitEquipe1");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y));
            setOnMouseClicked(e -> ctrlG.clickAutreBateauPourTir(x, y));;
        }
    }

    private class NavirePetitEquipe2 extends BoxView {

        public NavirePetitEquipe2(int x, int y) {
            getStyleClass().add("bateauPetitEquipe2");
            setOnMouseClicked(e -> ctrlG.clickBateau(x, y));
            setOnMouseClicked(e -> ctrlG.clickAutreBateauPourTir(x, y));
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

    private void afficheLegende() {
        System.out.println(" B: Grand Bateau / S: Petit Bateau");
        System.out.println(" A: Mine Atomique / N: Mine Normale");
    }

    private void afficheEtatArmeeEquipe1(Army army1) {
        System.out.println("");
        System.out.println("Etat des armees");
        System.out.println("Position   Type   Integrité(%) Armée ");
        for (Navire n : army1.getListeNavire()) {
            System.out.println(" " + n.getPopo() + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());
        }
    }

    private void afficheEtatArmeeEquipe2(Army army2) {
        System.out.println("");
        System.out.println("Etat des armees");
        System.out.println("Position   Type   Integrité(%) Armée ");
        for (Navire n : army2.getListeNavire()) {
            System.out.println(" " + n.getPopo() + "        " + n.getType() + "      " + n.getPointVie() + "       " + army2.getNom());

        }

    }

}
