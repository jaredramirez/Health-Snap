package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Managers;
import objects.CalorieEntry;
import objects.ExerciseEntry;
import objects.WaterEntry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateWindowController implements Initializable {

    Managers managers = new Managers();
    Stage stage = new Stage();
    Stage prevStage = new Stage();

    //Error Label
    @FXML
    Label nameError;
    @FXML
    Label passwordError;
    @FXML
    Label passwordError2;
    @FXML
    Label heightError;
    @FXML
    Label weightError;
    @FXML
    Label ageError;
    @FXML
    Label waistError;
    @FXML
    Label genderError;
    @FXML
    Label intensityError;


    //DESCRIPTION LABLES
    @FXML
    Label nameLabel;
    @FXML
    Label passwordLabel;
    @FXML
    Label passwordLabel2;
    @FXML
    Label heightLabel;
    @FXML
    Label weightLabel;
    @FXML
    Label ageLabel;
    @FXML
    Label waistLabel;

    //TEXT FIELDS
    @FXML
    TextField nameField;
    @FXML
    TextField passwordField;
    @FXML
    TextField passwordField2;
    @FXML
    TextField heightField;
    @FXML
    TextField weightField;
    @FXML
    TextField ageField;
    @FXML
    TextField waistField;

    //OTHER
    @FXML
    ComboBox<String> genderComboBox;
    @FXML
    ComboBox<String> intensityComboBox;
    @FXML
    Label createdLabel;

    //HYPERLINK INFO

    @FXML
    Hyperlink hyperLink;
    @FXML
    Label whichIntensity;

    public void setManagers(Managers m) {
        managers = m;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void setPrevStage(Stage s) {
        prevStage = s;
    }

    @FXML
    public void getIntensities() {
        try {
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/intensityInfo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            IntensityInfoController controller = fxmlLoader.getController();
            controller.setStage(stage2);

            stage2.setTitle("Which Intensity do I choose?");
            stage2.setResizable(false);
            stage2.setScene(new Scene(root));
            stage2.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @FXML
    public void createPerson(ActionEvent event) {
        int b = 0;
        if (managers.validateTextString(nameField.getText(), 30, 1, nameError, "Must enter username", "Must enter username", "Can't be larger then 30 characters")) {
            b++;
        }
        if (managers.validateTextString(passwordField.getText(), 30, 6, passwordError, "Must be entered", "Must be at least 6 characters", "Can't be larger then 30 characters")) {
            b++;
        }
        if (managers.checkPassword(passwordField.getText(), passwordField2.getText(), passwordError2, "Passwords don't match")) {
            b++;
        }
        if (managers.validateTextInt(heightField.getText(), 85, 0, heightError, "Must enter value", "Too short", "Too long")) {
            b++;
        }
        if (managers.validateTextInt(weightField.getText(), 500, 0, weightError, "Must enter value", "Too short", "Too long")) {
            b++;
        }
        if (managers.validateTextInt(ageField.getText(), 120, 0, ageError, "Must enter value", "Too short", "Too long")) {
            b++;
        }
        if (managers.validateTextInt(waistField.getText(), 100, 0, waistError, "Must enter value", "Too short", "Too long")) {
            b++;
        }
        if (managers.validateComboBox(genderComboBox, genderError, "Must Select Value")) {
            b++;
        }
        if (managers.validateComboBox(intensityComboBox, intensityError, "Must Select Value")) {
            b++;
        }

        if (b == 9) {
            ArrayList<CalorieEntry> calorieData = new ArrayList();
            ArrayList<WaterEntry> waterData = new ArrayList();
            ArrayList<ExerciseEntry> exerciseData = new ArrayList();

            managers.createUser(Double.parseDouble(heightField.getText()), Double.parseDouble(weightField.getText()),
                    Double.parseDouble(ageField.getText()), Double.parseDouble(waistField.getText()), isBoyOrGirl(genderComboBox),
                    nameField.getText(), passwordField.getText(), calorieData, waterData, exerciseData, intensityComboBox.getValue());

            managers.save();
            refereshPage();
            stage.close();
            prevStage.show();
        }


    }

    public void refereshPage() {
        nameField.setText("");
        passwordField.setText("");
        passwordField2.setText("");
        heightField.setText("");
        weightField.setText("");
        ageField.setText("");
        waistField.setText("");
        genderComboBox.setValue("");

        createdLabel.setText("User Created");
    }

    @FXML
    public void cancelWindow() {
        stage.close();
        prevStage.show();
    }

    public boolean isBoyOrGirl(ComboBox comboBox) { // true: = boy false = girl
        if (comboBox.getValue().equals("Male")) {
            return true;
        } else {
            return false;
        }
    }

    ObservableList<String> list = FXCollections.observableArrayList(
            "Male", "Female"
    );

    ObservableList<String> list2 = FXCollections.observableArrayList(
            "Low", "Light", "Moderate", "Active", "Very Active"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genderComboBox.setItems(list);
        intensityComboBox.setItems(list2);
    }

}
