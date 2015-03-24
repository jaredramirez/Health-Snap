package main;

import controllers.LoginWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Managers managers = new Managers();

        if (managers.isSerNull() == true) {
            managers.load();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.WHITE);

        LoginWindowController controller = fxmlLoader.getController();
        controller.setManagers(managers);
        controller.setPrevStage(stage);

        stage.setResizable(false);
        stage.setHeight(300);
        stage.setWidth(400);
        stage.setTitle("Health Snap");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
