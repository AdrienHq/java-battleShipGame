package view;

import controller.ControllerGraphique;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AffichageSetup extends VBox {

    private final ControllerGraphique control;

    public AffichageSetup(Stage stage, ControllerGraphique ctrl) {
        control = ctrl;
        setup();
        stage.setTitle("Choose Size");
        stage.setScene(new Scene(this, 300, 120));
        stage.show();
    }

    private void setup() {
        TextField tf = new InputNumber();
        Button bt = new Button("OK");
        bt.setOnAction(e -> {
            if (!tf.getText().isEmpty()) {
                switchToMainWindow(Integer.valueOf(tf.getText()));
            } else {
                tf.requestFocus(); // Laisse le focus au TextField
            }
        });
        getChildren().addAll(tf, bt);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);
    }

    private void switchToMainWindow(int size) {
        control.switchToMainWindow(size);
    }

    private class InputNumber extends TextField {

        InputNumber() {
            super("5");
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
            // Capture du Enter pour valider saisie
            setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
                    switchToMainWindow(Integer.valueOf(getText()));
                }
            });
        }
    }
}
