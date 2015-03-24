package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Jared Ramirez
 */
public class IntensityInfoController implements Initializable {
    Stage stage = new Stage();

    public void setStage(Stage s) {
        stage = s;
    }

    @FXML
    public void closeWindow() {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
