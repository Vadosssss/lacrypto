package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.IOException;

public class MainController {

    @FXML
    private JFXButton toEncrypt;

    @FXML
    private JFXButton toDecrypt;

    @FXML
    private AnchorPane Mainlayout;

    @FXML
    public void startDecrp(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("second.fxml"));
        loader.setController(Main.secondController);
        loader.load();
        Mainlayout.getChildren().setAll(Main.secondController.Mainlayout.getChildren());
    }

    public void startEncr(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
       // System.out.println("hello world");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(Main.controller);
        loader.load();

        Mainlayout.getChildren().setAll(Main.controller.Mainlayout.getChildren());
    }

}
