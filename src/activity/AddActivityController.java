package activity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import mainMenu.MainMenuController;
import model.AccountList;
import model.ActivityList;
import util.OpenImage;
import util.OpenScene;
import util.PopUpAlert;

public class AddActivityController implements Initializable {

    protected OpenScene openScene = new OpenScene();
    protected ActivityList activityList = new ActivityList();
    protected String imgPath = "desain/noImg.jpg";
    protected String[] locations = { "Bantul", "Gunung Kidul", "Kulon Progo", "Sleman", "Yogyakarta" };

    @FXML
    protected TextArea descField;

    @FXML
    protected TextField nameField, capacityField;

    @FXML
    protected ChoiceBox<String> locField;

    @FXML
    protected DatePicker dateField;

    @FXML
    protected ImageView thumbnail;

    public void makeEventAction(ActionEvent event) throws IOException {
        if (nameField.getText().equals("")
                || descField.getText().equals("")
                || locField.getValue() == null
                || dateField.getValue() == null
                || capacityField.getText().equals("")) {

            PopUpAlert.warning("Empety Forms", "Make sure all forms are filled out.");

        } else {
            activityList.addNewActivity(
                    AccountList.getLoginAccount().getUsername(),
                    nameField.getText(),
                    descField.getText(),
                    locField.getValue(),
                    dateField.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")).toString(),
                    imgPath,
                    Integer.parseInt(capacityField.getText()));
            PopUpAlert.information("Waiting for verification", "Data is being verified by admins.");
            openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
            MainMenuController.getInstance().showActivityAction();
        }
    }

    public void cancelAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
        MainMenuController.getInstance().showActivityAction();
    }

    public void fileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*.png"),
                new FileChooser.ExtensionFilter("All images", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        imgPath = selectedFile.getAbsolutePath();
        OpenImage.setImage(thumbnail, imgPath);
    }

    public void disablePostDate() {
        dateField.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenImage.setImage(thumbnail, "desain/noImg.jpg");
        disablePostDate();
        locField.getItems().addAll(locations);
    }

}
