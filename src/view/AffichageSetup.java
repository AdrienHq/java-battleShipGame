package view;

import controller.ControllerGraphique;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AffichageSetup extends VBox {

    private final ControllerGraphique control;

    public AffichageSetup(Stage stage, ControllerGraphique ctrl) {
        control = ctrl;
        setup();
        stage.setTitle("Entrez les noms des deux joueurs.");
        stage.setScene(new Scene(this, 500, 300));
        stage.show();
    }

    TextField tf = new InputText();
    TextField tf2 = new InputText();
    TextField tfTaille = new InputNumber();

    private void setup() {
        FlowPane root = new FlowPane();
        Label labelJ1 = new Label("Joueur1");
        Label labelJ2 = new Label("Joueur2");
        Label labelTaille = new Label("Taille");

        Button btOk = new Button("Accepter");
        Button btReset = new Button("Reset");
        btOk.setOnAction(e -> {
            if (!tf.getText().isEmpty() && !tf2.getText().isEmpty()) {
                switchToMainWindow(tf.getText(), tf2.getText(),Integer.valueOf(tfTaille.getText()));
            } else {
                tf.requestFocus(); // Laisse le focus au TextField
            }
        });
        btReset.setOnAction(e -> {
            tf.setText(null);
            tf2.setText(null);
            tfTaille.setText(null);
        });
        getChildren().addAll(tf, tf2, tfTaille, btOk, btReset);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);
    }

    private void switchToMainWindow(String joueur1, String joueur2,int taille) {
        control.switchToMainWindow(joueur1, joueur2, taille);
    }

    private class InputText extends TextField {

        InputText() {
            super("Nom du Joueur");
            setAlignment(Pos.TOP_CENTER);
            setMaxWidth(150);
            installListeners();
        }

        private void installListeners() {
            textProperty().addListener((obs, oldValue, newValue) -> {
                if (!newValue.matches("\\[A-Za-z]")) { //regex pour que seulement des lettres soient acceptée
                    setText(oldValue);
                }
            });
//            setOnKeyPressed(ke -> {
//                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
//                    switchToMainWindow(String.copyValueOf(chars), String.copyValueOf(chars));
////                            Integer.valueOf(getText()));
//                }
//            });
        }
    }
    
    private class InputNumber extends TextField { //Pour la taille après

        InputNumber() {
            super("Taille de la mer: ?");
            setAlignment(Pos.CENTER);
            setMaxWidth(150);
            installListeners();
        }

        private void installListeners() {
            // N'accepte que les chiffres
            textProperty().addListener((obs, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    setText(oldValue);
                }
            });
//            // Capture du Enter pour valider saisie
//            setOnKeyPressed(ke -> {
//                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
//                    switchToMainWindow(Integer.valueOf(getText()));
//                }
//            });
        }
    }
}
