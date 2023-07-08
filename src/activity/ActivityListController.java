package activity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import mainMenu.MainMenuController;
import model.AccountList;
import util.PopUpAlert;
import verify.VerifyListController;

public class ActivityListController extends VerifyListController {

    @FXML
    private VBox cardLayout;

    public void makeActivity(ActionEvent event) throws IOException {
        if (AccountList.getLoginAccount() != null) {
            MainMenuController.getInstance().addActivityAction();
        } else {
            PopUpAlert.information("Guest", "Untuk membuat kegiatan, silakan login terlebih dahulu.");
            MainMenuController.getInstance().loginAction(event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            showList(true, "join");
    }

}
