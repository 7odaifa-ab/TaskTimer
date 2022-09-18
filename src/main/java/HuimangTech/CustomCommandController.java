package HuimangTech;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomCommandController implements Initializable {
    private int STARTTIME = 0;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private Timeline timeline;
    private final ChangeListener<String> listener = ((observable, oldValue, newValue) -> ResetTimer());


    @FXML
    private JFXButton StartBtn;
    @FXML
    private JFXButton pauseBtn;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private TextField HoursTxtField;
    @FXML
    private TextField CommandTxtField;
    @FXML
    private TextField MinTxtField;
    @FXML
    private TextField SecTxtField;
    @FXML
    private Label taskLbl;

    @FXML
    void StartBtn() {
        if (timeline != null) {
            checkTimerStatus(); //pause if it was running and vise versa
            return;
        }

        StartTimer();
        StartBtn.setVisible(false);
        resetBtn.setVisible(true);
        pauseBtn.setVisible(true);
        bindTimeToTextField();
        setOnFishedCommand();

    }

    @FXML
    void resetBtn() throws IOException {
        ResetTimer();
        App.setRoot("CustomCommand");
    }

    @FXML
    void CLOSE_APP() {
        App.primaryStage.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void BACK() throws IOException {
        App.setRoot("MainUI");
    }

    @FXML
    void MINIMIZE_APP() {
        App.primaryStage.setIconified(true);
    }

    void checkTimerStatus() {
        if (timeline.getStatus().toString().equals("RUNNING")) {
            timeline.pause();
            pauseBtn.setText("Start");
            unBindTimeToTextField();
            RestoreStyleClass();

        } else if (timeline.getStatus().toString().equals("PAUSED")) {
            bindTimeToTextField();
            removeStyleClass();
            timeline.play();
            pauseBtn.setText("Pause");
        }
    }

    private Integer TimerTimeInSeconds() {
        int HoursToSec = 0;
        int MinToSec = 0;
        int SecToSec = 0;

        if (!HoursTxtField.getText().isEmpty())
            HoursToSec = (Integer.parseInt(HoursTxtField.getText()) * 60 * 60);
        if (!MinTxtField.getText().isEmpty())
            MinToSec = (Integer.parseInt(MinTxtField.getText()) * 60);
        if (!SecTxtField.getText().isEmpty())
            SecToSec = Integer.parseInt(SecTxtField.getText());

        HoursTxtField.setEditable(false);
        MinTxtField.setEditable(false);
        SecTxtField.setEditable(false);

        return HoursToSec + MinToSec + SecToSec;
    }

    void StartTimer() {
        STARTTIME = TimerTimeInSeconds();

        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    void ResetTimer() {
        timeline = null;
    }

    void bindTimeToTextField() {
        removeChangeListener();
        removeStyleClass();

        // Bind the Timer TextFields text property to the Timer property
        HoursTxtField.textProperty().bind(timeSeconds.divide(3600).asString());
        MinTxtField.textProperty().bind(timeSeconds.divide(60).subtract(timeSeconds.divide(3600).multiply(60)).asString());
        SecTxtField.textProperty().bind(timeSeconds.subtract(timeSeconds.divide(60).multiply(60)).asString());
    }

    void unBindTimeToTextField() {
        // Bind the Timer TextFields text property to the timeSeconds property
        HoursTxtField.textProperty().unbind();
        MinTxtField.textProperty().unbind();
        SecTxtField.textProperty().unbind();

        HoursTxtField.setEditable(true);
        MinTxtField.setEditable(true);
        SecTxtField.setEditable(true);

        addChangeListener();
    }

    void addChangeListener() {
        //allow to change when paused
        HoursTxtField.textProperty().addListener(listener);
        MinTxtField.textProperty().addListener(listener);
        SecTxtField.textProperty().addListener(listener);
        CommandTxtField.textProperty().addListener(listener);
    }

    void removeStyleClass() {
        HoursTxtField.getStyleClass().clear();
        MinTxtField.getStyleClass().clear();
        SecTxtField.getStyleClass().clear();
        CommandTxtField.getStyleClass().clear();
    }

    void RestoreStyleClass() {
        HoursTxtField.getStyleClass().add("editableText-field");
        MinTxtField.getStyleClass().add("editableText-field");
        SecTxtField.getStyleClass().add("editableText-field");
        CommandTxtField.getStyleClass().add("editableText-field");
    }

    void removeChangeListener() {
        //remove due to conflict of timer updating count as change
        HoursTxtField.textProperty().removeListener(listener);
        MinTxtField.textProperty().removeListener(listener);
        SecTxtField.textProperty().removeListener(listener);
        CommandTxtField.textProperty().removeListener(listener);
    }

    void setOnFishedCommand() {
        timeline.setOnFinished(event -> {
            try {
                App.setRoot("Timer"); // reset timer
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", CommandTxtField.getText()); //get command
                builder.redirectErrorStream(true);
                builder.start();
                CLOSE_APP();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StartBtn.disableProperty().bind(Bindings.isEmpty(HoursTxtField.textProperty())
                .and(Bindings.isEmpty(MinTxtField.textProperty()))
                .and(Bindings.isEmpty(SecTxtField.textProperty()))
                .or(Bindings.isEmpty(CommandTxtField.textProperty())));

        taskLbl.setText(resourceBundle.getString("tskCommandLbl"));
    }

}
