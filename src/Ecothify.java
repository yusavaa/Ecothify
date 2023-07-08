import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ecothify extends Application {

    /*
     * ECOTHIFY by Big Brain
     * 
     * 22523173 Fath Yusava Arden (Ketua)
     * 22523060 Adin Abimanyu
     * 22523169 Muhammad Rafi Cantas Wicaksana
     * 22523285 Naufal Rafi Hidayat
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
