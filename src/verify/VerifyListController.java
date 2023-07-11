package verify;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import activity.ActivityCardController;
import model.AccountList;
import model.ActivityList;

public class VerifyListController implements Initializable {

    private ActivityList activityList = new ActivityList();
    private int verified = 0;
    private int unverified = 0;

    @FXML
    private VBox cardLayout;

    @FXML
    private Label totalLabel, verifiedLabel, unverifiedLabel;

    // Make card from each activity in the database
    public void showActivity(int index, String purpose) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/activity/ActivityCardView.fxml"));
        HBox cardBox = fxmlLoader.load();
        ActivityCardController cardController = fxmlLoader.getController();
        cardController.setLabel(
                activityList.getActivity(index).getName(),
                activityList.getActivity(index).getDate(),
                activityList.getActivity(index).getDescription(),
                activityList.getActivity(index).getLocation(),
                String.valueOf(activityList.getActivity(index).getParticipantLenght()));
        cardController.setImage(activityList.getActivity(index).getImgPath());
        if (purpose.equals("join")) {
            cardController.addJoinButton(index, activityList);
            if (AccountList.getLoginAccount() != null) {
                if (AccountList.getLoginAccount().getUsername().equals(activityList.getActivity(index).getCreator())
                        || AccountList.getLoginAccount().getUsername().equals("admin")) {
                    cardController.addEditButton(index, activityList);
                }
            }
        } else {
            cardController.addVerifyButton(index, activityList);
        }
        cardLayout.getChildren().add(cardBox);
    }

    // Display activity card in "Activity and Verify"
    public void showList(boolean validated, String purpose) {
        try {
            for (int i = 0; i < activityList.getActivityList().size(); i++) {
                if (activityList.getActivity(i).isValidated() == validated) {
                    showActivity(i, purpose);
                    unverified++;
                } else {
                    verified++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSummary() {
        totalLabel.setText(Integer.toString(activityList.getActivityList().size()) + " Activity");
        verifiedLabel.setText(Integer.toString(verified) + " Activity");
        unverifiedLabel.setText(Integer.toString(unverified) + " Activity");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showList(false, "verify");
        showSummary();
    }

}
