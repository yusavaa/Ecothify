package mainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import model.AccountList;
import util.OpenScene;

public class MainMenuController implements Initializable {

    private OpenScene openScene = new OpenScene();
    private static MainMenuController instance;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox cardLayout;

    @FXML
    private Button loginButton, logoutButton, verifyButton;

    @FXML
    private Label welcomeLabel;

    public MainMenuController() {
        instance = this;
    }

    public static MainMenuController getInstance() {
        return instance;
    }

    public void dashboardAction() {
        mainPane.setCenter(openScene.getPane("/dashboard/DashboardView.fxml"));
    }

    public void showActivityAction() {
        mainPane.setCenter(openScene.getPane("/activity/ActivityListView.fxml"));
    }

    public void addActivityAction() {
        mainPane.setCenter(openScene.getPane("/activity/AddActivityView.fxml"));
    }

    public void showActivityDetail() {
        mainPane.setCenter(openScene.getPane("/activity/ActivityDetailView.fxml"));
    }

    public void showEditAction() {
        mainPane.setCenter(openScene.getPane("/activity/EditActivityView.fxml"));
    }

    public void scheduleAction() {
        mainPane.setCenter(openScene.getPane("/schedule/ScheduleView.fxml"));
    }

    public void verifyAction() {
        mainPane.setCenter(openScene.getPane("/verify/VerifyListView.fxml"));
    } 

    public void loginAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/LoginView.fxml");
    }

    public void logoutAction(ActionEvent event) throws IOException {
        AccountList.setLoginAccount(null);
        openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
    }

    public void checkLogin() {
        loginButton.setVisible(true);
        logoutButton.setTranslateX(57);
        welcomeLabel.setVisible(false);
        verifyButton.setVisible(false);
        if (AccountList.getLoginAccount() != null) {
            loginButton.setVisible(false);
            welcomeLabel.setVisible(true);
            welcomeLabel.setText(welcomeLabel.getText() + AccountList.getLoginAccount().getName());
            if (AccountList.getLoginAccount().getROLE().equals("admin")) {
                verifyButton.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkLogin();
        dashboardAction();
    }

}
