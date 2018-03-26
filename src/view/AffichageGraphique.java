package view;

import controller.ControllerGraphique;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.scene.text.TextAlignment;
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
    
//    TableView etatArmee1 = new TableView();
//    TableView etatArmee2 = new TableView();
    GridPane etatArmee1 = new GridPane();
    GridPane etatArmee2 = new GridPane();
    
    GridPane merPane = new GridPane();

    Text action = new Text("Action");

    public AffichageGraphique(Stage stage, int cote, ControllerGraphique ctrl) {
        ctrlG = ctrl;
        COTE = cote;

       // nomArmee1.setPrefHeight(30);
        
        
        VBox centre = new VBox();
        centre.getChildren().add(merPane);
        centre.getChildren().add(action);
        
        BorderPane bp = new BorderPane();
        //bp.setPadding(new Insets(20,0, 100,0));
        bp.setLeft(etatArmee1);
        bp.setCenter(centre);
        centre.setAlignment(Pos.CENTER);
        bp.setRight(etatArmee2);

        for (int i = 0; i < COTE; i++) {
            ColumnConstraints column = new ColumnConstraints(60);
            merPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(60);
            merPane.getRowConstraints().add(row);
        }
        //etatArmee1.getColumnConstraints()
        //for

        Scene scene = new Scene(bp,265+(COTE*60), 120+(COTE*60));
        stage.setTitle("Bataille Navale");
        stage.setScene(scene);
        stage.show();
        

    }
    

    private void afficherJeu(Observable o) {
        
        
        Game game = (Game) o;
        
        etatArmee(game.getJoueur1(),game.getJoueur2());
                
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
    private void etatArmee(Army army1,Army army2) {
        //nomArmee1 = new Text(army.getNom());
        Text a1 = new Text(army1.getNom());
        Text a2 = new Text(army2.getNom());
        
        Text pos = new Text("Position");
        Text type = new Text("Type");
        Text etat = new Text("Etat");
        
        etatArmee1.setHgap(10);
        etatArmee1.setVgap(10);
        etatArmee1.add(a1, 1, 0, 1, 1);
        etatArmee1.add(pos, 0, 1, 1, 1);
        etatArmee1.add(type, 1, 1, 1, 1);
        etatArmee1.add(etat, 2, 1, 1, 1);
        
        etatArmee2.setHgap(10);
        etatArmee2.setVgap(10);
        etatArmee2.add(a2, 1, 0, 1, 1);
        etatArmee2.add(pos, 0, 1, 1, 1);
        etatArmee2.add(type, 1, 1, 1, 1);
        etatArmee2.add(etat, 2, 1, 1, 1);
        
        
        
        int x = 0 ;
        int y = 2 ;
        for (Navire n : army1.getListeNavire()) {
            
             // + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());
            Text posX = new Text(n.getPopo());
            
            etatArmee1.add(posX, x, y, 1, 1);
            ++x;
            Text typeX = new Text( n.getType());
            etatArmee1.add(typeX, x, y, 1, 1);
            ++x;
            Text etatX = new Text(String.valueOf(n.getPointVie()));
            etatArmee1.add(etatX, x, y, 1, 1);
             x =  0;
             ++y;
        }
        x = 0 ;
        y = 2 ;
        for (Navire n2 : army2.getListeNavire()) {
            
             // + "        " + n.getType() + "      " + n.getPointVie() + "       " + army1.getNom());
            Text posX2 = new Text(n2.getPopo());
            etatArmee2.add(posX2, x, y, 1, 1);
            ++x;
            Text typeX2 = new Text( n2.getType());
            etatArmee2.add(typeX2, x, y, 1, 1);
            ++x;
            Text etatX2 = new Text(String.valueOf(n2.getPointVie()));
            etatArmee2.add(etatX2, x, y, 1, 1);
             x =  0;
             ++y;            
        
        }
       
        
    }

    @Override
    public void update(Observable o, Object arg) {
        afficherJeu(o);
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
