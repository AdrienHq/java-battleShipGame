package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choix Controller");
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn.setText("Controller Console");
        btn2.setText("Controller Graphique");
        btn3.setText("Quitter");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            private String[] args;

            @Override
            public void handle(ActionEvent event) {
                ControllerConsole.main(args);     
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            private String[] args;

            @Override
            public void handle(ActionEvent event) {
                ControllerGraphique.main(args);
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               System.exit(0);
            }
        });

        GridPane root = new GridPane();
        root.add(btn, 0, 1);
        root.add(btn2, 2, 1);
        root.add(btn3, 3, 1);
        primaryStage.setScene(new Scene(root, 300, 25));
        primaryStage.show();
    }
}
