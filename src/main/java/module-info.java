module TaskTimer {

    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens HuimangTech to javafx.graphics, javafx.fxml;

    exports HuimangTech;

}