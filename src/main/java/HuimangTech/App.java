package HuimangTech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Authour 7odaifa_ab
 */
public class App extends Application {

    private static Scene scene;
    public static Stage primaryStage = null;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainUI"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        primaryStage = stage;
        makeStageDraggable();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.bundles.TaskTimer", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/resources/" + fxml + ".fxml"), bundle);
        return fxmlLoader.load();
    }

    private void makeStageDraggable() {
        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);

        scene.setOnMousePressed(event -> {
            xOffset.set(event.getSceneX());
            yOffset.set(event.getSceneY());
        });
        scene.setOnMouseDragged(event -> {
            App.primaryStage.setX(event.getScreenX() - xOffset.get());
            App.primaryStage.setY(event.getScreenY() - yOffset.get());
        });
        scene.setOnDragDone((e) -> App.primaryStage.setOpacity(1.0f));
        scene.setOnMouseDragReleased((e) -> {
            if (e.isConsumed()) {
                App.primaryStage.setOpacity(1.0f);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }


}
