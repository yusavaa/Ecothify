package util;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpenScene {
    
    private Pane pane;

    public Pane getPane(String fileName) {
        try {
            URL file = getClass().getResource(fileName);
            if (file == null) {
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }
            pane = FXMLLoader.load(file);
        } catch (Exception e) {
            System.out.println("Tidak ditemukan halaman tersebut");
        }
        return pane;
    }

    public void getStage(ActionEvent event, String fileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
