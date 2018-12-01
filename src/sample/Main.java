package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;

    public static Controller controller = new Controller();
    public static SecondController secondController = new SecondController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Laurel - XOR decrypt program");
        stage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}