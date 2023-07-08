package util;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class PopUpAlert {
    
    @FXML
    public static void information(String tittle, String description) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(tittle);
        alert.setContentText(description);
        alert.showAndWait();
    }

    @FXML
    public static void warning(String tittle, String description) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText(tittle);
        alert.setContentText(description);
        alert.showAndWait();
    }

    @FXML
    public static void confirmation(String tittle, String description, Consumer<Object> action) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(tittle);
        alert.setContentText(description);
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            action.accept(null);
        }
    }

}
