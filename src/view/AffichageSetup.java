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

    private TextField tf = new InputText1();
    private TextField tf2 = new InputText2();
    private TextField tfTaille = new InputNumber();
    private CheckBox switchBateau = new CheckBox("Placement aléatoire");
    private static boolean switchBat = false;

    private void setup() {
        Button btOk = new Button("Accepter");
        btOk.setOnAction(e -> {
            if (!tf.getText().isEmpty() && !tf2.getText().isEmpty() && !tf.getText().isEmpty()) {
                if (switchBateau.isSelected()) {
                    this.switchBat = false;
                    switchToMainWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
                } else {
                    this.switchBat = true;
                    switchtoBuilderWindow(tf.getText(), tf2.getText(), Integer.valueOf(tfTaille.getText()));
                }
            } else {
                tf.requestFocus(); // Laisse le focus au TextField
            }
        });
        getChildren().addAll(tf, tf2, tfTaille, btOk, switchBateau);
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

    private void switchtoBuilderWindow(String joueur1, String joueur2, int taille) {
        control.switchToBuilderWindow(joueur1, joueur2, taille);
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
                if (!newValue.matches("\\sa-zA-Z*")) {
                    setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
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
                if (!newValue.matches("\\sa-zA-Z*")) {
                    setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
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
