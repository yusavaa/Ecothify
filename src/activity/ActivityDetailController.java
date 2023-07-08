package activity;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import mainMenu.MainMenuController;
import model.AccountList;
import model.ActivityList;
import model.Member;
import model.NoteList;
import util.PopUpAlert;

public class ActivityDetailController implements Initializable {

    private ActivityList activityList = new ActivityList();
    private AccountList accountList = new AccountList();
    private NoteList noteList = new NoteList();
    private ObservableList<Member> list = FXCollections.observableArrayList();
    private static int activityTarget;

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> nameColumn;

    @FXML
    private TableColumn<Member, String> cityColumn;

    @FXML
    private Label tittleLabel, locLabel, dateLabel, slotLabel, descLabel;

    @FXML
    private ImageView thumbnail;

    public static int getActivityTarget() {
        return activityTarget;
    }

    public static void setActivityTarget(int index) {
        activityTarget = index;
    }

    public void setLabel() {
        tittleLabel.setText("Name: " + activityList.getActivity(activityTarget).getName());
        descLabel.setText("Desc: " + activityList.getActivity(activityTarget).getDescription());
        locLabel.setText("Location: " + activityList.getActivity(activityTarget).getLocation());
        dateLabel.setText("Date: " + activityList.getActivity(activityTarget).getDate());
        slotLabel.setText(
                "Max participant: " + String.valueOf(activityList.getActivity(activityTarget).getParticipantLenght()));
    }

    public void setImage() {
        Path path = Paths.get(activityList.getActivity(activityTarget).getImgPath());
        try {
            BufferedImage bufferedImage = ImageIO.read(path.toFile());
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            thumbnail.setImage(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void joinActivity(ActionEvent event) {
        int slot = activityList.getActivity(activityTarget).getTotalParticipant();
        if (!existParticipant()) {
            if (slot < activityList.getActivity(activityTarget).getParticipantLenght()) {
                activityList.getActivity(activityTarget).addParticipant(slot, AccountList.getLoginAccount());
                activityList.saveActivityList();
                noteList.addNewNote(
                        AccountList.getLoginAccount().getUsername(),
                        activityList.getActivity(activityTarget).getName(),
                        LocalDate.parse(activityList.getActivity(activityTarget).getDate(),
                                DateTimeFormatter.ofPattern("MMM-dd-yyy")),
                        activityTarget);
                addPoint();
                PopUpAlert.information("Success", "Thank you for participating.");
            } else {
                PopUpAlert.information("Can't Join", "Participant slots are fully filled.");
            }
        } else {
            PopUpAlert.information("Can't Join", "You have joined this activity.");
        }
        MainMenuController.getInstance().showActivityDetail();
    }

    public boolean existParticipant() {
        for (int i = 0; i < activityList.getActivity(activityTarget).getParticipantLenght(); i++) {
            if (activityList.getActivity(activityTarget).getParticipant(i) != null) {
                if (activityList.getActivity(activityTarget).getParticipant(i).getName()
                        .equals(AccountList.getLoginAccount().getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addPoint() {
        if (AccountList.getLoginAccount() != null) {
            for (int i = 0; i < accountList.getAccountList().size(); i++) {
                if (accountList.getAccount(i).getUsername().equals(AccountList.getLoginAccount().getUsername())) {
                    accountList.getAccount(i).setPoint(accountList.getAccount(i).getPoint() + 10);
                    accountList.saveAccountList();
                    AccountList.setLoginAccount(accountList.getAccount(i));
                }
            }
        }
    }

    public void participantList() {
        for (int i = 0; i < activityList.getActivity(activityTarget).getParticipantLenght(); i++) {
            if (activityList.getActivity(activityTarget).getParticipant(i) != null) {
                list.add(activityList.getActivity(activityTarget).getParticipant(i));
            }
        }
    }

    public void setTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("city"));
        tableView.setItems(list);
    }

    public void cancelAction() {
        MainMenuController.getInstance().showActivityAction();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
        setImage();
        participantList();
        setTable();
    }
}
