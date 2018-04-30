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
import javafx.scene.paint.Color;
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
    Text armee1 = new Text("joueur1");
    Text armee2 = new Text("joueur2");
    
    

    public AffichageBuilder(Stage stage, int cote, ControllerGraphique ctrl) {

        ctrlG = ctrl;
        COTE = cote;
        VBox centre = new VBox();
        VBox gauche = new VBox();
        VBox droite = new VBox();
        
        centre.getChildren().add(merBuild);
        centre.getChildren().add(action);
        
        gauche.getChildren().add(armee1);
        gauche.getChildren().add(bateauArmee1);
        
        droite.getChildren().add(armee2);
        droite.getChildren().add(bateauArmee2);
        
        Insets insets = new Insets(2);
        BorderPane bp = new BorderPane();
        bp.setLeft(gauche);
        bp.setMargin(gauche, insets);
        bp.setCenter(centre);
        bp.setMargin(centre, insets);
        centre.setAlignment(Pos.CENTER);
        bp.setRight(droite);
        bp.setMargin(droite, insets);
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
            
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            bateauArmee1.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(60);
            bateauArmee1.getRowConstraints().add(row);
        }

        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            bateauArmee2.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(60);
            bateauArmee2.getRowConstraints().add(row);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        MerBuilder merBuilder = (MerBuilder) o;
        getChildren().clear();
        afficherBateau(merBuilder.getJoueur1(), merBuilder.getJoueur2());
        MerBoard board = merBuilder.getBoard();
        Case[][] mer = board.getTab();
        Case c = null;
        Position pos = null;
        Boolean debug = true;

        merBuild.getChildren().clear();
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
                    merBuild.add(new AffichageBuilder.EmptyBoxView2(x, y), x, y);
                }
            }
        }
    }

    public void affichageNomJoueur(Army army1, Army army2){
        armee1.setText(army1.getNom());
        System.out.println(army1.getNom());
        armee1.setStyle("-fx-background-color: red; -fx-padding: 10px;");
        armee1.setFont(Font.font("Verdana", 20));
        armee2.setText(army2.getNom());
        armee2.setStyle("-fx-background-color: blue; -fx-padding: 10px;");
        armee2.setFont(Font.font("Verdana", 20));
    }
    
    private void afficherBateau(Army army1, Army army2) {
        
        bateauArmee1.getChildren().clear();
        bateauArmee2.getChildren().clear();
        
        affichageNomJoueur(army1,army2);

        int x = 0;
        int y = 1 ;
        Case c = null;
        MerBuilder merBuilder = null;
//        bateauArmee1.add(new NavireGrandEquipe1(3, 3), 3, 3);

        for (Navire n : army1.getListeNavire()) {
            
            if (n.getType() == "BIG") { //si grand navire 
                bateauArmee1.add(new NavireGrandEquipe1(x, y), x, y);
                System.out.println("big " + x + " " + y);
            } else if (n.getType() == "SMALL") {                  //si petit navire
                bateauArmee1.add(new NavirePetitEquipe1(x, y), x, y);
                System.out.println("petit " + x + " " + y);
            }
            ++x;

        }
        x = 0;
        for (Navire n : army2.getListeNavire()) {
            if (n.getType() == "BIG") { //si grand navire 
                bateauArmee2.add(new NavireGrandEquipe2(x, y), x, y);
            } else if (n.getType() == "SMALL") {                  //si petit navire
                bateauArmee2.add(new NavirePetitEquipe2(x, y), x, y);
            }
            ++x;

        }

    }

    public void afficherTextAction(String army, String msg) {
        action.setText(army + " " + msg);
    }

    // La vue d'une "case"
    private abstract class BoxView2 extends Pane {

        public BoxView2() {
            getStylesheets().add("view/Image.css");
        }
    }

    // La vue d'une "case" vide
    private class EmptyBoxView2 extends BoxView2 {

        //case vide 
        public EmptyBoxView2(int x, int y) {
            getStyleClass().add("mer");
        }
    }

    // La vue d'un bateau
    private class NavireGrandEquipe1 extends BoxView2 {

        public NavireGrandEquipe1(int x, int y) {
            getStyleClass().add("bateauGrandEquipe1");
//            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
//            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
//            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavireGrandEquipe2 extends BoxView2 {

        public NavireGrandEquipe2(int x, int y) {
            getStyleClass().add("bateauGrandEquipe2");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavirePetitEquipe1 extends BoxView2 {

        public NavirePetitEquipe1(int x, int y) {
            getStyleClass().add("bateauPetitEquipe1");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }
    }

    private class NavirePetitEquipe2 extends BoxView2 {

        public NavirePetitEquipe2(int x, int y) {
            getStyleClass().add("bateauPetitEquipe2");
            setOnMousePressed(e -> ctrlG.clickPressed(x, y));
            setOnMouseReleased(e -> ctrlG.clickReleased(x, y));
            setOnMouseDragged(e -> ctrlG.clickDragged(x, y));
        }

    }
}
