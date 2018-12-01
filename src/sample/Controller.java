package sample;
import com.jfoenix.controls.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Controller{
    private File fileName = null;
    private byte[] characters;
    @FXML
    private StackPane OneStack;

    @FXML
    private StackPane stackPane;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    AnchorPane Mainlayout;

    @FXML
    private JFXTextArea encryptField;

    @FXML
    private JFXTextArea encryptOutput;

    @FXML
    private JFXTextField Encryptkey;

    @FXML
    private MediaView media;
    private static final String MEDIA_URL = "SampleVideo.mp4";
    private MediaPlayer mediaPlayer;

    public void initialize (URL location, ResourceBundle resources){
        System.out.println(location.toString());
        System.out.println(this.getClass().getResource(MEDIA_URL).toExternalForm());
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        media.setMediaPlayer(mediaPlayer);

    }

    public Controller() {
    }

    @FXML
    void Encrypt(MouseEvent event) {
        Encrypt.crypt(encryptField,encryptOutput,Encryptkey);
    }

    @FXML
    private JFXButton flChoose;

    @FXML
    private JFXButton flChoose1;

    @FXML
    void useee(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(new Stage());

    }

    @FXML
    void initialize() {
        assert Mainlayout != null : "fx:id=\"Mainlayout\" was not injected: check your FXML file 'sample.fxml'.";
        assert encryptField != null : "fx:id=\"encryptField\" was not injected: check your FXML file 'sample.fxml'.";
        assert encryptOutput != null : "fx:id=\"encryptOutput\" was not injected: check your FXML file 'sample.fxml'.";
        assert Encryptkey != null : "fx:id=\"Encryptkey\" was not injected: check your FXML file 'sample.fxml'.";

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
            encryptField.setText(new String(characters));

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
                output.write(encryptOutput.getText().getBytes());
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

        JFXDialog dialog = new JFXDialog(OneStack, dialogLayout, JFXDialog.DialogTransition.TOP);

        dialog.show();
        stackPane.setEffect(blur);

        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) ->{
            dialog.close();
        });

        dialog.setOnDialogClosed(event1 -> {
            stackPane.setEffect(null);
        });
    }

}












