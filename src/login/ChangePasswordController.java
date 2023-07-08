package login;

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

public class ChangePasswordController implements Initializable {

    private AccountList accountList = new AccountList();
    private OpenScene openScene = new OpenScene();

    @FXML
    private TextField usernameField, passwordField, confirmField;

    @FXML
    private ImageView landingImg;

    public void changePassword(ActionEvent event) throws IOException {
        boolean available = false;
        for (int i = 0; i < accountList.getAccountList().size(); i++) {
            if (usernameField.getText().equals(accountList.getAccount(i).getUsername())) {
                available = true;
                if (!passwordField.getText().equals("") && passwordField.getText().equals(confirmField.getText())) {
                    accountList.getAccount(i).setPassword(passwordField.getText());
                    accountList.saveAccountList();
                    PopUpAlert.information("Success", "successfully changed password");
                    openScene.getStage(event, "/login/LoginView.fxml");
                } else {
                    PopUpAlert.warning("Error", "Password field and confirm field doesn't match or blank.");
                }
            }
        }
        if (!available) {
            PopUpAlert.information("Account not Found", "Input username correctly.");
        }
    }

    public void backAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/LoginView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenImage.setImage(landingImg, "desain/forest.jpg");
    }

}
