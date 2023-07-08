package mainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import activity.ActivityCardController;
import model.AccountList;
import model.ActivityList;
import util.OpenScene;

public class MainMenuController implements Initializable {

    private OpenScene openScene = new OpenScene();
    private ActivityList activity = new ActivityList();
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

    public void showActivityAction() {
        mainPane.setCenter(openScene.getPane("/activity/ActivityListView.fxml"));
    }

    public void loginAction(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/LoginView.fxml");
    }

    public void logoutAction(ActionEvent event) throws IOException {
        AccountList.setLoginAccount(null);
        openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
    }

    public void showActivity(int index) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/activity/ActivityCardView.fxml"));
        HBox cardBox = fxmlLoader.load();
        ActivityCardController cardController = fxmlLoader.getController();
        cardController.setLabel(
                activity.getActivity(index).getName(),
                activity.getActivity(index).getDate(),
                activity.getActivity(index).getDescription(),
                activity.getActivity(index).getLocation(),
                String.valueOf(activity.getActivity(index).getParticipantLenght()));
        cardLayout.getChildren().add(cardBox);
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
