package HuimangTech;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        HibernationSwitch();

    }

    @FXML
    void switchToSleep() throws IOException {
        TaskName = "Sleep";
        sleepSwitch();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void sleepSwitch() throws IOException {
        if (isHibernationOn()) {
            String message = "Hibernation is ON and must be turned OFF to use the sleep mode, continue to turn it off ?";
            if (showAlert(message)) {
                //turn off hibernation in admin mode
            }
        } else {
            App.setRoot("Timer");
        }
    }

    void HibernationSwitch() throws IOException {
        if (!isHibernationOn()) {
            String message = "Hibernation is OFF and must be turned ON to use this mode, continue to turn it on ?";
            if (showAlert(message)) {
                //turn on hibernation in admin mode
            }
        } else {
            App.setRoot("Timer");
        }
    }

    private boolean showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }

    Boolean isHibernationOn() {
        String line;
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "powercfg -availablesleepstates");
            builder.redirectErrorStream(true);
            builder.start();

            Process p = builder.start();
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("Hibernation has not been enabled.")) {
                    return false;
                }
            }
            int r = p.waitFor(); // Let the process finish.
            if (r == 0) { // No error
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

