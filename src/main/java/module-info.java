module org.example.demodictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    // requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.net.http;
    requires freetts;
    requires javafx.media;

    opens org.example.demodictionary to javafx.fxml;
    exports org.example.demodictionary;
    exports org.example.controllers;
    opens org.example.controllers to javafx.fxml;
}