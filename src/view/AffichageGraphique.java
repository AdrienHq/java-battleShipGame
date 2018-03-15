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
import model.Case;
import model.MerBoard;
import model.Navire;
import model.Position;

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
        MerBoard board = game.getBoard();
        Case[][] mer = board.getTab();
        Case c = null;
        Position pos = null;
        Boolean debug = true;

//        for (int x = 0; x < COTE; x++) {
//            
//             //Affiche l'entête de la ligne (1 2 3 4 5 ...)
//            for (int y = 0; y < COTE; y++) {
//                
//                    add(new EmptyBoxView(x, y), x, y);
//                
//            }
//            
//        }
        
        
        for (int x = 0; x < COTE; x++) {
            
             //Affiche l'entête de la ligne (1 2 3 4 5 ...)
            for (int y = 0; y < COTE; y++) {
                pos = new Position(x, y);
                c = mer[x][y];
                if (!c.estVide()) {//si contient qqch
                    if (c.getTypeNavire() == "BIG") { //si grand navire 
                        Navire n = c.getNavire();
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            add(new NavireGrandEquipe1(x, y), x, y);
                        } else {
                            add(new NavireGrandEquipe2(x, y), x, y);
                        }

                    } else if (c.getTypeNavire() == "SMALL") {                  //si petit navire
                        Navire n = c.getNavire();
                        if (game.getNomJoueur1() == n.getNom()) {       //si nom bateau =
                            add(new NavirePetitEquipe1(x, y), x, y);
                        } else {
                            add(new NavirePetitEquipe2(x, y), x, y);
                        }
                    } 
                } else if (c.estchoixPossible()) {
                        add(new EmptyBoxView(x, y), x, y);   //mettre image eau verte .
                } else if (c.estFlottant() && debug == true) {
                    if (c.getTypeFlottant() == "ATOMIQUE") {
                        add(new Atomique(x, y), x, y);
                    } else if (c.getTypeFlottant() == "NORMALE") {
                        add(new Normale(x, y), x, y);
                    }
                } else {
                    add(new EmptyBoxView(x, y), x, y);
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
            getStylesheets().add("view/Image.css");
        }
    }

    // La vue d'une "case" vide
    private class EmptyBoxView extends BoxView {

        public EmptyBoxView(int x, int y) {
            getStyleClass().add("mer");
//            setOnMouseClicked(e -> ctrlG.emptyBoxClicked(x, y));
        }
    }

    // La vue d'un bateau
    private class NavireGrandEquipe1 extends BoxView {

        public NavireGrandEquipe1(int x, int y) {
            getStyleClass().add("bateauGrandEquipe1");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }

    private class NavireGrandEquipe2 extends BoxView {

        public NavireGrandEquipe2(int x, int y) {
            getStyleClass().add("bateauGrandEquipe2");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }

    private class NavirePetitEquipe1 extends BoxView {

        public NavirePetitEquipe1(int x, int y) {
            getStyleClass().add("bateauPetitEquipe1");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }

    private class NavirePetitEquipe2 extends BoxView {

        public NavirePetitEquipe2(int x, int y) {
            getStyleClass().add("bateauPetitEquipe2");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }

    private class Atomique extends BoxView {

        public Atomique(int x, int y) {
            getStyleClass().add("atomique");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }

    private class Normale extends BoxView {

        public Normale(int x, int y) {
            getStyleClass().add("normale");
//            setOnMouseClicked(e -> ctrlG.boatClicked(x, y));
        }
    }
}
