package schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MarkController {

    @FXML
    private Label tittleLabel;

    public void setLabel(String tittle) {
        tittleLabel.setText(tittle);
    }

}
