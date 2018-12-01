package sample;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;

public class SecondController {
    private File fileName = null;
    private byte[] characters;

    @FXML
    private StackPane TwoStack;

    @FXML
    private StackPane stackPane2;

    @FXML
    AnchorPane Mainlayout;

    @FXML
    private JFXTextField decrKey;

    @FXML
    private JFXTextArea inDecrypt;

    @FXML
    private JFXTextArea outDecrypt;

    @FXML
    private JFXButton flChoose2;

    @FXML
    private JFXButton flChoose3;

    @FXML
    private JFXButton Decr;
    @FXML
    void Decrypt(MouseEvent event) {
        Decrypt.decrypt(inDecrypt,outDecrypt,decrKey);
    }

    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("DOC", "*.doc"),
                new FileChooser.ExtensionFilter("DOCX", "*.docx")
        );
    }

    @FXML
    void choose_file(MouseEvent event) throws IOException {

        Stage stage = (Stage)Main.stage.getScene().getWindow();

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File To Encrypt");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")  + "/Documents"));


        setExtFilters(fileChooser);
        fileName = fileChooser.showOpenDialog(stage);
        if (fileName != null) {
            BufferedInputStream read = new BufferedInputStream(new FileInputStream(fileName));
            characters = new byte[read.available()];
            read.read(characters);
            inDecrypt.setText(new String(characters));

        }

    }
    @FXML
    void choose__safeDirectory(MouseEvent event) throws IOException{
//        Stage stage = (Stage)Controller.stage.getScene().getWindow();
        Stage stage = (Stage)Main.stage.getScene().getWindow();


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose directory to safe file");

        fileChooser.setInitialFileName("SampleFileName.txt");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")  + "/Documents"));
        setExtFilters(fileChooser);

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            //System.out.println(file.getAbsolutePath());
            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()))) {
                output.write(outDecrypt.getText().getBytes());
            }
        }
    }
    void createMessageDialog(String headText, String bodyText, String buttonText){
        BoxBlur blur = new BoxBlur(5, 5, 1);

        JFXButton okButton = new JFXButton(buttonText);
        okButton.getStyleClass().add("okButton");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new javafx.scene.control.Label(headText));
        dialogLayout.setBody(new Label(bodyText));

        dialogLayout.setActions(okButton);

        JFXDialog dialog = new JFXDialog(TwoStack, dialogLayout, JFXDialog.DialogTransition.TOP);

        dialog.show();
        stackPane2.setEffect(blur);

        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) ->{
            dialog.close();
        });

        dialog.setOnDialogClosed(event1 -> {
            stackPane2.setEffect(null);
        });
    }
}



