package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Managers;
import objects.CalorieEntry;
import objects.ExerciseEntry;
import objects.Person;
import objects.WaterEntry;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Jared Ramirez
 */
public class MainWindowController implements Initializable {
    Managers managers = new Managers();
    Stage stage = new Stage();
    Stage prevStage = new Stage();
    Person person = new Person(0.0, 0.0, 0.0, 0.0, false, "", "", null, null, null, "");

    //FXML variables
    //Labels

    @FXML
    Label calorieDateLabel;
    @FXML
    Label totalCaloriesLabel;
    @FXML
    Label waterDateLabel;
    @FXML
    Label totalWaterLabel;
    @FXML
    Label exerciseDateLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label intensityLabel;
    @FXML
    Label quoteLabel;
    @FXML
    Label calorieDisplay;
    @FXML
    Label greeting;

    //Data Recieved
    @FXML
    TextField calorieDateText;
    @FXML
    TextField totalCalorieText;
    @FXML
    TextField waterDateText;
    @FXML
    TextField totalWaterText;
    @FXML
    TextField exerciseDateText;
    @FXML
    TextField timeText;
    @FXML
    ComboBox intensityComboBox;

    //Error Labels
    @FXML
    Label calorieDateError;
    @FXML
    Label totalCaloriesError;
    @FXML
    Label waterDateError;
    @FXML
    Label totalWaterError;
    @FXML
    Label exerciseDateError;
    @FXML
    Label timeError;
    @FXML
    Label intensityError;

    //Charts
    CategoryAxis calorieXAxis = new CategoryAxis();
    NumberAxis calorieYAxis = new NumberAxis();
    @FXML
    LineChart calorieChart = new LineChart(calorieXAxis, calorieYAxis);
    XYChart.Series calorieSeries = new XYChart.Series();

    CategoryAxis waterXAxis = new CategoryAxis();
    NumberAxis waterYAxis = new NumberAxis();
    @FXML
    LineChart waterChart = new LineChart(waterXAxis, waterYAxis);
    XYChart.Series waterSeries = new XYChart.Series();

    CategoryAxis exerciseXAxis = new CategoryAxis();
    NumberAxis exerciseYAxis = new NumberAxis();
    @FXML
    LineChart exerciseChart = new LineChart(exerciseXAxis, exerciseYAxis);
    XYChart.Series exerciseSeries = new XYChart.Series();


    public void setPrevStage(Stage s) {
        prevStage = s;
    }

    @FXML
    public void editUser() {
        try {
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ChangeUser.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ChangeUserController controller = fxmlLoader.getController();
            controller.setPrevStage(stage);
            controller.setStage(stage2);
            controller.setPerson(person);
            controller.setManagers(managers);

            stage2.setTitle("Change User");
            stage2.setResizable(false);
            stage2.setScene(new Scene(root));
            stage2.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void setQuote(String s) {
        Text t = new Text(s + " - Anonymous");
        quoteLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        quoteLabel.setText(t.getText());
    }

    public void setManagers(Managers m) {
        managers = m;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void setUser(Person p) {
        person = p;
    }

    public void setSeries(XYChart.Series c, XYChart.Series w, XYChart.Series e) {
        calorieSeries = c;
        waterSeries = w;
        exerciseSeries = e;
        calorieChart.getData().add(calorieSeries);
        waterChart.getData().add(waterSeries);
        exerciseChart.getData().add(exerciseSeries);
    }

    public void setBMR(Person person) {
        Double percentFat = 0.0;
        Double fatMass = 0.0;
        Double leanBodyMass = 0.0;
        Double BMR = 0.0;
        Double fin = 0.0;
        if (person.getGender()) {
            percentFat = (-98.42 + 4.15 * person.getWaist() - .082 * person.getWeight()) / person.getWeight();
        } else {
            percentFat = (-76.76 + 4.15 * person.getWaist() - .082 * person.getWaist()) / person.getWeight();
        }

        fatMass = percentFat * person.getWeight();
        leanBodyMass = person.getWeight() - fatMass;
        BMR = leanBodyMass * 13.8;
        fin = BMR * getIntenstiyCalc();
        fin = (double) Math.round(fin * 100) / 100;
        calorieDisplay.setText("Daily calorie intake: " + fin);
    }

    public void setGreeting(Person p) {
        greeting.setText("Welcome " + p.getUsername() + "!");
    }

    public Double getIntenstiyCalc() {
        String string = person.getIntensity();

        if (string.equals("Low")) {
            return 1.2;
        } else if (string.equals("Light")) {
            return 1.375;
        } else if (string.equals("Moderate")) {
            return 1.55;
        } else if (string.equals("Active")) {
            return 1.725;
        } else {
            return 1.9;
        }
    }

    @FXML
    public void logout() {
        stage.close();
        prevStage.show();
    }

    @FXML
    public void calorieEntry() {
        String date = calorieDateText.getText();
        String calories = totalCalorieText.getText();
        int counter = 0;

        if (managers.validateDate(date, calorieDateError)) {
            counter++;
        }
        if (managers.validateTextInt(calories, 5000, 0, totalCaloriesError, "Enter Value", "Cannot be less than 0", "Cannot be over 5000")) {
            counter++;
        }

        if (counter == 2) {
            CalorieEntry c = new CalorieEntry(Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(0, 2)),
                    Integer.parseInt(date.substring(6)), Integer.parseInt(calories));

            person.getCalorieData().add(c);
            calorieSeries.getData().add(new XYChart.Data(c.getFullDate(), c.getCalories()));
            managers.save();

            calorieDateText.setText("");
            totalCalorieText.setText("");
        }
    }

    @FXML
    public void waterEntry() {
        String date = waterDateText.getText();
        String water = totalWaterText.getText();
        int counter = 0;

        if (managers.validateDate(date, waterDateError)) {
            counter++;
        }
        if (managers.validateTextInt(water, 128, 0, totalWaterError, "Enter Value", "Cannot be less than 0", "Cannot be over 128")) {
            counter++;
        }

        if (counter == 2) {
            WaterEntry w = new WaterEntry(Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(0, 2)),
                    Integer.parseInt(date.substring(6)), Integer.parseInt(water));

            person.getWaterData().add(w);
            waterSeries.getData().add(new XYChart.Data(w.getFullDate(), w.getFluidOZ()));
            managers.save();

            waterDateText.setText("");
            totalWaterText.setText("");
        }
    }

    @FXML
    public void exerciseEntry() {
        String date = exerciseDateText.getText();
        String time = timeText.getText();

        int counter = 0;

        if (managers.validateDate(date, exerciseDateError)) {
            counter++;
        }
        if (managers.validateTextInt(time, 128, 0, timeError, "Enter Value", "Cannot be less than 0", "Cannot be over 128")) {
            counter++;
        }

        if (counter == 2) {
            ExerciseEntry e = new ExerciseEntry(Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(0, 2)),
                    Integer.parseInt(date.substring(6)), managers.getDouble(time));

            person.getExerciseData().add(e);
            exerciseSeries.getData().add(new XYChart.Data(e.getFullDate(), e.getTime()));
            managers.save();

            exerciseDateText.setText("");
            timeText.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calorieChart.getData().add(calorieSeries);
        waterChart.getData().add(waterSeries);
        exerciseChart.getData().add(exerciseSeries);
    }

}