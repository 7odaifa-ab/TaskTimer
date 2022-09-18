package HuimangTech;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    String enablerPATH = System.getenv("APPDATA") + "\\TaskTimer\\ExternalCommands\\TaskTimer-HibernationTask.exe";
    ResourceBundle TaskTimer_lang_bundle = ResourceBundle.getBundle("resources/bundles/TaskTimer_en_US");

    public MainUIController() {
    }


    public static String getTaskName() {
        return TaskName;
    }


    static String TaskName = "";

    @FXML
    void CLOSE_APP() {
        App.primaryStage.close();
        Platform.exit();
        System.exit(0);    }

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

    @FXML
    void switchToCommand() throws IOException {
        TaskName = "Custom Command";
        App.setRoot("CustomCommand");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void sleepSwitch() throws IOException {
        if (isHibernationOn()) {
            String message = TaskTimer_lang_bundle.getString("sleepWarning");
            if (showAlert(message)) {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", enablerPATH, "disable");
                builder.start();
                App.setRoot("Timer");
            }
        } else {
            App.setRoot("Timer");
        }
    }

    void HibernationSwitch() throws IOException {
        if (!isHibernationOn()) {
            String message = TaskTimer_lang_bundle.getString("hibernationWarning");
            if (showAlert(message)) {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", enablerPATH, "enable");
                builder.start();
                App.setRoot("Timer");
            }
        } else {
            App.setRoot("Timer");
        }
    }

    private boolean showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.YES, ButtonType.CANCEL);
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

