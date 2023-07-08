package activity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import mainMenu.MainMenuController;
import model.NoteList;
import util.OpenImage;
import util.PopUpAlert;

public class EditActivityController extends AddActivityController {

    private NoteList noteList = new NoteList();
    private static int activityTarget;

    public static int getActivityTarget() {
        return activityTarget;
    }

    public static void setActivityTarget(int index) {
        activityTarget = index;
    }

    public void setDetail() {
        OpenImage.setImage(thumbnail, activityList.getActivity(activityTarget).getImgPath());
        nameField.setText(activityList.getActivity(activityTarget).getName());
        locField.setValue(activityList.getActivity(activityTarget).getLocation());
        descField.setText(activityList.getActivity(activityTarget).getDescription());
        capacityField.setText(Integer.toString(activityList.getActivity(activityTarget).getParticipantLenght()));
        dateField.setValue(
                LocalDate.parse(activityList.getActivity(activityTarget).getDate(),
                        DateTimeFormatter.ofPattern("MMM-dd-yyy")));
    }

    public void saveAction(ActionEvent event) throws IOException {
        if (nameField.getText().equals("")
                || descField.getText().equals("")
                || locField.getValue() == null
                || dateField.getValue() == null
                || capacityField.getText().equals("")) {

            PopUpAlert.warning("Empety Forms", "Make sure all forms are filled out.");

        } else {
            activityList.getActivity(activityTarget).setName(nameField.getText());
            activityList.getActivity(activityTarget).setDescription(descField.getText());
            activityList.getActivity(activityTarget).setLocation(locField.getValue());
            activityList.getActivity(activityTarget).setDate(dateField.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")).toString());
            activityList.getActivity(activityTarget).setImgPath(activityList.getActivity(activityTarget).getImgPath());
            activityList.getActivity(activityTarget).setValidated(false);

            activityList.saveActivityList();
            PopUpAlert.information("Waiting for verification", "Data is being verified by admins.");
            openScene.getStage(event, "/mainMenu/MainMenuView.fxml");
            MainMenuController.getInstance().showActivityAction();
        }
    }

    public void deleteActivity() {
        activityList.removeActivity(activityTarget);
        activityList.saveActivityList();
        for (int i = 0; i < noteList.getNoteList().size(); i++) {
            if (noteList.getNote(i).getTargetIndex() == ActivityDetailController.getActivityTarget()) {
                noteList.removeNote(i);
                noteList.saveNoteList();
            }
        }
        MainMenuController.getInstance().showActivityAction();
    }

    public void deleteAction(ActionEvent event) {
        Consumer<Object> delete = e -> deleteActivity();
        PopUpAlert.confirmation("Confirm", "Are you sure to delete this activity", delete);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        setDetail();
    }

}
