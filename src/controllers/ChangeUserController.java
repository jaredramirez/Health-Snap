package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Managers;
import objects.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Jared Ramirez
 */
public class ChangeUserController implements Initializable {
    Person person = new Person(0.0, 0.0, 0.0, 0.0, false, "", "", null, null, null, "");
    Stage stage = new Stage();
    Stage prevStage = new Stage();
    Managers managers = new Managers();

    //Textfields
    @FXML
    TextField nameText;
    @FXML
    TextField passwordText;
    @FXML
    TextField passwordText2;
    @FXML
    TextField weightText;
    @FXML
    TextField heightText;
    @FXML
    TextField ageText;
    @FXML
    TextField waistText;

    //ComboBox
    @FXML
    ComboBox<String> genderComboBox;
    @FXML
    ComboBox<String> intensityComboBox;

    //Error Labels
    @FXML
    Label nameError;
    @FXML
    Label passwordError;
    @FXML
    Label passwordError2;
    @FXML
    Label weightError;
    @FXML
    Label heightError;
    @FXML
    Label ageError;
    @FXML
    Label waistError;
    @FXML
    Label genderError;
    @FXML
    Label intensityError;

    //OTHER
    @FXML
    Label changedLabel;

    ObservableList<String> list = FXCollections.observableArrayList(
            "Male", "Female"
    );

    ObservableList<String> list2 = FXCollections.observableArrayList(
            "Low", "Light", "Moderate", "Active", "Very Active"
    );

    public void setStage(Stage s) {
        stage = s;
    }

    public void setManagers(Managers m) {
        managers = m;
    }

    public void setPerson(Person p) {
        person = p;
    }

    public void setPrevStage(Stage s) {
        prevStage = s;
    }

    @FXML
    public void changeName() {
        if (managers.validateTextString(nameText.getText(), 30, 0, nameError, "Not filled out", "Too short", "Too long")) {
            person.setUser(nameText.getText());
            changedLabel.setText("Username Changed" + ", must logout for changes to take effect");
            nameError.setText("");
            nameText.setText("");
            managers.save();
        }
    }

    @FXML
    public void changePassword() {
        int b = 0;
        if (managers.validateTextString(passwordText.getText(), 50, 6, passwordError, "Not filled out", "Too short", "Too long")) {
            b++;
        }
        if (managers.checkPassword(passwordText.getText(), passwordText2.getText(), passwordError2, "Passwords don't match")) {
            b++;
        }

        if (b == 2) {
            person.setPassword(passwordText.getText());
            changedLabel.setText("Password Changed" + ", must logout for changes to take effect");
            passwordError.setText("");
            passwordError2.setText("");
            passwordText.setText("");
            passwordText2.setText("");
            managers.save();
        }
    }

    @FXML
    public void changeWeight() {
        if (managers.validateTextInt(weightText.getText(), 400, 0, weightError, "Enter Value", "Too short", "Too long")) {
            person.setWeight(managers.getDouble(weightText.getText()));
            changedLabel.setText("Weight Changed" + ", must logout for changes to take effect");
            weightText.setText("");
            weightError.setText("");
            managers.save();
        }
    }

    @FXML
    public void changeHeight() {
        if (managers.validateTextInt(heightText.getText(), 84, 0, heightError, "Enter Value", "Too short", "Too long")) {
            person.setHeight(managers.getDouble(heightText.getText()));
            changedLabel.setText("Height Changed" + ", must logout for changes to take effect");
            heightText.setText("");
            heightError.setText("");
            managers.save();
        }
    }

    @FXML
    public void changeAge() {
        if (managers.validateTextInt(ageText.getText(), 120, 0, ageError, "Enter Value", "Too short", "Too long")) {
            person.setAge(managers.getDouble(ageText.getText()));
            changedLabel.setText("Age Changed" + ", must logout for changes to take effect");
            ageError.setText("");
            ageText.setText("");
            managers.save();
        }
    }

    @FXML
    public void changeWaist() {
        if (managers.validateTextInt(waistText.getText(), 50, 0, waistError, "Enter Value", "Too short", "Too long")) {
            person.setWaist(managers.getDouble(waistText.getText()));
            changedLabel.setText("Waist Size Changed" + ", must logout for changes to take effect");
            waistError.setText("");
            waistText.setText("");
            managers.save();
        }
    }

    @FXML
    public void changeGender() {
        if (managers.validateComboBox(genderComboBox, genderError, "Please enter a value")) {
            if (genderComboBox.getValue().equals("Male")) {
                person.setGender(true);
            } else {
                person.setGender(false);
            }
            changedLabel.setText("Gender Changed" + ", must logout for changes to take effect");
            genderError.setText("");
            genderComboBox.setValue(null);
            managers.save();
        }
    }

    @FXML
    public void changeIntensity() {
        if (managers.validateComboBox(intensityComboBox, intensityError, "Please Enter a Value")) {
            if (!intensityComboBox.getValue().equals(null)) {
                person.setIntensity(intensityComboBox.getValue());
                changedLabel.setText("Intensiy Changed" + ", must logout for changes to take effect");
                genderError.setText("");
                genderComboBox.setValue(null);
                changedLabel.setText("Gender Changed");
                managers.save();
            }
        }
    }

    @FXML
    public void back() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
            System.out.println(person);
            Parent root = (Parent) fxmlLoader.load();
            MainWindowController controller = fxmlLoader.getController();
            controller.setUser(person);
            controller.setBMR(person);
            stage.close();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genderComboBox.setItems(list);
        intensityComboBox.setItems(list2);
    }

}
