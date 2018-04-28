package view;

import controller.ControllerGraphique;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;

public class AffichageSetup extends VBox {

    private final ControllerGraphique control;

    ;

    public AffichageSetup(Stage stage, ControllerGraphique ctrl) {
        control = ctrl;
        setup();
        stage.setTitle("Initialisation");
        stage.setScene(new Scene(this, 300, 350));
        stage.show();
    }

    TextField tf = new InputText1();
    TextField tf2 = new InputText2();
    TextField tfTaille = new InputNumber();
    CheckBox switchBateau = new CheckBox("Placement aléatoire");
    private static boolean switchBat = false;

    private void setup() {
//        FlowPane root = new FlowPane();
//        Label labelJ1 = new Label("Joueur1");
//        Label labelJ2 = new Label("Joueur2");
//        Label labelTaille = new Label("Taille");

        Button btOk = new Button("Accepter");
        Button btReset = new Button("Reset");
        btOk.setOnAction(e -> {
            if (!tf.getText().isEmpty() && !tf2.getText().isEmpty() && !tf.getText().isEmpty()) {
                switchToMainWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
            } else {
                tf.requestFocus(); // Laisse le focus au TextField
            }
            if (switchBateau.isSelected()) {
                this.switchBat = true;
            }
        });
        btReset.setOnAction(e -> {
            tf.setText(null);
            tf2.setText(null);
            tfTaille.setText(null);
        });
        getChildren().addAll(tf, tf2, tfTaille, btOk, btReset, switchBateau);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);
    }

    public static boolean getCheckBox() {
        return AffichageSetup.switchBat;
    }

    private void switchToMainWindow(String joueur1, String joueur2, int taille) {
        control.switchToMainWindow(joueur1, joueur2, taille);
    }

    private class InputText1 extends TextField {

        InputText1() {
            super("Nom du Joueur 1");
            setAlignment(Pos.TOP_CENTER);
            setMaxWidth(150);
            installListeners();
        }

        private void installListeners() {
            textProperty().addListener((obs, oldValue, newValue) -> {
                if (!newValue.matches("^[a-zA-Z]+$")) { //regex pour que seulement des lettres soient acceptée
                    setText(oldValue);
                }
            });
            setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
                    switchToMainWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
                }
            });
        }
    }

    private class InputText2 extends TextField {

        InputText2() {
            super("Nom du Joueur 2");
            setAlignment(Pos.TOP_CENTER);
            setMaxWidth(150);
            installListeners();
        }

        private void installListeners() {
            textProperty().addListener((obs, oldValue, newValue) -> {
                if (!newValue.matches("^[a-zA-Z]+$")) { //regex pour que seulement des lettres soient acceptée
                    setText(oldValue);
                }
            });
            setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
                    switchToMainWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
                }
            });
        }
    }

    private class InputNumber extends TextField { //Pour la taille après

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
            setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER) && !getText().isEmpty()) {
                    switchToMainWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
                }
            });

        }
    }
}
