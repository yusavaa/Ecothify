package register;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import model.AccountList;
import util.OpenImage;
import util.OpenScene;
import util.PopUpAlert;

public class RegisterController implements Initializable {

    private OpenScene openScene = new OpenScene();
    private AccountList accountList = new AccountList();

    @FXML
    private TextField nameField, cityField, passwordField, usernameField;

    @FXML
    private ImageView landingImg;

    public void goToLogin(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/LoginView.fxml");
    }

    public void createAccountAction(ActionEvent event) throws IOException {
        if (nameField.getText().equals("")
                || cityField.getText().equals("")
                || usernameField.getText().equals("")
                || passwordField.getText().equals("")) {

            PopUpAlert.warning("Empety Forms", "Make sure all forms are filled out.");
        } else {
            accountList.addNewAccount(
                    nameField.getText(),
                    cityField.getText(),
                    usernameField.getText(),
                    passwordField.getText());
            PopUpAlert.information("Welcome to ECOTHIFY", "Login to continue.");
            openScene.getStage(event, "/login/LoginView.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenImage.setImage(landingImg, "desain/forest.jpg");
    }

}
