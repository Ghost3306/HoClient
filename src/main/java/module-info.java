module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;
    requires jfoenix;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
}