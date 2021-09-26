package HuimangTech;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    public MainUIController() {
    }


    public static String getTaskName() {
        return TaskName;
    }


    static String TaskName = "";

    @FXML
    void CLOSE_APP() {
        App.primaryStage.close();
        System.exit(0);
    }

    @FXML
    void MINIMIZE_APP() {
        App.primaryStage.setIconified(true);
    }

    @FXML
    void switchToShutdown() throws IOException {
        TaskName = "ShutDown";
        App.setRoot("Timer");
    }

    @FXML
    void switchToRestart() throws IOException {
        TaskName = "Restart";
        App.setRoot("Timer");
    }

    @FXML
    void switchToHibernate() throws IOException {
        TaskName = "Hibernate";
        App.setRoot("Timer");
    }

    @FXML
    void switchToSleep() throws IOException {
        TaskName = "Sleep";
        App.setRoot("Timer");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
