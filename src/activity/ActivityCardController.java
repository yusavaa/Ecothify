package activity;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import mainMenu.MainMenuController;
import model.AccountList;
import model.ActivityList;
import util.OpenImage;
import util.PopUpAlert;

public class ActivityCardController {

    @FXML
    private VBox buttonLayout, aproveLayout, declineLayout;

    @FXML
    private Label tittleLabel, locLabel, dateLabel, slotLabel, descLabel;

    @FXML
    private ImageView thumbnail;

    public void setLabel(String tittle, String date, String desc, String location, String slot) {
        tittleLabel.setText("Name: " + tittle);
        dateLabel.setText("Date: " + date);
        descLabel.setText("Desc: " + desc);
        locLabel.setText("Location: " + location);
        slotLabel.setText("Max participant: " + slot);
    }

    public void setImage(String imgPath) {
        OpenImage.setImage(thumbnail, imgPath);
    }

    public void addJoinButton(int index, ActivityList activityList) {
        Button button = new Button("Join");
        button.setId(Integer.toString(index));
        button.setPrefWidth(45);
        button.setOnAction(e -> {
            try {
                checkLogin(e);
                for (int i = 0; i < activityList.getActivityList().size(); i++) {
                    if (((Button) e.getSource()).getId().equals(Integer.toString(activityList.getActivityIndex(i)))) {
                        ActivityDetailController.setActivityTarget(index);
                        MainMenuController.getInstance().showActivityDetail();
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        buttonLayout.getChildren().add(button);
    }

    public void addEditButton(int index, ActivityList activityList) {
        Button button = new Button("Edit");
        button.setId(Integer.toString(index));
        button.setPrefWidth(45);
        button.setOnAction(e -> {
            try {
                checkLogin(e);
                for (int i = 0; i < activityList.getActivityList().size(); i++) {
                    if (((Button) e.getSource()).getId().equals(Integer.toString(activityList.getActivityIndex(i)))) {
                        EditActivityController.setActivityTarget(index);
                        MainMenuController.getInstance().showEditAction();
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        buttonLayout.getChildren().add(button);
        VBox.setMargin(button, new Insets(20, 0, 0, 0));
    }

    public void addVerifyButton(int index, ActivityList activityList) {
        Button aproveButton = new Button("Aprove");
        aproveButton.setId(Integer.toString(index));
        aproveButton.setOnAction(e -> {
            for (int i = 0; i < activityList.getActivityList().size(); i++) {
                if (((Button) e.getSource()).getId().equals(Integer.toString(activityList.getActivityIndex(i)))) {
                    activityList.getActivity(i).setValidated(true);
                    activityList.saveActivityList();
                    MainMenuController.getInstance().verifyAction();
                }
            }
        });
        aproveLayout.getChildren().add(aproveButton);

        Button declineButton = new Button("Decline");
        declineButton.setId(Integer.toString(index));
        declineButton.setOnAction(e -> {
            for (int i = 0; i < activityList.getActivityList().size(); i++) {
                if (((Button) e.getSource()).getId().equals(Integer.toString(activityList.getActivityIndex(i)))) {
                    activityList.removeActivity(i);
                    activityList.saveActivityList();
                    MainMenuController.getInstance().verifyAction();
                }
            }
        });
        declineLayout.getChildren().add(declineButton);
    }

    public void checkLogin(ActionEvent event) throws IOException {
        if (AccountList.getLoginAccount() == null) {
            PopUpAlert.information("Guest", "Untuk bergabung dalam kegiatan, silakan login terlebih dahulu.");
            MainMenuController.getInstance().loginAction(event);
        }
    }

}
