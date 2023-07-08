package schedule;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import activity.ActivityDetailController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import mainMenu.MainMenuController;
import model.AccountList;
import model.NoteList;

public class ScheduleController implements Initializable {

    private NoteList noteList = new NoteList();
    private ZonedDateTime dateFocus;
    private ZonedDateTime today;

    @FXML
    private FlowPane calendarLayout;

    @FXML
    private Label month, year;

    public void toCurrentDate() throws IOException {
        dateFocus = ZonedDateTime.now();
        calendarLayout.getChildren().clear();
        showCalendar();
    }

    public void backAction() throws IOException {
        dateFocus = dateFocus.minusMonths(1);
        calendarLayout.getChildren().clear();
        showCalendar();
    }

    public void forwardAction() throws IOException {
        dateFocus = dateFocus.plusMonths(1);
        calendarLayout.getChildren().clear();
        showCalendar();
    }

    public void showCalendar() throws IOException {
        month.setText(String.valueOf(dateFocus.getMonth()));
        year.setText(String.valueOf(dateFocus.getYear()));

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(
                dateFocus.getYear(),
                dateFocus.getMonthValue(),
                1, 0, 0, 0, 0,
                dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                DropShadow dropShadow = new DropShadow();

                dropShadow.setOffsetY(3.0);
                dropShadow.setOffsetX(3.0);
                dropShadow.setColor(Color.rgb(0, 0, 0, 0.2));

                rectangle.setFill(Color.web("#ffffff"));
                rectangle.setStroke(Color.TRANSPARENT);
                rectangle.setStrokeWidth(2);
                rectangle.setArcWidth(15);
                rectangle.setArcHeight(15);
                rectangle.setWidth(105);
                rectangle.setHeight(62);
                rectangle.setEffect(dropShadow);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        date.setTranslateX(-40);
                        date.setTranslateY(-20);
                        stackPane.getChildren().add(date);
                        stackPane.getChildren().add(addMark(dateFocus.getYear(), dateFocus.getMonth(), currentDate));

                        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth()
                                && today.getDayOfMonth() == currentDate) {
                            rectangle.setStroke(Color.web("#EA5455"));
                        } else {
                            stackPane.setOnMouseEntered(e -> {
                                rectangle.setStroke(Color.web("#068FFF"));
                            });
                            stackPane.setOnMouseExited(e -> {
                                rectangle.setStroke(Color.TRANSPARENT);
                            });
                        }
                    } else {
                        rectangle.setFill(Color.web("#fffef6"));
                        rectangle.setEffect(null);
                    }
                } else {
                    rectangle.setFill(Color.web("#fffef6"));
                    rectangle.setEffect(null);
                }
                calendarLayout.getChildren().add(stackPane);
            }
        }
    }

    public VBox addMark(int year, Month month, int date) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/schedule/MarkView.fxml"));
        VBox mark = fxmlLoader.load();
        MarkController markController = fxmlLoader.getController();

        if (AccountList.getLoginAccount() != null) {
            for (int i = 0; i < noteList.getNoteList().size(); i++) {
                if (noteList.getNote(i).getUsername().equals(AccountList.getLoginAccount().getUsername())
                        && noteList.getNote(i).getDate().getMonth().equals(month)
                        && noteList.getNote(i).getDate().getDayOfMonth() == date
                        && noteList.getNote(i).getDate().getYear() == year) {
                    markController.setLabel(noteList.getNote(i).getTittle());

                    final int TARGET = i;

                    mark.setOnMouseClicked(e -> {
                        ActivityDetailController.setActivityTarget(noteList.getNote(TARGET).getTargetIndex());
                        MainMenuController.getInstance().showActivityDetail();
                    });
                    return mark;
                }
            }
        }
        return new VBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dateFocus = ZonedDateTime.now();
            today = ZonedDateTime.now();
            showCalendar();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
