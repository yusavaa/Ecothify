package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import model.AccountList;
import util.OpenImage;
import util.OpenScene;
import util.PopUpAlert;

public class LoginController implements Initializable {

    private OpenScene openScene = new OpenScene();
    private AccountList accountList = new AccountList();

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView landingImg;

    @FXML
    private TextField usernameField;

    public void changePasswordAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/ChangePasswordView.fxml");
    }

    public void registerAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/register/RegisterView.fxml");
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
    }

    public void logInAction(ActionEvent event) throws IOException {
        boolean isAvailable = false;
        for (int i = 0; i < accountList.getAccountList().size(); i++) {
            if (accountList.getAccount(i).getUsername().equals(usernameField.getText())
                    && accountList.getAccount(i).getPassword().equals(passwordField.getText())) {
                AccountList.setLoginAccount(accountList.getAccount(i));
                isAvailable = true;
                PopUpAlert.information("Login Success", "Welcome to ECOTHIFY");
                goToDashboard(event);
                break;
            }
        }
        if (!isAvailable) {
            PopUpAlert.information("Can't Login", "Incorrect Username or Password");
        }
        usernameField.setText("");
        passwordField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenImage.setImage(landingImg, "desain/forest.jpg");
    }

}
