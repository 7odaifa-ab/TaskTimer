package HuimangTech;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    String enablerPATH = "C:\\Program Files (x86)\\TaskTimer\\ExternalCommands\\TaskTimer-HibernationTask.exe";

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
            String message = "Hibernation is ENABLED and must be DISABLED to use the Sleep Timer, continue to Disable it?\n(Require admin permission)";
            if (showAlert(message)) {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", enablerPATH, "disable");
                builder.start();
            }
        } else {
            App.setRoot("Timer");
        }
    }

    void HibernationSwitch() throws IOException {
        if (!isHibernationOn()) {
            String message = "Hibernation is DISABLED and must be ENABLED to use the Hibernation Timer, continue to Enable it?\n(Require admin permission)";
            if (showAlert(message)) {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", enablerPATH, "enable");
                builder.start();
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

