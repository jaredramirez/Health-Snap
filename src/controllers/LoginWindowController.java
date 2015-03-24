package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Managers;
import objects.CalorieEntry;
import objects.ExerciseEntry;
import objects.Person;
import objects.WaterEntry;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jared Ramirez
 */
public class LoginWindowController implements Initializable {

    //Variables
    Managers managers = new Managers();
    Stage stage = new Stage();
    Stage prevStage = new Stage();
    String loginError = "Username or password not valid";
    XYChart.Series calorieSeries = new XYChart.Series();
    XYChart.Series waterSeries = new XYChart.Series();
    XYChart.Series exerciseSeries = new XYChart.Series();
    String[] quotes = new String[10];

    @FXML
    private Label bottomLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void setManagers(Managers m) {
        managers = m;
    }

    public Label getBottomLabel() {
        return bottomLabel;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void setPrevStage(Stage s) {
        prevStage = s;
    }

    @FXML
    public void login(ActionEvent event) {
        if (managers.getArraySize() != 0) {
            for (int i = 0; i < managers.getArraySize(); i++) {
                Person p = (Person) managers.getArrayList().get(i);

                if (p != null) {
                    if (p.getUsername().equals(username.getText()) && p.getPassword().equals(password.getText())) {
                        for (int j = 0; j < p.getCalorieData().size(); j++) {
                            CalorieEntry c = (CalorieEntry) p.getCalorieData().get(j);
                            calorieSeries.getData().add(new XYChart.Data(c.getFullDate(), c.getCalories()));
                        }

                        for (int j = 0; j < p.getWaterData().size(); j++) {
                            WaterEntry w = (WaterEntry) p.getWaterData().get(j);
                            waterSeries.getData().add(new XYChart.Data(w.getFullDate(), w.getFluidOZ()));
                        }

                        for (int j = 0; j < p.getExerciseData().size(); j++) {
                            ExerciseEntry e = (ExerciseEntry) p.getExerciseData().get(j);
                            exerciseSeries.getData().add(new XYChart.Data(e.getFullDate(), e.getTime()));
                        }
                        int num = (int) (Math.random() * 9);
                        prevStage.close();
                        refereshPage();
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            MainWindowController controller = fxmlLoader.getController();
                            controller.setManagers(managers);
                            controller.setStage(stage);
                            controller.setBMR(p);
                            controller.setGreeting(p);
                            controller.setPrevStage(prevStage);
                            controller.setUser(p);
                            controller.setSeries(calorieSeries, waterSeries, exerciseSeries);
                            controller.setQuote(quotes[num]);

                            stage.setTitle("Health Snap Main");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());

                        }
                    } else {
                        errorMessage(bottomLabel, loginError);
                    }
                } else {
                    errorMessage(bottomLabel, loginError);
                }
            }
        } else {
            errorMessage(bottomLabel, loginError);
        }
    }

    @FXML
    private void newUser() {
        prevStage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreateWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            CreateWindowController controller = fxmlLoader.getController();
            controller.setManagers(managers);
            controller.setStage(stage);
            controller.setPrevStage(prevStage);

            stage.setTitle("Sign up!");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void refereshPage() {
        bottomLabel.setText("");
        username.setText("");
        password.setText("");
    }

    public void errorMessage(Label label, String msg) {
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.RED);
        label.setText(msg);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quotes[0] = "When making a salad alway include extra ranch";
        quotes[1] = "Sleep with the window open, you'll be colder and burn more calories";
        quotes[2] = "If you want to lose weight, you have to exercise";
        quotes[3] = "No pain, no gain";
        quotes[4] = "You gotta carbo-load, Bro!";
        quotes[5] = "Water aerobics is the way to go";
        quotes[6] = "The best time to work out is the morning, afternoon, or night";
        quotes[7] = "If you don't workout, you don't deserve to be thin";
        quotes[8] = "Never eat again";
        quotes[9] = "LIPOSUCTION";
    }

}
